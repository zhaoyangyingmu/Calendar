package todoitem;

import java.util.HashMap;
import java.util.Map;

public interface AttributeMap {
    HashMap<String, String> attrsMap = new HashMap<>();

    default HashMap<String, String> getAttrs() {
        return attrsMap;
    }

    default String getValue(String key) {
        if (attrsMap.containsKey(key))
            return attrsMap.get(key);
        return null;
    }

    default void addAttr(String key, String value) {
        if (attrsMap.containsKey(key))
            attrsMap.replace(key, value);
        else
            attrsMap.put(key, value);
    }

    default void removeAttr(String key) {
        if (attrsMap.containsKey(key))
            attrsMap.remove(key);
    }


    default void addAttrs(Map<String, String> attrs) {
        attrsMap.putAll(attrs);
    }
}
