/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.hashtables.withLists;

import phase1.lists.movies.MovieList;
import phase1.lists.movies.MovieListNode;
import phase1.lists.movies.SelfAdjustingMovieList;
import phase1.lists.movies.UnsortedMovieList;
import phase1.parser.MovieData;

/**
 *
 * @author ApollonDigital
 */
public class ULMovieHashTable extends LMovieHashTable {

    /**
     * Creates an new hashTable (initializes the table variable) of
     * size="initialTableSize" with empty UnsortedMovieList in each cell.
     */
    public ULMovieHashTable() {

        for (int i = 0; i < super.tableSize; i++) {
            super.table[i] = new UnsortedMovieList();
        }

    }

    public void getAllKeys() {
        System.out.println("++++++++++++UsortedLMovieHashTable++++++++++++");
        int j, k = 0;
        for (j = 0; j < super.table.length; j++) {
            if (super.table[j].tail.getPrevious() != super.table[j].head) {
                System.out.println("++++++++++++In " + j + " bucket++++++++++++");
                MovieListNode w = super.table[j].head.getNext();
                while (w != super.table[j].tail) {
                    System.out.println(w.getTitle() + "-->" + w.getId() + "\n");

                    w = w.getNext();
                }



            }
        }

    }

    /**
     * When the number of elements in the table is over 2*tableSize, this method
     * should be called. It creates a new table of UnsortedMovieList with size:
     * a prime number over 2*tableSize and re-inserts all elements to the new
     * table.
     */
    @Override
    protected void rehash() {
        super.tableSize = 2 * super.tableSize + 1;
        MovieList[] old = super.table;
        super.table = new MovieList[super.tableSize];
        for (int i = 0; i < super.tableSize; i++) {
            super.table[i] = new UnsortedMovieList();
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
