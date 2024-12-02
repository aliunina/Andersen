package model.storage;

import java.util.Arrays;

public class CustomArrayList<T> {
    private T list[];

    public CustomArrayList() {
        list = (T[]) new Object[0];
    }

    public void add(T element) {
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = element;
    }

    public T getByIndex(int index) {
        return list[index];
    }

    public void delete(int index) {
        T[] result = (T[]) new Object[list.length - 1];

        for (int i = 0; i < list.length; i++) {
            if (i != index) {
                int newIndex = i < index ? i : i - 1;
                result[newIndex] = list[i];
            }
        }

        list = result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            res.append(i + ") " + list[i].toString() + "\n");
        }
        return res.toString();
    }

}
