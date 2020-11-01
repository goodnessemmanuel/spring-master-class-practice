package com.ocheejeh.springmasterclass.complexscope;

import org.springframework.stereotype.Component;

@Component
public class JdbcConnection {
    public JdbcConnection(){
        System.out.println("JDBC CONNECTION!!");
    }
}
