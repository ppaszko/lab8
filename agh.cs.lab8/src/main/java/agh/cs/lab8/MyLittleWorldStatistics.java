package agh.cs.lab8;



import javax.swing.*;
import java.awt.*;
import java.io.*;


public class MyLittleWorldStatistics extends JPanel {

    public MyLittleWorld myLittleWorld;
    public Lifecycle lifecycle;

    private final int n;
    private StringBuilder storyOfLife = new StringBuilder("");

    public MyLittleWorldStatistics(MyLittleWorld myLittleWorld, Lifecycle lifecycle, int n) {
        this.myLittleWorld = myLittleWorld;
        this.lifecycle = lifecycle;

        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(lifecycle.myLittleWorldSizes.getStatisticsWidth(), lifecycle.myLittleWorldSizes.getStatisticsHeight());

        this.setLocation(lifecycle.myLittleWorldSizes.getStatisticsX(), lifecycle.myLittleWorldSizes.getStatisticsY());

        g.setColor(new Color(158, 132, 241));
        g.fillRect(0, 0, lifecycle.myLittleWorldSizes.getStatisticsWidth(), lifecycle.myLittleWorldSizes.getStatisticsHeight());
        g.setColor(new Color(211, 13, 227));

        g.setFont(new Font("Calibri", Font.BOLD, 22));


        String days = "Day " + myLittleWorld.day;

        g.drawString(days, 0, 20);
        storyOfLife.append(days).append('\n');



        g.setFont(new Font("Calibri", Font.BOLD, 18));


        String animals = "Living animals: " + myLittleWorld.getAnimals().size();
        g.drawString(animals, 0, 50);
        storyOfLife.append("Living animals: ").append(myLittleWorld.getAnimals().size()).append('\n');


        String corpses = "Animals in heaven: " + myLittleWorld.getHeaven().size();
        g.drawString(corpses, 0, 70);
        storyOfLife.append("Animals in heaven: ").append(myLittleWorld.getHeaven().size()).append('\n');


        String plants = "Plants: " + myLittleWorld.getPlants().size();
        g.drawString(plants, 0, 90);
        storyOfLife.append("Plants: ").append(myLittleWorld.getPlants().size()).append('\n');

        // mean animals in heaven Age
        int counter = 0;
        for (Animal corp : myLittleWorld.getHeaven()) {
            counter += corp.getAge();
        }
        int meanAge = 0;
        if (myLittleWorld.getHeaven().size() > 0) {
            meanAge = counter / myLittleWorld.getHeaven().size();
        }


        String corpsAge = "Mean death age: " + meanAge;
        g.drawString(corpsAge, 0, 110);
        storyOfLife.append("Mean death age: ").append(meanAge).append('\n');


//mean energy

        int counter2 = 0;
        for (Animal animal : myLittleWorld.getAnimals()) {
            counter2 += animal.getEnergy();
        }
        float meanEnergy = 0;
        if (myLittleWorld.getAnimals().size() > 0) {
            meanEnergy = counter2 / (float) myLittleWorld.getAnimals().size();
        }


        String Energy = "Mean energy: " + meanEnergy;
        g.drawString(Energy, 0, 130);
        storyOfLife.append(Energy).append('\n');


        //mean kids

        int counter3 = 0;
        for (Animal animal : myLittleWorld.getAnimals()) {
            counter3 += animal.getKids().size();
        }



        float meanKids = 0;
        if (myLittleWorld.getAnimals().size() > 0) {
            meanKids = counter3 / (float) myLittleWorld.getAnimals().size();
        }


        String kids = "Mean kids: " + meanKids;
        g.drawString(kids, 0, 150);
        storyOfLife.append(kids).append('\n');


        String genotype = "Most common genotype: ";
        g.drawString(genotype, 0, 170);
        storyOfLife.append("Most common genotype: ").append('\n');



        g.setFont(new Font("Calibri", Font.BOLD, 10));

        String dna = myLittleWorld.mostCommonDNA();
        storyOfLife.append(dna).append('\n').append('\n');
        if (dna!=null){
            if (dna.length() > 0) {
                g.drawString("" + dna.substring(1, 42), 10, 190);
                g.drawString("" + dna.substring(42, 95), 10, 205);
            } else {

                g.setFont(new Font("Calibri", Font.BOLD, 22));
                g.drawString("NO ONE SURVIVED!!!", 0, 190);
            }
        }

        if (n == myLittleWorld.day) {

            try {

                PrintWriter file = new PrintWriter("/home/paszko/IdeaProjects/agh.cs.lab8/src/main/java/agh/cs/lab8/status.txt");

                file.write(storyOfLife.toString());
                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    }
}

