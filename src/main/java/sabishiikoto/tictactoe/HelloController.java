package sabishiikoto.tictactoe;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private GridPane gridPaneForMatch;

    @FXML
    private ImageView imageViewForPlayer1;

    @FXML
    private ImageView imageViewForPlayer2;

    @FXML
    private Label labelForError;

    @FXML
    private Label labelForPlayer1;

    @FXML
    private Label labelForPlayer2;

    @FXML
    private Label labelForTitle;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private MenuBar menuBar;

    private static int turn = 1;

    private static char[][] map = null;
    private static Button[][] buttonMap = null;
    private static int level = 0;
    private static int length = 0;
    private static String X = "";
    private static String O = "";
    private static int counts = 0;
    private static boolean win = false;
    private static boolean bot = true;
    private static String gridPaneColor = "";


    @FXML
    void aboutTrigger(ActionEvent event) {
        // Set the about's alert info
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About This Tic-Tac-Toe!");
        alert.setHeaderText("Information and Contact");
        alert.setContentText("Hi, I hope you enjoy this game.\nFor my contact, check out my GitHub:\nhttps://github.com/SabishiiKoto");
        // Set the about's alert graphic
        Image image = new Image(getClass().getResource("/assets/Sabii's avatar.jpeg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        alert.setGraphic(imageView);
        // Show the alert
        alert.showAndWait();
    }

    @FXML
    void blueColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #0196C1;");
        labelForTitle.setStyle("-fx-text-fill: #046C95;");
        mainPane.setStyle("-fx-background-color: #B3E0EE;");
        labelForError.setStyle("-fx-background-color: #B3E0EE;-fx-text-fill: #083346;");

        labelForPlayer1.setStyle("-fx-text-fill: #046C95;");
        labelForPlayer2.setStyle("-fx-text-fill: #046C95;");

        oImage = new Image(getClass().getResource("/assets/oBlue.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xBlue.png").toExternalForm());

        gridPaneColor = "#B3E0EE";
        gridpaneMapping();

//
    }

    @FXML
    void darkColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #2B2F6C;");
        labelForTitle.setStyle("-fx-text-fill: #DE978F;");
        mainPane.setStyle("-fx-background-color: #564779;");
        labelForError.setStyle("-fx-background-color: #564779;-fx-text-fill: #DE978F;");

        labelForPlayer1.setStyle("-fx-text-fill: #DE978F;");
        labelForPlayer2.setStyle("-fx-text-fill: #DE978F;");

        oImage = new Image(getClass().getResource("/assets/oDark.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xDark.png").toExternalForm());

        gridPaneColor ="#564779";
        gridpaneMapping();

    }

    @FXML
    void greenColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #549895;");
        labelForTitle.setStyle("-fx-text-fill: #387271;");
        mainPane.setStyle("-fx-background-color: #8EBCB1;");
        labelForError.setStyle("-fx-background-color: #8EBCB1;-fx-text-fill: #245254;");

        labelForPlayer1.setStyle("-fx-text-fill: #387271;");
        labelForPlayer2.setStyle("-fx-text-fill: #387271;");

        oImage = new Image(getClass().getResource("/assets/oGreen.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xGreen.png").toExternalForm());

        gridPaneColor ="#8EBCB1";
        gridpaneMapping();

    }

    @FXML
    void pinkColorTrigger(ActionEvent event) {

        menuBar.setStyle("-fx-background-color: #EABEC3;");
        labelForTitle.setStyle("-fx-text-fill: #DD868C;");
        mainPane.setStyle("-fx-background-color: #F5DDE0;");
        labelForError.setStyle("-fx-background-color: #F5DDE0;-fx-text-fill: #D0637C;");

        labelForPlayer1.setStyle("-fx-text-fill: #DD868C;");
        labelForPlayer2.setStyle("-fx-text-fill: #DD868C;");
        oImage = new Image(getClass().getResource("/assets/oPink.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xPink.png").toExternalForm());

        gridPaneColor ="#F5DDE0";
        gridpaneMapping();

    }

    @FXML
    void vintageColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #F6BD60;");
        labelForTitle.setStyle("-fx-text-fill: #84A59D;");
        mainPane.setStyle("-fx-background-color: #F7EDE2;");
        labelForError.setStyle("-fx-background-color: #F7EDE2;-fx-text-fill: #F28482;");

        labelForPlayer1.setStyle("-fx-text-fill: #F28482;");
        labelForPlayer2.setStyle("-fx-text-fill: #F28482;");

        oImage = new Image(getClass().getResource("/assets/oVintage.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xVintage.png").toExternalForm());

        gridPaneColor = "#F7EDE2";
        gridpaneMapping();

    }

    @FXML
    void easyModeTrigger(ActionEvent event) {
        level = 3;
        length = 3;
        gridpaneMapping();

        labelForError.setText("Hello! To win this level, get 3 identical images in a row/column/slope.");

    }

    @FXML
    void exitTrigger(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void hardModeTrigger(ActionEvent event) {
        level = 5;
        length = 4;
        gridpaneMapping();

        labelForError.setText("Hello! To win this level, get 4 identical images in a row/column/slope.");

    }

    @FXML
    void mediumModeTrigger(ActionEvent event) {
        level = 4;
        length = 3;
        gridpaneMapping();

        labelForError.setText("Hello! To win this level, get 3 identical images in a row/column/slope.");

    }

    public void gridpaneMapping(){
        // Set the 2d map
        map = new char[level][level];
        buttonMap = new Button[level][level];
        // Reset variables
        counts = 0;
        win = false;
        labelForTitle.setText("Tic-Tac-Toe");


        // Clear things inside the gridpane
        gridPaneForMatch.getRowConstraints().clear();
        gridPaneForMatch.getColumnConstraints().clear();
        gridPaneForMatch.getChildren().clear();

        // Set the size of the gridpane
        double size = 0;
        if (level == 3){
            size = 126;
        }
        else if (level == 4){
            size = 90;
        }
        else if (level == 5){
            size = 70;
        }

        for (int row = 0; row < level; row ++){
            for (int column = 0; column < level; column ++){
                Button button = new Button();
                button.setPrefSize(size, size);
                button.setStyle(String.format("-fx-border-color: white; -fx-border-width: 1;-fx-background-color: %s;", gridPaneColor));
                button.setOnMouseClicked(new myMouseClickerHandler(row, column, button));
                buttonMap[row][column] = button;
                gridPaneForMatch.add(button, column, row);
            }
        }
        BorderStroke borderStroke = new BorderStroke(
                Color.GREY,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(5)
        );

        Border border = new Border(borderStroke);

        gridPaneForMatch.setStyle(String.format("-fx-color-fill: %s;", gridPaneColor));
        gridPaneForMatch.setPadding(new Insets(8)); // Adds space inside the border
        gridPaneForMatch.setPickOnBounds(true);
        gridPaneForMatch.setBorder(border);
        gridPaneForMatch.setPrefSize(378,378);
        gridPaneForMatch.setAlignment(Pos.CENTER);


        // Pick o gets to go first or x gets to go first
        turn = Functions.randomNumber(2) + 1;
        Image tempImage = null;
        if (turn == 1) {
            tempImage = oImage;
        } else {
            tempImage = xImage;
        }
        // Pick player 1 goes first or 2 goes first
        int player = Functions.randomNumber(2) + 1;
        if (!bot) {
            if (player == 1) {
                imageViewForPlayer1.setImage(tempImage);
                imageViewForPlayer2.setImage(null);
            } else {
                imageViewForPlayer1.setImage(null);
                imageViewForPlayer2.setImage(tempImage);
            }
            if ((turn == 1 && player == 1) || (turn == 2 && player == 2)) {
                O = "Player 1";
                X = "Player 2";
            } else if ((turn == 1 && player == 2) || (turn == 2 && player == 1)) {
                X = "Player 1";
                O = "Player 2";
            }
        }
        else{
            if (level == 3) {
                size = 80;
            } else if (level == 4) {
                size = 60;
            } else if (level == 5) {
                size = 40;
            }
            if ((turn == 1 && player == 1) || (turn == 2 && player == 2)) {
                O = "You";
                X = "Bot";
            } else if ((turn == 1 && player == 2) || (turn == 2 && player == 1)) {
                X = "You";
                O = "Bot";
            }
            labelForPlayer1.setText("You");
            labelForPlayer2.setText("Bot");
            if (player == 1) {
                imageViewForPlayer1.setImage(tempImage);
                imageViewForPlayer2.setImage(null);
            } else {
                imageViewForPlayer1.setImage(null);
                imageViewForPlayer2.setImage(tempImage);

                ImageView imageView = new ImageView(tempImage);
                imageView.setFitHeight(size);
                imageView.setFitWidth(size);
                int[] location = Functions.randomLocation(map);

                buttonMap[location[0]][location[1]].setGraphic(imageView);
                buttonMap[location[0]][location[1]].setOnMouseClicked(null);

                if (turn == 1){
                    map[location[0]][location[1]] = 'o';
                    turn = 2;
                    imageViewForPlayer1.setImage(xImage);
                    imageViewForPlayer2.setImage(null);
                }
                else {
                    map[location[0]][location[1]] = 'x';
                    turn = 1;
                    imageViewForPlayer1.setImage(oImage);
                    imageViewForPlayer2.setImage(null);
                }
                counts++;
            }
        }
    }

    public class myMouseClickerHandler implements EventHandler {
        private int row;
        private int column;
        private Button button;

        myMouseClickerHandler(int row, int column, Button button){
            this.row = row;
            this.column = column;
            this.button = button;
        }

        @Override
        public void handle(Event event){
            if (!win) {
                counts++;
                double size;
                if (level == 3) {
                    size = 80;
                } else if (level == 4) {
                    size = 60;
                } else if (level == 5) {
                    size = 40;
                } else {
                    size = 0;
                }
                if (!bot) {
                    if (turn == 1) {

                        // Change the image
                        ImageView imageView = new ImageView(oImage);
                        imageView.setFitHeight(size);
                        imageView.setFitWidth(size);
                        button.setGraphic(imageView);

                        // Update the 2d map
                        map[row][column] = 'o';
                        // Check if win
                        if (Functions.win(map, 'o', length)) {
                            win = true;
                            labelForTitle.setText(String.format("%s's winning!!!", O));
                            labelForError.setText("Congratulation!");
                        } else {
                            // change to other player turn
                            turn = 2;
                            if (X.equals("Player 1")) {
                                imageViewForPlayer1.setImage(xImage);
                                imageViewForPlayer2.setImage(null);
                            } else {
                                imageViewForPlayer1.setImage(null);
                                imageViewForPlayer2.setImage(xImage);
                            }
                        }
                    }
                    else{
                        // Change the image
                        ImageView imageView = new ImageView(xImage);
                        imageView.setFitHeight(size);
                        imageView.setFitWidth(size);
                        button.setGraphic(imageView);

                        // Update the 2d map
                        map[row][column] = 'x';
                        // Check if win
                        if (Functions.win(map, 'x', length)) {
                            win = true;
                            labelForTitle.setText(String.format("%s's winning!!!", X));
                            labelForError.setText("Congratulation!");
                        } else {
                            // change to other player turn
                            turn = 1;
                            if (O.equals("Player 1")) {
                                imageViewForPlayer1.setImage(oImage);
                                imageViewForPlayer2.setImage(null);
                            } else {
                                imageViewForPlayer1.setImage(null);
                                imageViewForPlayer2.setImage(oImage);
                            }
                        }
                    }
                    button.setOnMouseClicked(null);
                    if (!win && counts == level * level) {
                        labelForTitle.setText("Game is tie");
                        labelForError.setText("Welp!");
                    }
                }
                else {
                    if (turn == 1) {
                        //Change the image
                        ImageView imageView = new ImageView(oImage);
                        imageView.setFitHeight(size);
                        imageView.setFitWidth(size);
                        button.setGraphic(imageView);
                        button.setOnMouseClicked(null);
                        // Update the 2d map
                        map[row][column] = 'o';
                        // Check if win
                        if (Functions.win(map, 'o', length)) {
                            win = true;
                            labelForTitle.setText(String.format("%s's winning!!!", O));
                            labelForError.setText("Congratulation!");
                        } else if (Functions.inGame(map)){
                            counts++;
                            imageViewForPlayer1.setImage(null);
                            imageViewForPlayer2.setImage(xImage);
                            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                            pauseTransition.setOnFinished(event1 -> {
                                int[] location = Functions.randomLocation(map);

                                ImageView imageView1 = new ImageView(xImage);
                                imageView1.setFitWidth(size);
                                imageView1.setFitHeight(size);
                                int row = location[0];
                                int column = location[1];

                                buttonMap[row][column].setGraphic(imageView1);
                                map[row][column] = 'x';

                                buttonMap[row][column].setOnMouseClicked(null);

                                if (Functions.win(map, 'x', length)) {
                                    win = true;
                                    labelForTitle.setText(String.format("%s's winning!!!", X));
                                    labelForError.setText("Congratulation!");
                                } else if (Functions.inGame(map)){
                                    imageViewForPlayer1.setImage(oImage);
                                    imageViewForPlayer2.setImage(null);
                                }
                                if (!win && counts == level * level) {
                                    labelForTitle.setText("Game is tie");
                                    labelForError.setText("Welp!");
                                }
                            });
                            pauseTransition.play();
                        }
                        else{
                            labelForTitle.setText("Game is tie");
                            labelForError.setText("Welp!");
                        }
                    } else {
                        //Change the image
                        ImageView imageView = new ImageView(xImage);
                        imageView.setFitHeight(size);
                        imageView.setFitWidth(size);
                        button.setGraphic(imageView);
                        button.setOnMouseClicked(null);
                        // Update the 2d map
                        map[row][column] = 'x';
                        // Check if win
                        if (Functions.win(map, 'x', length)) {
                            win = true;
                            labelForTitle.setText(String.format("%s's winning!!!", X));
                            labelForError.setText("Congratulation!");
                        } else if (Functions.inGame(map)){
                            counts++;
                            imageViewForPlayer1.setImage(null);
                            imageViewForPlayer2.setImage(oImage);

                            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                            pauseTransition.setOnFinished(event1 -> {
                                int[] location = Functions.randomLocation(map);

                                ImageView imageView1 = new ImageView(oImage);
                                imageView1.setFitWidth(size);
                                imageView1.setFitHeight(size);
                                int row = location[0];
                                int column = location[1];

                                buttonMap[row][column].setGraphic(imageView1);
                                map[row][column] = 'o';

                                buttonMap[row][column].setOnMouseClicked(null);

                                if (Functions.win(map, 'o', length)) {
                                    win = true;
                                    labelForTitle.setText(String.format("%s's winning!!!", O));
                                    labelForError.setText("Congratulation!");
                                } else if (Functions.inGame(map)){
                                    imageViewForPlayer1.setImage(xImage);
                                    imageViewForPlayer2.setImage(null);
                                }
                                if (!win && counts == level * level) {
                                    labelForTitle.setText("Game is tie");
                                    labelForError.setText("Welp!");
                                }
                            });
                            pauseTransition.play();
                        }
                        else{
                            labelForTitle.setText("Game is tie");
                            labelForError.setText("Welp!");
                        }
                    }
                }
            }
        }
    }

    public void end(){
        if (turn == 1){

        }
        labelForTitle.setText("");
    }

    private Image xImage = null;
    private Image oImage = null;

    @FXML
    void initialize(){
        menuBar.setStyle("-fx-background-color: #F6BD60;");
        labelForTitle.setStyle("-fx-text-fill: #84A59D;");
        mainPane.setStyle("-fx-background-color: #F7EDE2;");
        labelForError.setStyle("-fx-background-color: #F7EDE2;-fx-text-fill: #F28482;");

        labelForPlayer1.setStyle("-fx-text-fill: #F28482;");
        labelForPlayer2.setStyle("-fx-text-fill: #F28482;");

        oImage = new Image(getClass().getResource("/assets/oVintage.png").toExternalForm());
        xImage = new Image(getClass().getResource("/assets/xVintage.png").toExternalForm());

        level = 3;
        length = 3;
        gridPaneColor = "#F7EDE2";
        gridpaneMapping();

        labelForError.setText("Hello! To win this level, get 3 identical images in a row/column/slope.");



    }
}
