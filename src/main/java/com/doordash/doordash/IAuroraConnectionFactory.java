package com.doordash.doordash;
import com.amazonaws.services.rds.AmazonRDS;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface IAuroraConnectionFactory {
    AmazonRDS GetConnection();
}
