package model.storage;

import model.ticket.Ticket;

import java.util.*;

public class CustomHashSet<T> implements Iterable {

    private ArrayList<HashValue> list;

    class HashValue implements Comparable {
        T value;
        int hashCode;
        public HashValue(T value) {
            this.value = value;
            this.hashCode = value.hashCode();
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public CustomHashSet() {
        list = new ArrayList<>();
    }

    public CustomHashSet<T> add(T element) {
        if (!contains(element)) {
            list.add(new HashValue(element));
        }
        return this;
    }

    public boolean delete(T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).hashCode == element.hashCode()) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(T element) {
        for (HashValue hashVal : list) {
            if (hashVal.hashCode == element.hashCode() && hashVal.value.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Iterator<HashValue> keyIterator = list.iterator();

            @Override
            public boolean hasNext() {
                return keyIterator.hasNext();
            }

            @Override
            public T next() {
                return keyIterator.next().value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            res.append(i + ") " + list.get(i).value + "\n");
        }
        return res.toString();
    }
}
