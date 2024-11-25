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
	private Jogador jogador;

    private Image backgroundImage;
    private List<String> cartasNames;
    private List<Image> cartas;
    private int dealerPoints = 0;
    private JLabel dealerPointsLabel;

    
    private ArrayList<myButton> ArrayButtons;
    private ArrayList<myLabel> ArrayLabels;
    
    private myLabel betLabel;
    private myLabel balanceLabel;
    
    private myButton hoveredButton = null;
    
    private int balance = 0;
    private int bet = 0;
    private boolean estourou = false;
    private int dealerStatus = -1;
    
    private boolean rodadaTerminou = false;

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

        // Atualiza o texto do dealerPointsLabel
        dealerPointsLabel.setText("Pontos do Dealer: " + dealerPoints);

        if (cartas != null) {
            int cartaLargura = 100;
            int cartaAltura = 125;
            int espaco = 50;
            int i = 0;

            for (Image carta : cartas) {
                g2d.drawImage(carta, (getWidth() / 2) - (cartaLargura * i) - espaco, getHeight() / 3, cartaLargura, cartaAltura, this); // Precisamos rever o posicionamento das cartas
                i++;
            }
        }
        
    }


    public Banca(PrincipalView frame) {
    	
    	gameController = GameController.getInstancia(); // Instanciando o singleton GameController
    	jogador = Jogador.getInstancia();
    	
        backgroundImage = new ImageIcon(getClass().getResource("/Imagens/blackjack.png")).getImage();
        ArrayButtons = new ArrayList<>();
        ArrayLabels = new ArrayList<>();
        cartas = new ArrayList<>();
        cartasNames = new ArrayList<>();
        
        setLayout(null);
        
        dealerPointsLabel = new JLabel("Pontos do Dealer: " + dealerPoints);
        dealerPointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dealerPointsLabel.setForeground(Color.WHITE);
        dealerPointsLabel.setBounds(10, 10, 200, 30);
        this.add(dealerPointsLabel);
        
        
        balance = gameController.getSaldo();

        setButtons();
        setLabels();
        mouseListeners();
    }
    
    private void setLabels() { // Função incorreta, precisamos olhar mais atentamente
    	betLabel = new myLabel("BET: 0.00", 0.02, 0.85, 0.3, 0.1, getWidth(), getHeight());
        ArrayLabels.add(betLabel);
        betLabel.setForeground(Color.WHITE);
        betLabel.updateText("BET: %.2f", bet);
        
        balanceLabel = new myLabel("BAL: 0.00", 0.02, 0.91, 0.3, 0.1, getWidth(), getHeight());
        balanceLabel.setForeground(Color.WHITE);
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
            
            gameController.salvarEmTxt();
        }));
        
        
        // Botoes centrais inferiores
        ArrayButtons.add(new myButton(0.21, 0.92, 0.14, 0.06, "double", false, () -> {
            System.out.println("Double Clicado");
            atualizaBet(bet);
           
        }));
        
        ArrayButtons.add(new myButton(0.36, 0.92, 0.14, 0.06, "split", false, () -> {
            System.out.println("Split Clicado");
            
        }));
        
        ArrayButtons.add(new myButton(0.51, 0.92, 0.14, 0.06, "clear", false, () -> {
            System.out.println("Clear Clicado");
            resetaBet();
            
            
        }));
        
        ArrayButtons.add(new myButton(0.66, 0.92, 0.14, 0.06, "deal", false, () -> {
            if (cartasNames.isEmpty() || estourou || rodadaTerminou) { // Esse if garante que apenas fará alguma coisa se não tiverem cartas na mesa
            	if (gameController.aposta(bet) == 0) {
            		resetaBet();
            		return;
            	}
            	System.out.println("Aposta valida");
            	
            	rodadaTerminou = false;
            	estadoInicial();
	            // Ao ser clicado, as cartas devem ser distribuídas
	            gameController.onDeal();
	            
	            dealerPoints = gameController.getPontosDealer();

	            atualizaCartasBanca();
	        	
	        	jogador.atualizaCartas();
	        	
	        	if(jogador.points == 21 && dealerPoints == 21) {
	        		JOptionPane.showMessageDialog(this, "Empate! Dois Blackjack.", "Resultado", JOptionPane.WARNING_MESSAGE);
	        		rodadaTerminou = true;
	        		gameController.devolverAposta(bet + balance);
                    resetaBet();
	        	}
	        	
	        	if (jogador.points == 21 && !rodadaTerminou) {
	        		JOptionPane.showMessageDialog(this, "Você ganhou! Blackjack.", "Resultado", JOptionPane.WARNING_MESSAGE);
	        		ganhou();
	        		rodadaTerminou = true;
	        	}
	        	if(dealerPoints == 21 && !rodadaTerminou) {
	        		JOptionPane.showMessageDialog(this, "Você perdeu! Blackjack.", "Resultado", JOptionPane.WARNING_MESSAGE);
	        		rodadaTerminou = true;
	        		resetaBet();
	        	}
	            
	        	if(rodadaTerminou) {
	        		limpaSessao();
	        	}
	        	
	        	repaint();
            }
        }));
        
        
        // Botoes laterais inferiores
        ArrayButtons.add(new myButton(0.84, 0.77, 0.14, 0.06, "hit", false, () -> {
            System.out.println("Hit Clicado");
            
            if (!cartasNames.isEmpty() && !estourou) {
            	estourou = gameController.onHit();
	        	
	        	jogador.atualizaCartas();
	        	
	        	if (estourou) {
	        		// Mostra um alerta de que o jogador perdeu
	        		JOptionPane.showMessageDialog(this, "Você perdeu! Estourou o limite de pontos.", "Resultado", JOptionPane.WARNING_MESSAGE);
	        		resetaBet();
	        		limpaSessao();
	        	}
            }
        }));
        
        ArrayButtons.add(new myButton(0.84, 0.845, 0.14, 0.06, "stand", false, () -> {
            System.out.println("Stand Clicado");

            if (!cartasNames.isEmpty() && !estourou && !rodadaTerminou) {
                dealerStatus = 0;
                
                while (dealerStatus == 0) {
                    dealerStatus = gameController.onStand();
                    dealerPoints = gameController.getPontosDealer();
                    dealerPointsLabel.setText("Pontos do Dealer: " + dealerPoints);
                    atualizaCartasBanca();
                    repaint();
                }

                if (dealerStatus == 1) {	// Dealer parou com pontos entre 17 e 21
                    
                    int playerPoints = gameController.getPontosJogador();

                    if (playerPoints > dealerPoints) {
                        JOptionPane.showMessageDialog(this, "Você ganhou! Sua pontuação é maior.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        ganhou();
                    } else if (playerPoints < dealerPoints) {
                        JOptionPane.showMessageDialog(this, "Você perdeu! Dealer tem pontuação maior.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        resetaBet();
                    } else {
                        JOptionPane.showMessageDialog(this, "Empate! A aposta será devolvida.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        gameController.devolverAposta(bet + balance);
                        resetaBet();
                    }
                } else if (dealerStatus == 2) {		// Dealer estourou
                    
                    JOptionPane.showMessageDialog(this, "Você ganhou! Dealer estourou.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    ganhou();
                }

                rodadaTerminou = true;
                limpaSessao();
            }
        }));


        
        ArrayButtons.add(new myButton(0.84, 0.92, 0.14, 0.06, "sunder", false, () -> {
            System.out.println("Sunder Clicado");
            if (!cartasNames.isEmpty() && !estourou) {
            	limpaSessao();
            	JOptionPane.showMessageDialog(this, "Você se rendeu! Faca uma nova aposta", "Alerta", JOptionPane.WARNING_MESSAGE);
            	
            }
           
            
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
	    betLabel.updateText("BET: %.2f", bet);
	    balanceLabel.updateText("BAL: %.2f", balance);
	
	    repaint(); // Redesenha a interface
	}
	
	private void resetaBet() {
		balance = gameController.getSaldo();
		bet = 0;
		
		 // Atualiza o texto dos labels para refletir os novos valores
	    betLabel.updateText("BET: %.2f", bet);
	    balanceLabel.updateText("BAL: %.2f", balance);
	    
	    repaint(); // Redesenha a interface
	}
	
	private void estadoInicial() {
    	gameController.reinicia();
		cartasNames.clear();
		cartas.clear();
		estourou = false;
	}
	
	private void atualizaCartasBanca() {
		cartas.clear();
		cartasNames = gameController.getMaoDealer();
		
		if (cartasNames == null || cartasNames.isEmpty()) {
	        System.out.println("Não há cartas para atualizar na banca.");
	        return;
	    }
		
        // Procura as imagens no diretório do projeto e adiciona as mesmas à List<Image>
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
	
	void limpaSessao() {
		gameController.limpaSessao();
    	jogador.atualizaCartas();
    	updateView();
    	resetaBet();
    	rodadaTerminou = true;
	}
	
	void updateView() {
		// cartas e pontos
		atualizaCartasBanca();
		dealerPoints = gameController.getPontosDealer();
		
		// saldo e aposta
		balance = gameController.getSaldo();
		balanceLabel.updateText("BAL: %.2f", balance);
		
		bet = gameController.getUltimaAposta();
		betLabel.updateText("BET: %.2f", bet);
		
	
		// variaveis extras
		
		
	}
	
	private void ganhou() {
		gameController.ganhouAposta(bet);
		resetaBet();
	}
}
