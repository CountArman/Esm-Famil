import javafx.animation.PauseTransition;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
public class ChooseInformation {
    ChooseLetter chooseLetter = new ChooseLetter();
    private int round = 1;
    private int minutes = 0;
    private int seconds = 45;
    private CheckBox[] checkBoxes= new CheckBox[11];
    public void choose(){
        Stage stage = new Stage();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        Rectangle rectangle = new Rectangle(300, 80, Color.LIGHTGOLDENRODYELLOW);
        rectangle.setArcHeight(40);
        rectangle.setArcWidth(40);
        Label error = new Label("حداقل پنج تا گزینه رو انتخاب کن" + "\n" + "تا بتونیم بریم بخش بعدی");
        error.setFont(Font.font(20));
        error.setTextAlignment(TextAlignment.CENTER);
        error.setVisible(false);
        rectangle.setVisible(false);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, error);
        stackPane.setLayoutX(106);
        stackPane.setLayoutY(240);
        stackPane.setAlignment(Pos.CENTER);
        Button up = new Button("+");
        Button down = new Button("-");
        Button minutePlus = new Button("+"), minuteMinus = new Button("-"),
            secondPlus = new Button("+"), secondMinus = new Button("-");
        Button finish = new Button("تمام");
        Text roundText = new Text("دور ها: " + round);
        Text minuteText = new Text("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
        Text secondText = new Text("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);
        up.setTextAlignment(TextAlignment.CENTER);
        down.setTextAlignment(TextAlignment.CENTER);
        HBox hBox1 = new HBox(10), hBox2 = new HBox(10),
                hBox3 = new HBox(10);
        hBox1.getChildren().addAll(down, up);
        hBox2.getChildren().addAll(minuteMinus, minutePlus);
        hBox3.getChildren().addAll(secondMinus, secondPlus);
        VBox vBox1 = new VBox(20);
        vBox1.setAlignment(Pos.CENTER);
        VBox vBox2 = new VBox(20);
        Pane pane = new Pane();
        checkBoxes[0] = new CheckBox("اسم");
        checkBoxes[1] = new CheckBox("فامیل");
        checkBoxes[2] = new CheckBox("شهر");
        checkBoxes[3] = new CheckBox("کشور");
        checkBoxes[4] = new CheckBox("غذا");
        checkBoxes[5] = new CheckBox("پوشاک");
        checkBoxes[6] = new CheckBox("میوه");
        checkBoxes[7] = new CheckBox("ماشین");
        checkBoxes[8] = new CheckBox("گل");
        checkBoxes[9] = new CheckBox("حیوان");
        checkBoxes[10] = new CheckBox("اشیا");
        vBox1.getChildren().addAll(roundText, hBox1, minuteText, hBox2, secondText, hBox3, finish);
        vBox2.getChildren().addAll(checkBoxes);
        vBox1.setLayoutX(400);
        vBox1.setLayoutY(130);
        vBox2.setLayoutX(75);
        vBox2.setLayoutY(85);
        pane.getChildren().addAll(vBox1, vBox2, stackPane);
        pane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(pane, 512, 560);
        finish.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(check()){
                stage.close();
                chooseLetter.chooseLetter(checkBoxes, round, minutes,seconds);
            }else {
                rectangle.setVisible(true);
                error.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.playFromStart();
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        error.setVisible(false);
                        rectangle.setVisible(false);
                    }
                });
            }
        });
        up.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            round++;
            roundText.setText("دور ها: " + round);
        });
        down.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(round > 1){
                round--;
                roundText.setText("دور ها: " + round);
            }
        });
        minutePlus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            minutes++;
            minuteText.setText("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
        });
        secondPlus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            seconds += 5;
            if(seconds == 60){
                seconds = 0;
                minutes++;
                minuteText.setText("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
            }
            secondText.setText("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);
        });
        minuteMinus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(minutes > 0){
                minutes--;
                minuteText.setText("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
            }
            if(minutes == 0 && seconds < 45){
                seconds = 45;
                secondText.setText("seconds: " + "" + seconds);
            }
        });
        secondMinus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(minutes * 60 + seconds > 45 && seconds > 0){
                seconds -= 5;
                secondText.setText("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);
            }
        });
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }
    private boolean check(){
        int result = 0;
        for (int i = 0; i < 11; i++) {
            if(checkBoxes[i].isSelected()) result++;
        }
        return result >= 5;
    }
}