package com.wkrol.logreader.tests;

import com.wkrol.logreader.LogProccesingResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LogProccesingResultTest {

    @Test
    public void equalsTest_Equals() {
        LogProccesingResult result1 = new LogProccesingResult("aa",2,2,3);
        LogProccesingResult result2 = new LogProccesingResult("aa",2,2,3);

        assertEquals(result1 , result2);
        assertEquals(result2 , result2);
        assertEquals(result1 , result1);
    }

    @Test
    public void equalsTest_NotEquals() {
        LogProccesingResult result1 = new LogProccesingResult("aa",2,2,3);
        LogProccesingResult result2 = new LogProccesingResult("ab",2,2,3);
        LogProccesingResult result3 = new LogProccesingResult("aa",3,2,3);
        LogProccesingResult result4 = new LogProccesingResult("aa",2,3,3);
        LogProccesingResult result5 = new LogProccesingResult("aa",2,2,4);

        assertNotEquals(result1 , result2);
        assertNotEquals(result1 , result3);
        assertNotEquals(result1 , result4);
        assertNotEquals(result1 , result5);
        assertNotEquals(result2 , result3);
        assertNotEquals(result2 , result4);
        assertNotEquals(result2 , result5);
        assertNotEquals(result3 , result4);
        assertNotEquals(result3 , result5);
        assertNotEquals(result4 , result5);

    }
}
