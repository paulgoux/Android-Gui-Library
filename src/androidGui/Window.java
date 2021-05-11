package androidGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class Window extends Scene{
	BMScontrols Bms;
	PApplet applet;
	public float bw,bh, navheight = 50,deltay,transparency = 200,transparency1 = 50,transparency2 = 80,
			transparency3 = 100,transparency4 = 150,r1,r2,r3,r4;
	public String label,itemLabel;
	public int type = 0,index = -1,click,windex = -1,level = 0,wid,counter;
	public int main;
	public int navcol,quickNavcol,fcol,bcol,xcol,quickNavItemcol,navItemcol,buttoncol,selectcol;
	public boolean drag,resize,hover,border,backgapplet,round,hidden,fill = true,debug,navigation = true;
	Button child;
	Menu parent,select;

	public float bsize = 12,tsize = 12,svalue,svalue2,colwidth = 100,rowheight = 80;;

	public boolean smdown,ddown,amendslider,initB,launchable = true,rapidAccess,navtoggle = true,transparent;
	public boolean show = true,open,close,visible=true;
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Button> quickNav = new ArrayList<Button>();
	public ArrayList<Button> buttonGrid = new ArrayList<Button>();
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<Window> windows = new ArrayList<Window>();
	public ArrayList<PVector> track = new ArrayList<PVector>();
	public ArrayList<String> Links = new ArrayList<String>();
	//sliderBox sliders;
	String []list;
	public String link,currentp,back,forward,currentl,currentf;
	float [] window;
	int wcol;
	TextBox fileName,fileDir;

	Window(){

	};
	
	

	public Window(PApplet bms){
		applet = bms;
	};

	public Window(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

	};

	public Window(float X,float Y,float WW,float HH){

		x = X;
		y = Y;
		w = WW;
		h = HH;

		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		fill = true;
	};

	public Window(float X,float Y,float WW,float HH,BMScontrols bms){

		x = X;
		y = Y;
		w = WW;
		h = HH;

		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		Bms = bms;
		applet = bms.applet;
		fill = true;
	};
	
	public Window(float X,float Y,float WW,float HH,Boundary b){

		x = X;
		y = Y;
		w = WW;
		h = HH;
		b.x = x;
		b.y = y;
		b.h = h;
		b.w = w;
		

		x1 = x;
		y1 = y;
		x2 = x + w;
		y2 = y + h;
		Bms = b.Bms;
		applet = b.applet;
		Boundaries.add(b);
		fill = true;
		init(Bms);
//		applet.println("Window const b",b.x,b.y,b.h,b.w,applet);
	};

	public Window(float xx, float yy, float ww,float hh,String dir,BMScontrols bms){

		x = xx;
		y = yy;
		w = 500;
		h = hh;
		bw = w;
		bh = h;
//		
		
		
		
		Bms = bms;
		applet = bms.applet;
//		if(dir!=null&&Bms.absolutePath!=null&&dir!=Bms.absolutePath)dir = dir.replace(Bms.absolutePath+"//", "");
//		applet.println("window const 1 dir",dir,bms.absolutePath);
		list = fileUtils.listNames2(dir);
		link = dir;
		currentp = dir;
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();

		if(list!=null)
			temp = fileUtils.trimNames(list);
		list = null;
		for(int i =0;i<temp.size();i++){

			String l = temp.get(i);
			String ndir =  l +"/";
//			applet.println("window const 1 dir",ndir,"name",l);
			list = fileUtils.listNames2(ndir);
			Button a = new Button( x ,y + 20 *(i), w-10,20,l,bms);
			if(list==null){

//				applet.println("window const 1 no info found");

			}else {
				temp2 = fileUtils.trimNames(list);
				if(temp2.size()>0){
					a.submenu = new Menu(a.x+20,a.y+a.h,a.w-20,temp2,bms);
					a.submenu.setBms(Bms);
					a.submenu.slide = true;
					a.submenu.visible = false;
				}else applet.println("window const 1 trim failed");
			}
			a.border = false;
			a.setToggleBox();
			a.tyoff = 50;
			buttons.add(a);

		}

		parent = new Menu( x + w - 10,y , 10,h-20,bms);
		parent.setBms(Bms);
		Slider s = new Slider( x + w - 10,y , 10,h,bms);
		s.setClassicBar();
		s.tvisible = false;
		s.vertical = true;
	    s.tooltip = null;
			   
		sliders.add(s);
		Boundary b = new Boundary(x,y-10,w,h+10,4,bms);
		Boundaries.add(b);

		for(int i =0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			Button a = new Button(0,0,40,40,b1.label,bms);
			a.textbtm = true;
			a.tyoff = 25;
			a.txoff = -10;
			a.tcol = 0;
			a.border = false;
			buttonGrid.add(a);
		}
		cols = 5;
		rows = PApplet.round(buttonGrid.size()/cols)+1;

		b = new Boundary(x,y-50,cols*colwidth,5*rowheight + navheight,4,bms);
		Boundaries.add(b);
		windows.add(this);

		Button b2 = new Button(x+w - 80,y - 40 + 10, 30,30,"Back",bms);
		Button f = new Button(x+w - 80,y - 40 + 10, 30,30,"Forward",bms);
		Button X = new Button(x + colwidth*cols - 35,y-50 + 5, 30,30,"X",bms);

		X.txoff = 7;
		b2.border = false;
		f.border = false;
		X.border = false;
		//X.textbtm = true;
		nav.add(b2);
		nav.add(f);
		nav.add(X);

		for(int i=0;i<nav.size()-1;i++){
			Button b1 = nav.get(i);
			b1.x = x + 5 + 40 * i;
			b1.y = y - 50 + 5 + dy;
			b1.tyoff = 20;
			b1.txoff = -10;
		}

		initqNav();
//		h = sliders.get(0).h;
		if(debug)applet.println("window",this);
	};

	public Window(float xx, float yy, float ww,float hh,String dir,int n,BMScontrols bms){

		x = xx;
		y = yy;
		w = ww;
		h = hh;
		bw = w;
		bh = h;
		Bms = bms;
		applet = bms.applet;
//		if(dir!=null&&Bms.absolutePath!=null&&dir!=Bms.absolutePath)dir = dir.replace(Bms.absolutePath+"//", "");
		list = fileUtils.listNames2(dir);
		link = dir;
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();


		if(list!=null)temp = fileUtils.trimNames(list);
		//h = temp.size();
		int j = 0;
		int k = PApplet.floor(temp.size()/n);
		for(int i =0;i<temp.size();i++){

			String l = temp.get(i);
			String ndir = l + "/";
//			applet.println("window const 2 dir",ndir,"name",l);
			list = fileUtils.listNames2(ndir);
			if(list==null){
//				applet.println("window const 2 no info found");
				Button a = new Button( x + w*j,y + 20 *(i)-k*20*(j), w-10,20,l);
				a.Bms = Bms;
				a.applet = Bms.applet;
				a.submenu = new Menu(x+20,y,w-20,20);
				a.submenu.setBms(Bms);
				a.submenu.borders(false);
				if(j>0) {
					a = new Button( x + w*j,y + 20 *(i)-k*20*(j)-40, w-10,20,l);
					a.Bms = Bms;
					a.applet = Bms.applet;
				}
				a.border = false;
				a.classicBar = true;
				buttons.add(a);
				if(i>k*(j+1))j++;
			}else {

				Button a = new Button( x + w*j,y + 20 *(i)-k*20*(j), w-10,20,l);
				a.Bms = Bms;
				a.applet = Bms.applet;
				a.submenu = new Menu(x+20,y,w-20,list);
				a.submenu.setBms(Bms);
				a.submenu.visible = false;
				a.submenu.slide = true;
				a.submenu.borders(false);
				if(j>0) a = new Button( x + w*j,y + 20 *(i)-k*20*(j)-40, w-10,20,l);
				a.border = false;
				a.classicBar = true;
				buttons.add(a);
				if(i>k*(j+1))j++;
			}}
		//h = temp.size()*20;
		parent = new Menu( x + w*n - 10,y, 10,h-20);
		parent.setBms(Bms);
		Slider s = new Slider( x + w*n - 10,y, 10,h);
		s.setClassicBar();
		s.tvisible = false;
		s.vertical = true;
	    s.tooltip = null;

		sliders.add(s);
		w = w*n;
		type = 1;
		Boundary b = new Boundary(x,y-10,w,h+20,4,bms);
		//b.id = -1;
		Boundaries.add(b);

		for(int i =0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			Button a = new Button(0,0,40,40,b1.label);
			s.Bms = Bms;
			s.applet = Bms.applet;
			a.tcol = 0;
			a.textbtm = true;
			a.border = false;
			buttonGrid.add(a);
		}
		cols = 5;
		rows = PApplet.round(buttonGrid.size()/cols);
		windows.add(this);

		Button b2 = new Button(x+w - 80,y - 40 + 10, 30,30,"Back",bms);
		Button f = new Button(x+w - 80,y - 40 + 10, 30,30,"Forward",bms);
		Button X = new Button(x + colwidth*cols - 35,y-50 + 5, 30,30,"X",bms);
		b2.border = false;
		f.border = false;
		X.border = false;
		nav.add(b2);
		nav.add(f);
		nav.add(X);

		for(int i=0;i<nav.size()-1;i++){
			Button b1 = nav.get(i);
			b1.x = x + 5 + 40 * i;
			b1.y = y - 50 + 5 + dy;
			b1.tyoff = 25;
			b1.txoff = -10;
		}

		initqNav();
//		h = sliders.get(0).h;
	};

	public void initNav(){

	};

	public void initqNav(){
		Button n = new Button(x - 35,y,25,25,"My Computer",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.Bms = Bms;
		n = new Button(x - 35,y,25,25,"Data Folder",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		n = new Button(x - 35,y + 50,25,25,"Documents",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		n = new Button(x - 35,y + 100,25,25,"Downloads",Bms);
		n.tyoff = 20;
		n.txoff = -10;
		n.textbtm = true;
		n.border = false;
		n.tcol = 0;
		quickNav.add(n);
		fileName = new TextBox(0,0,370,20,100,"File name...",Bms);
		fileDir = new TextBox(0,0,370,20,100,Bms);
		String [] s1 = {"Open","Cancel"};
		select = new Menu(0,0,90,20,10,s1,Bms);
	};


	public void render(){
		if(toggle){
			applet.stroke(0);
//			applet.println("window renderp 00");
//			if(!border)applet.noStroke();
//			applet.noFill();
//			applet.fill(Bms.tabcol);
//			applet.rect(x,y,w,h);
//			display();
			draw();
//			applet.println("window renderp 01");
			functions();
//			applet.println("window renderp 02");
//			applet.fill(255);
//			for(int i=0;i<scenes.size();i++){
//				scenes.get(i).display();
//			}
		}
//		sLogic();
	};
	
	public void renderPublic(){
			applet.stroke(0);
			if(!border)applet.noStroke();
			applet.noFill();
			applet.stroke(1);
			applet.strokeWeight(1);
//			applet.fill(Bms.tabcol);
			applet.rect(x,y,w,h);
//			display();
			draw();
			functions();
			applet.fill(255);
			for(int i=0;i<scenes.size();i++){
				scenes.get(i).display();
			}
//		sLogic();
	};

	public void sLogic(){

		if(applet.mousePressed&&pos()){
			if(Bms.currentMouseObject==null)Bms.currentMouseObject = this.itemLabel;
		}

	};

	public void display(){
//		if(sliders.size()>0)h = sliders.get(0).h;
		if(toggle||show){
			boundingbox();
			submenu();
			menu();
			scrollbar();
			wlogic();

			if(Boundaries.size()>0)Boundaries.get(0).draw2();
		}
	};

	public void boundingbox(){
		applet.noStroke();
		if(!transparent)applet.fill(Bms.tabcol);
		else applet.fill(Bms.tabcol,transparency);
		applet.rect(x,y,w,h,r1,r2,r3,r4);
		applet.rect(x,y-10,w,10);

		applet.fill(0,transparency4);
		applet.rect(x,y,w,h,r1,r2,r3,r4);
		applet.fill(0,transparency1);
		applet.rect(x,y-10,w,10);
	};

	public void menu(){

		if(sliders.size()>0){
			Slider s = sliders.get(0);

			//s.value = map(s.valuex,0,s.h,0,buttons.get(index).submenu.items.size()*20);

			for(int i=0;i<buttons.size();i++){

				Button b = buttons.get(i);

				if(b.y>y+h)b.visible = false;
				else b.visible = true;

				if(index<0)svalue = s.value;
				if(index>=i||index==-1)b.y = b.by - s.valuex;
				else if(index>=0&&i>index)b.y = b.by - s.valuex + buttons.get(index).submenu.items.size()*20;

				b.x = x;
				b.by = y+10 + b.h*i;
				if(b.y+b.h<=y+h&&b.y>=y){
					if(b.pos())b.toggle();
					b.draw();
					applet.fill(255);
					applet.textSize(b.bsize);
					if(b.submenu!=null)applet.text(">",b.x+b.w-10,b.y+20);
					applet.textSize(12);
					if(b.submenu!=null&&b.subpos()&&applet.mousePressed&&!smdown){
						smdown = true;
						amendslider = false;
						index = i;
					}
					if(applet.mousePressed&&!b.pos())b.toggle=false;
					b.highlight();
				}
			}
		}
	};

	public void submenu(){
		if(index>-1){
			Button a = buttons.get(index);
			Menu m = a.submenu;
			//w = bw + 20;
			m.y = a.y+a.h + (dy);

			for(int j=0;j<m.items.size();j++){
				Button b = m.items.get(j);
				b.x = x + 20;
				if(b.y+b.h>y+h||b.y<y){
					b.visible=false;
					float dy2 = PApplet.abs(y - by);

					applet.noStroke();
					if(b.border)applet.strokeWeight(1);
					applet.fill(255);
					applet.rect(b.x,y,b.w,dy2,r1,r2,r3,r4);
					applet.fill(0,150);
					applet.rect(b.x,y,b.w,dy2,r1,r2,r3,r4);
				}
				//b.draw();
				else b.visible = true;

			}
			m.draw();
		}
	};

	public void scrollbar(){
		if(sliders.size()>0){
			Slider s = sliders.get(0);

			s.x = x+w-10;
			s.y = y;
			s.draw();
			//			s.mouseFunctions();
			s = sliders.get(0);
			s.r1 = 0;
			s.r2 = 0;
			s.r3 = 0;
			s.r4 = 0;

		}
	};

	public void scrollbar(PGraphics canvas){
		if(sliders.size()>0){
			Slider s = sliders.get(0);

			s.x = x+w-10;
			s.y = y;
			//s.draw(mouse);
			//s.mouseFunctions(mouse);
		}
	};

	public void wlogic(){

		float my = applet.mouseY;
		if(click>2)click=0;
		ArrayList<Float> temp = new ArrayList<Float>();
		float dy = 0;

		if(sliders.size()>0){
			Slider s = sliders.get(0);
			if(type==0)s.set(0,buttons.size()-h/20);
			else s.set(0,buttons.size()/3-h/20);
			if(index>-1&&buttons.get(index).pos()&&applet.mousePressed&&!smdown){index=-1;smdown = true;}
			if(pos()&&!s.mdown&&applet.mousePressed)smdown = true;
			if(!applet.mousePressed){smdown = false;ddown=false;}
			if(!pos()&&applet.mousePressed&&!s.mdown){index = -1;smdown = false;}
			if(dpos()&&applet.mousePressed&&!smdown&&!s.mdown){
				ddown = true;
			}

			if(ddown){

				Boundary b = Boundaries.get(0);
				b.mtranslate(b,new PVector(applet.mouseX,applet.mouseY));

				x = b.Boundaries.get(0).x1;
				y = b.Boundaries.get(0).y1+10;

			}

			if(index>-1&&!amendslider){
				if(!amendslider&&!s.pos()&&!applet.mousePressed){
					s.value = (index)*20;
					s.valuex = applet.map(index,0,buttons.size(),0,h);

				}

				if(s.pos()&&applet.mousePressed){
					temp.add(my);
					amendslider = true;
					dy = applet.mouseY - temp.get(0);
				}}
		}
	};

	public void displayGrid(){
//		
		h = (7+1) * 50;
		if(sliders.get(0)!=null)sliders.get(0).h = h;
		if(close){
			toggle = false;
			open = false;
			close = false;
		}
		if(toggle){
			close = false;
			open = true;
			toggle = false;
		}
		//else open = false;

		if(open&&show){
			Window w2 = windows.get(windows.size()-1);
			float ay = 50;
			if(w2!=null) {
				fileDir.textBox = currentp.replace("//", "/");
			}
			//else fileDir.textBox = "";
			select.setPos(x+w-100,y+h-ay);
			fileName.setPos(x+20,y+h-ay);
			fileDir.setPos(x +90,y-ay+10);
			drawBorder();
			drawNav();
			grid();
			drawRapidOptions();
			select.draw();
			select.toggleAll();
			select.toggle(0,this,"close");
			select.toggle(1,this,"close");
			fileName.draw();
			fileDir.draw();
			drawScrollbar();
			gridlogic();
		}

	};

	public void drawRapidOptions(){
		applet.noStroke();

		if(rapidAccess&&navtoggle){

			for(int i=0;i<quickNav.size();i++){
				Button b = quickNav.get(i);
				b.x = x - 70;
				b.y = y + 20 + 50 * i;
				//fill(0);
				b.draw();
				//b.setLabelOff();
				applet.fill(0);
				//	        applet.text(b.label,b.x-20,b.h+b.y+20);
				b.highlight();
			}
		}
	};

	public void drawScrollbar(){

		Slider s = sliders.get(0);

		s.setPos(x+cols*colwidth-10, y);
		s.set(0,rowheight*8);
		s.draw();
		s.mouseFunctions();
	};

	public void gridlogic(){

		ArrayList<Float> temp = new ArrayList<Float>();

		float my = applet.mouseY;
		Button X = nav.get(2);
		if(dposg()&&applet.mousePressed&&!navPos()&&track.size()==0&&!sliders.get(0).mdown){
			// Bms.currentObject==this;
			// Bms.currentMouseObject==currentf;
			// fill(255,50);
			// rect(0,0,width,height);
			ddown = true;
		}
		if(pos()&&applet.mousePressed&&!dposg()&&!ddown)smdown = true;

		if(dposg()&&applet.mousePressed&&!nav.get(0).pos()&&!nav.get(1).pos()&&!sliders.get(0).mdown&&!smdown&&!Bms.globalDown&&!X.pos())ddown = true;

		if(sliders.size()>0&&sliders.get(0)!=null){
			if(!ddown&&pos()&&applet.mousePressed&&!dposg()&&!sliders.get(0).pos()&&!mdown&&!ddown&&!sliders.get(0).mdown){
				if(track.size()<2)track.add(new PVector(applet.mouseX,applet.mouseY));
				mdown = true;
			}}
		else {
			if(applet.mousePressed&&!dposg()&&!sliders.get(0).pos()&&!mdown&&!ddown){
				if(track.size()<2)track.add(new PVector(applet.mouseX,applet.mouseY));
				mdown = true;

			}}


		if(!applet.mousePressed){
			smdown = false;
			ddown = false;
			mdown = false;
			track = new ArrayList<PVector>();
			ddown = false;
		}

		//text(track.size(),100,100);
		if(track.size()>0){
			//noFill();
			applet.fill(0,20);
			applet.stroke(0,50);
			applet.strokeWeight(1);
			applet.rect(track.get(0).x,track.get(0).y,(applet.mouseX - track.get(0).x ),(applet.mouseY - track.get(0).y));
			applet.noStroke();
		}


		if(ddown){

		}
		Boundary b = Boundaries.get(1);
		//	    
		if(!mdown&&!ddown&&!sliders.get(0).mdown)X.latch(this,"close");


		//		
		if(ddown){

			b.mtranslate(new PVector(applet.mouseX,applet.mouseY));
			x = b.Boundaries.get(0).x1;
			y = b.Boundaries.get(0).y1+50;

			X.y = y-50 + 5 + dy;
			X.x = x + colwidth*cols - 35;

			for(int i=0;i<nav.size()-1;i++){
				Button b1 = nav.get(i);

				b1.x = x + 5 + 40 * i;
				b1.y = y - 50 + 5 + dy;
			}
		}else{
			b.mdown = false;
		}
		//	    
		//		Boundaries.get(1).draw3();
		//	    
		if(sliders.get(0).pos()&&applet.mousePressed){
			temp.add((float) (applet.mouseY));
			deltay = applet.mouseY - temp.get(0);
		}

		rows = PApplet.round(windows.get(windows.size()-1).buttons.size()/5)+1;
	};

	public void drawBorder(){
		applet.stroke(0);
		if(!border)applet.noStroke();
		if(rapidAccess&&navtoggle){

			applet.fill(255);
			applet.rect(x - 80,y-navheight,colwidth*cols+80,h+navheight,r1,r2,r3,r4);
			if(!transparent)applet.fill(Bms.hcol);
			else applet.fill(fcol,transparency);
			applet.rect(x - 80,y-navheight,colwidth*cols+80,h+navheight,r1,r2,r3,r4);

		}
		else if(navtoggle){
			applet.fill(255);
			applet.rect(x,y-navheight,colwidth*cols,h+navheight,r1,r2,r3,r4);
			if(!transparent)applet.fill(Bms.hcol);
			else applet.fill(fcol,transparency);
			applet.rect(x,y-navheight,colwidth*cols,h+navheight,r1,r2,r3,r4);
		}
		else if(rapidAccess) {
			applet.fill(255);
			applet.rect(x-80,y,colwidth*cols+80,h,r1,r2,r3,r4);
			if(!transparent)applet.fill(Bms.hcol);
			else applet.fill(fcol,transparency);
			applet.rect(x-80,y,colwidth*cols+80,h,r1,r2,r3,r4);
		}
		applet.fill(255);
		applet.rect(x,y,colwidth*cols,h,r1,r2,r3,r4);
		if(!transparent)applet.fill(Bms.tabcol);
		else applet.fill(fcol,transparency);
		applet.rect(x,y,colwidth*cols,h,r1,r2,r3,r4);
	};

	public void drawNav(){

		for(int i=0;i<nav.size();i++){
			Button b = nav.get(i);

			b.textbtm = true;

			b.draw();
			b.highlight();

			if(b.pos()&&applet.mousePressed&&b.label=="Back"&&!smdown&&track.size()<1){
				smdown = true;
				if(windows.size()>1){
					windows.remove(windows.size()-1);
					currentp = windows.get(windows.size()-1).link;
					sliders.get(0).value = 0;sliders.get(0).valuey = 0;
					if(fileDir!=null) {
						fileDir.textBox = "";
						fileDir.textbox = new ArrayList<Letter>();
					}
				}
			}
			if(b.pos()&&applet.mousePressed&&b.label=="Forward"&&!smdown&&forward!=null&&track.size()<1){
				smdown = true;
				
				Window w1 = new Window(x,y,w,h,forward,Bms);
				windows.add(w1);
				currentp = windows.get(windows.size()-1).link;
				if(fileDir!=null) {
					fileDir.textBox = "";
					fileDir.textbox = new ArrayList<Letter>();
				}
			}
		}
		if(!applet.mousePressed)smdown = false;
	};

	public void grid(){

		Window w2 = windows.get(windows.size()-1);
		Slider s = sliders.get(0);

		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){

				int pos = j+i*cols;
				Button b = null;
				if(pos<w2.buttonGrid.size()){
					b = w2.buttonGrid.get(pos);
					Button b1 = w2.buttons.get(pos);
					b1.border = false;
					b.textbtm = true;
//					b.x = ;
//					b.y = ;
					b.setPos(x + 10 + colwidth * j, y + 10 + (rowheight+b.tsize) * i - s.value);
					b.highlight3();
					if(b.y<y||b.y+b.h + b.tsize>y + rowheight * 4)b.visible = false;
					else b.visible = true;
					if(!b.textcheck)b.labelcheck(colwidth);

					if(b.visible)b.draw();
					applet.fill(0);
					if(b.pos())b.highlight();

					applet.fill(0); 
					if(b.pos()&&b.submenu!=null&&track.size()<1){
						int size = currentp.length();
//						if(currentp.charAt(size-1)!='/')currentp += "/";
						currentl = currentp + b.blabel.substring(b.blabel.lastIndexOf("/"),b.blabel.length());;
					}
					//else if(b.pos()&&b.submenu==null){
					//  int size = currentp.length();
					//  if(currentp.charAt(size-1)!='/')currentp += "/";
					//   currentf = currentp + b.blabel;
					//}
					if(b.pos()&&applet.mousePressed&&b1.submenu!=null&&!smdown&&track.size()<1){
						int size = currentp.length();
						if(currentp.charAt(size-1)!='/')currentp += "/";
						
						back = currentp;
						currentp += b.blabel.substring(b.blabel.lastIndexOf("/"),b.blabel.length());
//						applet.println("currentp",currentp);
//						applet.println("label",b.blabel);
						forward = currentp;
						Window w1 = new Window(w2.x+200,w2.y,w,h,currentp,Bms);
						windows.add(w1);
						windex = 0;
						s.value = 0;
						s.valuex = 0;
						wid = windows.size()-1;
						smdown = true;
					}else if(b.pos()&&applet.mousePressed&&b1.submenu==null&&!smdown&&track.size()<1){
						int size = currentp.length();
//						if(currentp.charAt(size-1)!='/')currentp += "/";
						String file = currentp + b.blabel.substring(b.blabel.lastIndexOf("/"),b.blabel.length());;
						currentf = file;
						//if(launchable)launch(file);
						smdown = true;
					}
				}}}
		if(!applet.mousePressed)smdown = false;
	};

	public void navbar(){

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

		for(int i=0;i<buttonGrid.size();i++){
			Button b = buttonGrid.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<quickNav.size();i++){
			Button b = quickNav.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<nav.size();i++){
			Button b = nav.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b = sliders.get(i);
			b.r1 = a;
			b.r2 = a;
			b.r3 = a;
			b.r4 = a;
		}

		select.setRadius(a);
		fileName.setRadius(a);
		fileDir.setRadius(a);
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
		for(int i=0;i<buttons.size();i++){
			Button b1 = buttons.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<buttonGrid.size();i++){
			Button b1 = buttonGrid.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<quickNav.size();i++){
			Button b1 = quickNav.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<nav.size();i++){
			Button b1 = nav.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		for(int i=0;i<sliders.size();i++){
			Slider b1 = sliders.get(i);
			b1.r1 = a;
			b1.r2 = b;
			b1.r3 = c;
			b1.r4 = d;
		}

		select.setRadius(a,b,c,d);
		fileName.setRadius(a,b,c,d);
		fileDir.setRadius(a,b,c,d);
	};

	boolean navPos(){
		boolean k = false;
		if(nav!=null){
			for(int i=0;i<nav.size();i++){
				if(nav.get(i).pos()){
					k = true;
					break;
				}
			}
		}
		return k;
	};

	boolean dpos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-10&&applet.mouseY<y;

	};

	boolean dposg(){
		return applet.mouseX>x&&applet.mouseX<x+cols*colwidth&&applet.mouseY>y-50&&applet.mouseY<y;

	};

public boolean pos(){

		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;

	};

	public void setBorder(Boolean b) {
		border = b;
	};

	void windowLogic() {
		if(!mdown&&!ddown&&!sliders.get(0).mdown&&applet.mousePressed) {
//			PApplet.println("window logic 00");
		}
//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
			select.getButton(0).click(this,"close");
//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
			select.getButton(1).click(this,"close");
	};
	
	void windowLogic(PVector m) {
		if(!mdown&&!ddown&&!sliders.get(0).mdown&&applet.mousePressed) {
			PApplet.println("window logic 00");
		}
//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
			select.getButton(0).click(this,"close");
//		if(!mdown&&!ddown&&!sliders.get(0).mdown)
			select.getButton(1).click(this,"close");
	};

};
