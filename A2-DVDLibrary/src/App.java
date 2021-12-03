
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * The main class of the DVD Library Service
 *
 *
 * @author Anne
 */
public class App {

    public static void main(String[] args) throws IOException {

        DVDLibraryController controller = new DVDLibraryController();

        controller.displayMenu();

    }// End of main

}// End of class
