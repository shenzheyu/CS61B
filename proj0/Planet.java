public class Planet {

	public double xxPos;	//Its current x position
	public double yyPos;	//Its current y position
	public double xxVel;	//Its current velocity in the x direction
	public double yyVel;	//Its current velocity in the y direction
	public double mass;	//Its mass
	public String imgFileName;	//The name of the file that corresponds to the image that depicts the planet
	public static final double G = 6.67e-11;	//The gravitational constant  

	/* the first constructor of Planet */
	public Planet (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/* the second constructor of Planet, which copys p */
	public Planet (Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/* calculate the distance between two Planets */
	public double calcDistance (Planet p) {
		double res = 0;
		res += Math.pow(p.xxPos - xxPos, 2);
		res += Math.pow(p.yyPos - yyPos, 2);
		res = Math.pow(res, 0.5);
		return res;
	}

	/*  return a double describing the force exerted on this planet by the given planet */
	public double calcForceExertedBy (Planet p) {
		double res = 0;
		res = G * mass * p.mass;
		res /= Math.pow(calcDistance(p), 2);
		return res;
	}

	/* describe the force exerted in the X and Y directions */
	public double calcForceExertedByX (Planet p) {
		double res = 0;
		res = calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
		return res;
	}
	public double calcForceExertedByY (Planet p) {
		double res = 0;
		res = calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
		return res;
	}

	public boolean equals (Planet p) {
		boolean res = true;
		if (xxPos != p.xxPos) {
			res = false;
		}
		if (yyPos != p.yyPos) {
			res = false;
		}
		if (xxVel != p.xxVel) {
			res = false;
		}
		if (yyVel != p.yyVel) {
			res = false;
		}
		if (mass != p.mass) {
			res = false;
		}
		if (imgFileName != p.imgFileName) {
			res = false;
		}
		return res;
	}

	/* take in an array of Planets and calculate the net X and net Y force exerted by all planets in that array upon the current Planet */
	public double calcNetForceExertedByX (Planet[] allPlanets) {
		double res = 0;
		for (Planet p : allPlanets) {
			if (!equals(p)) {
				res += calcForceExertedByX(p);
			}
		}
		return res;
	}
	public double calcNetForceExertedByY (Planet[] allPlanets) {
		double res = 0;
		for (Planet p : allPlanets) {
			if (!equals(p)) {
				res += calcForceExertedByY(p);
			}
		}
		return res;
	}

	/* adjust the velocity and position */
	public void update (double dt, double xxForce, double yyForce) {
		double xxAcceleration, yyAcceleration;
		xxAcceleration = xxForce / mass;
		yyAcceleration = yyForce / mass;
		xxVel += xxAcceleration * dt;
		yyVel += yyAcceleration * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	/* draw the Planet’s image at the Planet’s position */
	public void draw () {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		StdDraw.show();
	}
}