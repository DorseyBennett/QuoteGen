package QuoteGen;

public class PartsOfSpeech {
	
	public static final int DirectObject = 0;
	
	public static final int StandAlone = 1;
	
	public static final int Prepositional = 2;

	public static String[][] Verbs = {{"Believe", "Control", "Are", "Climb", "Run", "Take", "Leave", "Have"}, 	//Direct object verbs

			{"Run", "Fly", "Walk", "Climb", "Leave"},					//Standalone verbs

			{"Say", "Think", "Believe", "Hate", "Realize", "Deny"}};			//Prepositional verbs

	public static String[][] nouns = {{"You", "People", "They", "Hearts", "Brothers", "Family", "Sisters"}};

	public static String[] negations = {"Never", "Always", "Sometimes"};

	public static String[] prepositions = {"In", "On", "Outside of", "Inside of", "Around", "Beside", "Towards", "Away from", "Without", "With", "Above"};

	public static String[] places = {"The heart", "Hope", "Love", "Life", "Happiness", "Friendship"};
	
	public static String[] conjunctions = {"And", "Or", "But"};


}