package QuoteGen;

import java.util.concurrent.DelayQueue;

public class Test {
	
	public static void main(String[] args) {
//		
		while(true)
		{
			try {
				String quote = SentenceGenerator.generateSentence();
				while(quote.length()>140)
				{
					 quote = SentenceGenerator.generateSentence();
				}
				tweetFunctions.tweet(quote);
				Thread.sleep(1450000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			
		}
	}
	
}
