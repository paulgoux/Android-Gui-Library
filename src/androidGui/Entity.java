package androidGui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Entity{
	BMScontrols Bms;
	PApplet applet;
	public float minageh = 100;
	public float maxageh = 200;

	public int id,xp,yp,txtb_value,vcount,polarity,pos,race,ent,
	function,eIndex1 = -1,eIndex2 = -1,cIndex1,cIndex2,aIndex1,aIndex2,
	hRaces,acount,scount,alcount,mcount,gid,xpos,ypos,range;

	public float x,y,z,w,h,intensity,mass,radius,
	lifespan,intelligence,dexterity,endurance,piety,vision,health,speed,selfp,charisma,agility,
	reputation,sanity,humanity,faith,fresistance;

	public float homex,homey,homew,homeh,birthrate = 2000;
	public float memory,movement,respiration,sensitivity,growth,regulation,nutrition,synthesis,transportation,
	reparation, size,tscale = 0.3f,ssize = 1,hunger,h_cooldown,i_cooldown;

	public float age = 0,bscale = 0.2f,time,delay,r,skill,vlimit,vlimitb = vlimit,fov = 20,
			eating_time,breathingroom,breathingroomb,breathingroomb1 = breathingroom,
			breathingroomb2 = breathingroomb,alimit,mult1,mult2,mu,min,minb;

	float  bdir = 0,inc = 0,countdown,countdownb,offset = 100,
			valueOffset,Friction,maxSeek,maxAvoid,maxAlign,minSeek,minAvoid,minAlign,maxRange,minRange;

	public float avoidance,cohesion,cohesionb,alignment,neg = 0,
			wandertheta = 0,wanderm = 0.2f,wandermb = wanderm,cohesionb1 = cohesion,cohesionb2 = cohesionb,
			separationc=1,cohesionc=1,separation,separationb,alimitb = alimit, alignmentb = alignment,
			avoidanceb = avoidance,alignmentc = 1,separationd = 20,separationdb,separationb1,separationb2,
			seekN,avoidN,alignN,seekN2,avoidN2,Avoid,Cohesion,Align;

	public int carrying_capacity,boundaryspace = 30,drawtotalh;
	public float vx,vy,vz,ax = 0.02f,ay = 0.02f,az = 0.1f,dir,sumvx,sumvy,tgdist;
	public String label;
	public String arrays,floats,bools;
	public float a1,a2,a3,a4,a5;
	public PVector p, vel, ac,target,center,centervel,fp,tp,targetb,fp2,centerf,centervelf,fpf ,velf,friction;
	public int col ;
	public int col2 = col,cols,colb = col,hcol,col3;
	public _tri tri_boundary = null;
	public _tri tri_boundaryf = null;
	public _tri tri_boundaryp = null;
	public _rect squ_boundary;
	public _ellipse ellipse_boundary;
	public Boundary current_boundary;
	public Boundary Boundary;
	public Plant targetfruit;
	public Human leader,parent,desired,owner,obstructionh;
	public Species species;
	public Scene scene;
	public sliderBox fRate;
	public Menu entitiesSublist;
	public tab sliders,Menu;

	public String type,path;
	public boolean seek,avoid,align,avoid2;
	public boolean drag,resize,seek_food,fight,mate,work,bond,hide,mazecrawler,human,plant,animal,element,real = true,
				   dead,birth,move,toggle,idle,familyties,addh,Birth,Death;
	public boolean locked,setvel,highlight,updategenes,e1down,e2down,mdown,paused;
	public boolean trackmouse,pause,dynamic,debug,boundary,reset,followb,showbroom,showta,showtb,showheading,showsliders,
				   showview,info,merge,stop,turn,turnh,hidesliders,visible,separationManager,cohesionManager,
				   alignmentManager,save;
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	public HashMap<Integer,Float> neighbourVariables = new HashMap<Integer,Float>();
	public ArrayList<Float> seekNeighbour = new ArrayList<Float>();
	public ArrayList<Float> avoidNeighbour = new ArrayList<Float>();
	public ArrayList<Float> alignNeighbour = new ArrayList<Float>();
	public ArrayList<Float> seekNeighbour_ = new ArrayList<Float>();
	public ArrayList<Float> avoidNeighbour_ = new ArrayList<Float>();
	public ArrayList<Float> alignNeighbour_ = new ArrayList<Float>();
	public ArrayList<Float> oscilators = new ArrayList<Float>();
	public ArrayList<Float> oscilatorCounters = new ArrayList<Float>();

	//---------------Spatial partititoning variables----------------

	public PVector a,v,part,mouse;
	public boolean collide = false, update = false, ext = false;
	public ArrayList<Integer> iid = new ArrayList<Integer>();
	public ArrayList<Integer> iid2 = new ArrayList<Integer>();
	public ArrayList<Quad> Neighbours = new ArrayList<Quad>();
	public ArrayList<Quad> neighbourGrid = new ArrayList<Quad>();
	public int t,c,t2 = 0;

	//---------------ArrayLists-------------------------

	public ArrayList<ArrayList> Entities = new ArrayList<ArrayList>();
	public ArrayList<Boundary> boundariesAvoid = new ArrayList<Boundary>();
	public ArrayList<Boundary> boundariesFollow = new ArrayList<Boundary>();
	public ArrayList<Boolean> walls = new ArrayList<Boolean>();
	public ArrayList<ArrayList<Human>> Humans = new ArrayList<ArrayList<Human>>();
	public ArrayList<ArrayList<Plant>> Plants = new ArrayList<ArrayList<Plant>>();
	public ArrayList<ArrayList<Animal>> Animals = new ArrayList<ArrayList<Animal>>();
	public ArrayList<ArrayList<Mineral>> Minerals = new ArrayList<ArrayList<Mineral>>();
	public ArrayList<ArrayList<Metal>> Metals = new ArrayList<ArrayList<Metal>>();
	public ArrayList<ArrayList<Celluloid>> Celluloids = new ArrayList<ArrayList<Celluloid>>();
	public ArrayList<ArrayList<Particle>> Particles = new ArrayList<ArrayList<Particle>>();
	public ArrayList<Entity> neighbours = new ArrayList<Entity>();
	ArrayList<Human> neighboursH = new ArrayList<Human>();
	public ArrayList<ArrayList<mazeCrawler>> Mazecrawlers = new ArrayList<ArrayList<mazeCrawler>>();

	//public ArrayList<Integer> genes = new ArrayList<Integer>();
	boolean births,try_,conception;
	int child;
	float dice;
	fileOutput saveEntities;
	String []saveLocations = {"entities\\humans.txt","entities\\animals.txt","entities\\particles.txt"};
	String saveLocation = "";
	String [] folder,humans,plants;

	public ArrayList<Human> Children = new ArrayList<Human>();
	public ArrayList<Human> family = new ArrayList<Human>();
	public ArrayList<Human> avoidh = new ArrayList<Human>();
	public ArrayList<Human> proximity = new ArrayList<Human>();
	public ArrayList<Human> prox = new ArrayList<Human>();
	public ArrayList<ArrayList<Human>> prox2 = new ArrayList<ArrayList<Human>>();
	public ArrayList<ArrayList<Human>> proximity2 = new ArrayList<ArrayList<Human>>();

	HashMap<String,Float>genes = new HashMap<String,Float>();
	HashMap<String,Float>sgenes = new HashMap<String,Float>();
	public ArrayList<String> Genes = new ArrayList<String>();
	public ArrayList<String> desiredTraits = new ArrayList<String>();
	public ArrayList<String> desired_skill = new ArrayList<String>();

	Float [] avoid_angles = {radians(0),radians(45),radians(-45),radians(90),radians(-90),radians(135),radians(-135),radians(180)};
	Float [] avoid_angles2 = {radians(90),radians(-90),radians(135),radians(-135),radians(180)};

	String []oscilatorLabels = { "vlimit","alimit","cohesion","alignment","separation","breathingroom","separationc","cohesionc"};
	String[] Labelsf = {"Total","FrameRate","B room","Cohesion","Separation","Alignment","Max Force","Max Speed","Wanderm","SceneW", "SceneH","B Cohesion","B Separ.","B room B","Tscale","C Cohesion","C Separ.","C Align","Friction","Min","D Sep"};
	String[] EntitiesSubLabels = {"Show","Add Human","Add Race","Follow Bound","Pause",
			"Debug","Adjust","Separation","Cohesion","Alignment","Sliders",
			"Births","Deaths","Load","Save","Reset"};
	String [] debuglist = {"Family Ties", "CZone","Target A", "Target B","Heading","Eyes","info","Hide Sliders","QuadP","Field","Limit",};


	public Entity(){
	};

	public Entity(float xx, float yy,float ww,float hh,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		homex = xx;
		homey = yy;
		homew = xx + ww;
		homeh = yy + hh;
		w = ww;
		h = hh;
		//Boundary = new Boundary();
		scene = new Scene(xx,yy,w,h,bms);
		sliders = new tab(0,100,220,400,"sliders",bms);
		sliders.draggable = true;
		sliders.toggle = true;
		fRate = new sliderBox(70,20,90,10,5,Labelsf,bms);
		fRate.setClassicBar();
		fRate.createFileWriter();
		sliders.add(fRate);
		sliders.setvScroll();
		saveEntities = new fileOutput(Bms);
		Menu = new tab(applet.width-110,applet.height/2-70,110,400,"Options",bms);
		Menu.setvScroll();
		Menu.draggable = true;
		entitiesSublist = new Menu(0,0,90,EntitiesSubLabels,bms);
		entitiesSublist.setclassicBar();
		Menu.add(entitiesSublist);
		Menu.toggle = true;
		Button debug = entitiesSublist.getButton(5);

		entitiesSublist.items.get(5).submenu = new Menu(debug.x-80,debug.y,90,debuglist,Bms);
		entitiesSublist.getSubMenu(5).parentRight = true;
	};


	public Entity(float xx, float yy,int Type){

		x = xx;
		y = yy;

	};

	public float radians(float a) {
		return (float) Math.toRadians(a);
	};

	public void init() {

		polarity = PApplet.floor(applet.random(-2,2));
		function  = PApplet.floor(applet.random(0,6));
		mass = applet.random(1,2);
		radius = applet.random(10,30);
		vel = new PVector(0,0);
		center = new PVector(x,y);
		avoidance = applet.random(0,5);
		cohesion = applet.random(0,5);
		separation = applet.random(0,15);
		separationb = applet.random(0,15);
		separationb1 = separation;
		separationb2 = separationb;
		eating_time = applet.random(10,20);
		valueOffset = applet.random(-offset/2,offset/2);
		countdown = applet.random(10);
		countdownb = countdown;
		dir = applet.random(-2*PConstants.PI,2*PConstants.PI);
		centerf = new PVector(x,y);
		col = applet.color(applet.random(0,256),applet.random(0,256),applet.random(0,256));


	};

	void buttonLogic() {
		
		sliders.setInt(0,0,1,4,this.scene,"limit");
		sliders.set(0,1,0,60);
		sliders.set(0,2,0,200,this,"breathingroom");
		sliders.set(0,3,-100,100,this,"cohesion");
		sliders.set(0,4,-100,100,this,"separation");
		sliders.set(0,5,-100,100,this,"alignment");
		sliders.set(0,6,-50,50,this,"alimit");
		sliders.set(0,7,-50,50,this,"vlimit");
		sliders.set(0,8,0,5,this,"wanderm");
		sliders.set(0,9,50,1440,this,"w");
		sliders.set(0,10,50,720,this,"h");
		sliders.set(0,11,-100,100,this,"cohesionb");
		sliders.set(0,12,-100,100,this,"separationb");
		sliders.set(0,13,0,200,this,"breathingroomb");
		sliders.set(0,14,0,0.5f,this,"tscale");
		sliders.set(0,16,-50,50,this,"separationc");
		sliders.set(0,15,-50,50,this,"cohesionc");
		sliders.set(0,17,-50,50,this,"alignmentc");
		sliders.set(0,18,0,1,this,"mu");
		sliders.set(0,19,0,200,this,"min");
		sliders.set(0,20,-100,100,this,"separationd");
		
		Menu m = Menu.getMenu(0);
		if(m!=null) {
			Menu.toggle(0,0,this,"visible");
			Menu.toggle(0,1,this,"addh");
			Menu.toggle(0,2,this,"boundary");
			Menu.toggle(0,3,this,"followb");
			Menu.toggle(0,4,this,"pause");
			Menu.toggle(0,6,this,"dynamic");
			Menu.toggle(0,7,this,"separationManager");
			Menu.toggle(0,8,this,"cohesionManager");
			Menu.toggle(0,9,this,"alignmentManager");
			Menu.toggle(0,10,this,"showsliders");
			Menu.toggle(0,11,this,"Birth");
			Menu.toggle(0,12,this,"Death");
			Menu.toggle(0,13,this,"reset");
		}else applet.println("entity btnl no menu");

		Menu debug = entitiesSublist.getSubMenu(5);

//		debug.toggle(0,this,"familyties");
//		debug.toggle(1,this,"showbroom");
//		debug.toggle(2,this,"showta");
//		debug.toggle(3,this,"showtb");
//		debug.toggle(4,this,"showheading");
//		debug.toggle(5,this,"showview");
//		debug.toggle(6,this,"info");
//		debug.toggle(7,this,"hidesliders");
//		debug.toggle(8,this.scene,"showq");
//		debug.toggle(9,this.scene,"showf");


	};

	public ArrayList<Human> getNeighbours2(){

		Entity e = Bms.entities.get(ent);

		float max = PApplet.round(Humans.get(race).get(0).maxRange);
		float min = PApplet.floor(Humans.get(race).get(0).minRange);
		//int clip = 0;
		//if(k1>0)clip = k1;
		ArrayList<Quad> n = new ArrayList<Quad>();
		Quad myTile = Bms.qgrid.get(pos);

		for(int i=PApplet.floor(xpos+min);i<=xpos+max;i++){
			for(int j=PApplet.floor(ypos+min);j<=ypos+max;j++){

				if((i+j*scene.gw)<Bms.qgrid.size()&&(i+j*scene.gw)>0){

					Quad q = Bms.qgrid.get((i+j*scene.gw));
					float d = PApplet.dist(xpos,ypos,q.xpos,q.ypos);
					if(d<maxRange){
						if(!neighbourGrid.contains(q))neighbourGrid.add(q);
					}}
			}}

		//neighbourGrid = n;
		ArrayList<Human> n1 = new ArrayList<Human>();

		for(int i=0;i<neighbourGrid.size();i++){
			Quad a = neighbourGrid.get(i);
			for(int j=0;j<a.children.size();j++){
				Human c = (Human) a.children.get(j);
				if(c!=null&&c!=this&&!neighbours.contains(c))neighbours.add(c);
				//else break;
			}}

		neighboursH = n1;
		return neighboursH;
	};

	public void save(){
		//if(save){
		int n = 0;
		saveEntities.checkLocation(saveLocations[n]);
		saveEntities.open();
		for(int i=0;i<Humans.size();i++){

			ArrayList< Human> H = Humans.get(i);

			for(int j=0;j<H.size();j++){
				String s = "[";
				for (int k=0;k<H.get(j).Genes.size();k++) {
					s += H.get(j).genes.get(H.get(j).Genes.get(k)) + ",";
				}

				s = s.substring(0,s.length() - 1)+"]";
				saveEntities.write(s);

			}}

		for(int i=0;i<Humans.size();i++){
			//saveEntities.write("[");
			ArrayList< Human> H = Humans.get(i);
			String s = "[",s1 = "",s2 = "";

			for(int j=0;j<H.get(0).avoidNeighbour.size();j++){
				s += H.get(0).avoidNeighbour.get(j) + ",";
				//saveEntities.write(s);
			}
			for(int j=0;j<H.get(0).seekNeighbour.size();j++){
				s += H.get(0).seekNeighbour.get(j) + ",";
				//saveEntities.write(s1);
			}
			for(int j=0;j<H.get(0).alignNeighbour.size();j++){
				s += H.get(0).alignNeighbour.get(j) + ",";
				//saveEntities.write(s2);
			}
			s+= "]";
			saveEntities.write(s);
		}

		saveEntities.close();
		PApplet.println("entities save",Humans.size());
		n ++;
		//saveEntities.checkLocation(saveLocations[n]);
//		saveEntities.update("entities" ,"plants.txt",saveEntities.counter);
//
//		saveEntities.open();
//		for(int i=0;i<Plants.size();i++){
//			ArrayList< Plant> H = Plants.get(i);
//			for(int j=0;j<H.size();j++){
//				String s = "[";
//				for (int k=0;k<H.get(j).Genes.size();k++) {
//					s += H.get(j).genes.get(H.get(j).Genes.get(k)) + ",";
//				}
//				s = s.substring(0,s.length() - 1)+"]";
//				saveEntities.write(s);
//			}}
//		saveEntities.close();
		// n ++;
		//fRate.savePath = "entities" + "\\" + "sliders.txt";
		fRate.save.update("entities" ,"sliders.txt",saveEntities.counter);
		fRate.save();
		//}
	};

	public void load(){
		int n = 1000;
		String []s = fileUtils.listNames(applet.dataPath("")+"//");
		if(s!=null&&path==null){
			for(int i=0;i<s.length;i++){
				String []s1 = PApplet.match(s[i],"entities");
				if(s1!=null){
					for (int j=n; j>-1;j--){
						String []s2 = PApplet.match(s[j],n + "entities");
						if(s2!=null){
							path = n + "entities";
							break;
						}}}}
			//folder = loadStrings(path);
		}

		if(folder!=null){
			s = fileUtils.listNames(path);

			for(int i=0;i<s.length;i++){
				String []s1 = PApplet.match(s[i],"humans");
				if(s1!=null){

				}
			}
		}

	};

	public void Reset(){
		if(reset){
			//	    Humans = new ArrayList<ArrayList<Human>>();
			//	    Bms.entities.remove(ent);
			//	    spawnRaces("human",1,200);
			//	    btn35.toggle = 0;
			//	    entitiesSublist.toggle = 0;
			//	    reset = false;
		}
	};

	public void move(){

		x += applet.random(-vx,vx);
		y += applet.random(-vy,vy);

	};

	public void create_genes(){

	};

	public void update_Genes(){
		if(updategenes){

			for ( String key : genes.keySet() ) {

				try{
					Field field = key.getClass().getField(key);
					Float strValue = (Float) field.getFloat(key);
					genes.replace(key,strValue);

				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
			updategenes = false;
		}

		//for(int i=0;i<
	};

	public void info(){
		applet.fill(255);
		applet.textSize(20);
		//if(lines.scene.boundary!=null)applet.text(lines.scene.boundary.type,100,80);
		applet.text(Humans.size(),100,100);
		applet.text(Plants.size(),100,120);
		applet.text(Animals.size(),100,140);
		applet.textSize(12);
	};

	@SuppressWarnings("deprecation")
	PVector futurep(){
		fp = PVector.add(p,vel.get().normalize().mult(vlimit));
		return fp;
	};

	PVector futurep(float czone){

		return p.add(vel.normalize().mult(czone/2));
	};

	PVector futurep(PVector a, PVector b, float c){

		return a.add(b.normalize().mult(c/2));
	};

	PVector futurep(Human h,float czone){

		return h.p.add(h.vel.normalize().mult(czone/2));
	};

	PVector futurep(Animal a,float czone){

		return a.p.add(vel.normalize().mult(czone/2));
	};

	PVector futurep(Boundary b,float czone){

		return p.add(vel.normalize().mult(czone/2));
	};

	PVector seek(PVector a){

		PVector d = PVector.sub(a,p);
		d.setMag(vlimit);

		PVector s = PVector.sub(d,vel);
		s.limit(alimit);
		return s;
	};

	public void quadPos(){

		float sw = scene.fields.get(0).w;
		float sh = scene.fields.get(0).h;

		float X = scene.x;
		float Y = scene.y;

		x = p.x;
		y = p.y;
		int xpos = PApplet.floor((x-X)/sw);
		int ypos = PApplet.floor((y-Y)/sh);
		if((xpos + ypos * (scene.cols))<scene.fields.size())pos = (xpos + ypos * (scene.cols));
		// applet.fill(255);
		// applet.text(pos,p.x,p.y);

		if(iid.size()==2&&iid.get(0)!=pos)update = true;
		else update = false;
		if(iid.size()<2)update = true;

		if(iid.size()==0)iid.add(pos);
		else if(iid.get(iid.size()-1)!=pos)iid.add(pos);

		if(iid.size()>2)iid.remove(0);
	};



	public void separationLogic(){
		Entity e = Bms.entities.get(ent);

		int k = -1;
		int col = 255;
		float d1 = 0;
		for(int i=0;i< Humans.size();i++){

			float xx = applet.width/2;
			float yy = applet.height/2;
			float theta = PApplet.radians(360/Humans.size());
			float r = 200;

			float x1 = xx + r * PApplet.cos(theta*i);
			float y1 = yy + r * PApplet.sin(theta*i);

			applet.fill(Humans.get(i).get(0).col);
			float d = PApplet.dist(applet.mouseX,applet.mouseY,x1,y1);
			d1 = PApplet.dist(applet.mouseX,applet.mouseY,xx,yy);

			if(d<10){
				applet.fill(255);
				k = i;
			}

			int count = 0;
			for(int j=0;j< Humans.size();j++){
				float cc = Humans.get(i).get(0).avoidNeighbour.get(j);
				if(cc>-1)count++;
			}

			applet.text(i,x1,y1-20);
			applet.text(eIndex1,x1+20,y1);
			applet.text(eIndex2,x1+20,y1+10);
			applet.text(count,x1-40,y1+10);
			applet.ellipse(x1,y1,20,20);
			applet.ellipse(xx,yy,30,30);
		}

		for(int i=0;i< Humans.size();i++){

			float xx = applet.width/2;
			float yy = applet.height/2;
			float theta = PApplet.radians(360/Humans.size());
			float r = 200;

			float x1 = xx + r * PApplet.cos(theta*i);
			float y1 = yy + r * PApplet.sin(theta*i);


			for(int j=0;j<Humans.get(i).get(0).avoidNeighbour.size();j++){

				float p1 = Humans.get(i).get(0).avoidNeighbour.get(j);

				float x2 = xx + r * PApplet.cos(theta*j);
				float y2 = yy + r * PApplet.sin(theta*j);
				if(p1>0){
					applet.stroke(0);
					applet.ellipse(x2,y2,5,5);

					applet.line(x1,y1,x2,y2);
				}}}

		if(k>-1){
			if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT){
				//		  if(applet.mousePressed){
				for(int j=0;j< Humans.size();j++)Humans.get(k).get(0).avoidNeighbour.set(j,(float) (-1));
				eIndex1 = -1;
				eIndex2 = -1;
			}
			else if(applet.mousePressed&&!e1down&&!e2down&&!mdown){
				eIndex1 = k;
				e1down = true;
				mdown = true;

			}else if(applet.mousePressed&&!e2down&&!mdown&&e1down){
				eIndex2 = k;
				e2down = true;
				mdown = true;
				Humans.get(eIndex1).get(0).avoidNeighbour.set(eIndex2,applet.random(r*2,50));
			}}else {
				if(applet.mousePressed&&Bms.mouseButton==PConstants.LEFT&&!mdown&&d1<15){
					//	        	  if(applet.mousePressed&&!mdown&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							if(j!=i)Humans.get(i).get(0).avoidNeighbour.set(j,applet.random(r*2,50));

						}}}
				else if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT&&!mdown&&d1<15){
					//    		  else if(applet.mousePressed&&!mdown&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							Humans.get(i).get(0).avoidNeighbour.set(j,(float) (-1));

						}}}}

		if(!mdown&&applet.mousePressed){
			e1down = false;
			e2down = false;
			mdown = true;
		}
		if(!applet.mousePressed)mdown = false;
		if(!applet.mousePressed&&e2down&&e1down){
			e1down = false;
			e2down = false;
		}
	};

	public void cohesionLogic(){

		int k = -1;
		int col = 255;
		float d1 = 0;


		for(int i=0;i< Humans.size();i++){

			float xx = applet.width/2;
			float yy = applet.height/2;

			float theta = PApplet.radians(360/Humans.size());
			float r = 200;

			float x1 = xx + r * PApplet.cos(theta*i);
			float y1 = yy + r * PApplet.sin(theta*i);

			applet.fill(Humans.get(i).get(0).col);
			float d = PApplet.dist(applet.mouseX,applet.mouseY,x1,y1);
			d1 = PApplet.dist(applet.mouseX,applet.mouseY,xx,yy);

			if(d<10){
				applet.fill(255);
				k = i;
			}
			int count = 0;
			for(int j=0;j< Humans.size();j++){
				float cc = Humans.get(i).get(0).seekNeighbour.get(j);
				if(cc>-1)count++;
			}

			applet.text(cIndex1,x1+20,y1);
			applet.text(cIndex2,x1+20,y1+10);
			applet.text(count,x1-40,y1+10);
			applet.ellipse(x1,y1,20,20);
			applet.ellipse(xx,yy,30,30);
		}

		for(int i=0;i< Humans.size();i++){

			float xx = applet.width/2;
			float yy = applet.height/2;
			float theta = PApplet.radians(360/Humans.size());
			float r = 200;

			float x1 = xx + r * PApplet.cos(theta*i);
			float y1 = yy + r * PApplet.sin(theta*i);


			for(int j=0;j<Humans.get(i).get(0).seekNeighbour.size();j++){

				float p1 = Humans.get(i).get(0).seekNeighbour.get(j);

				float x2 = xx + r * PApplet.cos(theta*j);
				float y2 = yy + r * PApplet.sin(theta*j);


				if(p1>0){
					applet.stroke(0);
					applet.ellipse(x2,y2,5,5);
					applet.line(x1,y1,x2,y2);
				}}}

		if(k>-1){
			if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT){
				//		  if(applet.mousePressed){
				for(int j=0;j< Humans.size();j++)Humans.get(k).get(0).seekNeighbour.set(j,(float) (-1));

				cIndex1 = -1;
				cIndex2 = -1;
			}
			else if(applet.mousePressed&&!e1down&&!e2down&&!mdown){
				cIndex1 = k;
				e1down = true;
				mdown = true;
			}else if(applet.mousePressed&&!e2down&&!mdown&&e1down){
				cIndex2 = k;
				e2down = true;
				mdown = true;
				Humans.get(cIndex1).get(0).seekNeighbour.set(cIndex2,applet.random(5,40));
				if(cIndex1==cIndex2)Humans.get(cIndex1).get(0).seekNeighbour.set(cIndex2,applet.random(r*2,50));
			}}else {
				if(applet.mousePressed&&Bms.mouseButton==PConstants.LEFT&&!mdown&&d1<15){
					//	        	  if(applet.mousePressed&&!mdown&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							if(j!=i)Humans.get(i).get(0).seekNeighbour.set(j,applet.random(r*2,50));

						}}}
				else if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT&&!mdown&&d1<15){
					//	          else if(applet.mousePressed&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							Humans.get(i).get(0).seekNeighbour.set(j,(float) (-1));

						}}}}

		if(!mdown&&applet.mousePressed){
			e1down = false;
			e2down = false;
			mdown = true;
		}
		if(!applet.mousePressed)mdown = false;
		if(!applet.mousePressed&&e2down&&e1down){
			e1down = false;
			e2down = false;
		}
	};

	public void drawArrangement(ArrayList a){

	};

	public void alignmentLogic(){

		int k = -1;
		int col = 255;
		float d1 = 0;
		for(int i=0;i< Humans.size();i++){

			float xx = applet.width/2;
			float yy = applet.height/2;
			float theta = PApplet.radians(360/Humans.size());
			float r = 200;

			float x1 = xx + r * PApplet.cos(theta*i);
			float y1 = yy + r * PApplet.sin(theta*i);

			applet.fill(Humans.get(i).get(0).col);
			float d = PApplet.dist(applet.mouseX,applet.mouseY,x1,y1);
			d1 = PApplet.dist(applet.mouseX,applet.mouseY,xx,yy);

			if(d<10){
				applet.fill(255);
				k = i;
			}

			int count = 0;
			for(int j=0;j< Humans.size();j++){
				float cc = Humans.get(i).get(0).alignNeighbour.get(j);
				if(cc>-1)count++;
			}

			applet.text(i,x1,y1-20);
			applet.text(aIndex1,x1+20,y1);
			applet.text(aIndex2,x1+20,y1+10);
			applet.text(count,x1-40,y1+10);
			applet.ellipse(x1,y1,20,20);
			applet.ellipse(xx,yy,30,30);

			for(int j=0;j<Humans.get(i).get(0).alignNeighbour.size();j++){

				float p1 = Humans.get(i).get(0).alignNeighbour.get(j);

				float x2 = xx + r * PApplet.cos(theta*j);
				float y2 = yy + r * PApplet.sin(theta*j);

				if(p1>0){
					applet.stroke(0);
					applet.ellipse(x2,y2,5,5);
					applet.line(x1,y1,x2,y2);
				}}}

		if(k>-1){
			if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT){
				//	  if(applet.mousePressed){
				for(int j=0;j< Humans.size();j++)Humans.get(k).get(0).alignNeighbour.set(j,(float) (-1));

				aIndex1 = -1;
				aIndex2 = -1;
			}
			else if(applet.mousePressed&&!e1down&&!e2down&&!mdown){
				aIndex1 = k;
				e1down = true;
				mdown = true;
			}else if(applet.mousePressed&&!e2down&&!mdown&&e1down){
				aIndex2 = k;
				e2down = true;
				mdown = true;
				Humans.get(aIndex1).get(0).alignNeighbour.set(aIndex2,applet.random(r*2,50));
			}}else {
				if(applet.mousePressed&&Bms.mouseButton==PConstants.LEFT&&!mdown&&d1<15){
					//	        	if(applet.mousePressed&&!mdown&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							if(j!=i)Humans.get(i).get(0).alignNeighbour.set(j,applet.random(r*2,50));

						}}}
				//	          	else if(applet.mousePressed&&!mdown&&d1<15){
				else if(applet.mousePressed&&Bms.mouseButton==PConstants.RIGHT&&!mdown&&d1<15){
					mdown = true;
					for(int i=0;i< Humans.size();i++){
						for(int j=0;j< Humans.size();j++){

							Humans.get(i).get(0).alignNeighbour.set(j,(float) (-1));

						}}}}

		if(!mdown&&applet.mousePressed){
			e1down = false;
			e2down = false;
			mdown = true;
		}
		if(!applet.mousePressed)mdown = false;
		if(!applet.mousePressed&&e2down&&e1down){
			e1down = false;
			e2down = false;
		}
	};

	public void manageBoundaries(){

		for(int i=0;i<Bms.main.Boundaries.size();i++){
			Boundary b = Bms.main.Boundaries.get(i);

			//if(b.mtranslate()&&applet.mousePressed&&)
		}

	};

	PVector seek2(PVector a){

		PVector desired = PVector.sub(a,p);  // A vector pointing from the position to the target

		// Normalize desired and scale to maximum speed
		desired.normalize();
		desired.mult(vlimit);
		// Steering = Desired minus Velocity
		PVector steer = PVector.sub(desired,vel);
		return steer.limit(alimit);  // Limit to maximum steering force

		//ac.add(steer);
	};

	public void applyFriction(){
		//Friction = mu*mass*-1;
		friction = vel.get();
		friction.mult(-1); 
		friction.normalize();
		friction.mult(mu*alimit*50);
		PVector n = PVector.div(friction,mass);
		//friction.setMag(mu*mass*-1);
		ac.add(n);
		//vel.limit(vlimit);
		// friction.setMag(mu*mass*-1);
		// vel.x *= friction.x;
		// vel.y *= friction.y;
	};

	PVector separate(){

		float desiredseparation = breathingroom;
		PVector steer = new PVector(0,0);
		int count = 0;
		float t = 0;
		// For every Human in the system, check if it's too close
		Entity e = Bms.entities.get(ent);
		for (int j=0;j<proximity.size();j++) {

			Entity other = proximity.get(j);

			float d = PVector.dist(p,other.p);
			// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)

			if (d<(radius+other.radius)/2) {
				// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(p,other.p);
				diff.normalize();
				diff.div(radius);        // Weight by distance
				steer.add(diff);
				count++;            // Keep track of how many
				//t+= Humans.get(race).get(0).avoidNeighbour.get(other.race);
			}
			// if (race==other.race) {
			//   // Calculate vector pointing away from neighbor
			//   PVector diff = PVector.sub(p,other.p);
			//   diff.normalize();
			//   diff.div(radius);        // Weight by distance
			//   steer.add(diff);
			//   count++;            // Keep track of how many
			//   //t+= Humans.get(race).get(0).avoidNeighbour.get(other.race);
			// }

		}
		// Average -- divide by how many
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;
	};

	PVector separate_(){

		PVector steer = new PVector(0,0);
		int count = 0;
		int count1 = 0;
		// For every Human in the system, check if it's too close
		for (int j=0;j<prox.size();j++) {

			Entity other = prox.get(j);

			float d = PVector.dist(p,other.p);
			// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)

			if (d<(radius+other.radius)/2) {
				// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(p,other.p);
				diff.normalize();
				diff.div((radius+other.radius)/2);        // Weight by distance
				steer.add(diff);
				count++;            // Keep track of how many
			}
		}
		// Average -- divide by how many//

		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(100);
			steer.sub(vel);
			steer.limit(100);
		}
		return steer;
	};

	PVector separate2_(){

		PVector steer = new PVector(0,0);
		int count = 0;
		int count1 = 0;
		Entity e = Bms.entities.get(ent);
		// For every Human in the system, check if it's too close
		for (int j=0;j<prox.size();j++) {

			Entity other = prox.get(j);

			float d = PVector.dist(p,other.p);
			// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)

			if (d<min&&Humans.get(race).get(0).avoidNeighbour.get(other.race)>0) {
				// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(p,other.p);
				diff.normalize();
				diff.div((radius+other.radius)/2);        // Weight by distance
				steer.add(diff);
				count++;            // Keep track of how many
			}}
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;
	};

	PVector separate2(ArrayList<Human> a){

		PVector steer = new PVector(0,0);
		int count = 0;
		// For every Human in the system, check if it's too close
		for (int j=0;j<a.size();j++) {

			Entity other = a.get(j);

			float d = PVector.dist(p,other.p);
			// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)

			if (other.avoid2) {
				// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(p,other.p);
				diff.normalize();
				diff.div((radius+other.radius)/2);        // Weight by distance
				steer.add(diff);
				count++;            // Keep track of how many
			}}
		// Average -- divide by how many
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;

	};

	public void repel(ArrayList<Human> a){

		separate2(a);
		cohesion2(a);

	};

	PVector separateBoundary(Window s){

		float desiredseparation = breathingroomb;

		PVector steer = new PVector(0,0);
		mult1 = 1;
		int count = 0;
		// For every Human in the system, check if it's too close

		for ( int i=0;i<s.Boundaries.size();i++) {

			Boundary C = s.Boundaries.get(i);

			if(C.state==1||C.state==3){


				for(int j=0;j<C.Boundaries.size();j++){

					Boundary B = C.Boundaries.get(j);

					if(B.state1==1||B.state1==3){

						PVector a = new PVector(B.x1,B.y1);
						PVector b = new PVector(B.x2,B.y2);

						PVector c = find_normal(fp,a,b);
						float d = PVector.dist(fp,c);

						// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
						if ((d > 10) && ((d < desiredseparation+B.broom))&&check_lineP(a,b,c)) {

							// Calculate vector pointing away from neighbor
							mult1 += B.avoidance;
							PVector diff = PVector.sub(p,c);
							diff.normalize();
							diff.div(d);        // Weight by distance
							steer.add(diff);
							count++;            // Keep track of how many
						}}}}}
		// Average -- divide by how many
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			mult1 /=count;
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;
	};

	PVector cohesionf() {

		float neighbordist = breathingroom;
		PVector sum = new PVector(0,0);   // Start with empty vector to accumulate all pitions
		int count = 0;

		for (int j=0;j<proximity.size();j++) {

			Entity other = proximity.get(j);

			float d = PVector.dist(p,other.p);
			float d2 = radius + other.radius;

			if (d>breathingroom&&Humans.get(race).get(0).seekNeighbour.get(other.race)>0) {
				sum.add(other.p); // Add pition
				count++;
			}}
		if (count > 0) {
			sum.div((float)count);
			return seek(sum);  // Steer towards the pition
		}
		return sum;
	};

	PVector cohesionf_() {

		PVector sum = new PVector(0,0);   // Start with empty vector to accumulate all pitions
		int count = 0;

		for (int j=0;j<prox.size();j++) {

			Entity other = prox.get(j);

			float d = PVector.dist(p,other.p);

			if (d>(radius+other.radius)/2&&other.race==race) {
				sum.add(other.p); // Add pition
				count++;
			}
		}
		if (count > 0) {
			sum.div((float)count);
			return seek(sum);  // Steer towards the pition
		}
		return sum;
	};

	PVector cohesion2(ArrayList<Human> a) {

		PVector sum = new PVector(0,0);   // Start with empty vector to accumulate all pitions
		int count = 0;

		for (int j=0;j<a.size();j++) {

			Entity other = a.get(j);

			float d = PVector.dist(p,other.p);

			if (other.seek) {
				sum.add(other.p); // Add pition
				count++;
			}}
		if (count > 0) {
			sum.div((float)count);
			return seek(sum);  // Steer towards the pition
		}
		return sum;
	};

	PVector cohesionfBoundary(Window s) {

		float neighbordist = breathingroomb;
		PVector sum = new PVector(0,0);   // Start with empty vector to accumulate all pitions
		mult2 = 1;
		int count = 0;

		for ( int i=0;i<s.Boundaries.size();i++) {

			Boundary C = s.Boundaries.get(i);

			if(C.state==1||C.state==2){

				for(int j=0;j<C.Boundaries.size();j++){

					Boundary B = C.Boundaries.get(j);

					if(B.state1==1||B.state1==2){

						PVector a = new PVector(B.x1,B.y1);
						PVector b = new PVector(B.x2,B.y2);

						PVector c = find_normal(p,a,b);
						float d = PVector.dist(p,c);
						if ((d > radius) && (d < neighbordist+B.broom)&&check_lineP(a,b,c)){
							mult2 += B.cohesion;
							sum.add(c); // Add position
							count++;
						}}}}}
		if (count > 0) {
			mult2 /= count;
			sum.div((float)count);
			return seek(sum);  // Steer towards the position
		}
		return sum;

	};

	PVector align() {
		float neighbordist = breathingroom;
		PVector steer = new PVector(0,0);
		int count = 0;

		for (int j=0;j<proximity.size();j++) {

			Entity other = proximity.get(j);

			if (Humans.get(race).get(0).alignNeighbour.contains(other.race)) {
				steer.add(other.vel);
				count++;
			}}
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;
	};

	PVector align2(ArrayList<Human> h) {

		PVector steer = new PVector(0,0);
		int count = 0;

		for (int j=0;j<h.size();j++) {

			Entity other = h.get(j);

			if (other.align) {
				steer.add(other.vel);
				count++;
			}}
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(vlimit);
			steer.sub(vel);
			steer.limit(alimit);
		}
		return steer;
	};

	PVector align_() {
		float neighbordist = breathingroom;
		PVector steer = new PVector(0,0);
		int count = 0;

		for (int j=0;j<family.size();j++) {

			Entity other = family.get(j);
			float d = PApplet.dist(x,y,other.x,other.y);

			if (other.race==race) {
				steer.add(other.vel);
				count++;
			}}
		if (count > 0) {
			steer.div((float)count);
			// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(50);
			steer.sub(vel);
			steer.limit(50);
		}
		return steer;
	};


	public void flockBoundary(){
		PVector sepB = separateBoundary(Bms.main);   // Separation
		PVector cohB = cohesionfBoundary(Bms.main);
		PVector wan = wander(); //wander

		cohB.mult(cohesionb*50);
		sepB.mult(separationb*50);
		wan.mult(wanderm);

		ac.add(sepB);
		ac.add(cohB);
		ac.add(wan);
		//update();
	};

	public void flockNeer(){

		PVector sep = new PVector();   // Separation
		//PVector sep2 = new PVector();
		PVector coh = new PVector();   // Cohesion
		//PVector ali = new PVector();   // Alignment

		if(prox.size()>0){
			sep = separate_();
			//sep2 = separate2_();
			coh = cohesionf_();
			// ali = align_();

			coh.mult(cohesionc);
			sep.mult(separationc*(vlimit/50)*50);
			//sep2.mult(separation);
			//ali.mult(alignmentc);
			coh.limit(vlimit);
			sep.limit(vlimit);
			//applyFriction();
			ac.add(sep);
			ac.add(coh);
			// //ac.add(sep2);
			// ac.add(ali);
		}
		//ac.add(ali);
	};

	public void flock(){
		Quad field = null;
		futurep();
		if(pos>0&&pos<scene.cols*scene.rows)field = scene.fields.get(pos);
		if(proximity.size()>0)flockSep();
		if(prox2.size()>0)flockSP();
		if(prox.size()>0)flockNeer();
		flockBoundary();
	};

	public void oscilator(){

		if(id==0){
			//noStroke();
			applet.noFill();



			for(int i=0;i<oscilatorLabels.length;i++){

				String a = oscilatorLabels[i];

				float c = (float) (oscilatorCounters.get(i) + 0.01);

				oscilatorCounters.set(i ,c);

				float b = oscilators.get(i) + 2*PApplet.sin(c);
				oscilators.set(i,b);


				Field field = null;

				try{
					field = this.getClass().getField(a); 
					field.set(this, field.getFloat(this) + 5*PApplet.sin(c)); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
			//applet.ellipse(p.x,p.y,50,50);

		}
	};

	public void flockSep() {

		Quad field = null;

		PVector sep = new PVector();

		sep = separate();   // Separation
		sep.mult(separationd);
		//sep.limit(vlimit)
		sep.limit(vlimit);
		//  ali.mult(alignment*(field.dens+1));
		//  coh.mult(cohesion*(field.dens+1));
		applyFriction();
		ac.add(sep);
	};


	public void flockSP(){

		PVector sep = new PVector(0,0);
		PVector sep2 = new PVector(0,0);
		PVector coh = new PVector(0,0);
		PVector ali = new PVector(0,0);
		Entity e = Bms.entities.get(ent);
		for(int i=0;i<prox2.size();i++){

			ArrayList< Human> h = prox2.get(i);
			ArrayList< Human> h1 = proximity2.get(i);

			if(h.size()>0){
				float avoid_ = e.Humans.get(race).get(0).avoidNeighbour.get(i);
				float seek_ = e.Humans.get(race).get(0).seekNeighbour.get(i);
				float align_ = e.Humans.get(race).get(0).alignNeighbour.get(i);
//				 if(id==0)applet.text(race + " Scount " + scount + " " + seek_,100,300+40*race+10*i);
//				 if(id==0)applet.text(race +" Acount " + acount + " " + avoid_,250,300+40*race+10*i);
//				 if(id==0)applet.text(race + " Alcount " + alcount + " " + align_,400,300+40*race+10*i);

				if(scount>0&&seek_>-1)
					coh.add(cohesion2(h).mult(seek_+cohesion));
				if(acount>0&&avoid_>-1)
					sep.add(separate2(h).mult(avoid_+separation));
				if(alcount>0&&align_>-1)
					ali.add(align2(h).mult(align_+alignment));
				if(mcount>0&&avoid_>-1)
					sep2.add(separate2(h).mult((avoid_+separationd)*50));
				//update2();
			}}
		coh.limit(vlimit);
		sep.limit(vlimit);
		ali.limit(vlimit);
		sep2.limit(vlimit);
		applyFriction();
		ac.add(coh);
		ac.add(sep);
		ac.add(sep2);
		ac.add(ali);
		//update();

	};

	public void flockFamily(){

		if(family.size()>0){

			PVector sep2 = new PVector(0,0);   // Separation
			PVector coh2 = new PVector(0,0);      // Alignment

			sep2 = separate2(family);
			coh2 = cohesion2(family);
			//PVector ali = align_();

			coh2.mult(cohesionc);
			sep2.mult(separationc);
			//ali.mult(1);

			ac.add(sep2);
			ac.add(coh2);
		}
		//update2();

	};


	PVector wander() {

		float wanderR = 25;         // Radius for our "wander circle"
		float wanderD = 80;         // Distance for our "wander circle"
		float change = 0.3f;
		wandertheta += applet.random(-wanderm,wanderm);     // Randomly change wander theta

		// Now we have to calculate the new position to steer towards on the wander circle
		PVector circlepos = vel.get();    // Start with velocity
		circlepos.normalize();            // Normalize to get heading
		circlepos.mult(wanderD);          // Multiply by distance
		circlepos.add(p);               // Make it relative to boid's position

		float h = vel.heading2D();        // We need to know the heading to offset wandertheta

		PVector circleOffSet = new PVector(wanderR*PApplet.cos(wandertheta+h),wanderR*PApplet.sin(wandertheta+h));
		PVector t = PVector.add(circlepos,circleOffSet);
		//if(debug) drawWanderStuff(p,circlepos,t,wanderR);
		return seek2(t);

		// Render wandering circle, etc.

	};

	public void drawWanderStuff(PVector position, PVector circle, PVector target, float rad) {
		applet.stroke(0);
		applet.noFill();
		applet.ellipseMode(PConstants.CENTER);
		applet.ellipse(circle.x,circle.y,rad*2,rad*2);
		applet.ellipse(target.x,target.y,4,4);
		applet.line(p.x,p.y,circle.x,circle.y);
		applet.line(circle.x,circle.y,target.x,target.y);
	};

	public void collisions(ArrayList<Human> a){

		for(int i=0;i<a.size();i++){

			Entity h =  a.get(i);

			checkCollision(h);
		}

	};

	public void checkCollision(Entity other) {

		// Get distances between the balls components
		PVector distanceVect = PVector.sub(other.p, p);

		// Calculate magnitude of the vector separating the balls
		float distanceVectMag = distanceVect.mag();

		// Minimum distance before they are touching
		float minDistance = radius + other.radius;

		if (distanceVectMag < minDistance) {
			float distanceCorrection = (float) ((minDistance-distanceVectMag)/2.0);
			PVector d = distanceVect.copy();
			PVector correctionVector = d.normalize().mult(distanceCorrection);
			PVector o = other.p.copy();
			o.add(correctionVector);
			o.sub(correctionVector);

			// get angle of distanceVect
			float theta  = distanceVect.heading();
			// precalculate trig values
			float sine = PApplet.sin(theta);
			float cosine = PApplet.cos(theta);

			/* bTemp will hold rotated ball positions. You 
	     just need to worry about bTemp[1] position*/
			PVector[] bTemp = {
					new PVector(), new PVector()
			};

			/* this ball's position is relative to the other
	     so you can use the vector between them (bVect) as the 
	     reference point in the rotation expressions.
	     bTemp[0].position.x and bTemp[0].position.y will initialize
	     automatically to 0.0, which is what you want
	     since b[1] will rotate around b[0] */
			bTemp[1].x  = cosine * distanceVect.x + sine * distanceVect.y;
			bTemp[1].y  = cosine * distanceVect.y - sine * distanceVect.x;

			// rotate Temporary velocities
			PVector[] vTemp = {
					new PVector(), new PVector()
			};

			vTemp[0].x  = cosine * vel.x + sine * vel.y;
			vTemp[0].y  = cosine * vel.y - sine * vel.x;
			vTemp[1].x  = cosine * other.vel.x + sine * other.vel.y;
			vTemp[1].y  = cosine * other.vel.y - sine * other.vel.x;

			/* Now that velocities are rotated, you can use 1D
	     conservation of momentum equations to calculate 
	     the final velocity along the x-axis. */
			PVector[] vFinal = {  
					new PVector(), new PVector()
			};

			// final rotated velocity for b[0]
			vFinal[0].x = ((mass - other.mass) * vTemp[0].x + 2 * other.mass * vTemp[1].x) / (mass + other.mass);
			vFinal[0].y = vTemp[0].y;

			// final rotated velocity for b[1]
			vFinal[1].x = ((other.mass - mass) * vTemp[1].x + 2 * mass * vTemp[0].x) / (mass + other.mass);
			vFinal[1].y = vTemp[1].y;

			// hack to apublic void clumping
			bTemp[0].x += vFinal[0].x;
			bTemp[1].x += vFinal[1].x;

			/* Rotate ball positions and velocities back
	     Reverse signs in trig expressions to rotate 
	     in the opposite direction */
			// rotate balls
			PVector[] bFinal = { 
					new PVector(), new PVector()
			};

			bFinal[0].x = cosine * bTemp[0].x - sine * bTemp[0].y;
			bFinal[0].y = cosine * bTemp[0].y + sine * bTemp[0].x;
			bFinal[1].x = cosine * bTemp[1].x - sine * bTemp[1].y;
			bFinal[1].y = cosine * bTemp[1].y + sine * bTemp[1].x;

			// update balls to screen position
			other.p.x = p.x + bFinal[1].x;
			other.p.y = p.y + bFinal[1].y;

			p.add(bFinal[0]);

			// update velocities
			vel.x = cosine * vFinal[0].x - sine * vFinal[0].y;
			vel.y = cosine * vFinal[0].y + sine * vFinal[0].x;
			other.vel.x = cosine * vFinal[1].x - sine * vFinal[1].y;
			other.vel.y = cosine * vFinal[1].y + sine * vFinal[1].x;
		}
	};

	public void assignVectors(){

		futurep();
		p = new PVector(x,y);
		vel = new PVector(vx,vy);

	};

	public void steer_awayIntersection(ArrayList<Human> H,float czone){

		ArrayList<PVector> temp = new ArrayList<PVector>();

		for(int i=0;i<H.size();i++){

			Human h = H.get(i);

			Boundary b1 = new Boundary(x,y,fp.x,fp.y,Bms);
			Boundary b2 = new Boundary(h.x,h.y,h.fp.x,h.fp.y,Bms);

			PVector i1 = Boundary.check_intersect(b1,b2);

			if(i1!=null){

				for(int j=0;j<avoid_angles.length;j++){

					float t1 = avoid_angles[j];
					float t2 = PApplet.atan2(i1.y-y,i1.x-x);

					PVector next = new PVector(x + czone/2 * PApplet.cos(t1+t2),y + czone/2 * PApplet.sin(t1+t2));

					PVector ap = futurep(h,czone/2);

					Boundary b3 = new Boundary(x,y,next.x,next.y,Bms);
					Boundary b4 = new Boundary(h.x,h.y,ap.x,ap.y,Bms);

					PVector i2 = Boundary.check_intersect(b3,b4);

					if(i2==null){
						temp.add(next);
					}
					if(temp.size()>0){
						//if (target ==null&&targetb==null&&!familyties)      seekddw(greatestTo(temp,center));
						//else if (target ==null&&targetb==null&&familyties)  seekddw(greatestTo(temp,center));
						//else if (target !=null&&targetb==null&&!familyties) seekddw(greatestTo(temp,target));
						//else if (target ==null&&targetb!=null&&!familyties) seekddw(greatestTo(temp,targetb));
					}}}}
	};

	boolean check_lineP(PVector a, PVector b,PVector c){

		boolean k = false;
		float d1 = PApplet.dist(a.x,a.y,b.x,b.y);
		float d2 = PApplet.dist(a.x,a.y,c.x,c.y);
		float d3 = PApplet.dist(b.x,b.y,c.x,c.y);
		float d4 = d2 + d3;

		if(d4 <= d1 + 0.05 && d4 >= d1 - 0.05)k = true;

		return k;
	};

	public void steer_awayIntersection(ArrayList<Boundary> H){

		ArrayList<PVector> temp = new ArrayList<PVector>();

		for(int i=0;i<H.size();i++){

			Boundary C = H.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary h = C.Boundaries.get(j);

				Boundary b1 = new Boundary(x,y,fp.x,fp.y,Bms);
				Boundary b2 = new Boundary(h.x1,h.y1,h.x2,h.y2,Bms);

				PVector i1 = Boundary.check_intersect2(b1,b2);


				if(i1!=null&&!stop&&PApplet.dist(i1.x,i1.y,x,y)<breathingroom/2){

					applet.fill(col);
					applet.ellipse(i1.x,i1.y,10,10);
					stop = true;
					for(int k=0;k<avoid_angles2.length;k++){

						float t1 = avoid_angles[k];
						float t2 = PApplet.atan2(i1.y-y,i1.x-x);

						PVector next = new PVector(x + breathingroom/2 * PApplet.cos(t1+dir),y + breathingroom/2 * PApplet.sin(t1+dir));

						Boundary b3 = new Boundary(x,y,next.x,next.y,Bms);

						PVector i2 = Boundary.check_intersect2(b3,b2);

						if(i2==null){
							temp.add(next);
						}
						if(temp.size()>0){
							//if (target ==null&&targetb==null&&!familyties)      seekddw(shortestTo(temp,fp));
							//else if (target ==null&&targetb==null&&familyties)  seekddw(shortestTo(temp,center));
							//else if (target !=null&&targetb==null&&!familyties) seekddw(shortestTo(temp,target));
							//else if (target ==null&&targetb!=null&&!familyties) seekddw(shortestTo(temp,targetb));
						}}}}}
	};

	public void steer_awayZone(ArrayList<Human> H,float czone){

		ArrayList<PVector> temp = new ArrayList<PVector>();

		for(int i=0;i<H.size();i++){

			Human h = H.get(i);

			float d1 = PApplet.dist(x,y,h.x,h.y);

			if(d1<czone/2+h.breathingroom/2){

				float t1 = PApplet.atan2(h.y-y,h.x-x);

				for(int j=0;j<avoid_angles.length;j++){

					float t2 = avoid_angles[j];

					PVector next = new PVector(x + czone/2 * PApplet.cos(t2+t1),y + czone/2 * PApplet.sin(t2+t1));

					float d2 = PApplet.dist(next.x,next.y,h.x,h.y);

					if(d2>czone/2+h.breathingroom/2){
						temp.add(next);
					}
					if(temp.size()>0){
						//if (target ==null&&targetb==null&&!familyties)      seekddw(greatestTo(temp,center));
						//else if (target ==null&&targetb==null&&familyties)  seekddw(greatestTo(temp,center));
						//else if (target !=null&&targetb==null&&!familyties) seekddw(greatestTo(temp,target));
						//else if (target ==null&&targetb!=null&&!familyties) seekddw(greatestTo(temp,targetb));
					}}}}
	};

	PVector greatestTo(ArrayList<PVector> d,PVector h){

		PVector k = d.get(0);

		for(int i=0;i<d.size();i++){

			PVector a = d.get(i);

			float d1 = PApplet.dist(a.x,a.y,h.x,h.y);
			float d2 = PApplet.dist(k.x,k.y,h.x,h.y);

			if(d1>d2) k = a;
		}

		return k;
	};

	PVector shortestTo(ArrayList<PVector> d,PVector h){

		PVector k = d.get(0);

		for(int i=0;i<d.size();i++){

			PVector a = d.get(i);

			float d1 = PApplet.dist(a.x,a.y,h.x,h.y);
			float d2 = PApplet.dist(k.x,k.y,h.x,h.y);

			if(d1<d2) k = a;
		}

		return k;
	};

	public void seek_boundary(Scene s){

		for(int i=0;i<s.Boundaries.size();i++){

			Boundary C = s.Boundaries.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary B = C.Boundaries.get(j);

				PVector a = new PVector(B.x1,B.y1);
				PVector b = new PVector(B.x2,B.y2);

				PVector v = PVector.sub(p,a);
				PVector v2 = PVector.sub(b,a);

				v2.normalize();
				v2.mult(v.dot(v2));
				v2 = v2.add(a);

				//applet.line(x,y,v2.x,v2.y);
				//if(applet.dist(x,y,v2.x,v2.y)>=breathingroom) seekddw(v2);
			}}
	};

	public void seek_boundary(ArrayList<Boundary> s){

		for(int i=0;i<s.size();i++){


			Boundary C = s.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary B = C.Boundaries.get(j);

				PVector a = new PVector(B.x1,B.y1);
				PVector b = new PVector(B.x2,B.y2);

				PVector v = PVector.sub(p,a);
				PVector v2 = PVector.sub(b,a);

				v2.normalize();
				v2.mult(v.dot(v2));
				v2 = v2.add(a);

				//applet.line(x,y,v2.x,v2.y);
				//if(applet.dist(x,y,v2.x,v2.y)>=breathingroom) seekddw(v2);
			}}
	};

	public void avoid_boundary(Scene s){

		Boundary A = shortestToCenter(s.Boundaries);
		Boundary B = shortestToCenter(A.Boundaries);

		PVector a = new PVector(B.x1,B.y1);
		PVector b = new PVector(B.x2,B.y2);

		PVector v = PVector.sub(p,a);
		PVector v2 = PVector.sub(b,a);

		PVector v3 = PVector.sub(fp,a);
		PVector v4 = PVector.sub(b,a);

		v2.normalize();
		v2.mult(v.dot(v2));
		v2 = v2.add(a);

		v4.normalize();
		v4.mult(v3.dot(v4));
		v4 = v4.add(a);

		float d1 = PApplet.dist(fp.x,fp.y,v4.x,v4.y);
		float d2 = PApplet.dist(x,y,B.x1,B.y1);
		float d3 = PApplet.dist(x,y,B.x2,B.y2);

		applet.stroke(col);
		applet.line(x,y,v2.x,v2.y);
		applet.ellipse(v2.x,v2.y,10,10);

		//if(d1<=breathingroom)avoiddw(v4);
		//else if(d2<=breathingroom)avoiddw(new PVector(B.x1,B.y1));
		//else if(d3<=breathingroom)avoiddw(new PVector(B.x2,B.y2));


	};

	public void avoid_boundary2(Scene s){

		for(int i=0;i<s.Boundaries.size();i++){

			Boundary C = s.Boundaries.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary B = C.Boundaries.get(j);

				PVector a = new PVector(B.x1,B.y1);
				PVector b = new PVector(B.x2,B.y2);

				PVector v = PVector.sub(p,a);
				PVector v2 = PVector.sub(b,a);

				v2.normalize();
				v2.mult(v.dot(v2));
				v2 = v2.add(a);

				PVector v3 = PVector.sub(fp,a);
				PVector v4 = PVector.sub(b,a);

				v4.normalize();
				v4.mult(v3.dot(v4));
				v4 = v4.add(a);

				float d0 = PApplet.dist(x,y,v2.x,v2.y);
				float d1 = PApplet.dist(fp.x,fp.y,v4.x,v4.y);
				float d2 = PApplet.dist(x,y,B.x1,B.y1);
				float d3 = PApplet.dist(x,y,B.x2,B.y2);

				//applet.stroke(col);
				//applet.line(x,y,v2.x,v2.y);
				//applet.ellipse(v2.x,v2.y,10,10);

				//if(d1<=breathingroom/2)avoiddw(v4);
				//else if(d0<=breathingroom/2)avoiddw(v2);
				//else if(d2<=breathingroom/2)avoiddw(new PVector(B.x1,B.y1));
				//else if(d3<=breathingroom/2)avoiddw(new PVector(B.x2,B.y2));
			}}

	};

	public void avoid_boundary(ArrayList<Boundary> s){

		for(int i=0;i<s.size();i++){

			Boundary C = s.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary B = C.Boundaries.get(j);

				PVector a = new PVector(B.x1,B.y1);
				PVector b = new PVector(B.x2,B.y2);

				PVector v = PVector.sub(p,a);
				PVector v2 = PVector.sub(b,a);

				v2.normalize();
				v2.mult(v.dot(v2));
				v2 = v2.add(a);

				//applet.line(x,y,v2.x,v2.y);
				//if(applet.dist(x,y,v2.x,v2.y)<=30) avoid(v2);
			}}
	};

	public void follow_boundary(ArrayList<Boundary> s, float radius){

		if(s.size()>0){

			Boundary B = shortestTo(s);

			Boundary b1 = shortestTo(B.Boundaries);

			PVector a = new PVector(b1.x1,b1.y1);
			PVector b = new PVector(b1.x2,b1.y2);

			PVector n = find_normal(p,a,b);

			float d1 = PApplet.dist(x,y,n.x,n.y);
			float d2 = PApplet.dist(x,y,a.x,a.y);
			//float d2 = applet.dist(x,y,a.x,a.y);

			if(d1>radius){
				targetb = n;
			}else {
				targetb = b;
			}

			//seekddw(targetb);
		}
	};

	public void follow_boundary(Scene s,float radius){

		Boundary B = shortestTo(s.Boundaries.get(0).Boundaries);

		PVector a = new PVector(B.x1,B.y1);
		PVector b = new PVector(B.x2,B.y2);

		PVector n = find_normal(p,a,b);

		float d1 = PApplet.dist(x,y,n.x,n.y);
		float d2 = PApplet.dist(x,y,a.x,a.y);

		if(d1>radius){
			targetb = n;
		}else {
			targetb = b;
		}

		//seekddw(targetb);
	};

	Boundary shortestTo(ArrayList<Boundary> B){

		Boundary k = null;

		if(B.size()>0){

			k = B.get(0);;

			for(int i=1;i<B.size();i++){

				Boundary A = B.get(i);
				PVector a = new PVector(A.x1,A.y1);

				float d1 = PApplet.dist(a.x,a.y,x,y);
				float d2 = PApplet.dist(k.x1,k.y1,x,y);

				if(d1<d2) k = A;
			}}

		return k;

	};

	Boundary shortestToCenter(ArrayList<Boundary> B){

		Boundary k = null;

		if(B.size()>0){

			k = B.get(0);;

			for(int i=1;i<B.size();i++){

				Boundary A = B.get(i);
				PVector a = new PVector(A.center.x,A.center.y);

				float d1 = PApplet.dist(a.x,a.y,x,y);
				float d2 = PApplet.dist(k.center.x,k.center.y,x,y);

				if(d1<d2) k = A;
			}}

		return k;

	};

	PVector  find_normal(PVector p, PVector a, PVector b){

		PVector v = PVector.sub(p,a);
		PVector v2 = PVector.sub(b,a);

		v2.normalize();
		v2.mult(v.dot(v2));
		v2 = v2.add(a);

		return v2;
	};

	PVector[] sum(ArrayList<Human>a){

		PVector v = new PVector(0,0);
		PVector p = new PVector(0,0);

		for(int i=0;i<a.size();i++){

			Human e = a.get(i);

			p.add(e.p);
			v.add(e.vel);
		}
		p.div(family.size());
		v.div(family.size());

		PVector []sum = {p,v};

		return sum;

	};

	public void eyes(){

		applet.fill(col3,20);
		applet.noStroke();
		applet.arc(p.x + 10 * PApplet.cos(dir),p.y + 10 * PApplet.sin(dir),200,200,dir - PApplet.radians(fov),dir + PApplet.radians(fov));
		//applet.line(x + 10 * applet.cos(dir),y + 10 * applet.sin(dir),x + 110 * applet.cos(dir-applet.radians(fov)),y + 110 * applet.sin(dir-applet.radians(fov)));
		//applet.line(x + 10 * applet.cos(dir),y + 10 * applet.sin(dir),x + 110 * applet.cos(dir+applet.radians(fov)),y + 110 * applet.sin(dir+applet.radians(fov)));

	};

	public void eye(ArrayList<Human> a){

		float d = 0;
		vcount = 0;

		for(int i=0;i<a.size();i++){

			Human e = a.get(i);
			float d1 = PApplet.dist(p.x,p.y,e.x,e.y);
			if(e!=this&&d1<breathingroom){
				d = PApplet.atan2(p.y - e.y,p.x - e.x) + PConstants.PI;
				if(d>=PApplet.abs(dir)-PApplet.radians(fov)&&d<=PApplet.abs(dir)+PApplet.radians(fov))vcount++;

			}}
		if(vcount>0){
			highlight = true;
			col3 = hcol;
		}
		else {
			highlight = false;
			col3 = colb;
		}
	};

	public void eyes(PVector m){

		float d1 = PApplet.dist(x,y,m.x,m.y);
		float t1 = PApplet.atan2(p.y - m.y, p.x - m.x) + PConstants.PI;
		//float t2 = applet.atan2(m.y - p.y, m.x - p.x) + applet.PI;

		if(d1<200&&t1>dir-PApplet.radians(fov) && t1 < dir + PApplet.radians(fov)){highlight = true;}
		else {highlight = false;}
	};

	public void highlight(){
		if(highlight) col3 = hcol;
		else col3 = colb;
	};

	public void rotate(){
		dir+=0.1;
	};

	public void wander(float a){

		float t2 = 0;
		assignVectors();

		if(!turn&&!turnh){
			dir += PApplet.radians(applet.random(-10,10));
			fp = futurep();
			tri_boundaryf.tri.center = fp;
			tri_boundaryf.tri.rotate2(dir);
			PVector desired = new PVector(p.x + vel.x * PApplet.cos(dir), p.y + vel.y * PApplet.sin(dir));
			seek(desired);
			//applet.fill(col);
			//applet.ellipse(fp.x,fp.y,10,10);
		}
		if(!turn&&!turnh){
			if(!check_inter(scene.Boundaries,fp)){
				if(proximity.size()==0||!check_interh(proximity,fp)){


				}else{ turnh = true;bdir = dir;}}else{ turn = true;bdir = dir;}}
		if(turn||turnh){
			inc ++;
			neg = PApplet.pow(-1,inc);

			dir = bdir + (PApplet.radians(10+inc)*neg)+ (PApplet.radians((id+1)+inc)*neg);
			fp = futurep();
			tri_boundaryf.tri.center = fp;
			tri_boundaryf.tri.rotate2(dir);

			PVector desired = new PVector(p.x + vel.x * PApplet.cos(dir), p.y + vel.y * PApplet.sin(dir));

			if(!check_inter(scene.Boundaries,fp2)){
				if(!check_interh(proximity,fp2)){

					seek(desired);
					turn = false;
					turnh = false;
				}}else{
				}}
	};

	Boolean check_inter(ArrayList<Boundary> B,PVector a){

		boolean k = false;

		for(int i=0;i<B.size();i++){

			Boundary C = B.get(i);

			for(int j=0;j<C.Boundaries.size();j++){

				Boundary h = C.Boundaries.get(j);

				Boundary b1 = new Boundary(p.x,p.y,a.x,a.y,Bms);
				Boundary b2 = new Boundary(h.x1,h.y1,h.x2,h.y2,Bms);

				PVector i1 = Boundary.check_intersect2(b1,b2);

				if(a.x==h.x1||a.x==h.x2){
					current_boundary = h;
					k = true;
					turn = true;
					return true;
				}

				else if(i1!=null&&PApplet.dist(i1.x,i1.y,p.x,p.y)<PApplet.dist(a.x,a.y,p.x,p.y)){
					current_boundary = h;
					k = true;
					turn = true;

					return true;
				}}}

		return k;

	};

	Boolean check_inter(Boundary B,PVector a){

		boolean k = false;

		Boundary h = B;

		Boundary b1 = new Boundary(p.x,p.y,a.x,a.y,Bms);
		Boundary b2 = new Boundary(h.x1,h.y1,h.x2,h.y2,Bms);

		PVector i1 = Boundary.check_intersect(b1,b2);

		if(a.x==h.x1||a.x==h.x2||a.y == h.y1||a.y == h.y2||i1!=null){

			k = true;
			return true;
		}
		return k;

	};

	Boolean check_interh(ArrayList<Human> B,PVector a){

		boolean k = false;

		for(int i=0;i<B.size();i++){

			Human h = B.get(i);
			float d1 = PApplet.dist(p.x,p.y,a.x,a.y);
			float d2 = PApplet.dist(h.p.x,h.p.y,a.x,a.y);
			float d3 = PApplet.dist(h.p.x,h.p.y,p.x,p.y);
			if(h!=this){
				//fp = new PVector(x+vlimit*applet.cos(dir),x+vlimit*applet.sin(dir));
				Boundary b1 = new Boundary(p.x,p.y,a.x,a.y,Bms);
				Boundary b2 = new Boundary(h.p.x,h.p.y,h.p.x + h.vlimit * PApplet.cos(h.dir),h.p.y + h.vlimit*PApplet.sin(h.dir),Bms);

				PVector i1 = Boundary.check_intersect2(b1,b2);

				if(i1!=null&&PApplet.dist(i1.x,i1.y,x,y)<20){
					applet.stroke(col);
					applet.fill(col);
					applet.line(p.x,p.y,i1.x,i1.y);
					applet.ellipse(i1.x,i1.y,10,10);
					k = true;
					turnh = true;
				}else
					if(tri_boundaryf.pos(new PVector(h.fp.x + 10 * PApplet.cos(h.dir),h.fp.y + 10 * PApplet.sin(h.dir)))||
							tri_boundaryf.pos(new PVector(h.fp.x + 10 * PApplet.cos(dir+PApplet.radians(120)),h.fp.y + 10 * PApplet.sin(dir+PApplet.radians(120))))||
							tri_boundaryf.pos(new PVector(h.fp.x + 10 * PApplet.cos(dir-PApplet.radians(120)),h.fp.y + 10 * PApplet.sin(dir-PApplet.radians(120))))||
							h.tri_boundaryf.pos(new PVector(fp.x + 10 * PApplet.cos(dir),fp.y + 10 * PApplet.sin(dir)))||
							h.tri_boundaryf.pos(new PVector(fp.x + 10 * PApplet.cos(dir+PApplet.radians(120)),fp.y + 10 * PApplet.sin(dir+PApplet.radians(120))))||
							h.tri_boundaryf.pos(new PVector(fp.x + 10 * PApplet.cos(dir-PApplet.radians(120)),fp.y + 10 * PApplet.sin(dir-PApplet.radians(120))))){
						applet.stroke(col);
						applet.noFill();
						applet.line(p.x,y,p.x+vlimit*PApplet.cos(dir),p.y+vlimit*PApplet.sin(dir));
						applet.ellipse(p.x+vlimit*PApplet.cos(dir),p.y+vlimit*PApplet.sin(dir),10,10);
						turnh = true;
						k = true;
					}}}

		return false;

	};

	Boolean check_interh(Human B,PVector a){

		boolean k = false;
		Human h = B;

		Boundary b1 = new Boundary(p.x,p.y,a.x,a.y,Bms);
		Boundary b2 = new Boundary(h.p.x,h.p.y,h.fp.x,h.fp.y,Bms);

		PVector i1 = Boundary.check_intersect2(b1,b2);
		if(a.x==h.x||a.x==h.x){
			//turn = true;
			//k = true;
			//return true;
		}

		else if(i1!=null&&PApplet.dist(i1.x,i1.y,p.x,p.y)<PApplet.dist(x,y,fp.x,fp.y)){
			applet.fill(col);
			applet.stroke(col);
			applet.line(p.x,p.y,i1.x,i1.y);
			applet.ellipse(i1.x,i1.y,10,10);
			k = true;
			turn = true;

		}else obstructionh = null;

		return k;

	};


	public void draw_future(){
		applet.stroke(col);
		applet.fill(col);

		if(fp.x!=0&&fp.y!=0)applet.line(x,y,fp.x,fp.y);
		applet.ellipse(fp.x,fp.y,10,10);

	};

	public void arrive(PVector a){

		x = PApplet.lerp(p.x,a.x,(float) 0.5);
		y = PApplet.lerp(p.y,a.y,(float) 0.5);
		dir = PApplet.atan2(a.y-p.y,a.x-p.x);
	};

	PVector [] sumf(ArrayList<Human> a){

		float sumx = 0;
		float sumy = 0;
		float sumd = 0;
		int total = 0;

		PVector c = new PVector(0,0);
		PVector c2 = new PVector(0,0);

		for(int i=0;i<a.size();i++){

			Human e = a.get(i);
			e.p.x = e.x;
			e.p.y = e.y;

			c.add(e.p);
			c2.add(e.vel);
		}

		c.div(a.size());
		c2.div(a.size());

		centerf = new PVector(c.x,c.y);
		centervelf = new PVector(c2.x,c2.y);

		velf = centervel;
		PVector c3 = new PVector(dir,dir);
		PVector [] temp = {c,c2,c3};

		return temp;

	};

	PVector [] sumv(ArrayList<Human> a){

		PVector c = new PVector(0,0);
		PVector c2 = new PVector(0,0);

		for(int i=0;i<a.size();i++){

			Human e = a.get(i);

			e.p.x = e.x;
			e.p.y = e.y;
			c.add(e.p);
			c2.add(e.vel);
		}

		c.div((a.size()-1));
		c2.div((a.size()-1));

		center = c;
		centervel = c2;


		float dx = PApplet.abs(x - center.x);
		float dy = PApplet.abs(y - center.y);

		vel = new PVector(vx,vy);

		PVector c3 = new PVector(dir,dir);
		PVector [] temp = {c,c2,c3};

		return temp;

	};

	public void logic(){

		if(dir>=2*PConstants.PI) dir = dir%(2*PConstants.PI);
		if(dir<=-2*PConstants.PI) dir = dir%(-2*PConstants.PI);
	};

	public void draw_broom(){
		applet.stroke(col);
		applet.noFill();
		applet.ellipse(p.x,p.y,breathingroom,breathingroom);
	};

	public void fties(){
		applet.stroke(col);
		if(centerf.x!=0&&centerf.y!=0)applet.line(p.x,p.y,centerf.x,centerf.y);
		applet.fill(col);
		applet.ellipse(centerf.x,centerf.y,20,20);

	};

	float limit(float a, float l){
		if(a>=l) a = l;
		if(a<=-l)a = -l;

		return a;
	};

	public void update_boundary(){
		if(tri_boundary!=null){
			tri_boundary.tri.update_points(tri_boundary.tri.Boundaries);
		}
	};


	public void showtargeta(){
		if(target!=null){
			applet.text("T",x+25,y);
			Plant P = targetfruit;
			applet.stroke(col);
			applet.line(P.x,P.y,p.x,p.y);
		}
	};
	public void showtargetb(){
		if(targetb!=null){
			PVector P = targetb;
			applet.stroke(col);
			applet.line(p.x,p.y,P.x,P.y);
		}
	};

	public void wrap2(){

		if(p.x>scene.x+scene.w) p.x=scene.x+1;
		if(p.x<scene.x) p.x = scene.x+scene.w-1;
		if(p.y>scene.y+scene.h) p.y = scene.y+1;
		if(p.y<+scene.y) p.y = scene.y+scene.h-1;
	};

	public void wrap(){
		if(p.x >= applet.width) p.x = 1;
		if(p.x <= 0) p.x = applet.width - 1;
		if(p.y >= applet.height) p.y = 1;
		if(p.y <= 0) p.y = applet.height - 1;
	};

	public void boundary_functions(){

		tri_boundary.draw();
		//tri_boundaryf.draw();

		tri_boundary.tri.center = p;

		tri_boundary.tri.rotate2(dir);

		tri_boundaryf.tri.center = fp;

		tri_boundaryf.tri.rotate2(dir);

		tri_boundary.bg = col;
		tri_boundaryf.bg = col;

	};

	public void fboundary_functions(){

		tri_boundaryf.tri.center.x = fp.x;
		tri_boundaryf.tri.center.y = fp.y;
		tri_boundaryf.tri.rotate2(dir);
		tri_boundaryf.bg = col;
	};


	public void p_update(){

	};

	public void update() {
		// Update velocity
		vel.add(ac);

		// Limit speed

		vel.limit(vlimit);
		dir = PApplet.atan2(vel.y,vel.x) - PConstants.PI;
		p.add(vel);
		// Reset accelertion to 0 each cycle

		ac.mult(0);
	}
	public void update2() {
		// Update velocity
		vel.add(ac);
		// Limit speed
		vel.limit(vlimit);
		dir = PApplet.atan2(vel.y,vel.x) - PConstants.PI;
		p.add(vel);
		// Reset accelertion to 0 each cycle

	}
	
	public void debugsp() {
		applet.text("separationc: "+separationc, 200, 200+20);
		applet.text("broom: "+ breathingroom, 200, 200+20);
		applet.text("vlimit: "+ vlimit, 200, 200+20);
		applet.text("alimit: "+alimit, 200, 200+20);
		applet.text("cohesion: "+cohesion, 200, 200+20);
		applet.text("alignment: "+alignment, 200, 200+20);
	};

	public void runh(){
		a1 += 0.2;
		a2 += 0.5;
		a3 += 0.9;
		a4 += 1.2;
		a5 += 1.5;
		if(a1>=5)a1=0;
		if(a2>=10)a2=0;
		if(a3>=12)a3=0;
		if(a4>=14)a4=0;
		if(a5>=15)a5=0;
		
		if(mouse==null) mouse = new PVector(applet.mouseX,applet.mouseY);
		else mouse.set(applet.mouseX,applet.mouseY);
		if(toggle){
//			if(Humans.size()>0)applet.text("humans:",Humans.get(0).size(),100,110);
			if(dynamic)applet.text("dynamic: true", 200, 200);
//			applet.fill(0);
//			
//			applet.text("cohesionc: ", cohesionc, 200, 200+40);
//			applet.text("alignmentc: ",alignmentc, 200, 200+60);
//			applet.text("alignment: ",alignment, 200, 200+80);
//			applet.text("breathingroom: ",breathingroom, 200, 200+100);
//			applet.text("vlimit: ",vlimit, 200, 200+120);
//			applet.text("alimit: ",alimit, 200, 200+140);
			scene.w = w;
			scene.h = h;
			if(!pause)scene.field();
			buttonLogic();
			Menu.displayTab();
			Menu.setvScroll(0,200);
			sliders.displayTab();
			sliders.setvScroll(0,200);
			//fRate.save.location =  saveEntities.folderPath + "/"  + saveEntities.counter +"sliders.txt";
			//folderPath + "/" + coun
			//fRate.savePath = "entities\\" + 

			if(cohesionManager)cohesionLogic();
			if(separationManager)separationLogic();
			if(alignmentManager)alignmentLogic();

			if(!dynamic)drawtotalh = Bms.entities.get(ent).Humans.size()-1;
			if(!entitiesSublist.pos())newHuman(mouse);


			for ( int i=0;i<Humans.size();i++) {
				ArrayList<Human> H = Humans.get(i);

				for(int j=H.size()-1;j>-1;j--){


					Human h = H.get(j);

					h.proximity2 = new ArrayList<ArrayList<Human>>();
					h.family = new ArrayList<Human>();
					h.prox = new ArrayList<Human>();
					h.prox2 = new ArrayList<ArrayList<Human>>();

					for (int l=0;l<hRaces;l++){
						h.prox2.add(new ArrayList<Human>());
						h.proximity2.add(new ArrayList<Human>());
					}

					h.scene = scene;
					h.alcount = 0;h.acount = 0;h.scount = 0;h.mcount=0;
					

					for ( int k=0;k<Humans.size();k++) {

						ArrayList<Human> H1 = Humans.get(k);

						for(int l=H1.size()-1;l>-1;l--){

							Human h1 = H1.get(l);

							float d = PApplet.dist(h.x,h.y,h1.x,h1.y);

							if(d<h.breathingroom&&h1!=h)h.proximity.add(h1);
							h1.avoid = false;
							h1.seek = false;
							h1.align = false;
							h1.avoid2 = false;

							if(d<h.radius+h1.radius&&h1!=h&&h.race==h1.race)h.family.add(h1);

							if(h1!=h){
								if(
										Humans.get(h.race).get(0).alignNeighbour.get(h1.race)>-1
										//&&d>h.min
										&&d<Humans.get(h.race).get(0).alignNeighbour.get(h1.race)+h.breathingroom){
									if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
									h.alcount++;
									h1.align = true;
								}


								if( (Humans.get(h.race).get(0).seekNeighbour.get(h1.race)>-1
										//&&d>h.min
										&&d<Humans.get(h.race).get(0).seekNeighbour.get(h1.race)+h.breathingroom)){
									if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
									h.scount++;
									h1.seek = true;
								}

								if(
										Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)>-1
										//&&d>h.min
										&&d<Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)+h.breathingroom){
									if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
									h.acount++;
									h1.avoid2 = true;
								}
								if(Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)>-1&&d<h.min&&h.min>=0
										&&d<Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)+h.breathingroom
										//&&!h.prox2.get(h1.race).contains(h1)
										&&h.race!=h1.race
										){
									if(!h.proximity2.get(h1.race).contains(h1))h.proximity2.get(h1.race).add(h1);
									h.mcount ++;
									h1.avoid2 = true;
								}}
							if(d<h.min&&h1!=h)h.prox.add(h1);

						}}
					//if(info){

					//h.life(h.proximity);
					assignVariable(h);
					if(!pause){
						//h.wander();
						
						h.quadPos();
						h.update();
						h.applyFriction();
						h.wrap2();
						if(h.update)h.update_qgrid();
						//h.collisions(h.proximity);
						h.flock();

						// if(h.id==0)applet.text(h.friction.x,200,150+10*h.race);
						// if(h.id==0)applet.text(h.scount,250,150+10*h.race);
						// h.oscilator();

					}
					h.dir = PApplet.atan2(h.vel.y,h.vel.x);
					if(visible)h.boundary_functions();
					else {
						applet.fill(h.col);
						applet.noStroke();
						applet.ellipse(h.p.x,h.p.y,h.radius,h.radius);
					}

				}
				if(info&&!applet.mousePressed)
					for(int j=Humans.size()-1;j>-1;j--){

						Human h = Humans.get(i).get(0);

						float cc = Humans.get(i).get(0).seekNeighbour.get(j);
						int count = 0;

						for(int k=0;k<Humans.get(i).get(0).prox2.get(j).size();k++){
							Human h1 = Humans.get(i).get(0).prox2.get(j).get(k);
							count++;
							//if(j==0)applet.line(h.p.x,h.p.y,h1.p.x,h1.p.y);
						}
						applet.fill(0);
						applet.text(i,150+100*i,100);
						applet.text(count,130+100*i,110+20*j);
						applet.text("race: " + j + " " + cc,150+100*i,120+10*j);
						applet.text("Vlimit: " + h.vlimit+", "+vlimit,150+100*i,160);
						applet.text("Alimit: " + h.alimit+", "+alimit,150+100*i,170);
						applet.text("Coh: " + h.cohesion+", "+cohesion,150+100*i,180);
						applet.text("Sep: " + h.separation+", "+separation,150+100*i,190);
						applet.text("Broom: " + h.breathingroom+", "+breathingroom,150+100*i,190);
					}
			}
			Reset();
			//if(!hidesliders)
		}
		//else fRate.menu.toggle=0;
	};

	public void runh_(){
		a1 += 0.2;
		a2 += 0.5;
		a3 += 0.9;
		a4 += 1.2;
		a5 += 1.5;
		if(a1>=5)a1=0;
		if(a2>=10)a2=0;
		if(a3>=12)a3=0;
		if(a4>=14)a4=0;
		if(a5>=15)a5=0;
		PVector m = new PVector(applet.mouseX,applet.mouseY);
		if(toggle){
			Entity e = Bms.entities.get(ent);
			if(Bms.entities.size()>=ent&&e.Humans.size()>0)applet.text(e.Humans.get(0).size(),100,100);
			e.scene.w = e.w;
			e.scene.h = e.h;
			if(!pause)e.scene.field();

			entitiesSublist.draw();
			//fRate.save.location =  saveEntities.folderPath + "/"  + saveEntities.counter +"sliders.txt";
			//folderPath + "/" + coun
			//fRate.savePath = "entities\\" + 
			//proximity = getNeighbours2();

			if(cohesionManager)cohesionLogic();
			if(separationManager)separationLogic();
			if(alignmentManager)alignmentLogic();

			if(!dynamic)drawtotalh = Bms.entities.get(ent).Humans.size()-1;
			if(!entitiesSublist.pos())newHuman(m);



			for ( int i=0;i<e.Humans.size();i++) {
				ArrayList<Human> H = e.Humans.get(i);

				for(int j=H.size()-1;j>-1;j--){


					Human h = H.get(j);

					h.proximity2 = new ArrayList<ArrayList<Human>>();
					h.family = new ArrayList<Human>();
					h.prox = new ArrayList<Human>();
					h.prox2 = new ArrayList<ArrayList<Human>>();

					for (int l=0;l<hRaces;l++){
						h.prox2.add(new ArrayList<Human>());
						h.proximity2.add(new ArrayList<Human>());
					}

					h.scene = e.scene;
					h.alcount = 0;h.acount = 0;h.scount = 0;h.mcount=0;


					for ( int k=0;k<h.proximity.size();k++) {

						Human h1 = h.proximity.get(k);

						float d = PApplet.dist(h.x,h.y,h1.x,h1.y);

//						if(d<h.breathingroom&&h1!=h)h.proximity.add(h1);
						h1.avoid = false;
						h1.seek = false;
						h1.align = false;
						h1.avoid2 = false;
						

						if(d<h.radius+h1.radius&&h1!=h&&h.race==h1.race)h.family.add(h1);

						if(h1!=h){
							if(
									e.Humans.get(h.race).get(0).alignNeighbour.get(h1.race)>-1
									//&&d>h.min
									&&d<e.Humans.get(h.race).get(0).alignNeighbour.get(h1.race)+h.breathingroom){
								if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
								h.alcount++;
								h1.align = true;
							}


							if( (e.Humans.get(h.race).get(0).seekNeighbour.get(h1.race)>-1
									//&&d>h.min
									&&d<e.Humans.get(h.race).get(0).seekNeighbour.get(h1.race)+h.breathingroom)){
								if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
								h.scount++;
								h1.seek = true;
							}

							if(
									e.Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)>-1
									//&&d>h.min
									&&d<e.Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)+h.breathingroom){
								if(!h.prox2.get(h1.race).contains(h1))h.prox2.get(h1.race).add(h1);
								h.acount++;
								h1.avoid = true;
							}
							if(e.Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)>-1&&d<h.min&&h.min>=0
									&&d<e.Humans.get(h.race).get(0).avoidNeighbour.get(h1.race)+h.breathingroom
									//&&!h.prox2.get(h1.race).contains(h1)
									&&h.race!=h1.race
									){
								if(!h.proximity2.get(h1.race).contains(h1))h.proximity2.get(h1.race).add(h1);
								h.mcount ++;
								h1.avoid2 = true;
							}}
						if(d<h.min&&h1!=h)h.prox.add(h1);

					}
					//if(info){

					//h.life(h.proximity);
					assignVariable(h);

					if(!pause){
						//h.wander();
						h.quadPos();
						h.update();
						h.applyFriction();
						h.wrap2();
						if(h.update)h.update_qgrid();
						h.getNeighbours2();
						//h.collisions(h.proximity);
						h.flock();

						// if(h.id==0)applet.text(h.friction.x,200,150+10*h.race);
						// if(h.id==0)applet.text(h.scount,250,150+10*h.race);
						// h.oscilator();

					}
					h.dir = PApplet.atan2(h.vel.y,h.vel.x);
					if(visible)h.boundary_functions();
					else {
						applet.fill(h.col);
						applet.noStroke();
						applet.ellipse(h.p.x,h.p.y,h.radius,h.radius);
					}

				}
				if(info)
					for(int j=e.Humans.size()-1;j>-1;j--){

						Human h = e.Humans.get(i).get(0);

						float cc = e.Humans.get(i).get(0).seekNeighbour.get(j);
						int count = 0;

						for(int k=0;k<e.Humans.get(i).get(0).prox2.get(j).size();k++){
							Human h1 = e.Humans.get(i).get(0).prox2.get(j).get(k);
							count++;
							//if(j==0)applet.line(h.p.x,h.p.y,h1.p.x,h1.p.y);
						}

						applet.text(i,150+100*i,100);
						applet.text(count,130+100*i,110+20*j);
						applet.text("race: " + j + " " + cc,150+100*i,120+10*j);
						applet.text("Vlimit: " + h.vlimit,150+100*i,160);
						applet.text("Alimit: " + h.alimit,150+100*i,170);
						applet.text("Coh: " + h.cohesion,150+100*i,180);
						applet.text("Sep: " + h.separation,150+100*i,190);
					}
			}Reset();
			//if(!hidesliders)
			fRate.draw();
		}
		//else fRate.menu.toggle=0;
	}; 

	public void assignVariable(Entity h){
		if(dynamic){
			h.vlimit = h.vlimitb+vlimit;
			h.alimit = h.alimitb+alimit;
			h.cohesion = h.cohesionb1+cohesion;
			h.cohesionb = h.cohesionb2+cohesion;
			h.alignment = h.alignmentb+alignment;
			h.separation = h.separationb1+separation;
			h.separationb = h.separationb2 + separationb;
			h.separationd = h.separationdb + separationd;
			h.breathingroom = h.breathingroomb1+breathingroom;
			h.breathingroomb = h.breathingroomb2+breathingroomb;
			h.tscale = tscale;


		}else {
			h.alimit = h.alimitb;
			h.vlimit = h.vlimitb;
			h.cohesion = h.cohesionb1;
			h.cohesionb = h.cohesionb2;
			h.alignment = h.alignmentb;
			h.separation = h.separationb1;
			h.separationb = h.separationb2;
			h.wanderm = h.wandermb;
			h.breathingroom = h.breathingroomb1;
			h.breathingroomb = h.breathingroomb2;
			h.tscale = h.tscale;
			h.separationb = separationb;
			h.separationd = h.separationdb;
		}
		h.separationc = separationc;
		h.cohesionc = cohesionc;
		h.alignmentc = -alignmentc;
		h.mu = mu;
		h.min = h.minb +min;
		

		//h.prox = h.proximity;
	};
	
	void debugs() {
	};

	public void runh(Scene s){
		a1 += 0.2;
		a2 += 0.5;
		a3 += 0.9;
		a4 += 1.2;
		a5 += 1.5;
		if(a1>=5)a1=0;
		if(a2>=10)a2=0;
		if(a3>=12)a3=0;
		if(a4>=14)a4=0;
		if(a5>=15)a5=0;
		PVector m = new PVector(applet.mouseX,applet.mouseY);
		if(toggle){

			Entity e = Bms.entities.get(ent);
			if(Bms.entities.size()>=ent&&e.Humans.size()>0)applet.text(e.Humans.get(0).size(),100,100);
			//e.scene.w = e.w;
			//e.scene.h = e.h;
			s.field();

			entitiesSublist.draw();
			//if(!hidesliders)
			fRate.draw();
			if(!dynamic)drawtotalh = Bms.entities.get(ent).Humans.size()-1;
			if(!entitiesSublist.pos())newHuman(m);

			for ( int i=0;i<e.Humans.size();i++) {
				ArrayList<Human> H = e.Humans.get(i);

				for(int j=H.size()-1;j>-1;j--){

					Human h = H.get(j);
					h.proximity = new ArrayList<Human>();
					h.scene = s;


					for(int k=0;k<e.Humans.size();k++){
						ArrayList<Human> HH = e.Humans.get(k);
						for(int l=0;l<HH.size();l++){

							Human h2 = HH.get(l);
							float d = PApplet.dist(h.x,h.y,h2.x,h2.y);

							if(d<h.breathingroom/2&&h2!=h)h.proximity.add(h2);

						}}
					h.dir = PApplet.atan2(h.vel.y,h.vel.x);
					h.wander();
					h.quadPos();
					h.update();
					h.wrap2();
					h.update_qgrid();
					
					if(!pause)h.flock();
					if(!hidesliders&&dynamic){
						h.vlimit = vlimit;
						h.alimit = alimit;
						h.cohesion = cohesion;
						h.alignment = alignment;
						h.separation = separation;
						h.wanderm = wanderm;
						h.breathingroom = breathingroom;
					}
					//h.prox = h.proximity;


					if(visible)h.boundary_functions();

				}}Reset();}
	};

	public void runp(){

	};

	public void draw(){
		tri_boundary.draw();
	};

	public void draw2(){
		//flock();
	};

	public void desiredTrait(){
		for(int i=0;i<3;i++){

			int r = PApplet.floor(applet.random(Genes.size()-1));
			desiredTraits.add(Genes.get(r));
		}
	};

	public void mate(){

	};

	public void newHuman(PVector m){

		if(toggle&&addh&&applet.mousePressed){

			int Race = PApplet.floor(applet.random(Humans.size()));
			//int Ent = ent;
			int ID = Humans.get(Race).size()-1;

			Human entity = new Human(m.x,m.y,ID,Race,ent,this);

			entity.lifespan = applet.random(minageh,maxageh);
			entity.carrying_capacity = PApplet.floor(applet.random(0,6));

			Humans.get(Race).add(entity);
		}

	};

	public void life(){
		//boolean retry = false;
		//boolean try_ = false

		Entity e = Bms.entities.get(ent);

		if((real&&!pause)&&(Death||Birth)){

			for ( int i=0;i<e.Humans.size();i++) {
				ArrayList<Human> H = e.Humans.get(i);

				for(int j=H.size()-1;j>-1;j--){

					Human h = H.get(j);

					h.col2 = (int) (applet.map(h.age,0,h.lifespan,(float) 0.0,255));

					if(Birth){
						Boolean k = h.births;

						h.age += h.tscale;
						h.w += h.tscale/2;
						h.h += h.tscale/2;
						h.dice = 0;

						if(h.age>=h.lifespan/4&&h.age<=h.lifespan-h.lifespan/4&&h.child<h.carrying_capacity)h.births = true;
						if(h.child > h.carrying_capacity) h.births = false;

						if(h.births) h.delay ++;
						else h.delay = 0;

						if(h.delay > h.lifespan*0.9){h.try_ = true;}
						else h.try_ = false;h.conception = false;

						if(h.try_)h.dice = applet.random( 0.001f,10);
						applet.fill(255);
						applet.text(h.prox.size(),x+20,y);
						if(h.dice>0&&h.dice<h.carrying_capacity/(h.prox.size()+1) && h.delay >0&&(h.age > applet.random(h.lifespan*1/4,h.lifespan*3/4)))
						{h.conception = true;h.delay = 0; h.dice = 0 ;}
						else h.conception = false;

						if(h.conception){

							Human entity = new Human(h.x + applet.random(-20,20),h.y + applet.random(-20,20),Humans.get(h.race).size(),h.race,h.ent,this);
							entity.parent = h;
							h.Children.add(entity);
							h.child ++;
							Humans.get(h.race).add(entity);
							h.conception = false;

						}}

					if((h.age>h.lifespan||h.health<=0)&&Death){


						Humans.get(h.race).remove(i);

					}}}}


	};



	public void fam(){

	};

	public void updateplants(){

	};

	public void seek_food(){
		if(hunger>90 && !idle){

		}
		if(hunger>50 && idle){

		}

	};

	public void seek(){

	};

	public void p_in_space(){

	};


	public void add(){

		if(applet.mousePressed){

		}
	};

	boolean p(){
		float X = applet.mouseX;
		float Y = applet.mouseY;
		return X > x -w/2 && X < x + w/2 && Y > y -h/2 && Y < y + h/2;
	};

	public void create(int n,int type){

		for(int i=0;i<n;i++){

			float x = applet.random(0,applet.width);
			float y = applet.random(0,applet.height);
			int k = Bms.entities.get(ent).Mazecrawlers.get(race).size()-1;
			if(type==0){
				Bms.entities.get(ent).Humans.get(race).add(new Human(x,y,k,species.id,ent,this));
			}else if(type==1){

				Bms.entities.get(ent).Animals.get(race).add(new Animal(x,y,k,species.id,ent));
			}else if(type==2){
				Bms.entities.get(ent).Plants.get(race).add(new Plant(x,y,k,species.id,ent));
			}else if(type==3){

				Bms.entities.get(ent).Mazecrawlers.get(race).add(new mazeCrawler(x,y,k,species.id,ent));
			}
		}

	};

	public void setBms(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
	};

	public void spawnRaces(String a, int b,int num){
		ent = Bms.entities.size();
		hRaces = b;
		int pos = ent;
		float a1 = 0;
		for(int i=0;i<8;i++){
			oscilators.add(a1);
			oscilatorCounters.add(a1);
		};
		//e.saveEntities.checkLocation(e.saveLocations[0]);

		// e.saveEntities.location = e.saveLocations[0];
		// e.saveEntities.folderPath = "Bms.entities"; 
		// e.saveLocation = e.saveLocations[0];
		if(a=="Human"||a=="human"||a=="humans"||a=="Humans"){

			for(int i=0;i<b;i++){

				ArrayList<Human> race = new ArrayList<Human>();
				int c = applet.color(applet.random(255),applet.random(255),applet.random(255));

				float r = applet.random(10,20);
				//r = 10;
				float br = applet.random(r*2,50);
				float vlimit = applet.random(5,10);
				vlimit = 5;
				float alimit = applet.random(0.1f,0.9f);
				alimit = 0.1f;
				float min = applet.random(r*2,50);
				float m = 0;
				for(int j=0;j<num;j++){
					Human h = new Human(applet.random(scene.w),applet.random(scene.h),j,i,pos,c,this);

					h.scene = scene;

					h.radius = r + applet.random(-m,m);

					h.oscilators = oscilators;

					h.oscilatorCounters = oscilatorCounters;

					h.breathingroom = br + applet.random(-m,m);
					
					h.breathingroomb = h.breathingroom;

					h.vlimit = vlimit + applet.random(-m,m);

					h.vlimitb = h.vlimit;

					h.alimit = alimit+ applet.random(-m,m);

					h.alimitb = alimit;

					h.mass = (float) (h.radius*0.4+ applet.random(-m,m));

					h.min = min+ applet.random(-m,m);

					h.minb = h.min;
					race.add(h);
				}
				Humans.add(race);
			}
			//saveEntities.close();

			for(int k=0;k<b;k++){
				//saveEntities.write_("[");
				for(int l=0;l<b;l++){
					Humans.get(k).get(0).seekNeighbour.add((float) (-1));
					Humans.get(k).get(0).avoidNeighbour.add((float) (-1));
					Humans.get(k).get(0).alignNeighbour.add((float) (-1));
					Humans.get(k).get(0).prox2.add(new ArrayList<Human>());
				}
				float maxAvoid = 0;
				float minAvoid = 1000000;
				float maxSeek = 0;
				float minSeek = 1000000;
				float minAlign = 1000000;
				float maxAlign = 0;
				float maxRange = 0;
				float minRange = 100000;
				for(int l=0;l<b;l++){
					int p = PApplet.floor(applet.random(20));
					int p1 = PApplet.floor(applet.random(20));
					int p2 = PApplet.floor(applet.random(20));
					if(l!=k){

						if(p>=10){
							float m1 = applet.random(0,50);
							Humans.get(k).get(0).seekNeighbour.set(l,m1);
							Humans.get(k).get(0).maxSeek = maxSeek;
							Humans.get(k).get(0).minSeek = minSeek;
							if(a1>maxSeek)maxSeek = m1;
							if(a1<minSeek)minSeek = m1;
						}
						else Humans.get(k).get(0).seekNeighbour.set(l,(float) (-1));

						if(p1>=10){
							float m1 = applet.random(0,50);
							Humans.get(k).get(0).avoidNeighbour.set(l,m1);
							if(a1>maxAvoid)maxAvoid = m1;
							if(a1<minAvoid)minAvoid = m1;
						}
						else Humans.get(k).get(0).avoidNeighbour.set(l,(float) (-1));

						if(p2>=10){
							float m1 = applet.random(0,50);
							Humans.get(k).get(0).alignNeighbour.set(l,m1);
							Humans.get(k).get(0).maxAlign = maxAlign;
							Humans.get(k).get(0).minAlign = minAlign;
							if(a1<minAlign)minAlign = m1;
							if(a1>maxAlign)maxAlign = m1;
						}
						else Humans.get(k).get(0).alignNeighbour.set(l,(float) (-1));

						Humans.get(k).get(0).maxRange = maxRange;
						Humans.get(k).get(0).minRange = minRange;

					}
					if(l==k){
						Humans.get(k).get(0).seekNeighbour.set(k,(float) (-1));

						Humans.get(k).get(0).avoidNeighbour.set(k,(float) (-1));
						Humans.get(k).get(0).alignNeighbour.set(k,(float) (-1));

					}}}
//			save();

		}

		if(a=="Plant"||a=="plant"||a=="plants"||a=="Plants"){
			for(int i=0;i<b;i++){

				ArrayList<Plant> species = new ArrayList<Plant>();

				for(int j=0;j<num;j++){

					Plant p = new Plant(applet.random(scene.w),applet.random(scene.h),i,j,pos);

					p.scene = scene;

					species.add(p);

				}Plants.add(species); }}
		if(a=="Mineral"||a=="mineral"||a=="minerals"||a=="Minerals"){
			for(int i=0;i<b;i++){

				ArrayList<Mineral> species = new ArrayList<Mineral>();

				for(int j=0;j<b;j++){

					Mineral m = new Mineral(applet.random(scene.w),applet.random(scene.h),i,j,pos);

					m.scene = scene;

					species.add(m);

				}Minerals.add(species); }}

		if(a=="Animal"||a=="animal"||a=="Animals"||a=="animals"){
			for(int i=0;i<b;i++){

				ArrayList<Animal> species = new ArrayList<Animal>();

				for(int j=0;j<num;j++){

					Animal A = new Animal(applet.random(scene.w),applet.random(scene.h),i,j,pos);

					A.scene = scene;

					species.add(A);

				}Animals.add(species); }}

		if(a=="Metals"||a=="metal"||a=="metals"||a=="Metals"){
			for(int i=0;i<b;i++){

				ArrayList<Metal> type = new ArrayList<Metal>();

				for(int j=0;j<num;j++){

					Metal m = new Metal(applet.random(scene.w),applet.random(scene.h),i,j,pos);

					m.scene = scene;

					type.add(m);

				}Metals.add(type); }
		}

		Bms.entities.add(this);
		toggle = true;
		PApplet.println("Entities: ",Bms.entities.size(),toggle);

		if(Bms.entities.size()==b){

			for(int i=0;i<Bms.entities.size();i++){

				//Entity e = entities.get(i).Humans.get(0);


			}}
	};

};






