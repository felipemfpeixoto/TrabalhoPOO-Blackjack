package View;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


import View.Components.*;

public class Banca extends JPanel {

    private Image backgroundImage;
    private ArrayList<myButton> ArrayButtons;
    private myButton hoveredButton = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        for (myButton button : ArrayButtons) {
        	button.updateDimensions(getWidth(), getHeight());
            button.drawButton(g2d, button == hoveredButton);
        }
    }

    public Banca(PrincipalView frame) {
        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/blackjack.png")).getImage();
        ArrayButtons = new ArrayList<>();
        
        ArrayButtons.add(new myButton(0.008, 0.765, 0.182, 0.085, "exit", false, () -> {
            System.out.println("Ir Menu");
            PrincipalView.trocarTela("MenuInicial");
        }));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for (myButton button : ArrayButtons) {
                    if (button.contains(x, y)) {
                        button.click();
                        break;
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredButton = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                boolean foundHover = false;

                for (myButton button : ArrayButtons) {
                    if (button.contains(x, y)) {
                        hoveredButton = button;
                        foundHover = true;
                        break;
                    }
                }

                if (!foundHover) {
                    hoveredButton = null;
                }
                repaint();
            }
        });
    }
}
