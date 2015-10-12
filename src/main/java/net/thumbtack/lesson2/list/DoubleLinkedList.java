package net.thumbtack.lesson2.list;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
public class DoubleLinkedList {

    Node head;
    Node last;

    public DoubleLinkedList() {

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

    //Все кроме 1го
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

    //все кроме последнего
    public DoubleLinkedList body() {
        return null;
    }
}
