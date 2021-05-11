package androidGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Scene extends Boundary{
	public PApplet applet;
	public BMScontrols Bms;
	int id,gw = 25,gh = 20;
	public float bordersize = 1,limit;
	public int cols = 40, rows = 30;
	public String label;
	public boolean showq,showf;
	public boolean drag,resize,border = true,fill = true ,visible,clear;
	public int col;
	public int scol;
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	public ArrayList<Menu> menus = new ArrayList<Menu>();
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Button> nav = new ArrayList<Button>();
	//Boundary boundary;


	public PImage bgimage;
	public ArrayList<Quad> fields = new ArrayList<Quad>();


	public Scene(float xx,float yy, float ww, float hh,BMScontrols bms){

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		Bms = bms;
		applet = bms.applet;
		//main.Boundaries.add(new Boundary(x,y,w,h,4));
		float gW = (w)/cols, gH = h/rows;
		int k = gw;

		float sw = w /cols;
		float sh = h / rows;

		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){

				float X = x + (gW * j);  
				float Y = y + (gH * i);
				int ID = (j + i * cols);

				fields.add(new Quad(new PVector(X,Y),ID,gW,gH,this));

			}}
		Bms.scenes.add(this);
	};


	Scene(){

	};

	public void save(){

	};

	public void load(){

	};

	public void display(){

		applet.noStroke();applet.fill(255);applet.rect(x,y,w,h);
		applet.strokeWeight(bordersize);
		applet.stroke(bg);if(!border){applet.noStroke();}
		applet.fill(bg);
//		if(!fill)
			applet.noFill();

		applet.rect(x,y,w,h);

	};

	public void regression(){

	};

	public void field(){

		for(int i=0;i<fields.size();i++){

			Quad q = fields.get(i);

			q.draw();
			//q.setField();
			//q.field();
		}

	};

	public boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	}

};
