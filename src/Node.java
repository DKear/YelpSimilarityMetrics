import java.util.ArrayList;
import java.util.List;

public class Node {
    String key;
    String name;
    Node next;
    double latitude;
    double longitude;
    double stars;
    List<String> categories;

    Node(String key, String name, double latitude, double longitude, double stars, List<String> categories, Node next){
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stars = stars;
        this.categories = categories;
        this.next = next;
    }
}
