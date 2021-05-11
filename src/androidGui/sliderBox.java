package androidGui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class sliderBox{
	BMScontrols Bms;
	PApplet applet;
	float x,y,bx = x,by = y,w,bw = w,h,bh = h,vspacing,hspacing,r1,r2,r3,r4,rx,transparency,ghSpacing,gvSpacing;
	public int id,cols,rows;
	Slider sliderR,sliderB,sliderG,sliderSelected;
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	String type;
	public PVector mouse,mouse2;
	public boolean vertical, horizontal = true,draggable,saved,tdown,mdown,parentCanvas,t2down,m3down,m4down,
			border;
	public boolean visible = true,localTheme,grid;
	public Menu menu;
	public Menu tooltip;
	public tab parentTab;
	//fileInput load = new fileInput();
	//fileReader read = new fileReader();
	public fileOutput save;
	public String savePath = null,itemLabel,label;
	public int col, bcol, tcol, fcol, hcol,toggleCol;


	

	public sliderBox(float xx, float yy,float ww,float hh,float S,String [] Labels,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = (hh + S) * Labels.length - S;

		vspacing = S;

		menu = new Menu(x,y,w,h,bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;
		menu.show = true;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (hh + S) *i;
			Slider a =  new Slider(x ,ypos,w,hh,Labels[i],bms);
			a.initColors();
			a.id = i;
			sliders.add(a);
			menu.sliders.add(a);
		}

		createTooltip();

	};

	public sliderBox(float xx, float yy,float ww,float hh,float S,String [] Labels,float[]values,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = (hh + S) * Labels.length - S;

		vspacing = S;

		menu = new Menu(x,y,w,h,bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;
		menu.show = true;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (hh + S) *i;
			Slider a =  new Slider(x ,ypos,w,hh,Labels[i],bms);
			a.initColors();
			a.id = i;
			if(i<values.length)a.value = values[i];
			else applet.println("check values array");
			sliders.add(a);
			menu.sliders.add(a);
		}

		createTooltip();

	};


	public sliderBox(float xx, float yy,float ww,float S,String [] Labels,float [] values ,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		w = ww;
		h = (10 + S) * Labels.length - S;
		vspacing = S;
		bx = x;
		by = y;
		bw = w;
		bh = h;

		menu = new Menu(x,y,w,h,bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;
		menu.show = true;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (10 + S) *i;
			Slider a =  new Slider(x ,ypos,w,10,Labels[i],bms);
			a.id = i;
			a.initColors();
			a.bar = true;
			a.classic = true;
			a.parent = menu;
			a.valuex = a.w/2;
			sliders.add(a);
			menu.sliders.add(a);
		}

		for(int i=0;i<menu.sliders.size();i++){
			Slider a = getSlider(i);
			a.value = values[i];
			a.valuex = PApplet.map(a.value,0,255,0,a.w);
		}

		createTooltip();

	};

	public sliderBox(float xx, float yy,float ww,float S,String [] Labels,tab t){
		Bms = t.Bms;
		applet = Bms.applet;

		parentCanvas = true;
		x = xx;
		y = yy;
		w = ww;
		h = (10 + S) * Labels.length - S;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		vspacing = S;

		menu = new Menu(x,y,w,h,Bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (10 + S) *i;
			Slider a =  new Slider(x ,ypos,w,10,Labels[i],t.Bms);
			a.initColors();
			a.id = i;
			//	      a.tooltip.Width = (int) (t.w);
			//	      a.tooltip.Height = (int)(t.h);
			a.bar = true;
			a.classic = true;
			a.parent = menu;
			a.valuex = a.w/2;
			sliders.add(a);
			menu.sliders.add(a);
		}
		createTooltip();
	};

	public sliderBox(float xx, float yy,float ww,float S,String Label, String [] Labels,tab t){
		Bms = t.Bms;
		applet = Bms.applet;
		parentCanvas = true;
		x = xx;
		y = yy;
		w = ww;
		h = (10 + S) * Labels.length - S;
		bx = x;
		by = y;
		bw = w;
		bh = h; 

		vspacing = S;

		menu = new Menu(x,y,w,h,Label,Bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + 20 + (10 + S) *i;
			Slider a =  new Slider(x ,ypos,w,10,Labels[i],t.Bms);
			a.id = i;
			a.initColors();
			a.tooltip.Width = (int) (t.w);
			a.tooltip.Height = (int) (t.h);
			a.bar = true;
			a.classic = true;
			a.parent = menu;
			a.valuex = a.w/2;
			sliders.add(a);
			menu.sliders.add(a);
		}
		createTooltip();
	};

	public sliderBox(float xx, float yy,float ww,float S,Menu m,String [] Labels) {
		x = xx;
		y = yy;
		w = ww;
		h = (10 + S) * Labels.length - S;
		bx = x;
		by = y;
		bw = w;
		bh = h;

		vspacing = S;

		menu = m;
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (10 + S) *i;
			Slider a =  new Slider(x ,ypos,w,10,Labels[i]);
			a.initColors();
			a.id = i;
			a.bar = true;
			a.classic = true;
			a.parent = menu;
			a.valuex = a.w/2;
			sliders.add(a);
			menu.sliders.add(a);
		}
		createTooltip();
	};

	public sliderBox(float xx, float yy,float ww,float S,Menu m,String [] Labels,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		w = ww;
		h = (10 + S) * Labels.length - S;
		bx = x;
		by = y;
		bw = w;
		bh = h;

		vspacing = S;

		menu = m;
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = vspacing;
		menu.vertical = false;

		for(int i=0;i<Labels.length;i++){
			float ypos = y + (10 + S) *i;
			Slider a =  new Slider(x ,ypos,w,10,Labels[i],bms);
			a.initColors();
			a.id = i;
			a.bar = true;
			a.classic = true;
			a.parent = menu;
			a.valuex = a.w/2;
			sliders.add(a);
			menu.sliders.add(a);
		}
		createTooltip();
	};

	public sliderBox(float xx, float yy,float ww,float S,tab t){
		Bms = t.Bms;
		applet = Bms.applet;
		parentCanvas = true;
		vspacing = S;
		x = xx;
		y = yy;
		w = ww;
		bx = x;
		by = y;
		bw = w;

		menu = new Menu(x,y,w,h,t.Bms);
		menu.highlightable = false;
		menu.visible = false;
		menu.type = 2;
		menu.spacing = S;
		menu.vertical = false;
		menu.slide = false;
		menu.draggable = true;
		//Bms.add(menu);
		createTooltip();
	};

	public void createFileWriter() {

		save = new fileOutput(Bms);
	};

	public void createTooltip(){
		String []s1 = {"Reset All","Save","Load","Minimize"};
		tooltip = new Menu(x+w,y-30,40,20,s1,Bms);
		tooltip.setClassicBar();
		tooltip.items.get(3).toggle = true;
		tooltip.id = Bms.sliderBoxes.size()-1;
		tooltip.show = true;
		id = Bms.sliderBoxes.size()-1;
	};

	public void initColors() {

		col = Bms.fcol;
		bcol = Bms.bcol;
		fcol = Bms.fcol;
		hcol = Bms.hcol;
		toggleCol = Bms.toggleCol;

	};

	public void initAllColors() {

		col = Bms.fcol;
		bcol = Bms.bcol;
		fcol = Bms.fcol;
		hcol = Bms.hcol;
		toggleCol = Bms.toggleCol;

		for(int i=0;i<menu.sliders.size();i++) {
			Slider s = getSlider(i);
			s.initColors();
		}

	};

	PVector getMouse(PVector m,PVector n){

		return new PVector (m.x-n.x,m.y-n.y);
	};

	public void draw(){
		if(visible){
			//menu.sliderBoxP = this;
			//if(tooltip.items.get(3).toggle!=1)
			menu.setPos(x, y);
			menu.draw();
			if(!t2down&&tooltip!=null)drawToolTip();
			if(t2down&&tooltip!=null)tooltip.draw();
			logic();
			
		}
	};


	public void draw(PGraphics canvas){
		menu.mouse = mouse;
		
		menu.parentTab = parentTab;
		menu.setParentCanvas(canvas);
		menu.draw(canvas);
		menu.setPos(x, y);
		if(!t2down&&tooltip!=null)drawToolTip(canvas);
		if(tooltip!=null)drawToolTip(canvas);
		if(t2down&&tooltip!=null){
			tooltip.parentTab = parentTab;
			tooltip.setPos(x+w,y-30);
//			tooltip.mouse = new PVector(applet.mouseX-parentTab.x,applet.mouseY-parentTab.y);
//			tooltip.mouse = parentTab.getMouse(menu.mouse);
			tooltip.mouse = mouse;
			tooltip.setParentCanvas(canvas);
			tooltip.draw(canvas);
		}

		logic(canvas);
		
	};

	public void drawPie(){
		if(visible){
			//menu.sliderBoxP = this;
			//if(tooltip.items.get(3).toggle!=1)
			menu.draw();
			if(t2down&&tooltip!=null)
				tooltip.draw();
			logic();
			drawToolTip();
		}
	};

	public void logic(){
		if(tooltip!=null) {
			if(applet.mousePressed&&!m3down&&!pos()&&!tooltipPos()){
				m3down = true;
			}
			if(applet.mousePressed&&m3down&&(pos()||tooltipPos())){
				m4down = true;
			}
			if(applet.mousePressed&&m4down&&(!pos()&&!tooltipPos())){
				m4down = false;
			}
		}else {
			if(applet.mousePressed&&!m3down&&!pos()){
				m3down = true;
			}
			if(applet.mousePressed&&m3down&&(pos())){
				m4down = true;
			}
			if(applet.mousePressed&&m4down&&(!pos())){
				m4down = false;
			}

		}
		if(menu.draggable&&menu.drag){
			if(tooltip!=null){
				tooltip.x = menu.x + menu.w;
				tooltip.y = menu.y - 50;
			}

			for(int i=0;i<sliders.size();i++){

				Slider b = sliders.get(i);

				b.x = menu.x;
				b.y = menu.y + (b.h + vspacing) * i;
				if(tooltip!=null){
					b.tooltip.x = b.x+b.w+applet.textWidth("0.0000");
					b.tooltip.y = b.y;
				}
			}
			x = menu.x;
			y = menu.y;
		}else{
			menu.x = x;
			menu.y = y;
		}

		//if(!saved&&!Bms.autoControl){
		//  }
		if(applet.frameCount%Bms.autoSaveTimeout==0)saved = false;
		else saved = true;
		if(applet.mousePressed&&tooltip!=null&&tooltipPos()&&!t2down&&!mdown&&tooltip.visible){
			tdown = true;
			mdown = true;
		}
		if(applet.mousePressed&&tdown&&!mdown&&tooltip!=null&&!tooltipPos()&&!t2down&&tooltip.visible){
			tdown = false;
			mdown = true;
		}

		if(!applet.mousePressed)mdown = false;
		if(tooltip!=null&&!tooltipPos()&&!tooltip.pos())tdown = false;
		if(tooltip!=null&&tdown&&!applet.mousePressed&&!m4down&&tooltipPos())t2down = true;
		//if(tdown&&applet.mousePressed)t2down = true;

		boolean n = false;
		if(t2down){
			tdown = false;
			if(applet.mousePressed)mdown = true;
			if(tooltip!=null&&tooltip.items.get(0).pos()&&applet.mousePressed){
				mdown = true;
				for(int i=0;i<menu.sliders.size();i++){
					Slider s = getSlider(i);

					s.value = 0;
					s.valuex = s.w/2;

					if(s.Link!=null){
						Field field = null;
						try{
							field = s.Link.getClass().getField(s.control); 
							field.set(s.Link, 0); 
						}catch (NullPointerException e) {
						}catch (NoSuchFieldException e) {
						}catch (IllegalAccessException e) {
						} 
					}}t2down = false; }

			if(tooltip.items.get(1).pos()&&applet.mousePressed)save();

			//important
			//if(tooltip.items.get(2).pos())load.listen1();

			if(!applet.mousePressed &&mdown) t2down = false;

			tooltip.toggle(3,menu,"show");


		}
		if(applet.mousePressed&&!tooltipPos())t2down = false;

		if(!applet.mousePressed){
			m3down = false;
			if(!tooltipPos())m4down = false;
		}
	};

	public void logic(PGraphics canvas){
		canvas.fill(0);
		if(tooltip!=null){
			if(applet.mousePressed&&!m3down&&!pos(mouse)&&!tooltipPos2(mouse)){
				m3down = true;
			}
			if(applet.mousePressed&&m3down&&(pos(mouse)||tooltipPos2(mouse))){
				m4down = true;
			}
			if(applet.mousePressed&&m4down&&(!pos(mouse)&&!tooltipPos2(mouse))){
				m4down = false;
			}
		}else{
			if(applet.mousePressed&&!m3down&&!pos(mouse)){
				m3down = true;
			}
			if(applet.mousePressed&&m3down&&(pos(mouse))){
				m4down = true;
			}
			if(applet.mousePressed&&m4down&&(!pos(mouse))){
				m4down = false;
			}
		}

		if(menu.draggable&&menu.drag){
			if(tooltip!=null){
				tooltip.x = menu.x+menu.w;
				tooltip.y = menu.y - 50;
			}
			//menu.spacing = vspacing;
			for(int i=0;i<sliders.size();i++){

				Slider b = sliders.get(i);

				b.x = menu.x;
				b.y = menu.y + (b.h + vspacing) * i;
				if(tooltip!=null){
					b.tooltip.x = b.x+b.w+applet.textWidth("0.0000");
					b.tooltip.y = b.y;
				}

			}}

		if(applet.frameCount%Bms.autoSaveTimeout==0)saved = false;
		else saved = true;
		if(tooltip!=null&&applet.mousePressed&&!tdown&&!mdown&&tooltipPos2(mouse)&&!t2down&&!mdown&&tooltip.visible){
			tdown = true;
			mdown = true;
		}
		if(tooltip!=null&&applet.mousePressed&&tdown&&!mdown&&!tooltipPos2(mouse)&&!t2down&&tooltip.visible){
			tdown = false;
			mdown = true;
		}


		//	    if(!applet.mousePressed)mdown = false;
		//	    if(tooltip!=null&&!tooltipPos(mouse)&&!tooltip.pos(mouse))tdown = false;
		//	    if(tdown&&!applet.mousePressed&&!m4down&&tooltipPos(mouse))t2down = true;
		if(!applet.mousePressed)mdown = false;
		if(tooltip!=null&&!tooltipPos2(mouse))tdown = false;
		if(tooltip!=null&&tdown&&!applet.mousePressed&&!m4down&&tooltipPos2(mouse))t2down = true;

		if(t2down){
			tdown = false;
			if(applet.mousePressed)mdown = true;
			if(tooltip!=null&&tooltip.items.get(0).pos(mouse)&&applet.mousePressed){
				mdown = true;
				for(int i=0;i<menu.sliders.size();i++){
					Slider s = getSlider(i);

					s.value = 0;
					s.valuex = s.w/2;

					if(s.Link!=null){
						Field field = null;
						try{
							field = s.Link.getClass().getField(s.control); 
							field.set(s.Link, 0); 
						}catch (NullPointerException e) {
						}catch (NoSuchFieldException e) {
						}catch (IllegalAccessException e) {
						}}}
				t2down = false;
			}
			if(tooltip.items.get(1).pos(mouse)&&applet.mousePressed){
				save();
				t2down = false;
			}
			if(tooltip.items.get(2).pos(mouse)){
				//important
				//load.listen1();
				if(applet.mousePressed &&!mdown) t2down = false;
			}

			tooltip.toggle(3,menu,"show");
			//	          if(applet.mousePressed&&!tooltipPos(mouse))t2down = false;
		}
		//	        if(t2down&&tooltip!=null&&!tooltip.items.get(0).pos(mouse)&&applet.mousePressed)t2down = false;
		if(applet.mousePressed&&!tooltipPos(mouse))t2down = false;

		if(!applet.mousePressed){
			m3down = false;
			if(!tooltipPos(mouse))m4down = false;
		}
	};

	public void drawToolTip(PGraphics canvas){

		if(tooltip!=null&&tooltipPos2(mouse)&&tooltip.visible&&!t2down){
			canvas.noStroke();
			canvas.fill(Bms.fcol);
			if(localTheme)canvas.fill(fcol);
			canvas.rect(menu.x+menu.w,menu.y-50,50,50,r1,r2,r3,r4);
		}
	};

	public void drawToolTip(){
		if(!tdown&&tooltip!=null&&tooltipPos()&&!t2down&&tooltip.visible){
			applet.noStroke();
			applet.fill(Bms.fcol);
			if(localTheme)applet.fill(fcol);
			applet.rect(menu.x+menu.w,menu.y-50,50,50,r1,r2,r3,r4);
		}
	};

	public void add(Slider s,float spacing){

		vspacing = spacing;
		menu.spacing = spacing;
		s.valuex = w/2;
		menu.add(s,spacing);
		h = (s.h+spacing)*menu.sliders.size();

		menu.h = h;
	};

	public void add(Slider s){

		s.valuex = w/2;
		menu.add(s);
		h = (s.h+vspacing)*menu.sliders.size();

		menu.h = h;
	};
	
	public void set(int b, String a){

		if(b==menu.sindex)getSlider(b).label = a;
	};

	public void set(int a,int b, int c){
		getSlider(a).parent = this.menu;
		if(a==menu.sindex)getSlider(a).set(b,c);
	};
	
	public void set(int a,float b, float c){
		getSlider(a).parent = this.menu;
		if(a==menu.sindex)getSlider(a).set(b,c);
	};

	public void set(int i,float v){
		getSlider(i).parent = this.menu;
		getSlider(i).value = v;
	};

	public void save(){
		//important
		if(save.location!=null){
			save.checkLocation(save.location);
			save.open();
			for(int i=0;i<sliders.size();i++){

				Slider s = sliders.get(i);
				save.write((s.value));
			}
			save.close();
		}
	};

	public void setColor(){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);

			s.setint(0,255);
			s.value = PApplet.floor(s.value);
		}
	};

	public void load(){
		//important
		//for(int i=0;i<read.text.length;i++){
		//  String s = read.text[i];
		//  Slider s1 = sliders.get(i);
		//  if(i<sliders.size()) sliders.get(i).value = float(s);

		//}
	};

	public void display(String a){

		if(a=="vertical"|| a== "Vertical"){
			float k = 0;
			menu.vertical = true;
			k = menu.h;
			menu.h = menu.w ;
			h = w;
			menu.w = k;
			w = k;

			for(int i=0;i<sliders.size();i++){

				Slider b = sliders.get(i);

				//b.horizontal = false;
				k = b.w;
				b.w = b.h;
				b.h = k;
				k = b.btnw;
				b.btnw = b.btnh;
				b.btnh = k;
			}
		}

	};

	float get(int a){

		return getSlider(a).value;
	};

	int getint(int a){

		return PApplet.floor(getSlider(a).value);
	};

	public void set(int a, float b,float c,Object o, String variable){
		getSlider(a).parent = this.menu;
		getSlider(a).parentObject = o;
		getSlider(a).parentVar = variable;
		getSlider(a).functionId = 0;
		getSlider(a).startvalue = b;
		getSlider(a).endvalue = c;
		if(a==menu.sindex)getSlider(a).set(b,c,o,variable);
	};

	public void setPie(int a, float b,float c,Object o, String variable){
		getSlider(a).parent = this.menu;
		getSlider(a).parentObject = o;
		getSlider(a).parentVar = variable;
		getSlider(a).functionId = 0;
		getSlider(a).startvalue = b;
		getSlider(a).endvalue = c;
		if(a==menu.sindex)getSlider(a).setPie(b,c,o,variable);
	};

	public void setPieInt(int a, int b,int c,Object o, String variable){
		getSlider(a).parent = this.menu;
		getSlider(a).parentObject = o;
		getSlider(a).parentVar = variable;
		getSlider(a).functionId = 0;
		getSlider(a).startvalue = b;
		getSlider(a).endvalue = c;
		if(a==menu.sindex)getSlider(a).setPieInt(b,c,o,variable);
	};

	public void set(int a, float b,float c,Object o, String variable,String n){
		if(a==menu.sindex){
			getSlider(a).parent = this.menu;
			getSlider(a).parentObject = o;
			getSlider(a).parentVar = variable;
			getSlider(a).functionId = 1;
			getSlider(a).startvalue = b;
			getSlider(a).endvalue = c;
			getSlider(a).set(b,c,o,variable,n);
		}
	};

	public void setint(int a, int b,int c,Object o, String variable){
		if(a==menu.sindex){
			getSlider(a).parent = this.menu;
			getSlider(a).parentObject = o;
			getSlider(a).parentVar = variable;
			getSlider(a).functionId = 2;
			getSlider(a).startvalue = b;
			getSlider(a).endvalue = c;
			getSlider(a).setint(b,c,o,variable);
		}
	};

	public void setint(int a, int b,int c){
		if(a==menu.sindex){
			getSlider(a).parent = this.menu;
			getSlider(a).functionId = 3;
			getSlider(a).startvalue = b;
			getSlider(a).endvalue = c;
			getSlider(a).setint(b,c);
		}
	};

	public void setint(int a, int b,int c,Object o, String variable,String n){
		//if(visible)
		//getSlider(a).setint(b,c,o,variable,n);
	};

	public void set(int a, String b, String c){

		if(b == "Classic" || b == "classic"){
			if(c == "Bar" || c =="bar"){
				getSlider(a).classic = true;
				getSlider(a).bar = true;
				getSlider(a).radio = false;
				getSlider(a).square = false;
			}

			if(c == "Radio" || c =="radio"){
				getSlider(a).classic = true;
				getSlider(a).bar = false;
				getSlider(a).radio = true;
				getSlider(a).square = false;
			}

			if(c == "Square" || c =="square"){
				getSlider(a).classic = true;
				getSlider(a).bar = false;
				getSlider(a).radio = false;
				getSlider(a).square = true;
			}}

		//menus.remove(menu.id);
		//menu.add(
	};

	//public void draw2(){

	//  fill(255);
	//  text("hello",100,100);

	//};




	public void set(Window a, String b){

	};

	public void set(Menu a, String b){

	};

	public void set(Boundary a, String b){

	};


	public void setbg(){

	};

	public void setBar(){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.bar = true;
			s.radio = false;
			s.square = false;
			s.pie = false;
			s.matrix = false;
			s.classic = true;
		};
	};

	public void setSquare(){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.bar = false;
			s.radio = false;
			s.square = true;
			s.pie = false;
			s.matrix = false;
			s.classic = true;
			//println(s);
		};
	};

	public void setRadio(){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.bar = false;
			s.radio = true;
			s.square = false;
			s.pie = false;
			s.matrix = false;
			s.classic = true;
		};
	};

	public void setMatrix(){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.bar = false;
			s.radio = false;
			s.square = false;
			s.pie = false;
			s.matrix = true;
			s.classic = false;
		};
	};

	public void setPie(){

		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.bar = false;
			s.radio = false;
			s.square = true;
			s.pie = true;
			s.matrix = false;
			s.classic = false;
		}
	};

	public void setTooltipsOff() {
		tooltip = null;
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);
			s.tooltip = null;
		}
	};

	public void setDrag(Boolean a){
		menu.draggable = a;
	};

	boolean pos(){
		return applet.mouseX>menu.x&&applet.mouseX<menu.x+menu.w&&applet.mouseY>menu.y&&applet.mouseY<menu.y+menu.w;
	}

	boolean pos(PVector m){
		return m.x>menu.x&&m.x<menu.x+menu.w&&m.y>menu.y&&m.y<menu.y+menu.w;
	}

	boolean tooltipPos(){
		return applet.mouseX>menu.x+menu.w&&applet.mouseX<menu.x+menu.w+50&&applet.mouseY>menu.y-50&&applet.mouseY<menu.y;
	};

	boolean tooltipPos(PVector m){
		//return m.x>menu.x+menu.w&&m.x<menu.x+menu.w+50&&m.x>menu.y-50&&m.x<menu.y;
		return applet.mouseX>menu.x+menu.w+parentTab.x&&applet.mouseX<menu.x+menu.w+50+parentTab.x&&applet.mouseY>menu.y-50+parentTab.y&&applet.mouseY<menu.y+parentTab.y;
	};

	boolean tooltipPos(PVector m,PGraphics c){
		return applet.mouseX>menu.x+menu.w+parentTab.x&&applet.mouseX<menu.x+menu.w+50+parentTab.x&&applet.mouseY>menu.y-50+parentTab.y&&applet.mouseY<menu.y+parentTab.y;
	};

	boolean tooltipPos2(PVector m){
		return m.x>menu.x+menu.w&&m.x<menu.x+menu.w+50&&m.y>menu.y-50&&m.y<menu.y;
	};

	public void setStart(float start){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);

			s.startvalue = start;
		}
	};

	public void setEnd(float end){
		for(int i=0;i<menu.sliders.size();i++){
			Slider s = getSlider(i);

			s.endvalue = end;
		}
	};

	public void setFloat(int i,float a,float b){
		if(i<menu.sliders.size()){
			Slider s = getSlider(i);
			s.startvalue = a;
			s.endvalue = b;
		}
	};

	public void setFloat(int i,int a,int b){
		if(i<menu.sliders.size()){
			Slider s = getSlider(i);
			s.startvalue = a;
			s.endvalue = b;
		}
	};

	public void setBms(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		if(tooltip!=null)tooltip.setBms(bms);
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.setBms(bms);
		}

	};

	public void showTooltip(boolean k) {
		if(k = true)createTooltip();
		else tooltip = null;

	};

	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
		menu.setRadius(a);
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
		menu.setRadius(a,b,c,d);
	};

	public void setPieSquare() {
		menu.setPieSquare();

	};

	public void setClassicSquare() {
		menu.setClassicSquare();
	};

	public void setClassicRadio() {
		menu.setClassicRadio();
	};

	public void setClassicBox() {
		menu.setClassicBox();
	};

	public void setClassicBar() {
		menu.setClassicBar();
	};

	public void setValues(float[]values) {
		for(int i=0;i<menu.sliders.size();i++){
			Slider a = getSlider(i);
			a.value = values[i];
		}
	};

	public void setBorder() {
		border = true;
	};

	public void setAllBorder(boolean k) {
		border = true;
		menu.setAllBorder(k);
	};

	public void setTransparency(float a) {
		transparency = a;
		menu.setTransparency(a);
	};

	public void setAllTransparency(float a) {
		transparency = a;
		menu.setAllTransparency(a);
	};

	public void setGrid(int i,int j) {
		grid = true;
		menu.grid = true;
		cols = i;
		rows = j;

	};

	public void setGrid(int i,int j,float h,float v) {
		cols = i;
		rows = j;
		grid = true;
		ghSpacing = h;
		gvSpacing = v;

		menu.setGrid(i, j, h, v);
	};
	
	public Slider getSlider(int i) {
		if(i<menu.sliders.size())return menu.sliders.get(i);
		else return null;
	};

};
