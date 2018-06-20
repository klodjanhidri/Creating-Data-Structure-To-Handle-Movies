/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.movies;

import parser.MovieData.genre_t;
import java.util.ArrayList;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieListNode {
    
    private int id;
    private String title;
    private int year;
    private double rating;
    private int votes;
    private int duration;
    private ArrayList<genre_t> genres;
    private MovieListNode previous;
    private MovieListNode next;
    
    public MovieListNode(int id, String title, int year, double rating,
            int votes, int duration, ArrayList<genre_t> genres) {
        this.genres = new ArrayList<genre_t>();
        
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.duration = duration;
        this.genres = genres;
    }
    
    public int getId() {
        return this.id;
        
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public double getRating() {
        return this.rating;
    }

    public int getVotes() {
        return this.votes;
    }

    public int getDuration() {
        return this.duration;
    }

    public ArrayList<genre_t> getGenres() {
        return this.genres;
    }

    public MovieListNode getPrevious() {
        return this.previous;
    }

    public MovieListNode getNext() {
        return this.next;
    }
    
    public void setNext(MovieListNode next) {
        this.next = next;
    }

    public void setPrevious(MovieListNode previous) {
        this.previous = previous;
        
    }
}
