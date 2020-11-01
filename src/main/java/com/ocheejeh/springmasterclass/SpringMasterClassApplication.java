package com.ocheejeh.springmasterclass;

import com.ocheejeh.springmasterclass.complexscope.PersonDAO;
import com.ocheejeh.springmasterclass.complexscope.PersonDAODifferentScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringMasterClassApplication {
    static Logger LOGGER = LoggerFactory.getLogger(SpringMasterClassApplication.class);

    public static void main(String[] args) {
        ApplicationContext  context = SpringApplication.run(SpringMasterClassApplication.class, args);

        /**
         * Spring Bean Scope (beans are different object that are managed by the spring framework)
         * 1. singleton -> One bean instance per Spring Context
         * 2. prototype -> New bean instance whenever requested
         * 3. request -> One bean per HTTP Request
         * 4. session -> One bean per session
         */

        //note that by default the scope of spring bean is singleton...
        PersonDAO personDAO = context.getBean(PersonDAO.class);

        PersonDAO personDAO1 = context.getBean(PersonDAO.class);

        //..for this reason personDAO and personDAO1 both point to the same object(i.e instance)
        //see log in output for confirmation, observe that getJdbcConnection() instance are the same as well

        LOGGER.info("******my test here******");
        LOGGER.info("{}", personDAO); //logs PersonDAO@71104a4
        LOGGER.info("{}", personDAO.getJdbcConnection());//logs JdbcConnection@6e9319f


        LOGGER.info(String.valueOf(personDAO1)); //logs PersonDAO@71104a4
        LOGGER.info(String.valueOf(personDAO1.getJdbcConnection())); //logs JdbcConnection@6e9319f

        //in this case, the scope feature is specified in this PersonDAODifferentScope class component to be prototype
        //hence there will be different instances of this class created each time it is instantiate
        PersonDAODifferentScope personDAODifferentScope1 = context.getBean(PersonDAODifferentScope.class);
        PersonDAODifferentScope personDAODifferentScope2 = context.getBean(PersonDAODifferentScope.class);

        LOGGER.info("*****Bean with different instance******");
        LOGGER.info(String.valueOf(personDAODifferentScope1)); //logs PersonDAODifferentScope@77307458


        LOGGER.info(String.valueOf(personDAODifferentScope2)); //logs PersonDAODifferentScope@1fc0053e

        //note, when a dependency (in this case JdbcConnection2.class) is a prototype but the bean using such
        // dependency is not a prototype(in this case PersonDAO.class) u need to add proxy to the scope
        // feature of such dependency before you can be able to get different instances of such dependency
        //see JdbcConnection2.class for illustration
        LOGGER.info("*****PersonDAO Bean is singleton but its dependency (i.e. JdbcConnection2.class) is a prototype******");
        LOGGER.info("{}", personDAO.getJdbcConnection2()); //logs JdbcConnection2@72c927f1
        LOGGER.info("{}", personDAO1.getJdbcConnection2()); //JdbcConnection2@1ac85b0c


    }

}
