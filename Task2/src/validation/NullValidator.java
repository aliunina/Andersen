package validation;

import java.lang.reflect.Field;

public class NullValidator {
    public static void checkNulls(Object obj) {
        Class<?> objClass = obj.getClass();
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        System.out.printf("Variable [%s] is null in [%s]!\n", field.getName(), objClass.getSimpleName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
