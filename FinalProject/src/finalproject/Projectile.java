package finalproject;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * Projectile.java - Projectile class to allow the pillbox to shoot enemies
 *
 * @author d.ross2
 * @since 12-Jun-2019
 * @instructor Mr. Wachs
 */
public class Projectile {

    // projectile JLabel is declared
    private static JLabel projectile;

    /**
     * a projectile JLabel and Swing timer are created
     */
    Projectile() {
        // projectile JLabel is created
        projectile = new JLabel();
        // text is set to be blank
        projectile.setText("");
        // the projectile is colered black
        projectile.setBackground(Color.black);
        // the projectile become opaque and visible
        projectile.setOpaque(true);

    }

    /**
     * This method is solely meant to retrieve the formatted projectile JLabel
     *
     * @return the projectile JLabel
     */
    public static JLabel createProjectile() {
        // projectile JLabel is returned
        return projectile;
    }

    /**
     * The projectile moves a small distance using X length and Y length
     *
     * @param x how much the projectile will move vertically
     * @param y how much the projectile will move horizontally
     */
    public static void travel(int x, int y) {
        // if statement makes sure that the projectile is in the window
        if (projectile.getX() < 1000
                && projectile.getX() > -10
                && projectile.getY() < 1000
                && projectile.getY() > -10) {
            // move the projectile a fraction of the distance between the cursor and the origin
            projectile.move(projectile.getX() + x / 10, projectile.getY() + y / 10);
            // if the cursor is further away from the center, the projectile moves faster
        }

    }

}
