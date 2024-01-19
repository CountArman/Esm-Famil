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
import java.util.Random;
public class ChooseLetter {
    private int round = 0;
    private int[] usedLetters = new int[32];
    Match match = new Match();
    private HBox hBox1 = new HBox(20), hBox2 = new HBox(20),
            hBox3 = new HBox(20), hBox4 = new HBox(20),
            hBox5 = new HBox(20), hBox6 = new HBox(20),
            hBox7 = new HBox(20), hBox8 = new HBox(20);
    private CheckBox[] checkBoxes = new CheckBox[32];
    public ChooseLetter(){
        checkBoxes[0] = new CheckBox("الف"); checkBoxes[1] = new CheckBox("ب");
        checkBoxes[2] = new CheckBox("پ"); checkBoxes[3] = new CheckBox("ت");
        checkBoxes[4] = new CheckBox("ث"); checkBoxes[5] = new CheckBox("ج");
        checkBoxes[6] = new CheckBox("چ"); checkBoxes[7] = new CheckBox("ح");
        checkBoxes[8] = new CheckBox("خ"); checkBoxes[9] = new CheckBox("د");
        checkBoxes[10] = new CheckBox("ذ"); checkBoxes[11] = new CheckBox("ر");
        checkBoxes[12] = new CheckBox("ز"); checkBoxes[13] = new CheckBox("ژ");
        checkBoxes[14] = new CheckBox("س"); checkBoxes[15] = new CheckBox("ش");
        checkBoxes[16] = new CheckBox("ص"); checkBoxes[17] = new CheckBox("ض");
        checkBoxes[18] = new CheckBox("ط"); checkBoxes[19] = new CheckBox("ظ");
        checkBoxes[20] = new CheckBox("ع"); checkBoxes[21] = new CheckBox("غ");
        checkBoxes[22] = new CheckBox("ف"); checkBoxes[23] = new CheckBox("ق");
        checkBoxes[24] = new CheckBox("ک"); checkBoxes[25] = new CheckBox("گ");
        checkBoxes[26] = new CheckBox("ل"); checkBoxes[27] = new CheckBox("م");
        checkBoxes[28] = new CheckBox("ن"); checkBoxes[29] = new CheckBox("و");
        checkBoxes[30] = new CheckBox("ه"); checkBoxes[31] = new CheckBox("ی");
        for (int i = 0; i < 4; i++)
            hBox1.getChildren().add(checkBoxes[i]);
        for (int i = 4; i < 8; i++)
            hBox2.getChildren().add(checkBoxes[i]);
        for (int i = 8; i < 12; i++)
            hBox3.getChildren().add(checkBoxes[i]);
        for (int i = 12; i < 16; i++)
            hBox4.getChildren().add(checkBoxes[i]);
        for (int i = 16; i < 20; i++)
            hBox5.getChildren().add(checkBoxes[i]);
        for (int i = 20; i < 24; i++)
            hBox6.getChildren().add(checkBoxes[i]);
        for (int i = 24; i < 28; i++)
            hBox7.getChildren().add(checkBoxes[i]);
        for (int i = 28; i < 32; i++)
            hBox8.getChildren().add(checkBoxes[i]);
        for(int i = 0; i < 32; i++) usedLetters[i] = 0;
    }
    public void chooseLetter(CheckBox[] chB, int round, int minutes, int seconds){
        Stage stage = new Stage();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Rectangle rectangle = new Rectangle(260, 80, Color.LIGHTGOLDENRODYELLOW);
        rectangle.setArcHeight(40);
        rectangle.setArcWidth(40);
        Label error1 = new Label("یک حرف انتخاب کنید");
        Label error2 = new Label("بیشتر از یکی نمیتونی " + "\n" + "انتخاب کنی :)");
        error1.setFont(Font.font(20));
        error1.setTextAlignment(TextAlignment.CENTER);
        error2.setFont(Font.font(20));
        error2.setTextAlignment(TextAlignment.CENTER);
        error1.setVisible(false);
        error2.setVisible(false);
        rectangle.setVisible(false);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, error1, error2);
        stackPane.setLayoutX(126);
        stackPane.setLayoutY(40);
        Image image = new Image(inputStream);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        hBox4.setAlignment(Pos.CENTER);
        hBox5.setAlignment(Pos.CENTER);
        hBox6.setAlignment(Pos.CENTER);
        hBox7.setAlignment(Pos.CENTER);
        hBox8.setAlignment(Pos.CENTER);
        Button player = new Button("بازیکن"), computer = new Button("کامپیوتر");
        VBox vBox = new VBox(20);
        vBox.setLayoutX(165);
        vBox.setLayoutY(150);
        computer.setDisable(true);
        HBox buttons = new HBox(40);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(computer, player);
        vBox.getChildren().addAll(hBox1, hBox2,
                hBox3, hBox4, hBox5, hBox6,
                hBox7, hBox8, buttons);
        computer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Random random = new Random();
            int number = random.nextInt(32);
            while(usedLetters[number] == 1)
                number = random.nextInt(32);
            usedLetters[number] = 1;
            char ch = checkBoxes[number].getText().charAt(0);
            for (int i = 0; i < 32; i++) checkBoxes[i].setDisable(false);
            player.setDisable(false);
            computer.setDisable(true);
            if(number >= 0 && number < 4){
                hBox1.getChildren().remove(checkBoxes[number]);
            } else if (number >= 4 && number < 8) {
                hBox2.getChildren().remove(checkBoxes[number]);
            } else if (number >= 8 && number < 12) {
                hBox3.getChildren().remove(checkBoxes[number]);
            } else if (number >= 12 && number < 16) {
                hBox4.getChildren().remove(checkBoxes[number]);
            } else if (number >= 16 && number < 20) {
                hBox5.getChildren().remove(checkBoxes[number]);
            } else if (number >= 20 && number < 24) {
                hBox6.getChildren().remove(checkBoxes[number]);
            } else if (number >= 24 && number < 28) {
                hBox7.getChildren().remove(checkBoxes[number]);
            } else if (number >= 28 && number < 32) {
                hBox8.getChildren().remove(checkBoxes[number]);
            }
            this.round++;
            if(this.round == round){
                stage.close();
                match.match(ch, chB, true, minutes, seconds);
            }else match.match(ch, chB, false, minutes, seconds);
        });
        player.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(checkLetters(checkBoxes) == 1){
                char ch = 0;
                for (int i = 0; i < 4; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox1.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 4; i < 8; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox2.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 8; i < 12; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox3.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 12; i < 16; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox4.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 16; i < 20; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox5.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 20; i < 24; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox6.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 24; i < 28; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox7.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 28; i < 32; i++) {
                    if(checkBoxes[i].isSelected()) {
                        ch = checkBoxes[i].getText().charAt(0);
                        usedLetters[i] = 1;
                        hBox8.getChildren().remove(checkBoxes[i]);
                    }
                }
                for (int i = 0; i < 32; i++) checkBoxes[i].setSelected(false);
                for(int i = 0; i < 32; i++) checkBoxes[i].setDisable(true);
                player.setDisable(true);
                computer.setDisable(false);
                this.round++;
                if(this.round == round){
                    stage.close();
                    match.match(ch, chB, true, minutes, seconds);
                }else match.match(ch, chB, false, minutes, seconds);

            } else if (checkLetters(checkBoxes) > 1) {
                rectangle.setVisible(true);
                error2.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.playFromStart();
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        rectangle.setVisible(false);
                        error2.setVisible(false);
                    }
                });
            } else if (checkLetters(checkBoxes) == 0) {
                rectangle.setVisible(true);
                error1.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                pause.playFromStart();
                pause.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        rectangle.setVisible(false);
                        error1.setVisible(false);
                    }
                });
            }
        });
        vBox.setAlignment(Pos.CENTER);
        Pane pane = new Pane();
        pane.getChildren().addAll(vBox, stackPane);
        pane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(pane, 512, 560);
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }
    private int checkLetters(CheckBox[] checkBoxes){
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if(checkBoxes[i].isSelected()) result++;
        }
        if (result == 1) return 1;
        else if(result > 1) return result;
        else return 0;
    }
}