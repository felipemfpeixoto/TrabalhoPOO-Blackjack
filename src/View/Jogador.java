package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import View.Components.*;


public class Jogador extends JFrame {
    
	 private Image backgroundImage;

	@Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
	 
	public Jogador() {
        setTitle("Jogador");
        setSize(800, 200);
        setMinimumSize(new Dimension(600, 200));
        setMaximumSize(new Dimension(800, 200));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/fundoJogador.png")).getImage();
    }
}
