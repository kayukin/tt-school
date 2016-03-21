package net.thumbtack.lesson2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
public class MathUtilsTest {
    @Test
    public void test5Plus5Equals10() throws Exception {
        //arrange
        int a = 5, b = 5;
        //act
        int actual = MathUtils.sum(a, b);
        //assert
        assertEquals(10, actual);
    }
}