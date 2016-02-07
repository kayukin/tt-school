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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return value == node.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
