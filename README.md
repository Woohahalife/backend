![image](https://github.com/user-attachments/assets/fbe932f0-17b6-4e97-8e6e-25cf067ac3b7)
# 스르륵 - QR 결제 기반 자동 & 동시 정산 더치페이 서비스 - 핀테크 인턴십 해커톤 최우수상 수상 👑
## 프로젝트 개요
프로젝트 기간: 2024.07 ~ 2024.08 (약 2주일)

스르륵은 모임 후 정산 과정에서 발생하는 문제를 해결하기 위해 개발된 서비스입니다. 총무가 정산금을 받기 어려워하거나, 모임 멤버들이 정산해야 한다는 사실을 잊는 문제를 해결하고자 QR 결제 기반의 자동 & 동시 정산 서비스를 제공해 문제를 해결하고자 합니다.

## 주요 기능
- **자동 정산**: QR 결제를 통해 모임 멤버들이 간편하게 결제 금액을 자동 정산할 수 있습니다.
- **동시 정산**: 모임 후 정산에 참가하는 멤버들의 승낙 여부를 검토한 후 결제와 동시에 정산을 진행해 정산 누락이나 지연을 방지할 수 있습니다.
- **모임 관리**: 모임 내에서 발생한 정산 내역을 쉽게 확인하고 관리할 수 있는 기능을 제공합니다.

## 기술 스택
[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1724000074135?alt=media&token=bf94d928-3bb3-4e07-bf5b-588032846ee4)](https://github.com/msdio/stackticon)

## ERD
![image](https://github.com/user-attachments/assets/7759f3a7-8ef5-4fa7-a1bc-7cac9cdb40a4)


## 아키텍처 다이어그램
![image](https://github.com/user-attachments/assets/68ef53ca-2e16-4008-830a-4f1a5a3109bc)


## API 명세서

명세 상세 정보 : [https://www.notion.so/joyulmoo/API-6ecb6b44cb5a4de3a33737d132e9e095](https://joyulmoo.notion.site/API-6ecb6b44cb5a4de3a33737d132e9e095)

### Group (그룹 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 사용자 모임방 생성                    | POST   | /api/groups                                   | @GroupRegisterRequest        | @CreateGroupResponse        |
| 사용자가 참여한 모임방 목록 조회       | GET    | /api/groups                                   | @Void                        | @GroupInfoResponse          |
| 모임방 기정산 내역 목록 조회          | GET    | /api/groups/{groupId}/settlements             | PathVariable: groupId        | @GroupSettlementResponse    |
| 모임방 참여 멤버 조회                | GET    | /api/groups/{groupId}/members                 | PathVariable: groupId        | @GroupMemberResponse        |
| 모임 참여자 초대 (초대코드 생성)      | POST   | /api/groups/{groupId}/invite                  | PathVariable: groupId        | @GroupInviteResponse        |
| 모임방 입장 (초대코드 입력 방식)      | POST   | /api/groups/entrance                          | @GroupEntranceRequest        | @GroupEntranceResponse      |

### Settlement (정산 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 기정산 내역 상세 조회               | GET    | /api/settlements/{settlementId}              | PathVariable: settlementId   | @SettlementDetailResponse   |
| 모임방 정산 요청 (정산 생성)         | POST   | /api/settlements/{groupId}/request           | @SettlementRegisterRequest   | @Void                      |
| 요청한 정산 상황 (동의 여부) 조회     | GET    | /api/settlements/{settlementId}              | PathVariable: settlementId   | @SettlementParticipantResponse |
| 정산 여부에 따른 스르륵머니 QR결제    | POST   | /api/settlements/{settlementId}              | PathVariable: settlementId   | @CompletedSettlementResponse |
| 요청받은 정산 내역 조회              | GET    | /api/settlements                             | @Void                        | @RequestedSettlementResponse  |

### Participant (참여자 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 요청받은 정산 동의                   | PUT    | /api/participants/{participantId}            | PathVariable: participantId  | @Void                      |

### Account (계좌 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 사용자 결제수단 등록                 | POST   | /api/accounts/register                       | @AcoountRegisterRequest      | @Void                      |
| 사용자 대표 계좌 등록                | PUT    | /api/accounts/{accountId}/mark               | PathVariable: accountId      | @AccountMarkResponse        |
| 사용자 대표 계좌 해제                | PUT    | /api/accounts/{accountId}/unmark             | PathVariable: accountId      | @AccountMarkResponse        |

### User (사용자 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 사용자 일반 로그인                   | POST   | /api/users/login                             | @UserLoginRequest            | @UserLoginResponse         |
| 사용자 회원가입                      | POST   | /api/users/register                          | @UserRegisterRequest         | @Void                      |
| 사용자 소셜 로그인 (Kakao)            | POST   | /api/users/social                            | @OauthLoginRequest           | @UserLoginResponse         |
| 마이페이지 정보 조회                 | GET    | /api/users                                   | @Void                        | @UserMyPageResponse        |

### Point (포인트 관련 기능)

| 기능                                | 메서드 | 경로                                         | 요청 데이터                 | 응답 데이터                |
|-------------------------------------|--------|----------------------------------------------|-----------------------------|----------------------------|
| 포인트 정보 조회                     | GET    | /api/points                                  | @Void                        | @PointInfoResponse          |
| 계좌로부터 포인트 환전               | POST   | /api/points                                  | @PointConversionRequest      | @PointConversionResponse    |
| 보유 포인트를 계좌 금액으로 환전      | POST   | /api/point/exchange                          | @PointConversionRequest      | @PointConversionResponse    |
