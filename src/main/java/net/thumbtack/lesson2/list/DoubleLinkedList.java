package net.thumbtack.lesson2.list;

import java.util.Collection;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
public class DoubleLinkedList {

    Node head;
    Node last;

    public DoubleLinkedList() {

    }

    public DoubleLinkedList(int[] arr) {
        for (int i : arr) {
            Add(i);
        }
    }

    public void Add(int e) {
        Node node = new Node(last, null, e);
        if (head == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }

    }

    public int head() {
        if (head == null)
            throw new OperationOnEmptyList("head called on empty list");
        return head.value;
    }

    public int last() {
        if (last == null)
            throw new OperationOnEmptyList("last called on empty list");
        return last.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public DoubleLinkedList rest() {
        if (head == null)
            throw new OperationOnEmptyList("rest called on empty list");
        DoubleLinkedList result = new DoubleLinkedList();
        Node node = head.next;
        while (node != null) {
            result.Add(node.value);
            node = node.next;
        }
        return result;
    }

    public DoubleLinkedList body() {
        if (head == null)
            throw new OperationOnEmptyList("rest called on empty list");
        DoubleLinkedList result = new DoubleLinkedList();
        Node node = head;
        while (node.next != null) {
            result.Add(node.value);
            node = node.next;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleLinkedList that = (DoubleLinkedList) o;

        for (Node thisNode = head, thatNode = that.head; thisNode != null && thatNode != null; thisNode = thisNode.next, thatNode = thatNode.next) {
            if (!thisNode.equals(thatNode))
                return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        return head != null ? head.hashCode() : 0;
    }

    @Override
    public String toString() {
        String result = "";
        for (Node node = head; node != null; node = node.next) {
            result += node + " ";
        }
        return result.substring(0, result.length() - 1);
    }

    

}
