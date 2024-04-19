package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    public BitSet bits;
    public int numHashFunctions;

    public MessageDigest[] myfuncs;

    public BloomFilter(int size, String... algorithms) {
        bits = new BitSet(size);
        numHashFunctions = algorithms.length;
        myfuncs = new MessageDigest[numHashFunctions];
        for(int i = 0 ; i<numHashFunctions;i++){
            try {
                this.myfuncs[i] = MessageDigest.getInstance(algorithms[i]);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void add(String word) {
        for (int i = 0; i < numHashFunctions; i++) {;
            byte[] hashBytes = myfuncs[i].digest(word.getBytes());
            BigInteger hashInt = new BigInteger(hashBytes);
            int hash = Math.abs(hashInt.intValue() % bits.size());
            bits.set(hash,true);
    }
    }

    public boolean contains(String word) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hashIndex = getHashIndex(word, i);
            if (!bits.get(hashIndex)) {
                return false;
            }
        }
        return true;
    }

    private int getHashIndex (String word,int functionIndex){
        myfuncs[functionIndex].update(word.getBytes());
        byte[] hashBytes = myfuncs[functionIndex].digest();
        BigInteger hashInt = new BigInteger(1, hashBytes);
        return Math.abs(hashInt.intValue() % bits.size());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            sb.append(bits.get(i) ? '1' : '0');
        }
        return sb.toString();
    }

}
