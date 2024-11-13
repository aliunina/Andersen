package model.storage;

import model.ticket.Ticket;

import java.util.*;

public class TicketHashSet implements Iterable{
    static final Object DUMMY = new Object();
    private HashMap<Ticket, Object> map;

    public TicketHashSet() {
        map = new HashMap<>();
    }

    public boolean add(Ticket ticket) {
        return map.put(ticket, DUMMY) == null;
    }

    public boolean delete(Ticket ticket) {
        return map.remove(ticket) == DUMMY;
    }

    public boolean contains(Ticket ticket) {
        return map.containsKey(ticket);
    }

    @Override
    public Iterator iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<Ticket> {
        private Ticket[] iterableArr;
        int cursor;
        int lastReturned = -1;

        private KeyIterator() {
            iterableArr = map.keySet().toArray(new Ticket[map.size()]);
        }

        public boolean hasNext() {
            return cursor != map.size();
        }

        public Ticket next() throws NoSuchElementException {
            int i = cursor;
            if (i >= map.size())
                throw new NoSuchElementException();
            cursor = i + 1;
            lastReturned = i;
            return iterableArr[lastReturned];
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int i = 0;
        for (Map.Entry<Ticket, Object> set: map.entrySet()) {
            res.append(i + ") " + set.getKey() + "\n");
            i++;
        }
        return res.toString();
    }
}
