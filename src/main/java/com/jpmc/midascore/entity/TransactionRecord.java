package com.jpmc.midascore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "transaction_record")
public class TransactionRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserRecord sender;
    @ManyToOne(targetEntity = UserRecord.class)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserRecord recipient;
    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "incentive", nullable = true, columnDefinition = "FLOAT DEFAULT 0")
    private float incentive;

    public TransactionRecord() {
    }

    public TransactionRecord(UserRecord sender, UserRecord recipient, float amount,  float incentive) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
    public UserRecord getSender() {
        return sender;
    }
    public void setSender(UserRecord sender) {
        this.sender = sender;
    }
    public UserRecord getRecipient() {
        return recipient;
    }
    public void setRecipient(UserRecord recipient) {
        this.recipient = recipient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public String toString() {
        return "TransactionRecord {senderId=" + senderId + ", recipientId=" + recipientId + ", amount=" + amount + ", isSuccessful=" + isSuccessful + "}";
    }
}
