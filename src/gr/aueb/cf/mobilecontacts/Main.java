package gr.aueb.cf.mobilecontacts;

import gr.aueb.cf.mobilecontacts.controller.MobileContactController;
import gr.aueb.cf.mobilecontacts.dto.BaseDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final MobileContactController controller = new MobileContactController();

    public static void main(String[] args) {
        String choice;

        while (true) {
            printMenu();
            choice = getToken();

            if (choice.equals("q") || choice.equals("Q")) {
                break;
            }

            handleChoice(choice);
        }

        System.out.println("Thank you for using Mobile Contacts App");
    }

    public static void handleChoice(String choice) {
        String firstname;
        String lastname;
        String phoneNumber;
        String response;
        MobileContact mobileContact = new MobileContact();

        switch (choice) {
            case "1":
                System.out.println("Παρακαλώ εισάγετε Όνομα, Επώνυμο, Αριθμό Τηλεφώνου");
                firstname = getToken();
                lastname = getToken();
                phoneNumber = getToken();
                MobileContactInsertDTO insertDTO = new MobileContactInsertDTO(firstname, lastname, phoneNumber);
                response = controller.insertContact(insertDTO);
                if (response.startsWith("OK")) {
                    System.out.println("Επιτυχής εισαγωγή");
                    System.out.println(response.substring(3));

                } else {
                    System.out.println("Ανεπιτυχής εισαγωγή");
                    System.out.println(response.substring(7));
                }
                break;
            case "2":
                //
                break;
            case "3":
                System.out.println("Παρακαλώ εισάγετε το τηλέφωνο της επαφής που θέλετε να διαγράψετε");
                phoneNumber = getToken();
                response = controller.deleteContactByPhoneNumber(phoneNumber);
                if (response.startsWith("OK")) {
                    System.out.println("Επιτυχής διαγραφή");
                    System.out.println(response.substring(3));

                } else {
                    System.out.println("Ανεπιτυχής διαγραφή");
                    System.out.println(response.substring(7));
                }
                break;
            case "4":
                System.out.println("Παρακαλώ εισάγετε το τηλέφωνο της επαφής που θέλετε να αναζητήσετε");
                phoneNumber = getToken();
                response = controller.getContactByPhoneNumber(phoneNumber);
                if (response.startsWith("OK")) {
                    System.out.println("Επιτυχής αναζήτηση");
                    System.out.println(response.substring(3));

                } else {
                    System.out.println("Ανεπιτυχής αναζήτηση");
                    System.out.println(response.substring(7));
                }
                break;
            case "5":
                //
                break;
            default:
                //
                break;
        }
    }

    public static void printMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω");
        System.out.println("1. Εισαγωγή επαφής");
        System.out.println("2. Ενημέρωση επαφής");
        System.out.println("3. Διαγραφή επαφής");
        System.out.println("4. Αναζήτηση επαφής");
        System.out.println("5. Προβολή επαφών");
        System.out.println("Q/q. Έξοδος");


    }

    public static String getToken() {
        return in.nextLine().trim();
    }


}
