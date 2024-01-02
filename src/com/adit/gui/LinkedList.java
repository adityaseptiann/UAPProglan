package com.adit.gui;

import java.util.Iterator;

public class LinkedList <Item> implements Iterable<Item> {

    private Node first = new Node();

    @Override
    public Iterator<Item> iterator() {
        {
            return new ListIterator();

        }
    }
    private class Node {
        Item item;
        Node next;
    }
    public void insertFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public void insertLast(Item item) {
        var newNode = new Node();
        newNode.item = item;
        newNode.next = new Node();

        if (first == null) {
            first = newNode;

            return;
        }

        Node next = first.next;

        do {
            if (next.item == null) {
                next.item = item;
                next.next = new Node();
                break;
            }

            next = next.next;
        }while (next != null);
    }
    private class ListIterator implements  Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public Item next(){
            Item item = current.item;
            current= current.next;
            return item;
        }
    }
}
