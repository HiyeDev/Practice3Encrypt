package test;

import decrypt.TypeDecrypt;

public class TypeDecryptTest {
    public static void main(String[] args) {
        TypeDecrypt typeDecrypt = new TypeDecrypt("DjR\\PcHC3t\n" +
                "\\¿pp-wñx9R\n" +
                "?KV? z¡QY¡", 5);
        System.out.println(typeDecrypt.decryptMessage());
    }
}
