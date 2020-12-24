package agh.cs.lab8;



import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MyLittleWorldPlayground extends JPanel {

    public MyLittleWorld myLittleWorld;
    public Lifecycle lifecycle;

    public MyLittleWorldPlayground(MyLittleWorld myLittleWorld, Lifecycle lifecycle) {
        this.myLittleWorld = myLittleWorld;
        this.lifecycle = lifecycle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setLocation(lifecycle.myLittleWorldSizes.getPlaygroundX(), lifecycle.myLittleWorldSizes.getPlaygroundY());
        this.setSize(lifecycle.myLittleWorldSizes.getPlaygroundWidth(), lifecycle.myLittleWorldSizes.getPlaygroundHeight());


        int width = lifecycle.myLittleWorldSizes.getPlaygroundWidth();
        int widthScale = width / myLittleWorld.getWidth();

        int height = lifecycle.myLittleWorldSizes.getPlaygroundHeight();
        int heightScale = height / myLittleWorld.getHeight();

        g.setColor(new Color(154, 146, 65));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(13, 88, 16));


        g.fillRect(myLittleWorld.getJungleLeftCorner().getX() * widthScale,
                myLittleWorld.getJungleLeftCorner().getY() * heightScale,
                (myLittleWorld.getJungleRightCorner().getX() - myLittleWorld.getJungleLeftCorner().getX()) * widthScale,
                (myLittleWorld.getJungleRightCorner().getY() - myLittleWorld.getJungleLeftCorner().getY()) * heightScale);

        for (Map.Entry<Vector2d, Food> entry : myLittleWorld.getPlants().entrySet()){


            g.setColor(new Color(125, 241, 6));
            int y = entry.getKey().getY() * heightScale;
            int x = entry.getKey().getX() * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }

        g.setFont(new Font("Arial", Font.BOLD, 22));
        for(List<Animal> listOfAnimals: myLittleWorld.getAnimalsPositions().values()) {



            for (Animal animal : listOfAnimals) {
                g.setColor(new Color(33, 17, 92));
                int y = animal.getPosition().getY() * heightScale;
                int x = animal.getPosition().getX() * widthScale;
                g.fillRect(x, y, widthScale, heightScale);


                g.setColor(new Color(215, 118, 217));
                g.setFont(new Font("Calibri", Font.BOLD, 18));

                g.drawString(Integer.toString(animal.getId()), x + widthScale/3, y + heightScale/2);

                g.setColor(new Color(255, 255, 255, 255));
                g.setFont(new Font("Calibri", Font.BOLD, 10));

                g.drawString(Integer.toString(animal.getEnergy()), x + widthScale/3, y + heightScale);
                }
            }
        }

}
