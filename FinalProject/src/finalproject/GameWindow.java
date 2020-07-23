package finalproject;

// imports are created for the global variables
import static finalproject.FinalProject.maxEnemies;
import static finalproject.FinalProject.maxWaveNumber;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * GameWindow.java - Displays the contents in the game window
 *
 * @author d.ross2
 * @since 4-Jun-2019
 * @instructor Mr. Wachs
 */
public class GameWindow extends JFrame {

    // java container is declared
    private Container container;

    // timers are declared
    private Timer tickTimer;
    // newWave separates waves of enemies
    private Timer newWaveTimer;
    // 
    private Timer stopwatchTimer;

    // JLabels are declared
    private JLabel pillboxLabel;
    private JLabel sandLabel;
    private JLabel coordLabel;
    private JLabel distanceLabel;
    private JLabel projectileLabel;
    private JLabel waveLabel;
    private JLabel stopwatchLabel;

    // enemy JLabel array is declared
    private JLabel[] enemyLabel;

    // boolean array determines if enemies in array are dead
    private boolean[] isDead;

    // integers are declared
    // xValue nad yValue determine the coordinates relative to the JFrame
    private int xValue = 0;
    private int yValue = 0;
    // xLength and yLength determine the distance between the origin point and the mouse
    private int xLength = 0;
    private int yLength = 0;
    // xLock and yLock maintain the direction of the projectile
    private int xLock = 0;
    private int yLock = 0;
    // waveNumber determines how many sets of enemies have spawned
    private int waveNumber = 0;
    // stopwatch determines how much time it took to reach the max wave number
    private int stopwatch = 0;
    // the maximum number of waves
    private final int MAX_WAVE_NUMBER = maxWaveNumber;

    // constants are declared
    // origin is the middle of the JFrame
    // multiply origin by 2 to get JFrame length or width
    private static final int ORIGIN = 500;
    // MAX_ENEMIES determines the maximum number of enemies at once
    private final int MAX_ENEMIES = maxEnemies;

    // random declared
    private Random random;

    /**
     * Sets up the JFrame with components and actions
     */
    public GameWindow() {
        // JLabels are created and formatted
        initControls();
        // adds the JLabels to the JFrame
        addControlsToContainer();
        // sets the properties of the JFrame
        setContainerProperties();
        // sets up detection for mouse moving and pressing
        setActions();
        // start the timers
        startTimers();
        // makes the JFrame visible
        this.setVisible(true);
        
    }

    /**
     * The JLabels are created and formatted
     */
    private void initControls() {
        // new JLabel for the enemy array is created
        // array ends at max enemies
        enemyLabel = new JLabel[MAX_ENEMIES];
        // array is created to determine if enemies are dead
        isDead = new boolean[MAX_ENEMIES];
        // new JLabel for the pillbox
        pillboxLabel = new JLabel();
        // new JLabel for the sand
        sandLabel = new JLabel();
        // new JLabel for the coordinates
        coordLabel = new JLabel();
        // new JLabel for the mouse distance from origin
        distanceLabel = new JLabel();
        // new label for the number of times the enemies have spawned
        waveLabel = new JLabel();
        // stopwatch label created
        stopwatchLabel = new JLabel();

        // images are set for the pillbox and sand texture
        pillboxLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/pillbox.png")));
        sandLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/sand.png")));

        // new random instance is created
        random = new Random();

        // timers are initialized
        initTimers();
        
    }

    /**
     * The JLabels are added to the JFrame
     */
    private void addControlsToContainer() {
        // new container is created
        container = this.getContentPane();
        // the layout is set to null in order to be customized
        container.setLayout(null);
        // the projectile label which is formatted in the class is returned here
        projectileLabel = Projectile.createProjectile();

        // all enemies are added to the container
        for (int i = 0; i < MAX_ENEMIES; i++) {
            // create an enemy JLabel from the class
            enemyLabel[i] = new JLabel();
            // add the enemy JLabel to the container
            container.add(enemyLabel[i]);
            // target sprite is added to the container
            enemyLabel[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/target.png")));
        }

        // other JLabels are added to the container
        // JLabels added first will be on top of the ones added after
        container.add(coordLabel);
        container.add(distanceLabel);
        container.add(stopwatchLabel);
        container.add(waveLabel);
        container.add(pillboxLabel);
        container.add(projectileLabel);
        container.add(sandLabel);

        // the coordinates and distance labels are placed in the upper left corner
        distanceLabel.setBounds(0, 20, 200, 20);
        coordLabel.setBounds(0, 0, 200, 20);
        
        stopwatchLabel.setBounds(800, 0, 200, 20);
        stopwatchLabel.setText("Time: " + stopwatch);

        // the wave label is placed at the top right corner
        waveLabel.setBounds(0, 40, 200, 20);
        // the text is set
        waveLabel.setText("Wave " + waveNumber);

        // the sand label begins at the top left corner and fills the entire JFrame
        sandLabel.setBounds(0, 0, ORIGIN * 2, ORIGIN * 2);

        // the pillbox is placed near the origin point
        pillboxLabel.setBounds(ORIGIN - pillboxLabel.getPreferredSize().width / 2, ORIGIN - pillboxLabel.getPreferredSize().height / 2, 200, 200);
        
    }

    /**
     * The properties of the JFrame are added
     */
    private void setContainerProperties() {
        // the JFrame is unable to be resized
        this.setResizable(false);
        // the title is set
        this.setTitle("Pillbox Defense (Reaction Game)");
        // the window size is set to be twice the size of the origin point
        this.setSize(ORIGIN * 2, ORIGIN * 2);
        // the JFrame appears at the centre of the monitor
        this.setLocationRelativeTo(null);
        // the JFrame exits when the exit button is pressed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // the cursor is set to be a crosshair
        this.setCursor(CROSSHAIR_CURSOR);
        
    }

    /**
     * Detects if the mouse is doing stuff
     */
    private void setActions() {

        // mouse motion listener is added
        this.addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            // detects mouse movement
            public void mouseMoved(MouseEvent e) {
                // the mouse coordinates are calculated
                calculateCoordinates(e);
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });

        // mouse clicker listener is added
        this.addMouseListener(new MouseListener() {
            
            @Override
            // detects if mouse is pressed
            public void mousePressed(MouseEvent e) {
                // pillbox shoots a projectile
                shoot();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
    }

    /**
     * Creates a small projectile that travels
     */
    private void shoot() {
        // if mouse is pressed, create a new projectile in the center
        projectileLabel.setBounds(ORIGIN - 10, ORIGIN - 10 * 3, 10, 10);
        // X and Y values of mouse are locked in place
        // to make the projectile travel in a straight line
        xLock = xLength;
        yLock = yLength;
    }

    /**
     * Calculates coordinates of mouse and distance from origin point
     *
     * @param e mouse event parameter
     */
    private void calculateCoordinates(MouseEvent e) {
        // calculate the coordinates of the mouse relative to the JFrame
        xValue = e.getX();
        yValue = e.getY();

        // calculate the distance between the mouse and the origin
        xLength = xValue - ORIGIN;
        yLength = yValue - ORIGIN;

        // display the coordinates and distance
        coordLabel.setText("Coordinates:    X: " + xValue + "   Y: " + yValue);
        distanceLabel.setText("Distance:    X: " + xLength + "   Y: " + yLength);
        
    }

    /**
     * The enemies are placed in the JFrame
     */
    private void spawnEnemies() {
        // for loop for setting bounds of enemy
        for (int i = 0; i < MAX_ENEMIES; i++) {
            // enemy JLabel is placed in a random location
            enemyLabel[i].setBounds(random.nextInt(ORIGIN * 2 - 100), random.nextInt(ORIGIN * 2 - 100), 50, 73);
            // enemy is made visible
            enemyLabel[i].setVisible(true);
            // the index in the boolean array is set to not dead
            isDead[i] = false;
            
        }
        
    }

    /**
     * Timers are initialized
     */
    private void initTimers() {
        // tick timer //
        // every time the tickTimer finishes, multiple things are calculated
        tickTimer = new Timer(10, (ActionEvent ae) -> {
            // move the projectile
            Projectile.travel(xLock, yLock);
            // for loop goes through each enemy
            for (int i = 0; i < MAX_ENEMIES; i++) {
                // if the enemy is alive...
                if (isDead[i] == false) {
                    // detect for collisions between projectile and enemy
                    // isDead becomes true if there is a collision
                    isDead[i] = Detector.checkForCollisions(projectileLabel, enemyLabel[i]);
                    // if the enemy is dead...
                    if (isDead[i] == true) {
                        // check if all enemies are dead
                        checkEnemies();
                    }
                }
                
            }
        });

        // newWaveTimer //
        // every time the newWaveTimer finishes, a new wave of enemies appears
        newWaveTimer = new Timer(1000, (ActionEvent ae) -> {
            // enemies are spawned
            spawnEnemies();
            // the wave number increases by one
            waveNumber++;
            // wave label is updated
            waveLabel.setText("Wave " + waveNumber);
            // if wavenumber reached max wave number...
            if (waveNumber > MAX_WAVE_NUMBER) {
                // game window becomes invisible
                this.setVisible(false);
                // show message stating challenge is complete
                JOptionPane.showMessageDialog(this, "Challenge completed!\nYour time: " + stopwatch);
                // game is exited
                System.exit(0);
            }
        });

        // stopwatch timer //
        // every time this finishes, a second is added to the stopwatch
        stopwatchTimer = new Timer(1000, (ActionEvent ae) -> {
            stopwatch++;
            stopwatchLabel.setText("Time: " + stopwatch);
        });

        // the wave timer does not repeat because it only signifies the time between waves
        newWaveTimer.setRepeats(false);
    }

    /**
     * Starts all the timers that are necessary
     */
    private void startTimers() {
        // start the wave timer
        newWaveTimer.start();
        // start the game timer
        tickTimer.start();
        // start the stopwatch
        stopwatchTimer.start();
    }

    /**
     * Check if all enemies are dead. If they are all dead, a new wave starts
     */
    private void checkEnemies() {
        // for loop checks if all enemies are dead
        for (int i = 0; i < MAX_ENEMIES; i++) {
            // if the enemy is alive... 
            if (isDead[i] == false) {
                // method returns (no new wave is started)
                return;
            }
        }

        // if all are dead, a new wave starts
        newWaveTimer.start();
        
    }
    
}
