package View;
import java.util.List;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import View.Components.*;
import Controller.*;

public class Banca extends JPanel {
	
	private GameController gameController;

    private Image backgroundImage;
    private Image carta1, carta2;
    private ArrayList<myButton> ArrayButtons;
    private ArrayList<myLabel> ArrayLabels;
    
    private myLabel dealLabel;
    private myLabel balanceLabel;
    
    private myButton hoveredButton = null;
    
    private int balance = 2000;
    private int bet = 0;

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
        
        // Mostra as cartas do dealer
        int cartaLargura = 100;
        int cartaAltura = 125;
        int espaco = 50;
        carta1 = new ImageIcon(getClass().getResource("/Imagens/ac.gif")).getImage();
        carta2 = new ImageIcon(getClass().getResource("/Imagens/ad.gif")).getImage();
        g2d.drawImage(carta1, (getWidth() / 2) - cartaLargura - espaco, getHeight() / 3, cartaLargura, cartaAltura, this);
        g2d.drawImage(carta2, (getWidth() / 2) + espaco, getHeight() / 3, cartaLargura, cartaAltura, this);
        
    }

    public Banca(PrincipalView frame) {
    	
    	gameController = GameController.getInstancia(); // Instanciando o singleton GameController
    	
        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/blackjack.png")).getImage();
        ArrayButtons = new ArrayList<>();
        ArrayLabels = new ArrayList<>();
        
        setLayout(null);

        setButtons();
        setLabels();
        mouseListeners();
    }
    
    private void setLabels() {
    	dealLabel = new myLabel("BET: 0.00", 0.02, 0.85, 0.3, 0.1, getWidth(), getHeight());
        ArrayLabels.add(dealLabel);
        dealLabel.setForeground(Color.BLACK);
        dealLabel.updateText("BET: %.2f", bet);
        
        balanceLabel = new myLabel("BAL: 0.00", 0.02, 0.91, 0.3, 0.1, getWidth(), getHeight());
        balanceLabel.setForeground(Color.BLACK);
        ArrayLabels.add(balanceLabel);
        balanceLabel.updateText("BAL: %.2f", balance);
        
        for (myLabel label : ArrayLabels) {
            add(label);
        }
    }
    
    private void setButtons() {
    	
    	
    	ArrayButtons.add(new myButton(0.84, 0.25, 0.05, 0.07, "ficha1", true, "/Imagens/ficha 1$.png" , () -> {
            System.out.println("Ficha 1$ Clicado");
            
            atualizaBet(1);
            
        }));
    	
    	ArrayButtons.add(new myButton(0.91, 0.25, 0.05, 0.07, "ficha5", true, "/Imagens/ficha 5$.png" , () -> {
            System.out.println("Ficha 5$ Clicado");

            atualizaBet(5);
        }));
    	
    	ArrayButtons.add(new myButton(0.84, 0.35, 0.05, 0.07, "ficha10", true, "/Imagens/ficha 10$.png" , () -> {
            System.out.println("Ficha 10$ Clicado");
            atualizaBet(10);
        }));
    	
    	ArrayButtons.add(new myButton(0.91, 0.35, 0.05, 0.07, "ficha20", true, "/Imagens/ficha 20$.png" , () -> {
            System.out.println("Ficha 20$ Clicado");
            atualizaBet(20);
        }));
    	
    	ArrayButtons.add(new myButton(0.84, 0.45, 0.05, 0.07, "ficha50", true, "/Imagens/ficha 50$.png" , () -> {
            System.out.println("Ficha 50$ Clicado");
            atualizaBet(50);
        }));
    	
    	ArrayButtons.add(new myButton(0.91, 0.45, 0.05, 0.07, "ficha100", true, "/Imagens/ficha 100$.png" , () -> {
            System.out.println("Ficha 100$ Clicado");
            atualizaBet(100);
        }));
    	
    	// Botao Deque
    	ArrayButtons.add(new myButton(0.008, 0.05, 0.14, 0.18, "deque", false, () -> {
            System.out.println("Deque Clicado");
            
        }));
    	
    	// Botao exit esquerda
        ArrayButtons.add(new myButton(0.008, 0.765, 0.182, 0.085, "exit", false, () -> {
            System.out.println("Ir Menu");
            PrincipalView.trocarTela("MenuInicial");
        }));
        
        
        // Botoes centrais inferiores
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
        
        
        // Botoes laterais inferiores
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
    
	private void atualizaBet(int valor) {
	    bet += valor;
	    balance -= valor;
	
	    // Atualiza o texto dos labels para refletir os novos valores
	    dealLabel.updateText("BET: %.2f", bet);
	    balanceLabel.updateText("BAL: %.2f", balance);
	
	    repaint(); // Redesenha a interface
	}
}
