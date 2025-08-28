package com.example.proj4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class HelloController {
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
    private Text winner;

    private BufferedReader in;
    private PrintWriter out;
    private String playerSymbol;

    public void setNetworkConnection(BufferedReader in, PrintWriter out, String playerSymbol) {
        this.in = in;
        this.out = out;
        this.playerSymbol = playerSymbol;

        Platform.runLater(this::disableAllButtons);

        new Thread(() -> {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    final String currentResponse = response;
                    if (currentResponse.startsWith("MOVE")) {
                        String[] parts = currentResponse.split(" ");
                        int position = Integer.parseInt(parts[1]);
                        String symbol = parts[2];
                        Platform.runLater(() -> updateBoard(position, symbol));
                    } else if (currentResponse.startsWith("YOUR_TURN")) {
                        Platform.runLater(this::enablePlayerTurn);
                    } else if (currentResponse.equals("DRAW") || currentResponse.endsWith("WINS")) {
                        Platform.runLater(() -> showWinner(currentResponse));
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    public void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        System.out.println("Button clicked: " + clickedButton.getId());

        int position = positionFromButton(clickedButton);
        System.out.println("Sending position: " + position);
        out.println(position);
        clickedButton.setText(playerSymbol);
        clickedButton.setDisable(true);
        disableAllButtons();
    }

    private int positionFromButton(Button button) {
        if (button == button1) return 0;
        if (button == button2) return 1;
        if (button == button3) return 2;
        if (button == button4) return 3;
        if (button == button5) return 4;
        if (button == button6) return 5;
        if (button == button7) return 6;
        if (button == button8) return 7;
        if (button == button9) return 8;
        return -1;
    }

    private Button buttonFromPosition(int position) {
        return switch (position) {
            case 0 -> button1;
            case 1 -> button2;
            case 2 -> button3;
            case 3 -> button4;
            case 4 -> button5;
            case 5 -> button6;
            case 6 -> button7;
            case 7 -> button8;
            case 8 -> button9;
            default -> throw new IllegalArgumentException("Invalid");
        };
    }

    private void updateBoard(int position, String symbol) {
        Button button = buttonFromPosition(position);
        button.setText(symbol);
        button.setDisable(true);
    }

    private void enablePlayerTurn() {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                button.setDisable(false);
            }
        }
        System.out.println("Buttons enabled");
    }

    private void disableAllButtons() {
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (Button button : buttons) {
            button.setDisable(true);
        }
        System.out.println("Buttons disabled");
    }

    private void showWinner(String message) {
        winner.setText(message);
        disableAllButtons();
    }
}
