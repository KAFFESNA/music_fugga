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
 * This class will instantiate the hashmap that will store the album information
 * This class will also house different processes like searching or adding
 * 
 * @author NZK 
 * @version 0.1
 */
public class Playlist{
    //creating the hashmap
    private HashMap<String, Album> albumList;
    private int counter;
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
     * This method will add an album to the hashmap
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
     * This method will find information on the album based on the key
     */
    public void printChosen(String name){
        UI.clearText();
        try {
            String art = albumList.get(name).getArtist();
            int pub = albumList.get(name).getYear();
            String gen = albumList.get(name).getGenre();
            //UI.println("The "+ gen + " Album " + name + " was released in " + pub );
            UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen);
        } catch (Exception wrongName) {
            UI.println("That album is not in our database");
        }
    }
    
    /**
     * This method will grab all the albums with a specified genre
     */
    public void printGenre(String genre){
        counter = 0;
        UI.clearText();
        for (String name : albumList.keySet()){
            String gen = albumList.get(name).getGenre();
            if (genre.equals(gen)){
                counter += 1;
                int pub = albumList.get(name).getYear();
                String art = albumList.get(name).getArtist();
                UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\n");
            }
        }
        if (counter == 0){
            UI.println("There were no albums with that genre in our database");
        }
    }
    
    /**
     * This method will grab all information about all of the albums
     */
    public void printAll(){
        UI.clearText();
        for (String name : albumList.keySet()){
            String art = albumList.get(name).getArtist();
            int pub = albumList.get(name).getYear();
            String gen = albumList.get(name).getGenre();
            UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\n");
        }
    }
    
    /**
     * This method will let the user rate an album
     */
    public void albumRating(){
        UI.clearText();
        String rateName = UI.askString("What Album would you like to Rate? ");
        try {
            int rat = albumList.get(rateName).getRating();
            UI.println(rateName + " currently has a rating of: " + rat);
        } catch (Exception albumMissing) {
            UI.println("That album is not in our database");
        }
    }
}

