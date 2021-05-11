package androidGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

class tabBoundary{
	BMScontrols Bms;
	PApplet applet;
	float x1,x2,y1,y2,w,h,x3,y3,size = 1,x4,y4,D1,D2,T1,T2,collision_index,menux,menuy,deltamx,deltamy,tacx,tacy,dir;
	float x,y,bx = x,by = y;
	public float mass,vx,vy,ax,ay,density,friction_u,momentum,bax ,bay,pathWidth=20,
			gconst = 0.1f,floor = 590,rtheta = 0,avoidance,cohesion;
	PVector p1,midpoint1,midpoint2,midpoint3,m;
	PVector p2,p3,p4,center,vel,ac,rotatep;
	int key1,key2,type,t,t2,vert,vert1,vert3,trit,id,index,lindex,sindex,ccount,sid,vcount,hcount,uindex = -1,
			counter,tpoints,mcount,sides,shapeIndex,lineIndex,lcount,pcount,options = -1,numm,state,state1=1;
	public int cols = 40,rows = 25;
	public boolean toggle = false,mdown,mup = true,circle,square,bezier,tri,c_poligon,poligon,drag,none,spline,hover,hover2,point1,point2,point3,point4,gravity,hidemenu,clear,
			edit,complex,cstart,showgrid,cfinish = false,border,fill,hidden,connectedlines,path,
			avoidtabBoundary,cohesiontabBoundary,amendtabBoundary,amendInnertabBoundary,
			amendCohesion,amendAvoidance,dashed;
	boolean linedown = false,sdown,rtoggle,click,open,dynamic = true,falling = true,rotating,update,lock,pdown,
			updatep,updateg,tdown,trow,menudown,pointadded,converted,rdown,phover,lhover,contextClick,visible = true;
	float dx,dy,Theta,Norm;
	int col,bg;
	ArrayList<Integer> indices = new ArrayList<Integer>();
	ArrayList<Button> Buttons = new ArrayList<Button>();
	ArrayList<tabBoundary> Boundaries = new ArrayList<tabBoundary>();
	ArrayList<tabBoundary> innerBoundaries = new ArrayList<tabBoundary>();
	ArrayList<tabBoundary> outerBoundaries = new ArrayList<tabBoundary>();
	ArrayList<PVector> points = new ArrayList<PVector>();
	ArrayList<PVector> rotation_points = new ArrayList<PVector>();
	Scene scene;
	ArrayList<Scene> scenes = new ArrayList<Scene>();
	tabBoundary parent;
	tabBoundary child;
	PShape bpath;
	ArrayList<PShape> paths = new ArrayList<PShape>();
	ArrayList<Float[]> velocity = new ArrayList<Float[]>();
	ArrayList<PVector> velocitypv = new ArrayList<PVector>();
	ArrayList<tabBoundary> Quadrants = new ArrayList<tabBoundary>();
	ArrayList<PVector> Midpoints = new ArrayList<PVector>();
	ArrayList<PVector> collision_array = new ArrayList<PVector>();

	String []contextlabels = {"Undo","Redo"};
	String []contextDellabels = {"Delete Point","Cancel"};
	String []palletelabels = {"Fill","Gravity","Friction","Velocity","Connect","Amend B","Amend iB","Avoid","Seek",
			"Follow","Dashed","Border","Clear","Visible","Grid","Hide","Edit","Reset"};
	String []complexmenu = {"Finish"};
	String []convertmenu = {"Convert","Delete Shape"};
	String []connectedlMenul = {"Convert","Delete Line","Delete Shape"};

	Menu palletehide,context,contextDel,pallete,complexsub,convertToPath,connectedlMenu;
	String [] settingsLabel = {"Cohesion","Avoidance"};
	sliderBox settings;

	TextBox Sides;
	ArrayList<Float> norm = new ArrayList<Float>();
	ArrayList<Float[]> mdist = new ArrayList<Float[]>();
	ArrayList<Float[]> pdist = new ArrayList<Float[]>();
	ArrayList<Float> dist = new ArrayList<Float>();
	ArrayList<Integer> types = new ArrayList<Integer>();
	ArrayList<Float> theta = new ArrayList<Float>();
	ArrayList<Float> mtheta = new ArrayList<Float>();
	ArrayList<PVector> temp = new ArrayList<PVector>();
	ArrayList<ArrayList> polytemp = new ArrayList<ArrayList>();
	HashMap<String,Boolean> values = new HashMap<String,Boolean>();

	PVector polymidpoint;
	Grid gridlayout;
	ArrayList<Point> grid = new ArrayList<Point>();
	ArrayList<Point> ugrid = new ArrayList<Point>();
	ArrayList<Point> ogrid = new ArrayList<Point>();

	tabBoundary(){


	};

	tabBoundary(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		palletehide = new Menu(0,200+300/2 - 100,20,50,bms);
		context = new Menu(applet.mouseX,applet.mouseY,90,contextlabels,bms);
		contextDel = new Menu(applet.mouseX,applet.mouseY,90,contextDellabels,bms);
		pallete = new Menu(0,200,90,palletelabels,0,bms);
		complexsub = new Menu(applet.width-200,applet.height-200,90,complexmenu,bms);
		convertToPath = new Menu(applet.mouseX,applet.mouseY,90,convertmenu,bms);
		connectedlMenu = new Menu(applet.mouseX,applet.mouseY,90,connectedlMenul,bms);
		Sides = new TextBox (applet.width-200,applet.height-200,20,20,5,"Sides",bms);
		type = -1;

	};

	tabBoundary(float X1, float Y1,float X2, float Y2){

		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		type = 2;
		p1 = new PVector(x1,y1);
		p2 = new PVector(x2,y2);
		center = new PVector((x1+x2)/2,(y1+y2)/2);

	};

	tabBoundary(float X1, float Y1,float X2, float Y2,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		type = 2;
		p1 = new PVector(x1,y1);
		p2 = new PVector(x2,y2);
		center = new PVector((x1+x2)/2,(y1+y2)/2);

	};

	tabBoundary(float X1, float Y1,float ww,float hh,int Type,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		float t1 = 0;
		x1 = X1;
		y1 = Y1;
		w = ww;
		h = hh;

		Float [] d1 = {0.0f,0.0f};
		Float [] vel = {0.0f,0.0f};

		if(Type==0){

			x1 = X1;
			y1 = Y1;
			w = ww;
			h = hh;
			x2 = x1 + ww;
			y2 = y1 + hh;

			type = 0;
			center = new PVector((x1+x2)/2,(y1+y2)/2);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);

		}

		if(Type==2){
			x1 = X1;
			y1 = Y1;
			x2 = ww;
			y2 = hh;

			type = 2;
			center = new PVector((x1+x2)/2,(y1+y2)/2);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2,bms));
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			velocity.add(vel);
			create_rotation_points(Boundaries);
			create_points(Boundaries);

		}

		if(Type==0||Type==2){
			center = new PVector((x1+x2)/2,(y1+y2)/2);
		}

		if(Type==3){

			w = PApplet.dist(x1,y1,ww,hh);
			h = PApplet.dist(x1,y1,ww,hh);

			Norm = -PApplet.atan2(x1 - ww,y1 - hh);

			x2 = (float) (ww + w/1.5 * PApplet.cos(Norm));
			y2 = (float) (hh + h/1.5 * PApplet.sin(Norm));
			x3 = (float) (ww - w/1.5 * PApplet.cos(Norm));
			y3 = (float) (hh - h/1.5 * PApplet.sin(Norm));

			center = new PVector((x1+x2+x3)/3,(y1+y2+y3)/3);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2,bms));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			midpoint1 = new PVector((x1 + x2)/2,(y1 + y2)/2);
			Boundaries.add(new tabBoundary(x2 , y2 ,x3 , y3,bms));
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			midpoint2 = new PVector((x2 + x3)/2,(y2 + y3)/2);
			velocity.add(vel);
			Boundaries.add(new tabBoundary(x3, y3,x1,y1,bms ));
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			velocity.add(vel);
			midpoint3 = new PVector((x3 + x1)/2,(y3 + y1)/2);
			type = 3;
			create_rotation_points(Boundaries);
			create_points(Boundaries);

		}

		if(Type==4){

			x2 = x1 + ww;
			y2 = y1;
			x3 = x1 + ww;
			y3 = y1 + hh;
			x4 = x1;
			y4 = y1 + hh;

			center = new PVector((x1+x2+x3+x4)/4,(y1+y2+y3+y4)/4);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2,bms));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x2,y2,x3,y3,bms));
			velocity.add(vel);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x3,y3,x4,y4,bms));
			velocity.add(vel);
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x4,y4,x1,y1,bms));
			velocity.add(vel);
			d1[0] = center.x - x4;
			d1[1] = center.y - y4;
			pdist.add(d1);
			t1 = PApplet.atan2(y4-center.y,x4-center.x);
			theta.add(t1);

			center = new PVector((x1+x2+x3+x4)/4,(y1+y2+y3+y4)/4);
			type = 4;
		}

		if(Type==103){

			Norm = -PApplet.atan2(x1 - ww,y1 - hh);

			x2 = x1 + w/2 ;
			y2 = y1 + h ;
			x3 = x1 - w/2 ;
			y3 = y1 + h ;
			center = new PVector((x1+x2+x3)/3,(y1+y2+y3)/3);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2,bms));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			midpoint1 = new PVector((x1 + x2)/2,(y1 + y2)/2);
			Boundaries.add(new tabBoundary(x2 , y2 ,x3 , y3,bms));
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			midpoint2 = new PVector((x2 + x3)/2,(y2 + y3)/2);
			velocity.add(vel);
			Boundaries.add(new tabBoundary(x3, y3,x1,y1,bms ));
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			velocity.add(vel);
			midpoint3 = new PVector((x3 + x1)/2,(y3 + y1)/2);
			type = 3;
			create_points(Boundaries);

		}

		type = Type;
		create_points(Boundaries);
		create_dist();
		create_angles(Boundaries);
		create_rotation_points(Boundaries);
	};

	tabBoundary(float X1, float Y1,float ww,float hh,int Type){

		float t1 = 0;
		x1 = X1;
		y1 = Y1;
		w = ww;
		h = hh;

		Float [] d1 = {0.0f,0.0f};
		Float [] vel = {0.0f,0.0f};

		if(Type==0){

			x1 = X1;
			y1 = Y1;
			w = ww;
			h = hh;
			x2 = x1 + ww;
			y2 = y1 + hh;

			type = 0;
			center = new PVector((x1+x2)/2,(y1+y2)/2);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);

		}

		if(Type==2){
			x1 = X1;
			y1 = Y1;
			x2 = ww;
			y2 = hh;

			type = 2;
			center = new PVector((x1+x2)/2,(y1+y2)/2);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2));
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			velocity.add(vel);
			create_rotation_points(Boundaries);
			create_points(Boundaries);

		}

		if(Type==0||Type==2){
			center = new PVector((x1+x2)/2,(y1+y2)/2);
		}

		if(Type==3){

			w = PApplet.dist(x1,y1,ww,hh);
			h = PApplet.dist(x1,y1,ww,hh);

			Norm = -PApplet.atan2(x1 - ww,y1 - hh);

			x2 = (float) (ww + w/1.5 * PApplet.cos(Norm));
			y2 = (float) (hh + h/1.5 * PApplet.sin(Norm));
			x3 = (float) (ww - w/1.5 * PApplet.cos(Norm));
			y3 = (float) (hh - h/1.5 * PApplet.sin(Norm));

			center = new PVector((x1+x2+x3)/3,(y1+y2+y3)/3);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			midpoint1 = new PVector((x1 + x2)/2,(y1 + y2)/2);
			Boundaries.add(new tabBoundary(x2 , y2 ,x3 , y3));
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			midpoint2 = new PVector((x2 + x3)/2,(y2 + y3)/2);
			velocity.add(vel);
			Boundaries.add(new tabBoundary(x3, y3,x1,y1 ));
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			velocity.add(vel);
			midpoint3 = new PVector((x3 + x1)/2,(y3 + y1)/2);
			type = 3;
			create_rotation_points(Boundaries);
			create_points(Boundaries);

		}

		if(Type==4){

			x2 = x1 + ww;
			y2 = y1;
			x3 = x1 + ww;
			y3 = y1 + hh;
			x4 = x1;
			y4 = y1 + hh;

			center = new PVector((x1+x2+x3+x4)/4,(y1+y2+y3+y4)/4);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x2,y2,x3,y3));
			velocity.add(vel);
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x3,y3,x4,y4));
			velocity.add(vel);
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			Boundaries.add(new tabBoundary(x4,y4,x1,y1));
			velocity.add(vel);
			d1[0] = center.x - x4;
			d1[1] = center.y - y4;
			pdist.add(d1);
			t1 = PApplet.atan2(y4-center.y,x4-center.x);
			theta.add(t1);

			center = new PVector((x1+x2+x3+x4)/4,(y1+y2+y3+y4)/4);
			type = 4;
		}

		if(Type==103){

			Norm = -PApplet.atan2(x1 - ww,y1 - hh);

			x2 = x1 + w/2 ;
			y2 = y1 + h ;
			x3 = x1 - w/2 ;
			y3 = y1 + h ;
			center = new PVector((x1+x2+x3)/3,(y1+y2+y3)/3);
			Boundaries.add(new tabBoundary(x1,y1,x2,y2));
			velocity.add(vel);
			d1[0] = center.x - x1;
			d1[1] = center.y - y1;
			pdist.add(d1);
			t1 = PApplet.atan2(y1-center.y,x1-center.x);
			theta.add(t1);
			midpoint1 = new PVector((x1 + x2)/2,(y1 + y2)/2);
			Boundaries.add(new tabBoundary(x2 , y2 ,x3 , y3));
			d1[0] = center.x - x2;
			d1[1] = center.y - y2;
			pdist.add(d1);
			t1 = PApplet.atan2(y2-center.y,x2-center.x);
			theta.add(t1);
			midpoint2 = new PVector((x2 + x3)/2,(y2 + y3)/2);
			velocity.add(vel);
			Boundaries.add(new tabBoundary(x3, y3,x1,y1 ));
			d1[0] = center.x - x3;
			d1[1] = center.y - y3;
			pdist.add(d1);
			t1 = PApplet.atan2(y3-center.y,x3-center.x);
			theta.add(t1);
			velocity.add(vel);
			midpoint3 = new PVector((x3 + x1)/2,(y3 + y1)/2);
			type = 3;
			create_points(Boundaries);

		}

		type = Type;
		create_points(Boundaries);
		create_dist();
		create_angles(Boundaries);
		create_rotation_points(Boundaries);
	};

	tabBoundary (float X1, float Y1,float X2,float Y2,float X3, float Y3){

		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		x3 = X3;
		y3 = Y3;
		type = 3;
	};

	tabBoundary(PVector A, PVector B,PVector C){

		p1 = A;
		p2 = B;
		p3 = C;
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		type = 3;
	};

	tabBoundary(PVector A, PVector B){

		p1 = A;
		p2 = B;
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
	};

	tabBoundary(ArrayList<Point> A){

		center = new PVector (0,0);
		for(int i=0;i<A.size();i++){

			Point a = A.get(i);
			Point b = A.get(i);

			if(i<A.size()-1)b = A.get(i+1);
			else b = A.get(0);
			tabBoundary B = new tabBoundary(a.x,a.y,b.x,b.y,2);
			Boundaries.add(B);

		}

		calc_center();
		create_points(Boundaries);
		create_dist();
		create_angles(Boundaries);
		create_rotation_points(Boundaries);
		type = 105;
	};

	tabBoundary(ArrayList<Point> A,int k){

		center = new PVector (0,0);
		for(int i=0;i<A.size();i++){

			Point a = A.get(i);
			Point b = null;
			Point c = null;

			if(i+2<A.size()){
				b = A.get(i+1);
				c = A.get(i+2);

				if(((b.x==a.x)&&(c.x==a.x)||b.y==a.y&&(c.y==a.y))){
					A.remove(i+1);
					i--;

				}else{
					tabBoundary B = new tabBoundary(a.x,a.y,b.x,b.y,2);
					Boundaries.add(B);
				}}else if(i+1<A.size()){
					b = A.get(i+1);
					tabBoundary B = new tabBoundary(a.x,a.y,b.x,b.y,2);
					Boundaries.add(B);
				}}

		calc_center();
		create_points(Boundaries);
		create_dist();
		create_angles(Boundaries);
		create_rotation_points(Boundaries);
		type = 106;
	};

	tabBoundary(tabBoundary B){

		//Boundaries = B.Boundaries;

		if(B.type==106){

			float pw = pathWidth; 

			center = new PVector (0,0);

			tabBoundary b0 = B.Boundaries.get(0);

			PVector p0 = new PVector(b0.x1,b0.y1);
			PVector p1 = new PVector(b0.x2,b0.y2);

			float t = -PApplet.atan2(p0.x-p1.x,p0.y-p1.y);

			PVector p2 =  new PVector(p0.x+pw*PApplet.cos(t),p0.y+pw*PApplet.sin(t));
			PVector p3 =  new PVector(p0.x-pw*PApplet.cos(t),p0.y-pw*PApplet.sin(t));

			tabBoundary b = new tabBoundary(p1,p2);

			Boundaries.add(b);

			for(int i=1;i<B.Boundaries.size()-1;i++){

				b = B.Boundaries.get(i);

				tabBoundary b1 = B.Boundaries.get(i-1);
				tabBoundary b2 = B.Boundaries.get(i+1);

				p0 = new PVector(b.x1,b.y1);
				p1 = new PVector(b1.x2,b1.y2);

				p2 = new PVector(b2.x2,b2.y2);
				p3 = new PVector();

				float t1 = PApplet.atan2(p0.y-p1.y,p0.x-p1.x);
				float t2 = PApplet.atan2(p0.y-p2.y,p0.x-p2.x);

				t = -1/((t1+t2)/2);

				PVector p4 = new PVector(p0.x+pw*PApplet.cos(t),p0.y+pw*PApplet.sin(t));
				PVector p5 = new PVector(p1.x+pw*PApplet.cos(t),p1.y+pw*PApplet.sin(t));

				Boundaries.add(new tabBoundary(p4.x,p4.y,p5.x,p5.y));

			}

			b0 =  B.Boundaries.get(B.Boundaries.size()-1);
			p0 = new PVector(b0.x1,b0.y1);

			t = PApplet.radians(90);
			p1 =  new PVector(p0.x+pw*PApplet.cos(t),p0.y+pw*PApplet.sin(t));
			p2 =  new PVector(p0.x-pw*PApplet.cos(t),p0.y-pw*PApplet.sin(t));

			b = new tabBoundary(p1,p2);

			Boundaries.add(b);

			for(int i=B.Boundaries.size()-1;i>-1;i--){

				b = B.Boundaries.get(i);
				tabBoundary b1 = B.Boundaries.get(i-1);
				tabBoundary b2 = B.Boundaries.get(i+1);

				p0 = new PVector(b.x1,b.y1);
				p1 = new PVector(b1.x2,b1.y2);

				p2 = new PVector(b2.x2,b2.y2);
				p3 = new PVector();

				float t1 = PApplet.atan2(p0.y-p1.y,p0.x-p1.x);
				float t2 = PApplet.atan2(p0.y-p2.y,p0.x-p2.x);

				t = -1/((t1+t2)/2);

				PVector p4 = new PVector(p0.x-pw*PApplet.cos(t),p0.y-pw*PApplet.sin(t));
				PVector p5 = new PVector(p1.x-pw*PApplet.cos(t),p1.y-pw*PApplet.sin(t));

				Boundaries.add(new tabBoundary(p4.x,p4.y,p5.x,p5.y));

			}

			calc_center();
			create_points(Boundaries);
			create_dist();
			create_angles(Boundaries);
			create_rotation_points(Boundaries);
			type = 107;

		}else if(B.type!=0){

			float pw = pathWidth; 

			center = B.center;
			calcInner(B);
			calcOuter(B);

			create_points(innerBoundaries);
			create_points(outerBoundaries);
			create_angles(innerBoundaries);
			create_angles(outerBoundaries);
			create_rotation_points(innerBoundaries);
			create_rotation_points(outerBoundaries);
			type = 107;
		}else {

		}
	};

	Point getNextPoint(ArrayList<Point> a,Point b){
		Point c = null;
		for(int i=0;i<a.size();i++){
			Point d = a.get(i);
			if(d.x!=b.x&&d.y!=b.y)c = d; 
		}

		return c;
	};

	public void debug(){
		applet.fill(255);
	};

	public void calcInner(tabBoundary a){
		if(a.type==0){

		}else if(a.type!=0&&a.type!=106){

			float pw = a.pathWidth;

			for(int i=0;i<a.Boundaries.size();i++){
				tabBoundary b = a.Boundaries.get(i);
				tabBoundary b1 = a.Boundaries.get(i);

				if(i<a.Boundaries.size()-1)b1 = a.Boundaries.get(i+1);
				else b1 = a.Boundaries.get(0);

				PVector p = new PVector(b.x1,b.y1);
				PVector p1 = new PVector(b.x2,b.y2);

				float t = PApplet.atan2(a.center.y-p.y,a.center.x-p.x);
				float d = PApplet.dist(p.x,p.y,a.center.x,a.center.y);

				float t1 = PApplet.atan2(a.center.y-p1.y,a.center.x-p1.x);
				float d1 = PApplet.dist(p1.x,p1.y,a.center.x,a.center.y);

				PVector p2 = new PVector(a.center.x+(d-pw)*PApplet.cos(t),a.center.y+(d-pw)*PApplet.sin(t));
				PVector p3 = new PVector(a.center.x+(d1-pw)*PApplet.cos(t1),a.center.y+(d1-pw)*PApplet.sin(t1));


				//a.innerBoundaries.add(new tabBoundary(p2.x,p2.y,p3.x,p3.y));
				//a.outerBoundaries.add(new tabBoundary(p2.x,p2.y,p3.x,p3.y));
			}a.type = 107;}else{

			}

	};

	public void calcOuter(tabBoundary a){
		if(a.type==0){

		}else if(a.type!=0&&a.type!=106){

			float pw = a.pathWidth;

			for(int i=0;i<a.Boundaries.size();i++){
				tabBoundary b = a.Boundaries.get(i);
				tabBoundary b1 = a.Boundaries.get(i);

				if(i<a.Boundaries.size()-1)b1 = a.Boundaries.get(i+1);
				else b1 = a.Boundaries.get(0);

				PVector p = new PVector(b.x1,b.y1);
				PVector p1 = new PVector(b1.x1,b1.y1);

				float t = PApplet.atan2(a.center.y-p.y,a.center.x-p.x)-PConstants.PI;
				float d = PApplet.dist(p.x,p.y,a.center.x,a.center.y);

				float t1 = PApplet.atan2(a.center.y-p1.y,a.center.x-p1.x)-PConstants.PI;
				float d1 = PApplet.dist(p1.x,p1.y,a.center.x,a.center.y) ;

				PVector p2 = new PVector(a.center.x+(d+pw)*PApplet.cos(t),a.center.y+(d+pw)*PApplet.sin(t));
				PVector p3 = new PVector(a.center.x+(d1+pw)*PApplet.cos(t1),a.center.y+(d1+pw)*PApplet.sin(t1));

				PVector p4 = new PVector(a.center.x+(d)*PApplet.cos(t) - pw*PApplet.cos(t),a.center.y+(d)*PApplet.sin(t) - pw*PApplet.sin(t));
				PVector p5 = new PVector(a.center.x+(d1)*PApplet.cos(t1) - pw*PApplet.cos(t),a.center.y+(d1)*PApplet.sin(t1) - pw*PApplet.sin(t));

				a.innerBoundaries.add(new tabBoundary(p4.x,p4.y,p5.x,p5.y));
				a.outerBoundaries.add(new tabBoundary(p2.x,p2.y,p3.x,p3.y));
			}a.type = 107;}else{

			}
	};

	public void save(){

	};

	public void load(){

	};

	public void draw(){
		rmenu();
		hcount = 0;lcount = 0;pcount = 0;
		boolean lineHover = false;
		int k = -1;
		applet.fill(255);

		if(applet.mouseX>0)m.x = applet.mouseX;
		if(applet.mouseY>0)m.y = applet.mouseY;
		if(!clear){
			//for(int i=Boundaries.size()-1;i>-1;i--){
			for(int i=1;i<Boundaries.size();i++){
				tabBoundary b = Boundaries.get(i);

				b.id = i;
				if(!b.visible&&pos(b,m)){
					if(visible&&applet.mousePressed)b.visible = true;
				}
				if(b.visible){
					if(edit){
						if(b.type!=2&&!point1&&!point2&&!linedown&&!rdown)mtranslate(b,m);
						if(!point1&&!point2&&!tdown&&!rdown)move(b,m);
						if((b.type!=0)&&!linedown&&!tdown&&!rdown)pospoint(b);
						if(!point1&&!point2&&!linedown&&!tdown)pos_rotationpoint(b);
						b.update(b);
					}

					if(!pospoint(b,0))pcount++;

					if(gravity)b.gravity = true;
					else b.gravity = false;

					applet.strokeWeight(1);
					if(pos(b,m)){
						if(!visible&&applet.mousePressed)b.visible = false;
						if(amendtabBoundary){
							applet.fill(255);
							applet.text(b.state,b.x1,b.y1);
							if(applet.mousePressed){
								if(amendCohesion&&amendAvoidance)b.state = 1;
								else if(amendCohesion&&!amendAvoidance)b.state = 2;
								else if(!amendCohesion&&amendAvoidance)b.state = 3;
								else if(!amendCohesion&&!amendAvoidance)b.state = 4;
							}}
						indices.add(i);
						if(indices.size()>2)indices.remove(0);
					}
					if(!pos(b,m))hcount ++;

					if(b.type==0){
						applet.stroke(0);
						applet.fill(0,50);applet.strokeWeight(1);
						if(pos(b,m)){
							shapeIndex=i;
							applet.fill(255,50);
						}
						applet.ellipseMode(PConstants.CENTER);
						applet.ellipse(b.center.x,b.center.y,b.w,b.h);
					}else if(b.type!=107){

						if(b.type!=106)applet.fill(0,50);
						else applet.noFill();
						if(pos(b,m)){
							shapeIndex=i; 
							applet.fill(255,50);
						}
						applet.stroke(0);
						applet.beginShape();

						for(int j=0;j<b.Boundaries.size();j++){
							tabBoundary c = b.Boundaries.get(j);

							applet.vertex(c.x1,c.y1);
							applet.vertex(c.x2,c.y2);
						}}}
				if(b.type==106)applet.endShape();
				else if(b.type!=107)applet.endShape(PConstants.CLOSE);

				if(b.type==107){

					//if(!b.converted){

					b.bpath = applet.createShape();
					//---------------begin shape------------------------------------
					applet.beginShape();
					applet.fill(0,150);
					applet.stroke(0);
					applet.strokeWeight(1);
					//-------------exterior------------------------------------

					for(int j=0;j<b.outerBoundaries.size()-1;j++){

						tabBoundary c = b.outerBoundaries.get(j);
						tabBoundary c1 = b.outerBoundaries.get(j+1);

						applet.vertex(c.x1,c.y1);
						applet.vertex(c1.x1,c1.y1);

						if(pos(c,m)){
							if(amendInnertabBoundary){
								if(applet.mousePressed){
									if(amendCohesion&&amendAvoidance)c.state1 = 1;
									else if(amendCohesion&&!amendAvoidance)c.state1 = 2;
									else if(!amendCohesion&&amendAvoidance)c.state1 = 3;
									else if(!amendCohesion&&!amendAvoidance)c.state1 = 4;
								}}
							if(c.state1==3)applet.stroke(255,0,0);
							else if(c.state1==2)applet.stroke(0,0,255);
							else if(c.state1==1)applet.stroke(0,255,0);
							applet.line(c.x1,c.y1,c.x2,c.y2);
							lineHover = true;
							k = j;
						}}
					//------------interior------------------------------------
					applet.beginContour();

					for(int j=b.innerBoundaries.size()-1;j>0;j--){

						tabBoundary c = b.innerBoundaries.get(j);
						tabBoundary c1 = b.innerBoundaries.get(j-1);

						applet.vertex(c.x1,c.y1);
						applet.vertex(c1.x1,c1.y1);
						if(pos(c,m)){
							if(amendInnertabBoundary){
								if(applet.mousePressed){
									if(amendCohesion&&amendAvoidance)c.state1 = 1;
									else if(amendCohesion&&!amendAvoidance)c.state1 = 2;
									else if(!amendCohesion&&amendAvoidance)c.state1 = 3;
									else if(!amendCohesion&&!amendAvoidance)c.state1 = 4;
								}}
							if(c.state1==3)applet.stroke(255,0,0);
							else if(c.state1==2)applet.stroke(0,0,255);
							else if(c.state1==1)applet.stroke(0,255,0);
							applet.line(c.x1,c.y1,c.x2,c.y2);
							lineHover = true;
							k = j;
						}}
					//for(int j=0;j<b.innerBoundaries.size()-1;j++){

					//  tabBoundary c = b.innerBoundaries.get(j);
					//  tabBoundary c1 = b.innerBoundaries.get(j+1);

					//  applet.vertex(c.x1,c.y1);
					//  applet.vertex(c.x2,c.y2);
					//  //applet.vertex(c1.x1,c.y1);
					//}
					applet.endContour();
					//----------end shape------------------------------------
					applet.endShape(PConstants.CLOSE);
					//b.converted = true;
					//shape(b.bpath);
					//}
					//else shape(b.bpath);
				}
				if(!clear){

					for(int j=0;j<b.Boundaries.size();j++){

						tabBoundary c = b.Boundaries.get(j);

						if(pos(c,m)){
							if(amendInnertabBoundary){
								if(applet.mousePressed){
									if(amendCohesion&&amendAvoidance)c.state1 = 1;
									else if(amendCohesion&&!amendAvoidance)c.state1 = 2;
									else if(!amendCohesion&&amendAvoidance)c.state1 = 3;
									else if(!amendCohesion&&!amendAvoidance)c.state1 = 4;
								}}
							if(c.state1==3)applet.stroke(255,0,0);
							else if(c.state1==2)applet.stroke(0,0,255);
							else if(c.state1==1)applet.stroke(0,255,0);
							applet.line(c.x1,c.y1,c.x2,c.y2);
							lineHover = true;
							k = j;
						}}
					lindex = k;
					if(lineHover)shapeIndex = i;
					else lcount++;
				}}
			if(hcount>0) hover2 = true;
			else hover2  = false;
		}else{
			//tabBoundary a = Boundaries.get(0);
			//Boundaries = new ArrayList<tabBoundary>();
			//Boundaries.add(a);
			while(Boundaries.size()>1)Boundaries.remove(Boundaries.size()-1);
			clear = false;
		}
		int n = Boundaries.size();
		if(pcount==0)phover = false;
		if(lcount==0)lhover = false;
		if(hcount==n&&lcount==n&&pcount==n)shapeIndex=-1;
	};

	public void draw2(){
		logic();
		debug();

		applet.strokeWeight(1);
		for(int i=0;i<Boundaries.size();i++){
			tabBoundary b = Boundaries.get(i);
			b.id = i;
			move(b,m);
			applet.beginShape();
			applet.stroke(0);
			applet.vertex(b.x1,b.y1);
			applet.vertex(b.x2,b.y2);
			applet.endShape(PConstants.CLOSE);
		}
	};

	public void trace(){

		//scene.draw(9);

	};

	public void rmenu(){
		logic();
		if(open||none){
			pallete.draw();
			//if(showgrid){
			//  gridlayout.toggle=true;
			//  gridlayout.grid_functions();
			//  gridlayout.terrain2d = true;
			//  }
			//else if(type==-2)gridlayout.toggle=false;

			//if(applet.mousePressed&&applet.mouseButton == applet.RIGHT&&!rtoggle){rtoggle = true;}
			//-----------------------------------------------------------------------------

			if(applet.mousePressed&&!rtoggle&&!contextClick)rtoggle=true;


			if(rtoggle&&edit){

				contextDel.visible = false;
				convertToPath.visible = false;
				context.visible = false;
				connectedlMenu.visible = false;

				if(shapeIndex==-1)options = 0;
				else {
					if(Boundaries.get(shapeIndex).type==106){
						numm = shapeIndex;
						if(pcount==1)options = 5;
						else if(lcount==pcount) options = 1;
						//else if((pcount<lcount))options = 1;
						//else options = 5;
					}else {
						if(pcount==lcount&&pcount>0)options = 3;
						else if(pcount>lcount)options = 2;
						else if((pcount<lcount))options = 1;
						else options = 4;
					}}
				if(!edit||Boundaries.size()==0)options=4;
				context.x = applet.mouseX;
				context.y = applet.mouseY;

				connectedlMenu.x = applet.mouseX;
				connectedlMenu.y = applet.mouseY;

				for (int i=0;i<connectedlMenu.items.size();i++){
					Button a = connectedlMenu.items.get(i); 
					a.x = connectedlMenu.x;
					a.y = connectedlMenu.y+ i*20;
				}

				for (int i=0;i<context.items.size();i++){
					Button a = context.items.get(i); 
					a.x = context.x;
					a.y = context.y+ i*20;
				}

				contextDel.x = applet.mouseX;
				contextDel.y = applet.mouseY;

				if(options==1)contextDel.items.get(0).label = "Delete Point";
				else if(options==2)contextDel.items.get(0).label = "Delete Line";

				for (int i=0;i<contextDel.items.size();i++){
					Button a = contextDel.items.get(i); 
					a.x = contextDel.x;
					a.y = contextDel.y+ i*20;
				}

				convertToPath.x = applet.mouseX;
				convertToPath.y = applet.mouseY;

				for (int i=0;i<convertToPath.items.size();i++){
					Button a = convertToPath.items.get(i); 
					a.x = convertToPath.x;
					a.y = convertToPath.y+ i*20;
				}

				if(!applet.mousePressed){
					contextClick = true;
					rtoggle=false;
				}}
			//------------------------------------------------------------------------------------
			//if(applet.mousePressed&&applet.mouseButton==applet.LEFT)options = 4;
			if(options==0){
				contextDel.visible = false;
				convertToPath.visible = false;
				context.visible = true;
				connectedlMenu.visible = false;
			}
			else if(options==1||options==2){
				contextDel.visible = true;
				convertToPath.visible = false;
				context.visible = false;
				connectedlMenu.visible = false;
			}else if(options==3){
				contextDel.visible = false;
				convertToPath.visible = true;
				context.visible = false;
				connectedlMenu.visible = false;
			}else if(options==4){
				contextDel.visible = false;
				convertToPath.visible = false;
				context.visible = false;
				connectedlMenu.visible = false;
			}else if(options==5){
				connectedlMenu.visible = true;
				contextDel.visible = false;
				convertToPath.visible = false;
				context.visible = false;
			}

			if(contextClick){
				if(contextDel.visible){
					contextDel.draw();
					if(applet.mousePressed&&!contextDel.items.get(0).pos()&&!contextDel.items.get(1).pos()){
						rtoggle=false;
						contextClick=false;
					}}
				else if(context.visible){
					context.draw();
					if(applet.mousePressed&&!context.items.get(0).pos()&&!context.items.get(1).pos()){
						rtoggle=false;
						contextClick=false;
					}}
				else if(convertToPath.visible){
					convertToPath.draw();
					//if(applet.mousePressed&&applet.mouseButton==applet.LEFT){
					if(applet.mousePressed&&convertToPath.items.get(1).pos()){
						if(shapeIndex>-1)Boundaries.remove(shapeIndex);
						rtoggle=false;
						contextClick=false;
					}else if(applet.mousePressed&&convertToPath.items.get(0).pos()){
						calcInner(Boundaries.get(shapeIndex));
						calcOuter(Boundaries.get(shapeIndex));

						rtoggle=false;
						contextClick=false;
					}else if(applet.mousePressed&&!convertToPath.items.get(0).pos()&&!convertToPath.items.get(1).pos()){
						rtoggle=false;
						contextClick=false;
					}}
				else if(connectedlMenu.visible){
					connectedlMenu.draw();
					if(applet.mousePressed&&connectedlMenu.items.get(2).pos()){
						if(numm>-1)Boundaries.remove(numm);
						rtoggle=false;
						contextClick=false;
					}else if(applet.mousePressed&&connectedlMenu.items.get(0).pos()){
						calcInner(Boundaries.get(numm));
						calcOuter(Boundaries.get(numm));

						rtoggle=false;
						contextClick=false;
					}}
				else if (applet.mousePressed){
					contextClick = false;
					rtoggle = false;
				}}}

		if(contextClick&&!contextDel.pos()&&context.pos()&&!convertToPath.pos()&&applet.mousePressed){
			contextClick=false;
			rtoggle = false;
		}

	};

	public void update(tabBoundary a){
		float midx = 0;
		float midy = 0;

		float aa = 0.05f;
		vcount = 0;

		//dir = applet.atan2(a.Boundaries.get(0).y1 - center.y,a.Boundaries.get(0).x1 - center.x);
		//applet.line(Boundaries.get(0).x1,Boundaries.get(0).y1,center.x,center.y);
		if(type!=0){

			if(!applet.mousePressed){trow = true;}

			if(gravity){

				//updateg = true;
				if(falling){
					vy += gconst;
					if(vy>=4)vy = 4.0f;
					if(vy<=-4)vy = -4.0f;
					if(vy>=4)vy = 4.0f;
					if(vy<=-4)vy = -4.0f;
					center.y += vy;
				}
				if(type!=2){
					update_Boundaries();
					a.update_angles();
					create_rotation_points(Boundaries);
				}else{
					//center.x = (x1+x2)/2;
					//center.y = (y1+y2)/2;
					a.update_line();
				}


			}else updateg = false;

			for(int i=0;i<Boundaries.size();i++){

				tabBoundary b = Boundaries.get(i);

				if(b.y1>=floor)vcount++;rotatep = (new PVector(b.x1,b.y1));
				if(b.y2>=floor)vcount++;rotatep = (new PVector(b.x2,b.y2));
			}

			if(vcount>0) falling = false;
			if(vcount>0) rotating = true;

		}

		if(type==0){
			if(gravity){

				if(center.y<floor){
					vy += ay;
					center.y += vy;
				}}}

		applet.strokeWeight(20);
		applet.stroke(255);
		applet.point(center.x,center.y);
		applet.strokeWeight(1);

		if(!applet.mousePressed)sdown = false;linedown = false;
		if(applet.mousePressed&&!linedown)linedown = true;

		if(!applet.mousePressed&&!a.point1&&!a.point2&&!a.point3&&a.updatep&&a.uindex==id&&a.type!=2){

			calc_center();
			update_dist(Boundaries);
			a.create_rotation_points(Boundaries);
			a.update_angles();
			a.updatep = false;
		}
	};

	public void create_points(ArrayList<tabBoundary> a){
		for(int i=0;i<a.size();i++){

			tabBoundary b = a.get(i);
			if(points.size()==i)points.add(new PVector(b.x1,b.y1));
		}
	};

	public void create_angles(ArrayList<tabBoundary> a){
		for(int i=0;i<a.size();i++){

			tabBoundary b = Boundaries.get(i);

			float t1 = PApplet.atan2(b.y1 - center.y,b.x1 - center.x);

			if(theta.size()<a.size())theta.add(t1);
			else theta.set(i,t1);
		}
	};

	public void update_points(ArrayList<tabBoundary> a){


		for(int i=0;i<a.size();i++){

			PVector b = points.get(i);

			float d1 = PApplet.dist(b.x,b.y,center.x,center.y);

			float t1 = theta.get(i);;

			float ax = center.x + (d1) * PApplet.cos(t1);
			float ay = center.y + (d1) * PApplet.sin(t1);

			if(points.size()<a.size())points.add(new PVector(ax,ay));
			else rotation_points.set(i,new PVector(ax,ay));
		}
	};

	public void create_dist(){
		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);

			dist.add(PApplet.dist(center.x,center.y,b.x1,b.y1));
		}
	};

	public void update_Boundaries(){

		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);

			float t = theta.get(i);
			Float d = dist.get(i);

			float t1 = 0;
			Float d1 = dist.get(i);

			if(i<Boundaries.size()-1){
				t1 = theta.get(i+1);
				d1 = dist.get(i+1);
			}else if(type!=106){
				t1 = theta.get(0);
				d1 = dist.get(0);
			}
			applet.strokeWeight(10);
			applet.stroke(0);

			b.x1 = center.x + d * PApplet.cos(t);
			b.y1 = center.y + d * PApplet.sin(t);
			if(type!=106){
				b.x2 = center.x + d1 * PApplet.cos(t1);
				b.y2 = center.y + d1 * PApplet.sin(t1);
			}else {

			}
			applet.strokeWeight(1);
		}
	};

	public void update_line(){

		tabBoundary a = Boundaries.get(0);

		float b = theta.get(0);
		Float d = dist.get(0);

		a.x1 = center.x - d * PApplet.cos(b) ;
		a.y1 = center.y - d * PApplet.sin(b) ;

		a.x2 = center.x + d * PApplet.cos(b) ;
		a.y2 = center.y + d * PApplet.sin(b) ;
	};

	public void update_Boundaries(tabBoundary a){

		for(int i=0;i<a.Boundaries.size();i++){

			tabBoundary b = a.Boundaries.get(i);

			float t = a.theta.get(i);
			Float d = a.dist.get(i);

			float t1 = 0;
			Float d1 = a.dist.get(i);

			if(i<Boundaries.size()-1){
				t1 = a.theta.get(i+1);
				d1 = a.dist.get(i+1);
			}else if(a.type!=106){
				t1 = a.theta.get(0);
				d1 = a.dist.get(0);
			}
			applet.strokeWeight(10);
			applet.stroke(0);

			b.x1 = center.x + d * PApplet.cos(t);
			b.y1 = center.y + d * PApplet.sin(t);
			if(a.type!=106){
				b.x2 = center.x + d1 * PApplet.cos(t1);
				b.y2 = center.y + d1 * PApplet.sin(t1);
			}
			applet.strokeWeight(1);
		}
	};

	public void rotate(float t){

		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);
			float t2 = theta.get(i);
			float d2 = dist.get(i);

			if(i<theta.size()-1){
				t2 = theta.get(i+1);
				d2 = dist.get(i+1);
			}else if(type!=106){
				t2 = theta.get(0);
				d2 = dist.get(0);
			}

			b.x1 = center.x + dist.get(i) * PApplet.cos(theta.get(i)+PApplet.radians(t));
			b.y1 = center.y + dist.get(i) * PApplet.sin(theta.get(i)+PApplet.radians(t));

			b.x2 = center.x + d2 * PApplet.cos(t2+PApplet.radians(t));
			b.y2 = center.y + d2 * PApplet.sin(t2+PApplet.radians(t));
		}

	};
	public void rotate2(float t){

		float t3 = PApplet.radians(90);
		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);
			float t2 = theta.get(i);
			float d2 = dist.get(i);

			if(i<theta.size()-1){
				t2 = theta.get(i+1);
				d2 = dist.get(i+1);
			}else if(type!=106){
				t2 = theta.get(0);
				d2 = dist.get(0);
			}

			b.x1 = center.x + dist.get(i) * PApplet.cos(theta.get(i)+(t)+t3);
			b.y1 = center.y + dist.get(i) * PApplet.sin(theta.get(i)+(t)+t3);

			b.x2 = center.x + d2 * PApplet.cos(t2+(t)+t3);
			b.y2 = center.y + d2 * PApplet.sin(t2+(t)+t3);
		}

	};

	public void update_angles(){
		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);

			float t = PApplet.atan2(b.y1 - center.y,b.x1 - center.x);
			if(i<theta.size())theta.set(i,t);
			else theta.add(t);
		}
	};

	public void angular_vel(float a, float u){



	};

	public void create_rotation_points(ArrayList<tabBoundary>a){

		float d = 10;

		for(int i=0;i<a.size();i++){

			tabBoundary b = a.get(i);

			float d1 = PApplet.dist(b.x1,b.y1,center.x,center.y);

			float t1 = PApplet.atan2(b.y1 - center.y,b.x1 - center.x);

			float ax = center.x + (d1+d) * PApplet.cos(t1);
			float ay = center.y + (d1+d) * PApplet.sin(t1);

			if(rotation_points.size()<a.size())rotation_points.add(new PVector(ax,ay));
			else rotation_points.set(i,new PVector(ax,ay));
		}

	};

	public void rotate_about(PVector a,Float v){

		for(int i=0;i<Boundaries.size();i++){

			tabBoundary b = Boundaries.get(i);

			float d1 = PApplet.dist(b.x1,b.y1,center.x,center.y);
			float d2 = PApplet.dist(b.x2,b.y2,center.x,center.y);
			float t1 = PApplet.atan2(b.y2 - b.y1,b.x2 - b.x1);
			float t2 = PApplet.atan2(b.y1 - b.y2,b.x1 - b.x2);

			b.x1 = center.x + d1 * PApplet.cos(t1+v);
			b.y1 = center.y + d1 * PApplet.sin(t1+v);
			b.x2 = center.x + d2 * PApplet.cos(t2+v);
			b.y2 = center.y + d2 * PApplet.sin(t2+v);
		}

	};

	public void pos_rotationpoint(tabBoundary a){

		float X = applet.mouseX;
		float Y = applet.mouseY;

		t2 = 0;
		float t0 = 0;

		boolean k = false;

		for(int i=0;i<a.rotation_points.size();i++){
			PVector b = a.rotation_points.get(i);

			float d1 = PApplet.dist(b.x,b.y,X,Y);

			if(d1<5){
				applet.strokeWeight(5);
				applet.stroke(255);
				applet.point(b.x,b.y);
				if(applet.mousePressed&&!point3){
					point3 = true;
					vert = i;
					index = a.id;
					rdown = true;
				}}}

		if(point3&&applet.mousePressed&&index == a.id&&!point1&&!point2&&!linedown){
			rdown = true;
			float t2 = PApplet.atan2(a.center.y -  applet.mouseY,a.center.x - applet.mouseX);

			for(int i=0;i<a.Boundaries.size();i++){

				tabBoundary b = a.Boundaries.get(i);

				PVector c = a.rotation_points.get(i);
				PVector d = null;

				float d1 = PApplet.dist(c.x,c.y,a.center.x,a.center.y)-10;
				float t1 = PApplet.atan2(a.center.y - c.y,a.center.x - c.x);

				float d2 = 0,t3 = 0;

				if(i<a.Boundaries.size()-1)d = a.rotation_points.get(i+1);
				else if(a.type!=106)d = a.rotation_points.get(0);

				if(d!=null){
					d2 = PApplet.dist(d.x,d.y,a.center.x,a.center.y)-10;
					t3 = PApplet.atan2(a.center.y - d.y,a.center.x - d.x);
				}
				b.x1 = a.center.x + d1 * PApplet.cos(t1 + t2);
				b.y1 = a.center.y + d1 * PApplet.sin(t1 + t2);

				b.x2 = a.center.x + d2 * PApplet.cos(t3 + t2);
				b.y2 = a.center.y + d2 * PApplet.sin(t3 + t2);
			}}

		if(!applet.mousePressed&&point3){
			vert = -1;
			vert1 = -1;
			index = -1;
			rdown = false;
			point3 = false;
			a.update_dist(a.Boundaries);
			a.create_rotation_points(a.Boundaries);
			uindex = a.id;
			updatep = true;
		}
	};

	public void calc_center(){

		float midx = 0;
		float midy = 0;

		for(int i=0;i<Boundaries.size();i++){
			tabBoundary b = Boundaries.get(i);
			midx += (b.x1 + b.x2)/2;
			midy += (b.y1 + b.y2)/2;
		}

		midx /= Boundaries.size();
		midy /= Boundaries.size();

		center = new PVector(midx,midy);
	};

	public void logic(){
		if((tri||toggle||circle||bezier||square||spline||poligon||c_poligon||connectedlines||path)){ open = true;}


		if(none)open = false;
		if(pallete!=null)pallete.toggle(12,Bms.main,"clear");
		if(hidemenu){

			if(pallete.x+pallete.w>=0){
				//if(type==0)palletelist.menu.x -= 2;
				pallete.x -= 6;
				for(int i=0;i<pallete.items.size();i++){
					Button a = pallete.items.get(i);
					a.x = pallete.x;
				}
				applet.rect(pallete.x,pallete.y,pallete.w,pallete.h);
			}else if(pallete.x+pallete.w<0){
				palletehide.draw();

				if(palletehide.pos()&&applet.mousePressed){
					hidemenu = false;
					pallete.items.get(10).toggle = false;
				}
			}}else if(pallete!=null&&pallete.x<0){
				Bms.main.pallete.items.get(15).toggle = false;
				pallete.x += 6;
				for(int i=0;i<pallete.items.size();i++){
					Button a = pallete.items.get(i);
					a.x = pallete.x;
				}
				applet.rect(pallete.x,pallete.y,pallete.w,pallete.h);
			}


		if(type==-1&&clear){
			Boundaries = new ArrayList<tabBoundary>();
			ArrayList<Float[]> velocity = new ArrayList<Float[]>();
			ArrayList<PVector> velocitypv = new ArrayList<PVector>();
			ArrayList<tabBoundary> Quadrants = new ArrayList<tabBoundary>();
			ArrayList<PVector> Midpoints = new ArrayList<PVector>();
			clear = false;
		}
	};

	public void mtranslate(tabBoundary b ,PVector m){

		float mx = m.x;
		float my = m.y;

		if(!point1&&!point2&&!point3&&!linedown&&!rdown&&type!=2){
			if(pos(b,m)&&applet.mousePressed&&!tdown){
				index = b.id;
				deltamx = (b.center.x - mx);
				deltamy = (b.center.y - my);

				b.tdown = true;
				tdown = true;
			}}


		if(b.tdown&&index == b.id&&type!=2){
			if(index>=0){

				tabBoundary c = Boundaries.get(index);
				b.center.x = mx + deltamx;
				b.center.y = my + deltamy;
				b.create_rotation_points(b.Boundaries);
				//b.update_angles();
				b.update_Boundaries();
			}}

		if(!applet.mousePressed&&type!=2){
			if(index>=0){
				tabBoundary c = Boundaries.get(index);
				index = -1;
				deltamx = 0;deltamy = 0;
				uindex = c.id;
				updatep = true;
				b.tdown = false;
				tdown = false;

			}}

	};

	public void mtranslate(PVector m){

		float mx = m.x;
		float my = m.y;

		if(applet.mousePressed&&!mdown){
			mdown = true; 
			dx = center.x - mx;
			dy = center.y - my;
		}

		if(mdown){
			center.x = mx + dx;
			center.y = my + dy;
		}
		update_Boundaries();
		if(!applet.mousePressed){
			mdown = false;

		}

	};

	public void functions(){
		debug();
		if(!edit&&!pallete.pos()&&open&&!palletehide.pos()){
			if (toggle)create();
			else if (square)createtabRect();
			else if(circle)create_circle();
			else if(tri)createtab_tri();
			else if(bezier)create_bezier();
			else if(poligon)create_poligon();
			else if(path)drawPath();
			else if(spline){

			}}

	};

	boolean pos(tabBoundary a,PVector p){
		float X = p.x;
		float Y = p.y;
		boolean k = false;
		trit = 0;
		if(a.type==0){

			float mx = PApplet.abs((a.x2-a.x1))/2;
			float my = PApplet.abs((a.y2-a.y1))/2;

			float A = PApplet.sq(X - a.center.x);
			float B = PApplet.sq(Y - a.center.y);

			float C = PApplet.sq(mx);
			float D = PApplet.sq(my);
			return A/C + B/D <1;

		} if(a.type==2){
			return check_lineP(new PVector(a.x1,a.y1),new PVector(a.x2,a.y2),new PVector(X,Y));
		}
		if(a.type==4||a.type==3||a.type==103||a.type==201){

			for(int i=0;i<a.Boundaries.size();i++){

				tabBoundary b = a.Boundaries.get(i);

				tabBoundary A = new tabBoundary((b.x1 + b.x2)/2,(b.y1 + b.y2)/2,X,Y);
				//applet.line((b.x1 + b.x2)/2,(b.y1 + b.y2)/2,X,Y);
				for(int j=0;j<a.Boundaries.size();j++){

					tabBoundary C = a.Boundaries.get(j);

					PVector i1 = check_intersect(A,C);

					if(i1!=null&&i!=j){
						trit++;

					}}}
			if(trit>0)k = false;
			else k = true;
		}
		return k;

	};

	boolean check_lineP(PVector a, PVector b,PVector c){

		boolean k = false;
		float d1 = PApplet.dist(a.x,a.y,b.x,b.y);
		float d2 = PApplet.dist(a.x,a.y,c.x,c.y);
		float d3 = PApplet.dist(b.x,b.y,c.x,c.y);
		float d4 = d2 + d3;

		if(d4 <= d1 + 0.05 && d4 >= d1 - 0.05){
			k = true;
		}
		return k;
	};

	boolean check_lineP(float x1, float y1,float x2,float y2, PVector c){

		boolean k = false;
		float d1 = PApplet.dist(x1,y1,x2,y2);
		float d2 = PApplet.dist(x1,y1,c.x,c.y);
		float d3 = PApplet.dist(x2,y2,c.x,c.y);
		float d4 = d2 + d3;

		if(d4 <= d1 + 0.05 && d4 >= d1 - 0.05){
			k = true;
		}
		return k;
	};

	public void drag(){
	};

	boolean pospoint(tabBoundary a,int n){
		float X = applet.mouseX;
		float Y = applet.mouseY;

		boolean k = false;

		for(int i=0;i<a.Boundaries.size();i++){
			tabBoundary b = a.Boundaries.get(i);

			float d1 = PApplet.dist(b.x1,b.y1,X,Y);
			float d2 = PApplet.dist(b.x2,b.y2,X,Y);

			if(d1<5){
				k = true;
			}

			if(d2<5){
				k = true;
			}}
		return k;
	};

	public void pospoint(tabBoundary a){
		float X = applet.mouseX;
		float Y = applet.mouseY;

		t = 0;

		boolean k = false;
		//if(!a.linedown){
		for(int i=0;i<a.Boundaries.size();i++){
			tabBoundary b = a.Boundaries.get(i);

			float d1 = PApplet.dist(b.x1,b.y1,X,Y);
			float d2 = PApplet.dist(b.x2,b.y2,X,Y);

			if(d1<5){
				applet.strokeWeight(5);
				applet.stroke(255);
				applet.point(b.x1,b.y1);
				if(applet.mousePressed&&!point1&&!a.point1){

					t++;
					vert = i;
					index = a.id;
					point1 = true;
					a.point1 = true;
					a.pdown = true;
					pdown = true;
				}}

			if(d2<5){
				applet.strokeWeight(5);
				applet.stroke(255);
				applet.point(b.x2,b.y2);
				if(applet.mousePressed&&!point2&&!a.point2){

					t++;
					vert1 = i;
					index = a.id;
					point2 = true;
					a.point2 = true;
					a.pdown = true;
					pdown = true;
				}}
			if(point1&&vert>=0&&index==a.id){
				tabBoundary c = a.Boundaries.get(vert);
				c.x1 = X;
				c.y1 = Y;
			}
			if(point2&&vert1>=0&&index==a.id){
				tabBoundary c = a.Boundaries.get(vert1);
				c.x2 = X;
				c.y2 = Y;
			}
			if(a.type==2){
				a.x1 = b.x1;a.y1 = b.y1;
				a.x2 = b.x2;a.y2 = b.y2;
			}
			if(a.type==4){

				if(vert==0)a.x1 = b.x1;
				if(vert==1)a.x2 = b.x1;
				if(vert==2)a.x3 = b.x1;
				if(vert==3)a.x4 = b.x1;

				if(vert1==0)a.x2 = b.x2;
				if(vert1==1)a.x3 = b.x2;
				if(vert1==2)a.x4 = b.x2;
				if(vert1==3)a.x1 = b.x2;
			}
			if(a.type==3||a.type==103){
				if(vert==0)a.x1 = b.x1;a.y1 = b.y1;
				if(vert==1)a.x2 = b.x1;a.y2 = b.y1;
				if(vert==2)a.x3 = b.x1;a.y3 = b.y1;

				if(vert1==0)a.x2 = b.x2;a.y2 = b.y2;
				if(vert1==1)a.x3 = b.x2;a.y3 = b.y2;
				if(vert1==2)a.x1 = b.x2;a.y1 = b.y2;

			}}

		if(t>0){
			k = true;
			hover = true;
		}
		if(!applet.mousePressed){
			a.update_dist(a.Boundaries);
			a.calc_center();
			a.create_rotation_points(a.Boundaries);
			a.update_angles();
		}
		if(!applet.mousePressed){

			vert = -1;
			vert1 = -1;
			index = -1;
			point1 = false;
			point2 = false;
			a.point2 = false;
			a.point1 = false;
			uindex = a.id;
			updatep = true;
			a.pdown = false;
			pdown = false;

		}
	};

	public void update_dist(ArrayList<tabBoundary> a){


		for(int i=0;i<a.size();i++){

			tabBoundary b = a.get(i);
			int id = b.id;
			//float Dist = dist.get
			float dx = (b.x1 - center.x);
			float dy = (b.y1 - center.y);
			float d  = PApplet.dist(b.x1,b.y1,center.x,center.y);
			Float [] d1 = {dx,dy};

			if(dist.size()==Boundaries.size()){dist.set(i,d);}
			else {dist.add(d);}
			if(pdist.size()==Boundaries.size()*2)pdist.set(i,d1);
			else pdist.add(d1);
		}
	};

	public void move(tabBoundary a,PVector p){
		float mx = p.x;
		float my = p.y;

		boolean down = false;

		for (int i=0;i<a.Boundaries.size();i++){

			tabBoundary b = a.Boundaries.get(i);

			float d1 = PApplet.dist(b.x1,b.y1,mx,my);
			float d2 = PApplet.dist(b.x2,b.y2,mx,my);

			boolean k = check_lineP(b.x1,b.y1,b.x2,b.y2,new PVector(mx,my));
			if(k&&!linedown&&d1>6&&d2>6&&!point1&&!point2&&applet.mousePressed&&!a.linedown){
				vert3 = i;
				sid = a.id;
				D1 = d1;
				D2 = d2;
				T1 = PApplet.atan2(b.y2 - b.y1,b.x2 - b.x1);
				T2 = PApplet.atan2(b.y1 - b.y2,b.x1 - b.x2);

				linedown = true;
				a.linedown = true;
			}}

		if(linedown && sid==a.id && a.linedown&&a.Boundaries.size()>0){

			tabBoundary b   = a.Boundaries.get(vert3);
			tabBoundary b_1 = a.Boundaries.get(0);
			tabBoundary bp1 = a.Boundaries.get(0);

			if(vert3>0)b_1 = a.Boundaries.get(vert3-1);
			else if(a.type!=106)b_1 = a.Boundaries.get(a.Boundaries.size()-1);
			if(vert3<a.Boundaries.size()-1)bp1 = a.Boundaries.get(vert3+1);
			else if(a.type!=106)bp1 = a.Boundaries.get(0);

			applet.stroke(255,0,0);
			PVector p1 = new PVector( mx + D2 * PApplet.cos(T1),my + D2 * PApplet.sin(T1));
			PVector p2 = new PVector( mx + D1 * PApplet.cos(T2),my + D1 * PApplet.sin(T2));
			//applet.line(p1.x,p1.y,p2.x,p2.y);

			b.x2 = p1.x;
			b.y2 = p1.y;
			b.x1 = p2.x;
			b.y1 = p2.y; 

			b_1.x2 = p2.x;
			b_1.y2 = p2.y;
			bp1.x1 = p1.x;
			bp1.y1 = p1.y;

		}

		if(!applet.mousePressed&&a.linedown){
			D1 = 0;D2 = 0;T1 = 0;T2 = 0;vert3 = -1;sid = -1;down = false;
			a.update_dist(a.Boundaries);
			uindex = a.id;
			updatep = true;
			linedown = false;
			a.linedown = false;
		}
	};

	public void create_circle(){
		float ax = applet.mouseX;
		float ay = applet.mouseY;

		tabBoundary n;
		if (circle&&!menudown&&applet.mousePressed){

			mdown = true;
			ax = applet.mouseX;
			ay = applet.mouseY;

			temp.add(new PVector(ax,ay));
			dx = ax - temp.get(0).x;
			dy = ay - temp.get(0).y;

			applet.strokeWeight(1);
			applet.stroke(col);
			applet.ellipseMode(PConstants.CORNER);
			applet.ellipse(temp.get(0).x,temp.get(0).y,dx,dy);
		}
		if(mdown&&!applet.mousePressed&&!point1&&!point2){

			if((temp.get(0).x!=temp.get(temp.size()-1).x&&temp.get(0).y!=temp.get(temp.size()-1).y)){
				tabBoundary a = new tabBoundary(temp.get(0).x,temp.get(0).y,dx,dy,0);
				a.id = Boundaries.size();
				Boundaries.add (a);
				//scene.Boundaries.add (a);
				theta.add(PApplet.atan2((temp.get(0).y + dy) - temp.get(0).y,(temp.get(0).x + dx) - temp.get(0).x));
				norm.add(PApplet.atan2((temp.get(0).x + dx) - temp.get(0).x, (temp.get(0).y + dy) - temp.get(0).y));
				Float [] vel = {0.0f,0.0f};
				velocity.add(vel);
				velocitypv.add(new PVector(0,0));
				temp = new ArrayList<PVector>();
				mdown = false;

			}}
	};

	public void create(){

		float ax = applet.mouseX;
		float ay = applet.mouseY;

		tabBoundary n;


		if (toggle&&!menudown&&applet.mousePressed){

			mdown = true;
			ax = applet.mouseX;
			ay = applet.mouseY;

			temp.add(new PVector(ax,ay));

			applet.line(temp.get(0).x,temp.get(0).y,temp.get(temp.size()-1).x,temp.get(temp.size()-1).y);
		}
		if(mdown&&!applet.mousePressed&&!point1&&!point2){
			if((temp.get(0).x!=temp.get(temp.size()-1).x&&temp.get(0).y!=temp.get(temp.size()-1).y)){
				n = new tabBoundary(temp.get(0).x,temp.get(0).y,temp.get(temp.size()-1).x,temp.get(temp.size()-1).y,2);
				n.id = Boundaries.size();
				Boundaries.add (n);
				theta.add(PApplet.atan2(temp.get(temp.size()-1).y - temp.get(0).y,temp.get(temp.size()-1).x - temp.get(0).x));
				norm.add(PApplet.atan2(temp.get(temp.size()-1).x - temp.get(0).x, temp.get(temp.size()-1).y - temp.get(0).y));
				Float [] vel = {0.0f,0.0f};
				velocity.add(vel);
				velocitypv.add(new PVector(0,0));
				temp = new ArrayList<PVector>();

			}mdown = false;
		}
	};

	public void createtab_tri(){

		float ax = applet.mouseX;
		float ay = applet.mouseY;

		tabBoundary n;
		float d;

		if (tri&&applet.mousePressed&&Bms.shapes.toggle){

			mdown = true;
			ax = applet.mouseX;
			ay = applet.mouseY;

			temp.add(new PVector(ax,ay));
			d = PApplet.dist(ax,ay,temp.get(0).x,temp.get(0).y);

			float Norm = -PApplet.atan2(temp.get(0).x - ax,temp.get(0).y - ay);

			float X2 = (float) (ax + d/1.5 * PApplet.cos(Norm));
			float Y2 = (float) (ay + d/1.5 * PApplet.sin(Norm));
			float X3 = (float) (ax - d/1.5 * PApplet.cos(Norm));
			float Y3 = (float) (ay - d/1.5 * PApplet.sin(Norm));

			applet.beginShape();
			applet.stroke(255);
			applet.fill(0);

			applet.vertex(temp.get(0).x,temp.get(0).y);
			applet.vertex(X2,Y2);
			applet.vertex(X3,Y3);
			applet.endShape(PConstants.CLOSE);
		}
		if(mdown&&!applet.mousePressed&&!point1&&!point2){
			if((temp.get(0).x!=temp.get(temp.size()-1).x&&temp.get(0).y!=temp.get(temp.size()-1).y)){
				n = new tabBoundary(temp.get(0).x,temp.get(0).y,ax,ay,3);
				n.id = Boundaries.size();
				Boundaries.add (n);
				theta.add(PApplet.atan2(temp.get(temp.size()-1).y - temp.get(0).y,temp.get(temp.size()-1).x - temp.get(0).x));
				norm.add(PApplet.atan2(temp.get(temp.size()-1).x - temp.get(0).x, temp.get(temp.size()-1).y - temp.get(0).y));
				Float [] vel = {0.0F,0.0F};
				velocity.add(vel);
				velocitypv.add(new PVector(0,0));
				temp = new ArrayList<PVector>();

			}mdown = false;
		}
	};

	public void createtabRect(){

		float ax = applet.mouseX;
		float ay = applet.mouseY;

		tabBoundary n;
		if (square&&!menudown&&applet.mousePressed&&Bms.shapes.toggle){

			mdown = true;
			ax = applet.mouseX;
			ay = applet.mouseY;

			temp.add(new PVector(ax,ay));
			dx = ax - temp.get(0).x;
			dy = ay - temp.get(0).y;

			applet.strokeWeight(20);
			applet.stroke(0);
			applet.point(ax,ay);
			applet.strokeWeight(1);
			applet.stroke(col);
			applet.rect(temp.get(0).x,temp.get(0).y,dx,dy);
		}
		if(mdown&&!applet.mousePressed&&!point1&&!point2){

			if((temp.get(0).x!=temp.get(temp.size()-1).x&&temp.get(0).y!=temp.get(temp.size()-1).y)){
				tabBoundary a = new tabBoundary(temp.get(0).x,temp.get(0).y,dx,dy,4);
				a.id = Boundaries.size();
				Boundaries.add (a);
				theta.add(PApplet.atan2((temp.get(0).y + dy) - temp.get(0).y,(temp.get(0).x + dx) - temp.get(0).x));
				norm.add(PApplet.atan2((temp.get(0).x + dx) - temp.get(0).x, (temp.get(0).y + dy) - temp.get(0).y));
				temp = new ArrayList<PVector>();
				Float [] vel = {0.0f,0.0f};
				velocity.add(vel);
				velocitypv.add(new PVector(0,0));
				mdown = false;

			}}
	};

	public void create_poligon(){
		float ax = applet.mouseX;
		float ay = applet.mouseY;

		tabBoundary n;

		Sides.draw();
		sides = (int) Sides.value;
		if(sides>0){

			if (applet.mousePressed&&mcount==0&&!complexsub.pos()){

				mdown = true;
				ax = applet.mouseX;
				ay = applet.mouseY;

				temp.add(new PVector(ax,ay));
				dx = ax - temp.get(0).x;
				dy = ay - temp.get(0).y;

				applet.strokeWeight(20);
				applet.stroke(0);
				applet.point(ax,ay);
				applet.strokeWeight(1);
				applet.stroke(col);
				applet.beginShape();

				float X = (temp.get(0).x + ax)/2;
				float Y = (temp.get(0).y + ay)/2;

				for(int i=0;i<sides;i++){

					float theta = PApplet.radians(360/sides*i);

					float w = PApplet.abs((temp.get(0).x - ax))/2;
					float h = PApplet.abs((temp.get(0).y - ay))/2;

					PVector a = new PVector(X + w * PApplet.cos(theta),Y + h * PApplet.sin(theta));
					applet.vertex(a.x,a.y);

					//applet.vertex(X + w * applet.cos(theta2),Y + h * applet.sin(theta2));

				}
				applet.endShape(PConstants.CLOSE);
			}
			if(mdown&&!applet.mousePressed){

				if((temp.get(0).x!=temp.get(temp.size()-1).x&&temp.get(0).y!=temp.get(temp.size()-1).y)){
					n = new tabBoundary(temp.get(0).x,temp.get(0).y,ax,ay,201);
					float X = (temp.get(0).x + ax)/2;
					float Y = (temp.get(0).y + ay)/2;
					n.center = new PVector(X,Y);

					for(int i=0;i<sides;i++){

						float theta = PApplet.radians(360/sides*i);
						float theta2 = PApplet.radians(360/sides*(i+1));

						float w = PApplet.abs((temp.get(0).x - ax))/2;
						float h = PApplet.abs((temp.get(0).y - ay))/2;

						PVector a = new PVector(X + w * PApplet.cos(theta),Y + h * PApplet.sin(theta));
						PVector b = new PVector(X + w * PApplet.cos(theta2),Y + h * PApplet.sin(theta2));
						//applet.vertex(a.x,a.y);
						n.Boundaries.add(new tabBoundary(a.x,a.y,b.x,b.y));

					}

					n.id = Boundaries.size();
					Boundaries.add (n);
					theta.add(PApplet.atan2((temp.get(0).y + dy) - temp.get(0).y,(temp.get(0).x + dx) - temp.get(0).x));
					norm.add(PApplet.atan2((temp.get(0).x + dx) - temp.get(0).x, (temp.get(0).y + dy) - temp.get(0).y));
					temp = new ArrayList<PVector>();
					Float [] vel = {0.0f,0.0f};
					velocity.add(vel);
					velocitypv.add(new PVector(0,0));
					mdown = false;

				}}}else applet.text("Please define number of sides",100,100);
	};

	public void create_bezier(){

	};


	public void drawPath(){
		//mcount = 0;
		float mx = applet.mouseX;
		float my = applet.mouseY;

		float sw = w/cols, sh = h/rows;
		if(!applet.mousePressed){
			mcount = 0;
			pointadded = false;
		}
		if(path){
			complexsub.draw();

			if(applet.mousePressed&&mcount==0&&!complexsub.pos())mdown = true;
			else mdown = false;
			if(applet.mousePressed&&complexsub.pos())cfinish = true;
			else if(!applet.mousePressed)cfinish = false;

			if(mdown){

				if(!complexsub.pos()){
					Point a = new Point(PApplet.floor(mx/sw)*sw,PApplet.floor(my/sh)*sh,grid.size(),Bms);
					Point b;
					boolean k = grid.contains(a);
					if(grid.size()==0)grid.add(a);
					else{
						b = grid.get(grid.size()-1);
						if(b.x!=a.x||b.y!=a.y)grid.add(a);
					}}
				//pointadded = true;
			}
			for(int i=grid.size()-1;i>-1;i--){
				Point a = grid.get(i);

				a.draw();

				if(grid.size()>1&&!cfinish){
					int pos = 0;

					if(i<grid.size()-1)pos = i+1;

					Point b = grid.get(pos);
					applet.stroke(0);
					applet.strokeWeight(1);
					if(i<grid.size()-1)applet.line(b.x,b.y,a.x,a.y);

				}}

			if(cfinish){

				if(grid.size()>0)Boundaries.add(new tabBoundary(grid,0));

				grid = new ArrayList<Point>();

				cfinish = false;
				mdown = false;
			}}
		applet.strokeWeight(1);
	};

	public void create_free(){

	};

	Boolean posWindow(){

		float mx = applet.mouseX;
		float my = applet.mouseY;

		return(mx>x1&&mx<x2&&my>y1&&my<y2);

	};

	public void mass_Center(){

		ccount = 0;
		PVector Center = new PVector(0,0);
		if(center == null|| center !=Center){
			for(int i=0;i<Boundaries.size();i++){

				tabBoundary a = Boundaries.get(i);
				PVector midp = new PVector((a.x1+a.x2)/2,(a.y1+a.y2)/2);
				Center.add(midp);
				Midpoints.add(midp);
				ccount++;
			}
			if(ccount==Boundaries.size())center = Center;
		}
	};


	PVector check_intersect(tabBoundary a, tabBoundary b){

		float a1 = a.y2 - a.y1;
		float b1 = a.x1 - a.x2;
		float c1 = a1 * a.x1 + b1 * a.y1;
		float a2 = b.y2 - b.y1;
		float b2 = b.x1 - b.x2;
		float c2 = a2 * b.x1 + b2 * b.y1;
		float denom = a1 * b2 - a2 * b1;

		if((a.x1==b.x1||a.x2==b.x2)&&(a.y1==b.y1||a.y2==b.y2)){

			return null;
		}
		else{

			Float X = (b2 *c1 - b1 * c2) / denom;
			Float Y = (a1 *c2 - a2 * c1) / denom;

			PVector p = new PVector(X,Y);   
			boolean Linea = ((p.x<a.x1&&p.x>a.x2)||(p.x>a.x1&&p.x<a.x2))||((p.y<a.y1&&p.y>a.y2)||(p.y>a.y1&&p.y<a.y2));
			boolean Lineb = ((p.x<b.x1&&p.x>b.x2)||(p.x>b.x1&&p.x<b.x2))||((p.y<b.y1&&p.y>b.y2)||(p.y>b.y1&&p.y<b.y2));
			boolean Linec = (p.x!=a.x1&&p.x!=a.x2)&&(p.y!=a.y1&&p.y!=a.y2);

			Linec = (PApplet.dist(p.x,p.y,a.x1,a.y1)>0.5&&PApplet.dist(p.x,p.y,a.x1,a.y1)>0.5);
			if(Linec&&Linea&&Lineb){
				//applet.strokeWeight(20);
				//  applet.stroke(0);
				//  applet.point(p.x,p.y);
				//  applet.strokeWeight(1);
				return p;
			}
			else{
				return null;
			}}
	};

	PVector check_intersect2(tabBoundary a, tabBoundary b){

		float a1 = a.y2 - a.y1;
		float b1 = a.x1 - a.x2;
		float c1 = a1 * a.x1 + b1 * a.y1;
		float a2 = b.y2 - b.y1;
		float b2 = b.x1 - b.x2;
		float c2 = a2 * b.x1 + b2 * b.y1;
		float denom = a1 * b2 - a2 * b1;

		Float X = (b2 *c1 - b1 * c2) / denom;
		Float Y = (a1 *c2 - a2 * c1) / denom;

		PVector p = new PVector(X,Y);   
		boolean Linea = ((p.x<=a.x1&&p.x>=a.x2)||(p.x>=a.x1&&p.x<=a.x2))||((p.y<=a.y1&&p.y>=a.y2)||(p.y>=a.y1&&p.y<=a.y2));
		boolean Lineb = ((p.x<=b.x1&&p.x>=b.x2)||(p.x>=b.x1&&p.x<=b.x2))||((p.y<=b.y1&&p.y>=b.y2)||(p.y>=b.y1&&p.y<=b.y2));
		boolean Linec = (p.x==a.x1||p.x==a.x2)||(p.y==a.y1||p.y==a.y2);
		boolean Lined = (p.x==b.x1||p.x==b.x2)&&(p.y==b.y1||p.y==b.y2);
		if(Linea&&Lineb||Linec){
			return p;
		}
		else{
			return null;
		}
	};

};


