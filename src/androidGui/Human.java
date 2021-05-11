package androidGui;

import java.util.ArrayList;

import processing.core.PVector;

public class Human extends Entity{

	ArrayList<Integer> connections = new ArrayList<Integer>();

	public Human(float X, float Y,Entity e){

		x = X;
		y = Y;
		vx = 2;
		vy = 2;
		Bms = e.Bms;
		applet = e.applet;
		init();
	}

	public Human(float X, float Y,int i,int j,int E,Entity e){

		x = X;
		y = Y;
		vx = 2;
		vy = 2;
		id = i;
		race = j;
		ent = E;
		Bms = e.Bms;
		applet = e.applet;
		init();
	}

	public Human(float X, float Y,int i,int j,int E,int c,Entity e){

		x = X;
		y = Y;
		vx = 2;
		vy = 2;
		id = i;
		race = j;
		ent = E;
		Bms = e.Bms;
		applet = e.applet;
		col = c;

		init();
	}

	public void init(){
		p = new PVector(x,y);
		vel = new PVector(applet.random(-1,1),applet.random(-1,1));
		ac = new PVector(0.1f,0.1f);
		lifespan = applet.random(200,250);
		movement = applet.random(40,100);
		intelligence = applet.random(40,100);
		dexterity = applet.random(40,100);
		speed = applet.random(40,100);
		charisma = applet.random(40,100);
		vision = applet.random(40,100);
		health = applet.random(200,250);
		endurance = applet.random(40,100);
		selfp = applet.random(40,100);
		agility = applet.random(40,100);
		reputation = applet.random(40,100);
		sanity = applet.random(40,100);
		humanity = applet.random(40,100);
		size = applet.random(10,20);
		skill = applet.random(0,10);
		w = size;
		h = size;
		center = new PVector(0,0);
		centervel = new PVector(0,0);
		breathingroom = applet.random(50,100);
		breathingroomb = breathingroom;

		tri_boundary = new _tri(x,y,10,20,Bms);
		tri_boundary.parent = this;
		tri_boundary.tri.center.x = tri_boundary.tri.x1;
		tri_boundary.tri.center.y = tri_boundary.tri.y1;
		tri_boundary.bg = col;
		tri_boundary.tri.update_Boundaries(tri_boundary.tri);
		tri_boundary.tri.create_rotation_points(tri_boundary.tri.Boundaries);
		tri_boundary.tri.update_dist(tri_boundary.tri.Boundaries);
		// previous boundary
		tri_boundaryp = new _tri(x,y,10,20,Bms);
		tri_boundaryp.parent = this;
		tri_boundaryp.tri.center.x = tri_boundaryp.tri.x1;
		tri_boundaryp.tri.center.y = tri_boundaryp.tri.y1;
		tri_boundaryp.bg = col;
		tri_boundaryp.tri.update_Boundaries(tri_boundaryp.tri);
		tri_boundaryp.tri.create_rotation_points(tri_boundaryp.tri.Boundaries);
		tri_boundaryp.tri.update_dist(tri_boundaryp.tri.Boundaries);

		// future boundary

		tri_boundaryf = new _tri(x,y,10,20,Bms);
		tri_boundaryf.parent = this;
		tri_boundaryf.tri.center.x = tri_boundaryf.tri.x1;
		tri_boundaryf.tri.center.y = tri_boundaryf.tri.y1;
		tri_boundaryf.bg = col;
		tri_boundaryf.tri.update_Boundaries(tri_boundaryf.tri);
		tri_boundaryf.tri.create_rotation_points(tri_boundaryf.tri.Boundaries);
		tri_boundaryf.tri.update_dist(tri_boundaryf.tri.Boundaries);
		fov = applet.random(30,40);
		//ac.x = map(speed,0,100,0,0.01);
		//ac.y = map(speed,0,100,0,0.01);

		//vlimit = applet.random(4,100);
		lifespan = applet.random(minageh,maxageh);
		carrying_capacity = applet.floor(applet.random(0,6));
		genes.put("carrying_capacity",(float) (carrying_capacity));
		genes.put("vlimit",vlimit);
		genes.put("vlimit",vlimitb);
		genes.put("alimit",alimit);
		genes.put("alimit",alimitb);
		genes.put("radius",radius);
		genes.put("breathingroom",breathingroom);
		genes.put("min",min);
		genes.put("minb",minb);
		genes.put("col",(float) (col));
		genes.put("mass", mass);
		genes.put("lifespan",lifespan);
		genes.put("intelligence",intelligence);
		genes.put("dexterity",dexterity);
		genes.put("endurance",endurance);
		genes.put("piety",piety);
		genes.put("vision,",vision);
		genes.put("health",health);
		genes.put("speed",speed);
		genes.put("selfp",selfp);
		genes.put("charisma",charisma);
		genes.put("agility",agility);
		genes.put("reputation",reputation);
		genes.put("sanity",sanity);
		genes.put("humanity",humanity);
		genes.put("faith",faith);
		genes.put("fresistance",fresistance);

		for (String key : genes.keySet()) {
			Genes.add(key);
		}
		desiredTrait();


	};

	public void savegenes(){
		genes.put("carrying_capacity",(float) (carrying_capacity));
		genes.put("vlimit",vlimit);
		genes.put("vlimit",vlimitb);
		genes.put("alimit",alimit);
		genes.put("alimit",alimitb);
		genes.put("radius",radius);
		genes.put("breathingroom",breathingroom);
		genes.put("min",min);
		genes.put("minb",minb);
		genes.put("col",(float) (col));
		genes.put("mass", mass);
		genes.put("lifespan",lifespan);
		genes.put("intelligence",intelligence);
		genes.put("dexterity",dexterity);
		genes.put("endurance",endurance);
		genes.put("piety",piety);
		genes.put("vision,",vision);
		genes.put("health",health);
		genes.put("speed",speed);
		genes.put("selfp",selfp);
		genes.put("charisma",charisma);
		genes.put("agility",agility);
		genes.put("reputation",reputation);
		genes.put("sanity",sanity);
		genes.put("humanity",humanity);
		genes.put("faith",faith);
		genes.put("fresistance",fresistance);

		for (String key : genes.keySet()) {
			Genes.add(key);
		}
	};

	public void draw(){
		applet.strokeWeight(ssize);
		applet.stroke(cols);
		applet.fill(col2);
		applet.ellipse(x,y,w,h);
	};

	public void update_qgrid(){

		//updates the corresponding qgrid array's children with the last id in the iid arraylist

		// iid is either of length 1 at the start or of length 2 any time after. If iid is size 2 then you need to check if the perticle crosses a border at anytime by comparing iid(0)
		// and iid(1), if they are different then the particle has crossed a border. Therefore update the corresponding previous grid space by removing this id, and update the new grid
		// space by adding the id.

		ArrayList<Quad> qgrid = scene.fields;
		if(iid.size()==2){
			boolean a = true;
			if(iid.get(1)<qgrid.size()&&iid.get(1)>=0){
				a = qgrid.get(iid.get(1)).children.contains(this);
			}
			boolean b = false;
			if(iid.get(0)<qgrid.size()&&iid.get(0)>=0){
				b = qgrid.get(iid.get(0)).children.contains(this);
			}
			if(!a){
				qgrid.get(iid.get(1)).children.add(this);

			}
			//qgrid.get(iid.get(1)).fillc();
			if (b){
				int k = qgrid.get(iid.get(0)).children.indexOf(this);
				qgrid.get(iid.get(0)).children.remove(k);
			}}
		if(iid.size()==1){
			if(iid.get(0)<qgrid.size()&&iid.get(0)>=0){
				boolean a = qgrid.get(iid.get(0)).children.contains(this);
				if(!a){
					//qgrid.get(iid.get(0)).fillc();
					qgrid.get(iid.get(0)).children.add(this);
				}}}
	};

	public void interact(){

	};

	public void reproduce(){

	}

	public void collide(){

	}

	public void ears(){

	};

	public void gene(){

	};

};

