package androidGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Grid_space{
	BMScontrols Bms;
	PApplet applet;
	int id,iid,pid,row,t,toggle,steps = 10000,psteps,counter = 0,index,t2,xpos,ypos,sides,currentId,totalChildren,
			popCount,popId=-1;
	float x,y,w,h,vx,vy,ax,ay,elasticity,lim,mass = 10,type,dx,dy;
	String label;
	boolean drag,resize,collide,forward = true,backward = false, pause = false,graph,visible,field,neighboured,
			convolved = false,image,webcam,attractor,findHeading;
	boolean popVisited,popVisited1,popVisited2,counted,grid,border,visited,visited1,visited3,visited4,updated,
	active = true,corrected,popVerified,histVerified,popClosed,wVisited,walled,popColChecked,compared,debug,
	node,pathConnected,noNewPopNeighbours;

	PVector heading ;
	float incidence;
	float reflection ;
	float norm;
	Grid parent;
	Grid_space leader = this,next = null,transfer;
	PImage img,shade;

	Shape shape;
	ArrayList<PVector> pos = new ArrayList<PVector>();
	ArrayList<PVector> vel = new ArrayList<PVector>();
	ArrayList<PVector> headings = new ArrayList<PVector>();

	ArrayList<Grid_space> Neighbours = new ArrayList<Grid_space>();
	ArrayList<Grid_space> visitedBy = new ArrayList<Grid_space>();
	ArrayList<Grid_space> modifiedAgents = new ArrayList<Grid_space>();
	ArrayList<Grid_space> Neighbours2 = new ArrayList<Grid_space>();
	ArrayList<Grid_space> Neighbours3 = new ArrayList<Grid_space>();
	ArrayList<Grid_space> Neighbours_8 = new ArrayList<Grid_space>();
	ArrayList<Grid_space> Neighbours_4 = new ArrayList<Grid_space>();
	ArrayList<Grid_space> NeighboursWalls = new ArrayList<Grid_space>();
	ArrayList<Grid_space> connections = new ArrayList<Grid_space>();
	ArrayList<Grid_space> parents = new ArrayList<Grid_space>();
	ArrayList<Boundary> Boundaries = new ArrayList<Boundary>();
	ArrayList<Boundary> Boundaries2 = new ArrayList<Boundary>();
	ArrayList<Grid_space> history = new ArrayList<Grid_space>();
	ArrayList<PVector> Vertices = new ArrayList<PVector>();

	Grid_space []Neighbours_a;
	Grid_space []Neighbours_b;

	ArrayList<Boolean> Walls_a = new ArrayList<Boolean>();
	ArrayList<Boolean> Walls_b = new ArrayList<Boolean>();

	ArrayList<PVector[][]> walls = new ArrayList<PVector[][]>();

	PVector temp;
	HashMap<String,Boolean> values = new HashMap<String,Boolean>();

	public int col,col2 = -10000,col3,col4,col5,col6,col7,popCol;

	Grid_space[][] kernel2d =   {{null , null, null }, 
			{null , this, null }, 
			{null , null, null }}; 
	Grid_space[] kernel1d =      {null,null,null,
			null,this,null,
			null,null,null};  

	//Grid_space(float xx,float yy, float ww, float hh, int Id,int I, int J){

		//  x = xx;
		//  y = yy;
		//  w = ww;
		//  h = hh;
		//  xpos = I;
		//  ypos = J;
		//  id = Id;
		//  iid = id;
		//  ax = 0.02;
		//  ay = 0.02;

		//  Neighbours_a = new Grid_space[8];
	//  Neighbours_b = new Grid_space[4];
	//  for(int i = 0; i<Neighbours_a.length;i++){
	//    Walls_a.add(true);
	//  }
	//  for(int i = 0; i<Neighbours_b.length;i++){
	//    Walls_b.add(true);
	//  }

	//  for(int i = 0; i<Neighbours_a.length;i++){
	//    Boundaries.add(new Boundary(0,0,0,0));
	//  }
	//  for(int i = 0; i<Neighbours_b.length;i++){
	//    Boundaries.add(new Boundary(0,0,0,0));
	//  }
	//  //shade = new PImage(w,h);

	//};

	Grid_space(float xx,float yy, float ww, float hh, int Id,int I, int J,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		xpos = I;
		ypos = J;
		id = Id;
		iid = id;
		ax = 0.02f;
		ay = 0.02f;

		Neighbours_a = new Grid_space[8];
		Neighbours_b = new Grid_space[4];
		for(int i = 0; i<Neighbours_a.length;i++){
			Walls_a.add(true);
		}
		for(int i = 0; i<4;i++){
			Walls_b.add(true);
		}
		Boundary a1,a2,a3,a4;
		a1 = new Boundary(x,y,x+w,y,bms);
		a2 = new Boundary(x+w, y, x+w, y+h,bms);
		a3 = new Boundary(x+w, y+h, x, y+h,bms);
		a4 = new Boundary(x, y+h, x, y,bms);

		Bms.boundaries.add(a1);
		Bms.boundaries.add(a4);
		Bms.boundaries.add(a2);
		Bms.boundaries.add(a3);

		//shade = new PImage(w,h);

	};

	public void save(){

	};

	public void load(){

	};

	public void init(){

	};

	public void draw(){

	};

	public void drawBoundaries(){

		for(int i=0;i<Walls_b.size();i++){
			Boundary b = Boundaries.get(i);
			b.col = applet.color(applet.red(col)+30,applet.green(col)+30,applet.blue(col)+30);
			if(Walls_b.get(i)) b.draw3();
		}
	};

	public void drawBoundaries2(){

		for(int i=0;i<Boundaries.size();i++){
			Boundary b = Boundaries.get(i);
			b.col = applet.color(applet.red(col)+30,applet.green(col)+30,applet.blue(col)+30);
			applet.stroke(b.col);
			if(Walls_b.get(i)){
				applet.line(b.x1,b.y1,b.x2,b.y2);
			} 
			//b.draw3();
		}
	};

	public void move(){

		float a = 5;

		vx += applet.random(-a,a);
		vy += applet.random(-a,a);

	}

	boolean pos(){

		return applet.mouseX>=x&&applet.mouseX<=x+w&&applet.mouseY>=y&&applet.mouseY<=y+h;
	};

	public void info(){
		float xx = applet.mouseX;
		float yy = applet.mouseY;
		float vspacing = 20;
		if(pos()&&debug){
			applet.fill(240, 214, 50,100);
			applet.rect(xx,yy,120,100);
			for(int i=0;i<Neighbours_4.size();i++){
				applet.fill(0);
				// String s = "false";
				// if (Walls_a[i])s = "true";
				applet.text(applet.str(Walls_a.get(i)),xx + 40 * i,yy);
			}
			applet.text("Direc Neighbours: " + Neighbours_4.size(),xx,yy + vspacing * 1);
			applet.text("All Neighbours: " + Neighbours_8.size(),xx,yy + vspacing * 2);

			applet.text("Node: " + applet.str(node), xx,yy + vspacing * 3);
			applet.text("Id: " + id, xx,yy + vspacing * 4);
			applet.text("Conn. Neighbours: " + connections.size(),xx,yy + vspacing * 5);
			applet.text(applet.str(toggle),xx,yy + vspacing * 6);
			applet.text("Pop Visited: " + applet.str(popVisited),xx,yy + vspacing * 7);
			applet.text("Visited: " + applet.str(popVisited),xx,yy + vspacing * 8);
			applet.text("Path Connected: " + applet.str(pathConnected),xx,yy + vspacing * 9);
			applet.text("Parents: " + applet.str(popVisited),xx,yy + vspacing * 10);
			for(int i=0;i<parents.size();i++){
				applet.fill(0);
				applet.text(parents.get(i).id,xx + 60 + 40 * i,yy + vspacing * 11);
			}
			for(int i=0;i<connections.size();i++){
				applet.fill(0);
				applet.text(connections.get(i).id,xx + 80 * i,yy + vspacing * 12);
			}


		}
	};

	public void update(){
		steps = pos.size()-1;
		if(forward){

			if(index==0){
				if(pos.size()>0)counter = pos.size()-1;

				//if(lim>0){
				//  vx += Limit(vx,lim);
				//  vy += Limit(vy,lim);
				//}else{

				if(vx>4)vx = 4;
				if(vx<-4)vx = -4;
				if(vy>4)vx = 4;
				if(vy<-4)vx = -4;
				if(boundaries())reflect();

				x += vx;
				y += vy;

				//	      if(attractors.attractors.size()>0){
				//	        //if(findHeading)find_heading();
				//	        pos.add(new PVector(x,y));
				//	        vel.add(new PVector(vx,vy));
				//	      }
			}
			else if(index ==1){
				vx = vel.get(counter).x;
				vy = vel.get(counter).y;
				x = pos.get(counter).x;
				y = pos.get(counter).y;
				counter++;
				if(counter == pos.size()){
					index = 0;
				}}}
		else if(backward){
			if(counter>0){
				index = 1;
				vx = vel.get(counter).x;
				vy = vel.get(counter).y;
				x = pos.get(counter).x;
				y = pos.get(counter).y;
				counter --;
				if(counter==0){
					pos = new ArrayList<PVector>();
					vel = new ArrayList<PVector>();
					//pos.remove(pos.size()-1);
				}
				if(counter == 0)index = 0;

				//steps = counter;
			}}
		else if(pause){

		}

		if(graph)graph();

	};

	public void mouse(){
		if(forward){
			float t1 = applet.atan2(applet.mouseY - y, applet.mouseX - x);

			vx += ax * applet.cos(t1);
			vy += ay * applet.sin(t1);
		}
	};


	Grid_space getNewBorder(){

		Grid_space g = null;

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours2.size();i++){

			Grid_space a = Neighbours2.get(i);

			if(a.col2!=col2)neighbours.add(a);
		}

		if(neighbours.size()>0)g = neighbours.get(applet.floor(applet.random(neighbours.size())));
		return g;
	};

	Grid_space getNewNeighbour(){

		Grid_space g = null;

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);

			if(a!=null&&!a.popVisited&&a.col2==col2)neighbours.add(a);
		}

		if(neighbours.size()>0)g = neighbours.get(applet.floor(applet.random(neighbours.size())));
		return g;
	};

	Grid_space getNewNeighbour_(Grid g1,Grid_space k){

		Grid_space g = null;

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);

			if(a!=null&&!a.popVisited&&a.col2==col2){
				a.pid = k.pid;
				a.popId = k.popId;
				a.popCol = k.popCol;
				g1.popHist.add(new ArrayList<Grid_space>());
				g1.popStack.add(new ArrayList<Grid_space>());
				g1.agents.add(a);
				g1.agentsBackup.add(a);

				neighbours.add(a);
			}}

		if(neighbours.size()>0)g = neighbours.get(applet.floor(applet.random(neighbours.size())));
		return g;
	};

	ArrayList getNewNeighbour1(Grid g){

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);

			if(a!=null){
				if(a.popVisited
						&&a.popId!=popId
						&&a.pid!=pid
						&&a.col2==col2
						//&&a.popCol!=popCol
						//&&g.agentsConst.get(a.pid).popCol!=g.agentsConst.get(pid).popCol
						//&&g.agentsConst.get(a.pid)!=g.agentsConst.get(popId)
						//&&!Walls_b.get(i)
						&&g.agentsConst.get(a.popId).col2==g.agentsConst.get(popId).col2
						&&g.agentsBackup.get(popId).steps>g.agentsBackup.get(a.popId).steps
						&&!g.agentsConst.get(popId).modifiedAgents.contains(g.agentsConst.get(a.popId))
						&&!g.agentsConst.get(a.popId).visitedBy.contains(g.agentsConst.get(popId))
						)neighbours.add(a);

				if(a.popVisited&&a.popId!=popId&&a.col2==col2&&a.popCol!=popCol){

					if(!g.agentsConst.get(popId).modifiedAgents.contains(g.agentsConst.get(a.popId))){
						g.agentsConst.get(popId).modifiedAgents.add(g.agentsConst.get(a.popId));
						if(a.pid!=a.popId){
							g.agentsConst.get(popId).modifiedAgents.add(g.agentsConst.get(a.pid));
							if(pid!=popId)g.agentsConst.get(pid).modifiedAgents.add(g.agentsConst.get(a.pid));
						}
						if(pid!=popId){
							g.agentsConst.get(pid).modifiedAgents.add(g.agentsConst.get(a.popId));
							if(a.pid!=a.popId)g.agentsConst.get(pid).modifiedAgents.add(g.agentsConst.get(a.pid));
						}}
					if(!g.agentsConst.get(a.popId).visitedBy.contains(g.agentsConst.get(popId)))
						g.agentsConst.get(a.popId).visitedBy.add(g.agentsConst.get(popId));
				}}}
		if(neighbours.size()==0)neighbours=null;
		return neighbours;
	};

	ArrayList getNewNeighbour4(Grid g){

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);
			if(a!=null){
				if(a.popVisited
						&&a.popId!=popId
						&&a.col2==col2
						&&a.popCol!=popCol
						//&&!Walls_b.get(i)
						&&g.agentsConst.get(a.popId).col2==g.agentsConst.get(popId).col2
						&&g.agentsBackup.get(popId).steps>g.agentsBackup.get(a.popId).steps
						&&!g.agentsConst.get(popId).modifiedAgents.contains(g.agentsConst.get(a.pid))
						&&!g.agentsConst.get(a.popId).visitedBy.contains(g.agentsConst.get(popId))
						)neighbours.add(a);

			}}
		if(neighbours.size()==0)neighbours=null;
		return neighbours;
	};

	ArrayList getNewNeighbour2(Grid g){

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);

			if(a!=null){
				if(a.popVisited&&a.pid!=pid&&a.col2==col2&&a.leader.steps>leader.steps){
					neighbours.add(a);
					if(!a.visitedBy.contains(this.leader))a.visitedBy.add(this.leader);
				}

				if( a.popVisited&&a.col2==col2&&a.pid!=pid
						&&a.popId>-1&&popId>-1&&!g.agents.get(popId).modifiedAgents.contains(g.agents.get(a.popId))
						&&g.agentsConst.get(popId).steps<g.agentsConst.get(a.popId).steps)neighbours.add(a);

				if( a.popVisited&&a.col2!=col2
						&&!g.agents.get(pid).modifiedAgents.contains(g.agents.get(a.pid))
						&&g.agentsConst.get(popId).steps<g.agentsConst.get(a.popId).steps){
					a.popCol=popCol;
					neighbours.add(a);
				}}
		}
		if(neighbours.size()==0)neighbours=null;
		return neighbours;
	};

	Grid_space getNewNeighbour3(Grid g){

		Grid_space k = null;

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours_4.size();i++){

			Grid_space a = Neighbours_4.get(i);

			if(a!=null){
				if(a.popVisited&&a.col2==col2
						&&!g.agents.get(popId).modifiedAgents.contains(g.agents.get(a.popId))
						&&g.agentsConst.get(popId).steps<g.agentsConst.get(a.popId).steps)neighbours.add(a);

				if(a.popVisited&&a.col2!=col2
						&&!g.agents.get(pid).modifiedAgents.contains(g.agents.get(a.pid))
						&&g.agentsConst.get(popId).steps<g.agentsConst.get(a.popId).steps)neighbours.add(a);
			}}


		if(neighbours.size()>0)k = neighbours.get(applet.floor(applet.random(neighbours.size())));
		return k;
	};

	Grid_space getNewWallNeighbour(Grid k){

		Grid_space g = null;

		ArrayList<Grid_space> neighbours = new ArrayList<Grid_space>();

		for(int i=0;i<Neighbours.size();i++){

			Grid_space a = Neighbours.get(i);
			if(a!=null){
				if(k.wallSquares.contains(a)){
					for(int j=0;j<a.Walls_b.size();j++){

						if((a.Walls_b.get(j)&&a.col2==col2)&&!a.wVisited){
							if(!neighbours.contains(a))neighbours.add(a);
							else i++;
						}}}}}


		if(neighbours.size()>0)g = neighbours.get(applet.floor(applet.random(neighbours.size())));
		return g;

	};

	Grid_space sort(ArrayList<Grid_space> a){

		Grid_space g = a.get(0);

		for(int i=1;i<a.size();i++){
			Grid_space g1 = a.get(i);

			if(g.steps<g1.steps)g = g1;
		}

		return g;
	};

	public void find_heading(){

		heading = new PVector(x,y);

		if(pos.size()>0){

			PVector p = pos.get(pos.size()-1);
			PVector c = new PVector(x,y);

			//PVector p = new PVector(x + vx,y +vy);;
			//PVector c = new PVector(x,y);

			int mult = 10;
			float dx = c.x - p.x;
			float dy = c.y - p.y;

			float theta = applet.atan2(dy,dx);
			incidence = theta;

			if(dx>0)heading.x = x + dx * mult * applet.cos(theta);
			else heading.x = x - dx * mult * applet.cos(theta);
			if(dy>0)heading.y = y + dy * mult * applet.sin(theta);
			else heading.y = y - dy * mult * applet.sin(theta);

			heading.x += vx;
			heading.y += vy;

		}


	};

	//revise code
	public void attractors(){
		if(forward&&attractor){
			//		    if(attractors.attractors.size()>0){
			//		    for(int i=0;i<attractors.attractors.size();i++){
			//		      
			//		      Attractor a = attractors.attractors.get(i);
			//		      
			//		      float t1 = applet.atan2(a.y - y, a.x - x);
			//		    
			//		      float d = applet.dist(x,y,a.x,a.y);
			//		      if(a.type == 0){
			//		      ax = (a.mass + mass)/(d*d)*9.81*a.intensity;
			//		      ay = (a.mass + mass)/(d*d)*9.81*a.intensity;
			//		      }
			//		       else if( a.type == 1){
			//		      ax = a.intensity;
			//		      ay = a.intensity;
			//		      vx -=  a.intensity * dx; ;
			//		      vy -=  a.intensity * dy; ;
			//		      }
			//		      vx -= ax * applet.cos(t1);
			//		      vy -= ay * applet.sin(t1);
			//		      
			//		    }}
		}
	};

	public void reflect(){
		if(collide){
			vx = -ax  * applet.cos(reflection);
			vy = -ay  * applet.sin(reflection);
		}

	};

	public void popBoundaries(){
		for(int i=0;i<walls.size();i++){

		}
	};

	boolean boundaries(){
		t2 = 0;
		boolean k = false;
		//float incidence = 0;
		//PVector temp = new PVector(0,0);
		//if(forward){
		//if(lines.Boundaries.size()>0){

		//for(int i=0;i<lines.Boundaries.size();i++){

		//  Boundary a = lines.Boundaries.get(i);
		//  float n = lines.norm.get(i);

		//  PVector a1 = new PVector(a.x1,a.y1); 
		//  PVector a2 = new PVector(a.x2,a.y2); 

		//  boolean k2 = check_lineP(a1,a2,new PVector(x,y));
		//  //norm.set(0,a.norm);
		//  norm = n;
		//  if(k2){
		//    t2++;
		//  }}}}

		//if(t2>0){
		//  k = true;
		//  collide = true;
		//}
		//else{
		//  collide = false;
		//}

		//reflection = norm + (norm + incidence);
		return k;
	};


	boolean check_lineP(PVector a, PVector b,PVector c){

		boolean k = false;
		float d1 = applet.dist(a.x,a.y,b.x,b.y);
		float d2 = applet.dist(a.x,a.y,c.x,c.y);
		float d3 = applet.dist(b.x,b.y,c.x,c.y);
		float d4 = d2 + d3;


		//if(d5>=inc/2&&d6>=inc/2){
		if(d4 <= d1 + 0.05 && d4 >= d1 - 0.05)k = true;

		return k;
	};

	float Limit(float a,float b){
		if(a>0)if(a > b) a = b;

		if(a<0)if(a < -b) a = b;

		return a;
	};

	public void wrap(){

		if(x>applet.width)x=1;
		if(x<0)x=applet.width;
		if(y>applet.height)y=1;
		if(y>applet.height)x=1;
		if(y<0)y=applet.height;
	};

	public void graph(){
		applet.stroke(col);
		int max = 10;
		if(forward){
			if(index==0){

				if(pos.size()>max&& pos.size()>2){
					for( int i=pos.size()-max;i<pos.size()-1;i++){

						PVector a = pos.get(i);
						PVector b = pos.get(i+1);

						applet.line(a.x,a.y,b.x,b.y);

					}} else{
						for( int i=0;i<pos.size()-1;i++){

							PVector a = pos.get(i);
							PVector b = pos.get(i+1);

							applet.line(a.x,a.y,b.x,b.y);

						}}}
			else if(index==1){
				if(counter>max&& pos.size()>2){
					for( int i=counter-max;i<counter;i++){
						PVector a = pos.get(i);
						PVector b = pos.get(i+1);

						applet.line(a.x,a.y,b.x,b.y);

					}}else{
						for( int i=counter-max;i<counter;i++){
							PVector a = pos.get(i);
							PVector b = pos.get(i+1);

							applet.line(a.x,a.y,b.x,b.y);

						}}}}
		else if(backward){
			if(counter>max&& pos.size()>2){
				for( int i=counter-max;i<counter;i++){
					PVector a = pos.get(i);
					PVector b = pos.get(i+1);

					applet.line(a.x,a.y,b.x,b.y);
				}}else{
					for(int i=0;i<counter;i++){
						PVector a = pos.get(i);
						PVector b = pos.get(i+1);

						applet.line(a.x,a.y,b.x,b.y);
					}}}
	};

	public void bounce(){
		if(forward){
			float e = elasticity;
			if(elasticity!=0){
				if(x >= applet.width )vx = - vx;
				if(x <= 0)vx = - vx;
				if(y >= applet.height )vy = - vy;
				if(y <= 0 )vy = - vy;
			}

			else{
				if(x >= applet.width )vx = - vx * (1 - e);
				if(x <= 0)vx = - vx * (1 - e);
				if(y >= applet.height )vy = - vy * (1 - e);
				if(y <= 0 )vy = - vy * (1 - e);
			}}
	};


	public void get_neighbours(GridImg img){
		if(!neighboured){


			ArrayList<Grid_space> n = new ArrayList<Grid_space>();
			ArrayList<Grid_space> n2 = new ArrayList<Grid_space>();
			int cell = id;

			Grid_space b = img.imsquares.get(cell);

			Grid_space topl,top,topr,right,btmr,btm,btml,left;

			topl = index2(xpos - 1, ypos - 1,img);
			top = index2(xpos , ypos - 1,img);
			topr = index2(xpos + 1, ypos - 1,img);
			right = index2(xpos + 1, ypos,img);
			btmr = index2(xpos + 1, ypos + 1,img);
			btm = index2(xpos , ypos + 1,img);
			btml = index2(xpos - 1, ypos + 1,img);
			left = index2(xpos - 1, ypos ,img);

			if(topl!=null){
				n.add(topl);
				Neighbours_a[0] = topl;
				kernel1d[0] = topl;
			}
			if(top!=null){
				n.add(top);
				n2.add(top);
				Neighbours_a[1] = top;
				Neighbours_b[0] = top;
				kernel1d[1] = top;
			}
			if(topr!=null){
				n.add(topr);
				Neighbours_a[2] = topr;
				kernel1d[2] = topr;
			}
			if(right!=null){
				n.add(right);
				n2.add(right);
				Neighbours_a[3] = right;
				Neighbours_b[1] = right;
				kernel1d[3] = right;
			}
			if(btmr!=null){
				n.add(btmr);
				Neighbours_a[4] = btmr;
				kernel1d[5] = btmr;
			}
			if(btm!=null){
				n.add(btm);
				n2.add(btm);
				Neighbours_a[5] = btm;
				Neighbours_b[2] = btm;
				kernel1d[6] = btm;
			}
			if(btml!=null){
				n.add(btml);
				Neighbours_a[6] = btml;
				kernel1d[7] = btml;
			}
			if(left!=null){
				n.add(left);
				n2.add(left);
				Neighbours_a[7] = left;
				Neighbours_b[3] = left;
				kernel1d[8] = left;
			}
			Neighbours = n;
			Neighbours2 = n2;
			neighboured = true;
		}
	};

	public void compareNeighbours(){

		if(!compared){
			for(int i=0;i<Neighbours_b.length;i++){
				Grid_space g = Neighbours_b[i];
				//Grid_space g = Neighbours_b.get(i);
				if(g!=null){
					if(g.col==col)Walls_b.set(i,false);
					else Walls_b.set(i,true);
				}
				else Walls_b.set(i,true);
				//applet.fill(255);

				if(pos()&&debug){
					applet.fill(0);
					applet.stroke(0);
					applet.ellipse(x+w/2,y+h/2,10,10);
					if(g!=null&&g.col!=col)applet.line(x+w/2,y+h/2,g.x+w/2,g.y+h/2);
					if(g!=null)applet.text(i,g.x+w/2+10,g.y+h/2);
				}
			}}
	};

	public void get_neighbours(Grid g){

		if(!neighboured){

			ArrayList<Grid_space> n = new ArrayList<Grid_space>();
			ArrayList<Grid_space> n2 = new ArrayList<Grid_space>();
			ArrayList<Grid_space> n3 = new ArrayList<Grid_space>();
			int cell = id; 

			Grid_space b = g.squares.get(cell);

			Grid_space topl,top,topr,right,btmr,btm,btml,left;

			topl = index2(xpos - 1, ypos - 1,g);
			top = index2(xpos , ypos - 1,g);
			topr = index2(xpos + 1, ypos - 1,g);
			right = index2(xpos + 1, ypos,g);
			btmr = index2(xpos + 1, ypos + 1,g);
			btm = index2(xpos , ypos + 1,g);
			btml = index2(xpos - 1, ypos + 1,g);
			left = index2(xpos - 1, ypos ,g);

			if(topl!=null){
				n.add(topl);
				Neighbours_a[0] = topl;
				kernel1d[0] = topl;
				n3.add(topl);
			}else{
				n3.add(null);
			}
			if(top!=null){
				n.add(top);
				n2.add(top);
				Neighbours_a[1] = top;
				Neighbours_b[0] = top;
				kernel1d[1] = top;
				n3.add(top);
			}else{
				n3.add(null);
				//Walls_b.set(0,null);
			}
			if(topr!=null){
				n.add(topr);
				Neighbours_a[2] = topr;
				kernel1d[2] = topr;
				n3.add(topr);
			}else{
				n3.add(null);
			}
			if(right!=null){
				n.add(right);
				n2.add(right);
				Neighbours_a[3] = right;
				Neighbours_b[1] = right;
				kernel1d[3] = right;
				n3.add(right);
			}else{
				n3.add(null);
				//Walls_b.set(1,null);
			}
			if(btmr!=null){
				n.add(btmr);
				Neighbours_a[4] = btmr;
				kernel1d[5] = btmr;
				n3.add(btmr);
			}else{
				n3.add(null);
			}
			if(btm!=null){
				n.add(btm);
				n2.add(btm);
				Neighbours_a[5] = btm;
				Neighbours_b[2] = btm;
				kernel1d[6] = btm;
				n3.add(btm);
			}else{
				n3.add(null);
				//Walls_b.set(2,null);
			}
			if(btml!=null){
				n.add(btml);
				Neighbours_a[6] = btml;
				kernel1d[7] = btml;
				n3.add(btml);
			}else{
				n3.add(null);
			}
			if(left!=null){
				n.add(left);
				n2.add(left);
				Neighbours_a[7] = left;
				Neighbours_b[3] = left;
				kernel1d[8] = left;
				n3.add(left);
			}else{
				n3.add(null);
				//Walls_b.set(3,null);
			}


			Neighbours = n;
			Neighbours2 = n2;
			Neighbours3 = n3;

			neighboured = true;
		}
	};

	public void getNeighbours4(int n){

		for(int i=ypos -n;i<ypos +n;i++){
			for(int j=xpos -n;j<xpos +n;j++){

				Grid_space g = null;
				int p = j + i * (2 * n +1);

				if(j==xpos||j<parent.cols||j<parent.cols)j++;
				if(i==ypos||i<parent.cols||j>parent.cols)i++;
				if(j!=xpos&&i!=ypos){
					g = parent.squares.get(p);

					if(g.col==col);
				}

			}}
		neighboured = true;
	};

	public void getNeighbours4(Grid a){

		if(!neighboured){
			// applet.fill(255);
			// applet.text(Neighbours_b.length,x,y+h/2);
			for(int i= ypos-1;i<ypos+2;i++){

				for(int j= xpos-1;j<xpos+2;j++){

					Grid_space g = null;
					int p = (j) + (i) * a.cols;

					if( p>=0&&p<a.squares.size()
							&&j>=0&&j<a.cols
							&&i>=0&&i<a.rows){
						g = a.squares.get(p);
						Neighbours_8.add(g);
					}
					else Neighbours_8.add(null);
					//count ++;
				}}
			//println(Neighbours_8.size());

			for(int i=1;i<Neighbours_8.size();i+=2){

				Grid_space g = Neighbours_8.get(i);
				//Neighbours_a[i] = g;

				//if(Neighbours_4.size()<=4&&g==null)
				Neighbours_4.add(g);
				Neighbours_b[applet.floor(i/2)] = g;
			}
			//println(Neighbours_4.size());
			neighboured = true;
		}

	};

	Grid_space index2(int xpos,int ypos,Grid a){

		Grid_space b = null;
		if(a.toggle){
			if(xpos<0||xpos>a.cols-1||ypos<0||ypos>a.rows-1) return null;
			else b = a.squares.get(xpos + (ypos * (a.cols)));
		}else{
			if(xpos<0||xpos>a.cols-1||ypos<0||ypos>a.rows-1)return null;
			else b = a.squares.get(xpos + (ypos * (a.cols)));
		}
		return b;
	};

	Grid_space index2(int xpos,int ypos,GridImg a){
		Grid_space b = null;
		if(a.toggle){
			if(xpos<0||xpos>a.img.width-1||ypos<0||ypos>a.img.height-1)return null;
			else b = a.imsquares.get(xpos + (ypos * (a.img.width)));
		}else{
			if(xpos<0||xpos>a.cols-1||ypos<0||ypos>a.rows-1)return null;
			else b = a.imsquares.get(xpos + (ypos * (a.cols)));
		}
		return b;
	};
};
