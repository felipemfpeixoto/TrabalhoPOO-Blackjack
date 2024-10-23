import javax.swing.SwingUtilities;

import View.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	PrincipalView frame = new PrincipalView();
            frame.setVisible(true);
        });
    }
}


