package com.example._05lab_springdataintro;

import com.example._05lab_springdataintro.models.Account;
import com.example._05lab_springdataintro.models.User;
import com.example._05lab_springdataintro.services.AccountService;
import com.example._05lab_springdataintro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // System.out.println("Working");

        this.userService.register("Third", 40);

        User user = this.userService.findByusername("First");

        this.accountService.depositMoney(BigDecimal.valueOf(40), 2L);
        System.out.println("depositMoney: " + BigDecimal.valueOf(40));


        this.accountService.withdrawMoney(BigDecimal.valueOf(20), 2L);
        System.out.println("withdrawMoney: " + BigDecimal.valueOf(20));





    }
}
