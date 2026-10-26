package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

        @FXML
        private Button button1;

        @FXML
        private Button button2;

        @FXML
        private Button button3;

        @FXML
        private Button button4;

        @FXML
        private Button button5;

        @FXML
        private Button button6;

        @FXML
        private Button button7;

        @FXML
        private Button button8;

        @FXML
        private Button button9;

        @FXML
        private Text winnerText;

        private int playerTurn = 0;

        ArrayList<Button> buttons;

        private static final int[][] LINES = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 4, 8}, {2, 4, 6},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}
        };


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

            buttons.forEach(button ->{
                setupButton(button);
                button.setFocusTraversable(false);
            });
        }

        @FXML
        void restartGame() {
            buttons.forEach(this::resetButton);
            playerTurn = 0;
            winnerText.setText("Tic-Tac-Toe");
        }

        public void resetButton(Button button){
            button.setDisable(false);
            button.setText("");
        }

        private void setupButton(Button button) {
            button.setOnMouseClicked(mouseEvent -> {
                setPlayerSymbol(button);
                button.setDisable(true);
                checkIfGameIsOver();
            });
        }

        public void setPlayerSymbol(Button button){
            if(playerTurn % 2 == 0){
                button.setText("X");
            } else{
                button.setText("O");
            }
            playerTurn++;
        }

        public void checkIfGameIsOver(){
            for (int[] line : LINES) {
                String first = buttons.get(line[0]).getText();
                if (!first.isEmpty()
                        && first.equals(buttons.get(line[1]).getText())
                        && first.equals(buttons.get(line[2]).getText())) {
                    winnerText.setText(first + " won!");
                    endGame();
                    return;
                }
            }

            if (buttons.stream().allMatch(button -> !button.getText().isEmpty())) {
                winnerText.setText("It's a draw!");
                endGame();
            }
        }

        private void endGame() {
            buttons.forEach(button -> button.setDisable(true));
        }
    }