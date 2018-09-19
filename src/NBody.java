	

/**
 * @author Kamyar Yazdani
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// TODO: read values at beginning of file to
		// find the radius
		s.nextDouble();
		double radius = s.nextDouble();
//		double radius2 = radius.nextDouble();
		
		s.close();
		
		return radius;
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies (String x) {
		Scanner input = null;
		int i = 0;
		File file = new File(x);
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int number = input.nextInt();
		Body[] result = new Body[number];
		input.nextDouble();
		for (int j = 0; j < number; j++) {
			double xp = input.nextDouble();
			double yp = input.nextDouble();
			double xv = input.nextDouble();
			double yv = input.nextDouble();
			double mass = input.nextDouble();
			String name = input.next();
			Body b = new Body(xp, yp, xv, yv, mass, name);
			result[i] = b;
			i++;
		}
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {

		double T = 157788000.0;
		double dt = 25000.0;
		String filename = "./data/planets.txt";
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "starfield.jpg");
		for (Body a : planets) {
			a.draw();
		}
		double t = 0;
		for (t = 0; t < T; t += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i =0;i<planets.length;i++){
			xForces[i]=planets[i].calcNetForceExertedByX(planets);
			yForces[i]=planets[i].calcNetForceExertedByY(planets);}
		    for(int j = 0; j<planets.length;j++){
			planets[j].update(dt, xForces[j], yForces[j]);}
			StdDraw.picture(0, 0, "starfield.jpg");
			for (Body a : planets) {
				a.draw();}
			StdDraw.show(10);}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                      planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		// more code here
	}
}

