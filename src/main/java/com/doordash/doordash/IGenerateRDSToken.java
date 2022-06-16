package com.doordash.doordash;

import org.springframework.stereotype.Service;

@Service
public interface IGenerateRDSToken {
    String GetToken();
}
