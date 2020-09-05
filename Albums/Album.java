/* Based on the ecs 100 template
 * Code for album rating software
 * Name: Nathan Kaffes
 * Date: 24/07/20
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** Will let the user grab information for specific albums from the hashmap
 */
public class Album {
    //Creating the different variables to store in the hashmap
    private String artist;
    private int year;
    private String genre;
    private int rating;
    //stores the strings for each rating, used in assignRate
    private String[] ratingStrings = {"Not Yet Rated", "Bad", "Average", "Good"};
    private String ratString;
    
    /**
     * Constructor for objects of the class; "Album"
     */
    public Album(String art, int pub, String gen, int rat){
        this.artist = art;
        this.year = pub;
        this.genre = gen;
        this.rating = rat;
    }

    /**
     * Will return the Artist of the album that was selected in the GUI 
     */
    public String getArtist(){
        return this.artist;
    }
    
    /**
     * Will return the Year the album was released for the one that was selected in the GUI
     */
    public int getYear(){
        return this.year;
    }
    
    /**
     * Will return the Genre of the album that was selected in the GUI 
     */
    public String getGenre(){
        return this.genre;
    }
    
    /**
     * Will return the Rating of the album that was selected in the GUI 
     */
    public int getRating(){
        return this.rating;
    }
    
    /**
     * Will update rating for specific album
     */
    public int updateRating(int newRating){
        this.rating = newRating;
        return this.rating;
    }
    
    /**
     * Will assign a string to a rating
     */
    public String assignRate(int rat){
        ratString = ratingStrings[rat];
        return ratString;
    }
}

