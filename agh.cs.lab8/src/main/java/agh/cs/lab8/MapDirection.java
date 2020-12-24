package agh.cs.lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public enum MapDirection {NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static List<MapDirection> allDirections = List.of(values());
    public String toString() {
        switch(this) {
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case WEST:
                return "Zachód";
            case NORTHEAST:
                return  "Pólnocny wschód";
            case EAST:
                return "Wschód";
            case SOUTHEAST:

                return  "Południowy wschód";
            case SOUTHWEST:
                return  "Południowy zachód";
            case NORTHWEST:
                return  "Pólnocny zachód";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }



    }

    public MapDirection  next() {
        switch (this) {
            case NORTH:
                return NORTHEAST;
            case NORTHEAST:
                return  EAST;
            case EAST:
                return SOUTHEAST;
            case SOUTHEAST:
                return  SOUTH;
            case SOUTH:
                return SOUTHWEST;
            case SOUTHWEST:
                return WEST;
            case WEST:
                return NORTHWEST;
            case NORTHWEST:
                return NORTH;

            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }

    }


    public MapDirection next(int n) {
        n = n%8;
        MapDirection direction = this;
        for(int i=0;i<n;i++) {
            direction = direction.next();
        }
        return direction;
    }



        public Vector2d toUnitVector() {

            switch (this) {
                case NORTH:
                    return new Vector2d(0,1);
                case SOUTH:
                    return  new Vector2d(0,-1);
                case WEST:
                    return  new Vector2d(-1,0);
                case NORTHEAST:
                    return new Vector2d(1,1);
                case EAST:
                    return new Vector2d(1,0);
                case SOUTHEAST:
                    return new Vector2d(1,-1);
                case SOUTHWEST:
                    return new Vector2d(-1,-1);
                case NORTHWEST:
                    return new Vector2d(-1,1);
                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }



    public static List<MapDirection> randomDirections()  {
        List<MapDirection> randomDirList = new ArrayList<>(allDirections);
        Collections.shuffle(randomDirList);
        return randomDirList;
    }
}

