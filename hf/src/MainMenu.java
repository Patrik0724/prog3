import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    public class NewGameButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            dispose();
            NewGame ng = new NewGame();
        }
    }
    public class ExitButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            System.exit(0);
        }
    }
    public class LoadGameButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try {
                FileInputStream fis = new FileInputStream("map.txt");
                FileInputStream fis2 = new FileInputStream("players.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ObjectInputStream ois2 = new ObjectInputStream(fis2);
                Map m = (Map)ois.readObject();
                ArrayList<Player> players = (ArrayList<Player>)ois2.readObject();
                Game g = new Game(m, players);
                ois.close();
                ois2.close();
                dispose();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public MainMenu(){
        super("Battle Sheep");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jp = new JPanel(new GridLayout(3, 1));
        JButton jb1 = new JButton("New Game");
        JButton jb2 = new JButton("Load Game");
        JButton jb3 = new JButton("Exit");

        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));

        add(jp);
        pack();
        setVisible(true);

        ActionListener al1 = new ExitButtonActionListener();
        jb3.addActionListener(al1);

        ActionListener al2 = new NewGameButtonActionListener();
        jb1.addActionListener(al2);

        ActionListener al3 = new LoadGameButtonListener();
        jb2.addActionListener(al3);
    }
}
