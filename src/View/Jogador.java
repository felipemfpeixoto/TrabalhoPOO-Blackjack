package View;
import Model.Baralho;
import Model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends JFrame {
    private Image backgroundImage;
    private Image carta1, carta2;
    
    private Baralho barallho = new Baralho();
    private List<Carta> mao = new ArrayList<>();
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
    }

    // Função para mostrar as cartas na interface
    private void mostrarCartas() {
    	atualizaCartas();
        
        // Atualiza a interface
        repaint();
    }
    
    private void atualizaCartas() {
    	cartas.clear();
    	
        for (Carta carta : mao) {
        	Image newCarta = new ImageIcon(getClass().getResource("/Imagens/" + carta.getNome() + carta.getNaipe() + ".gif")).getImage();
        	if (newCarta == null) {
        		System.out.printf("Carta %s%s não encontrada\n", carta.getNome(), carta.getNaipe());
        	} else {
        	cartas.add(newCarta);
        	}
        }
    }
    
    public void getCard() {
    	Carta carta = Baralho.giveCard();
    	mao.add(carta);
    	for (Carta cartaMao : mao) {
    		System.out.printf("%s%s", cartaMao.getNome(), cartaMao.getNaipe());
    	}
    	mostrarCartas();
    }
}
