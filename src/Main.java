import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main {
    public static void main(String args[]){
        HashTable hashTable = new HashTable();
        File json = new File("business.json");
        String line;
        Set categorySet = new HashSet();


        try{Scanner scanner = new Scanner(json);
            while(scanner.hasNextLine() & hashTable.count < 10000){
                line = scanner.nextLine();
                JSONObject obj = new JSONObject(line);
                String id = obj.getString("business_id");
                String name = obj.getString("name");
                double stars = obj.getDouble("stars");
                JSONArray categoriesJ = obj.getJSONArray("categories");
                List categories = new ArrayList(categoriesJ.length());

                for(int i = 0; i < categoriesJ.length(); i++) {
                    categories.add(categoriesJ.toList().get(i));
                }
                //categories.
                hashTable.put(name, name,  stars, categories);
                for(int i = 0; i < categories.size(); i++){
                    categorySet.add(categories.get(i));


                }
            }
        }
        catch(Exception e){
            System.out.println("file not found");
        }
        hashTable.getNode("DAVIDsTEA").setSimilarAL();
    }
}
