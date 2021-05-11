package androidGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Quad{
	BMScontrols Bms;
	PApplet applet;
	int id,iid,counter,xpos,ypos;
	float x,y,w,h,dens,dens2,dens3,dens4,v,v2,v3,v4,kv,kv2,kv3,kv4;
	float dir,dir2,dir3,dir4;
	PVector p, kp,kp2,kp3;
	int col = 0,col2 = 0,col3 = 0,col4 = 0;
	ArrayList<Entity> children  = new ArrayList<Entity>();
	ArrayList<Quad> affectees  = new ArrayList<Quad>();
	ArrayList<Quad> affectors  = new ArrayList<Quad>();
	//	  ArrayList<PVector> children  = new ArrayList<PVector>();
	//HashMap<Quad,ArrayList
	Scene scene;

	public Quad(PVector P,int ID,float W,float H, PApplet applet){
		p = P;
		id = ID;
		x = p.x;
		y = p.y;
		w = W;
		h = H;
		this.applet = applet;
	};

	public Quad(PVector P,int ID,float W,float H,Scene s){
		p = P;
		id = ID;
		x = p.x;
		y = p.y;
		w = W;
		h = H;
		scene = s;
		Bms = scene.Bms;
		applet = scene.applet;
		init();
	};
	
	public Quad(PVector P,int ID,float W,float H,Scene s,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		p = P;
		id = ID;
		x = p.x;
		y = p.y;
		w = W;
		h = H;
		scene = s;
		init();
	};
	
	public void init() {
		kp = new PVector(0,0);
		kp2 = new PVector(0,0);
		kp3 = new PVector(0,0);
	};

	public void draw(){

	};

	public void fillc(){
		applet.fill(255);
		//rect(p.x,p.y,w,h);

	}; 

	public void drawSpace(){
		applet.noFill();
		applet.stroke(0);
		applet.strokeWeight(1);
		applet.rect(p.x,p.y,w,h);

	};

	public void field(){
		dens2 = 0;
		kv2 = 0;
		col2 = 0;
		dir2 = 0;
		if(x+w<scene.x+scene.w + w&&y+h<scene.y+scene.h+w){
			for(int i=0;i<scene.fields.size();i++){
				Quad q = scene.fields.get(i);

				float d = PApplet.dist(x,y,q.x,q.y);
				if(dens*4>d&&q!=this){
					q.dens2 = dens/4;
					q.kv2 = kv/4;
					q.col2 = col/4;
					q.kp = kp;
					q.dir2 = PApplet.atan2(y-q.y,x-q.x);

				}}}
	};

	public void humans(){

	}

};


