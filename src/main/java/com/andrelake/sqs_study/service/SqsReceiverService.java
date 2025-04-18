package com.andrelake.sqs_study.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Service
public class SqsReceiverService {
    private final SqsClient sqsClient;
    private final String queueUrl = "https://localhost.localstack.cloud:4566/000000000000/test-queue";

    public SqsReceiverService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void receiveMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5)
                .build();

        sqsClient.receiveMessage(request)
                .messages()
                .forEach(message -> {
                    System.out.println("Mensagem recebida: " + message.body());
                    // Após processar, exclua a mensagem da fila:
                    sqsClient.deleteMessage(DeleteMessageRequest.builder()
                            .queueUrl(queueUrl)
                            .receiptHandle(message.receiptHandle())
                            .build());
                });
    }
}

