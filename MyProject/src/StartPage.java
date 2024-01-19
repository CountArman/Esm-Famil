import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
public class StartPage {
    ChooseInformation chooseInformation = new ChooseInformation();
    public void start(){
        Stage stage = new Stage();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        stage.getIcons().add(image);
        Button start = new Button("شروع");
        start.setPrefSize(100, 30);
        Button exit = new Button("خروج");
        exit.setPrefSize(80, 30);
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
            chooseInformation.choose();
        });
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> stage.close());
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(start, exit);
        vBox.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(vBox, 512, 560);
        stage.setScene(scene);
        stage.show();
    }
}