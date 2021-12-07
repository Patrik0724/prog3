import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Result extends JFrame {
    private Player winner;
    public class OkButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public Result(Player p){
        super();
        winner = p;
        JPanel jp = new JPanel();
        JTextField jtf = new JTextField(20);
        JButton jb1 = new JButton("Ok");
        ActionListener al1 = new OkButtonListener();
        jb1.addActionListener(al1);
        JPanel jp2 = new JPanel(new GridLayout(1, 2));
        jp2.add(jb1);
        jp2.setBorder(new EmptyBorder(new Insets(100, 50, 10, 50)));
        jtf.setText("Congratulations " + winner.getName());
        jtf.setEditable(false);
        jp.add(jtf);
        add(jp, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void paint(Graphics g) {
        super.paintComponents(g);
        g.drawImage(winner.getPic(), getSize().width / 2 - winner.getPic().getWidth() / 2, getSize().height / 2 - winner.getPic().getHeight() / 2, this);
    }
}
