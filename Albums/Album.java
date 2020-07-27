/* Based on the ecs 100 template
 * Code for album rating software
 * Name: Nathan Kaffes
 * Date: 24/07/20
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** <description of class Album>
 */
public class Album {
    //Creating the different variables to store in the hashmap
    private String artist;
    private int year;
    private String genre;
    
    /**
     * Constructor for objects of the class; "Album"
     */
    public Album(String art, int pub, String gen){
        this.artist = art;
        this.year = pub;
        this.genre = gen;
    }

    /**
     * This object will return the Artist of the album that was selected in the GUI 
     */
    public String getArtist(){
        return this.artist;
    }
    
    /**
     * This object will return the Year the album was released for the one that was selected in the GUI
     */
    public int getYear(){
        return this.year;
    }
    
    /**
     * This object will return the Genre of the album that was selected in the GUI 
     */
    public String getGenre(){
        return this.genre;
    }
}

