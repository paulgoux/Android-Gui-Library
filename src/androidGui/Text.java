package androidGui;

import processing.core.PApplet;

public class Text{
	BMScontrols Bms;
	PApplet applet;
	public float x,y,by,bx,textsize;

	public boolean visible;
	public int col,col1,col2,col3,col4;
	public String text = "";
	public Text(float X,float y,String s,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		x = X;
		this.y = y;

		text = s;
	};

	public void draw(){
		if(visible){
			applet.fill(col);
			applet.text(text,x,y);
		}
	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};
};
