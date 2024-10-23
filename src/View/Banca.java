package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Banca extends JPanel{
	
	private Image backgroundImage;
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
	
	public Banca(PrincipalView frame) {
		backgroundImage = new ImageIcon(getClass().getResource("/Imagens/blackjack.png")).getImage();
	}
}
