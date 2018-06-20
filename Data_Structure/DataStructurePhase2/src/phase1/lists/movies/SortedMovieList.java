/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.lists.movies;

import java.util.ArrayList;
import java.util.Comparator;
import phase1.parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
public class SortedMovieList extends MovieList {

//    /*
//     * Comparator<MovieListNode> is an object that implements the int compare(MovieListNode o1, MovieListNode o2) method.
//     * This method compares two MovieListNodes and returns:
//     *     A positive number when the first MovieListNode is bigger than the second element
//     *     Zero when the first MovieListNode is equal to the second element
//     *     A negative number when the first MovieListNode is smaller than the second element
//     * To compare two MovieListNode objects call "comparator.compare(m1, m2);" method.
//     */
    private Comparator<MovieListNode> comparator;
    public MovieListNode a;

    /**
     * Creates an empty sorted list
     *
     * @param comparator is an object that implements compare method which
     * defines how objects should be compared
     */
    
    public SortedMovieList(Comparator<MovieListNode> comparator) {
        super.head = new MovieListNode(0, null, 0, 0, 0, 0, null);
        super.size = 0;
        super.tail = new MovieListNode(0, null, 0, 0, 0, 0, null);
        super.head.setNext(super.tail);
        super.tail.setNext(null);
        super.tail.setPrevious(super.head);
        this.comparator = comparator;
    }
//
//    
//    
//    /**
//     * Creates a MovieListNode and appends the specified element (MovieListNode) in the sorted list in such
//     * way that list should remain sorted. The comparator defines the way 2 nodes should be compared.
//     * The values for the MovieListNode are contained inside MovieData
//     * @param node
//     * @return true if node inserted successfully, false otherwise
//     */
//    @Override

    public void setYear(int a,int b){
        super.getMovies(a, b);
    }
    
    public MovieListNode getTail(){
    return super.tail;
}
    public MovieListNode getHead(){
    return super.head;
}
    
    public void insert(MovieData node) {
        this.a = new MovieListNode(node.getId(), node.getTitle(), node.getYear(),
                node.getRating(), node.getVotes(), node.getDuration(), node.getGenres());
        MovieListNode w = super.head.getNext(), u = null;
        while (w != super.tail && w.getYear() <= a.getYear()) {
            u = w;
            w = w.getNext();
        }
       
        a.setNext(w);
        a.setPrevious(u);
        if (u == null) {
            super.head.setNext(a);
        } else {
            u.setNext(a);
        }
        if (w != null) {
            w.setPrevious(a);
        }
        super.size++;
        //  this.comparator.compare(a, a);

    }
    
     public void a() {
String out1 = null, out=" ";
ArrayList<String> a2=new ArrayList<String>();
        MovieListNode aa = super.head.getNext();
        out1 = "\n----------------------SortedMovieList Movies ----------------------\n";
        System.out.println(out1);
        while (aa != super.tail) {
            out = "\n"+
             "ID:       " + aa.getId() + "\n"+
             "Title:    " + aa.getTitle() + "\n"+
             "Year:     " + aa.getYear() + "\n"+
             "Rating:   " + aa.getRating() + "\n"+
             "Votes:    " + aa.getVotes() + "\n"+
             "Duration: " + aa.getDuration() + " min\n"
             +"Genres:   ";
            for (MovieData.genre_t g : aa.getGenres()) {
                a2.add(g+" ");
            }
           
            System.out.println(out+a2);aa = aa.getNext();
            a2.clear();
        }
        System.out.println("There are " + super.size + " elements in list");
    }

//    
//    
//    
//    /**
//     * Removes the first occurrence of the specified element (id) from this list, if it is
//     * present (optional operation). If this list does not contain the element, it is unchanged
//     * @param id
//     * @return the element which have been deleted or null if the element is not present
//     */
//    @Override

    public MovieListNode remove(int id) {
        MovieListNode u, w;
        this.a=this.head.getNext();
        while (this.a != super.tail) {
            if (this.a.getId() == id) {
                u = a.getPrevious();
                w = a.getNext();
                w.setPrevious(u);
                u.setNext(w);
                a.setNext(null);
                a.setPrevious(null);
                super.size--;
                break;
            }
            this.a = a.getNext();
        }
        return a;
    }
//    
//    
//    
//    /**
//     * Returns the element with the specified id in this list or null if it not present
//     * @param id
//     * @return the element
//     */
//    @Override

    public MovieListNode get(int id) {
                MovieListNode aa=this.head.getNext();

       while (aa != super.tail) {
            if (aa.getId() == id) {
                return aa;
            }
            aa = aa.getNext();
        }
        return null;

    }
}
