package com.example._05lab_springdataintro.exceptions;

public class EntityMessageException extends RuntimeException {
    public EntityMessageException(String not_valid_account_id) {
        super(not_valid_account_id);
    }
}
