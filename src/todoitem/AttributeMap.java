package todoitem;

import java.util.HashMap;
import java.util.Map;

public interface AttributeMap {
    HashMap<String, String> getAttrs();

    String getValue(String key);

    void addAttr(String key, String value);

    void removeAttr(String key);


    void addAttrs(Map<String, String> attrs);
}
