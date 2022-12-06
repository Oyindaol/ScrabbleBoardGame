import javax.swing.*;
import java.io.*;

public class SaveLoad {

    private ScrabbleBoardFrame boardFrame;
    private static ScrabbleModel model;

//    public SaveLoad(ScrabbleBoardFrame boardFrame){
//        this.boardFrame = boardFrame;
//
//    }

    public void saveGame(){
        try{
            FileOutputStream fos = new FileOutputStream("ScrabbleGame-saved.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(model);
            oos.flush();
            oos.close();
            System.out.println("ScrabbleGame Saved!\n");

        }
        catch (Exception e){
            System.out.println("Game could not be saved");
        }
    }

    public void loadGame(){
        try{
            FileInputStream fis = new FileInputStream("ScrabbleGame-saved.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            model = (ScrabbleModel) ois.readObject();
            ois.close();
            System.out.println("\nScrabbleGame Loaded!");
        }
        catch (Exception e){
            System.out.println("Game could not be loaded");
        }
    }
}

