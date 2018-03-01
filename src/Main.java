//Main class and method. Holds a lot of GUI.
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
    TextField searchField;
    Button searchButton;
    Text title;
    HBox hbox;
    HBox hbox2;
    VBox vbox;
    TextArea textAreaQuery;
    TextArea textAreaResult1;
    TextArea textAreaResult2;
    TextArea textAreaResult3;
    TextArea textAreaResult4;
    TextArea textAreaResult5;

    HashTable hashTable = new HashTable();
    File json = new File("business.json");
    String line;
    Set categorySet = new HashSet();

    //setup method for the GUI
    void setup(){
        try{Scanner scanner = new Scanner(json);
            while(scanner.hasNextLine() & hashTable.count < 10000){
                line = scanner.nextLine();
                JSONObject obj = new JSONObject(line);
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
    }
    public static void main(String args[]){


        launch(args);
    }
    //Execute the GUI
    public void start(Stage primaryStage) throws Exception{

        setup();

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        VBox vbox = addVBox();
        HBox hbox2 = addHBox2();
        border.setTop(hbox);
        border.setCenter(vbox);
        border.setBottom(hbox2);
        hbox2.setAlignment(Pos.TOP_LEFT);



        primaryStage.setTitle("Yelp similarity finder");



        Scene scene = new Scene(border, 1500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    //Event handler, there is only one Button and a TextField to read from and 6 (non-editable) TextAreas to write to.
    public void handle(ActionEvent event){
        if(event.getSource() == searchButton){
            try{

                Node n = hashTable.getNode(searchField.getText());
                n.setSimilarAL();
                textAreaQuery.setText("Name: " + n.name + "\n" + "Rating: " + n.stars + "\n" + "Categories: " + n.categories.toString());
                textAreaResult1.setText("Name: " + n.similar.get(0).name + "\n" + "Rating: " + n.similar.get(0).stars + "\n" + "Categories: " + n.similar.get(0).categories.toString());
                textAreaResult2.setText("Name: " + n.similar.get(1).name + "\n" + "Rating: " + n.similar.get(1).stars + "\n" + "Categories: " + n.similar.get(1).categories.toString());
                textAreaResult3.setText("Name: " + n.similar.get(2).name + "\n" + "Rating: " + n.similar.get(2).stars + "\n" + "Categories: " + n.similar.get(2).categories.toString());
                textAreaResult4.setText("Name: " + n.similar.get(3).name + "\n" + "Rating: " + n.similar.get(3).stars + "\n" + "Categories: " + n.similar.get(3).categories.toString());
                textAreaResult5.setText("Name: " + n.similar.get(4).name + "\n" + "Rating: " + n.similar.get(4).stars + "\n" + "Categories: " + n.similar.get(4).categories.toString());


            }catch(Exception e){

            }

        }
    }
    //top panel where user input from TextField and Button resides
    HBox addHBox(){
        hbox = new HBox();
        searchField = new TextField();
        searchField.setPrefColumnCount(30);
        searchButton = new Button("Search");
        searchButton.setOnAction(this);
        hbox.getChildren().addAll(searchField, searchButton);
        return hbox;
    }
    //middle panel where the search result of the top panel resides
     VBox addVBox(){
        vbox = new VBox();
        title = new Text("Search result:");
        textAreaQuery = new TextArea();
        textAreaQuery.setEditable(false);
         Text lowertitle = new Text("Similar:");

         vbox.getChildren().addAll(title, textAreaQuery, lowertitle);
        return vbox;
    }
    //bottom panel where the 5 most similar businesses appear
    HBox addHBox2(){
        hbox2 = new HBox();
        textAreaResult1 = new TextArea();
        textAreaResult2 = new TextArea();
        textAreaResult3 = new TextArea();
        textAreaResult4 = new TextArea();
        textAreaResult5 = new TextArea();
        textAreaResult1.setEditable(false);
        textAreaResult1.setWrapText(true);
        textAreaResult2.setEditable(false);
        textAreaResult2.setWrapText(true);
        textAreaResult3.setEditable(false);
        textAreaResult3.setWrapText(true);
        textAreaResult4.setEditable(false);
        textAreaResult4.setWrapText(true);
        textAreaResult5.setEditable(false);
        textAreaResult5.setWrapText(true);
        hbox2.getChildren().addAll(textAreaResult1, textAreaResult2, textAreaResult3, textAreaResult4, textAreaResult5);
        return hbox2;

    }
}
