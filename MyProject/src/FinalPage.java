import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
public class FinalPage {
    public void finalPage(){
        Stage stage = new Stage();
        Text text = new Text("THANKS FOR PLAYING :)");
        text.setFont(Font.font(35));
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStream inputStream2;
        try {
            inputStream2 = new FileInputStream("gameOver.png");
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        Image image2 = new Image(inputStream2);
        ImageView imageView = new ImageView(image2);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(imageView, text);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.getIcons().add(image);
        Scene scene = new Scene(vBox, 521, 560);
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                stage.close();
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.close());
        stage.setScene(scene);
        stage.show();
    }
}