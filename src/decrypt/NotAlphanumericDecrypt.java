package decrypt;

import configData.ConfigData;

public class NotAlphanumericDecrypt {
    ConfigData configData;
    String encryptMessage;

    public NotAlphanumericDecrypt(String encryptMessage) {
        configData = ConfigData.getInstance();
        this.encryptMessage = encryptMessage;
    }

}
