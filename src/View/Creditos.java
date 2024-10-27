package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Creditos extends JPanel {
	private JButton button_back = new JButton("< Voltar");
	
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setFont(new Font("Arial", Font.PLAIN, 18));
	    g2d.setColor(Color.BLACK);

	    String[] lines = {
	        "Trabalho POO 2024.2",
	        "Feito por:",
    	    "Felipe Peixoto - 2211750",
    	    "Luis Felipe Gadelha - 22261050",
    	    "Luiz Seibel - 21110799"
	    };


	    FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
	    int y = 70;

	    for (String line : lines) {
	        int x = (getWidth() - metrics.stringWidth(line)) / 2;
	        g2d.drawString(line, x, y);
	        y += metrics.getHeight();
	    }
	}
	
	public Creditos(PrincipalView frame) {
        setLayout(new BorderLayout());
 
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topLeftPanel.add(button_back);

        add(topLeftPanel, BorderLayout.NORTH);
        
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PrincipalView.trocarTela("MenuInicial");
            	System.out.println("Ir Menu");
            	
            }
        });
	}
}
