/**
 * CandyBoxProducer.java
 * 
 * Version 1.0
 * 
 */
package question3;

import java.util.List;
/**
 * This class produces candy boxes and puts 4 candies in a box
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Swapnil Kamat (snk6855@g.rit.edu)
 *
 */

public class CandyBoxProducer implements Runnable {

	private List<String> wrapcandy;

	CandyBoxProducer(List<String> wrapcandy) {

		this.wrapcandy = wrapcandy;
	}

	public void run() {
		int box = 0;
		/*
		 * puts 4 wrap candies in a box, this is done for 20 boxes.
		 * 
		 */
		synchronized (wrapcandy) {
			while (box < 20) {
				//waits if the wrapcandy queue is empty
				while (wrapcandy.size() < 5) {
					try {
						wrapcandy.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Box "+box+":");
				//puts 4 candies in a box
				for (int j = 0; j < 4; j++) {
					
					System.out.println(wrapcandy.remove(j));

				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				box++;
				wrapcandy.notifyAll();
			}
		}
		// exits the program if 20 boxes are filled.
		if( box == 20){
			
			System.exit(0);
		}
	
	}

}
