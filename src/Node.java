import java.util.*;

public class Node implements Comparator<Node> {
    String key;
    String name;
    Node next;
    double latitude;
    double longitude;
    double stars;
    double similarity;
    List<String> categories;
    HashTable hashTable;
    List<Node> similar = new ArrayList<>();


    Node(String key, String name, double stars, List<String> categories, Node next, HashTable hashTable){
        this.key = key;
        this.name = name;
        this.stars = stars;
        this.categories = categories;
        this.next = next;
        this.hashTable = hashTable;
    }

    public int compare(Node o1, Node o2) {
        int n = (int)o2.similarity - (int)o1.similarity;
        return n;
    }

    void setSimilar(double d ){
        similarity = d;
    }

    void setSimilarAL(){
        int catScore;
        int starScore;
        List<Node> tempSimilar = new ArrayList<>();

        for(int i = 0; i < hashTable.table.length; i++){
            for(Node e = hashTable.table[i]; e!= null; e = e.next) {
                int catCount = 0;
                for (int j = 0; j < e.categories.size(); j++) {
                    for (int k = 0; k < this.categories.size(); k++) {
                        if (e.categories.get(j).equals(this.categories.get(k))) {
                            catCount= catCount + 100;
                        }
                    }
                }
                catScore = catCount;


                e.similarity = catScore;
                tempSimilar.add(e);

           /* for(Node e = hashTable.table[i]; e!= null; e = e.next){
                int nextCatCount = 0;
                for (int j = 0; j < hashTable.table[i].next.categories.size(); j++){
                    for(int k = 0; k < this.categories.size(); k++){
                        if(hashTable.table[i].next.categories.get(j).equals(this.categories.get(k))){
                            nextCatCount++;
                        }
                    }
                }
                hashTable.table[i].next.similarity = nextCatCount;
                tempSimilar.add(hashTable.table[i].next);
            }*/
            }
        }

        Collections.sort(tempSimilar, this);
        for (int i = 0; i < 5; i++){
            similar.add(tempSimilar.get(i));
        }



    }
    /*Node[] getSimilar(){

    }*/
}
