import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.Scanner;
public class Match {
    private int playerScore = 0, computerScore = 0;
    private TextField name = new TextField(), lastName = new TextField(),
            city = new TextField(), country = new TextField(),
            food = new TextField(), clothing = new TextField(),
            fruit = new TextField(), car = new TextField(),
            flower = new TextField(), animal = new TextField(),
            object = new TextField();
    private String[] computerWords = new String[11];
    private File[] inputs = new File[11];
    private Scanner scanner;
    private Timeline timeline;
    private Text timer;
    private int minutes;
    private int seconds;
    Scoreboard scoreboard = new Scoreboard();
    public Match(){
        for (int i = 0; i < 11; i++)
            computerWords[i] = new String();
        inputs[0] = new File("اسم.txt");
        inputs[1] = new File("فامیل.txt");
        inputs[2] = new File("شهر.txt");
        inputs[3] = new File("کشور.txt");
        inputs[4] = new File("غذا.txt");
        inputs[5] = new File("پوشاک.txt");
        inputs[6] = new File("میوه.txt");
        inputs[7] = new File("ماشین.txt");
        inputs[8] = new File("گل.txt");
        inputs[9] = new File("حیوان.txt");
        inputs[10] = new File("اشیا.txt");
    }
    private void change() {
        if(seconds == 0){
            minutes--;
            seconds = 60;
        }
        seconds--;
        timer.setText((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds);
    }
    private boolean check(){
        return minutes * 60 + seconds == 0;
    }
    public void match(char ch, CheckBox[] checkBoxes, boolean lastRound, int minutes, int seconds){
        Stage stage = new Stage();
        name.setText(null);
        lastName.setText(null);
        city.setText(null);
        country.setText(null);
        food.setText(null);
        clothing.setText(null);
        fruit.setText(null);
        car.setText(null);
        flower.setText(null);
        animal.setText(null);
        object.setText(null);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("icon.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        this.minutes = minutes;
        this.seconds = seconds;
        timer = new Text((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            change();
            if(check()){
                timeline.stop();
                stage.close();
                scoreCalculation(checkBoxes, ch);
                scoreboard.scoreboard(lastRound, playerScore, computerScore);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();
        name.setPromptText("اسم");
        lastName.setPromptText("فامیل");
        city.setPromptText("شهر");
        country.setPromptText("کشور");
        food.setPromptText("غذا");
        clothing.setPromptText("پوشاک");
        fruit.setPromptText("میوه");
        car.setPromptText("ماشین");
        flower.setPromptText("گل");
        animal.setPromptText("حیوان");
        object.setPromptText("اشیا");
        VBox vBox = new VBox(10);
        if(checkBoxes[0].isSelected()) vBox.getChildren().add(name);
        if(checkBoxes[1].isSelected()) vBox.getChildren().add(lastName);
        if(checkBoxes[2].isSelected()) vBox.getChildren().add(city);
        if(checkBoxes[3].isSelected()) vBox.getChildren().add(country);
        if(checkBoxes[4].isSelected()) vBox.getChildren().add(food);
        if(checkBoxes[5].isSelected()) vBox.getChildren().add(clothing);
        if(checkBoxes[6].isSelected()) vBox.getChildren().add(fruit);
        if(checkBoxes[7].isSelected()) vBox.getChildren().add(car);
        if(checkBoxes[8].isSelected()) vBox.getChildren().add(flower);
        if(checkBoxes[9].isSelected()) vBox.getChildren().add(animal);
        if(checkBoxes[10].isSelected()) vBox.getChildren().add(object);
        Button finish = new Button("تمام");
        finish.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            timeline.stop();
            stage.close();
            scoreCalculation(checkBoxes, ch);
            scoreboard.scoreboard(lastRound, playerScore, computerScore);
        });
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(50);
        vBox.setLayoutY(70);
        Pane pane = new Pane();
        Text letter = new Text("حرف: " + ch);
        letter.setFont(Font.font(30));
        pane.getChildren().add(finish);
        finish.setLayoutX(320);
        finish.setLayoutY(410);
        letter.setX(320);
        letter.setY(150);
        finish.setFont(Font.font(20));
        finish.setPrefSize(100, 45);
        pane.getChildren().add(vBox);
        pane.getChildren().addAll(timer, letter);
        pane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        timer.setTextAlignment(TextAlignment.CENTER);
        timer.setX(320);
        timer.setY(300);
        timer.setFont(Font.font(40));
        Scene scene = new Scene(pane, 512, 560);
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }
    private int checkName(String str, String str2, char ch) throws FileNotFoundException {
        if(str2.equals(str)) return 5;
        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[0]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkLastName(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;
        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[1]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkCity(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[2]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkCountry(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[3]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkFood(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[4]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkClothing(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[5]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkFruit(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[6]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkCar(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[7]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkFlower(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[8]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkAnimal(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;
        Scanner scanner = new Scanner(inputs[9]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private int checkObject(String str, String str2, char ch) throws FileNotFoundException{
        if(str2.equals(str)) return 5;

        if((ch == 'ا') && (str.charAt(0) == 'ا' || str.charAt(0) == 'آ')){

        }else if(str.charAt(0) != ch) return 0;

        Scanner scanner = new Scanner(inputs[10]);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            if(str.equals(string)) return 10;
        }
        return 0;
    }
    private void scoreCalculation(CheckBox[] checkBoxes, char ch){
        for (int i = 0; i < 11; i++) {
            try {
                scanner = new Scanner(inputs[i]);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            computerWords[i] = scanner.nextLine();
            while(computerWords[i].charAt(0) != ch && scanner.hasNext()){
                computerWords[i] = scanner.nextLine();
                if((ch == 'ا') && (computerWords[i].charAt(0) == 'ا' || computerWords[i].charAt(0) == 'آ'))
                    break;
            }
        }
        try {
            if(checkBoxes[0].isSelected()){
                if(name.getText() == null) computerScore += 10;
                else {
                    playerScore += checkName(name.getText(), computerWords[0], ch);
                    computerScore += checkName(computerWords[0], name.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[1].isSelected()){
                if(lastName.getText() == null) computerScore += 10;
                else {
                    playerScore += checkLastName(lastName.getText(), computerWords[1], ch);
                    computerScore += checkLastName(computerWords[1], lastName.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[2].isSelected()){
                if(city.getText() == null) computerScore += 10;
                else {
                    playerScore += checkCity(city.getText(), computerWords[2], ch);
                    computerScore += checkCity(computerWords[2], city.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[3].isSelected()){
                if(country.getText() == null) computerScore += 10;
                else {
                    playerScore += checkCountry(country.getText(), computerWords[3], ch);
                    computerScore += checkCountry(computerWords[3], country.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[4].isSelected()){
                if(food.getText() == null) computerScore += 10;
                else {
                    playerScore += checkFood(food.getText(), computerWords[4], ch);
                    computerScore += checkFood(computerWords[4], food.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[5].isSelected()){
                if(clothing.getText() == null) computerScore += 10;
                else {
                    playerScore += checkClothing(clothing.getText(), computerWords[5], ch);
                    computerScore += checkClothing(computerWords[5], clothing.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[6].isSelected()){
                if(fruit.getText() == null) computerScore += 10;
                else {
                    playerScore += checkFruit(fruit.getText(), computerWords[6], ch);
                    computerScore += checkFruit(computerWords[6], fruit.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[7].isSelected()){
                if(car.getText() == null) computerScore += 10;
                else {
                    playerScore += checkCar(car.getText(), computerWords[7], ch);
                    computerScore += checkCar(computerWords[7], car.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[8].isSelected()){
                if(flower.getText() == null) computerScore += 10;
                else {
                    playerScore += checkFlower(flower.getText(), computerWords[8], ch);
                    computerScore += checkFlower(computerWords[8], flower.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[9].isSelected()){
                if(animal.getText() == null) computerScore += 10;
                else {
                    playerScore += checkAnimal(animal.getText(), computerWords[9], ch);
                    computerScore += checkAnimal(computerWords[9], animal.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if(checkBoxes[10].isSelected()){
                if(object.getText() == null) computerScore += 10;
                else {
                    playerScore += checkObject(object.getText(), computerWords[10], ch);
                    computerScore += checkObject(computerWords[10], object.getText(), ch);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}