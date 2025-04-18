package com.andrelake.sqs_study.controller;

import com.andrelake.sqs_study.service.SqsReceiverService;
import com.andrelake.sqs_study.service.SqsSenderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    private final SqsSenderService sqsSenderService;
    private final SqsReceiverService sqsReceiverService;

    public SqsController(SqsSenderService sqsSenderService, SqsReceiverService sqsReceiverService) {
        this.sqsSenderService = sqsSenderService;
        this.sqsReceiverService = sqsReceiverService;
    }

    @RequestMapping("/send")
    public void sendMessage() {
        sqsSenderService.sendMessage("Teste de envio de mensagem para SQS");
    }

    @RequestMapping("/receive")
    public void receiveMessage() {
        sqsReceiverService.receiveMessages();
    }

}
