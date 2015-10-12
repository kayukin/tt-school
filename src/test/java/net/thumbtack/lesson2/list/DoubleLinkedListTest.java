package net.thumbtack.lesson2.list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
public class DoubleLinkedListTest {
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
        DoubleLinkedList list = new DoubleLinkedList();
        list.Add(1);
        list.Add(2);
        list.Add(3);
        assertTrue(true);
    }

    @Test
    public void testEquals() throws Exception {
        DoubleLinkedList list1 = new DoubleLinkedList(), list2 = new DoubleLinkedList();
        list1.Add(1);
        list1.Add(2);
        list1.Add(3);
        list2.Add(1);
        list2.Add(2);
        list2.Add(3);
        assertEquals(list1,list2);

    }
}