package com.core.backend.auth.util;

import com.core.backend.auth.exception.AuthException;
import com.core.backend.common.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BearerTokenParser {

    private static final String TOKEN_TYPE = "Bearer";
    private static final int TOKEN_TYPE_LOCATION = 0;
    private static final int ACCESS_TOKEN_LOCATION = 1;
    private static final int HEADER_SIZE = 2;

    public String parse(String authorizationHeader) {
        validationAuthHeader(Objects.isNull(authorizationHeader));

        String[] split = splitTypeAndCredentials(authorizationHeader);
        validationAuthHeader(split.length != HEADER_SIZE || !split[TOKEN_TYPE_LOCATION].equals(TOKEN_TYPE));

        return split[ACCESS_TOKEN_LOCATION];
    }

    private String[] splitTypeAndCredentials(String authorizationHeader) {
        return authorizationHeader.split(" ");
    }

    private void validationAuthHeader(boolean authorizationHeader) {
        if (authorizationHeader) {
            throw new AuthException(ErrorCode.INVALID_TOKEN_FORMAT);
        }
    }

}
