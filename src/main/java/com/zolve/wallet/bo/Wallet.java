package com.zolve.wallet.bo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Wallet {
    @Id
    Long userId;
    float balance;
}