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
        DoubleLinkedList list=new DoubleLinkedList();
        list.Add(1);
        list.Add(2);
        list.Add(3);

    }
}