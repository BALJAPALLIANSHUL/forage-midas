package com.jpmc.midascore.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;

@Service
public class TransactionService {
    // Business logic methods for processing transactions would go here
    private UserRepository userRepository;

    @Autowired
    private TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void processTransaction(Transaction transaction) {
        // Implementation of transaction processing logic
        
        //validate sender
        Optional<UserRecord> sender = userRepository.findById(transaction.getSenderId());
        Optional<UserRecord> recipient = userRepository.findById(transaction.getRecipientId());

        if(sender.isPresent() && recipient.isPresent())
        {
            UserRecord senderObj = sender.get();
            UserRecord recipientObj = recipient.get();
            if(senderObj.getBalance()>=transaction.getAmount())
            {
                senderObj.setBalance(senderObj.getBalance()-transaction.getAmount());
                recipientObj.setBalance(recipientObj.getBalance()+transaction.getAmount());
                userRepository.save(senderObj);
                userRepository.save(recipientObj);
                System.out.println("Balance:");
                System.out.println(senderObj.getName()+senderObj.getBalance());
                System.out.println(recipientObj.getName()+recipientObj.getBalance());
            }
            else{
                System.out.println("Insufficient balance");
            }
        }
        else{
            System.out.println("Sender or reciever does not exist in database");
        }
    }
}
