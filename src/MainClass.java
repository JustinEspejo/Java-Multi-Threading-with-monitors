/*
 * Justin Espejo
 * CS344
 * Project 1 - Monitors
 */


public class MainClass {

	public static void main(String[] args){
		//Initial Values (later ask the user)
		int theater_capacity = 7;
		int blueStudents = 8;
		int orangeStudents = 16;
		Monitor h = new Monitor(theater_capacity);
		Clock c = new Clock(h);
		
		//create orange student thread
		BlueStudent o[] = new BlueStudent[orangeStudents];
		for(int i = 0; i < orangeStudents; i++){
			o[i] = new BlueStudent(h, i, true,c);
		}
		
		//create blue student thread
		BlueStudent b[] = new BlueStudent[blueStudents];
		for(int i = 0; i < blueStudents; i++){
			b[i] = new BlueStudent(h, i, false,c);
		}
		
	}

}
