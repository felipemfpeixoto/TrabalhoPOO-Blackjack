package View;

import javax.swing.*;
import java.awt.*;


public class PrincipalView extends JFrame {
    
    private static JPanel contentPanel; 
    
    public PrincipalView() {
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPanel = new JPanel(new CardLayout());
        setContentPane(contentPanel);
        
        contentPanel.add(new MenuInicial(this), "MenuInicial");
        contentPanel.add(new Creditos(this), "Creditos");
        contentPanel.add(new Banca(this), "Banca");
    }
  
    
    public static void trocarTela(String nomePainel) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, nomePainel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	PrincipalView frame = new PrincipalView();
            frame.setVisible(true);
        });
    }
}