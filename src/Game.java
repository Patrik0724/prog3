import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel{
    private Polygon origo;
    private ArrayList<Field> board;
    private ArrayList<BufferedImage> images;
    private ArrayList<Point> coordinates;
    private Player player1, player2;
    private Dimension size;
    public class MouseListener extends MouseAdapter{
        private Point prevPos = new Point(0,0);
        public void mouseClicked(MouseEvent me){
            if(coordinates == null || images == null){
                images = new ArrayList<>();
                coordinates = new ArrayList<>();
                board = new ArrayList<>();
                board.add(new Field(new Point(0,0)));
            }
            int x = me.getX(); //* 10 / size.width - 5;
            int y = me.getY(); //* 10 / size.height - 5;
            board.add(new Field(new Point(x, y)));
            coordinates.add(new Point( x - 30,  y - 50));
            try {
                images.add(ImageIO.read(new File("transparent_field.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
    public Game(Player p1, Player p2){
        player1 = p1; player2 = p2;
        initComponents();
    }
    public void initComponents(){
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setVisible(true);
        size = jf.getSize();
        Point o = new Point(size.width / 2, size.height / 2);
        int[] xs = {o.x - 30, o.x - 15, o.x + 15, o.x + 30, o.x + 15, o.x - 15};
        int [] ys = {o.y, o.y + 26, o.y + 26, o.y, o.y - 26, o.y - 26};
        origo = new Polygon(xs, ys, 6);
        repaint();
        MouseListener ml = new MouseListener();
        addMouseListener(ml);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(images != null){
            for(int i = 0; i < images.size(); ++i){
                g.drawImage(images.get(i), coordinates.get(i).x, coordinates.get(i).y, this);
            }
        }
        else{
            g.setColor(Color.gray);
            g.drawPolygon(origo);
            g.fillPolygon(origo);
        }

    }
}
