package com.example._05lab_springdataintro.services;

import com.example._05lab_springdataintro.exceptions.EntityMessageException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, Long id) throws EntityMessageException;

    void depositMoney(BigDecimal money, Long id);

}
