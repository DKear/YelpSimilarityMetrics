//This is the Node class that is used for the businesses parsed from the json file.
import java.util.*;

public class Node implements Comparator<Node> {
    String key;
    String name;
    Node next;
    double stars;
    double similarity;
    List<String> categories;
    HashTable hashTable;
    List<Node> similar = new ArrayList<>();

    //Nodes are just businesses
    Node(String key, String name, double stars, List<String> categories, Node next, HashTable hashTable){
        this.key = key;
        this.name = name;
        this.stars = stars;
        this.categories = categories;
        this.next = next;
        this.hashTable = hashTable;
    }

    //compare method to compare values of businesses by the business variable
    public int compare(Node o1, Node o2) {
        int n = (int)o2.similarity - (int)o1.similarity;
        return n;
    }

    //setting the similar arraylist
    void setSimilarAL(){
        //int for the category score
        int catScore;
        //int for the star score
        int starScore;
        //temporary node arraylist to hold the businesses to compare later
        List<Node> tempSimilar = new ArrayList<>();

        //traverse the hash table, compare categories and star values
        for(int i = 0; i < hashTable.table.length; i++){
            for(Node e = hashTable.table[i]; e!= null; e = e.next) {
                int catCount = 0;
                for (int j = 0; j < e.categories.size(); j++) {
                    for (int k = 0; k < this.categories.size(); k++) {
                        if (e.categories.get(j).equals(this.categories.get(k))) {
                            //if there is a similar category, add 100 to the category count
                            catCount= catCount + 100;
                        }
                    }
                }

                //set the star score, multiply their star value by 10, take the difference of their scores away from 50
                starScore = 50 -(int)Math.abs((e.stars*10) - (this.stars*10));
                //set the similarity score by adding the star and category scores
                e.similarity = catCount +starScore;
                //add the Node to the temporary arraylist
                tempSimilar.add(e);


            }
        }

        //remove yourself from the arraylist, you are clearly the most similar to yourself
        tempSimilar.remove(this);
        //sort the temporary arraylist
        Collections.sort(tempSimilar, this);

        //add the top 5 values to similar arraylist
        for (int i = 0; i < 5; i++){
            similar.add(tempSimilar.get(i));
        }



    }

}
