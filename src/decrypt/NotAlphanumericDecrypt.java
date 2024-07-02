package decrypt;

import configData.ConfigData;
import symbols.NotAlphanumerics;

public class NotAlphanumericDecrypt {
    ConfigData configData;
    char[] encryptMessage;

    public NotAlphanumericDecrypt(char[] encryptMessage) {
        configData = ConfigData.getInstance();
        this.encryptMessage = encryptMessage;
    }

    public String checkNotAlphanumeric(char c) {
        for ( char notAlphanumeric : NotAlphanumerics.NOT_ALPHANUMERICS) {
            if (c == notAlphanumeric) {
                return decryptNotAlphanumeric(c);
            }
        }
        return "";
    }

    private String decryptNotAlphanumeric(char c) {
        int jumpsMessage = configData.getNotAlphanumericCharactersSymbols() + 1;
        int endNotAlphanumeric = configData.getCountMessage() + jumpsMessage;

        configData.setCountMessage(endNotAlphanumeric);
        return getNotAlphanumeric(c, endNotAlphanumeric);
    }

    private String getNotAlphanumeric(char c, int endNotAlphanumeric) {
        switch (c) {
            case '\\':
                return decryptBacklash(endNotAlphanumeric);
            case '(':
                return decryptOpenParenthesis(endNotAlphanumeric);
            case '-':
                return decryptHyphen(endNotAlphanumeric);
            case '/':
                return decryptSlash(endNotAlphanumeric);
            case '@':
                return decryptAtSymbol(endNotAlphanumeric);
            case '_':
                return decryptUnderScore(endNotAlphanumeric);
            case ':':
                return decryptColon(endNotAlphanumeric);
        }
        return "";
    }

    private String decryptColon(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == 'º') {
            return "" + '(';
        } else if (this.encryptMessage[endNotAlphanumeric] == '[') {
            return "" + '¿';
        }
        return "";
    }

    private String decryptUnderScore(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == '=') {
            return "" + '"';
        } else if (this.encryptMessage[endNotAlphanumeric] == '_') {
            return "" + '?';
        }
        return "";
    }

    private String decryptAtSymbol(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == '*') {
            return "" + '>';
        } else if (this.encryptMessage[endNotAlphanumeric] == '¡') {
            return "" + '%';
        }
        return "";
    }

    private String decryptSlash(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == ':') {
            return "" + '<';
        } else if (this.encryptMessage[endNotAlphanumeric] == '¬') {
            return "" + '¬';
        }
        return "";
    }

    private String decryptHyphen(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == '-') {
            return "" + '{';
        } else if (this.encryptMessage[endNotAlphanumeric] == '¿') {
            return "" + 'ª';
        }
        return "";
    }

    private String decryptOpenParenthesis(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == '$') {
            return "" + ':';
        } else if (this.encryptMessage[endNotAlphanumeric] == ')') {
            return "" + '*';
        }
        return "";
    }

    private String decryptBacklash(int endNotAlphanumeric) {
        if (this.encryptMessage[endNotAlphanumeric] == '-') {
            return "" + ' ';
        } else if (this.encryptMessage[endNotAlphanumeric] == ',') {
            return "" + '=';
        }
        return "";
    }


}
