/*
 * 
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lists.users;

import lists.movies.MovieListNode;
import lists.movies.UnsortedMovieList;

/**
 *
 * @author Giwrgos Hompis
 */
public class UserListNode {

    private String username;
    private UserListNode next;
    public WatchMovieListNode head, watchMoviesList, tail;
    private int size = 0;
        UnsortedMovieList un = new UnsortedMovieList();
//MovieListNode a;
    public UserListNode() {
        this.username = null;
        this.next = null;
        this.watchMoviesList = null;
            this.head = new WatchMovieListNode(0);

        this.tail = new WatchMovieListNode(0);
        this.tail = null;
        this.head.setNext(this.tail);
    }

    public void setUsername(String a) {
        this.username = a;
    }

    public String getUsername() {
        return this.username;
    }

    public void setNexts(UserListNode a) {
        this.next = a;
    }

    public UserListNode getNext() {
        return this.next;
    }

    public void setNext(WatchMovieListNode next) {
        this.watchMoviesList = next;
    }

    public void insertMovie(int id) {
        WatchMovieListNode abc=this.get(id);
        if(abc==null){
        this.watchMoviesList = new WatchMovieListNode(id);
        WatchMovieListNode w = this.head.getNext();
        this.watchMoviesList.setNext(w);
        this.head.setNext(this.watchMoviesList);
        this.size++;
        }
    }

    public String aa1() {
        String out1 = null, out  = null;
     //   if(out==null)
        WatchMovieListNode aa = this.head.getNext();
        
        while (aa != this.tail) {
            
        //  a=  un.get(aa.getMovieId());
            out = 
                     "   ID: " + aa.getMovieId() + "\n";
            System.out.println(out);
            aa = aa.getNext();
        }
      //  System.out.print("with size "+this.size);
        return out;
    }

    public WatchMovieListNode removeMovie(int id) {

        WatchMovieListNode w = null,a=this.head.getNext();
        
        while (a != this.tail && (a.getMovieId() != id)) {

            w =a;
            a = a.getNext();
        }
        if (w == null) {
            a = this.head;
            this.head = this.head.getNext();
            a.setNext(null);
        } else {
            w.setNext(a.getNext());
            a.setNext(null);
        }
        this.size--;
        return a;

    }
    
    public WatchMovieListNode get(int id) {
         WatchMovieListNode aa = this.head.getNext();
         while (aa != this.tail) {
            if (aa.getMovieId()==id) {
                return aa;
            }
            aa = aa.getNext();
        }
         return aa;
    }
    public boolean containsMovie(int id) {
        WatchMovieListNode w = this.head.getNext();
        while (w != tail) {
            if (w.getMovieId() == id) {
                return true;
            }
            
            w=w.getNext();
        }
        return false;
    }

    public boolean isMoviesListEmpty() {
        return (this.size == 0);

    }
}
