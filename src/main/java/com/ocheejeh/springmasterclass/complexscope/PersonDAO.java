package com.ocheejeh.springmasterclass.complexscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

    @Autowired
    JdbcConnection jdbcConnection;

    @Autowired
    JdbcConnection2 jdbcConnection2;

    public JdbcConnection getJdbcConnection() {
        return jdbcConnection;
    }

    public void setJdbcConnection(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Although this class(i.e PersonDAO ) is not a prototype because it is not annotated with
     * @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE), still if you call
     * @return getJdbcConnection2 you will get different instances of it because the
     * JdbcConnection2 class is annotated with @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
     */
    public JdbcConnection2 getJdbcConnection2() {
        return jdbcConnection2;
    }

    public void setJdbcConnection2(JdbcConnection2 jdbcConnection2) {
        this.jdbcConnection2 = jdbcConnection2;
    }
}
