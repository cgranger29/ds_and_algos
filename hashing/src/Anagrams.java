import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagrams {
    final Integer[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;
    Long keyToReturn = null;

    public Anagrams(){
        this.buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    private void buildLetterTable(){
        // intialize starting ascii char for 'a' which is 97
        // cast to char
        // map casted char to prime value
        // repeat for len of primes
        this.letterTable = new HashMap<Character, Integer>();
        int asciiCode = 97;
        for(Integer num: this.primes){
            Character asciiChar = (char)asciiCode;
            this.letterTable.put(asciiChar, num);
            asciiCode += 1;
        }
    }

    private void addWord(String s){
        //implement
        Long hashCode = this.myHashCode(s);
        if(this.anagramTable.get(hashCode) == null){
            this.anagramTable.put(hashCode, new ArrayList<>());
        }

        this.anagramTable.get(hashCode).add(s);
    }

    private Long myHashCode(String s){
        // initialize hashCode to 0
        // first char mapping will set hashCode to its value retrieved from letterTable
        // every char after will multiply current hascode by its prime mapping from letterTable
        Long hashCode = null;
        for(int i = 0; i < s.length(); i++){
            Integer charMapping = letterTable.get(s.charAt(i));
            if(hashCode == null){
                hashCode = charMapping.longValue();
            } else{
                hashCode *= charMapping.longValue();
            }
        }
    return hashCode;
        
    }

    private void processFile(String s) throws IOException{
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while((strLine = br.readLine()) != null){
            this.addWord(strLine);
        }
            br.close();
    }

    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
        //implement
        ArrayList<Map.Entry<Long, ArrayList<String>>> ans = new ArrayList<>();
        int maxLength = 0;

        for(Map.Entry<Long, ArrayList<String>> pair: this.anagramTable.entrySet()){
            if(pair.getValue().size() == maxLength){
                ans.add(pair);
            } else if(pair.getValue().size() > maxLength){
                maxLength = pair.getValue().size();
                ans = new ArrayList<>();
                ans.add(pair);
                this.keyToReturn = pair.getKey();

            }
        }
        return ans;
    }

    public static void main(String[] args){

        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();

        try{
            a.processFile("words_alpha.txt");
        } catch(IOException e1){
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1000000000);
               
        System.out.println("Elapsed Time: " + seconds);
        System.out.println("Key of max anagrams: " + a.keyToReturn);
        System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
        System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());

    }
}
