/**
 * Main.java
 * 
 * Version 1.0
 * 
 */

package question3;

import java.util.LinkedList;
import java.util.List;

/**
 *  
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Swapnil Kamat(snk6855@g.rit.edu)
 *
 */
public class Main {

	/**
	 * starts the production of candies
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> wrapper = new LinkedList<String>();
		List<String> candy = new LinkedList<String>();
		List<String> wrapcandy = new LinkedList<String>();
		
		new Thread(new CandyProducer(candy)).start();
		new Thread(new CandyWrapperProducer(wrapper)).start();
		new Thread(new CandyConsumer(candy,wrapper,wrapcandy)).start();
		new Thread(new CandyBoxProducer(wrapcandy)).start();
		
	}
}
