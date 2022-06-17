package com.doordash.doordash;

import org.springframework.stereotype.Component;

@Component
public class RDSConnectionOptions {
    String region = "us-east-1";
    String hostname = "db1.cluster-c9xtnsw4bbg7.us-east-1.rds.amazonaws.com";
    int port = 3306;
    String username = "admin";
}
