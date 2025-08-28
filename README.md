# ❌⭕ JavaFX + Multithreaded Tic-Tac-Toe  
A JavaFX-based Tic-Tac-Toe game enhanced with a multithreaded server–client architecture. Play interactively with synchronized turns, a responsive board, and server-side validation.  

---

## 📖 Gameplay at a Glance
- Two players connect to the same server.  
- Each player takes turns placing **X** or **O** by clicking an empty button on the 3×3 board.  
- Win conditions are checked after each move (rows, columns, diagonals).  
- Buttons are automatically **disabled** when it’s not the player’s turn.  
- The first to align three wins; otherwise, a **draw** is declared when the board is full.  

---

## 🏗️ Project Structure
Project4/
├─ src/
│ ├─ TicTacToeApp.java ← Main JavaFX application
│ ├─ Controller.java ← Handles UI, turns, and win logic
│ ├─ Server.java ← Multithreaded game server (console-based)
│ └─ Client.java ← Game client connecting to server
├─ resources/
│ └─ Board.fxml ← JavaFX layout (buttons grid)
└─ README.md

yaml
Copy code

---

## 🔑 Key Components
| File            | Role |
|-----------------|------|
| **Controller.java** | Connects JavaFX UI to logic, manages turns, disables/enables buttons, checks win/draw states. |
| **Server.java** | Hosts the multiplayer session. Handles multiple client connections via threads. |
| **Client.java** | Connects to server, updates board state based on moves, enforces turn order. |
| **Board.fxml**  | Defines 3×3 button grid with SceneBuilder, linked to the controller. |

---

## 🖥️ Getting Started
1. Open the project in **IntelliJ IDEA** (or another Java IDE).  
2. Ensure **JavaFX SDK** is installed and linked.  
3. Run `Server.java` (console-based).  
4. Start two instances of `Client.java` to connect players.  
5. The **JavaFX board** will appear for each player.  

---

## 🔧 Customization Ideas
- 🎨 **UI Styling**: Modify `Board.fxml` or CSS for themed boards.  
- 🔊 **Sound/Haptics**: Add effects on button clicks or wins.  
- 🤝 **Extended Networking**: Add support for LAN/Internet play.  
- 🧠 **AI Opponent**: Implement Minimax for single-player mode.  
- 📊 **Score Tracking**: Persist scores across multiple rounds.  

---

## 🛣️ Roadmap
- Highlight winning line with color or animation.  
- Add replay / rematch functionality.  
- Expand to custom board sizes (e.g., 4×4).  
- Build GUI front-end for server controls.  

---

## 📜 License
© 2024 Mohamad Syaj. All Rights Reserved.  
This code is provided for **personal or educational use only**. Redistribution, selling, or incorporation into commercial products requires explicit written permission.  

---

## 👥 Author
- **Mohamad Syaj** – mbsyaj@umich.edu  

Enjoy the game – and may your triples always connect!  
