package com.doordash.doordash;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateRDSToken implements IGenerateRDSToken {

    private final RDSTokenOptions tokenOptions;
    private final AWSCredentials awsCredentials;

    @Autowired
    public GenerateRDSToken(RDSTokenOptions tokenOptions, AWSCredentials awsCredentials){
        this.tokenOptions = tokenOptions;
        this.awsCredentials = awsCredentials;
    }

    @Override
    public String GetToken() {
        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
                .credentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsCredentials.AccessKeyId, awsCredentials.SecretKeyId)))
                .region(tokenOptions.region)
                .build();

        return generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(tokenOptions.hostname)
                        .port(tokenOptions.port)
                        .userName(tokenOptions.username)
                        .build());
    }
}
