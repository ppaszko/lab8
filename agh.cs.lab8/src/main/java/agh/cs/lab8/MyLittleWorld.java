package agh.cs.lab8;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static agh.cs.lab8.DNAcreator.exNihiloDNA;

public class MyLittleWorld extends AbstractMyLittleWorldMap {

    private final Map<Vector2d, Food> plants = new ConcurrentHashMap<>();

    protected int startEnergy;
    protected int foodEnergy;
    protected int moveEnergy;
    public int day = 0;

    protected Vector2d jungleLeftCorner;
    protected Vector2d jungleRightCorner;



    public MyLittleWorld(int width, int height, Vector2d jungleLeftCorner, Vector2d jungleRightCorner,  int nPlants, int foodEnergy,
                         int startEnergy, int nAnimals, int moveEnergy) {
        super(width, height);
        this.startEnergy = startEnergy;
        this.foodEnergy = foodEnergy;
        this.moveEnergy = moveEnergy;

        this.jungleLeftCorner = jungleLeftCorner;
        this.jungleRightCorner = jungleRightCorner;

        this.initPlants(nPlants);
        this.initAnimals(nAnimals);
    }

    public Vector2d getJungleLeftCorner() {
        return jungleLeftCorner;
    }

    public Vector2d getJungleRightCorner() {
        return jungleRightCorner;
    }

    public Map<Vector2d, Food> getPlants() {
        return plants;
    }



    private void initPlants(int n) {
        for (int i=0; i<n; i++) {
            Vector2d v = randomGenerator(this.width, this.height);
            this.plants.put(v, new Food(v, this.foodEnergy));
        }
    }

    private void initAnimals(int n) {
        for (int i=0; i<n; i++){
       Vector2d v= randomGenerator(n, this.width, this.height);
        Animal animal = new Animal(this, this.startEnergy, v, exNihiloDNA());
        this.animals.add(animal);
        this.animalsPositions.put(v, new LinkedList<>());
        this.animalsPositions.get(v).add(animal);}
    }
    private Vector2d randomGenerator( int n, int x, int y){
        //for animals

            int randomX = ThreadLocalRandom.current().nextInt(0, x );
            int randomY = ThreadLocalRandom.current().nextInt(0,y);

            while (this.animalsPositions.containsKey(new Vector2d(randomX, randomY))){
                randomX=ThreadLocalRandom.current().nextInt(0, x );
                randomY=ThreadLocalRandom.current().nextInt(0, y);
            }
            return new Vector2d(randomX,randomY);

    }
    private Vector2d randomGenerator(int x, int y){
//for food
        int randomX = ThreadLocalRandom.current().nextInt(0, x );
        int randomY = ThreadLocalRandom.current().nextInt(0,y);

        while (this.animalsPositions.containsKey(new Vector2d(randomX, randomY)) & this.plants.containsKey(new Vector2d(randomX, randomY))){
            randomX=ThreadLocalRandom.current().nextInt(0, x );
            randomY=ThreadLocalRandom.current().nextInt(0, y);
        }

        return new Vector2d(randomX,randomY);


    }


    @Override
    public boolean isOccupied(Vector2d position) {
        if(super.isOccupied(position)) {
            return true;
        }
        else {
            return this.plants.get(position) != null;
        }
    }

    public void addNewPlants() {


            Vector2d v = randomGenerator( this.width, this.height);
            this.plants.put(v, new Food(v, this.foodEnergy));
            Vector2d inJungle= randomGenerator( jungleRightCorner.getX()- jungleLeftCorner.getX(), jungleRightCorner.getY()- jungleLeftCorner.getY());
            Vector2d vMoved= new Vector2d( inJungle.getX() + jungleLeftCorner.getX(),inJungle.getY() + jungleLeftCorner.getY());
            this.plants.put(vMoved, new Food(vMoved, this.foodEnergy) );

    }


    public void removeDeadAnimals() {
        day += 1;
        for (int i = 0; i < this.animals.size(); i++) {

            if (animals.get(i).getEnergy() <= 0) {
                this.animalsPositions.remove(animals.get(i).getPosition());
            }
            animals.get(i).daySurvived(this.moveEnergy);
        }

        }


    public void moveAnimals() {
        for(Animal animal:this.animals) {
            animal.rotate();
            animal.move();
        }
    }

    public void eating() {

        for (Map.Entry<Vector2d, Food> entry : this.plants.entrySet()){
            List<Animal> animalsAtPlace = this.animalsPositions.get(entry.getKey());
            if(animalsAtPlace != null) {
                if(animalsAtPlace.size() != 0) {
                    animalsAtPlace.sort(Comparator.comparing(Animal::getEnergy));
                    Collections.reverse(animalsAtPlace);

                    int counter=1;
                    int maxEnergy=animalsAtPlace.get(0).getEnergy();
                    for(int i=1; i<animalsAtPlace.size(); i++){
                        if (animalsAtPlace.get(i).getEnergy()==maxEnergy){
                            counter++;
                        }
                    }

                    int energy = this.foodEnergy /counter;
                    for (int j=0; j<counter; j++){

                        animalsAtPlace.get(j).addEnergy(energy);
                    }

                    this.plants.remove(entry.getKey());
                }
            }
        }
    }

    public void birth() {

        Iterator<Map.Entry<Vector2d,List<Animal>>> it = this.animalsPositions.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Vector2d,List<Animal>> entry = it.next();
            List<Animal> animalsAtPlace = this.animalsPositions.get(entry.getKey());


            if(animalsAtPlace != null) {
                if(animalsAtPlace.size() >= 2) {
                    animalsAtPlace.sort(Comparator.comparing(Animal::getEnergy));
                    Collections.reverse(animalsAtPlace);
                    if(animalsAtPlace.get(1).getEnergy() >= this.startEnergy/2) {

                        Animal child=Animal.love(animalsAtPlace.get(0), animalsAtPlace.get(1));
                        this.animals.add(child);
                        this.animalsPositions.put(child.getPosition(), new LinkedList<>());
                        this.animalsPositions.get(child.getPosition()).add(child);}

                    }
                }
            }
        }




    public String getAnimalInfo(int id) {
        StringBuilder report = new StringBuilder("");
        Animal animalToDescribe = null;
        for (Animal animal:this.animals){
            if (animal.getId()==id){
                 animalToDescribe=animal;
            }
        }


        if(animalToDescribe != null) {
            report.append("Id: ").append(id).append("\n");
            report.append("DNA: ").append(animalToDescribe.getDNAString()).append("\n");
            report.append("Age: ").append(animalToDescribe.getAge()).append("\n");
            report.append("Energy: ").append(animalToDescribe.getEnergy()).append("\n");
            report.append("Position: ").append(animalToDescribe.getPosition()).append("\n");
            report.append("Kids: ").append(animalToDescribe.getKids().size()).append("\n");
            report.append("Descendant: ").append(animalToDescribe.countDescendants());
        }
        else {

            for (Animal animal:this.heaven){
                if (animal.getId()==id){
                    animalToDescribe=animal;
                }
            }

            if(animalToDescribe != null) {
                report.append("It is dead!").append("\n");
                report.append("Id: ").append(id).append("\n");
                report.append("DNA: ").append(animalToDescribe.getDNAString()).append("\n");
                report.append("Days survived: ").append(animalToDescribe.getAge()).append("\n");
                report.append("Kids: ").append(animalToDescribe.getKids().size()).append("\n");
                report.append("Descendants: ").append(animalToDescribe.countDescendants());
            }
            else {
                report.append("There is no animal  ").append(id);
            }
        }
        return report.toString();
    }
}
