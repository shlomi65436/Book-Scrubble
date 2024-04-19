package test;


import java.util.HashSet;

public class CacheManager {
	public HashSet<String> set;
    public CacheReplacementPolicy crp;
    public int cap;
    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.set = new HashSet<>(size);
        this.crp = crp;
        this.cap = size;
    }
    public boolean query(String wod){
        if(set.contains(wod)){
            return true;
        }
        return false;
    }
    public void add(String wod){
        if(set.contains(wod)){
            crp.add(wod);
        }
        else{
            if(set.size() >= cap) {
                set.remove(crp.remove());
                set.add(wod);
                crp.add(wod);
            }
            else{
                set.add(wod);
                crp.add(wod);
            }
        }
    }
}
