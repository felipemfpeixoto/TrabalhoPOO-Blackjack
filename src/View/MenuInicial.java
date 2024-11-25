package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.GameController;

import java.awt.*;

public class MenuInicial extends JPanel {
	
	private Jogador jogadorFrame;
	private Banca Banca;
    
    private JButton button_ini = new JButton("Iniciar");
    private JButton button_cnt = new JButton("Continuar");
    private JButton button_cre = new JButton("Créditos");
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.BLACK);

        String text = "Blackjack";
        
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 70; 
        
        g2d.drawString(text, x, y);
    }
    
    public MenuInicial(PrincipalView frame) { 
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(button_ini, gbc);
        
        gbc.gridy = 1;
        add(button_cnt, gbc);
        
        gbc.gridy = 2;
        add(button_cre, gbc);
        
        button_ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PrincipalView.trocarTela("Banca");
            	System.out.println("Ir Banca");
            }
        });
        
        button_cnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int auxValue = GameController.leTxteSetGame();
            	
            	 if (auxValue == -1) {
            		 JOptionPane.showMessageDialog(null, "Nao tem save, vamos iniciar outro jogo.", "Alerta", JOptionPane.WARNING_MESSAGE);
            	 }
            	
            	PrincipalView.trocarTela("Banca");
            	System.out.println("Ir Banca");
            	
            	// Atualiza a View Jogador
            	Jogador jogador = Jogador.getInstancia();
                jogador.atualizaCartas();
                
                Banca banca = PrincipalView.getBanca(); // Método para obter a instância de Banca
                if (banca != null) {
                    banca.updateView();
                } else {
                    System.err.println("Erro: A instância de Banca é null!");
                }
            	
            }
        });
        
        button_cre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PrincipalView.trocarTela("Creditos");
            	System.out.println("Ir Creditos");
            	
            }
        });
    }
}
