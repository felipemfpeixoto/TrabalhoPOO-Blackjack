package View.Components;

import java.awt.*;

public class myButton {
    int x, y, width, height;
    
    double xRatio, yRatio, widthRatio, heightRatio;
    public String name;
    public boolean isVisible;
    private ButtonHandler handler;
    
    public myButton(double xRatio, double yRatio, double widthRatio, double heightRatio, String name, boolean isVisible, ButtonHandler handler) {
        this.xRatio = xRatio;
        this.yRatio = yRatio;
        this.widthRatio = widthRatio;
        this.heightRatio = heightRatio;
        this.name = name;
        this.isVisible = isVisible;
        this.handler = handler;
    }
    
    public void updateDimensions(int panelWidth, int panelHeight) {
        this.x = (int) (xRatio * panelWidth);
        this.y = (int) (yRatio * panelHeight);
        this.width = (int) (widthRatio * panelWidth);
        this.height = (int) (heightRatio * panelHeight);
    }

    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= (x + width) && mouseY >= y && mouseY <= (y + height);
    }
    
    public void drawButton(Graphics2D g2d, boolean isHovered) {

        if (isHovered) {
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.fillRect(x, y, width, height);
        }
        
        if (!isVisible) return;
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + fm.getAscent()) / 2 - 2;
        g2d.drawString(name, textX, textY);
    }
    
    public void click() {
        if (handler != null) {
            handler.handleClick();
        }
    }
}
