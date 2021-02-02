package souchon.game.collisionner;

import souchon.game.entity.Base;
import souchon.game.entity.Enemy;
import souchon.game.entity.Tower;
import souchon.game.view.EnemyView;
import souchon.game.view.TowerView;

import javax.swing.text.html.ImageView;
import java.io.Serializable;

/**
 * Class to make collide a {@link Enemy} in a {@link Tower} range
 */
public class Collisionner {

    public boolean inRadiusTower(Enemy e, Tower t) {
        return t.isInRange(e) && t.isReadyToShoot();
    }
}
