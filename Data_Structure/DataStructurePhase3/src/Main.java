/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hashtables.withTrees.BSTMovieHashTable;
import parser.FileParsers;
//import dbGenerator.Movie;
import parser.MovieData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import parser.EventData;
import trees.MovieBSTree;
import trees.MovieBSTreeNode;
import users.UserList;
import users.UserListNode;
import users.WatchMovieListNode;

/**
 *
 * @author Giwrgos Hompis
 */
public class Main {
    // Define here the file that contains information about the movies and the events

    static long times, time1, time2, time3, time4, time5;
    private static final String moviesInputFilePath = "/home/klodjan/NetBeansProjects/DataStructurePhase3/src/movies_full.txt";
    private static final String eventsInputFilePath = "/home/klodjan/NetBeansProjects/DataStructurePhase4/src/events_2.txt";
    Boolean t = false;
    private static double rank;
    private static double tt, max = 0;
    static int id = 0;
    static String s = null;

    public static double getRank(double votes, double rating) {
        rank = ((votes / (votes + 25000)) * rating) + ((25000 / (votes + 25000)) * 6.9);
        return rank;
    }

    public static void print(UserList a2, BSTMovieHashTable un) {
        int i = 0;
        String out = null, out1 = null;
        UserListNode aa2 = a2.head.getNext();
        while (aa2 != null) {
            WatchMovieListNode a11 = aa2.head.getNext();
            while (a11 != aa2.tail) {
                if (i == 0) {
                    System.out.println("User [" + aa2.getUsername() + "] has these movies in watch list:");
                }
                out = "[" + un.get(a11.getMovieId()).getTitle() + "]";
                System.out.println(out);
                i++;
                a11 = a11.getNext();
            }
            i = 0;
            aa2 = aa2.getNext();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
         PrintWriter outputStream;
        outputStream = new PrintWriter(new FileOutputStream("/home/klodjan/Desktop/phase3.txt"));
        int year1, year2;
        MovieBSTreeNode aa = new MovieBSTreeNode(0, null, 0, 0, 0, 0, null);
        String user = null, ge2 = null, ge1 = null;
        UserList a2 = new UserList();
        WatchMovieListNode a5 = new WatchMovieListNode(0);
        int hashSize;
        System.out.println("Enter the size of the HashTable");

        Scanner inn = new Scanner(System.in);
        hashSize = inn.nextInt();
        BSTMovieHashTable bstreehash = new BSTMovieHashTable(hashSize);
        UserListNode aaa = new UserListNode(), tmp = new UserListNode();
        MovieBSTree bstree = new MovieBSTree();
        // Read the files
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
        int p = 0;
        long sor = 0, uns = 0, sel = 0, tim33, tim3;
        // Get all movies from file
        while (FileParsers.hasNextMovie()) {
            time1 = System.currentTimeMillis();
            MovieData movie = FileParsers.getNextMovie();
            
            time1 = System.currentTimeMillis();
           bstree.insert(movie);
            times = System.currentTimeMillis();
            sor = (times - time1) + sor;
            bstreehash.insert(movie);
            
        }
        System.out.println("BSTree time = " + " " + sor + "ns");
        outputStream.println("BSTree time = " + " " + sor + "ns");
        // Get all events from file
        while (FileParsers.hasNextEvent()) {
            EventData event = FileParsers.getNextEvent();
            if (event.getOperation().equals("I")) {
                if (a2.get(event.getUsername()) == null) {
                    aa = bstreehash.get(event.getMovieId());
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
                aa = bstreehash.get(event.getMovieId());
                if (aa != null) {
                    if(aaa!=null)
                    aaa.insertMovie(event.getMovieId());{
                    System.out.println("The user [" + event.getUsername() + "] " + "add the movie [" + aa.getTitle() + "] " + event.getMovieId());
                }} else {
                    System.out.println("The movie with id [" + event.getMovieId() + "] " + "there isn't in movielist");
                }
            } else if (event.getOperation().equals("D")) {
                aaa = a2.get(event.getUsername());
                if (aaa != null) {
                    if (aaa.containsMovie(event.getMovieId())) {
                        aa = bstreehash.get(event.getMovieId());
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

         // while(true)
        {
//==============================================================================
          //  Main.print(a2, bstreehash);
//===================================Erwtima 1==================================
         time1 = System.nanoTime();    
        MovieBSTreeNode w = null;
            String s = null;
            MovieBSTree[] kl = bstreehash.getTable();
            max = 0;
            int j;
            for (j = 0; j < kl.length; j++) {

                if (kl[j].root != null) {
                    aa = kl[j].root;
                    s = bstree.trace(aa);
                }
            }
            System.out.println("The movie with the highiest is rank [" + s + "]");
time2 = System.nanoTime();
            sor =(time2-time1);
                    outputStream.println("Query1 time = " + " " + sor + "ns");

//===================================Erwtima 2==================================
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the year for search:");
            year1 = in.nextInt();
            year2 = in.nextInt();
            time1 = System.nanoTime();    
            bstreehash.getMovies(year1, year2);
            time2 = System.nanoTime();
            sor =(time2-time1);
            outputStream.println("Query2 time = " + " " + sor + "ns");
            //===================================Erwtima 3==================================
            Scanner in2 = new Scanner(System.in);
            Scanner in3 = new Scanner(System.in);
            System.out.println("Enter two movies genres :");
            ge1 = in2.next();
            ge2 = in3.next();
            System.out.println("+++++++++++++++++++++Movies Gengre+++++++++++++++++");
           time1 = System.nanoTime();    
            bstreehash.getGengre(ge2, ge1);
            time2 = System.nanoTime();
            sor =(time2-time1);
            outputStream.println("Query3 time = " + " " + sor + "ns");
            //===================================Erwtima 4==================================
            System.out.println("Enter the name of user: ");
            Scanner in1 = new Scanner(System.in);
            user = in1.next();
            int time = 0;
            String out1 = null, out = " ";
            time1 = System.nanoTime();  
            UserListNode aa2 = a2.head.getNext();
            aa2 = a2.get(user);
            if (aa2 != null) {
                WatchMovieListNode a11 = aa2.head.getNext();
                if (a11 != aa2.tail) {
                    System.out.println("User " + user + " has movies in his watchlist:");
                }
                while (a11 != aa2.tail) {
                    MovieBSTreeNode ww = bstreehash.get(a11.getMovieId());
                    out1 = "[" + ww.getTitle() + "]";
                    time += ww.getDuration();
                    System.out.println(out1 + "-> " + ww.getDuration());
                    a11 = a11.getNext();
                }
                System.out.println("The duration of all movies tha will watch " + user + " is " + time + " minutes.");
            }
             time2 = System.nanoTime();
            sor =(time2-time1);
            outputStream.println("Query3 time = " + " " + sor + "ns");
//===================================Erwtima 5==================================      
            System.out.println("Enter the genre you want to search:");
            Scanner in4 = new Scanner(System.in);
            String u = in4.next();
           time1 = System.nanoTime();  
            a2.getUser(u, bstreehash, a2);
            time2 = System.nanoTime();
            sor =(time2-time1);
            outputStream.println("Query3 time = " + " " + sor + "ns");
            outputStream.close();
        }

    }
}
