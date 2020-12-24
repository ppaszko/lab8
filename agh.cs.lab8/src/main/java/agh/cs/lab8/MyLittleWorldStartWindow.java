package agh.cs.lab8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyLittleWorldStartWindow extends JPanel implements ActionListener {

    private final Integer[] initialConditions;
    private int counter=1;
    JButton startButton;
    JButton startButton2;


    @Override
    public void actionPerformed(ActionEvent e) {
        createMyLittleWorld();
    }

    public MyLittleWorldStartWindow(Integer[] initialConditions) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        this.initialConditions = initialConditions;

        startButton = new JButton("Give them life!");
        startButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel);
        startButton2 = new JButton("Start race for survive!");
        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMyLittleWorld();
                createMyLittleWorld();
            }
        });
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(startButton2);
        add(buttonPanel2);
    }


    private void createMyLittleWorld() {
        MyLittleWorld myLittleWorld = new MyLittleWorld(initialConditions[0], initialConditions[1],
                new Vector2d(initialConditions[2], initialConditions[3]),
                new Vector2d(initialConditions[4], initialConditions[5]),
                initialConditions[6], initialConditions[7], initialConditions[8], initialConditions[9], initialConditions[10]
        );

        Lifecycle lifecycle = new Lifecycle(myLittleWorld, initialConditions[11]);
        counter += 1;
        lifecycle.start();
    }
}