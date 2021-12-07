import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private Color col;
    private String name;
    private String picPath;
    private ArrayList<Point> coordinates = new ArrayList<>();
    public Player(Color c, String n){
        col = c;
        name = n;
        if (Color.white.equals(col)) {
            picPath = "pictures/transparent_white.png";
        }
        else {
            picPath = "pictures/transparent_orange.png";
        }
    }
    public String getName(){
        return name;
    }
    public BufferedImage getPic(){
        BufferedImage pic = null;
        try {
             pic = ImageIO.read(new File(picPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pic;
    }
    public boolean canStep(ArrayList<Field> board){
        Builder b = new PossibleStepBuilder();
        for(Point p: coordinates){
            for(int i = 0; i <board.size(); ++i){
                if(board.get(i).getPosition().equals(p) && board.get(i).getSheeps() > 1){
                    board.add(board.get(i));
                    if(b.build(board, new ArrayList<>(), new ArrayList<>()))
                        return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Point> getCoordinates(){
        return coordinates;
    }
}
