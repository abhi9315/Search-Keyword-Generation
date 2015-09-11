package model;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import utility.Trie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Extractor {

    private String[] in;

    private List<String> getLocations() {
        List<String> locations = new ArrayList<String>();
        try (InputStream inputStream = getClass().getResourceAsStream("/en-ner-location.bin")) {
            TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
            inputStream.close();
            NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);
            Span[] span = nameFinderME.find(in);

            for (Span s : span) {
                String temp = "";
                for (int i = s.getStart(); i < s.getEnd(); i++)
                    temp += in[i] + " ";
                temp = temp.trim();
                locations.add(temp);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return locations;
    }

    public void extract(Trie dict, String input) {
        in = input.split(" ");
        System.out.println("Interests : ");
        for (String token : in) {
            if (token.equals(token.toLowerCase()) && dict.contains(token))
                System.out.println(token);
        }
        System.out.println("Locations : ");
        List<String> locations = getLocations();
        System.out.println(locations);
//        String[][] pos = getPosTag();
//        for (String[] p : pos) {
//            System.out.print(p[0] + "_" + p[1] + " ");
//        }
        System.out.println(model.Parser.parse(in));
    }

    private String[][] getPosTag() {
        String[][] pos = new String[in.length][2];
        Tokenizer _tokenizer = null;

        InputStream modelIn = null;
        try {
            // Loading tokenizer model
            modelIn = getClass().getResourceAsStream("/en-pos-maxent.bin");
            final POSModel posModel = new POSModel(modelIn);
            modelIn.close();

            POSTaggerME posTagger = new POSTaggerME(posModel);
            String[] tagged = posTagger.tag(in);
            for (int i = 0; i < in.length; i++) {
                pos[i][0] = in[i];
                pos[i][1] = tagged[i];
            }

        } catch (final IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (final IOException e) {
                } // oh well!
            }
            return pos;
        }

    }

    private void parse() {
        try (InputStream is = getClass().getResourceAsStream("/en-parser-chunking.bin")) {

            ParserModel model = new ParserModel(is);

            Parser parser = ParserFactory.create(model);
            Parse topParses[] = ParserTool.parseLine(String.join(" ", in), parser, 1);


            for (Parse p : topParses)
                p.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
