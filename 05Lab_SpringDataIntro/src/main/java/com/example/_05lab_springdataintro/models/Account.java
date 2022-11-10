package com.example._05lab_springdataintro.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal balance;

   // @ManyToOne
   // private User user;

    public Account(){
        this.balance=BigDecimal.ZERO;
    }

    public Account(BigDecimal balance, User user) {
        this();
        this.balance = balance;
     //   this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
