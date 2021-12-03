
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The view class that displays prompts to the user via the console, takes in
 * input and then transfers it to the controller
 *
 * @author Anne
 */
class DVDLibraryView {

    Scanner userInput = new Scanner(System.in);
    static final int MENU_OPTIONS = 8; // This variable was used for debugging purposes
    static final String SEPARATOR = ","; // Set, so you'd need to change the separator only once for the change to apply everywhere

    /**
     * Displays the available operations and sends the user choice to the
     * controller class
     *
     * @return an integer representing a user's choice
     */
    public int menu() {

        System.out.println("Please choose what you want to do");
        System.out.println("1. Add a DVD");
        System.out.println("2. Remove a DVD");
        System.out.println("3. Edit a DVD");
        System.out.println("4. List all DVDs");
        System.out.println("5. Display more info for a DVD");
        System.out.println("6. Find DVD by title");
        System.out.println("7. Load a library from file");
        System.out.println("8. Save a library to a file");
        System.out.println("0. Exit\n");

        int userChoice = userInput.nextInt();
        userInput.nextLine(); // as the user's choice is nextInt here, this line "consumes" the nextline symbol for the next input

        // I only now realised that this error check should have been in the controller class
        while (userChoice < 0 || userChoice > MENU_OPTIONS) {
            System.out.println("Please choose an available option\n");
            userChoice = userInput.nextInt();
            userInput.nextLine();
        }

        return userChoice;
    }// End of menu

    /**
     *
     * @param operation integer representing an operation to be performed by the
     * user
     * @return a String with the user inputs needed to complete the operation
     * the string could have multiple different inputs in it, separated by the
     * SEPARATOR declared
     */
    public String askForInput(int operation) {

        String userChoice = "";

        switch (operation) {
            case 1: // Add a DVD
                System.out.print("Input a title: ");
                userChoice += userInput.nextLine() + SEPARATOR;
                System.out.print("Input a release date: ");
                userChoice += userInput.nextLine() + SEPARATOR;
                System.out.print("Input the MPAA rating: ");
                userChoice += userInput.nextLine() + SEPARATOR;
                System.out.print("Input a director: ");
                userChoice += userInput.nextLine() + SEPARATOR;
                System.out.print("Input a studio: ");
                userChoice += userInput.nextLine() + SEPARATOR;
                System.out.print("Add a note: ");
                userChoice += userInput.nextLine();
                // User choice here is a String with all the fields needed to initialize a DVD
                break;
            case 2: // Remove a DVD
                System.out.println("Input DVD id to remove: ");
                userChoice += userInput.nextLine();
                // User choice here is an integer in a string form
                break;
            case 3: // Edit a DVD
                System.out.println("Input DVD id to edit: ");
                userChoice += userInput.nextLine();
                // User choice begins with a nnumber representing a DVD to be edired
                System.out.println("What would you like to edit? :");
                System.out.println("1. Title");
                System.out.println("2. Release date");
                System.out.println("3. MPAA rating");
                System.out.println("4. Director");
                System.out.println("5. Studio");
                System.out.println("6. User note");
                userChoice += "SEPARATOR" + userInput.nextLine();
                // Next input is which field of the DVD will be replaced
                System.out.println("Enter a new value: ");
                userChoice += "SEPARATOR" + userInput.nextLine();
                // The final part of the String is the new value
                break;
            // Case 4 has no need for user input and cannot happen under normal circumstances
            case 5: // Display info for a DVD
                System.out.println("Input DVD id to view information for: ");
                userChoice += userInput.nextLine();
                break;
            case 6: // Search for a DVD by title
                System.out.println("Input the title you are looking for: ");
                userChoice += userInput.nextLine();
                break;
            case 7: // Input a file path to read from
                System.out.println("Enter a filepath to read from: ");
                userChoice += userInput.nextLine();
                break;
            case 8: // Input a file path to save the library to
                System.out.println("Enter a filepath to sace to: ");
                userChoice += userInput.nextLine();
                break;
            case 9: // Quit the program; Check if user want to save what they have done
                System.out.println("Do you want to save your library?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                userChoice += userInput.nextLine();
                break;

        }// End of switch

        return userChoice;
    }// End of ask for user input

    /**
     * A custom success message for each operation
     *
     * @param operation an integer symbolizing an operation
     */
    void confirmSuccess(int operation) {

        switch (operation) {
            case 1: // Add a DVD
                System.out.println("\nDVD added.\n");
                break;
            case 2: // Remove a DVD
                System.out.println("\nDVD removed.\n");
                break;
            case 3: // Edit a DVD
                System.out.println("\nChange successful.\n");
                break;
            case 7: // Load a file
                System.out.println("\nLibrary loaded.\n");
                break;
            case 8: // Save a file
                System.out.println("\nSaved successfully.\n");
                break;
            default: // Generic success/Unimplemented for an operation
                System.out.println("\nUnknown success.\n");
                break;

        }// End of Switch statement
    }

    /**
     * A method for when an Exception is caught but I didn't want to run the
     * project all over again
     */
    void fail() {
        System.out.println("\nOperation failed.\n");
    }

    /**
     * Operation 4 or display all DVDs in a library
     *
     * @param library the library from the DAO file
     */
    void displayAll(ArrayList<DVD> library) {
        library.forEach((d) -> {
            System.out.println(d);
        });

        System.out.println();

    }

    /**
     * Used to display info of DVD when asked, might have been a nicer way to
     * implement display all DVDs, too.
     *
     * @param find DVD that will have its info displayed.
     */
    void displayInfo(DVD find) {
        System.out.println(find);
        System.out.println();
    }

}
