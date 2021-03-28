package com.zolve.wallet.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@Data
public class Wallet {
    @Id
    String userName;
    float balance;
    @Version
    @JsonIgnore
    int version;
}