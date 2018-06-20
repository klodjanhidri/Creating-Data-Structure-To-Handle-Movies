
import java.io.*;

import java.util.*;

class Main1 {

    /*
     *  Το πρόγραμμα αυτό διαβάζει τον πηγαίο κώδικα του εαυτού του, βρίσκει τις διαφορετικές
        λέξεις που εμφανίζονται σε αυτό και πόσες φορές εμφανίζεται η κάθε μία τους. Τέλος τυπώνει
     *  την λέξη που είναι τελευταία λεξικογραφικά και το πλήθος των εμφανίσεων της.
     *  Στην προκειμένου (και αφού οι  χαρακτήρες είναι λατινικοί) η τελευταία
     *  λέξη είναι η “z” η οποία εμφανίζεται 3 φορές.
     */
    public void dowork(String param) {

        if (param == null) {
            System.exit(1);
        }

        StreamTokenizer in = null;
        try {
            in = new StreamTokenizer(new BufferedReader(new FileReader(param)));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            Integer one = new Integer(1);
            while ((in.nextToken() != in.TT_EOF)) { // while we haven’t reached EOF 
                if (in.ttype == in.TT_WORD) { // the current token is a word 
                    Integer freq = (Integer) map.get(in.sval); //in.sval is the token 
                    if (freq == null) {
                        freq = one;
                    } else {
                        freq = new Integer(freq.intValue() + 1);
                    }
                    map.put(in.sval, freq);
                }
            }

            SortedSet tmp = new TreeSet(map.keySet());
            String z = (String) tmp.last();
            System.out.println(z + " " + map.get(z));

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    public static void main(String args[]) {
        Main1 m = new Main1();
        m.dowork("/home/klodjan/NetBeansProjects/DataStructure/src/phase1/test.txt");
    }
}
