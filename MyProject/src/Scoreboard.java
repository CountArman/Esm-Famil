import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
public class Scoreboard {
    FinalPage finalPage = new FinalPage();
    public void scoreboard(boolean lastRound, int playerScore, int computerScore){
        Stage stage = new Stage();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        stage.getIcons().add(image);
        Pane pane = new Pane();
        Button button = new Button();
        Text playerText = new Text("امتیاز بازیکن: " + playerScore);
        Text computerText = new Text("امتیاز کامپیوتر: " + computerScore);
        playerText.setTextAlignment(TextAlignment.CENTER);
        playerText.setFont(Font.font(30));
        playerText.setX(180);
        playerText.setY(150);
        computerText.setTextAlignment(TextAlignment.CENTER);
        computerText.setFont(Font.font(30));
        computerText.setX(145);
        computerText.setY(220);
        if(lastRound) button.setText("تمام");
        else button.setText("ادامه");
        button.setPrefSize(100, 50);
        button.setAlignment(Pos.CENTER);
        button.setLayoutX(206);
        button.setLayoutY(350);
        button.setFont(Font.font(20));
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(lastRound){
                stage.close();
                finalPage.finalPage();
            }else {
                stage.close();
            }
        });
        pane.getChildren().addAll(playerText, computerText, button);
        pane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(pane, 512, 560);
        stage.setScene(scene);
        stage.show();
    }
}