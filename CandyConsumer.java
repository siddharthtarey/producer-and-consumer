/**
 * CandyConsumer.java
 * 
 * Version 1.0
 */

package question3;

import java.util.LinkedList;
import java.util.List;
/**
 * This class consumes the candy and wrapper to produce a wrapped candy for the CandyBoxProducer class
 * 
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Swapnil Kamat(snk6855@g.rit.edu)
 */
public class CandyConsumer implements Runnable {
	private List<String> wrapper;
	private List<String> candy;
	volatile int index = 0;
	private List<String> wrapcandy;

	CandyConsumer(List<String> candy, List<String> wrapper, List<String> wrapcandy) {

		this.candy = candy;
		this.wrapper = wrapper;
		this.wrapcandy = wrapcandy;
	}

	/**
	 * consumes a candy and wrappers and produces a wrapped candy and passes it to box the candy
	 */
	public void run() {
		LinkedList<String> mycandy = new LinkedList<String>();
		LinkedList<String> mywrapper = new LinkedList<String>();
		int candycount =0;
		int wrappercount = 0;
		
		while(true){
			
			/*
			 * stores the candy in a temp string array mycandy 	
			 * 
			 */
		synchronized(candy){
			candy.notify();
			
			while(candy.size() < 5){
				
				try {
					candy.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 4; i++) {
				
				mycandy.add(candy.remove(0));
			}
			
			
		}
		
		/*
		 * stores the wrapper in a temp string array mywrapper 	
		 * 
		 */
		synchronized(wrapper){
			wrapper.notify();
			while(wrapper.size() < 5){
				
				try {
					wrapper.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 0; i < 4; i++) {
				
				mywrapper.add(wrapper.remove(0));
			}
		}
		
		/*
		 * wraps the candy and sends it to boxing to the CandyBoxProducer thread
		 */
		synchronized(wrapcandy){
			wrapcandy.notify();
			//System.out.println("here"+mycandy.size()+"and"+mywrapper.size());
			
			if(mycandy.size() >= 4 && mywrapper.size() >= 4){
				//System.out.println("here");
				for (int i = 0; i < 4; i++) {
						
					wrapcandy.add(mycandy.remove(0) + mywrapper.remove(0));
				}
			}
			
		}
		}
	}
}
