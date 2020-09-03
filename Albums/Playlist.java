/* Based on the ecs 100 template
 * Code for album rating software
 * Name: Nathan Kaffes
 * Date: 29/06/20
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.util.ArrayList;
//importing the hashmap lets us use it to store data
import java.util.HashMap;

/** 
 * Instantiates the hashmap that will store the album information
 * This class will also house different processes like searching or adding
 * 
 * @author NZK 
 * @version 0.3
 */
public class Playlist{
    //creating the hashmap
    private HashMap<String, Album> albumList;
    ArrayList<String> albumKeys = new ArrayList<String>();
    private int albumCounter;
    private boolean endPrint;
    //constants for the rating systems bounds
    private final int INITIALRATE = 0;
    private final int MINRATE = 1;
    private final int MAXRATE = 3;
    private final int PRINTSTRING = 100;
    //variables for stars
    private static final int MAXSTARS = 3;
    private Star[] stars = new Star[MAXSTARS];
    //positioning the stars
    private static final int LEFT = 100;
    private static final int TOP = 300;
    private static int starRating = 0;
    private static String starName = "";
    private boolean ratedAlbum = false;
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
        //creates an album counter to determine whether or not any albums were returned
        albumCounter = 0;
        //clears the shell of any text for ease of use
        UI.clearText();
        for (String name : albumList.keySet()){
            String gen = albumList.get(name).getGenre();
            //for every key in the hashmap, the loop checks whether the genre matches the one entered, if any are returned, they will be printed out.
            if (genre.equals(gen)){
                //the counter is used to determine whether or not any albums were returned
                albumCounter += 1;
                int pub = albumList.get(name).getYear();
                String art = albumList.get(name).getArtist();
                int rat = albumList.get(name).getRating();
                String ratString = albumList.get(name).assignRate(rat);
                UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString + "\n");
            }
        }
        //if no albums are returned, this will run, displaying to the user that indeed none were returned
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
                //will check if any of the albums in the hashmap match the chosen rating
                if (rating == rat){
                    //the counter is used to determine whether or not any albums were returned
                    albumCounter += 1;
                    int pub = albumList.get(name).getYear();
                    String art = albumList.get(name).getArtist();
                    String gen = albumList.get(name).getGenre();
                    String ratString = albumList.get(name).assignRate(rat);
                    UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString + "\n");
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
            UI.println("Name: " + name + " \nArtist: "+ art + " \nYear: " + pub + " \nGenre: " + gen + "\nRating: " + rat + " - " + ratString + "\n");
        }
    }
    
    /**
     * Will let the user rate an album
     */
    public void albumRating(){
        UI.clearText();
        UI.clearGraphics();
        String rateName = UI.askString("What Album would you like to Rate? ");
        try {
            int rat = albumList.get(rateName).getRating();
           if (rat == INITIALRATE){
                UI.println(rateName + " is currently not yet rated");
           } else {
                UI.println(rateName + " currently has a rating of: " + rat + "/3 stars.");
           }
           try {
                int newRating = UI.askInt("How many stars would you rate " + rateName + " ? (1-3 stars) ");
                if (newRating < MINRATE || newRating > MAXRATE) {
                    throw new ArithmeticException();
                } else {
                    newRating = albumList.get(rateName).updateRating(newRating);
                    String ratString = albumList.get(rateName).assignRate(newRating);
                    String checkGen = albumList.get(rateName).getGenre();
                    UI.println(rateName + "'s rating has changed to: " + newRating + " - " + ratString);
                    if(newRating >= 2){
                        recommendAlbum(newRating, rateName, checkGen);
                    }
                }
           } catch (ArithmeticException incorrectStars) {
                //Runs if the user enters a number out of range
                UI.println("Please enter a number between 1 - 3");
           }
        } catch (Exception albumMissing) {
            //Runs if user inputs an album that does not exist in the database
            UI.println("That album is not in our database");
        }
    }
    
    /**
     * Will recommend an album to the user based on the rating and the genre
     */
    public void recommendAlbum(int rat, String rateName, String checkGen){
        if (rat > 1){
            for (String name : albumList.keySet()){
                int recRating = albumList.get(name).getRating();
                String rateGen = albumList.get(name).getGenre();
                if ((recRating == 0) && (name != rateName) && (checkGen.equals(rateGen))){
                    albumKeys.add(name);
                }
            }
        }
        int printArr = albumKeys.size();
        try{
            if (printArr == 0){
                //will throw an error if no albums are stored in the arraylist
                throw new ArithmeticException();
            } else {
                for (int printCounter = 0; printCounter <= printArr; printCounter++){
                    ratedAlbum = false;
                    UI.clearGraphics();
                    String name = albumKeys.get(printCounter);
                    String art = albumList.get(albumKeys.get(printCounter)).getArtist();
                    int pub = albumList.get(albumKeys.get(printCounter)).getYear();
                    String gen = albumList.get(albumKeys.get(printCounter)).getGenre();
                    int printRat = albumList.get(albumKeys.get(printCounter)).getRating();
                    String ratString = albumList.get(rateName).assignRate(printRat);
                    UI.drawString("Displaying:" + (printCounter + 1) + "/" + printArr, PRINTSTRING, PRINTSTRING - 50);
                    UI.drawString("You might also like:", PRINTSTRING, PRINTSTRING);
                    UI.drawString(name + " by " + art, PRINTSTRING, PRINTSTRING + 25);
                    UI.drawString(pub + "     " + printRat + " - " + ratString, PRINTSTRING, PRINTSTRING + 50);
                    while (ratedAlbum == false){
                        stars(name);
                    }
                }
            }
        } catch (ArithmeticException noRecc) {
            //This will run if there is no recommendation stored in the ArrayList
            UI.drawString("No recommendations available in the hashmap",PRINTSTRING, PRINTSTRING);
        } 
    }
    
    /**
     * will create stars in the GUI
     */
    public void stars(String rateName) {
        //fills the stars array
        for (int starCounter = 0; starCounter < MAXSTARS; starCounter++){
            stars[starCounter] = new Star(LEFT * (starCounter + 1), TOP);
        }
        starName = rateName;
        UI.setMouseListener(this::manageMouse);
    }

    /**
     * Checks for mouse input
     */
    public void manageMouse(String mouseAction, double x, double y){
        if (mouseAction.equals("clicked")){
            for (int starCounter = 0; starCounter < MAXSTARS; starCounter++){
                if (this.stars[starCounter].onStar(x, y) == true){
                    starRating = (starCounter + 1);
                    SendRatingBack();
                    ratedAlbum = true;
                }
            }
        }
    }
    public void SendRatingBack(){
        int printrat = albumList.get(starName).updateRating(starRating);
        String ratString = albumList.get(starName).assignRate(starRating);
        UI.println(starName + "'s rating has changed to: " + starRating + " - " + ratString);
    }
}

