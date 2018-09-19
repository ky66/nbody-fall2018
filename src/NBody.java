import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	public static double readRadius(String x)

	{

		Scanner input = null;
		File file = new File(x);
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		input.nextDouble();
		double b = input.nextDouble();
		return b;
	}

	public static Body[] readBodies(String x) {
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

	public static void main(String[] args) {

		double T = 157788000.0;
		double dt = 25000.0;
		String filename = "/Users/kamyaryazdani/nbody-fall2018/data/planets.txt";
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "starfield.jpg");
		for (Body a : bodies) {
			a.draw();
		}
		double t = 0;
		for (t = 0; t < T; t += dt) {
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for(int i =0;i<bodies.length;i++){
			xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
			yForces[i]=bodies[i].calcNetForceExertedByY(bodies);}
		    for(int j = 0; j<bodies.length;j++){
		    	bodies[j].update(dt, xForces[j], yForces[j]);}
			StdDraw.picture(0, 0, "starfield.jpg");
			for (Body a : bodies) {
				a.draw();}
			StdDraw.show(10);}
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		    		 bodies[i].getX(), bodies[i].getY(), 
		    		 bodies[i].getXVel(), bodies[i].getYVel(), 
		    		 bodies[i].getMass(), bodies[i].getName());		
		}
		// more code here
	}
}
