/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import parser.MovieData;

/**
 *
 * @author Giwrgos Hompis
 */
public class MovieBSTree {

    protected int size, balance;         // The total number of elements in the tree
    public MovieBSTreeNode root, tree;  // The root node of the tree
    double tt, max = 0;
    String s = null;

    public int heightr(MovieBSTreeNode t) {
        if (t == null) {
            return 0;
        }
        int le = heightr(t.getrChild());
        return le + 1;
    }

    public int height(MovieBSTreeNode t) {

        return t == null ? -1 : t.height;
    }

    public int heightl(MovieBSTreeNode t) {

        if (t == null) {
            return 0;
        }
        int i = heightl(t.getlChild());
        return i + 1;
    }

    /**
     * Creates an empty BSTree
     */
    public MovieBSTree() {
        this.size = 0;
        this.root = new MovieBSTreeNode(0, null, 0, 0, 0, 0, null);
        this.root.setlChild(null);
        this.root.setrChild(null);
        this.root = null;

    }

    /**
     * Inserts a BSTreeNode in the tree. The appropriate position of the leave
     * of the binary search tree is determined by the id of the MovieData
     *
     * @param data
     */
    public MovieBSTreeNode findMin(MovieBSTreeNode x) {
        while (x != null) {
            if (x.getlChild() == null) {
                break;
            }

            x = x.getlChild();
        }
        return x;
    }

    public MovieBSTreeNode Diadoxos(MovieBSTreeNode x) {
        MovieBSTreeNode y;
        if (x.getrChild() != null) {
            return this.findMin(x.getrChild());
        }
        y = x.getParent();
        while (y != null && x == y.getrChild()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    public void insert(MovieData data) {
        MovieBSTreeNode x = root, y = null,
                z = new MovieBSTreeNode(data.getId(), data.getTitle(), data.getYear(),
                data.getRating(), data.getVotes(), data.getDuration(), data.getGenres());
        while (x != null) {
            y = x;
            if (data.getId() < x.getId()) {
                x = x.getlChild();
            } else {
                x = x.getrChild();
            }
        }
        z.setParent(y);
        if (y == null) {
            root = z;
        } else {
            if (z.getId() < y.getId()) {
                y.setlChild(z);
                this.size++;
                if (this.balancedl(root) == 2) {
                    if (z.getId() <= y.getlChild().getId()) {
                        this.Rightrotate(y);
                    } else {
                        y = this.doubleWithLeftChild(y);
                    }

                }

            } else {
                y.setrChild(z);
                this.size++;
                if (this.balancedr(root) == 2) {
                    if (z.getId() >= y.getrChild().getId()) {
                        this.Leftrotate(y);
                    } else {
                        y = this.doubleWithRightChild(y);

                    }
                }
            }
        }
    }

    public int balancedl(MovieBSTreeNode N) {
        return heightl(N) - heightr(N);
    }

    public int balancedr(MovieBSTreeNode N) {
        return heightr(N) - heightl(N);
    }

    public void p(MovieBSTreeNode aa) {
        if (aa != null) {
            p(aa.getlChild());
            System.out.println(aa.toString());
            p(aa.getrChild());
        }
    }

    public MovieBSTreeNode Leftrotate(MovieBSTreeNode x) {

        MovieBSTreeNode yy;
        yy = x.getrChild();
        x.setrChild(yy.getlChild());
        if (yy.getlChild() != null) {
        }
        yy.setParent(x.getParent());
        if (x.getParent() == null) {
           // System.out.println("lefroot");
            this.root = yy;
        }
        if (x == x.getParent().getlChild()) {
            //System.out.println("lefcht");
            x.getParent().setlChild(yy);
        } else {
            x.getParent().setrChild(yy);
        }
        yy.setlChild(x);
        x.setParent(yy);
        return yy;
    }

    public MovieBSTreeNode Rightrotate(MovieBSTreeNode x) {
        MovieBSTreeNode y;
        y = x.getlChild();
        x.lChild = y.rChild;
        if (y.getrChild() != null) {
            y.getrChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getrChild()) {
            x.getParent().setrChild(y);
        } else {
            x.getParent().setlChild(y);
        }
        y.setrChild(x);
        x.setParent(y);

        return y;
    }

    private MovieBSTreeNode doubleWithLeftChild(MovieBSTreeNode k3) {
        k3.setlChild(this.Rightrotate(k3.getlChild()));
        return this.Leftrotate(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child
     */
    private MovieBSTreeNode doubleWithRightChild(MovieBSTreeNode k1) {

        k1.setrChild(this.Leftrotate(k1.getrChild()));
        return this.Rightrotate(k1);
    }

    public MovieBSTreeNode remove(int id) {
        MovieBSTreeNode z = this.get(id), y, x;
        if (z.getlChild() == null || z.getrChild() == null) {
            y = z;
        } else {
            y = this.Diadoxos(z);
          //  System.out.println("lefrrroot" + y.getTitle());
        }
        if (y.getlChild() != null) {
            x = y.getlChild();
        } else {
            x = y.getrChild();
        }
        if (x != null) {
            x.setParent(y.getParent());

        }
        if (y.getParent() == null) {
            this.root = x;
        } else if (y == y.getParent().getlChild()) {
            y.getParent().setlChild(z);

        } else {
            y.getParent().setrChild(x);
        }
        return y;
    }

    /**
     * Returns the element with the specified id in the tree or null if it not
     * present
     *
     * @param id
     * @return the element
     */
    public MovieBSTreeNode get(int id) {
        MovieBSTreeNode a;
        a = root;
        while ((a != null)) {
            int rval = a.getId();
            if (id < rval) {
                a = a.getlChild();
            } else if (id > rval) {
                a = a.getrChild();
            } else {
                break;
            }

        }
        return a;
    }

    /**
     * Removes all of the elements from this tree. The tree will be empty after
     * this call returns
     */
    public void clear() {
        this.root = null;
    }

    /**
     * Returns the number of all elements that are inside the tree
     *
     * @return nElements
     */
    public int getSize() {
        return this.size;
    }

    public String trace(MovieBSTreeNode aa) {

        int id;

        if (aa != null) {
            trace(aa.getlChild());
            tt = this.getRank(aa.getVotes(), aa.getRating());
            if (tt > max) {
                max = tt;
                id = aa.getId();
                s = aa.getTitle();
            }
            trace(aa.getrChild());
        }
        return s;


    }

    public static double getRank(double votes, double rating) {
        double rank = ((votes / (votes + 25000)) * rating) + ((25000 / (votes + 25000)) * 6.9);
        return rank;

    }

    /**
     * Returns true if this tree contains no elements
     *
     * @return true if the number of elements in hashTable equals to 0 and false
     * otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    
    
}
