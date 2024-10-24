package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


import View.Components.*;

public class Banca extends JPanel {

    private Image backgroundImage;
    private ArrayList<myButton> ArrayButtons;
    private ArrayList<myLabel> ArrayLabels;
    private myButton hoveredButton = null;
    
    private JLabel dealLabel;
    private JLabel balanceLabel;
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        
        for (myButton button : ArrayButtons) {
        	button.updateDimensions(getWidth(), getHeight());
            button.drawButton(g2d, button == hoveredButton);
        }
        
        for (myLabel label : ArrayLabels) {
            label.updateDimensions(getWidth(), getHeight());
        }
        
    }

    public Banca(PrincipalView frame) {
        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/blackjack.png")).getImage();
        ArrayButtons = new ArrayList<>();
        ArrayLabels = new ArrayList<>();
        
        setLayout(null);

        setButtons();
        mouseListeners();
        setLabels();
    }
    
    private void setLabels() {
    	myLabel label1 = new myLabel("BET: 0.00", 0.02, 0.85, 0.3, 0.1, getWidth(), getHeight());
        ArrayLabels.add(label1);
        add(label1);
        label1.updateText("BET: %.2f", 0);
        
        myLabel label2 = new myLabel("BET: 0.00", 0.02, 0.91, 0.3, 0.1, getWidth(), getHeight());
        ArrayLabels.add(label2);
        add(label2);
        label2.updateText("BAL: %.2f", 100.50);
    }
    
    private void setButtons() {
    	// Botao exit esquerda
        ArrayButtons.add(new myButton(0.008, 0.765, 0.182, 0.085, "exit", false, () -> {
            System.out.println("Ir Menu");
            PrincipalView.trocarTela("MenuInicial");
        }));
        
        
        // Botoes centrais
        ArrayButtons.add(new myButton(0.21, 0.92, 0.14, 0.06, "double", false, () -> {
            System.out.println("Double Clicado");
           
        }));
        
        ArrayButtons.add(new myButton(0.36, 0.92, 0.14, 0.06, "split", false, () -> {
            System.out.println("Split Clicado");
            
        }));
        
        ArrayButtons.add(new myButton(0.51, 0.92, 0.14, 0.06, "clear", false, () -> {
            System.out.println("Clear Clicado");
            
        }));
        
        ArrayButtons.add(new myButton(0.66, 0.92, 0.14, 0.06, "deal", false, () -> {
            System.out.println("Deal Clicado");
            
        }));
        
        
        // Botoes laterais
        ArrayButtons.add(new myButton(0.84, 0.77, 0.14, 0.06, "hit", false, () -> {
            System.out.println("Hit Clicado");
            
        }));
        
        ArrayButtons.add(new myButton(0.84, 0.845, 0.14, 0.06, "stand", false, () -> {
            System.out.println("Stand Clicado");
            
        }));
        
        ArrayButtons.add(new myButton(0.84, 0.92, 0.14, 0.06, "sunder", false, () -> {
            System.out.println("Sunder Clicado");
            
        }));
    }
    
    private void mouseListeners() {
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
