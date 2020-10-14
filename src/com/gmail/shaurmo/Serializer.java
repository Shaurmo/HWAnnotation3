package com.gmail.shaurmo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

public class Serializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        Class<?> cls = obj.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                }
                stringBuilder.append(field.getName())
                        .append("=")
                        .append(field.get(obj))
                        .append(";");

            }
        }
        return stringBuilder.toString();
    }

    public static <T> T deserialize(String str, Class<?> cls) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T result = (T) cls.newInstance();
        String[] nameValuePairs = str.split(";");
        for (String nameValuePair : nameValuePairs) {
            String[] nameValue = nameValuePair.split("=");
            if (nameValue.length != 2) {
                throw new InvalidParameterException(str);
            }
            Field field = cls.getDeclaredField(nameValue[0]);
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            if (field.isAnnotationPresent(Save.class)) {
                if (field.getType() == int.class) {
                    field.setInt(result, Integer.parseInt(nameValue[1]));
                } else if (field.getType() == String.class) {
                    field.set(result, nameValue[1]);
                } else if (field.getType() == long.class) {
                    field.setLong(result, Long.parseLong(nameValue[1]));
                } else if (field.getType() == boolean.class) {
                    field.setBoolean(result, Boolean.parseBoolean(nameValue[1]));
                } else {
                    throw new IllegalArgumentException("Fields of " + field.getType().toString() + " are not supported");
                }
            }

        }
        return result;
    }
}
