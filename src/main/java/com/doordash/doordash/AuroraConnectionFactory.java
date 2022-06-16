package com.doordash.doordash;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

public class AuroraConnectionFactory implements IAuroraConnectionFactory {

    private final IGenerateRDSToken generateRDSToken;

    @Autowired
    public AuroraConnectionFactory(){
    }

    @Override
    public AmazonRDS GetConnection() {
        //String token = generateRDSToken.GetToken();

        var client = AmazonRDSClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials()))
                .build();

        System.out.println(client.describeDBClusters());

        return client;
    }
}
