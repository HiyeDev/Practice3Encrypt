package test;

import encrypt.CodeEncrypt;
import encrypt.TypeEncrypt;

public class TypeEncryptTest {
    public static void main(String[] args) {
        CodeEncrypt.init(5);
        TypeEncrypt typesEncryptation = new TypeEncrypt("hola mundo");
        System.out.println(typesEncryptation.encryptMessage());
    }
}
