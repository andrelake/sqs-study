package com.andrelake.sqs_study.controller;

import com.andrelake.sqs_study.service.S3NotificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3NotificationService s3NotificationService;

    public S3Controller(S3NotificationService s3NotificationService) {
        this.s3NotificationService = s3NotificationService;
    }

    @RequestMapping("/notification/configure")
    public void receiveNotification() {
        s3NotificationService.configureBucketNotification();
    }

}
