package com.jpmc.midascore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "transaction_record")
public class TransactionRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_id", nullable = false)
    private long senderId;
    @Column(name = "recipient_id", nullable = false)
    private long recipientId;
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "is_successful", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSuccessful;

    public TransactionRecord() {
    }

    public TransactionRecord(long senderId, long recipientId, float amount, boolean isSuccessful) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.isSuccessful = isSuccessful;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
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
