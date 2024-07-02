package test;

import decrypt.TypeDecrypt;

public class TypeDecryptTest {
    public static void main(String[] args) {
        TypeDecrypt typeDecrypt = new TypeDecrypt("2jrVP4hh[:\n" +
                "\\{D&-4ÑXPR\n" +
                "8Kvs{ZVRdn", 5);
        System.out.println(typeDecrypt.decryptMessage());
    }
}
