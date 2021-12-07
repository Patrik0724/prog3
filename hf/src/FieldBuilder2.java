import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FieldBuilder2 implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        Builder b = new FieldBuilder();
        ArrayList<Field> temp = new ArrayList<>();
        for(int i = 0; i < board.size(); i += 4){
            Field f1 = new Field(board.get(i).getPosition());
            Field f2 = new Field(board.get(i + 1).getPosition());
            temp.add(f1);
            temp.add(f2);
            b.build(temp, coordinates, images);
            temp.clear();
        }
        return true;
    }
}
