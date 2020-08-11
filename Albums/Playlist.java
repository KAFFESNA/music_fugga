/* Based on the ecs 100 template
 * Code for album rating software
 * Name: Nathan Kaffes
 * Date: 29/06/20
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
//importing the hashmap lets us use it to store data
import java.util.HashMap;

/** 
 * Instantiates the hashmap that will store the album information
 * This class will also house different processes like searching or adding
 * 
 * @author NZK 
 * @version 0.2
 */
public class Playlist{
    //creating the hashmap
    private HashMap<String, Album> albumList;
    private int albumCounter;
    /**
     * Constructor for objects in the class; "Albums"
     */
    public Playlist(){
        //initialising the Albums hashmap
        albumList = new HashMap<String, Album>();
        //testing the hashmap by manually implementing an album
        albumList.put("Thriller", new Album("Michael Jackson", 1982, "Pop", 0));
    }
    
    /**
     * Will add an album to the hashmap
     * @param name  The name of the album
     * @param art   The artist for the album
     * @param pub   The year of publication
     * @param gen   The genre of said album
     * @param rat   The rating of said album
     */
    public void addAlbum(String name, String art, int pub, String gen, int rat){
        albumList.put(name, new Album(art, pub, gen, rat));
    }
    
    /**
     * Will find information on the album based on the key
     */
    public void printChosen(String name){
        UI.clearText();
        try {
            //Grabs the information stored in the hashmap for this key
            String art = albumList.get(name).getArtist();
            int pub = albumList.get(name).getYear();
            String gen = albumList.get(name).getGenre();
            int rat = albumList.get(name).getRating();
            String ratString = albumList.get(name).assignRate(rat);
            //UI.println("The "+ gen + " Album " + name + " was released in " + pub );
            UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString);
        } catch (Exception wrongName) {
            UI.println("That album is not in our database");
        }
    }
    
    /**
     * Will grab all the albums with a specified genre
     */
    public void printGenre(String genre){
        albumCounter = 0;
        UI.clearText();
        for (String name : albumList.keySet()){
            String gen = albumList.get(name).getGenre();
            if (genre.equals(gen)){
                //the counter is used to determine whether or not any albums were returned
                albumCounter += 1;
                int pub = albumList.get(name).getYear();
                String art = albumList.get(name).getArtist();
                int rat = albumList.get(name).getRating();
                String ratString = albumList.get(name).assignRate(rat);
                UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString);
            }
        }
        if (albumCounter == 0){
            UI.println("There were no albums with that genre in our database");
        }
    }
    
    /**
     * Will grab all albums by rating choice
     */
    public void printRating(double rating){
        albumCounter = 0;
        UI.clearText();
        try {
            for (String name : albumList.keySet()){
                int rat = albumList.get(name).getRating();
                if (rating == rat){
                    //the counter is used to determine whether or not any albums were returned
                    albumCounter += 1;
                    int pub = albumList.get(name).getYear();
                    String art = albumList.get(name).getArtist();
                    String gen = albumList.get(name).getGenre();
                    String ratString = albumList.get(name).assignRate(rat);
                    UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString);
                }
            }   
            } catch (ArithmeticException ratingRange) {
                UI.println("Please enter a number between 0 - 3");
            } catch (Exception missingInteger) {
                UI.println("Please enter a number");
            }
        if (albumCounter == 0){
            UI.println("There were no albums with that rating stored in our database");
        }
    }
    
    /**
     * Will grab all information about all of the albums
     */
    public void printAll(){
        UI.clearText();
        for (String name : albumList.keySet()){
            String art = albumList.get(name).getArtist();
            int pub = albumList.get(name).getYear();
            String gen = albumList.get(name).getGenre();
            int rat = albumList.get(name).getRating();
            // assigns a string to the rating depending on the amount
            String ratString = albumList.get(name).assignRate(rat);
            //printing this in one line saves space but still looks clean on output
            UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString);
        }
    }
    
    /**
     * Will let the user rate an album
     */
    public void albumRating(){
        UI.clearText();
        String rateName = UI.askString("What Album would you like to Rate? ");
        try {
            int rat = albumList.get(rateName).getRating();
            if (rat == 0){
                UI.println(rateName + " is currently not yet rated");
            } else {
                UI.println(rateName + " currently has a rating of: " + rat + "/3 stars.");
            }
            try {
                int newRating = UI.askInt("How many stars would you rate " + rateName + " ? (1-3 stars) ");
                if (newRating < 0 || newRating > 3) {
                    throw new ArithmeticException();
                } else {
                    newRating = albumList.get(rateName).updateRating(newRating);
                    String ratString = albumList.get(rateName).assignRate(newRating);
                    UI.println(rateName + "'s rating has changed to: " + newRating + " - " + ratString);
                }
            } catch (ArithmeticException incorrectStars) {
                //Runs if the user enters a number out of range
                UI.println("Please enter a number between 1 - 3");
            } catch (Exception invalidStars) {
                //Runs if the user enters anything other than a number
                UI.println("Please input a number");
            }
        } catch (Exception albumMissing) {
            //Runs if user inputs an album that does not exist in the database
            UI.println("That album is not in our database");
        }
    }
}

