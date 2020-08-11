/* Based on the ecs 100 template
 * Code for album rating software
 * Name: Nathan Kaffes
 * Date: 24/07/20
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** 
 * Will create the GUI buttons and then link to other classes to carry out processes
 */
public class MusicGUI{
    private Playlist music = new Playlist();
    //these variables store the text fields information for the 
    private String chosenEntry;
    private double chosenSlide;
    private final double RATEMIN = 0;
    private final double RATEMAX = 3;
    //This constant is the set rating in the beginning, it is changeable with the Rating button
    private final int STARTRATE = 0;
    /**
     * Initialises the GUI and sets all of the buttons
     */
    public MusicGUI(){
        UI.initialise();
        //button to add an album
        UI.addButton("Add Album", this::newAlbum);
        //text field to input an album name, button is below the text field, you can also click genre button for a search by genre feature
        UI.addTextField("Search", this::setEntry);
        UI.addButton("Search by Name", this::searchAlbum);
        UI.addButton("Search by Genre", this::searchGenre);
        UI.addSlider("Rating", RATEMIN, RATEMAX, this::storeSlider);
        UI.addButton("Search by Rating", this::searchRating);
        //button to search for all albums in the hashmap
        UI.addButton("Search All", this::searchAll);
        UI.addButton("Rate Album", this::rateAlbum);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Will create a new album and then call a method to put the info in the hashmap
     */
    public void newAlbum(){
        //clears the output field
        UI.clearText();
        //asks for each part of the hashmap relating to a specific album
        String name = UI.askString("Please enter Album Name: ");
        String art = UI.askString("Please the Album's Artist's name: ");
        int pub = UI.askInt("Please enter the Album's Year of Publication: ");
        String gen = UI.askString("Please enter the Album's Genre: ");
        int rat = STARTRATE;
        //calls the addAlbum method to put the album in the hashmap
        music.addAlbum(name, art, pub, gen, rat);
        UI.println("\nName: " + name + "\nArtist: " + art + "\nYear: " + pub + "\nGenre: " + gen);
    }
    
    /**
     * Stores the text field's string in a variable
     */
    public void setEntry(String entry){
        chosenEntry = entry;
    }
    
    /**
     * Stores the sliders double in a variable
     */
    public void storeSlider(double slideEntry){
        chosenSlide = slideEntry;
    }
    
    /**
     * Will let the user search for an album by name
     */
    public void searchAlbum(){
        music.printChosen(chosenEntry);
    }

    /**
     * Will let the user search for an album or albums by genre
     */
    public void searchGenre(){
        music.printGenre(chosenEntry);
    }
    
    /**
     * Will let the user search for an album or albums by stored rating
     */
    public void searchRating(){
        music.printRating(chosenSlide);
    }
    
    /**
     * Will let the user search for all the albums in the hashmap
     */
    public void searchAll(){
        music.printAll();
    }
    
    /**
     * Will let the user rate an album based on their search
     */
    public void rateAlbum(){
        music.albumRating();
    }
    
    /**
     * Main function
     */
    public static void main(String[] args){
        //creates a new instance of the constructor
        MusicGUI obj = new MusicGUI();
    }
}

