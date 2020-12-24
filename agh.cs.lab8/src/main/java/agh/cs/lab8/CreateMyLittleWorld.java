package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;


public class CreateMyLittleWorld {
    public JFrame menuFrame;

    public CreateMyLittleWorld() {
        menuFrame = new JFrame();
        menuFrame.setSize(new Dimension(300,100));
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

    }
    public void startSimulation(Integer[] initialConditions){
        menuFrame.add(new MyLittleWorldStartWindow(initialConditions));
        menuFrame.setVisible(true);
    }
}