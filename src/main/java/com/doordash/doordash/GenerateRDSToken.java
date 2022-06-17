package com.doordash.doordash;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateRDSToken implements IGenerateRDSToken {

    private final RDSConnectionOptions rdsConnectionOptions;
    private final AWSCredentials awsCredentials;

    @Autowired
    public GenerateRDSToken(RDSConnectionOptions rdsConnectionOptions, AWSCredentials awsCredentials){
        this.rdsConnectionOptions = rdsConnectionOptions;
        this.awsCredentials = awsCredentials;
    }

    @Override
    public String GetToken() {
        // TODO: change to environment variable based credentials
        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
                .credentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsCredentials.AccessKeyId, awsCredentials.SecretKeyId)))
                .region(rdsConnectionOptions.region)
                .build();

        return generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(rdsConnectionOptions.hostname)
                        .port(rdsConnectionOptions.port)
                        .userName(rdsConnectionOptions.username)
                        .build());
    }
}
