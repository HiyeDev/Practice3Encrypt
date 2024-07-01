package test;

import encrypt.CodeEncrypt;
import encrypt.NotAlphanumericEncrypt;

public class NotAlphanumericEncryptTest {
    public static void main(String[] args) {
        NotAlphanumericEncrypt notAlphanumericsEncrypt = new NotAlphanumericEncrypt();

        CodeEncrypt.init(8);
        System.out.println(notAlphanumericsEncrypt.checkNotAlphanumeric('='));
    }
}
