/**
@author Jeremy McCulloch
@version 1.0
**/
package QuoteGen;
import java.util.ArrayList;
/** Class used to generate sentences, only used statically */
public class SentenceGenerator {
	
	private static ArrayList<Word> listOfWords;
	public static void setupListOfWords() {
		listOfWords = new ArrayList<Word>();
	}
	/**
	 * Generates inspirational sentences, currently only simple (1 independent clause)
	 * @return Formatted string containing the newly generated sentence
	 */
	public static String generateSentence() { 
		ArrayList<Word> sentence = new ArrayList<Word>();
		// only simple, present tense sentences
		boolean plural = appendIndependentClause(sentence);
		String res = "";
		//assume its present, positive for now
		boolean present = true;
		boolean negative = false;
		for (Word e : sentence) {
			if (e.getPartOfSpeech() == Word.verb) {//verbs need special treatment
				if (negative) {
					res += present ? (plural ? "do not " : "does not ") : "did not ";
					res += e.getWord(true) + " ";
				} else {
					Verb v = (Verb) e;
					res += v.getWord(plural, present) + " ";
				}
			} else {
				res += e.getWord(plural) + " ";
			}
		}
		//capitalization
		String firstChar = res.substring(0, 1);
		String restOfStr = res.substring(1, res.length()-1);//not include last space
		firstChar = firstChar.toUpperCase();
		restOfStr = restOfStr.toLowerCase();
		
		return firstChar + restOfStr + ".";
	}
	/**
	 * Appends an independent clause to end of given sentence
	 * @param sentence ArrayList of words to append independent clause to
	 * @return Boolean that indicated whether the subject of the sentence is plural 
	 * (true = plural, false = singular)
	 */
	private static boolean appendIndependentClause (ArrayList<Word> sentence) {
		//form = Subject Predicate (DO) (Prep phrase)
		boolean plural = appendDescriptiveNoun(sentence);//whether plural depends on subj.
		Verb predicate = (Verb) getWordOfType(Word.verb,sentence.get(sentence.size()-1));
			//get verb related to subj.
		sentence.add(predicate);
		if (predicate.isTransitive()) {//if transitive add DO
			appendDescriptiveNoun(sentence);
		}
		if (predicate.isUsedWithPrepPhrase()) {//add prep phrase if necessary
			appendPrepositionalPhrase(sentence);
		}
		return plural;
	}
	/**
	 * Appends prepositional phrase to end of given sentence
	 * @param sentence ArrayList of words to append prepositional phrase to
	 */
	private static void appendPrepositionalPhrase (ArrayList<Word> sentence) {
		sentence.add(getWordOfType(Word.preposition));
		appendDescriptiveNoun(sentence);
	}
	/**
	 * Appends noun, article (if needed), and related adjective to end of given sentence
	 * @param sentence ArrayList of words to append words to
	 */
	private static boolean appendDescriptiveNoun (ArrayList<Word> sentence) {
		Word lastWord = null;
		if (sentence.size()>0) {//if sentence is not empty, get noun related to last word
			lastWord = sentence.get(sentence.size()-1);
		}
		Noun noun = (Noun) getWordOfType(Word.noun, lastWord);
		if (noun.isUsedWithArticle()) {
			sentence.add(getWordOfType(Word.article));
		}
		sentence.add(getWordOfType(Word.adjective, noun));
		sentence.add(noun);
		return Math.random()>0.5;//needs to be fixed for words like "happiness"
	}
	/**
	 * Alias of getWordOfType that does not require related word (defaults to null)
	 */
	private static Word getWordOfType (int type) {
		return getWordOfType(type, null);
	}
	/**
	 * Used to get a word of a particular part of speech that is related to another
	 * @param type integer representing part of speech
	 * @param related word that return value should be similar to (null gives random word)
	 * @return word of specified type similar to specified word
	 */
	private static Word getWordOfType (int type, Word related) {
		//will be updated to use markov or related
		ArrayList<Word> temp = new ArrayList<Word>();
		for (Word e : listOfWords)
			if (e.getPartOfSpeech() == type)
				temp.add(e);
		int index = (int) (Math.random() * temp.size());
		return temp.get(index);
	}
}
