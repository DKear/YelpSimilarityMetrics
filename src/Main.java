import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        HashTable hashTable = new HashTable();
        File json = new File("business.json");
        String line = new String();


        try{Scanner scanner = new Scanner(json);
            while(scanner.hasNextLine() & hashTable.count < 10000){
                line = scanner.nextLine();
                JSONObject obj = new JSONObject(line);
                String id = obj.getString("business_id");
                String name = obj.getString("name");
                double lat = obj.getDouble("latitude");
                double lon = obj.getDouble("longitude");
                double stars = obj.getDouble("stars");
                JSONArray categoriesJ = obj.getJSONArray("categories");
                List categories = new ArrayList(categoriesJ.length());

                for(int i = 0; i < categoriesJ.length(); i++) {
                    categories.add(categoriesJ.toList().get(i));
                }
                //categories.
                hashTable.put(name, name, lat, lon, stars, categories);
            }
        }
        catch(Exception e){
            System.out.println("file not found");
        }
    }
}
