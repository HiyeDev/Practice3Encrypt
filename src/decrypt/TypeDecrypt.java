package decrypt;

import configData.ConfigData;
import symbols.Alphabet;
import symbols.Numbers;

public class TypeDecrypt {
    private char[] encryptedMessage;
    ConfigData configData;

    public TypeDecrypt (String message) {
        this.encryptedMessage = message.toLowerCase().toCharArray();
        configData = ConfigData.getInstance();
    }

    public String decryptMessage() {
        String s = "";
        for (int i = 0; i < encryptedMessage.length; i++) {
            if (encryptedMessage[i] == '\n') {
                continue;
            }
            if (isRealChar()) {
                if (isAlphanumeric(encryptedMessage[i])) {
                    s += decryptAlphanumeric(encryptedMessage[i]);
                } else {
                    s += decryptNotAlphanumeric(encryptedMessage[i]);
                }
            }
        }
        return s;
    }

    private String decryptNotAlphanumeric(char c) {
        String s = "";

        return s;
    }

    private String decryptAlphanumeric(char c) {
        String s = "";
        if (Character.isLetter(c)) {
            for (int i = 0; i < Alphabet.ALPHABET_SIZE; i++) {
                if (Alphabet.TYPES_LOWER[i] == c) {
                    s += Alphabet.TYPES_LOWER[i - configData.getPrimeNumbers()[configData.getCountPrimeArray()]];
                    break;
                }
            }
        } else if (Character.isDigit(c)) {
            for (int i = 0; i < Numbers.NUMBERS_SIZE; i++) {
                if (Numbers.NUMBERS[i] == c) {
                    s += Numbers.NUMBERS[i - configData.getPrimeNumbers()[configData.getCountPrimeArray()]];
                }
            }
        }
        return s;
    }

    private boolean isAlphanumeric(char c) {
        if (Character.isLetter(c) ||
                Character.isDigit(c)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRealChar() {
        if (configData.getPrimeNumbers()[configData.getCountPrimeArray()] == configData.getCountCharacterEncryptPosition()) {
            return true;
        } else {
            return false;
        }
    }
}
