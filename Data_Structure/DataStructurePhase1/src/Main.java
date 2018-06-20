/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import lists.movies.MovieList;
import lists.movies.MovieListNode;
import lists.movies.SelfAdjustingMovieList;
import lists.movies.SortedMovieList;
import lists.movies.UnsortedMovieList;
import lists.users.UserList;
import lists.users.UserListNode;
import lists.users.WatchMovieListNode;
import parser.EventData;

/**
 *
 * @author Giwrgos Hompis
 */
public class Main {
    // Define here the file that contains information about the movies and the events
static long times,time1,time2,time3,time4,time5;
    private static final String moviesInputFilePath = "/home/klodjan/NetBeansProjects/phase1/src/movies_full.txt";
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

    public static void print(UserList a2, UnsortedMovieList un) {
        int i=0;
        String out = null, out1 = null;
        UserListNode aa2 = a2.head.getNext();
       
        while (aa2 != null) {
             WatchMovieListNode a11 = aa2.head.getNext();
            while (a11 != aa2.tail) {
                if(i==0)    
                System.out.println("User [" + aa2.getUsername() + "] has these movies in watch list:");
                out = "[" + un.get(a11.getMovieId()).getTitle() + "]";
                System.out.println(out);
                i++;
                a11 = a11.getNext();
            }i=0;
            aa2 = aa2.getNext();
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int year1, year2;
        
        PrintWriter outputStream;
        outputStream = new PrintWriter(new FileOutputStream("/home/klodjan/Desktop/phase1.txt"));
        String user = null, ge2 = null, ge1 = null;
//        // Create an empty unsorted list
        UnsortedMovieList unsortedList = new UnsortedMovieList();
//        // Create an empty sorted-by-year list
        SortedMovieList sortedList = new SortedMovieList(
                new Comparator<MovieListNode>() {
                    @Override
                    public int compare(MovieListNode m1, MovieListNode m2) {
                        return m1.getYear() - m2.getYear();
                    }
                }), sort = new SortedMovieList(
                new Comparator<MovieListNode>() {
                    @Override
                    public int compare(MovieListNode m1, MovieListNode m2) {
                        return m1.getYear() - m2.getYear();
                    }
                });

        // Create an empty selfAdjustingList
        SelfAdjustingMovieList selfAdjustingList = new SelfAdjustingMovieList(), self = new SelfAdjustingMovieList();
        UserList a2 = new UserList();
        WatchMovieListNode a5 = new WatchMovieListNode(0);
        MovieListNode aa = new MovieListNode(0, null, 0, 0, 0, 0, null);
        UserListNode aaa = new UserListNode(), tmp = new UserListNode();
        long sor = 0,uns = 0,sel = 0,tim33,tim3;
        // Read the files
        FileParsers.initializeParsers(moviesInputFilePath, eventsInputFilePath);
        int p = 0;
        // Get all movies from file
        while (FileParsers.hasNextMovie()) {
            
            MovieData movie = FileParsers.getNextMovie();
           time1 = System.nanoTime();
           // sortedList.insert(movie);
            times = System.nanoTime();
            sor=(times-time1)+sor;
          
           // tim33=System.nanoTime();
           unsortedList.insert(movie);
           // time2 = System.nanoTime();
            //uns +=(time2-tim33);
            
             tim3=System.nanoTime();
         //   selfAdjustingList.insert(movie);
             time3 = System.nanoTime();
             sel++;
          //  sel=(time3-tim3)+sel;
            //  System.out.println( movie );
            /* fill the movie lists here */
        }
       // outputStream.println("Insert SortedList=" + " " + sor + "ns");
       // outputStream.println("UnsortedList=" + " " + uns + "ns");
        outputStream.println("SelfAdjustingList=" + " " + sel + "ns");
      System.out.print(sel);

        while (FileParsers.hasNextEvent()) {
            EventData event = FileParsers.getNextEvent();

            if (event.getOperation().equals("I")) {
                // aaa = a2.get(event.getUsername());
                if (a2.get(event.getUsername()) == null) {
                    aa = unsortedList.get(event.getMovieId());
                  // aa = sortedList.get(event.getMovieId());
                 //  aa = selfAdjustingList.get(event.getMovieId());

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
                aa = unsortedList.get(event.getMovieId());
         //       aa = sortedList.get(event.getMovieId());
              //  aa =selfAdjustingList.get(event.getMovieId());
                if (aa != null) {
                    if(aaa!=null)
                    {
                    aaa.insertMovie(event.getMovieId());
                    System.out.println("The user [" + event.getUsername() + "] " + "add the movie [" + aa.getTitle() + "] " + event.getMovieId());
                    }} else {
                    System.out.println("The movie with id [" + event.getMovieId() + "] " + "there isn't in movielist");
                }
            } else if (event.getOperation().equals("D")) {
                aaa = a2.get(event.getUsername());
                if (aaa != null) {
                    if (aaa.containsMovie(event.getMovieId())) {
                  //     aa = selfAdjustingList.get(event.getMovieId());
                       aa = unsortedList.get(event.getMovieId());
                //        aa = sortedList.get(event.getMovieId());
                        aaa.removeMovie(event.getMovieId());
                        if(aa!=null)
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

        while(true){
//==============================================================================
     // unsortedList.a();
     // sortedList.a();
      // selfAdjustingList.a();
    //  Main.print(a2, unsortedList);
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//===================================Erwtima 1==================================
     /* sor=0;uns=0;sel=0;
            time1 = System.nanoTime();
      aa = unsortedList.getHead().getNext();
      
       while (aa != unsortedList.getTail())
     
        {
            tt = Main.getRank(aa.getVotes(), aa.getRating());
            if (tt > max) {
                max = tt;
                id = aa.getId();
                s = aa.getTitle();
            }
            aa = aa.getNext();
        }
        System.out.println("The movie in unsortedList with the highiest is [" + s + "]");
        times = System.nanoTime();
            uns=(times-time1);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            tim33=System.nanoTime();
        max = 0;
        aa = sortedList.getHead().getNext();
        while (aa != sortedList.getTail()) {
            tt = Main.getRank(aa.getVotes(), aa.getRating());
            if (tt > max) {
                max = tt;
                id = aa.getId();
                s = aa.getTitle();
            }

            aa = aa.getNext();
        }
        System.out.println("The movie in sortedList with the highiest is [" + s + "]");
        time2 = System.nanoTime();
            sor=(time2-tim33);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            tim3=System.nanoTime();
        max = 0;
        aa = selfAdjustingList.getHead().getNext();
        while (aa != selfAdjustingList.getTail()) {
            tt = Main.getRank(aa.getVotes(), aa.getRating());
            if (tt > max) {
                max = tt;
                id = aa.getId();
                s = aa.getTitle();
            }
            aa = aa.getNext();
        }
        System.out.println("The movie in selfAdjustingList with the highiest is [" + s + "]");
        time3 = System.nanoTime();
            sel=(time3-tim3);
       //     outputStream.println("Query1 SortedList=" + " " + sor + "ns");
       // outputStream.println("Query1 UnsortedList=" + " " + uns + "ns");       
        outputStream.println("Query1 SelfAdjustingList=" + " " + sel + "ns");
            
//===================================Erwtima 2==================================
       sor=0;uns=0;sel=0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the year for search:");
        year1 = in.nextInt();
        year2 = in.nextInt();
        System.out.println("++++++++++++From UnsortedList:+++++++++++++");
        time1 = System.nanoTime();
       // unsortedList.setYear(year1, year2);
        times = System.nanoTime();
            uns=(times-time1);
        System.out.println("+++++++++++++++From SortedList:+++++++++++++++++++++");
        tim33=System.nanoTime();
       
     //   sortedList.setYear(year1, year2);
        time2 = System.nanoTime();
            sor =(time2-tim33);
        System.out.println("++++++++++++++++From SelfAdjustingList:++++++++++++++++++++");
        tim3=System.nanoTime();
        selfAdjustingList.setYear(year1, year2);
        time3 = System.nanoTime();
            sel=(time3-tim3);
       //      outputStream.println("Query2 SortedList=" + " " + sor + "ns");
      //  outputStream.println("Query2 UnsortedList=" + " " + uns + "ns");       
        outputStream.println("Query2 SelfAdjustingList=" + " " + sel + "ns");
*/
//===================================Erwtima 3==================================
         sor=0;uns=0;sel=0;
            Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        System.out.println("Enter two movies genres :");
        ge1 = in2.next();
        ge2 = in3.next();
        System.out.println("+++++++++++++++++++++UnsortedList Gengre+++++++++++++++++");
         time1 = System.nanoTime();
        unsortedList.getGengre(ge2, ge1);
        times = System.nanoTime();
            uns=(times-time1);
        System.out.println("+++++++++++++++++++++SortedList Gengre+++++++++++++++++");
        tim33=System.nanoTime();
     //   sortedList.getGengre(ge1, ge2);
        time2 = System.nanoTime();
            sor =(time2-tim33);
        System.out.println("+++++++++++++++++++++SelfAdjustingList Gengre+++++++++++++++++");
         tim3=System.nanoTime();
       // selfAdjustingList.setGengre(ge2, ge1);
        time3 = System.nanoTime();
            sel=(time3-tim3);
          //  outputStream.println("Query3 SortedList=" + " " + sor + "ns");
       // outputStream.println("Query3 UnsortedList=" + " " + uns + "ns");       
        outputStream.println("Query3 SelfAdjustingList=" + " " + sel + "ns");
        
//===================================Erwtima 4==================================
       
        System.out.println("Enter the name of user: ");
        Scanner in1 = new Scanner(System.in);
        user = in1.next();
         tim3=System.nanoTime();
        
        sor=0;uns=0;sel=0;     
        int time = 0;
        String out1 = null, out = " ";
        UserListNode aa2 = a2.head.getNext();
        aa2 = a2.get(user);
        System.out.println(aa2);
        if (aa2 != null) {
            WatchMovieListNode a11 = aa2.head.getNext();
            if (a11 != aa2.tail) {
                System.out.println("User " + user + " has movies in his watchlist:");
            }
            while (a11 != aa2.tail) {
                //MovieListNode w= selfAdjustingList.get(a11.getMovieId());
                                MovieListNode w= unsortedList.get(a11.getMovieId());
               //                                 MovieListNode w= sortedList.get(a11.getMovieId());


                out1 = "[" + w.getTitle() + "]";
                time += w.getDuration();
                System.out.println(out1 + "-> " + w.getDuration());
                a11 = a11.getNext();
            }
            System.out.println("The duration of all movies tha will watch " + user + " is " + time + " minutes.");
        }
        time3 = System.nanoTime();
            sel=(time3-tim3);
            outputStream.println("Query4 SelfAdjustingList=" + " " + sel + "ns");
//===================================Erwtima 5==================================      
        System.out.println("Enter the genre you want to search:");
        Scanner in4 = new Scanner(System.in);
        String u = in4.next();
       time1 = System.nanoTime();   
       // a2.getUser(u, unsortedList, a2);
                //a2.getUser(u, sortedList, a2);
        a2.getUser(u, unsortedList, a2);

        times = System.nanoTime();
            sor=(times-time1);
            outputStream.println("Query5 UnsortedList=" + " " + sor + "ns");
        outputStream.close();
        }
    }
}
