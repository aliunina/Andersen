package model.storage;

import model.ticket.Ticket;
import java.util.Arrays;

public class TicketArrayList {
    private Ticket[] list;
    private int size;

    public TicketArrayList() {
        list = new Ticket[0];
        size = 0;
    }

    public void add(Ticket ticket) {
        size++;
        list = Arrays.copyOf(list, size);
        list[list.length - 1] = ticket;
    }

    public Ticket getByIndex(int index) {
        try {
            return list[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is out of range while getting element!");
            return null;
        }
    }

    public void delete(int index) {
        try {
            Ticket[] result = new Ticket[size - 1];

            for (int i = 0; i < size; i++) {
                if (i != index) {
                    int newIndex = i < index ? i : i - 1;
                    result[newIndex] = list[i];
                }
            }

            list = result;
            size--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is out of range while deleting element!");
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            res.append(i + ") " + list[i].toString() + "\n");
        }
        return res.toString();
    }

}
