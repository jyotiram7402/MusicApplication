package org.gfg.musicapplication.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonS3 s3() {
        BasicAWSCredentials awsCredentials =
                new BasicAWSCredentials("AKIAYGLU35YBKDZSVHNX", "QjGjenKBt7Z8SE+Y8O0RbW7L6PSHD0m0HS852HM+");
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-south-1")
                .withCredentials(new
                        AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}

