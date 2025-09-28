package com.jpmc.midascore.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.jpmc.midascore.component.RestTemplateClient;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;

@Service
public class TransactionService {
    // Business logic methods for processing transactions would go here
    private UserRepository userRepository;
    private RestTemplateClient restTemplateClient;
    private TransactionRecordRepository transactionRecordRepository;

    private TransactionService(UserRepository userRepository, RestTemplateClient restTemplateClient, TransactionRecordRepository transactionRecordRepository) {
        this.userRepository = userRepository;
        this.restTemplateClient = restTemplateClient;
        this.transactionRecordRepository = transactionRecordRepository;
    }


    public void processTransaction(Transaction transaction) {
        // Implementation of transaction processing logic
        //validate sender
        Optional<UserRecord> sender = userRepository.findById(transaction.getSenderId());
        Optional<UserRecord> recipient = userRepository.findById(transaction.getRecipientId());
        System.out.println("================= Transaction Processing ================");
        if(sender.isPresent() && recipient.isPresent())
        {
            UserRecord senderObj = sender.get();
            UserRecord recipientObj = recipient.get();
            if(senderObj.getBalance()>=transaction.getAmount())
            {
                //Get incentive amount from external service
                Incentive incentive = restTemplateClient.getIncentive(transaction);
                senderObj.setBalance(senderObj.getBalance()-transaction.getAmount()+incentive.getAmount());
                recipientObj.setBalance(recipientObj.getBalance()+transaction.getAmount()+incentive.getAmount());
                userRepository.save(senderObj);
                userRepository.save(recipientObj);
                TransactionRecord transactionRecord = new TransactionRecord(senderObj, recipientObj, transaction.getAmount(), incentive.getAmount());
                transactionRecordRepository.save(transactionRecord);
                // Logging transaction details
                
                System.out.println("Transaction amount:"+transaction.getAmount());
                System.out.println("Incentive amount:"+incentive.getAmount());
                System.out.println("Balance:");
                System.out.println(senderObj.getName()+": "+senderObj.getBalance());
                System.out.println(recipientObj.getName()+": "+recipientObj.getBalance());
                
            }
            else{
                System.out.println("Insufficient balance");
            }
        }
        else{
            System.out.println("Sender or reciever does not exist in database");
        }
        System.out.println("========================================================");
    }
}
