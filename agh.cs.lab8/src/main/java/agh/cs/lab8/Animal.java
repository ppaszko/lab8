package agh.cs.lab8;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {

    private final LinkedList<Animal> kids = new LinkedList<>();
    private final AbstractMyLittleWorldMap map;
    private final List<IChangePosition> observers = new LinkedList<>();
    private final List<Integer> DNA;

    private int energy;
    private int age;
    private final int id;

    private MapDirection orientation;
    private Vector2d position;



    public int getId() {
        return id;
    }
    public int getEnergy() {
        return energy;
    }
    public int getAge() {
        return age;
    }

    public List<Animal> getKids() {
        return kids;
    }
    public Vector2d getPosition() {
        return position;
    }

    public String getDNAString() {
        return DNA.toString();
    }


    Animal(AbstractMyLittleWorldMap map, int energy, Vector2d initialPosition, List<Integer> DNA) {

        this.map = map;
        this.observers.add(map);
        this.DNA=DNA;
        this.energy=energy;
        this.orientation = MapDirection.randomDirections().get(0);
        this.position = initialPosition;
        this.id = map.getCounter();
        map.updateCounter();

    }

    public void move() {
        Vector2d oldPosition = this.position;
        Vector2d future_position = map.destination(this.position.add(this.orientation.toUnitVector()));
        this.position = future_position;
        this.positionChanged(oldPosition, future_position);
    }

    public void daySurvived(int moveEnergy){
        this.age += 1;
        this.energy -=moveEnergy;
        if(this.energy <= 0) {
            dead();
        }
    }



    public void rotate(){
        int rand_idx = ThreadLocalRandom.current().nextInt(0,32);
        this.orientation = this.orientation.next(this.DNA.get(rand_idx));
    }

    public static Animal love(Animal animal1, Animal animal2) {

        int energy = animal1.energy/4+animal2.energy/4;
        animal1.energy -= animal1.energy/4;
        animal2.energy -= animal2.energy/4;
        Animal child = new Animal(animal1.map, energy, placeToBorn(animal1), DNAcreator.fromParentsDNA(animal1.DNA, animal2.DNA));
        animal1.kids.add(child);
        animal2.kids.add(child);
        return child;
    }
    public static Vector2d placeToBorn(Animal father){
        Vector2d endPosition = father.getPosition();
        for(MapDirection d:MapDirection.randomDirections()) {
             Vector2d hypo = endPosition.add(d.toUnitVector());
            if (!father.map.isOccupied(hypo)) {
                endPosition = hypo;
                break;
            }
        }
        if(endPosition == father.getPosition()) {
            endPosition = endPosition.add(MapDirection.randomDirections().get(0).toUnitVector());
        }

        return endPosition;
    }



    public void addEnergy(int energy) {
        this.energy += energy;
    }



    public int countDescendants() {

        int counter=0;

        for(Animal kid: this.kids) {

            if (kid.getKids().size() > 0) {
                counter+=kid.countDescendants();
            }
            else {
                counter += 1;
            }
        }

        return counter;

    }


    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IChangePosition o : this.observers) {
            o.changePosition(oldPosition, newPosition, this);
        }
    }

    void dead() {
        for (IChangePosition o:this.observers) {
            o.dead(this);

        }
    }


    void addObserver(IChangePosition observer){
        this.observers.add(observer);
    }
    void removeObserver(IChangePosition observer){
        this.observers.remove(observer);
    }
}

