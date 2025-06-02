package Cache;

import java.util.HashMap;

public class Cache {
    private static final HashMap<String,Object> cache=new HashMap<>();

    public static void put(String key, Object value){
        cache.put(key,value);
    }

    public static Object get(String key){
        return cache.get(key);
    }

    public static boolean contains(String key){
        return cache.containsKey(key);
    }

    public static void clear(){
        cache.clear();
    }
}
