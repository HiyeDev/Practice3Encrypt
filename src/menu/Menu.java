package menu;

import decrypt.CodeDecrypt;
import decrypt.TypeDecrypt;
import encrypt.CodeEncrypt;
import encrypt.TypeEncrypt;

import java.util.Scanner;

public class Menu {
    private final String HEADER_MENU = "-----------MENU-----------";
    private final String BACKSPACE_MENU = "|                        |";
    private final String FOOTER_MENU = "--------------------------";
    private TypeEncrypt typeEncrypt;
    private TypeDecrypt typeDecrypt;
    private Scanner scanner;

    public Menu () {
        scanner = new Scanner(System.in);
    }

    public void initMenu() {
        int optionSelected;

        System.out.println(HEADER_MENU);
        System.out.println("| (1) ENCRYPT MESSSAGE   |");
        System.out.println(BACKSPACE_MENU);
        System.out.println("| (2) DECRYPT MESSAGE    |");
        System.out.println(BACKSPACE_MENU);
        System.out.println("| (0) EXIT               |");
        System.out.println(FOOTER_MENU);

        optionSelected = scanner.nextInt();
        scanner.nextLine();

        switch (optionSelected) {
            case 0:
                System.out.println("Bye!");
                return;
            case 1:
                encryptMessage();
                newOperation();
                return;
            case 2:
                decryptMessage();
                newOperation();
                return;
            default:
                System.out.println("Enter a valid option");
                initMenu();
        }

    }

    private void newOperation() {
        String s = "";

        System.out.print("Want make new operation: (y) (n)");
        s = scanner.nextLine();
        if (s.equalsIgnoreCase("y")) {
            initMenu();
        }
    }

    private void decryptMessage() {
        String message = "";
        String inputScanner = "";
        int code = 0;

        System.out.println("Enter encrypted lines (write 'end' for finish):");
        while (!inputScanner.equalsIgnoreCase("end")) {
            inputScanner = scanner.nextLine();
            if (!inputScanner.equalsIgnoreCase("end")) {
                message += inputScanner + '\n';
            }
        }
        System.out.print("Enter code: ");
        if (scanner.hasNextInt()) {
            code = scanner.nextInt();
            scanner.nextLine();
            typeDecrypt = new TypeDecrypt(message, code);
            System.out.println("MESSAGE DECRYPTED:");
            System.out.println(typeDecrypt.decryptMessage());
        } else {
            System.out.println("Enter a valid code");
            initMenu();
        }


    }

    private void encryptMessage() {
        String message = "";
        int code = 0;

        System.out.print("Insert message: ");
        message = scanner.nextLine();
        System.out.print("Insert code: ");
        if (scanner.hasNextInt()) {
            code = scanner.nextInt();
            scanner.nextLine();
            typeEncrypt = new TypeEncrypt(message, code);
            System.out.println("MESSAGE ENCRYPTED:");
            System.out.println(typeEncrypt.encryptMessage());
        } else {
            System.out.println("Enter a valid code");
            initMenu();
        }
    }
}
