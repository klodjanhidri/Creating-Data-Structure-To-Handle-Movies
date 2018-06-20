/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import parser.EventData;
import parser.FileParsers;
import parser.MovieData;
import trees.MovieBSTreeNode;
import users.UserList;
import users.UserListNode;

/**
 *
 * @author klodjan
 */
public class MaxHeap {

    private static final String moviesInputFilePath = "/home/klodjan/NetBeansProjects/DataStructurePhase4/src/movies_full.txt";
    private static final String eventsInputFilePath = "/home/klodjan/NetBeansProjects/DataStructurePhase4/src/events_2.txt";
    private MovieBSTreeNode heap[];
    private int heapSize, index;

    public MaxHeap() {
        this.heap = new MovieBSTreeNode[15000];
        for (int i = 0; i < this.heap.length; i++) {
            this.heap[i] = new MovieBSTreeNode(0, null, 0, 0, 0, 0, null);
        }
        this.heapSize = 0;
    }

    private int parent(int nodeindex) {
        return (int) ((nodeindex - 1) / 2);
    }

    private int leftchild(int nodeindex) {
        if (nodeindex == 0) {
            return 1;
        } else {
            return 2 * nodeindex + 1;
        }
    }

    private int rightchild(int nodeindex) {
        if (nodeindex == 0) {
            return 2;
        } else {
            return 2 * nodeindex + 2;
        }
    }

    public MovieBSTreeNode getMax() {
        return this.heap[0];
    }

    public void insert(MovieBSTreeNode value) throws Exception {
        MovieBSTreeNode tmp;
        if (heapSize == heap.length) {
            throw new Exception("Heap Overflow");
        }
        int m = heapSize;
        while ((m > 0) && (value.getRank() > this.heap[this.parent(m)].getRank())) {
            this.heap[m] = this.heap[this.parent(m)];
            m = this.parent(m);
        }
        this.heap[m] = value;
        this.heapSize++;

    }

    private void swap() {
        System.out.println("==================START====================");

        for (int i = 0; i < this.heapSize; i++) {
            if (this.heap[i] != null) {
                System.out.println(this.heap[i].getTitle());
            }
        }
        System.out.println("=================END===================== \n\n");
    }

    public MovieBSTreeNode HeapGet(int node) {
        for (int i = 0; i < this.heap.length; i++) {
            if (node == this.heap[i].getId()) {
                this.SetIndexOfArray(i);
                return this.heap[i];
            }
        }
        return null;
    }

    private void SetIndexOfArray(int movie) {
        this.index = movie;
    }

    private int getIndexOfArray() {
        return this.index;
    }

    public MovieBSTreeNode HeapRemove(int node) {
        int m, p;
        MovieBSTreeNode tmp, t;
        t = this.HeapGet(node);
        tmp = this.heap[this.heapSize - 1];
        this.heapSize--;
        m = this.getIndexOfArray();
        while (((this.leftchild(m) < this.heapSize && tmp.getRank() < this.heap[this.leftchild(m)].getRank()))
                || (this.rightchild(m) < this.heapSize && tmp.getRank() < this.heap[this.rightchild(m)].getRank())) {
            if (this.leftchild(m) < this.heapSize) {
                if (this.heap[this.leftchild(m)].getRank() > this.heap[this.rightchild(m)].getRank()) {
                    p = this.leftchild(m);
                } else {
                    p = this.rightchild(m);
                }

            } else {
                p = this.heapSize - 1;
            }
            this.heap[m] = this.heap[p];
            m = p;
        }
        this.heap[m] = this.heap[this.heapSize];
        this.heap[this.heapSize] = null;
        return t;


    }

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        PrintWriter outputStream;
        outputStream = new PrintWriter(new FileOutputStream("/home/klodjan/Desktop/phase4.txt"));
        
        String s;
        MovieBSTreeNode aa = new MovieBSTreeNode(0, null, 0, 0, 0, 0, null);
        MaxHeap b = new MaxHeap();
        MaxHeap[] heap = new MaxHeap[31];
        UserList a2 = new UserList();
        UserListNode aaa = new UserListNode();
        for (int j = 0; j < heap.length; j++) {
            heap[j] = new MaxHeap();
        }
        int index ;long sor=0,o=0;
               long time1,times;
        Enum dd;
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
        while (FileParsers.hasNextMovie()) {
           o++;
            MovieData data = FileParsers.getNextMovie();
            aa = new MovieBSTreeNode(data.getId(), data.getTitle(), data.getYear(),
                    data.getRating(), data.getVotes(), data.getDuration(), data.getGenres());
            time1 = System.currentTimeMillis();
            for (int p = 0; p < aa.getGenres().size(); p++) {
                dd = aa.getGenres().get(p);
                index = dd.ordinal();
                heap[index].insert(aa);
                        

            } 
            
            
            times = System.currentTimeMillis();
            sor = (times - time1) + sor;
        }
        
         outputStream.println("Insert Tree=" + " " + sor + "ns");
         System.out.print("777777"+"---->"+o);
       
         
       
        while (FileParsers.hasNextEvent()) {
            EventData event = FileParsers.getNextEvent();
            if (event.getOperation().equals("I")) {
                if (a2.get(event.getUsername()) == null) {
                    for (int f = 0; f < heap.length; f++) {
                        if (heap[f].heapSize != 0) {
                            aa = heap[f].HeapGet(event.getMovieId());
                            if (aa != null) {
                                break;
                            }
                        }
                    }                
                    a2.insert(event.getUsername());
                }
            }
        }

        FileParsers.finalizeParsers();
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);

        while (FileParsers.hasNextEvent()) {
            EventData event = FileParsers.getNextEvent();
            if (event.getOperation().equals("I")) {
                aaa = a2.get(event.getUsername());

                for (int f = 0; f < heap.length; f++) {
                    if (heap[f].heapSize != 0) {
                        aa = heap[f].HeapGet(event.getMovieId());
                        if (aa != null) {
                            break;
                        }
                    }
                }
                if (aa != null) {
                    if(aaa!=null){
                    aaa.insertMovie(event.getMovieId());
                    System.out.println("The user [" + event.getUsername() + "] " + "add the movie [" + aa.getTitle() + "] " + event.getMovieId());
                    }} else {
                    System.out.println("The movie with id [" + event.getMovieId() + "] " + "there isn't in movielist");
                }
            } else if (event.getOperation().equals("D")) {
                aaa = a2.get(event.getUsername());
                if (aaa != null) {
                    if (aaa.containsMovie(event.getMovieId())) {
                        for (int ff = 0; ff < heap.length; ff++) {
                            if (heap[ff].heapSize != 0) {
                                aa = heap[ff].HeapGet(event.getMovieId());
                                if (aa != null) {
                                    break;
                                }
                            }
                        }
                        aaa.removeMovie(event.getMovieId());
                        System.out.println("The user [" + event.getUsername() + "] delete the movie [" + aa.getTitle() + "]" + event.getMovieId());
                        if (aaa.isMoviesListEmpty()) {
                            a2.remove(event.getUsername());
                            System.out.println("User " + event.getUsername() + " deleted from userlist");
                        }
                    }
                } else {
                    System.out.println("The movie with id [" + event.getMovieId() + "] " + "there isn't in watch list of user [" + event.getUsername() + "]");
                }
            }
        }
//==============================================================================
       /* for (int t = 0; t < heap.length; t++) {
            if (heap[t].heapSize != 0) {
                System.out.println("Movies with the genre [" + MovieData.map(t) + "]");
                heap[t].swap();
            }

        }*/
//===================================Erwtima 1a==================================
         time1 = System.nanoTime();
         sor=0;
        String movie = null;
        double max = 0;
        for (int kk = 0; kk < heap.length; kk++) {
            if (heap[kk].heapSize != 0) {
                MovieBSTreeNode tt = heap[kk].getMax();
                if (tt.getRank() > max) {
                    max = tt.getRank();
                    movie = heap[kk].getMax().getTitle();

                }
            }
        }
        times = System.nanoTime();
            sor = (times - time1) + sor;
outputStream.println("Query1 heap=" + " " + sor + "ns");
        System.out.println("The movie that has the biggest rank is " + movie);
//===================================Erwtima 1b==================================
        time1 = System.nanoTime();
        for (int kk1 = 0; kk1 < heap.length; kk1++) {
            if (heap[kk1].heapSize != 0) {
                System.out.println("The movie that has the biggest rank in [" + MovieData.map(kk1) + "] genre is [" + heap[kk1].getMax().getTitle() + "]");
 times = System.nanoTime();
            sor = (times - time1) + sor;
            
            outputStream.println("Query1b heap=" + " " + sor + "ns");
            outputStream.close();
            }
        }
    }
}
