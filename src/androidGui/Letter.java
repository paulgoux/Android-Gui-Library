package androidGui;

import processing.core.PApplet;
import processing.core.PVector;

public class Letter{
	BMScontrols Bms;
	PApplet applet;
	int id,vpos,hpos;
	float x,y,w,h,tsize;
	String l;
	int col,col2;
	boolean visible = true;

	public Letter(String ll,float xx, float yy,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		l = ll;
		w = applet.textWidth(l);
		h = 12;

		initColors();
	};

	public void initColors() {
		col = applet.color(255,0);
		col2 = applet.color(0);;
	};

	public boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	}

	public boolean pos(PVector m){
		return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h;
	}
};
