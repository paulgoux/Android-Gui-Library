package androidGui;

public class Animal extends Entity{

	public Animal(float X, float Y){
		x = X;
		y = Y;

	}

	public Animal(float X, float Y,int i,int j,int E){
		x = X;
		y = Y;
		id = i;
		species = new Species(ent,i);
		ent = E;

	}

	public void draw(){
		applet.stroke(0);
		applet.fill(0);
		applet.ellipse(x,y,20,20);
	};

};
