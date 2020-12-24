package agh.cs.lab8;


import java.io.IOException;

public class World {

    public static void main(String[] args) {

        try {
            //Integer[] initialConditions = JSONLoader.defaultValues();
            Integer[] initialConditions = JSONLoader.JSONLoad("/home/paszko/IdeaProjects/agh.cs.lab8/src/main/java/agh/cs/lab8/params.json");


            CreateMyLittleWorld createMyLittleWorld = new CreateMyLittleWorld();
            createMyLittleWorld.startSimulation(initialConditions);

        } catch (IllegalArgumentException | IOException ex) {
            System.out.println(ex);
        }
    }

}
