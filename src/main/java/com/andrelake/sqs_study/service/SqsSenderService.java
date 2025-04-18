package com.andrelake.sqs_study.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsSenderService {

    private final SqsClient sqsClient;
    private final String queueUrl = "https://localhost.localstack.cloud:4566/000000000000/test-queue";

    public SqsSenderService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    public void sendMessage(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        sqsClient.sendMessage(request);
        System.out.println("Mensagem enviada: " + message);
    }

}

