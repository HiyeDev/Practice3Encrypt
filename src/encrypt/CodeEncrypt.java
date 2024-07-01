package encrypt;

import configData.ConfigData;

public class CodeEncrypt {
    private int code;

    public CodeEncrypt(int code) {
            this.code = code;
            ConfigData configData = ConfigData.getInstance();
            configData.setPrimeNumbers(findFirstPrimes());
            configData.setPrimeNumberCode(this.code);
            configData.setSizeLine(this.code * 2);
            configData.setCountCharacterEncryptPosition(1);
            if (this.code <= 2) {
                configData.setNotAlphanumericCharactersSymbols(0);
            } else {
                configData.setNotAlphanumericCharactersSymbols(this.code - 2);
            }

    }

    public static void init (int code ) {
        int c = 0;
        if (code == 0) {
            c = 1;
        } else if (code > 10){
            c = 10;
        } else {
            c = code;
        }
        CodeEncrypt codeEncrypt = new CodeEncrypt(c);
    }

    private int[] findFirstPrimes() {
        int[] primes = new int[code];
        int count = 0;
        int number = 2;

        while (count < code) {
            if (isPrime(number)) {
                primes[count] = number;
                count++;
            }
            number++;
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;

    }
}
