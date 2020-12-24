package agh.cs.lab8;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMyLittleWorldMap implements IMyLittleWorldMap, IChangePosition {

    protected List<Animal> animals;
    protected List<Animal> heaven;
    protected int width;
    protected int height;
    protected Map<Vector2d, List<Animal>> animalsPositions;

    private int counter =0;

    public int getCounter() {
        return counter;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Animal> getHeaven() {
        return heaven;
    }

    public Map<Vector2d, List<Animal>> getAnimalsPositions() {
        return animalsPositions;
    }

    public AbstractMyLittleWorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        this.animalsPositions = new ConcurrentHashMap<>();


        this.animals = Collections.synchronizedList(new LinkedList<>());
        this.heaven = Collections.synchronizedList(new LinkedList<>());

    }
    @Override
    public Vector2d destination(Vector2d position) {

        Vector2d destination = position;
        destination = isEndMap(destination);
        if (!isOccupied(destination)) {
            return destination;
        }
        return position;


    }
    public boolean canMoveTo(Vector2d position) {

        position = isEndMap(position);
        if (!isOccupied(position)) {
            return true;
        }

        return false;

    }


    public boolean isOccupied(Vector2d position) {
        if(this.animalsPositions.get(position) == null) {
            return false;
        }
        else {
            if(this.animalsPositions.get(position).size() == 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }



    @Override
    public void changePosition(Vector2d oldPosition, Vector2d newPosition, Animal animal) {

        if (this.animalsPositions.get(oldPosition)!=null){
            this.animalsPositions.get(oldPosition).remove(animal);
        }
        List<Animal> elements = this.animalsPositions.get(newPosition);
        if(elements==null) {
            this.animalsPositions.put(newPosition, new LinkedList<>());
            this.animalsPositions.get(newPosition).add(animal);
        }
        else {
            elements.add(animal);
        }
    }

    @Override
    public void dead(Animal animal) {
        this.animals.remove(animal);
        this.animalsPositions.remove(animal.getPosition());
        this.heaven.add(animal);

    }

    public Vector2d isEndMap(Vector2d position){
        if (position.getX() < 0) position.setX(this.getWidth()-1);
        if (position.getX() > (this.getWidth()-1)) position.setX(0);
        if (position.getY() < 0) position.setY(this.getHeight()-1);
        if (position.getY() > (this.getHeight()-1)) position.setY(0);
        return position;

    }

    public String mostCommonDNA() {
        if (animals.size() == 0) {
            return "";
        }

            List<String> genotypes= new ArrayList<>();
            for (Animal animal : animals) {

                genotypes.add(animal.getDNAString());
            }
            Collections.sort(genotypes);
            String mostCommon = genotypes.get(0);
            String last = null;
            int mostCount = 0;
            int lastCount = 0;
            for (String x : genotypes) {
                if (x.equals(last)) {
                    lastCount++;
                } else if (lastCount > mostCount) {
                    mostCount = lastCount;
                    mostCommon = last;
                }
                last = x;
            }
        return mostCommon;

        }


    public void updateCounter() {
        this.counter += 1;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

