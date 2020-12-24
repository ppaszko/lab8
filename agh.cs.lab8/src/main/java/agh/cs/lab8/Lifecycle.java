package agh.cs.lab8;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lifecycle implements ActionListener {
    public MyLittleWorld myLittleWorld;
    public JFrame frame;

    public MyLittleWorldPlayground myLittleWorldPlayground;
    public MyLittleWorldStatistics myLittleWorldStatistics;
    public MyLittleWorldFollowAnimal myLittleWorldFollowAnimal;
    public MyLittleWorldSizes myLittleWorldSizes;

    public Timer timer;
    private AnimalInfo animalInfo;
    private Animal followedAnimal;

    public boolean ifFollowing =false;


    public Lifecycle(MyLittleWorld myLittleWorld, int days_to_file_saving) {
        this.myLittleWorld = myLittleWorld;
        this.myLittleWorldSizes= new MyLittleWorldSizes(800,600);
        timer = new Timer(200, this);
        frame = new JFrame("MyLittleWorld");
        frame.setSize(myLittleWorldSizes.getFrameWidth(),myLittleWorldSizes.getFrameHeight());

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.setVisible(true);


        myLittleWorldPlayground = new MyLittleWorldPlayground(myLittleWorld, this);
        myLittleWorldPlayground.setSize(new Dimension(1, 1));
        frame.add(myLittleWorldPlayground);


        myLittleWorldStatistics = new MyLittleWorldStatistics(myLittleWorld, this, days_to_file_saving);
        myLittleWorldStatistics.setSize(new Dimension(1, 1));
        frame.add(myLittleWorldStatistics);


        myLittleWorldFollowAnimal = new MyLittleWorldFollowAnimal(myLittleWorld, this);
        myLittleWorldFollowAnimal.setSize(new Dimension(1, 1));
        frame.add(myLittleWorldFollowAnimal);

    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myLittleWorldPlayground.repaint();
        myLittleWorldStatistics.repaint();
        myLittleWorldFollowAnimal.repaint();


        myLittleWorld.removeDeadAnimals();
        myLittleWorld.moveAnimals();
        myLittleWorld.eating();
        myLittleWorld.birth();
        myLittleWorld.addNewPlants();


        if(ifFollowing) {
            if(myLittleWorld.day >= animalInfo.day + animalInfo.days) {
                String message = changeInAnimal();
                JOptionPane.showMessageDialog(null, message);
                ifFollowing = false;
            }
        }
    }

    private String changeInAnimal() {

        StringBuilder changeInfo = new StringBuilder("");
        changeInfo.append("Id: ").append(followedAnimal.getId()).append("\n");
        changeInfo.append("Kids in given period: ").append(followedAnimal.getKids().size() - animalInfo.kids).append("\n");
        changeInfo.append("Descendants in given period: ").append(followedAnimal.countDescendants() -
                animalInfo.descendants);

        if(followedAnimal.getAge()< myLittleWorld.day - animalInfo.age){
            changeInfo.append("\n");
            changeInfo.append("Died in day: ").append(animalInfo.day - animalInfo.age+followedAnimal.getAge())
                    .append("\n");
            changeInfo.append("Dead in the age of: ").append(followedAnimal.getAge());
        }
        return changeInfo.toString();
    }

    public void follow(Animal animal, int n) {
        ifFollowing = true;
        animalInfo = new AnimalInfo(animal.getId(),animal.getAge(), myLittleWorld.day, animal.getKids().size(), animal.countDescendants(), n);
        followedAnimal = animal;
    }

}