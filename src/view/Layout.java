package view;

import javax.swing.*;

public class Layout extends JFrame {
    public void guiInitilize(int width , int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Rent a Car");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
