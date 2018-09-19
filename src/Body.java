import java.lang.Math.*;

public class Body  {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	
	public Body (double xp, double yp, double xv, double yv, double Mass, String filename) {
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = Mass;
		this.myFileName = filename;
		
	
	}
	public Body (Body  b) {
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		this.myFileName = b.myFileName;
		
		
	}
	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}
	
	

	public double calcDistance(Body  b) {
		

		return Math.sqrt(Math.pow(this.myXPos-b.myXPos,2) +Math.pow(this.myYPos-b.myYPos,2));
				
	}
	
	public double calcForceExertedBy(Body p) {
		double G = 6.67*1e-11;
		double F = G * ((this.myMass * p.myMass)/(Math.pow(this.calcDistance(p), 2)));
		return F;
	}

	public double calcForceExertedByX(Body p) {
		double F = calcForceExertedBy(p);
		double Fx = F * ((p.myXPos-this.myXPos)/this.calcDistance(p));
		return Fx;
	}
	public double calcForceExertedByY(Body p) {
		double F = calcForceExertedBy(p);
		double Fy = F * ((p.myYPos-this.myYPos)/this.calcDistance(p));
		return Fy;
	}
	
	public double calcNetForceExertedByX(Body[] bodies){
		double force = 0;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				double xforces = calcForceExertedByX(b);
				force += xforces;
			}
		}
		return force;
	}
	public double calcNetForceExertedByY(Body[] bodies){
		double force = 0;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				double yforces = calcForceExertedByY(b);
				force += yforces;
			}
		}
		return force;
	}
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/this.myMass;
		double ay = yforce/this.myMass;
		double nvx = this.myXVel + deltaT*ax;
		double nvy = this.myYVel + deltaT*ay;
		double nx = this.myXPos + deltaT*nvx;
		double ny = this.myYPos + deltaT*nvy;
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
		
		
		
		
		
		
		
	}
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images" + myFileName);
		// TODO Auto-generated method stub
		
	}
}
