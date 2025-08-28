package com.example.proj4;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadServer {
  public static void main(String[] args) {
    int port = 12345;
    ExecutorService executor = Executors.newFixedThreadPool(2);

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server running");

      while (true) {
        System.out.println("Waiting for client connections");
        Socket player1 = serverSocket.accept();
        System.out.println("Client 1 connected");
        Socket player2 = serverSocket.accept();
        System.out.println("Client 2 connected");

        executor.execute(new GameHandling(player1, player2));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class GameHandling implements Runnable {
  private final Socket player1;
  private final Socket player2;
  private final String[] board = new String[9];
  private String currentPlayer = "X";

  public GameHandling(Socket player1, Socket player2) {
    this.player1 = player1;
    this.player2 = player2;
    for (int i = 0; i < board.length; i++) board[i] = "";
  }

  @Override
  public void run() {
    try (BufferedReader p1In = new BufferedReader(new InputStreamReader(player1.getInputStream()));
         PrintWriter p1Out = new PrintWriter(player1.getOutputStream(), true);
         BufferedReader p2In = new BufferedReader(new InputStreamReader(player2.getInputStream()));
         PrintWriter p2Out = new PrintWriter(player2.getOutputStream(), true)) {

      p1Out.println("START X");
      p2Out.println("START O");

      while (true) {
        Socket currentSocket = currentPlayer.equals("X") ? player1 : player2;
        PrintWriter currentOut = currentPlayer.equals("X") ? p1Out : p2Out;
        BufferedReader currentIn = currentPlayer.equals("X") ? p1In : p2In;

        currentOut.println("YOUR_TURN");
        String move = currentIn.readLine();
        int position = Integer.parseInt(move);

        board[position] = currentPlayer;
        sendMove(position);

        if (checkWinner()) {
          sendMessage(currentPlayer + " WINS");
          break;
        } else if (isBoardFull()) {
          sendMessage("DRAW");
          break;
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendMove(int position) throws IOException {
    PrintWriter p1Out = new PrintWriter(player1.getOutputStream(), true);
    PrintWriter p2Out = new PrintWriter(player2.getOutputStream(), true);

    p1Out.println("MOVE " + position + " " + currentPlayer);
    p2Out.println("MOVE " + position + " " + currentPlayer);
  }

  private void sendMessage(String message) throws IOException {
    PrintWriter p1Out = new PrintWriter(player1.getOutputStream(), true);
    PrintWriter p2Out = new PrintWriter(player2.getOutputStream(), true);

    p1Out.println(message);
    p2Out.println(message);
  }

  private boolean isBoardFull() {
    for (String cell : board) {
      if (cell.isEmpty()) return false;
    }
    return true;
  }

  private boolean checkWinner() {
    int[][] winningPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    for (int[] pattern : winningPatterns) {
      if (!board[pattern[0]].isEmpty() &&
              board[pattern[0]].equals(board[pattern[1]]) &&
              board[pattern[1]].equals(board[pattern[2]])) {
        return true;
      }
    }
    return false;
  }
}
