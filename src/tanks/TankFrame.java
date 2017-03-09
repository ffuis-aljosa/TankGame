package tanks;

import javax.swing.JFrame;

public class TankFrame extends JFrame {

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final String title = "Tanks Game";
    
    private final TankPanel panel;
    
    public TankFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        panel = new TankPanel();
        add(panel);
    }
    
    
}
