package View.Components;

import javax.swing.JLabel;
import java.awt.*;

public class myLabel extends JLabel{
	
	int x, y, width, height;
	double xRatio, yRatio, widthRatio, heightRatio;
	
	public myLabel(String text, double xRatio, double yRatio, double widthRatio, double heightRatio, int parentWidth, int parentHeight) {
        super(text);
        
        this.xRatio = xRatio;
        this.yRatio = yRatio;
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
        
        setFont(new Font("Arial", Font.PLAIN, 0));
        setForeground(Color.RED);
        
        updateDimensions(parentWidth, parentHeight);
    }
	
	
	public void updateDimensions(int panelWidth, int panelHeight) {
        this.x = (int) (xRatio * panelWidth);
        this.y = (int) (yRatio * panelHeight);
        this.width = (int) (widthRatio * panelWidth);
        this.height = (int) (heightRatio * panelHeight);
        
        int fontSize = (int) (Math.min(width, height) * 0.3);
        setFont(getFont().deriveFont((float) fontSize));
        
        setBounds(x, y, width, height);
    }
	
	
	public void updateText(String newString, double newBalance) {
        setText(String.format(newString, newBalance));
    }
}
