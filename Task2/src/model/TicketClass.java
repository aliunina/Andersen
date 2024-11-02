package model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public abstract class TicketClass {
    public short classId;

    public short getClassId() {
        return this.classId;
    }

    public void setClassId(short id) {
        this.classId = id;
    }

    public void print() {
        List<Field> fields = Arrays.asList(this.getClass().getDeclaredFields());
        List<Method> methods = Arrays.asList(this.getClass().getDeclaredMethods());

        System.out.println("Class id: " + classId);
        System.out.println("Class fields:");
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("Class methods:");
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
