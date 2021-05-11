package androidGui;

import processing.core.PApplet;
import processing.core.PVector;

public class Plant extends Entity{

	int col = applet.color(0,255,0);

	public Plant(float X, float Y){
		x = X;
		y = Y;
		vx = 2;
		vy = 2;
		init();
	};

	public Plant(float X, float Y,int i,int j,int E){
		x = X;
		y = Y;
		vx = 2;
		vy = 2;
		id = i;
		ent = E;
		species = new Species(ent,i);
		init();

	};

	public void init(){
		p = new PVector(x,y);
		vel =new PVector(0,0);
		ac =new PVector(0,0);
		lifespan = applet.random(40,100);
		movement = applet.random(40,100);
		intelligence = applet.random(40,100);
		dexterity = applet.random(40,100);
		speed = applet.random(40,100);
		charisma = applet.random(40,100);
		vision = applet.random(40,100);
		health = applet.random(40,100);
		endurance = applet.random(40,100);
		selfp = applet.random(40,100);
		agility = applet.random(40,100);
		reputation = applet.random(40,100);
		sanity = applet.random(40,100);
		humanity = applet.random(40,100);
		size = applet.random(5,10);
		skill = applet.random(0,10);
		w = size;
		h = size;
		lifespan = applet.random(500,1000);
		carrying_capacity = PApplet.floor(applet.random(0,6));
	}

	public void draw(){
		applet.stroke(col);
		applet.fill(col,100);
		applet.ellipse(x,y,w/(applet.frameCount/60)*2,h/(applet.frameCount/60)*2);
	};

};
