/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.hashtables.withLists;

//import phase1.lists.movies.MovieList;
import java.util.ArrayList;
import phase1.lists.movies.MovieList;
import phase1.lists.movies.MovieListNode;
import phase1.lists.movies.UnsortedMovieList;
import phase1.parser.MovieData;

/**
 *
 * @author ApollonDigital
 */
abstract public class LMovieHashTable {

    /**
     * These read-only variables are used inside this class. initialTableSize:
     * The initial tableSize
     */
    protected static final int initialTableSize = 3;
    protected int nElements;     // The total number of elements inside the hash table
    protected int tableSize;     // The current table size
    protected MovieList[] table; // The table with lists
    protected int shift, scale;

    /**
     * Initializes the nElements and tableSize variables
     */
    protected LMovieHashTable() {
        this.tableSize = LMovieHashTable.initialTableSize;
        this.nElements = 0;
        this.table = new MovieList[this.tableSize];
        java.util.Random rand = new java.util.Random();
        this.shift = rand.nextInt(this.tableSize);
        this.scale = rand.nextInt(this.tableSize - 1) + 1;
    }

    public MovieList[] getTable() {
        return this.table;
    }

    public void insert(MovieData data) {
        if (this.nElements > this.tableSize * 2) {
            rehash();
            int k = this.getHashIndex(data.getId());
        }
        this.table[this.getHashIndex(data.getId())].insert(data);
        this.nElements++;
    }

    /**
     * This is the hash function of the hash table. It calculates the index of
     * the table where the element should be. A good hash function should
     * distribute elements uniformly in the table
     */
    protected int getHashIndex(int movieId) {
        return Math.abs((movieId * scale + shift) % this.tableSize);
    }

    /**
     * ! DO NOT IMPLEMENT HERE ! This method is list-specific and should be
     * implemented in sub-classes When the number of elements in the table is
     * over 2*tableSize, this method should be called. It creates a new table
     * with size: a prime number over 2*tableSize and re-inserts all elements to
     * the new table. ! DO NOT IMPLEMENT HERE !
     */
    abstract protected void rehash();

    /**
     * Inserts a MovieListNode inside the hash table. The appropriate cell of
     * the hash table is determined by the hash function and the MovieData is
     * inserted in the pointing list(chain)
     *
     * @param data
     */
    /**
     * Removes the first occurrence of the specified element (id) from this
     * table, if it is present (optional operation). If this table does not
     * contain the element, it is unchanged
     *
     * @param id
     * @return the element which have been deleted or null if the element is not
     * present
     */
    public MovieListNode remove(int id) {
        MovieListNode u = this.get(id);
        int j = this.getHashIndex(id);
        this.table[j].remove(u.getId());
        this.nElements--;
        return u;
    }

    /**
     * Returns the element with the specified id in the hashTable or null if it
     * not present
     *
     * @param id
     * @return the element
     */
    public MovieListNode get(int id) {
        int j = 0;
        int key;
        key = this.getHashIndex(id);
        MovieListNode w = this.table[key].head.getNext();
        if (w != this.table[key].tail) {
            w = this.table[key].get(id);
            return w;
        }
        return null;
    }

    /**
     * Removes all of the elements from this hashTable. The lists in each cell
     * of the table will be empty after this call returns
     */
    public void clear() {
        for (int l = 0; l < this.table.length; l++) {
            this.table[l] = null;
        }
    }

    /**
     * Returns the number of all elements that are inside the hashTable
     *
     * @return nElements
     */
    public int getSize() {
        return this.nElements;
    }

    /**
     * Returns true if this list contains no elements
     *
     * @return true if the number of elements in hashTable equals to 0 and false
     * otherwise
     */
    public boolean isEmpty() {
        return (this.nElements == 0);
    }
int ll=0;
    public void getGengre(String a, String b) {
        
        int i = 0, j = 0, k = 0, p = 0, jj;
        String s;
        for (jj = 0; jj < this.table.length; jj++) {
            if (this.table[jj].tail.getPrevious() != this.table[jj].head) {
                MovieListNode w = this.table[jj].head.getNext();
                while (w != this.table[jj].tail) {

                    for (MovieData.genre_t g : w.getGenres()) {

                        s = g + "";
                        if (s.equals(a)) {
                            if (j == 0) {
                                i++;
                            }
                            j++;
                        }
                        if (s.equals(b)) {
                            if (k == 0) {
                                i++;
                            }
                            k++;
                        }
                        if (i > 1) {
               //             System.out.println("The movie[" + w.getTitle() + "]" + "has the gengres " + a + " " + b);
                            i = 0;
                            k = 0;
                            j = 0;
                            break;
                        }

                    }
                    i = 0;
                    k = 0;
                    j = 0;

                    w = w.getNext();
                }
            }
        }System.out.println(ll);
    }

    public void getMovies(int a, int b) {
        int i = 0;ll=0;
        int j, k = 0;
        for (j = 0; j < this.table.length; j++) {
            if (this.table[j].tail.getPrevious() != this.table[j].head) {
                MovieListNode w = this.table[j].head.getNext();
                while (w != this.table[j].tail) {
                    if ((w.getYear() >= a) && (w.getYear() <= b)) {
                        if (i == 0) {
                            System.out.println("Movies that are from " + a + " to " + b + " are:");
                            i++;

                        }
                        ll++;
                        
                       // System.out.println(w.getTitle() + "-->" + w.getId() + "\n");
                    }
                    w = w.getNext();
                }
            }
        }System.out.println(ll);
    }
}
