package com.zolve.wallet.bo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    Long userId;
    String phoneNumber;
}
