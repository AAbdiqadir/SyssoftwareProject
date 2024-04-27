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
public class BuiltInsTest {
    
    public BuiltInsTest() {
    }

    /**
     * Test of move method, of class BuiltIns.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        String OlderFile = "";
        String NewerFile = "";
        BuiltIns instance = new BuiltIns();
        instance.move(OlderFile, NewerFile);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class BuiltIns.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        String OlderFile = "";
        String NewerFile = "";
        BuiltIns instance = new BuiltIns();
        instance.copy(OlderFile, NewerFile);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
