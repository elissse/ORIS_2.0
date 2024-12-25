package com.oris.lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventHandler extends JFrame {
    public EventHandler() throws HeadlessException {
        super("обработка событий клавиатуры");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new KeyL());
        MouseL ml = new MouseL();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        setSize(400, 400);
        setVisible(true);
    }

    class KeyL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("key typed " + e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keyPressed " + e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased " + e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        new EventHandler();
                    }
                }
        );
    }

    class MouseL implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("mouseClicked " + e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed " + e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased " + e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("mouseEntered " + e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("mouseExited " + e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouseDragged " + e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("mouseMoved " + e.getX() + " " + e.getY());
        }
    }
}
