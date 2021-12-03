/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The DVD object
 * comes with a toString() method and all getters and setters
 *
 * @author Anne
 */
public class DVD {

    int personalId = 0;
    String title;
    String releaseDate;
    String director;
    String studio;
    String mpaa;
    String note;

    public DVD(String title, String releaseDate, String mpaa, String director, String studio, String note) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.studio = studio;
        this.mpaa = mpaa;
        this.note = note;

    }// End of constructor

    @Override
    public String toString() {
        return "DVD #" + personalId + ": " + title + ", " + releaseDate + ", directed by " + director + ", " + studio + ", " + mpaa + ", \"" + note + "\"";
    }

    public int getPersonalId() {
        return personalId;
    }

    // Have an inkling this should not be public
    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}// End of class
