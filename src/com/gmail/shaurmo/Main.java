package com.gmail.shaurmo;

public class Main {

    public static void main(String[] args) {
        Contact contact = new Contact("Alex", 1234567, "alex@gaf.com", "Some info");
        System.out.println(contact.toString());
        String serializedStr = null;
        try {
            serializedStr = Serializer.serialize(contact);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Serialization string: " + serializedStr);
        try {
            Contact contact2 = Serializer.deserialize(serializedStr, Contact.class);
            System.out.println("Deserialization string: " + contact2.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
