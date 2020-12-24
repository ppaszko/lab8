package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MyLittleWorldFollowAnimal extends JPanel {

    public MyLittleWorld myLittleWorld;
    public Lifecycle lifecycle;



    private final JButton stopButton;
    private final JTextField animalID;
    private final JTextField days;
    private final JButton infoButton;
    private final JButton followAnimalButton;

    public MyLittleWorldFollowAnimal(MyLittleWorld myLittleWorld, Lifecycle lifecycle) {
        setLayout(null);
        this.myLittleWorld = myLittleWorld;
        this.lifecycle = lifecycle;
        int width= lifecycle.myLittleWorldSizes.getFollowAnimalWidth();
        int height= lifecycle.myLittleWorldSizes.getFollowAnimalHeight();
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopAction);

        stopButton.setBounds(new Rectangle(0, 0, lifecycle.myLittleWorldSizes.getButtonWidth(), lifecycle.myLittleWorldSizes.getButtonHeight()));
        add(stopButton,BorderLayout.CENTER);

        animalID = new JTextField("");
        animalID.addActionListener(this::stopAction);

        animalID.setBounds(new Rectangle(width/3, 100, width/3, lifecycle.myLittleWorldSizes.getTextInputHeight()));
        animalID.setEnabled(false);
        add(animalID,BorderLayout.CENTER);

        infoButton = new JButton("Show animal info");
        infoButton.addActionListener((this::showInfo));
        infoButton.setBounds(new Rectangle(0, 230, lifecycle.myLittleWorldSizes.getButtonWidth(), lifecycle.myLittleWorldSizes.getButtonHeight()));
        infoButton.setEnabled(false);
        add(infoButton,BorderLayout.CENTER);

        days = new JTextField("");
        days.addActionListener(this::stopAction);

        days.setBounds(new Rectangle(width/3, 180, width/3, lifecycle.myLittleWorldSizes.getTextInputHeight()));
        days.setEnabled(false);
        add(days,BorderLayout.CENTER);

        followAnimalButton = new JButton("Follow animal");
        followAnimalButton.addActionListener((this::followAnimal));

        followAnimalButton.setBounds(new Rectangle(0, 290, lifecycle.myLittleWorldSizes.getButtonWidth(), lifecycle.myLittleWorldSizes.getButtonHeight()));
        followAnimalButton.setEnabled(false);

        add(followAnimalButton,BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width= lifecycle.myLittleWorldSizes.getFollowAnimalWidth();
        int height= lifecycle.myLittleWorldSizes.getFollowAnimalHeight();
        this.setSize(width, height);
        this.setLocation(lifecycle.myLittleWorldSizes.getFollowAnimalX(), lifecycle.myLittleWorldSizes.getFollowAnimalY());


        g.setColor(new Color(158, 132, 241));
        g.fillRect(0, 0, width, height);

        Font drawFont = new Font("Calibri", Font.BOLD, 12);
        g.setFont(drawFont);

        g.setColor(new Color(211, 13, 227));
        g.drawString("Provide Animal ID (Pink number):", 0,80);
        g.drawString("Provide days to follow:", 0,170);
    }

    public void stopAction(ActionEvent e) {
        if (!lifecycle.timer.isRunning()) {
            stopButton.setText("Resume");
            lifecycle.timer.start();

            changeStateButtons(false);
        } else {
            stopButton.setText("Start");
            lifecycle.timer.stop();


            changeStateButtons(true);
        }
    }

    public void showInfo(ActionEvent e) {
            int id = Integer.parseInt(animalID.getText());
            String animalInfo = myLittleWorld.getAnimalInfo(id);
            JOptionPane.showMessageDialog(null, animalInfo);
    }

    public void followAnimal(ActionEvent e) {
        if(lifecycle.ifFollowing) {
            JOptionPane.showMessageDialog(null, "Can't follow 2 animals");
        }
        else{

                int id = Integer.parseInt(animalID.getText());

                Animal toFollow= null;
                for ( Animal animal: myLittleWorld.getAnimals() ){
                    if(animal.getId()==id){
                        toFollow=animal;
                    }
                }
                if(toFollow != null) {

                    int n = Integer.parseInt(days.getText());
                    lifecycle.follow(toFollow, n);
                    stopButton.setText("Stop");
                    lifecycle.timer.start();


                    changeStateButtons(false);

                }
                else {

                    JOptionPane.showMessageDialog(null, "Can't follow dead animal!!!");
                }
            }

        }

        void changeStateButtons(boolean state){
            animalID.setEnabled(state);
            infoButton.setEnabled(state);
            days.setEnabled(state);
            followAnimalButton.setEnabled(state);


        }
    }

