package com.wkrol.logreader.tests;

import com.wkrol.logreader.LogProccesingResult;
import com.wkrol.logreader.RequestTimeAndSize;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RequestTimeAndSizeTests {

    @Test
    public void equalsTest_Equals() {
        RequestTimeAndSize result1 = new RequestTimeAndSize(2,3);
        RequestTimeAndSize result2 = new RequestTimeAndSize(2,3);

        assertEquals(result1 , result2);
        assertEquals(result2 , result2);
        assertEquals(result1 , result1);
    }

    @Test
    public void equalsTest_NotEquals() {
        RequestTimeAndSize result1 = new RequestTimeAndSize(2,3);
        RequestTimeAndSize result2 = new RequestTimeAndSize(2,2);
        RequestTimeAndSize result3 = new RequestTimeAndSize(3,1);

        assertNotEquals(result1 , result2);
        assertNotEquals(result1 , result3);
        assertNotEquals(result2 , result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSizeClassTest_InvalidSize(){
        RequestTimeAndSize result1 = new RequestTimeAndSize(1_073_741_826,3);
        result1.getSizeClass();
    }

     @Test(expected = IllegalArgumentException.class)
    public void getSizeClassTest_InvalidSizeNegative(){
        RequestTimeAndSize result1 = new RequestTimeAndSize(-1,3);
        result1.getSizeClass();
    }

    @Test()
    public void getSizeClassTest_ValidSize(){
        RequestTimeAndSize result1 = new RequestTimeAndSize(800,3);
        RequestTimeAndSize result2 = new RequestTimeAndSize(100_2400,3);
        RequestTimeAndSize result3 = new RequestTimeAndSize(1_048_576,3);
        RequestTimeAndSize result4 = new RequestTimeAndSize(10_485_760,3);
        RequestTimeAndSize result5 = new RequestTimeAndSize(104_857_600,3);

        assertEquals(result1.getSizeClass(), "<100kB");
        assertEquals(result2.getSizeClass(), ">100kB<1MB");
        assertEquals(result3.getSizeClass(), ">1M<10MB");
        assertEquals(result4.getSizeClass(), ">10MB<100MB");
        assertEquals(result5.getSizeClass(),">100MB<1GB");

    }

}
