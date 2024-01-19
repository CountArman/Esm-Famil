import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
public class MyProject extends Application {
    StartPage startPage = new StartPage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane stackPane = new StackPane();
        InputStream inputStream = new FileInputStream("esmFamil.png"),
                inputStream2 = new FileInputStream("icon.png");
        Image image = new Image(inputStream),
                image2 = new Image(inputStream2);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        stackPane.getChildren().add(imageView);
        Pane pane = new Pane();
        Text press = new Text("press any key to continue...");
        press.setFont(Font.font(25));
        press.setTextAlignment(TextAlignment.CENTER);
        press.setX(110);
        press.setY(520);
        stackPane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(press);
        stackPane.getChildren().add(pane);
        Scene scene = new Scene(stackPane, 512, 560);
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                primaryStage.close();
                startPage.start();
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            primaryStage.close();
            startPage.start();
        });
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(image2);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}