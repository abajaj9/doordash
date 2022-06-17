package com.doordash.doordash.aurora;

import com.doordash.doordash.IAuroraConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuroraConnectionController {
    private final IAuroraConnectionFactory auroraConnectionFactory;

    @Autowired
    public AuroraConnectionController(IAuroraConnectionFactory auroraConnectionFactory) {
        this.auroraConnectionFactory = auroraConnectionFactory;
    }

    @GetMapping("api/aurora")
    public String TestAuroraConnection(){
        try {
            var connection = auroraConnectionFactory.GetConnection();
            return connection.getSchema();
        }
        catch (Exception e){
            return "";
        }
    }
}
