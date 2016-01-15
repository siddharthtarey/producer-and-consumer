/**
 * CandyProducer.java
 * 
 * Version 1.0
 */
package question3;

import java.util.List;

/**
 * Produces candy and puts it on the candy queue
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Swapnil Kamat(snk6855@g.rit.edu)
 *
 */
public class CandyProducer implements Runnable {

	private List<String> candy;

	
	CandyProducer(List candy){
		
		this.candy =candy;
	}
	/**
	 * adds candy to the candy queue
	 * @param job: the candy that has to be added to the queue
	 */
	public void makeWork(String job) {
		synchronized(candy) {
			
			candy.add(job);
			candy.notifyAll();
		}
	}

	/**
	 * 
	 * creates 1 candy at a time and puts it on a queue
	 * 
	 */
	public void run() {
	int i = 0;
		while(i<90){
			
			makeWork("candy"+i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			i++;
		}
	}

}
