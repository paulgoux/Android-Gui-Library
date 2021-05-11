package androidGui;

import processing.core.PApplet;
import processing.core.PVector;

public class _tri{
	PApplet applet;
	BMScontrols Bms;
	float x,y,w,h,mass,density,viscoscity,friction,moment,vx,vy,ax,ay;
	boolean border, dashed,connected,fill = true;
	PVector pos,vel,ac;
	int bg,str;
	Boundary tri;
	public Human parent;

	public _tri(float xx,float yy,float ww,float hh,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		h = hh;
		w = ww;
		bg = applet.color(0,180);
		str = applet.color(0);
		tri = new Boundary(x,y,10,20,103,Bms);
	};

	public _tri(){

	};

	public void draw(){
		applet.beginShape();
		applet.stroke(str);
		if(!border)applet.noStroke();
		applet.fill(bg);
		//if(parent!=null)fill(parent.col);
		if(!fill)applet.noFill();

		for(int i=0;i<tri.Boundaries.size();i++){
			Boundary b = tri.Boundaries.get(i);
			applet.vertex(b.x1,b.y1);
			//vertex(b.x1,b.y1);
		}
		applet.endShape(applet.CLOSE);
	}
	public void update(){
		for(int i=0;i<tri.Boundaries.size();i++){
			Boundary b = tri.Boundaries.get(i);

			b.create_rotation_points(tri.Boundaries);
			b.update_dist(tri.Boundaries);
			b.update_angles();
			b.create_angles(tri.Boundaries);
		}
	};

	boolean pos(PVector a){
		boolean k = false;

		if(tri.pos(tri,a))k = true;
		return k;
	}
};
