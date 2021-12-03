
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The DVD Library Manager controller.
 *
 * @author Anne
 */
public class DVDLibraryController {

    static boolean isDone = false; // A flag used to determine if the program should end or not
    static DVDLibraryView view = new DVDLibraryView();
    DVDLibraryDAO library = new DVDLibraryDAO();

    /**
     * The displayMenu method shows every possible operation, and decides on
     * action based on input received from the view class
     *
     * @throws java.io.FileNotFoundException
     */
    public void displayMenu() throws FileNotFoundException, IOException {

        while (!isDone) {
            // view.menu returns an integer signifying the operation the user had chosen
            int userChoice = view.menu();

            // Based on what operation needs to be executed, the operation number
            // is sent to view to ask for a proper input and then sent back to a
            // relevant method in controller
            switch (userChoice) {
                case 0: // Exit
                    isDone = true;
                    break;
                case 1: // Add a DVD
                    addDVD(view.askForInput(userChoice));
                    break;
                case 2: // Delete a DVD
                    deleteDVD(view.askForInput(userChoice));
                    break;
                case 3: // Edit a DVD
                    editDVD(view.askForInput(userChoice));
                    break;
                case 4: // Display every DVD in a library
                    view.displayAll(library.getList());
                    break;
                case 5: // Display a specific DVD
                    displayDVDinfo(view.askForInput(userChoice));
                    break;
                case 6: // Display a specific DVD by looking for it by title
                    findDVDbyTitle(view.askForInput(userChoice));
                    break;
                case 7: // Load a library from file
                    loadDVDLibrary(view.askForInput(userChoice));
                    break;
                case 8: // Save a library to a file
                    saveDVDLibrary(view.askForInput(userChoice));
                    break;
                // Because the input method in view loops until a valid option is chosen, a default satetment whoul have never been reached
            }// End of switch statement
        }// End of while loop

        // If the program is outside of the while loop, it means the user is ready
        // to finish
        // Operation 9 is asking the user if they want to save before quitting
        if (Integer.parseInt(view.askForInput(9)) == 1) {
            saveDVDLibrary(view.askForInput(8));

        }// End of if statement

    }// End of displayMenu()

    /**
     * A method for adding DVDs to the library
     *
     * @param infoDVD a String array with all the needed fields to initialize a
     * DVD
     */
    private void addDVD(String infoDVD) {

        String[] fields = infoDVD.split(DVDLibraryView.SEPARATOR);
        // The separator is defined by the view class and is applied automatically
        // when user is inputting the DVD data

        String newTitle = fields[0];
        String newDate = fields[1];
        String newMPAA = fields[2];
        String newDirector = fields[3];
        String newStudio = fields[4];
        String newNote = fields[5];

        DVD newDVD = new DVD(newTitle, newDate, newMPAA, newDirector, newStudio, newNote);
        library.add(newDVD);

        view.confirmSuccess(1);

    }

    /**
     * Removes a DVD from a library with a given id
     * @param idDVD a string with a DVD id to be removed
     */
    private void deleteDVD(String idDVD) {

        int removedDVDid = Integer.parseInt(idDVD);

        try {
            library.delete(library.find(removedDVDid));
            view.confirmSuccess(2);
        } catch (Exception e) {
            view.fail();
        }

    }

    private void editDVD(String operationInformation) {

        //System.out.println(operationInformation);
        String[] choices = operationInformation.split(DVDLibraryView.SEPARATOR);
        try {
            DVD editedDVD = library.find(Integer.parseInt(choices[0]));

            switch (choices[1]) {
                case "1":
                    editedDVD.setTitle(choices[2]);
                    break;
                case "2":
                    editedDVD.setReleaseDate(choices[2]);
                    break;
                case "3":
                    editedDVD.setMpaa(choices[2]);
                    break;
                case "4":
                    editedDVD.setDirector(choices[2]);
                    break;
                case "5":
                    editedDVD.setStudio(choices[2]);
                    break;
                case "6":
                    editedDVD.setNote(choices[2]);
                    break;
                default:
                    view.fail();
            }
            view.confirmSuccess(3);

        } catch (Exception e) {
            view.fail();
        }

    }

    private void displayDVDinfo(String idDVD) {
        int displayDVDid = Integer.parseInt(idDVD);

        try {
            view.displayInfo(library.find(displayDVDid));
        } catch (Exception e) {
            view.fail();
        }
    }

    private void findDVDbyTitle(String askForInput) {
        boolean isFound = false;
        for (DVD d : library.getList()) {
            if (askForInput.equalsIgnoreCase(d.getTitle())) {
                view.displayInfo(d);
                isFound = true;
            }
        }

        if (!isFound) {
            view.fail();
        }

    }

    private void loadDVDLibrary(String filePath) throws FileNotFoundException {

        Scanner sc = new Scanner(
                new BufferedReader(new FileReader(filePath))
        );

        library.getList().clear();

        while (sc.hasNextLine()) {
            String[] fields = sc.nextLine().split(DVDLibraryView.SEPARATOR);
            //System.out.println(fields[4]);
            library.add(new DVD(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]));
            //System.out.println(library.getList().size());
        }

        view.confirmSuccess(7);

    }

    private void saveDVDLibrary(String filePath) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filePath));

        library.getList().forEach((d) -> {
            out.println(d.getTitle() + DVDLibraryView.SEPARATOR + d.getReleaseDate()
                    + DVDLibraryView.SEPARATOR + d.getMpaa() + DVDLibraryView.SEPARATOR
                    + d.getDirector() + DVDLibraryView.SEPARATOR + d.getStudio()
                    + DVDLibraryView.SEPARATOR + d.getNote());
        });

        out.flush();
        out.close();

        view.confirmSuccess(8);

    }

}
