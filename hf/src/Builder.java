import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public interface Builder extends Serializable {
    boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images);
}
