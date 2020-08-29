public class NBody{
	
	public static double readRadius(String s){
		In in = new In(s);
		int first = in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String s){
		In in = new In(s);
		Body[] planets = new Body[5];
		int first = in.readInt();
		double second = in.readDouble();
		for(int i=0; i<planets.length; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String im = in.readString();
			Body b = new Body(xP, yP, xV, yV, m, im);
			planets[i] = b;
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);
		double time = 0;
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		while(time<T){
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for(int i=0; i<planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0; i<planets.length; i++){
				planets[i].update(time, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i=0; i<planets.length; i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
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