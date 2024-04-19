package test;
import java.util.LinkedHashMap;


public class LRU implements CacheReplacementPolicy {
    public LinkedHashMap<String,String> mylist;
    public LRU(){
        this.mylist = new LinkedHashMap<>();
    }

    @Override
    public void add(String word) {
        if(mylist.containsKey(word)){
            mylist.remove(word);
            mylist.put(word,word);
        }
        else {
            mylist.put(word, word);
        }
    }

    @Override
    public String remove() {
        if (mylist.isEmpty()) {
            return null;
        }
        // Retrieve and remove the least recently used item
        String lruKey = mylist.keySet().iterator().next();
        mylist.remove(lruKey);
        return lruKey;
    }
}
