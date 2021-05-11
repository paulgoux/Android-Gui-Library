package androidGui;

import processing.core.PApplet;
import processing.core.PGraphics;

public class textBlock{
	BMScontrols Bms;
	PApplet applet;
	boolean parentCanvas;
	public boolean vertical,horizontal,border ;
	public float x,y,w,h,bx,by,bw,bh,offsetX,offsetY;
	public float tSize = 12,r1,r2,r3,r4;

	public String [] text;
	public String line;
	public int col;

	public textBlock(float x,float y,float w,float h,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		bx = x;
		by = y;
		bw = w;
		bh = h;

	};

	public textBlock(float x,float y,float w,float h,String[] s,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		bx = x;
		by = y;
		bw = w;
		bh = h;
		text = s;
	};

	public textBlock(float x,float y,float w,float h,String s,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		bx = x;
		by = y;
		bw = w;
		bh = h;
		line = s;
	};

	textBlock(){

	};

	public void draw(){

		applet.stroke(0);
		if(!border)applet.noStroke();
		applet.fill(255);
		applet.rect(x,y,w,h,r1,r2,r3,r4);

		if(text!=null)
			for(int i=0;i<text.length;i++){
				String s1 = text[i];
				applet.fill(0);
				applet.textSize(tSize);
				if(y + i*tSize - offsetY<y+h&&y + i*tSize - offsetY>0)applet.text(s1,x + offsetX,y + i*tSize - offsetY);

			}

	};

	public void draw(PGraphics canvas){
		//canvas.beginDraw();
		if(text!=null)
			for(int i=0;i<text.length;i++){
				String s1 = text[i];
				canvas.fill(col);
				canvas.textSize(tSize);
				if(y + i*tSize-offsetY<y+h&&y + i*tSize - offsetY>0)canvas.text(s1,x + offsetX,y + i*tSize - offsetY);
			}
		//canvas.endDraw();

	};

	public void draw(PGraphics canvas,boolean a){
		canvas.beginDraw();
		if(text!=null)
			for(int i=0;i<text.length;i++){
				String s1 = text[i];
				canvas.fill(col);
				canvas.textSize(tSize);
				if(y + i*tSize-offsetY<y+h&&y + i*tSize - offsetY>0)canvas.text(s1,x + offsetX,y + i*tSize - offsetY);
			}
		canvas.endDraw();

	};

	public void setRadius(float a) {
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
	};

	public void setRadius(float a,float b,float c,float d) {
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};
};
