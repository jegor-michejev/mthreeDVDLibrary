
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The model class for the DVD Library Manager Comes with add, update, delete
 * and getList methods
 *
 * @author Anne
 */
public class DVDLibraryDAO {

    private final ArrayList<DVD> library = new ArrayList<>();

    public int add(DVD dvd) {
        library.add(dvd);
        int index = library.size() - 1;
        dvd.setPersonalId(index);
        return index;
    }

    public void update(DVD dvd) {
        library.set(dvd.getPersonalId(), dvd);
    }

    public void delete(DVD dvd) {

        for (DVD d : library) {

            if (d.getPersonalId() > dvd.getPersonalId()) {
                d.setPersonalId(d.getPersonalId() - 1);

            }
        }

        library.remove(dvd);
    }

    public DVD find(int id) {
        return library.get(id);
    }

    public ArrayList<DVD> getList() {
        return library;
    }

}// End of class
