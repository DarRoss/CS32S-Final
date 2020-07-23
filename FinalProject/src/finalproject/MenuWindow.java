package finalproject;

import static finalproject.FinalProject.maxEnemies;
import static finalproject.FinalProject.maxWaveNumber;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * MenuWindow.java - Main menu to select different game modes
 *
 * @author d.ross2
 * @since 4-Jun-2019
 * @instructor Mr. Wachs
 */
public class MenuWindow extends JFrame {
    
    // java container is declared
    private Container container;

    // labels and buttons declared
    private JLabel selectLabel;
    private JButton challenge1;
    private JButton challenge2;
    private JButton challenge3;
    private JButton challenge4;
    private JLabel backgroundLabel;

    /**
     * Constructor places components
     */
    MenuWindow() {
        // JLabels are created and formatted
        initControls();
        // adds the JLabels to the JFrame
        addControlsToContainer();
        // sets the properties of the JFrame
        setContainerProperties();
        // sets up detection for button pressing
        setActions();
        // sets the JFrame to be visible
        this.setVisible(true);
    }

    /**
     * Controls are initialized
     */
    private void initControls() {
        // jlabels and buttons created
        selectLabel = new JLabel("Select your challenge:");
        challenge1 = new JButton("Sniper Challenge: 1 enemy per wave, 30 waves");
        challenge2 = new JButton("Squad Challenge: 6 enemies per wave, 20 waves");
        challenge3 = new JButton("Platoon Challenge: 40 enemy per wave, 10 waves");
        challenge4 = new JButton("Army Challenge: 100 enemies per wave, 3 waves");
        // background created
        backgroundLabel = new JLabel();
        // bg image is set
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/menu.jpg")));

    }

    /**
     * Controls added to container
     */
    private void addControlsToContainer() {
        // new container is created
        container = this.getContentPane();
        // the layout is set to null in order to be customized
        container.setLayout(null);

        // components added
        container.add(selectLabel);
        container.add(challenge1);
        container.add(challenge2);
        container.add(challenge3);
        container.add(challenge4);
        container.add(backgroundLabel);

        // set bounds of components
        selectLabel.setBounds(400, 450, 300, 20);
        challenge1.setBounds(100, 500, 350, 60);
        challenge2.setBounds(500, 500, 350, 60);
        challenge3.setBounds(100, 600, 350, 60);
        challenge4.setBounds(500, 600, 350, 60);
        backgroundLabel.setBounds(0, 0, 1000, 1000);

    }

    /**
     * Container properties added
     */
    private void setContainerProperties() {
        // the JFrame is unable to be resized
        this.setResizable(false);
        // the title is set
        this.setTitle("Pillbox Defense (Reaction Game)");
        // the window size is set to be twice the size of the origin point
        this.setSize(1000, 1000);
        // the JFrame appears at the centre of the monitor
        this.setLocationRelativeTo(null);
        // the JFrame exits when the exit button is pressed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Actions are set
     */
    private void setActions() {
        // mouse listener added to the first challenge button
        challenge1.addMouseListener(new MouseListener() {
            @Override
            // detects if mouse is clicked on the button
            public void mouseClicked(MouseEvent me) {
                // new game begins
                startNewGame(1, 30);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

        });

        // mouse listener added to the second challenge button
        challenge2.addMouseListener(new MouseListener() {
            @Override
            // detects if mouse is clicked on the button
            public void mouseClicked(MouseEvent me) {
                // new game begins
                startNewGame(6, 20);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        // mouse listener added to the third challenge button
        challenge3.addMouseListener(new MouseListener() {
            @Override
            // detects if mouse is clicked on the button
            public void mouseClicked(MouseEvent me) {
                // new game begins
                startNewGame(40, 10);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        // mouse listener added to the fourth challenge button
        challenge4.addMouseListener(new MouseListener() {
            @Override
            // detects if mouse is clicked on the button
            public void mouseClicked(MouseEvent me) {
                // new game begins
                startNewGame(100, 3);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

    }

    /**
     * Starts a new game. 
     * @param targets the max number of targets
     * @param waves the max number of waves
     */
    private void startNewGame(int targets, int waves) {
        // max enemies set
        maxEnemies = targets;
        // max waves set
        maxWaveNumber = waves;
        // new game window instance created
        GameWindow gameWindow = new GameWindow();
        // the menu is made invisible
        this.setVisible(false);
        // the menu is disposed
        this.dispose();
    }

}
