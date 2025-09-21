package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionKafkaListener {
    @KafkaListener(topics = "#{'${general.kafka-topic}'}", groupId = "midas-core-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Transaction transaction) {
        // This method will be called for each incoming Transaction message
        // You can add logging or debugging here to inspect received transactions
        System.out.println(transaction);
    }
}
