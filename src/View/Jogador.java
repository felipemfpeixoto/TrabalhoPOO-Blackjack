package View;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends JFrame {
	
	private GameController gameController;
	
    private Image backgroundImage;
    
    private List<String> cartasNames = new ArrayList<>();
    private List<Image> cartas = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
        // Desenha as cartas se elas não forem nulas
        int cartaLargura = 100;
        int cartaAltura = 125;
        int espaco = 20;
        
        int i = 0;
        for (Image carta : cartas) {
        	g2d.drawImage(carta, 100 + (i * espaco), 50, cartaLargura, cartaAltura, this);
        	i++;
        }
    }
	 
    public Jogador() {
        setTitle("Jogador");
        setSize(800, 200);
        setMinimumSize(new Dimension(600, 200));
        setMaximumSize(new Dimension(800, 200));

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Carrega a imagem de fundo
        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/fundoJogador.png")).getImage();
        
        // Instanciando o singleton GameController
        gameController = GameController.getInstancia();
        atualizaCartas();
    }

    private void atualizaCartas() {
    	cartas.clear();
    	cartasNames.clear();
    	
    	cartasNames = gameController.getMaoJogador();
    	
        for (String cartaName : cartasNames) {
        	Image newCarta = new ImageIcon(getClass().getResource("/Imagens/" + cartaName + ".gif")).getImage();
        	if (newCarta == null) {
        		System.out.printf("Carta %s%s não encontrada\n", cartaName);
        	} else {
        		cartas.add(newCarta);
        	}
        }
        
        repaint();
    }
}
