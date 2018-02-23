import java.util.ArrayList;
import java.util.List;

public class HashTable {
    Node[] table;
    int count;
    HashTable(){
        table = new Node[100];
    }

    String getName(String k){
        int h = k.hashCode();
        int i = h&(table.length-1);
        for(Node e = table[i]; e!= null; e = e.next){
            if(k.equals(e.key)){
                return e.name;
            }
        }
        return null;
    }

    void put(String k, String n, double lat, double lon, double s, List<String> cat){
        int h = k.hashCode();
        int i = h & (table.length-1);
        for(Node e = table[i]; e!= null; e = e.next){
            if(k.equals(e.key)){
                e.name = n;
                return;
            }
        }
        Node p = new Node(k,n,lat,lon,s,cat,table[i]);
        table[i] = p;
        count++;
    }
}
