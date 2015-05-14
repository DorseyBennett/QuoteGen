package QuoteGen;

public class PartsOfSpeech {
	
	public static final int DirectObject = 0;
	
	public static final int StandAlone = 1;
	
	public static final int Prepositional = 2;

	public static String[][] verbs = {{"Believe", "Control", "Are", "Climb", "Run", "Take", "Leave", "Have"}, 	//Transitive verbs

			{"Run", "Fly", "Walk", "Climb", "Leave"},					//Intransitive verbs

			{"Say", "Think", "Believe", "Hate", "Realize", "Deny"}};			//Prepositional verbs

	public static String[][] nouns = {{"You", "People", "They", "Some", "Souls", "Hearts", "Friends", "Brothers", "Sisters"}};

	public static String[] adverbs = {"Never", "Always", "Sometimes"};

	public static String[] prepositions = {"In", "On", "Outside of", "Inside of", "Besides", "Towards", "Away from", "Without", "With", "Above", "Below", "Out of"};

	public static String[] places = {"Fear", "Hope", "The heart", "The soul", "Life", "Happiness", "Joy", "Love", "Friendship", };
	
	public static String[] conjunctions = {"And", "Or", "But"};


}
