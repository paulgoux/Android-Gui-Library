package androidGui;

class Metal extends Entity{

	Metal(float X, float Y){
		x = X;
		y = Y;
	}

	Metal(float X, float Y,int i,int j,int E){
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