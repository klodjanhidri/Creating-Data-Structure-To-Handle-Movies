/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import hashtables.withTrees.BSTMovieHashTable;
import trees.MovieBSTreeNode;
import parser.MovieData;
import trees.MovieBSTree;

/**
 *
 * @author Giwrgos Hompis
 */
public class UserList {

    public UserListNode head, a, tail, tmp;
    private int size = 0;

    public UserList() {
        this.head = new UserListNode();
        tail = new UserListNode();
        this.tail = null;
        this.head.setNexts(this.tail);
        this.size = 0;

    }

    public void insert(String username) {

        this.a = new UserListNode();
        UserListNode w = this.head.getNext();
        this.a.setUsername(username);
        this.a.setNexts(w);
        this.head.setNexts(this.a);

        this.size++;

    }

    public UserListNode remove(String username) {
        UserListNode w = null;
        this.a=this.head.getNext();
        while (this.a != this.tail && !(this.a.getUsername().equals(username))) {

            w = a;
            this.a = a.getNext();
        }
        if (w == null) {
            a = this.head;
            this.head = this.head.getNext();
            a.setNexts(null);
        } else {
            w.setNexts(a.getNext());
            a.setNext(null);
        }
        this.size--;
        System.out.println("deleted "+a.getUsername());
        return a;
    }
    public void getUser(String genre,BSTMovieHashTable unsortlist,UserList userlist){
        int max=0, j = 0, k = 0, p = 0;
        String s = null,t=null;
        UserListNode aa = this.head.getNext();
       MovieBSTreeNode movlist;

        while (aa != this.tail) {
            
            WatchMovieListNode movie = aa.head.getNext();
            while (movie != aa.tail) {
                movlist=unsortlist.get(movie.getMovieId());

                for (MovieData.genre_t g : movlist.getGenres()) {
                    s = g + "";
                    if (s.equals(genre)) {
           System.out.println("The movie[" + movlist.getTitle() + "]" + "has the genres " + genre );
           j++;              
           break;
                    }
                }
                movie=movie.getNext();
            }
            if(j>max){
                max=j;
              
               t=aa.getUsername();
               
            }
            j=0;
           
            aa = aa.getNext();
        }
                    if(t!=null)
                   System.out.println("The user with most ["+genre+"]"+"movies is"+ "["+ t + "]"  );

    }
    
/*
    public void getUser(String genre,UnsortedMovieList unsortlist,UserList userlist){
        int max=0, j = 0, k = 0, p = 0;
        String s = null,t=null;
        UserListNode aa = this.head.getNext();
       MovieListNode movlist;

        while (aa != this.tail) {
            
            WatchMovieListNode movie = aa.head.getNext();
            while (movie != aa.tail) {
                movlist=unsortlist.get(movie.getMovieId());

                for (MovieData.genre_t g : movlist.getGenres()) {
                    s = g + "";
                    if (s.equals(genre)) {
           System.out.println("The movie[" + movlist.getTitle() + "]" + "has the genres " + genre );
           j++;              
           break;
                    }
                }
                movie=movie.getNext();
            }
            if(j>max){
                max=j;
              
               t=aa.getUsername();
               
            }
            j=0;
           
            aa = aa.getNext();
        }
                    if(t!=null)
                   System.out.println("The user with most ["+genre+"]"+"movies is"+ "["+ t + "]"  );

    }*/

    public UserListNode get(String username) {
        UserListNode aa = this.head.getNext();
        while (aa != this.tail) {
            if (aa.getUsername().equals(username)) {
                return aa;
            }
            aa = aa.getNext();
        }
        return null;
    }

    public boolean isEmpty() {

        return (this.size == 0);
    }
}
