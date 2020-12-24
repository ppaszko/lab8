package agh.cs.lab8;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONLoader {

    public static  Integer[] defaultValues() throws IOException {

        Integer[] jungleBorders = calculateJungleBorders(20, 20,
                0.01);

        return new Integer[]{
                20, //width
                20, //height
                jungleBorders[0], jungleBorders[1], jungleBorders[2], jungleBorders[3], //borders
                10, //starting plants
                30, //plants nergy
                50, // start animals energy
                10, // animals
                1, // energy to move
                10 //days to report
        };
    }

    public static Integer[] JSONLoad(String pathToJSON) throws IOException {

        String fileString = Files.readString(Paths.get(pathToJSON));
        JSONObject data = new JSONObject(fileString);

        Integer[] jungleBorders = calculateJungleBorders(data.getInt("width"),data.getInt("height"), data.getDouble("jungle_ratio"));

         Integer[] initialConditions= {
                data.getInt("width"),
                data.getInt("height"),
                jungleBorders[0], jungleBorders[1], jungleBorders[2], jungleBorders[3],
                data.getInt("number_of_plants"),
                data.getInt("food_energy"),
                data.getInt("start_energy"),
                data.getInt("initial_animals"),
                data.getInt("move_energy"),
                data.getInt("days_to_file_saving")};

                return initialConditions;
        };






        public static Integer[] calculateJungleBorders(int w, int h, double ratio) {

            int leftCornerX = (int)((w - w*Math.sqrt(ratio))/2);
            int leftCornerY = (int)((h - h*Math.sqrt(ratio))/2);
            int RightCornerX = (int)((w + w*Math.sqrt(ratio))/2);
            int RightCornerY = (int)((h + h*Math.sqrt(ratio))/2);

            return new Integer[]{leftCornerX, leftCornerY, RightCornerX, RightCornerY};
        }

    }

