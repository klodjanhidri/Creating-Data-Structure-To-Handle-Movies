/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phase1.lists.users;

/**
 *
 * @author Giwrgos Hompis
 */
public class WatchMovieListNode {

    private int movieId;
    private WatchMovieListNode next;

    public WatchMovieListNode(int movieId) {
        this.movieId = movieId;
    }
//

    public int getMovieId() {
        return this.movieId;
    }

    public WatchMovieListNode getNext() {
        return this.next;
    }

    public void setNext(WatchMovieListNode next) {
        this.next = next;
    }
}
