package test2;


import java.util.HashSet;

public class CacheManager {
	public HashSet<String> set;
    public CacheReplacementPolicy crp;
    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.set = new HashSet<>(size);
        this.crp = crp;
    }
    public boolean query(Word wod){
        
    }
}
