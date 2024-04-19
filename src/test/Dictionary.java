package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    public CacheManager lru;
    public CacheManager lfu;
    public BloomFilter blo;
    public String[] files;
    public Dictionary(String... fileNames)  {
         this.lru=new CacheManager(400, new LRU());
         this.lfu=new CacheManager(100, new LFU());
         this.blo= new BloomFilter(256,"MD5","SHA1");
         this.files = new String[fileNames.length];
         int i = 0;
        for(String file:fileNames) {
            try{
                this.files[i] = file;
                i+=1;
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    String[] words = line.split(" ");
                    for(String word : words){
                        blo.add(word);
                    }
                    line = reader.readLine();
                }
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean query(String word){
        if(lru.set.contains(word)){
            return true;
        }
        if(lfu.set.contains(word)){
            return false;
        }
        else{
            if(blo.contains(word)){
                lru.add(word);
                return true;
            }
            else{
                lfu.add(word);
                return false;
            }
        }
    }
    public boolean challenge(String word){
        if(IOSearcher.search(word,files)){
            lru.add(word);
            return true;
        }
        else{
            lfu.add(word);
            return false;
        }
    }
}
