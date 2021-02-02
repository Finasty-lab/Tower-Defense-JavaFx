package souchon.game.view;

import javafx.scene.control.ProgressBar;
import souchon.game.entity.Base;
import souchon.game.entity.Enemy;
import souchon.game.entity.Game;

/**
 * Class to represent the view of a hpBar for {@link Enemy} and {@link Base}
 */
public class HpBarView {

    private ProgressBar bar;

    public ProgressBar getBar() { return bar; }

    public HpBarView(Enemy e) {
        ProgressBar hpBar = new ProgressBar(0);
        hpBar.setPrefWidth(20);
        hpBar.setPrefHeight(9);
        hpBar.setLayoutY(e.getY() + 18);
        hpBar.setLayoutX(e.getX());

        double actualHp = e.getHp();
        double valueProgressBar = actualHp / e.getMaxHp();
        hpBar.setProgress(hpBar.getProgress() + valueProgressBar + 0.2);
        this.bar=hpBar;
    }

    public HpBarView(Game g,Base b) {
        ProgressBar hpBar = new ProgressBar(0);
        switch (g.getIdGame()) {
            case 1:
                hpBar.setPrefWidth(80);
                hpBar.setPrefHeight(15);
                hpBar.setLayoutY(240);
                hpBar.setLayoutX(518);
                break;
            case 2 :
                hpBar.setPrefWidth(80);
                hpBar.setPrefHeight(15);
                hpBar.setLayoutY(120);
                hpBar.setLayoutX(50);
                break;
            case 3 :
                hpBar.setPrefWidth(80);
                hpBar.setPrefHeight(15);
                hpBar.setLayoutY(305);
                hpBar.setLayoutX(480);
                break;
        }
        double actualHp = b.getActualHp();
        double valueProgressBar = actualHp / b.getMaxHp();
        hpBar.setProgress(hpBar.getProgress() + valueProgressBar);
        this.bar=hpBar;
    }


}
