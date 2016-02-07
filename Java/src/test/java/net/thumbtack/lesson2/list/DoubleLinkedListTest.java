package net.thumbtack.lesson2.list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
public class DoubleLinkedListTest {
    @Test
    public void testToString() throws Exception {
        DoubleLinkedList lst = new DoubleLinkedList(new int[]{1, 2, 3});
        assertEquals("1 2 3", lst.toString());
    }

    @Test(expected = OperationOnEmptyList.class)
    public void testTryHeadOnEmptyList() throws Exception {
        new DoubleLinkedList().head();
    }

    @Test
    public void testNewListIsEmpty() throws Exception {
        assertTrue(new DoubleLinkedList().isEmpty());
    }

    @Test
    public void testRestOn123List() throws Exception {
        DoubleLinkedList list = new DoubleLinkedList(new int[]{1, 2, 3});
        assertEquals(new DoubleLinkedList(new int[]{2, 3}), list.rest());
    }

    @Test
    public void testEquals() throws Exception {
        DoubleLinkedList list1 = new DoubleLinkedList(new int[]{1, 2, 3}), list2 = new DoubleLinkedList(new int[]{1, 2, 3});
        assertEquals(list1, list2);
    }
}