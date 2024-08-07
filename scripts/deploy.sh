#!/bin/bash

# Check if fintech-dev container is running
IS_DEV=$(docker ps | grep fintech-dev)

HEALTH_CHECK_REQUEST_NGINX=$(bash -c '</dev/tcp/127.0.0.1/80 >/dev/null && echo "Connected" || true')
NGINX_RUNNING=$(docker ps -q -f name=nginx)
DEFAULT_CONF="/etc/nginx/nginx.conf"

if [ -z "$NGINX_RUNNING" ]; then
    echo "Nginx 컨테이너가 실행 중이 아닙니다. nginx 컨테이너를 실행시킵니다."

    docker-compose up -d nginx

    if [ "$HEALTH_CHECK_REQUEST_NGINX" = "Connected" ]; then
        echo "Nginx 컨테이너가 성공적으로 실행되었습니다. [ CONTAINER ID ] : $NGINX_RUNNING"
    else
        echo "Nginx 컨테이너 실행에 실패했습니다... 로그를 확인하세요."
        exit 1
    fi
else
    echo "Nginx 컨테이너가 이미 실행 중입니다."
fi

if [ "$IS_DEV" ]; then
    echo "기존 컨테이너를 중지합니다..."
    sudo docker-compose stop fintech-dev
    sudo docker-compose rm -f fintech-dev
fi

echo "##### NEW VERSION UPDATE #####"

echo "1. 새 버전 이미지 가져오기"
sudo docker-compose pull fintech-dev

echo "2. 새 버전 컨테이너 실행"
sudo docker-compose up -d fintech-dev

counter=0
while true; do
    echo "3. 헬스 체크 진행 중..."
    ((counter++))
    sleep 3

    HEALTH_CHECK_REQUEST=$(curl -s http://172.18.0.1:8080/api/health | grep -o '"status":"[^"]*' | awk -F '"' '{print $4}')
    if [ "$HEALTH_CHECK_REQUEST" = "Connected" ]; then
        echo "헬스 체크 성공! 시도 횟수 : $counter"
        break
    fi
done

echo "4. Nginx 재로드"
docker exec nginx cp /etc/nginx/nginx.conf $DEFAULT_CONF
docker exec nginx nginx -s reload

echo "5. 새 버전 배포 확인"

CURRENT_SERVER_PORT=$(docker exec nginx grep -o 'proxy_pass http://[^:]\+:[0-9]\+' /etc/nginx/nginx.conf | awk -F ':' '{print $NF}' | head -n1)
if [ "$CURRENT_SERVER_PORT" = "8080" ]; then
    echo "서버가 성공적으로 배포되었습니다! [ CURRENT_SERVER_PORT ] : $CURRENT_SERVER_PORT"
fi
