import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InnerBorderBuilder implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        coordinates.clear();
        for(Field f: board){
            if(f.getSheeps() == 0) {
                for (int i = 0; i < 6; ++i) {
                    if (f.getNeighbor(i) == null) {
                        coordinates.add(f.getPosition());
                    }
                }
            }
        }
        for(int i = 0; i < coordinates.size() - 1; ++i)
            for(int j = i + 1; j < coordinates.size(); ++j)
                if(coordinates.get(i).equals(coordinates.get(j))){
                    coordinates.remove(j);
                    --j;
                }
        return true;
    }
}
