package football.utils;

import java.util.HashMap;

public class MapUtil {
    public static HashMap <String, String> getMap(String line) {
        HashMap<String, String> values = new HashMap<>();
        String items[] = line.split(";");
        for (String entry: items) {
            String[] entryData = entry.split("=");
            values.put(entryData[0], entryData[1]);
        }
        return values;
    }
}
