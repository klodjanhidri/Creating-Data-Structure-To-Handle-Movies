/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtables.withTrees;

import parser.MovieData;
import trees.MovieBSTree;
import trees.MovieBSTreeNode;

/**
 *
 * @author Giwrgos Hompis
 */
public class BSTMovieHashTable {

    private int nElements,sizemov=0;     // The total number of elements inside the hash table
    private int tableSize,k=0,kk=0,jjj=0,ii=0;     // The current table size
    public MovieBSTree[] table;    // The table with trees
private int shift,scale;
String s=null;
private MovieBSTree a=new MovieBSTree();
    /**
     * Constructor: Creates a new movie hash table with binary search trees. The
     * size of the table is given as a parameter
     *
     * @param tableSize
     */
    public BSTMovieHashTable(int tableSize) {
        this.tableSize=tableSize;
        table=new MovieBSTree[this.tableSize];
        for (int i = 0; i < this.tableSize; i++) {
            this.table[i] = new MovieBSTree();
        }
        java.util.Random rand = new java.util.Random();
        this.shift = rand.nextInt(this.tableSize);
        this.scale = rand.nextInt(this.tableSize - 1) + 1;
    }

    /**
     * This is the hash function of the hash table. It calculates the index of
     * the table where the element should be. A good hash function should
     * distribute elements uniformly in the table
     */
    private int getHashIndex(int movieId) {
                return Math.abs((movieId * scale + shift) % this.tableSize);

    }

    /**
     * Inserts a BSTreeNode inside the hash table. The appropriate cell of the
     * hash table is determined by the hash function and the MovieData is
     * inserted in the corresponding tree
     *
     * @param data
     */
    public void insert(MovieData data) {
        this.table[this.getHashIndex(data.getId())].insert(data);
        this.nElements++;
    }

    /**
     * Removes the first occurrence of the specified element (id) from this
     * table, if it is present (optional operation). If this table does not
     * contain the element, it is unchanged
     *
     * @param id
     * @return the element which have been deleted or null if the element is not
     * present
     */
    public MovieBSTreeNode remove(int id) {
        MovieBSTreeNode u = this.get(id);
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
    public MovieBSTreeNode get(int id) {
        int j = 0;
        int key;
        key = this.getHashIndex(id);
        MovieBSTreeNode w = this.table[key].root;
        if (w != null) {
            w = this.table[key].get(id);
            return w;
        }
        return null;
    }

    public void getAllKeys() {
        System.out.println("++++++++++++SelfAdjustingLMovieHashTable++++++++++++");
        int j, k = 0;
        for (j = 0; j < this.table.length; j++) {
            if (this.table[j].root!=null) {
                System.out.println("++++++++++++In " + j + " bucket++++++++++++");
                MovieBSTreeNode w = this.table[j].root;
            a.p(w);                
            }
            
        }
      
    }
    
     public void trace(MovieBSTreeNode aa,int a,int b) {

        int id;

        if (aa != null) {
            trace(aa.getlChild(),a,b);
             if ((aa.getYear() >= a) && (aa.getYear() <= b)) {
                        if (k == 0) {
                            System.out.println("Movies that are from " + a + " to " + b + " are:");
                            k++;
                            
                        }
                        this.sizemov++;
                      //  System.out.println(aa.getTitle() + "-->" + aa.getId() + "\n");
                    }
            trace(aa.getrChild(),a,b);
        }
     
      //  System.out.println("biggest rank movie is =" + s);
     


    }
    
     public void getMovies(int a, int b) {
        int i = 0;
        int j, k = 0;
        for (j = 0; j < this.table.length; j++) {
            if (this.table[j].root !=null) {
                 MovieBSTreeNode w = this.table[j].root;
               this.trace(w, a, b);
            }
        }System.out.println(this.sizemov);
    }
    public void trace(MovieBSTreeNode aa,String a,String b) {

        int id;

        if (aa != null) {
            trace(aa.getlChild(),a,b);
            for (MovieData.genre_t g : aa.getGenres()) {

                        s = g + "";
                        if (s.equals(a)) {
                            if (jjj == 0) {
                                ii++;
                            }
                            jjj++;
                        }
                        if (s.equals(b)) {
                            if (k == 0) {
                                ii++;
                            }
                            k++;
                        }
                        if (ii > 1) {
                         //System.out.println("The movie[" + aa.getTitle() + "]" + "has the gengres " + a + " " + b);
                            ii = 0;
                            k = 0;
                            jjj = 0;
                            break;
                        }

                    }
                    ii = 0;
                    k = 0;
                    jjj = 0;

            trace(aa.getrChild(),a,b);
        }}
     
     public void getGengre(String a, String b) {
        int i = 0, j = 0, k = 0, p = 0, jj;
        String s;
        for (int jj1 = 0; jj1 < this.table.length; jj1++) {
            if (this.table[jj1].root!=null) {
                MovieBSTreeNode w = this.table[jj1].root;
                this.trace(w,a, b);
            }
        }
    }
     
     
    /**
     * Removes all of the elements from this hashTable. The lists in each cell
     * of the table will be empty after this call returns
     */
    public void clear() {
        for (int l = 0; l < this.table.length; l++) {
            this.table[l].root = null;
        }this.nElements=0;
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
    public MovieBSTree[] getTable() {
        return this.table;
    }
    
    public boolean isEmpty() {
        return (this.nElements==0);
    }
}
