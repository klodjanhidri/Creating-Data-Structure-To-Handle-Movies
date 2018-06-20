/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.lists.movies;

import phase1.parser.MovieData;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Giwrgos Hompis
 */
public abstract class MovieList {

    protected int size = 0;
    public MovieListNode head;
    public MovieListNode tail;

//    /**
//     * Creates an empty sorted list, with size=0 and null head/tail
//     * @param comparator is an object that implements compare method 
//     *        which defines how objects should be compared
//     */
    public MovieList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /*
     //     * These 3 methods should not be implemented here. They are implementation
     //     * specific and should be implemented inside unsortedList, sortedList, selfAdjustingList
     //     */
    abstract public void insert(MovieData data);

    abstract public MovieListNode remove(int id);

    abstract public MovieListNode get(int id);

    abstract public void setYear(int a, int b);

    
    public void getMovies(int a, int b) {
        int i = 0;
        MovieListNode aa = this.head.getNext();
        while (aa != this.tail) {
            if ((aa.getYear() >= a) && (aa.getYear() <= b)) {
                if (i == 0) {
                    System.out.println("Movies that are from " + a + " to " + b + " are:");
                    i++;

                }
                System.out.println("[" + aa.getTitle() + "]-------->" + "[" + aa.getYear() + "]");
            }
            aa = aa.getNext();
        }
    }

    public void getGengre(String a, String b) {
        int i = 0, j = 0, k = 0,p=0;
        String s;
        MovieListNode aa = this.head.getNext();
        while (aa != this.tail) {
            for (MovieData.genre_t g : aa.getGenres()) 
            {
                
                s=g+"";
                if (s.equals(a)) {
                    if (j == 0) {
                        i++;
                    }
                    j++;
                } if (s.equals(b)) {
                    if (k == 0) {
                        i++;
                    }
                    k++;
                }
                if (i > 1) {
                    System.out.println("The movie[" + aa.getTitle() + "]" + "has the gengres " + a + " " + b);
                    i = 0;
                    k = 0;
                    j = 0;
                    break;
                }
                
            } 
            i=0;
            k=0;
            j=0;
            aa = aa.getNext();
        }
    }

    /**
     * Removes all of the elements from this list. The list will be empty after
     * this call returns
     */
    public void clear() {
        this.head.setNext(null);
    }

//    /**
//     * Returns the number of elements in this list
//     * @return the size of the list
//     */
    public int getSize() {
        return this.size;
    }
//    
//    /**
//     * Returns true if this list contains no elements
//     * @return true if the size of the list equals to 0 and false otherwise
//     */

    public boolean isEmpty() {

        return (this.size == 0);

    }
}
