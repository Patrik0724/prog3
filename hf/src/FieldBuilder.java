import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FieldBuilder implements Builder{
    public boolean build(ArrayList<Field> board, ArrayList<Point> coordinates, ArrayList<BufferedImage> images){
        Field f1 = board.get(board.size() - 2);
        Field f2 = board.remove(board.size() - 1);
        for(Field f: board){
            if(f.equals(f2)){
                return false;
            }
        }
        board.add(f2);
        f1.addNeighbor(f2);
        f2.addNeighbor(f1);
        int i = f1.getIndex(f2);
        Field f3, f4;
        switch (i){
            case -1:
                if(f1.getPosition().x == f2.getPosition().x + 1){
                    if(f1.getPosition().y == f2.getPosition().y + 1){
                        try {
                            images.add(ImageIO.read(new File("pictures/transparent_field3.png")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        f3 = new Field(new Point(f1.getPosition().x, f2.getPosition().y));
                        f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y));
                    }
                    else{
                        try {
                            images.add(ImageIO.read(new File("pictures/transparent_field2.png")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        f3 = new Field(new Point(f1.getPosition().x, f1.getPosition().y + 1));
                        f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y + 1));
                    }
                }
                else if(f1.getPosition().x == f2.getPosition().x - 1){
                    if(f1.getPosition().y == f2.getPosition().y - 1){
                        try {
                            images.add(ImageIO.read(new File("pictures/transparent_field3.png")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        f3 = new Field(new Point(f1.getPosition().x + 1, f1.getPosition().y));
                        f4 = new Field(new Point(f1.getPosition().x, f1.getPosition().y + 1));
                    }
                    else{
                        try {
                            images.add(ImageIO.read(new File("pictures/transparent_field2.png")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        f3 = new Field(new Point(f1.getPosition().x, f1.getPosition().y - 1));
                        f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y - 1));
                    }
                }
                else{
                    try {
                        images.add(ImageIO.read(new File("pictures/transparent_field.png")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(f1.getPosition().x == f2.getPosition().x + 2){
                        f3 = new Field(new Point(f1.getPosition().x - 1, f1.getPosition().y));
                        f4 = new Field(new Point(f1.getPosition().x - 1, f2.getPosition().y));
                    }
                    else{
                        f3 = new Field(new Point(f1.getPosition().x + 1, f1.getPosition().y));
                        f4 = new Field(new Point(f1.getPosition().x + 1, f2.getPosition().y));
                    }
                }
                break;
            case 0:
                try {
                    images.add(ImageIO.read(new File("pictures/transparent_field.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f3 = new Field(new Point(f1.getPosition().x - 1, f1.getPosition().y));
                f4 = new Field(new Point(f2.getPosition().x + 1, f2.getPosition().y));
                break;
            case 1, 4:
                try {
                    images.add(ImageIO.read(new File("pictures/transparent_field3.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f3 = new Field(new Point(f1.getPosition().x, f2.getPosition().y));
                f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y));
                break;
            case 2:
                try {
                    images.add(ImageIO.read(new File("pictures/transparent_field2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f3 = new Field(new Point(f1.getPosition().x, f1.getPosition().y + 1));
                f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y - 1));
                break;
            case 3:
                try {
                    images.add(ImageIO.read(new File("pictures/transparent_field.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f3 = new Field(new Point(f1.getPosition().x + 1, f1.getPosition().y));
                f4 = new Field(new Point(f2.getPosition().x - 1, f2.getPosition().y));
                break;
            default:
                try {
                    images.add(ImageIO.read(new File("pictures/transparent_field2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f3 = new Field(new Point(f1.getPosition().x, f1.getPosition().y - 1));
                f4 = new Field(new Point(f2.getPosition().x, f1.getPosition().y + 1));
                break;
        }
        for(Field f: board){
            if(f.equals(f3) || f.equals(f4)){
                board.remove(f2);
                f1.deleteNeighbor(f2);
                images.remove(images.size() - 1);
                return false;
            }
        }
        for(Field f: board){
            f.addNeighbor(f2);
            f2.addNeighbor(f);
        }
        board.add(f3);
        board.add(f4);
        for(Field f: board){
            f.addNeighbor(f3);
            f.addNeighbor(f4);
            f3.addNeighbor(f);
            f4.addNeighbor(f);
        }
        return true;
    }
}
