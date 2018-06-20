/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.hashtables.withLists;

import java.util.ArrayList;
import phase1.lists.movies.MovieList;
import phase1.lists.movies.MovieListNode;
import phase1.lists.movies.SelfAdjustingMovieList;
import phase1.lists.movies.SortedMovieList;
import phase1.lists.movies.UnsortedMovieList;
import phase1.parser.MovieData;

/**
 *
 * @author ApollonDigital
 */
public class SALMovieHashTable extends LMovieHashTable {

    private ArrayList<Integer> keys = new ArrayList<Integer>();

    /**
     * Creates an new hashTable (initializes the table variable) of
     * size="initialTableSize" with empty SelfAdjustingMovieList in each cell.
     */
    public SALMovieHashTable() {
        for (int i = 0; i < super.tableSize; i++) {
            super.table[i] = new SelfAdjustingMovieList();
        }
    }

    public ArrayList<Integer> getAllKeys() {
        System.out.println("++++++++++++SelfAdjustingLMovieHashTable++++++++++++");
        int j, k = 0;
        for (j = 0; j < super.table.length; j++) {
            if (super.table[j].tail.getPrevious() != super.table[j].head) {
                System.out.println("++++++++++++In " + j + " bucket++++++++++++");
                MovieListNode w = super.table[j].head.getNext();
                while (w != super.table[j].tail) {
                    System.out.println(w.getTitle() + "-->" + w.getId() + "\n");
                    this.keys.add(w.getId());

                    w = w.getNext();
                }



            }
        }
        return null;

    }

    /**
     * When the number of elements in the table is over 2*tableSize, this method
     * should be called. It creates a new table of SelfAdjustingMovieList with
     * size: a prime number over 2*tableSize and re-inserts all elements to the
     * new table.
     */
    @Override
    protected void rehash() {
        super.tableSize = 2 * super.tableSize + 1;
        MovieList[] old = super.table;
        super.table = new MovieList[super.tableSize];
        for (int i = 0; i < super.tableSize; i++) {
            super.table[i] = new SelfAdjustingMovieList();
        }

        java.util.Random rand = new java.util.Random();
        super.shift = rand.nextInt(super.tableSize);
        super.scale = rand.nextInt(super.tableSize - 1) + 1;
        MovieListNode u;
        for (int i = 0; i < old.length; i++) {
            MovieListNode w = old[i].head.getNext();

            if (w != old[i].tail) {
                while (w != old[i].tail) {
                    MovieData a = new MovieData(w.getId(), w.getTitle(), w.getYear(), w.getRating(),
                            w.getVotes(), w.getDuration(), w.getGenres());
                    int j = super.getHashIndex(w.getId());
                    super.table[j].insert(a);
                    w = w.getNext();
                }

            }
        }


    }
}
