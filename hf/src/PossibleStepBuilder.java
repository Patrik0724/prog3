import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PossibleStepBuilder implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        coordinates.clear();
        Field f = board.remove(board.size() - 1);
        for(int i = 0; i < 6; ++i){
            if(!f.getPosition().equals(f.getFurthestNeighbor(i).getPosition()))
                coordinates.add(f.getFurthestNeighbor(i).getPosition());
        }
        return coordinates.size() != 0;
    }
}
