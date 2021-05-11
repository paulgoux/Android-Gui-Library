package androidGui;
import processing.core.*;
import processing.core.PApplet;
import processing.core.PVector;;

public class _rect{
	BMScontrols Bms;
	PApplet applet;
	float x,y,w,h,mass,density,viscoscity,friction,moment,vx,vy,ax,ay;
	boolean border, dashed,connected;
	PVector pos,vel,ac;
	int bg,str;
	Boundary rect;

	public _rect(float xx,float yy,float ww,float hh,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		h = hh;
		w = ww;

		rect = new Boundary(xx,yy,ww,hh,4,Bms);
	};

	public void draw(){

	};

};
