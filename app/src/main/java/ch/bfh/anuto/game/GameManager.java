package ch.bfh.anuto.game;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.bfh.anuto.game.objects.Tower;
import ch.bfh.anuto.util.math.Vector2;

public class GameManager {

    /*
    ------ Listener Interface ------
     */

    public interface Listener {
        void onTick();
    }

    /*
    ------ Members ------
     */

    private Tower mSelectedTower;

    private final GameEngine mGame;

    private final List<Listener> mListeners = new CopyOnWriteArrayList<>();

    /*
    ------ Constructors ------
     */

    public GameManager(GameEngine game) {
        mGame = game;
    }

    /*
    ------ Methods ------
     */

    public void selectTower(float x, float y) {
        Vector2 pos = mGame.getGameCoordinate(x, y);

        Tower closest = (Tower)mGame.getGameObjects(Tower.TYPE_ID)
                .min(GameObject.distanceTo(pos));

        if (closest != null && closest.getDistanceTo(pos) < 0.5f) {
            selectTower(closest);
        } else {
            selectTower(null);
        }
    }

    public void selectTower(Tower tower) {
        if (mSelectedTower != null) {
            mSelectedTower.hideRange();
        }

        mSelectedTower = tower;

        if (mSelectedTower != null) {
            mSelectedTower.showRange();
        }
    }

    /*
    ------ Listener Stuff ------
     */

    public void addListener(Listener listener) {
        mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        mListeners.remove(listener);
    }

    private void onTick() {
        for (Listener l : mListeners) {
            l.onTick();
        }
    }
}
