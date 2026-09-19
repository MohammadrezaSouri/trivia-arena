package util;

import model.GameSession;

import java.io.*;

public class GameSaveManager {
    public static void saveGame(GameSession session, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(session);
        }
    }
    public static GameSession loadGame(String filePath) throws IOException , ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameSession) in.readObject();
        }
    }
}
