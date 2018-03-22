//This is the HashTable class that holds all the nodes. It is an array of 10 thousand elements where each Node is a linked list
import java.util.List;

public class HashTable {
    Node[] table;
    int count;
    //HashTable defaults to 10000 because 100000 businesses are being read from the JSON. Resize() needed for more optimal performance.
    HashTable(){
        table = new Node[10000];
    }


    //retrieve node from hash table
    Node getNode(String key){
        //hash the string
        int h = hash(key);
        //bitwise & to fit it in the array
        int i = h&(table.length-1);
        //search for node and get it
        for(Node e = table[i]; e!= null; e = e.next){
            if(key.equals(e.key)){
                return e;
            }
        }
        //otherwise return null
        return null;
    }

    //place a new node in the hash table, very similar to getNode()
    void put(String k, String n, double s, List<String> cat){
        int h = hash(k);
        int i = h & (table.length-1);
        for(Node e = table[i]; e!= null; e = e.next){
            if(k.equals(e.key)){
                e.name = n;
                return;
            }
        }
        //create new node after finding spot
        Node p = new Node(k,n,s,cat,table[i], this);
        table[i] = p;
        count++;
    }

    //hash function, takes in a string, gives the hash value as an int
    int hash(String s){
        int hash = 0;
        int h = hash;
        if(h ==0){
            //character offset
            int off = 0;
            //string to character array
            char val[] = s.toCharArray();
            //length of string
            int len = s.length();
            //loop through the character array use prime number to hash each character and add it all up
            for(int i = 0; i < len; i++){
                h = 3*h + val[off++];
            }
        }
        return h;
    }

}
