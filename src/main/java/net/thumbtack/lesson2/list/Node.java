package net.thumbtack.lesson2.list;

/**
 * Created by kayuk_000 on 11.10.2015.
 */
class Node {
    Node prev;
    Node next;
    int value;

    public Node(Node prev, Node next, int value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}
