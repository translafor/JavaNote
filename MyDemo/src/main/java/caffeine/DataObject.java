package caffeine;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @description: 缓存类
 * @author: frank.wu
 * @create: 2021-10-09
 */
@Data
public class DataObject {
    private final String data;

    private static int objectCounter = 0;

    public DataObject(String data) {
this.data = data;
    }
    // standard constructors/getters

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.remove("s");
    }
}
