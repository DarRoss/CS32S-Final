package finalproject;

import javax.swing.JLabel;

/**
 * Enemy.java - Class for spawning enemies
 *
 * @author d.ross2
 * @since 12-Jun-2019
 * @instructor Mr. Wachs
 */
public class Detector {

    // integers are declared
    static int enemyRight = 0;
    static int enemyBottom = 0;

    /**
     * Checks for when the projectile origin is overlapping the enemy JLabel
     *
     * @param projectile the projectile JLabel
     * @param enemy the enemy JLabel
     */
    static boolean checkForCollisions(JLabel projectile, JLabel enemy) {

        // calculate the cooordinates of the right-most edge of the enemy JLabel
        enemyRight = enemy.getX() + enemy.getPreferredSize().width;
        // calculate the coordinates of the bottom-most edge of the enemy JLabel
        enemyBottom = enemy.getY() + enemy.getPreferredSize().height;

        // checks if the projectile is overlapping the enemy
        if (projectile.getX() >= enemy.getX()
                && projectile.getX() <= enemyRight
                && projectile.getY() >= enemy.getY()
                && projectile.getY() <= enemyBottom) {


            // enemy is made invisible
            enemy.setVisible(false);
            
            // true is returned because enemy is dead
            return true;
        } else {
            // false is returned because enemy is alive
            return false;
        }

    }
}
