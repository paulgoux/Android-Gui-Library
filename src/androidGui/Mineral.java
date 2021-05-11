package androidGui;

class Mineral extends Entity{

	Mineral(float X, float Y){
		x = X;
		y = Y;

	}

	Mineral(float X, float Y,int i,int j,int E){
		x = X;
		y = Y;
		id = i;
		species = new Species(ent,i);
		ent = E;

	}

	public void draw(){
		applet.stroke(0);
		applet.ellipse(x,y,size,size);
	};

};
