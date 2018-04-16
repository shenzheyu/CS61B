public class NBody {

	/* return a double corresponding to the radius of the universe in that file */
	public static double readRadius (String file) {
		In in = new In(file);
		in.readInt();
		return in.readDouble();
	}

	/* return an array of Planets corresponding to the planets in the file */
	public static Planet[] readPlanets (String file) {
		In in = new In(file);
		int nums = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[nums];
		for (int i = 0; i < nums; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			planets[i] = p;
		}
		return planets;
	}

	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String file = args[2];
		double radius = readRadius(file);
		Planet[] planets = readPlanets(file);
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p : planets) {
			p.draw();
		}
		StdDraw.pause(2000);
		StdDraw.enableDoubleBuffering();
		double t = 0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		while (t < T) {
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
    			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

}