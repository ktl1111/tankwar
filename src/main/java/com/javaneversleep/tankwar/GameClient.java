package com.javaneversleep.tankwar;


import com.apple.eawt.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {

    private Tank playerTank;

    private List<Tank> enemyTanks;

    private List<Wall> walls;

    private GameClient() {
        this.playerTank = new Tank(400,100, Direction.DOWN);
        this.enemyTanks = new ArrayList<>(12);
        this.walls = Arrays.asList(
                new Wall(280, 140, true, 12),
                new Wall(280, 540, true, 12),
                new Wall(100, 160, false, 12),
                new Wall(700, 160, false, 12)

        );
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                this.enemyTanks.add(new Tank(200 + j * 120, 400 + 40 * i, true, Direction.UP));
            }
        }

        this.setPreferredSize(new Dimension(800,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        playerTank.draw(g);
        for (Tank tank : enemyTanks) {
            tank.draw(g);
        }
        for(Wall wall: walls) {
            wall.draw(g);
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
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
