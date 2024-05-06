package test;

import java.util.HashMap;

public class DictionaryManager {
    private static DictionaryManager instance = null;
    private HashMap<String,Dictionary> myMap = new HashMap<>();
    public boolean shlomi = false;
    public String myWord;
    public int index;
    private DictionaryManager(){}
    public boolean query(String... args){
        index =0;
        for(String file:args){
            if(index == args.length - 1){
                myWord = file;
                break;
            }
            if(!myMap.containsKey(file)){
                myMap.put(file,new Dictionary(file));
            }
            index++;
        }
        shlomi = false;
        myMap.forEach((key,value)->{
            if(value.query(myWord)){
                shlomi = true;
            }
        });
        return shlomi;
    }
    public boolean challenge(String... args){
        index =0;
        for(String file:args){
            if(index == args.length - 1){
                myWord = file;
                break;
            }
            if(!myMap.containsKey(file)){
                myMap.put(file,new Dictionary(file));
            }
            index++;
        }
        shlomi = false;
        myMap.forEach((key,value)->{
            if(value.challenge(myWord)){
                shlomi = true;
            }
        });
        return shlomi;
    }
    public static int getSize(){
        if(instance == null){
            return 0;
        }
        return instance.myMap.size();
    }
    public static DictionaryManager get(){
        if(instance == null){
            instance = new DictionaryManager();
        }
       return instance;
    }
}