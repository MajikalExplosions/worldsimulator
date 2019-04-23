

package worldsimulator.ui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author MajikalExplosions
 */
public class DisplayUI {
    private JFrame windowMain;
    private JPanel contentMain;
    
    public DisplayUI(String windowName) {
        windowMain = new JFrame(windowName);
        
        windowMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowMain.setSize(160 * 4, 90 * 4);
        windowMain.setVisible(true);
        windowMain.setFocusable(true);
    }
    
    public void initialize() {
        windowMain.setLayout(new BorderLayout());
        contentMain = new JPanel();
        
        windowMain.add(contentMain, BorderLayout.CENTER);
    }
}
