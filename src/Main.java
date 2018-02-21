import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        HashTable hashTable = new HashTable();
        File json = new File("business.json");
        String line = new String();
        try{Scanner scanner = new Scanner(json);
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                JSONObject obj = new JSONObject(line);
                String id = obj.getString("business_id");
                String name = obj.getString("name");
                hashTable.put(id, name);
            }
        }
        catch(Exception e){
            System.out.println("file not found");
        }
    }
}
