package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends JFrame {
    
    private GameController gameController;
    private static Jogador instanciaUnica;

    private List<String> cartasNames = new ArrayList<>();
    private List<Image> cartas = new ArrayList<>();

    int points = 0;
    private JLabel pointsLabel;
    private BackgroundPanel backgroundPanel;

    // Construtor privado para o padrão Singleton
    private Jogador() {
        setTitle("Jogador");
        setSize(800, 200);
        setMinimumSize(new Dimension(600, 200));
        setMaximumSize(new Dimension(800, 200));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Cria o painel de fundo personalizado
        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);  // Usamos layout nulo para posicionamento manual

        // Configura o pointsLabel
        pointsLabel = new JLabel("Pontos: " + points);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setBounds(10, 10, 200, 30);  // Posição do label no painel de fundo
        backgroundPanel.add(pointsLabel);

        // Adiciona o painel de fundo ao JFrame
        this.setContentPane(backgroundPanel);

        // Instancia o singleton GameController
        gameController = GameController.getInstancia();
        atualizaCartas();
    }

    // Método para obter a única instância do GameController (Singleton)
    public static Jogador getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Jogador();
        }
        return instanciaUnica;
    }

    public void atualizaCartas() {
        cartas.clear();
        cartasNames.clear();

        cartasNames = gameController.getMaoJogador();
        points = gameController.getPontosJogador();

        for (String cartaName : cartasNames) {
            Image newCarta = new ImageIcon(getClass().getResource("/Imagens/" + cartaName + ".gif")).getImage();
            if (newCarta == null) {
                System.out.printf("Carta %s não encontrada\n", cartaName);
            } else {
                cartas.add(newCarta);
            }
        }

        pointsLabel.setText("Pontos: " + points);  // Atualiza o texto do label
        repaint();
    }

    // Classe interna para criar o painel de fundo com imagem
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Carrega a imagem de fundo
            backgroundImage = new ImageIcon(getClass().getResource("/Imagens/fundoJogador.png")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

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
    }
}
