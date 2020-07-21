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
    /**
     * Constructor for objects in the class; "Albums"
     */
    public Playlist(){
        //initialising the Albums hashmap
        albumList = new HashMap<String, Album>();
        //testing the hashmap by manually implementing an album
        albumList.put("Thriller", new Album("Michael Jackson", 1982, "Pop"));
    }
    
    /**
     * This class will add an album to the hashmap
     * @param name  The name of the album
     * @param art   The artist for the album
     * @param pub   The year of publication
     * @param gen   The genre of said album
     */
    public void addAlbum(String name, String art, int pub, String gen){
        albumList.put(name, new Album(art, pub, gen));
    }
    
    /**
     * This class will find information on the album based on the key
     */
    public void printChosen(String name){
        String art = albumList.get(name).getArtist();
        int pub = albumList.get(name).getYear();
        String gen = albumList.get(name).getGenre();
        UI.println("The "+ gen + " Album " + name + " was released in " + pub );
    }
}

