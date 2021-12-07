import java.awt.*;
import java.io.Serializable;

public class Field implements Serializable {
    private Field[] neighbors = new Field[6];
    private Point position;
    private int sheeps = 0;
    public Field(Point pos){
        position = pos;
    }
    public Point getPosition(){
        return position;
    }
    public void addNeighbor(Field f){
        Point p = f.getPosition();
        if(position.x == p.x){
            if(position.y + 1 == p.y){
                neighbors[3] = f;
            }
            else if(position.y - 1 == p.y){
                neighbors[0] = f;
            }
        }
        else if(position.y == p.y){
            if(position.x + 1 == p.x){
                neighbors[2] = f;
            }
            else if(position.x - 1 == p.x){
                neighbors[5] = f;
            }
        }
        else{
            if(position.x + 1 == p.x && position.y - 1 == p.y){
                neighbors[1] = f;
            }
            else if(position.x - 1 == p.x && position.y + 1 == p.y){
                neighbors[4] = f;
            }
        }
    }
    public int getIndex(Field f){
        for(int i = 0; i < 6; ++i)
            if(f.equals(neighbors[i]))
                return i;
        return -1;
    }
    public Field getNeighbor(int i){
        return neighbors[i];
    }
    public boolean equals(Field f){
        if(f != null)
            return position.equals(f.position);
        return false;
    }
    public Point getNeighborsPosition(int i){
        switch(i){
            case 0: return new Point(position.x, position.y - 1);
            case 1: return new Point(position.x + 1, position.y - 1);
            case 2: return new Point(position.x + 1, position.y);
            case 3: return new Point(position.x, position.y + 1);
            case 4: return new Point(position.x - 1, position.y + 1);
            case 5: return new Point(position.x - 1, position.y);
            default: return null;
        }
    }
    public void deleteNeighbor(Field f){
        for(int i = 0; i < 6; ++i)
            if(neighbors[i] != null)
                if(neighbors[i].equals(f))
                    neighbors[i] = null;
    }
    public void addSheeps(int i){
        sheeps += i;
    }
    public int getSheeps(){
        return sheeps;
    }
    public Field getFurthestNeighbor(int i){
        Field f = getNeighbor(i);
        if(f == null || f.getSheeps() > 0)
            return this;
        else
            return f.getFurthestNeighbor(i);
    }
}
