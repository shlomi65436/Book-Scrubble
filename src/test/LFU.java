package test;
import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private final LinkedHashMap<String, String> mylist;
    private final LinkedHashMap<String, Integer> freq;
    public LFU() {
        this.mylist = new LinkedHashMap<>();
        this.freq = new LinkedHashMap<>();
    }

    @Override
    public void add(String word) {
        if (mylist.containsKey(word)) {
            freq.put(word, freq.get(word) + 1);
        }
        else {
            mylist.put(word, word);
            freq.put(word, 1);
        }
    }
    @Override
    public String remove() {
        if (mylist.isEmpty()) {
            return null;
        }
        String leastFreqWord = findLeastFreqWord();
        mylist.remove(leastFreqWord);
        freq.remove(leastFreqWord);
        return leastFreqWord;
    }

    private String findLeastFreqWord() {
        int minFreq = Integer.MAX_VALUE;
        String leastFreqWord = null;
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() < minFreq) {
                minFreq = entry.getValue();
                leastFreqWord = entry.getKey();
            }
        }
        return leastFreqWord;
    }
}
