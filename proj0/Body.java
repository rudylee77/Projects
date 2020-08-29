public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		return Math.pow(Math.pow(xxPos-b.xxPos, 2) + Math.pow(yyPos-b.yyPos, 2), 0.5);
	}

	public double calcForceExertedBy(Body b){
		double g = 6.67e-11;
		double r = calcDistance(b);
		double force = (g * mass * b.mass)/Math.pow(r,2);
		double forceX = (force * (xxPos-b.xxPos))/r;
		double forceY = (force * (yyPos-b.yyPos))/r;
		return Math.pow(Math.pow(forceX, 2)+Math.pow(forceY, 2), 0.5);
	}

	public double calcForceExertedByX(Body b){
		double g = 6.67e-11;
		double r = calcDistance(b);
		double force = (g * mass * b.mass)/Math.pow(r,2);
		return (force * (b.xxPos-xxPos))/r;
	}

	public double calcForceExertedByY(Body b){
		double g = 6.67e-11;
		double r = calcDistance(b);
		double force = (g * mass * b.mass)/Math.pow(r,2);
		return (force * (b.yyPos-yyPos))/r;
	}

	public double calcNetForceExertedByX(Body[] b){
		double netForce = 0;
		for(Body p : b){
			if(!this.equals(p)){
				netForce += calcForceExertedByX(p);
				}
			}
		return netForce;
	}

	public double calcNetForceExertedByY(Body[] b){
		double netForce = 0;
		for(Body p : b){
			if(!this.equals(p)){
				netForce += calcForceExertedByY(p);
				}
			}
		return netForce;
	}

	public void update(double t, double xF, double yF){
		double aX = xF/mass;
		double aY = yF/mass;
		xxVel += t * aX;
		yyVel += t * aY;
		xxPos += t * xxVel;
		yyPos += t * yyVel;
	}

	public void draw(){
		String reference = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, reference);
	}
}
