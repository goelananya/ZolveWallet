package com.zolve.wallet.bo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long transactionId;
    String userName;
    float transactionAmount;
    String transactionType;
    @CreationTimestamp
    Timestamp time;
}