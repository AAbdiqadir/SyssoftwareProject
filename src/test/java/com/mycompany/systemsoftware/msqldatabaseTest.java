/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.systemsoftware;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ntu-user
 */
public class msqldatabaseTest {
    
    public msqldatabaseTest() {
    }

    /**
     * Test of makeJDBCConnection method, of class msqldatabase.
     */
    @Test
    public void testMakeJDBCConnection() {
        System.out.println("makeJDBCConnection");
        msqldatabase instance = new msqldatabase();
        instance.makeJDBCConnection();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addDataToDB method, of class msqldatabase.
     */
    //@Test
//    public void testAddDataToDB() {
//        System.out.println("addDataToDB");
//        String user = "";
//        String password = "";
//        String permission = "";
//        msqldatabase instance = new msqldatabase();
//        
//        instance.addDataToDB(user, password, permission);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of decodePassword method, of class msqldatabase.
     */
    @Test
    public void testDecodePassword() {
        System.out.println("decodePassword");
        String encodedPassword = "";
        msqldatabase instance = new msqldatabase();
        String expResult = "";
        String result = instance.decodePassword(encodedPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encodePassword method, of class msqldatabase.
     */
    @Test
    public void testEncodePassword() {
        System.out.println("encodePassword");
        String plainPassword = "";
        msqldatabase instance = new msqldatabase();
        String expResult = "";
        String result = instance.encodePassword(plainPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of validateUser method, of class msqldatabase.
     */
//    @Test
//    public void testValidateUser() {
//        System.out.println("validateUser");
//        String user = "";
//        String pass = "";
//        msqldatabase instance = new msqldatabase();
//        boolean expResult = false;
//        boolean result = instance.validateUser(user, pass);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
