package encrypt;

import configData.ConfigData;
import symbols.Alphabet;
import symbols.NotAlphanumerics;
import symbols.Numbers;

import java.util.Random;

public class NotAlphanumericEncrypt {
    ConfigData configData;

    public NotAlphanumericEncrypt() {
        configData = ConfigData.getInstance();
    }

    public String checkNotAlphanumeric(char notAlphanumeric) {
        String s = "";
        for ( char c : NotAlphanumerics.NOT_ALPHANUMERICS) {
            if (c == notAlphanumeric) {
                s += encryptNotAlphanumeric(c);
            }
        }
        return s;
    }

    private String encryptNotAlphanumeric(char c) {
        String s = "";
        switch (c) {
            case ' ':
                s += '\\' + fillWithSymbols() + '-';
                break;
            case ',':
                s += '*' + fillWithSymbols() + '<';
                break;
            case '.':
                s += '!' + fillWithSymbols() + '|';
                break;
            case ';':
                s += '=' + fillWithSymbols() + '/';
                break;
            case ':':
                s += '(' + fillWithSymbols() + '$';
                break;
            case '-':
                s += '¡' + fillWithSymbols() + '·';
                break;
            case '_':
                s += ')' + fillWithSymbols() + '}';
                break;
            case '{':
                s += '-' + fillWithSymbols() + '-';
                break;
            case '}':
                s += '€' + fillWithSymbols() + '"';
                break;
            case '[':
                s += '<' + fillWithSymbols() + '(';
                break;
            case ']':
                s += '|' + fillWithSymbols() + '+';
                break;
            case '<':
                s += '/' + fillWithSymbols() + ':';
                break;
            case '>':
                s += '@' + fillWithSymbols() + '*';
                break;
            case '|':
                s += '>' + fillWithSymbols() + ')';
                break;
            case '!':
                s += 'ç' + fillWithSymbols() + '¡';
                break;
            case '@':
                s += '$' + fillWithSymbols() + ',';
                break;
            case '"':
                s += '_' + fillWithSymbols() + '=';
                break;
            case '#':
                s += '%' + fillWithSymbols() + ']';
                break;
            case '·':
                s += '?' + fillWithSymbols() + '.';
                break;
            case '~':
                s += 'ª' + fillWithSymbols() + '\'';
                break;
            case '$':
                s += '¬' + fillWithSymbols() + '{';
                break;
            case '%':
                s += '@' + fillWithSymbols() + '¡';
                break;
            case '¬':
                s += '¿' + fillWithSymbols() + '#';
                break;
            case '&':
                s += '#' + fillWithSymbols() + '#';
                break;
            case '/':
                s += ']' + fillWithSymbols() + '/';
                break;
            case '(':
                s += ':' + fillWithSymbols() + 'º';
                break;
            case ')':
                s += '+' + fillWithSymbols() + '&';
                break;
            case '=':
                s += '\\' + fillWithSymbols() + ',';
                break;
            case '?':
                s += '_' + fillWithSymbols() + '_';
                break;
            case '\'':
                s += '"' + fillWithSymbols() + '€';
                break;
            case '¿':
                s += ':' + fillWithSymbols() + '[';
                break;
            case '¡':
                s += '/' + fillWithSymbols() + '¬';
                break;
            case '\\':
                s += '%' + fillWithSymbols() + '%';
                break;
            case 'º':
                s += '}' + fillWithSymbols() + 'ç';
                break;
            case 'ª':
                s += '-' + fillWithSymbols() + '¿';
                break;
            case '+':
                s += ',' + fillWithSymbols() + '·';
                break;
            case '*':
                s += '(' + fillWithSymbols() + ')';
                break;
        }
        return s;
    }

    private String fillWithSymbols() {
        String s= "";
        for (int i = 0; i < configData.getNotAlphanumericCharactersSymbols(); i++) {
            Random randomCharacter = new Random();
            int rCharacter = randomCharacter.nextInt(4);
            Random randomChar = new Random();
            int rChar;
            if (rCharacter == 1) {
                rChar = randomChar.nextInt(Alphabet.ALPHABET_SIZE);
                s += Alphabet.TYPES_UPPER[rChar];
            } else if (rCharacter == 0) {
                rChar = randomChar.nextInt(Alphabet.ALPHABET_SIZE);
                s += Alphabet.TYPES_LOWER[rChar];
            } else if (rCharacter == 3) {
                rChar = randomChar.nextInt(Numbers.NUMBERS_SIZE);
                s += Numbers.NUMBERS[rChar];
            } else {
                rChar = randomChar.nextInt(NotAlphanumerics.NOT_ALPHANUMERICS_SIZE);
                s += NotAlphanumerics.NOT_ALPHANUMERICS[rChar];
            }
        }
        return s;
    }
}
