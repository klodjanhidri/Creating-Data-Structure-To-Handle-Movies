    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieData {
    public static enum genre_t {
        Short, Drama, Comedy, Documentary, Adult, Action, Romance,
        Thriller, Animation, Family, Crime, Horror, Music, Adventure, Fantasy,
        Sci_Fi, Mystery, Biography, Sport, History, Musical, Western, War, Reality_TV,
        News, Talk_Show, Game_Show, Film_Noir, Lifestyle, Experimental, Commercial 
    };
    
    private int id;
    private String title;
    private int year;
    private double rating;
    private int votes;
    private int duration;
    private ArrayList<genre_t> genres;
private static String []genre=new String[31];
    public MovieData(int id, String title, int year, double rating, int votes, int duration, ArrayList<genre_t> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.duration = duration;
        this.genres = genres;
    }

    public static String map(int index){
        genre[0]="Short";genre[1]="Drama";genre[2]="Comedy";genre[3]="Documentary";genre[4]="Adult";
        genre[5]="Action"; genre[6]="Romance"; genre[7]="Thriller";genre[9]="Animation";genre[10]="Family";
        genre[11]="Crime";genre[12]="Horror";genre[13]="Music";genre[14]="Adventure";genre[15]="Fantasy";
        genre[16]="Sci_Fi";genre[17]="Mystery";genre[18]="Biography";genre[19]="Sport";genre[20]="History";
        genre[21]="Musical";genre[22]="Western";genre[23]="War";genre[24]="Reality_TV";genre[25]="Talk_Show";
        genre[26]="Game_Show";genre[27]="Film_Noir";genre[28]="Lifestyle";genre[29]="Experimental";genre[30]="Commercial";
        
        
        return MovieData.genre[index];
    }
    public int getDuration() {
        return duration;
    }

    public ArrayList<genre_t> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getVotes() {
        return votes;
    }

    public int getYear() {
        return year;
    }
    
    @Override
    public String toString(){
        String out = "";
        out += "---------------------- Movie ----------------------\n";
        out += "ID:       " + this.id + "\n";
        out += "Title:    " + this.title + "\n";
        out += "Year:     " + this.year + "\n";
        out += "Rating:   " + this.rating + "\n";
        out += "Votes:    " + this.votes + "\n";
        out += "Duration: " + this.duration + " min\n";
        out += "Genres:   ";
        for( genre_t g : this.genres )
            out += g + ", ";
        out = out.substring(0, out.length()-2); // remove last comma
        out += "\n";
        return out;
    }
    
}
