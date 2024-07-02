package test;

import encrypt.CodeEncrypt;
import encrypt.TypeEncrypt;

public class TypeEncryptTest {
    public static void main(String[] args) {
        TypeEncrypt typesEncryptation = new TypeEncrypt("hola mundo",5);
        System.out.println(typesEncryptation.encryptMessage());
    }
}
