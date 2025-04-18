package com.andrelake.sqs_study.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Event;
import software.amazon.awssdk.services.s3.model.QueueConfiguration;
import software.amazon.awssdk.services.s3.model.PutBucketNotificationConfigurationRequest;
import software.amazon.awssdk.services.s3.model.NotificationConfiguration;

import java.util.Collections;

@Service
public class S3NotificationService {

    private final S3Client s3Client;

    private String bucketName = "bucket-test";
    private String queueArn = "arn:aws:sqs:us-east-1:000000000000:test-queue";

    public S3NotificationService(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public void configureBucketNotification() {
        QueueConfiguration queueConfig = QueueConfiguration.builder()
                .queueArn(queueArn) // ARN da sua fila SQS
                .eventsWithStrings("s3:ObjectCreated:Put", "s3:ObjectCreated:Post")
                .build();

        NotificationConfiguration notificationConfig = NotificationConfiguration.builder()
                .queueConfigurations(Collections.singletonList(queueConfig))
                .build();

        PutBucketNotificationConfigurationRequest request = PutBucketNotificationConfigurationRequest.builder()
                .bucket(bucketName)
                .notificationConfiguration(notificationConfig)
                .build();

        s3Client.putBucketNotificationConfiguration(request);
        System.out.println("Notificação configurada com sucesso no bucket " + bucketName);
    }
}

