package org.techtown.se_project_manager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void testSum(){
        assertEquals(5, Calculator.sum(2,3));
    }
}
