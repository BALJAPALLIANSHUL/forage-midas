package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionKafkaListener {
    @Autowired
    private TransactionService transactionService;
    @KafkaListener(topics = "#{'${general.kafka-topic}'}", groupId = "midas-core-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Transaction transaction) {
        // This method will be called for each incoming Transaction message
        
        //Transaction processing
        transactionService.processTransaction(transaction);
        System.out.println(transaction);
    }
}
