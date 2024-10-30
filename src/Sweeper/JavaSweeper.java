package Saper;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JButton Rest_button= new JButton("Repeat");
    private final int COLS = 14;
    private final int ROWS = 14;
    private final int BOMBS = 30;
    private final int IMAGE_SIZE = 100;
    
    public static void main(String[] args) {
        new JavaSweeper();
    }
    private JavaSweeper () {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }
    private void initPanel(){
        panel = new JPanel()
        {
            @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    for (Coord coord:Ranges.getAllCoords()){
                        g.drawImage((Image)game.getBox(coord).image, coord.x*IMAGE_SIZE, coord.y*IMAGE_SIZE,
                                100, 100, this);
                    }
            }
        };


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x=e.getX()/IMAGE_SIZE;
                int y=e.getY()/IMAGE_SIZE;
                Coord coord=new Coord(x, y);
                if(e.getButton()==MouseEvent.BUTTON1)
                    game.pressRightButton(coord);
                if(e.getButton()==MouseEvent.BUTTON3)
                    game.pressLeftButton(coord);
                if(game.getState()==GameState.BOMBED && e.getButton()==MouseEvent.BUTTON1)
                    game.returnGame();
                panel.repaint();
            }
        });



        panel.setPreferredSize(new Dimension(Ranges.getSize().x*IMAGE_SIZE, Ranges.getSize().y*IMAGE_SIZE+100));
        add(panel);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        setVisible(true);

        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);


    }
    private void setImages(){
        for (Box box : Box.values())
            box.image=getImage(box.name().toLowerCase());
    }
    private Image getImage (String name){
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon ("src/Saper/"+filename);
        return icon.getImage();
    }
}
