/* Based on the ecs 100 template
 * Code for ??
 * Name:
 * Date:
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** <description of class MusicGUI>
 */
public class MusicGUI{
    private Playlist music = new Playlist();
    public MusicGUI(){
        UI.initialise();
        UI.addButton("Add Album", this::newAlbum);
        UI.addTextField("Search", this::searchAlbum);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * This class will create a new album and then call a method to put the info in the hashmap
     */
    public void newAlbum(){
        //asks for each part of the hashmap relating to a specific album
        String name = UI.askString("Please enter Album Name: ");
        String art = UI.askString("Please the Album's Artist's name: ");
        int pub = UI.askInt("Please enter the Album's Year of Publication: ");
        String gen = UI.askString("Please enter the Album's Genre: ");
        //calls the addAlbum method to put the album in the hashmap
        music.addAlbum(name, art, pub, gen);
        UI.println("Name: " + name + "\nArtist: " + art + "\nYear: " + pub + "\nGenre: " + gen);
    }
    
    /**
     * This class will let the user search for an album by name
     */
    public void searchAlbum(String name){
        music.printChosen(name);
    }
    
    public static void main(String[] args){
        //creates a new instance of the constructor
        MusicGUI obj = new MusicGUI();
    }
}

