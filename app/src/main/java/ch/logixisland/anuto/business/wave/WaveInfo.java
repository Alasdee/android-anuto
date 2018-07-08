package ch.logixisland.anuto.business.wave;

import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.logixisland.anuto.util.container.KeyValueStore;

@Root
public class WaveInfo {

    private final int mWaveReward;
    private final int mExtend;
    private final int mMaxExtend;
    private final List<EnemyInfo> mEnemies;

    public WaveInfo(KeyValueStore data) {
        mWaveReward = data.getInt("waveReward");
        mExtend = data.getInt("extend");
        mMaxExtend = data.getInt("maxExtend");

        mEnemies = new ArrayList<>();
        for (KeyValueStore enemyData : data.getStoreList("enemies")) {
            mEnemies.add(new EnemyInfo(enemyData));
        }
    }

    public List<EnemyInfo> getEnemies() {
        return Collections.unmodifiableList(mEnemies);
    }

    public int getExtend() {
        return mExtend;
    }

    public int getMaxExtend() {
        return mMaxExtend;
    }

    public int getWaveReward() {
        return mWaveReward;
    }

}
