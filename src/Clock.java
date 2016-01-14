/*
 * Justin Espejo
 * CS344
 * Project 1 - Monitors
 */

public class Clock implements Runnable {
	public static final long startTime = System.currentTimeMillis(); //starts off the time
	private String name = "Clock";

	private Monitor h;
	public int showNumber=0;
	public boolean showtimeOver = false;

	Clock(Monitor h){
		this.h = h;
		new Thread(this).start();

	}

	@Override
	public void run() 
	{
		//		Parades: 11:00AM, 12:00PM, 1:00PM, 2:00PM, 3:00PM, 4:00PM, 5:00PM
		//		Show times: 11:45AM, 1:15PM, 2:45PM
		display("It's only 10:00am, Parade will start at 11:00 am.");
		nap(6000);  //11am
		display("Students can start looking for groups and parade!");
		h.paradeStart();
		//		
		nap(6000); //11:45 am
		display("let the show begin!");
		h.startShow();
		showNumber++;

		nap(1500); //12:00 pm
		display("Parade will start again.");
		h.paradeStart();

		nap(6000); //1:00pm
		display("Parade will start again.");
		h.paradeStart();

		nap(1500); //1:15pm
		display("let the show begin again!");
		h.startShow();
		showNumber++;

		nap(4500); //2:00 pm
		display("Parade will start again.");
		h.paradeStart();

		nap(4500); //2:45pm
		display("let the show begin again!");
		h.startShow();
		showNumber++;

		nap(1500); //3:00pm
		display("Parade will start again.");
		h.paradeStart();

		nap(6000); //4:00pm
		display("Parade will start again.");
		h.paradeStart();

		nap(6000); //5:00pm
		display("Parade will start again.");
		h.paradeStart();

		display("Everything is done show is over!");
		showtimeOver = true;
		h.endtime();
		nap(10000);
		h.endParade();


	}



	/**
	 * will be used for the thread to sleep
	 * 
	 * @param millis
	 */
	private void nap(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Does a subtraction between the time that the store opened to present.
	 * 
	 * @return The current time
	 * 
	 */
	protected static final long age() {
		return System.currentTimeMillis() - startTime;
	}
	/**
	 * 
	 * This method is like System.out.println but adding extra information about
	 * the time and thread name.
	 * 
	 * @param m
	 *            The message that needs to be displayed
	 * 
	 */
	public void display(String m) {
		System.out.println("[" + age() + "] " + this.name + ": " + m);
	}

}
