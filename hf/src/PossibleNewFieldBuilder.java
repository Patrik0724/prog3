import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PossibleNewFieldBuilder implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        coordinates.clear();
        Field f1 = board.remove(board.size() - 1);
        for(Field f: board){
            if(f.equals(f1)){
                return false;
            }
        }
        board.add(f1);
        Point p = f1.getPosition();
        ArrayList<Field> temp = new ArrayList<>();
        for(int i = 0; i < 6; ++i){
            temp.add(i, new Field(f1.getNeighborsPosition(i)));
        }
        temp.add(6, new Field(new Point(p.x - 1, p.y - 1)));
        temp.add(7, new Field(new Point(p.x + 1, p.y - 2)));
        temp.add(8, new Field(new Point(p.x + 2, p.y - 1)));
        temp.add(9, new Field(new Point(p.x + 1, p.y + 1)));
        temp.add(10, new Field(new Point(p.x - 1, p.y + 2)));
        temp.add(11, new Field(new Point(p.x - 2, p.y + 1)));
        for(Field f: board){
            for(int i = 0; i < temp.size(); ++i){
                if(temp.get(i).equals(f)){
                    temp.remove(i);
                    --i;
                }
            }
        }
        Builder b = new FieldBuilder();
        for(int i = 0; i < temp.size(); ++i){
            board.add(temp.get(i));
            if(b.build(board, coordinates, images)){
                Field f2 = board.remove(board.size() - 3);
                Field f3 = board.remove(board.size() - 2);
                Field f4 = board.remove(board.size() - 1);
                images.remove(images.size() - 1);
                for(Field f: board){
                    f.deleteNeighbor(f2);
                    f.deleteNeighbor(f3);
                    f.deleteNeighbor(f4);
                }
            }
            else{
                temp.remove(i);
                --i;
            }
        }
        for(Field f: temp){
            coordinates.add(f.getPosition());
        }
        if(coordinates.size() == 0){
            board.remove(f1);
            return false;
        }
        for(Field f: board){
            f.addNeighbor(f1);
            f1.addNeighbor(f);
        }
        return true;
    }
}
