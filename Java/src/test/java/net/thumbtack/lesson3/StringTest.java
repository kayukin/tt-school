package net.thumbtack.lesson3;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kayukin on 18.10.15.
 */
public class StringTest {

    @Test
    public void testFormat() throws Exception {
        assertEquals("a = 10", String.format("a = %d", 10));
    }

    @Test
    public void testTrim() throws Exception {
        assertEquals("abc", "   abc    ".trim());
    }

    @Test
    public void testSplit() throws Exception {
        String[] exp = new String[]{"abc", "qwe", "xyz"};
        String[] act = "abc  qwe xyz".split(" +");
        assertTrue(Arrays.equals(exp, act));
    }

    @Test
    public void testReplaceFirst() throws Exception {
        assertEquals("abcd", "abcd123".replaceFirst("[0-9]+", ""));
    }

    @Test
    public void testReplace() throws Exception {
        assertEquals("tbc", "abc".replace('a', 't'));

    }

    @Test
    public void testGetChars() throws Exception {
        char[] exp = new char[]{'a', 'b', 'c'};
        char[] act = new char[3];
        "abc".getChars(0, 3, act, 0);
        assertTrue(Arrays.equals(exp, act));
    }

    @Test
    public void testLength() throws Exception {
        String str = "abc";
        assertEquals(3, str.length());
    }

    @Test
    public void testCharAt() throws Exception {
        assertEquals('a', "abc".charAt(0));
    }

    @Test
    public void testIndexOf() throws Exception {
        assertEquals(1, "abcb".indexOf('b'));
    }

    @Test
    public void testLastIndexOf() throws Exception {
        assertEquals(3, "abcb".lastIndexOf('b'));
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue("abc".equals("abc"));
    }

    @Test
    public void testEqualsIgnoreCase() throws Exception {
        assertTrue("abc".equalsIgnoreCase("ABC"));
    }

    @Test
    public void testConcat() throws Exception {
        assertEquals("abcqwe", "abc".concat("qwe"));
    }

    @Test
    public void testOperatorPlus() throws Exception {
        assertEquals("abcqwe", "abc" + "qwe");
    }

    @Test
    public void testStringBuilder() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        builder.insert(1, "bb");
        assertEquals("abbbc", builder.toString());
    }

    @Test
    public void testStartsWith() throws Exception {
        assertTrue("abc".startsWith("ab"));
    }

    @Test
    public void testEndsWith() throws Exception {
        assertTrue("qwerty".endsWith("ty"));
    }

    @Test
    public void testSubstring() throws Exception {
        assertEquals("Hello", "Hello world".substring(0, 5));

    }

    @Test
    public void testGetBytes() throws Exception {
        byte[] exp = new byte[]{97, 98, 99};
        byte[] act = "abc".getBytes();
        assertTrue(java.util.Arrays.equals(exp, act));
    }

    @Test
    public void testValueOf() throws Exception {
        assertEquals("true", String.valueOf(true));
    }
}