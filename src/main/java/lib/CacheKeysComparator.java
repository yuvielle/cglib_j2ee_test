package lib;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Yuvielle
 * Date: 10.06.12
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class CacheKeysComparator implements Comparator {
    Map base;


    public CacheKeysComparator(Map base) {
        this.base = base;
    }


    @Override
    public int compare(Object o1, Object o2) {
        if((Integer)base.get(o1) < (Integer)base.get(o2)) {
            return 1;
        } else if((Integer)base.get(o1) == (Integer)base.get(o2)) {
            return 1;
        } else {
            return -1;
        }
    }

}
