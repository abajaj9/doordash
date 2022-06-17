package com.doordash.doordash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class AuroraConnectionFactory implements IAuroraConnectionFactory {

    private final IGenerateRDSToken generateRDSToken;
    private final RDSConnectionOptions rdsConnectionOptions;
    private Connection connection;
    @Autowired
    public AuroraConnectionFactory(IGenerateRDSToken generateRDSToken, RDSConnectionOptions rdsConnectionOptions){
        this.generateRDSToken = generateRDSToken;
        this.rdsConnectionOptions = rdsConnectionOptions;
    }

    @Override
    public Connection GetConnection () throws SQLException {
        if(connection != null) return connection;
        //String token = generateRDSToken.GetToken();

        Properties mysqlConnectionProperties = new Properties();
        // mysqlConnectionProperties.setProperty("verifyServerCertificate","true");
        mysqlConnectionProperties.setProperty("useSSL", "false");
        mysqlConnectionProperties.setProperty("user",rdsConnectionOptions.username);
        mysqlConnectionProperties.setProperty("password",generateRDSToken.GetToken());

        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s",rdsConnectionOptions.hostname, rdsConnectionOptions.port), mysqlConnectionProperties);
        return connection;
    }
}
