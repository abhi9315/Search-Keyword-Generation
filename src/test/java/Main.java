import model.Extractor;
import utility.Trie;

/**
 * Created by rajarshee on 16/7/15.
 */
public class Main {

    public static void main(String[] args) {
        String input = "find all people living in France and loves programming";
        Extractor extractor = new Extractor();
        Trie dict = new Trie();
        dict.add("programming");
        dict.add("singing");
        extractor.extract(dict,input);


    }


}
