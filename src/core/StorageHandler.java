package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorageHandler {
    private String path;
    private File file;

    public StorageHandler(String p) {
        path = p;
        file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Nothing found, first leaderboard entry! Structuring file!");
            saveLeaderboardData(new Object[0][0]);
        }
    }

    public Object[][] getLeaderboardData() {
        Object[][] data = null;
        ObjectInputStream inputStream;
        
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            data = (Object[][]) inputStream.readObject();
            System.out.println("Fetched leaderboard!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }

    public void saveLeaderboardData(Object[][] data) {
        ObjectOutputStream outputStream;
        
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(data);
            System.out.println("Saved leaderboard!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
