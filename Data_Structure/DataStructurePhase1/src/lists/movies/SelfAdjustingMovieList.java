/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.movies;

import java.util.ArrayList;
import parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
public class SelfAdjustingMovieList extends MovieList {

    private MovieListNode a;

//    /**
//     * Creates an empty self adjusting list
//     */
    public SelfAdjustingMovieList() {
        super.head = new MovieListNode(0, null, 0, 0, 0, 0, null);
        super.size = 0;
        super.tail = new MovieListNode(0, null, 0, 0, 0, 0, null);
        super.head.setNext(super.tail);
        super.tail.setNext(null);
        super.tail.setPrevious(super.head);

    }
//
//    
//    /**
//     * Creates a MovieListNode and appends it the specified element (MovieListNode) in the list.
//     * The values for the MovieListNode are contained inside MovieData
//     * @param node
//     * @return true if node inserted successfully, false otherwise
//     */
//    @Override
    
 public void setGengre(String a,String b){
        super.getGengre(a,b);
    }   
public void setYear(int a,int b){
        super.getMovies(a, b);
    }
    
    public MovieListNode getTail(){
    return super.tail;
}
 public MovieListNode getHead(){
    return super.head;
}
    public void addAfterHead(MovieListNode v, MovieListNode zz) {
        
        MovieListNode w = v.getNext(), u, c, s=this.remove(zz.getId());
       // u = zz.getPrevious();
       // c = zz.getNext();
        //c.setPrevious(u);
       // u.setNext(c);

this.insert(s);
       // zz.setPrevious(v);
       // zz.setNext(w);
       // w.setPrevious(zz);
       // v.setNext(zz);


    }

    public void insert(MovieListNode aa){
        MovieListNode w = super.head.getNext();
        aa.setPrevious(super.head);
        aa.setNext(w);
        w.setPrevious(aa);
        super.head.setNext(aa);
    }
    
    public void insert(MovieData data) {
        this.a = new MovieListNode(data.getId(), data.getTitle(), data.getYear(),
                data.getRating(), data.getVotes(), data.getDuration(), data.getGenres());
        MovieListNode w = super.head.getNext();
        a.setPrevious(super.head);
        a.setNext(w);
        w.setPrevious(a);
        super.head.setNext(a);
        size++;

    }

    public void a() {
        String out1 = null, out = " ";
        ArrayList<String> a2 = new ArrayList<String>();
        MovieListNode aa = super.head.getNext();
       out1 = "\n----------------------SelfAdjustingList Movies ----------------------\n";
        System.out.println(out1);
        while (aa != super.tail) {
            out = "\n"
                    + "ID:       " + aa.getId() + "\n"
                    + "Title:    " + aa.getTitle() + "\n"
                    + "Year:     " + aa.getYear() + "\n"
                    + "Rating:   " + aa.getRating() + "\n"
                    + "Votes:    " + aa.getVotes() + "\n"
                    + "Duration: " + aa.getDuration() + " min\n"
                    + "Genres:   ";
            for (MovieData.genre_t g : aa.getGenres()) {
                a2.add(g + " ");
            }

            System.out.println(out + a2);
            aa = aa.getNext();
            a2.clear();
        }
        System.out.println("There are " + super.size + " elements in list");
    }
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
        a=this.head.getNext();
        while (a != super.tail) {
            if (a.getId() == id) {
                u = a.getPrevious();
                w = a.getNext();
                w.setPrevious(u);
                u.setNext(w);
                a.setNext(null);
                a.setPrevious(null);
                super.size--;
                break;
            }
            a = a.getNext();
        }
        return a;

    }
//    
//    
//    /**
//     * Returns the element with the specified id in this list or null if it not present. If the
//     * element is present, then it moves to the front of the list
//     * @param id
//     * @return the element
//     */
//    @Override

    public MovieListNode get(int id) {
        MovieListNode aa=this.head.getNext();
        while (aa != super.tail) {
            if (aa.getId() == id) {
                this.addAfterHead(super.head, aa);
                return aa;
            }
            aa = aa.getNext();
        }
        return null;

    }
}
