package model;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import utility.Trie;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Extractor {


    private List<String> getLocations(String input[]) {
        List<String> locations = new ArrayList<String>();
        try(InputStream inputStream = new FileInputStream("en-ner-location.bin")) {
            TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
            inputStream.close();
            NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);
            Span[] span = nameFinderME.find(input);

            for(Span s : span) {
                String temp="";
                for(int i = s.getStart(); i < s.getEnd(); i++)
                    temp+=input[i]+" ";
                temp=temp.trim();
                locations.add(temp);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return locations;
    }

    public void extract(Trie dict, String input) {
        String[] input_splitted = input.split(" ");
        System.out.println("Interests : ");
        for (String token : input_splitted) {
            if (token.equals(token.toLowerCase()) && dict.contains(token))
                System.out.println(token);
        }
        System.out.println("Locations : ");
        List<String> locations = getLocations(input_splitted);
        System.out.println(locations);
    }

}
