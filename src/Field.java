import java.awt.*;
import java.util.ArrayList;

public class Field {
    static private int a = 30;
    private ArrayList<Field> neighbors;
    private Point position;
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
                neighbors.add(0, f);
                f.addNeighbor(this, 3);
            }
            else if(position.y - 1 == p.y){
                neighbors.add(3, f);
                f.addNeighbor(this, 0);
            }
        }
        else if(position.y == p.y){
            if(position.x + 1 == p.x){
                neighbors.add(2, f);
                f.addNeighbor(this, 5);
            }
            else if(position.x - 1 == p.x){
                neighbors.add(5, f);
                f.addNeighbor(this, 2);
            }
        }
        else{
            if(position.x + 1 == p.x && position.y + 1 == p.y){
                neighbors.add(4, f);
                f.addNeighbor(this, 1);
            }
            else if(position.x - 1 == p.x && position.y - 1 == p.y){
                neighbors.add(1, f);
                f.addNeighbor(this, 4);
            }
        }
    }
    public void addNeighbor(Field f, int i){
        neighbors.add(i, f);
    }
    public static int getA(){return a;}
}
