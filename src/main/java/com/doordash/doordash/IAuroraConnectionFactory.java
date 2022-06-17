package com.doordash.doordash;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public interface IAuroraConnectionFactory {
    Connection GetConnection() throws SQLException;
}
