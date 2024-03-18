package test;

import java.util.Objects;

public class Tile {
    public final char letter;
    public final int score;
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Tile)) return false;
        if (!super.equals(object)) return false;
        Tile tile = (Tile) object;
        return letter == tile.letter && score == tile.score;
    }
    public int hashCode() {
        return Objects.hash(super.hashCode(), letter, score);
    }
    public static class Bag
    {
       private static Bag bag = null;
       int bagSize = 98;
       int[] maxLetterQuan = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
       int[] letterQuan = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
       char[] letterType = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
       int[] letterScore = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
       Tile[] tiles = new Tile[26];
        private Bag() {
            for(int i =0;i<26;i++){
                tiles[i] = new Tile(letterType[i],letterScore[i]);
            }
        }
        public static Bag getBag(){
            if (bag == null) {
                bag = new Bag();
            }
            return bag;
        }
        public Tile getRand()
       {
           int i = 0;
           for(i=0;i<26;i++)
           {
               if(letterQuan[i]>0){
                   break;}
           }
           if (i == 26)
           {
               return null;
           }
           i = -1;
           java.util.Random num = new java.util.Random();
           while( i < 0 || (letterQuan[i] == 0))
           {
               i = num.nextInt(26);
           }
           letterQuan[i] -= 1;
           bagSize -= 1;
           return tiles[i];
       }
       public Tile getTile(char let)
       {
           int i = 0;
           for(i=0;i<26;i++){
               if (letterType[i] == let){
                   if (letterQuan[i] > 0){
                       letterQuan[i] -= 1;
                       bagSize -= 1;
                       return tiles[i];
                   }
               }
           }
           return null;
       }
       public void put(Tile let)
       {
           int i = 0;
           for(i=0;i<26;i++) {
               if (letterType[i] == let.letter) {
                   if (letterQuan[i] < maxLetterQuan[i]) {
                       letterQuan[i] += 1;
                       bagSize += 1;
                   }
               }
           }
       }
       public int size()
       {
           return bagSize;
       }
       int[] getQuantities(){
           return letterQuan.clone();
       }
    }
}
