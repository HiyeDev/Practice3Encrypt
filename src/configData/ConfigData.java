package configData;

public class ConfigData {
    private static ConfigData instance;

    private int[] primeNumbers; //Array que guarda los numeros primos dado por el codigo
    private int primeNumberCode; //Es el codigo que se introduce para la encriptación, esto se usara con un contador que cuando llegue a este numero el contador se reiniciara.
    private int sizeLine; //El codigo * 2 que indica la cantidad de caracteres en cada linea, tendra un contador que cuando llegue a este numero añadira un \n en el string generado
    private int notAlphanumericCharactersSymbols; //El codigo -2, que indica la cantidad de caracteres de relleno entre el inicio y final de un no alfanumerico

    private int countPrimeArray = 0; //Contador para indicar porque indice va de la array {primeNumbers}
    private int countCharacterEncryptPosition = 0; // Contador que indica si va una letra verdadera o no {primeNumbers}
    private int countSizeLine = 0; //Contador que cuenta hasta llegar al tamaño maximo de la linea {sizeLine}
    private int countRandomSymbolNotAlphanumeric = 0;
    private int countMessage = 0; // Contador que indica porque letra va del mensaje {mensaje}

    private ConfigData() {

    }

    public static ConfigData getInstance() {
        if (instance == null) {
            synchronized (ConfigData.class) {
                if (instance == null) {
                    instance = new ConfigData();
                }
            }
        }
        return instance;
    }

    public int[] getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(int[] primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public int getPrimeNumberCode() {
        return primeNumberCode;
    }

    public void setPrimeNumberCode(int primeNumberCode) {
        this.primeNumberCode = primeNumberCode;
    }

    public int getSizeLine() {
        return sizeLine;
    }

    public void setSizeLine(int sizeLine) {
        this.sizeLine = sizeLine;
    }

    public int getNotAlphanumericCharactersSymbols() {
        return notAlphanumericCharactersSymbols;
    }

    public void setNotAlphanumericCharactersSymbols(int notAlphanumericCharactersSymbols) {
        this.notAlphanumericCharactersSymbols = notAlphanumericCharactersSymbols;
    }

    public int getCountPrimeArray() {
        return countPrimeArray;
    }

    public void incrementCountPrimeArray() {
        this.countPrimeArray++;
    }

    public void setCountPrimeArray(int countPrimeArray) {
        this.countPrimeArray = countPrimeArray;
    }

    public int getCountCharacterEncryptPosition() {
        return countCharacterEncryptPosition;
    }

    public void incrementCountCharacterEncryptPosition() {
        this.countCharacterEncryptPosition++;
    }

    public void setCountCharacterEncryptPosition(int countLetterEncryptPosition) {
        this.countCharacterEncryptPosition = countLetterEncryptPosition;
    }

    public int getCountSizeLine() {
        return countSizeLine;
    }

    public void incrementCountSizeLine() {
        this.countSizeLine++;
    }

    public void setCountSizeLine(int countSizeLine) {
        this.countSizeLine = countSizeLine;
    }

    public int getCountRandomSymbolNotAlphanumeric() {
        return countRandomSymbolNotAlphanumeric;
    }

    public void setCountRandomSymbolNotAlphanumeric(int countRandomSymbolNotAlphanumeric) {
        this.countRandomSymbolNotAlphanumeric = countRandomSymbolNotAlphanumeric;
    }

    public void incrementCountMessage() {
        this.countMessage++;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public void resetAllCount() {
        setCountMessage(0);
        setCountSizeLine(0);
        setCountPrimeArray(0);
        setCountCharacterEncryptPosition(1);
    }
}
