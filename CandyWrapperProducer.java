/**
 * CandyWrapperProducer.java
 * 
 * Version 1.0
 * 
 */

package question3;

import java.util.List;

/**
 *  Produces 3 wrappers for the production and puts it on the wrapper queue 
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Swapnil Kamat(snk6855@g.rit.edu)
 *
 */
public class CandyWrapperProducer implements Runnable{

	private List<String> wrapper;
	
	CandyWrapperProducer(List<String> wrapper){
		
		this.wrapper = wrapper;
	}
	
	/**
	 * Adds the wrapper to the queue
	 * 
	 * @param job: the wrapper 
	 */
	public void makeWork(String job) {
		synchronized(wrapper) {
			
			wrapper.add(job);
			
			wrapper.notifyAll();
		}
	}

	/**
	 * 
	 * produces 3 wrappers at a given point of time
 	 * 
	 */
	public void run() {
		int j = 0;
		
		while(j<90){
			for (int i = 0; i < 3; i++) {
			
				makeWork("wrapper"+(j+i));
			}
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			j += 3;
		}
	}
}
