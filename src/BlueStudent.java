/*
 * Justin Espejo
 * CS344
 * Project 1 - Monitors
 */

import java.util.Random;
import java.util.Vector;

public class BlueStudent implements Runnable {

	protected int studentNumber; 
	protected boolean isOrange = true;
	protected Monitor h;
	protected int numOfParadesAttended = 0;
	protected int numOfShowsAttended = 0;
	String color = "";
	public int numOfParades;
	public int numOfShows;
	public int showNumber;
	public Clock c;
	public Vector paradeTimes = new Vector(); 
	public Vector showTimes = new Vector(); 
	private String paradeString= "";
	private String showString= "";

	BlueStudent(Monitor h, int students_number, boolean isOrange,Clock c){
		this.studentNumber = students_number;
		new Thread(this).start();
		this.isOrange = isOrange;
		this.h = h;
		this.c = c;
		if(isOrange){
			color = "Orange ";
		}
		else{
			color = "Blue";
		}

	}

	@Override
	public void run() {
		display("starts walking to the meeting place for parade.");

		while(true){
			nap(getRandomNumber(3));
			h.waitForParade(this);
			if(c.showtimeOver)break;

			//find group and Parade
			h.findGroup(this);
			paradeTimes.addElement(age());
			if(c.showtimeOver)break; //if showtime is over just get out of loop
			numOfParades++;

			//Done with parade and now taking a snackbreak
			nap(getRandomNumber(5));

			//Try to get in a show
			if(c.showtimeOver)break; //if showtime is over just get out of loop
			if(c.showNumber<3){
				h.showLine(this); //get in the show line
				if(c.showtimeOver)break; //if showtime is over just get out of loop
				showTimes.addElement(age());
				numOfShows++;
			}
			if(numOfParades==3 && numOfShows==3) break;
			display(" is done watching the show gonna go back because he hasn't attended enough shows and parades.");
		}

		generateStrings();

//		display("is done and has attended " + numOfParades +" parades and " +numOfShows +" shows at the following times");
		display(paradeString + "\n" + showString);


	}

	public void generateStrings(){

		for(int i=0; i<paradeTimes.size(); i++){
			int count = i+1;
			paradeString += " attended Parade " + count + " at " + paradeTimes.elementAt(i) + " ";

		}
		if(showTimes.size()==0){
			showString += "DID NOT ATTEND ANY SHOWS!!!";
		}
		else{
			for(int i=0; i<showTimes.size(); i++){
				int count = i+1;
				showString += "attended Show " + count + " at " + showTimes.elementAt(i) + " ";
			}
		}

	}


	public void display(String m) {
		System.out.println("[" + age() + "] " + color +"Student-"+ this.studentNumber + ": " + m);
	}

	protected static final long age() {
		return System.currentTimeMillis() - Clock.startTime;
	}

	public int getRandomNumber(int n) {
		int seconds = n*1000;
		Random random = new Random();
		return random.nextInt(seconds);
	}
	private void nap(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}