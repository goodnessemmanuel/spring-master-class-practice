package com.ocheejeh.springmasterclass.complexscope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//this annotation will ensure new instance of this class is created each time this bean is called
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JdbcConnection2 {
    public JdbcConnection2(){
        System.out.println("JDBC CONNECTION2!!");
    }
}
