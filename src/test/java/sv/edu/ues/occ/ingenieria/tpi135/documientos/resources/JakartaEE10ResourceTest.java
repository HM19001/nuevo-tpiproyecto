/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.resources;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alexo
 */
public class JakartaEE10ResourceTest {
    
    public JakartaEE10ResourceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testPing() {
        System.out.println("ping");
        JakartaEE10Resource instance = new JakartaEE10Resource();
        Response expResult = Response
                .ok("ping Jakarta EE")
                .build();
        Response result = instance.ping();
        assertTrue(result.getStatus() == 200);
//        fail("The test case is a prototype.");
    }
    
}
