package test;
import java.util.HashSet;

public class CacheManager {
	HashSet<String> hashSet;
	int maxSize;
    CacheReplacementPolicy policy;

    public CacheManager(int size, CacheReplacementPolicy plc) {
        maxSize = size;
        policy = plc;
    }
    void add(String word){
        if (hashSet.size() == maxSize){
            hashSet.remove(policy.remove());
            policy.add(word);
            hashSet.add(word);
        }else {
            policy.add(word);
            hashSet.add(word);
        }
    }
    boolean query(String word){
        if (hashSet.contains(word)){
            return true;
        }
        return false;
    }
}
