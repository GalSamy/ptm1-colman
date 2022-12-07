package test;
import java.util.*;

public class LRU implements CacheReplacementPolicy{
    Map<String,Integer> usedQueue;
    public LRU(){
        usedQueue = new HashMap<String,Integer>();
        }

    public void add(String word){
                usedQueue.put(word, 0);
                usedQueue.forEach((k, v) -> usedQueue.put(k,usedQueue.get(k) + 1));
    }

    public String remove() {

        final int[] max = {0};
        final String[] maxString = {""};
        usedQueue.forEach((k,v) ->{
            if(v >= max[0]){
                max[0] = v;
                maxString[0] = k;
            }
        });
        return maxString[0];
    }
}
