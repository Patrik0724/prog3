import java.awt.*;
import java.awt.image.BufferedImage;;
import java.util.ArrayList;

public class BorderBuilder implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        coordinates.clear();
        for(Field f: board){
            for(int i = 0; i < 6; ++i){
                if(f.getNeighbor(i) == null){
                    switch (i){
                        case 0: coordinates.add(new Point(f.getPosition().x, f.getPosition().y - 1)); break;
                        case 1: coordinates.add(new Point(f.getPosition().x + 1, f.getPosition().y - 1)); break;
                        case 2: coordinates.add(new Point(f.getPosition().x + 1, f.getPosition().y)); break;
                        case 3: coordinates.add(new Point(f.getPosition().x, f.getPosition().y + 1)); break;
                        case 4: coordinates.add(new Point(f.getPosition().x - 1, f.getPosition().y + 1)); break;
                        case 5: coordinates.add(new Point(f.getPosition().x - 1, f.getPosition().y)); break;
                    }
                }
            }
        }
        for(int i = 0; i < coordinates.size() - 1; ++i){
            for(int j = i + 1; j < coordinates.size(); ++j)
                if(coordinates.get(i).equals(coordinates.get(j)))
                    coordinates.remove(j);
        }
        Builder b = new PossibleNewFieldBuilder();
        ArrayList<Point> temp = new ArrayList<>();
        for(int i = 0; i < coordinates.size(); ++i){
            board.add(new Field(coordinates.get(i)));
            if(b.build(board, temp, images)){
                Field f1 = board.remove(board.size() - 1);
                for(Field f: board){
                    f.deleteNeighbor(f1);
                }
            }
            else{
                coordinates.remove(i);
                --i;
            }
        }
        return true;
    }
}
