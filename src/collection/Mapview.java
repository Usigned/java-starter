package collection;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mapview {
    static <K, V> boolean validate(Map<K, V> attrMap, Set<K> requiredAttrs, Set<K> permittedAttr) {
        boolean valid = true;
        Set<K> keys = attrMap.keySet();
        if (!keys.containsAll(requiredAttrs)) {
            Set<K> missing = new HashSet<>(requiredAttrs);
            missing.removeAll(keys);
            System.out.println("missing required keys: " + missing);
            valid = false;
        }
        if (!permittedAttr.containsAll(keys)) {
            Set<K> unpermitted = new HashSet<>(keys);
            unpermitted.removeAll(permittedAttr);
            System.out.println("unpermitted keys: " + unpermitted);
            valid = false;
        }
        return valid;
    }
}
