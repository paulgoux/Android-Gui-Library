package androidGui;

import processing.core.PApplet;
import processing.core.PVector;

public class _ellipse{
	BMScontrols Bms;
	PApplet applet;
	float x,y,w,h,mass,density,viscoscity,friction,moment,vx,vy,ax,ay;
	boolean border, dashed,connected;
	PVector pos,vel,ac;
	int bg,str;
	Boundary ellipse;

	public _ellipse(float xx,float yy,float ww,float hh,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		h = hh;
		w = ww;

		ellipse = new Boundary(xx,yy,ww,hh,2,bms);
	};

	public void draw(){

	}

};