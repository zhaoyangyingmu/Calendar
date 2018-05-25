package todoitem;

import java.util.HashMap;
import java.util.Map;

public interface AttributeMap {
    HashMap<String, Object> getAttrs();

    Object getValue(String key);

    void addAttr(String key, Object value);

    void removeAttr(String key);


    void addAttrs(Map<String, Object> attrs);
}
