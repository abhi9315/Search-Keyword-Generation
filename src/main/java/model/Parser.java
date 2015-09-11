package model;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.shiftreduce.ShiftReduceParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;

public class Parser {
    final static String modelPath = "englishSR.ser.gz";
    final static String taggerPath = "english-left3words-distsim.tagger";

    public static String parse(String[] in) {

        String text = String.join(" ", in);

        MaxentTagger tagger = new MaxentTagger(taggerPath);
        ShiftReduceParser model = ShiftReduceParser.loadModel(modelPath);

        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        List<HasWord> sentence = tokenizer.iterator().next();
        List<TaggedWord> tagged = tagger.tagSentence(sentence);
        Tree tree = model.apply(tagged);
        return tree.toString();
    }
}