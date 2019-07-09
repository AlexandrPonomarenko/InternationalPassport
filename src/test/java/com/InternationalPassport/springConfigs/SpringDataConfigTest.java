package com.InternationalPassport.springConfigs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import static org.junit.Assert.*;

public class SpringDataConfigTest {
    public String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    @Before
    public void setUp() throws Exception {
        System.out.println("SET UP");
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("TEAR DOWN");
        rootPath = null;
    }

    @Test
    public void loadPropFromFileProp() {
        try {
            String appConfig = rootPath + "applicationT.properties";

            Properties prop = new Properties();
            prop.load(new FileInputStream(appConfig));

            String strData = prop.getProperty("jdbc.url");
            String strData2 = prop.getProperty("hibernate.show_sql");
            String strData3 = prop.getProperty("jdbc.password", "root");

            // Update property
            String updateProp = "google.com";


            assertEquals("jdbc:postgresql://localhost:5432/db_intern_pas", strData);
            assertEquals("root", strData3);
            assertEquals("true", strData2);
            System.out.println(strData + " :: " + strData2 + " ::: " + strData3 );

            prop.setProperty("jdbc.url", updateProp);
            String newProp = prop.getProperty("jdbc.url");
            assertEquals(updateProp, newProp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    @Test
//    public void writeToFile() throws IOException {
//        String confPath = rootPath + "defaultTest.properties";
//        Properties prop = new Properties();
//
//        prop.setProperty("ololo", "LALALA");
////        String confPathTwo = rootPath + "defaultTest.properties";
//        prop.store(new FileWriter(confPath), "just comment");
//    }

//    @Test // if current porp have not, should get from def prop? but doesnt work
//    public void defProp() {
//        String str = null;
//        String defProper = rootPath + "defaultTest.properties";
//        Properties defPro = new Properties();
//
//        String curProper = rootPath + "applicationT.properties";
//        Properties curProp = new Properties();
//
//        try {
//            defPro.load(new FileInputStream(defProper));
//            curProp.load(new FileInputStream(curProper));
//            str = curProp.getProperty("hibernate.format");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        assertEquals("format", str);
//    }

    @Test
    public void listProp() throws IOException{

        Properties prop = new Properties();
        String appConfig = rootPath + "applicationT.properties";
        prop.load(new FileInputStream(appConfig));

        prop.list(System.out); // list all key-value pairs

        Enumeration<Object> valueEnumeration = prop.elements();
        while (valueEnumeration.hasMoreElements()) {
            System.out.println(valueEnumeration.nextElement());
        }

        Enumeration<Object> keyEnumeration = prop.keys();
        while (keyEnumeration.hasMoreElements()) {
            System.out.println(keyEnumeration.nextElement());
        }

        int size = prop.size();
        assertEquals(10, size);
    }

    @Test
    public void getEntityManagerFactoryBean() {
    }

    @Test
    public void dataSource() {
    }

    @Test
    public void exceptionTranslation() {
    }

    @Test
    public void transactionManager() {
    }

    @Test
    public void getJpaProperties() {
    }

    @Test
    public void loadProperty(){

    }
}