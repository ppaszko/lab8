package agh.cs.lab8;

import java.util.Objects;

public class Vector2d {
     private int x;
     private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+ other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }
    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
