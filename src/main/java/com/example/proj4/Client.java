package com.example.proj4;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
  private static final String HOST = "localhost";
  private static final int PORT = 12345;
  private static Socket socket;
  private static BufferedReader in;
  private static PrintWriter out;
  private static String playerSymbol;

  public static void main(String[] args) {
    try {
      socket = new Socket(HOST, PORT);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      playerSymbol = in.readLine().split(" ")[1];
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    Scene scene = new Scene(loader.load());
    HelloController controller = loader.getController();
    controller.setNetworkConnection(in, out, playerSymbol);

    primaryStage.setTitle("Tic Tac Toe - Player " + playerSymbol);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    socket.close();
  }
}
