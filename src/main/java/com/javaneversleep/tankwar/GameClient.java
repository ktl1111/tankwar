package com.javaneversleep.tankwar;

import com.apple.eawt.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GameClient extends JComponent {

    private Tank playerTank;

    private List<Tank>

    private GameClient() {
        this.playerTank = new Tank(400,100, Direction.DOWN);

        this.setPreferredSize(new Dimension(800,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        playerTank.draw(g);
    }

    public static void main(String[] args) throws IOException {
        final JFrame frame = new JFrame();
        frame.setTitle("TANK WAR!");

        File imageFile = new File("assets/images/icon.png");
        Image image =  ImageIO.read(imageFile);
        Application.getApplication().setDockIconImage(image);

        //frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());

        final GameClient client = new GameClient();
        frame.add(client);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean u = false, d = false, l = false, r = false;
                client.playerTank.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                client.playerTank.keyReleased(e);

            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true) {
            client.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
