package decrypt;

import configData.ConfigData;
import symbols.Alphabet;
import symbols.Numbers;

public class TypeDecrypt {
    private char[] encryptedMessage;
    ConfigData configData;
    NotAlphanumericDecrypt notAlphanumericDecrypt;

    public TypeDecrypt (String message, int code) {
        this.encryptedMessage = message.toLowerCase().toCharArray();
        configData = ConfigData.getInstance();
        notAlphanumericDecrypt = new NotAlphanumericDecrypt(encryptedMessage);
        CodeDecrypt.init(5);
    }

    public String decryptMessage() {
        String s = "";
        while (configData.getCountMessage() < encryptedMessage.length) {
            resetConts();
            char currentChar = encryptedMessage[configData.getCountMessage()];
            if (skipLineJump(currentChar)) {
                configData.incrementCountMessage();
                continue;
            }
            if (isRealChar()) {
                s += decryptMessage(currentChar);
                configData.incrementCountPrimeArray();
            }
            configData.incrementCountCharacterEncryptPosition();
            configData.incrementCountMessage();
        }
        return s;
    }

    private void resetConts() {
        if ( configData.getCountPrimeArray() == configData.getPrimeNumberCode()) {
            configData.setCountPrimeArray(0);
        }
        if ( configData.getCountCharacterEncryptPosition() > configData.getPrimeNumbers()[configData.getPrimeNumbers().length - 1]) {
            configData.setCountCharacterEncryptPosition(1);
        }
    }

    private String decryptMessage(char currentChar) {
        if (isAlphanumeric(currentChar)) {
            return decryptAlphanumeric(currentChar);
        } else {
            return decryptNotAlphanumeric(currentChar);
        }
    }

    private static boolean skipLineJump(char currentChar) {
        return currentChar == '\n';
    }

    private String decryptNotAlphanumeric(char c) {
        return notAlphanumericDecrypt.checkNotAlphanumeric(c);
    }

    private String decryptAlphanumeric(char c) {
        if (Character.isLetter(c)) {
            return getDecryptType(c);
        } else if (Character.isDigit(c)) {
            return getDecryptDigit(c);
            }
        return "";
    }

    private String getDecryptDigit(char c) {
        for (int i = 0; i < Numbers.NUMBERS_SIZE; i++) {
            if (Numbers.NUMBERS[i] == c) {
                int indexDecrypt = getIndexDecrypt(i, Numbers.NUMBERS_SIZE);
                return "" + Numbers.NUMBERS[indexDecrypt];
            }
        }
        return "";
    }

    private String getDecryptType(char c) {
        for (int i = 0; i < Alphabet.ALPHABET_SIZE; i++) {
            if (Alphabet.TYPES_LOWER[i] == c) {
                int indexDecrypt = getIndexDecrypt(i, Alphabet.ALPHABET_SIZE);
                return "" + Alphabet.TYPES_LOWER[indexDecrypt];
            }
        }
        return "";
    }

    private int getIndexDecrypt(int i, int size) {
        int primeNumber = configData.getPrimeNumbers()[configData.getCountPrimeArray()];
        int indexDecrypt = i - primeNumber;
        if (indexDecrypt < 0) {
            indexDecrypt += size;
        }
        return indexDecrypt;
    }

    private boolean isAlphanumeric(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }

    private boolean isRealChar() {
        if (configData.getPrimeNumbers()[configData.getCountPrimeArray()] == configData.getCountCharacterEncryptPosition()) {
            return true;
        } else {
            return false;
        }
    }
}
