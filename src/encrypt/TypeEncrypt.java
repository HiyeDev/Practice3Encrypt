package encrypt;

import configData.ConfigData;
import decrypt.CodeDecrypt;
import symbols.Alphabet;
import symbols.NotAlphanumerics;
import symbols.Numbers;

import java.util.Random;

public class TypeEncrypt {
    private char[] message;
    ConfigData configData;
    NotAlphanumericEncrypt notAlphanumericsEncrypt;
    CodeEncrypt codeEncrypt;

    public TypeEncrypt(String message, int code) {
        this.message = message.toLowerCase().toCharArray();
        configData = ConfigData.getInstance();
        this.notAlphanumericsEncrypt = new NotAlphanumericEncrypt();
        this.codeEncrypt = new CodeEncrypt(code);
        checkAccentMark();
    }

    public String encryptMessage() {
        String s = "";
        while (configData.getCountMessage() < message.length) {
            resetConts();
            if (isNewLine()) {
                s += "\n";
                configData.setCountSizeLine(0);
            }
            if (isRealChar()) {
                if (isAlphanumeric()) {
                    s += writeRealAlphanumeric();
                } else {
                    s += writeRealNotAlphanumeric();
                }
                configData.incrementCountPrimeArray();
                configData.incrementCountMessage();
            } else if (!isRealChar()) {
                s += writeFalseCharacter();
            }
            configData.incrementCountCharacterEncryptPosition();
            configData.incrementCountSizeLine();

        }
        while (configData.getCountSizeLine() < configData.getSizeLine()) { //Este bucle rellena la linea si acabo el mensaje y hay huecos por rellenar, todos con caracteres falsos
            s += writeFalseCharacter();
            configData.incrementCountSizeLine();
        }
        return s;
    }

    private String writeRealNotAlphanumeric() {
        String s = "";
        String sNotAlphanumeric = notAlphanumericsEncrypt.checkNotAlphanumeric(message[configData.getCountMessage()]);
        char[] cNotAlphanumerics = sNotAlphanumeric.toCharArray();
        for (char c : cNotAlphanumerics) {
            if (isNewLine()) {
                s += "\n";
                configData.setCountSizeLine(0);
            }
            s += c;
            configData.incrementCountSizeLine();
        }
        configData.setCountSizeLine(configData.getCountSizeLine() - 1); // Esta llamada de restarle uno es porque en el metodo principal donde pertence este meotdo, en el final de cada ciclo del while suma uno al contador del tamaño de linea.
        return s;
    }

    private boolean isAlphanumeric() { // Comprueba si es alphanumerico el character despues de comprobar si es
        if (Character.isLetter(message[configData.getCountMessage()]) ||
                Character.isDigit(message[configData.getCountMessage()])) {
            return true;
        } else {
            return false;
        }
    }

    private void resetConts() { // Chekea y resete contadores necesarios antes de un nuevo ciclo del bucle
        if ( configData.getCountPrimeArray() == configData.getPrimeNumberCode()) {
            configData.setCountPrimeArray(0);
        }
        if ( configData.getCountCharacterEncryptPosition() > configData.getPrimeNumbers()[configData.getPrimeNumbers().length - 1]) {
            configData.setCountCharacterEncryptPosition(1);
        }
    }

    private String writeFalseCharacter() {
        String s = "";
        Random randomCharacter = new Random();
        int rCharacter = randomCharacter.nextInt(4);
        Random randomChar = new Random();
        int rChar = 0;
        if (rCharacter == 1) {
            rChar = randomChar.nextInt(Alphabet.ALPHABET_SIZE);
            s += Alphabet.TYPES_UPPER[rChar];
        } else if (rCharacter == 0){
            rChar = randomChar.nextInt(Alphabet.ALPHABET_SIZE);
            s += Alphabet.TYPES_LOWER[rChar];
        } else if (rCharacter == 3) {
            rChar = randomChar.nextInt(Numbers.NUMBERS_SIZE);
            s += Numbers.NUMBERS[rChar];
        } else {
            rChar = randomChar.nextInt(NotAlphanumerics.NOT_ALPHANUMERICS_SIZE);
            s += NotAlphanumerics.NOT_ALPHANUMERICS[rChar];
        }
        return s;
    }

    private String writeRealAlphanumeric() {
        String s = "";
        if (Character.isLetter(message[configData.getCountMessage()])) { //Hace este bloque si es una letra
            for (int i = 0; i < Alphabet.ALPHABET_SIZE; i++) { // Recorre la array del alfabeto
                if (Alphabet.TYPES_LOWER[i] == message[configData.getCountMessage()]) { // Compara que letra es igual en el alfabeto con el mensaje a encriptar
                    s += writeLowerOrUpper(i);
                    break;
                }
            }
        } else if (Character.isDigit(message[configData.getCountMessage()])) { // Hace este bloque si es un digito
            for (int i = 0; i < Numbers.NUMBERS_SIZE; i++) { // Recorre la array de numeros
                if (Numbers.NUMBERS[i] == message[configData.getCountMessage()]) {  // Compara que numero  es igual en el el array de numeros con el mensaje a encriptar
                    s += writeEncryptNumber(i);
                    break;
                }
            }
        }
        return s;
    }

    private String writeEncryptNumber(int i) {
        String s = "";
        int index = i + configData.getPrimeNumbers()[configData.getCountPrimeArray()];
        if (index > Numbers.NUMBERS_SIZE - 1 ) {
            index = index % Numbers.NUMBERS_SIZE;
        }
        s += Numbers.NUMBERS[index];
        return s;
    }

    private String writeLowerOrUpper(int i) {
        String s = "";
        Random random = new Random();
        int r = random.nextInt(2);
        int index = i + configData.getPrimeNumbers()[configData.getCountPrimeArray()]; // Aquí esta haciendo la suma de la letra mas el contenido del array de nuemeros primos.
        if (index > Alphabet.ALPHABET_SIZE - 1) {
            index = index % Alphabet.ALPHABET_SIZE; // El resto se consigue para obtener la posición si el indice es mayor al tamaño del alfabeto, esto se hace de esta forma, ya que en el caso de que sean las ultimas letras del alfabeto con uno de los numeros primos mas altos habria que hacer un bucle o recursividad para sacarlo.
        }
        if (r == 1) {
            s += Alphabet.TYPES_UPPER[index];
        } else {
            s += Alphabet.TYPES_LOWER[index];
        }
        return s;
    }

    private boolean isRealChar() { // Comprueba si el caracter que toca a encryptar es verdadero
        if (configData.getPrimeNumbers()[configData.getCountPrimeArray()] == configData.getCountCharacterEncryptPosition()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNewLine() { // Comprueba si hay que hacer un salto de linea
        if (configData.getCountSizeLine() == configData.getSizeLine()) {
            return true;
        } else {
            return false;
        }
    }
    private void checkAccentMark() {
        for (int i = 0; i < message.length; i++) {
            switch (message[i]) {
                case 'á':
                    message[i] = 'a';
                case 'ä':
                    message[i] = 'a';
                case 'à':
                    message[i] = 'a';
                case 'â':
                    message[i] = 'a';
                    break;
                case 'é':
                    message[i] = 'e';
                case 'ë':
                    message[i] = 'e';
                case 'è':
                    message[i] = 'e';
                case 'ê':
                    message[i] = 'e';
                    break;
                case 'í':
                    message[i] = 'i';
                case 'ï':
                    message[i] = 'i';
                case 'ì':
                    message[i] = 'i';
                case 'î':
                    message[i] = 'i';
                    break;
                case 'ó':
                    message[i] = 'o';
                case 'ö':
                    message[i] = 'o';
                case 'ò':
                    message[i] = 'o';
                case 'ô':
                    message[i] = 'o';
                    break;
                case 'ú':
                    message[i] = 'u';
                case 'ü':
                    message[i] = 'u';
                case 'ù':
                    message[i] = 'u';
                case 'û':
                    message[i] = 'u';
                    break;
            }
        }
    }

    public char[] getMessage() {
        return message;
    }
}