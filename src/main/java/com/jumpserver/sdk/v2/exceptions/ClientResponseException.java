package com.jumpserver.sdk.v2.exceptions;


public class ClientResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientResponseException(String message) {
        super(message);
    }

}
