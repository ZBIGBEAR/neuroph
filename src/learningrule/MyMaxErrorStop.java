package learningrule;

import org.neuroph.core.learning.stop.MaxErrorStop;
import org.neuroph.core.learning.stop.StopCondition;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/31 23:48
 */
public class MyMaxErrorStop implements StopCondition {
    @Override
    public boolean isReached() {
        return false;
    }
}
