package androidGui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Dock{
	BMScontrols Bms;
	PApplet applet;
	float x,y,w,h,bx,by,bw,bh,currentWidth,currentHeight,r1,r2,r3,r4;
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<PGraphics> canvases = new ArrayList<PGraphics>();
	ArrayList<Object> objects = new ArrayList<Object>();
	boolean update,vertical,horizontal,mdown;
	String loc;
	Object currentObject;
	Object parent;

	public Dock(float x,float y,float w,float h,BMScontrols bms){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		Bms = bms;
		applet = bms.applet;
	};

	public Dock(float x,float y,float w,float h,Object parent){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		this.parent = parent; 
	};

	public void add(tab t){
		String loc = t.label;
		PGraphics canvas = applet.createGraphics((int)applet.textWidth(loc)+20,20);
		canvases.add(canvas);
		Button b = new Button(currentWidth,y,applet.textWidth(loc)+20,20,loc,Bms);
		b.classicBar = true;
		buttons.add(b);
		objects.add(t);
		currentWidth += applet.textWidth(loc)+20;
		names.add(loc);
	};

	public void add(Window t){
		String loc ="";
		if(t.currentp!=null)loc = t.currentp;
		PGraphics canvas = applet.createGraphics((int)applet.textWidth(loc)+20,20);
		canvases.add(canvas);
		Button b = new Button(currentWidth,y,applet.textWidth(loc)+20,20,loc,Bms);
		b.applet = applet;
		b.Bms = Bms;
		b.classicBar = true;
		buttons.add(b);
		objects.add(t);
		currentWidth += applet.textWidth(loc)+20;
		names.add(loc);
	};

	public void logic(){

		if(applet.mousePressed&&pos()&&!mdown){
			loc = Bms.currentMouseObject;

			update = false;
			mdown = true;
		}

		if(!update&&mdown&&!applet.mousePressed&&pos()&&!names.contains(loc)){
			update = true;
			mdown = false;
		}
		if(pos()&&update&&loc!=null&&!names.contains(loc)){
			Bms.currentMouseObject = null;
			Bms.currentObject = null;
			PGraphics canvas = applet.createGraphics((int)applet.textWidth(loc)+20,20);
			canvases.add(canvas);

			Button b = new Button(currentWidth,y,applet.textWidth(loc)+20,20,loc,Bms);

			b.classicBar = true;
			buttons.add(b);

			objects.add(currentObject);
			currentWidth += applet.textWidth(loc)+20;

			names.add(loc);
			update = false;
			mdown = false;
			loc = null;
			currentObject = null;
		}
		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void draw(){

		//         applet.beginDraw();
		//
		//         applet.endDraw();
		//         applet.image(canvas,x,y);

	};

	public void draw(PGraphics canvas){

		canvas.beginDraw();

		canvas.endDraw();
		applet.image(canvas,x,y);

	};

	public void drawItems(){
		boolean k = false;
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);
			canvases.get(i).beginDraw();
			
			b.x = 0;
			b.y = 0;
			b.mouse = getMouse(b);
			b.draw(canvases.get(i));
			b.toggle(objects.get(i),"toggle",b.mouse);

			canvases.get(i).endDraw();
			applet.image(canvases.get(i),b.bx,b.by);
		}
		if(pos()&&applet.mousePressed&&Bms.currentObject!=null){
			applet.noFill();
			applet.stroke(0);
			applet.strokeWeight(1);
			applet.rect(x,y,w,h,r1,r2,r3,r4);
		}
	};

	PVector getMouse(Button b){

		return new PVector(applet.mouseX-x-b.bx,applet.mouseY-y);
	};

	PVector getMouse(){

		return new PVector(applet.mouseX-x,applet.mouseY-y);
	};

	boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	boolean pos(PVector mouse){

		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;

	};

	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}
	};
	
	public Object getItem(int i){
		Object o = null;
		if(i<objects.size())return objects.get(i);
		else return null;
	};
	
	public String getName(int i){
		Object o = null;
		if(i<names.size())return names.get(i);
		else return null;
	};
	
	public Object getItem(String s){
		Object o = null;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			if(b.label!=null&&b.label==s) {
				o = objects.get(i);
				break;
			}
		}
		return o;
	};
	
	public void removeItem(String s){
		int i = names.indexOf(s);
		if(i>-1) {
			objects.remove(i);
			names.remove(i);
			buttons.remove(i);
		}
	};

};
