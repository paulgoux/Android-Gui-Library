package androidGui;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;

public class tab extends tabBoundary {
	BMScontrols Bms;
	PApplet applet;
	public PGraphics canvas,canvas2;
	public  float x, y, w, h,bx,by,bh,bw,r1,r2,r3,r4,transparency,bbx,bby,textSize;
	int tabindex = -1, current,id,Width,Height,lastState,txoff,tyoff;
	public int state,canvasIndex;
	public String label,itemLabel;
	public boolean border,open,parentCanvas,overflow,docking,docked,dmdown,show;
	public boolean localTheme,resizable,vscroll,hscroll;
	public PVector mouse,mouse2;
	public Dock parentDock; 
	PFont myFont;
	
	public ArrayList<PGraphics> canvases = new ArrayList<PGraphics>();
	public ArrayList<Grid> grids = new ArrayList<Grid>();

	public ArrayList<Window> windows = new ArrayList<Window>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Boundary> boundaries = new ArrayList<Boundary>();
	public ArrayList<PImage> images = new ArrayList<PImage>();
	public ArrayList<PImage> temp_images = new ArrayList<PImage>();
	public ArrayList<Menu> menus = new ArrayList<Menu>();
	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<sliderBox> sliderBoxes = new ArrayList<sliderBox>();
	public ArrayList<Dropdown> dmenus = new ArrayList<Dropdown>();
	public ArrayList<Table_> tables = new ArrayList<Table_>();
	public ArrayList<String> links = new ArrayList<String>();

	public ArrayList<TextArea> textareas = new ArrayList<TextArea>();
	public ArrayList<textBlock> textBlocks = new ArrayList<textBlock>();
	public ArrayList<TextBox> textboxes = new ArrayList<TextBox>();

	public ArrayList<Img> imageProcessors = new ArrayList<Img>();
	public ArrayList<vectorText> wordVectors = new ArrayList<vectorText>();

	public ArrayList<String[]> temp_text = new ArrayList<String[]>();
	public ArrayList<processing.data.Table> temp_tables = new ArrayList<processing.data.Table>();
	public ArrayList<Boolean> bools = new ArrayList<Boolean>();

	public String folder = "",file = "";
	public HashMap<String, Boolean> keys = new HashMap<String, Boolean>();


	public PVector window;
	public Slider sliderv;
	public Slider sliderh;
	public tab navigator;
	public tab child, parent,parentTab;
	public tab current_tab, next_tab, previous_tab;
	public int titleColor,textColor,tabcol;

	public String [] text;
	public ArrayList<tab> tabs = new ArrayList<tab>();
	public ArrayList<tab> states = new ArrayList<tab>();
	public ArrayList<Integer> transitions = new ArrayList<Integer>();
	public boolean visible = true, scrollable,draggable,drag;
	boolean sliderset, displayChild, init, setTab,slidersUpdated;
	public Button title;

	public tab( float x, float y, float w, float h,int k,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		canvas = createCanvas(bms);
		//tabs.add(this);
		states.add(this);
		createConstruct();
		tabcol = applet.color(0,255,175);
	};

	public tab( float x, float y, float w, float h,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		//tabs.add(this);
		states.add(this);
		canvas = createCanvas(bms);
		tabcol = applet.color(0,255,175);
		createConstruct();
	};

	public tab( float x, float y, float w, float h,Slider s,BMScontrols bms) {


		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		canvas = createCanvas(bms);
		states.add(this);
		tabcol = applet.color(0,255,175);
	};

	public tab( float x, float y, float w, float h,Boundary b,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		canvas = createCanvas(bms);
		states.add(this);
		createConstruct2();
		tabcol = applet.color(0,255,175);
	};

	public tab( float x, float y, float w, float h, String label,int k,BMScontrols bms) {
		//super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;

		this.label = label;
		this.itemLabel = label;
		title = new Button(x, y, w, 20, label,bms);
		canvas = createCanvas(w,h-title.h,bms);
		//		this.x = title.x;
		//		this.y = title.y+title.h;
		title.setclassicBar();
		title.border = false;
		tabcol = applet.color(0,255,175);
		createConstruct();
	};

	public tab( float x, float y, float w, float h, String label,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		this.label = label;
		this.itemLabel = label;
		title = new Button(x,y, w, 20, label,bms);
		canvas = createCanvas(w,h-title.h,bms);
		//		this.x = title.x;
		//		this.y = title.y+title.h;
		title.setclassicBar();
		title.border = false;
		tabcol = applet.color(0,255,175);
		createConstruct();
	};

	public tab( float x, float y, float w, float h, String label,float w1,float h1,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		this.label = label;
		this.itemLabel = label;
		title = new Button(x,y, w1, h1, label,bms);
		canvas = createCanvas(w,h-title.h,bms);
		//		this.x = title.x;
		//		this.y = title.y+title.h;
		title.setclassicBar();
		title.border = false;
		tabcol = applet.color(0,255,175);
		createConstruct();
	};

	public tab( float x, float y, float w, float h, String label,boolean n,BMScontrols bms) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		Bms = bms;
		applet = bms.applet;
		this.label = label;
		this.itemLabel = label;
		title = new Button(x, y, w, 20, label,bms);
		canvas = createCanvas(w,h-title.h,bms);
		//		this.x = title.x;
		//		this.y = title.y+title.h;
		title.setclassicBar();
		title.border = false;
		tabcol = applet.color(0,255,175);
		createConstruct();
	};

	tab() {
	};

	public void createConstruct(){
		bx = x;
		by = y;
		myFont = applet.createFont("Arial", 12);
		Window w1 = new Window(83, 80, 200, 200, "C:\\Users\\paul goux\\",Bms);

		windows.add(w1);

		sliderv = new Slider(w-10, 0, 10, h,Bms);
		if(title!=null) {
			//			sliderv.y = title.h;
			sliderv.h = h-title.h;
		}
		sliderv.classic = true;
		sliderv.bar = true;
		sliderv.vertical = true;
		sliderv.tooltip = null;
		sliderv.parentCanvas = true;
		sliderv.parentTab = this;
		sliderv.initColors();
		//	    
		sliderh = new Slider(0, h-10, w-10, 10,Bms);
		sliderh.tooltip = null;
		sliderh.classic = true;
		sliderh.bar = true;
		sliderh.parentCanvas = true;
		sliderh.parentTab = this;
		sliderh.initColors();
		Boundaries.add(new tabBoundary(0, 0, w, h, 4,Bms));
		states.add(this);
	};

	public void createConstruct2(){

		sliderv = new Slider(w-10, 0, 10, h,Bms);
		if(title!=null) {
			//			sliderv.y = title.h;
			sliderv.h = h-title.h;
		}
		sliderv.classic = true;
		sliderv.bar = true;
		sliderv.vertical = true;
		sliderv.tvisible = false;
		sliderv.Bms = Bms;
		sliderv.applet = applet;
		sliderv.initColors();
		sliderh = new Slider(0, h-10, w-10, 10);
		sliderh.tvisible = false;
		sliderh.classic = true;
		sliderh.bar = true;
		sliderh.Bms = Bms;
		sliderh.applet = applet;
		sliderh.initColors();
		//Boundaries.add(new Boundary(0, 0, w, h, 4));
	};

	public void setvScroll() {

		sliderv = new Slider(w-10, 0, 10, h,Bms);
		if(title!=null) {
			//			sliderv.y = title.h;
			sliderv.h = h-title.h;
		}
		sliderv.setClassicBar();
		sliderv.vertical = true;
		sliderv.tvisible = false;
		scrollable = true;
		vscroll = true;
		//hscrol

	};

	public void sethScroll() {

		sliderh = new Slider(0, h-10, w-10, 10,Bms);
		sliderh.tvisible = false;
		sliderh.classic = true;
		sliderh.bar = true;
		sliderh.initColors();
		scrollable = true;
		hscroll = true;
	};

	public void setScroll() {

		sliderh = new Slider(0, h-10, w-10, 10,Bms);
		sliderh.tvisible = false;
		sliderh.classic = true;
		sliderh.bar = true;
		sliderh.initColors();
		scrollable = true;
		hscroll = true;

		sliderv = new Slider(w-10, 0, 10, h-10,Bms);
		//		sliderv = new Slider(w-10, 0, 10, h,Bms);
		if(title!=null) {
			sliderv.y = title.h;
			sliderv.h = h-title.h;
		}
		sliderv.classic = true;
		sliderv.bar = true;
		sliderv.vertical = true;
		sliderv.tvisible = false;
		sliderv.initColors();
		scrollable = true;
		vscroll = true;
	};

	PGraphics createCanvas(BMScontrols bms) {
		Bms = bms;
		PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
		canvases.add(pg);
		//pg.setLocation(x, y);
		return pg;
	};

	PGraphics createCanvas2(BMScontrols bms) {
		Bms = bms;
		PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
		canvases.add(pg);
		return pg;
	};

	PGraphics createCanvas(float w,float h,BMScontrols bms) {
		Bms = bms;
		PGraphics pg = bms.applet.createGraphics((int) (w), (int)(h));
		canvases.add(pg);
		return pg;
	};
	
	PGraphics createCanvas(float w,float h) {
		PGraphics pg = null;
		if(applet!=null) {
		pg = applet.createGraphics((int) (w), (int)(h));
		canvases.add(pg);
		}
		return pg;
	};

	public void setCanvas(tab t){
		if (!sliderh.mdown)sliderv.mouseFunctions(t.mouse);
		// fill(0);
		// ellipse(t.mouse.x,t.mouse.y,20,20);
		sliderv.mouse = mouse;
		sliderv.draw(t.canvas);
		if (!sliderv.mdown)sliderh.mouseFunctions(t.mouse);
		sliderh.mouse = mouse;
		sliderh.draw(t.canvas);

	};

	public void save(){

	};

	public void load(){

	};


	public void disptab(PGraphics scene) {

//		tab t = states.get(state);
//
//		if (toggle&&canvas!=null) {
//			canvas.beginDraw();
//			canvas.background(50);
//			t.logic();
//			t.drawBorder();
//			t.boundingBox();
//			t.drawDragBox();
//			t.drawButtons();
//			t.drawMenus();
//			t.drawTextboxes();
//			t.drawTextareas();
//			t.drawTextBlocks();
//			t.drawSliderBoxes();
//			if(t.title!=null&&t.visible){
//				t.title.toggle = true;
//				t.drawTitle();
//				t.drawBorder();
//				t.drawDmenu();
//			}
//			//t.drawImages();
//			if(t.scrollable)t.drawSlider();
//			//t.drawWindows();
//			canvas.endDraw();
//			scene.image(canvas,x,y);
//		}
		
		tab t = states.get(state);
		t.canvas = states.get(0).canvas;
		if(t!=null&&toggle&&t.canvas!=null) {
			if(t.canvas!=null)t.logic();
		}
		if (t!=null&&toggle&&t.visible&&t.canvas!=null) {

			//canvas.background(50);
			t.canvas.beginDraw();
			t.drawDragBox();
			t.drawBorder();
			t.boundingBox();
			t.drawButtons();
			t.drawMenus();
			t.drawDmenu();
			t.drawTextboxes();
			t.drawTextareas();
			t.drawSliderBoxes();
			t.drawTextBlocks();
			if(t.scrollable)t.drawSlider();
			if(text!=null) {
				t.canvas.textSize(12);
				t.canvas.textFont(myFont);
				int ty = 0;
				if(applet.mousePressed)ty = PApplet.floor(PApplet.map(tyoff,0,text.length*20,0,text.length))-1;
				
				if(ty<0)ty = 0;
				
				int t2y = ty + PApplet.floor(h/textSize+8);
				if(t2y>text.length-1)t2y = text.length;
				for(int i=ty;i<t2y;i++) {
					t.canvas.text(text[i],(int) txoff ,40+(int) tyoff + 20 * i);
				}
			}
			t.canvas.endDraw();
			if(t.title==null)scene.image(t.canvas,(int) x,(int) y);
			else scene.image(t.canvas,(int) x,(int) y+(int) title.h);
		}
		if((t!=null&&toggle)) {
			if(t.title!=null){
				t.drawTitle(scene);
			}
		}
	};

	public void displayTab() {
		tab t = states.get(state);
		tab t1 = states.get(lastState);
		if(state!=lastState) {

			for(int i=0;i<states.size();i++) {
				t.setPos(t1.x, t1.y);
				if(t.title!=null)t.title.setPos(t1.x, t1.y);

			}
			lastState = state;
		}
		mouse = t1.getMouse();
		t.mouse = mouse;
		//		if(t.title!=null)t.title.mouse = mouse;
		canvas = canvases.get(canvasIndex);
		t.canvas = canvases.get(canvasIndex);
		t.disptab();
		setAllToggle(toggle);
	};

	public void displayTab(PGraphics canvas) {
		tab t = states.get(state);
		tab t1 = states.get(lastState);
		if(state!=lastState) {

			for(int i=0;i<states.size();i++) {
				t.setPos(t1.x, t1.y);
				if(t.title!=null)t.title.setPos(t1.x, t1.y);

			}
			lastState = state;
		}
		mouse = t1.getMouse(parentTab.mouse);
		t.mouse = mouse;
		if(t.title!=null)t.title.mouse = mouse;
		canvas = canvases.get(canvasIndex);
		t.disptab(canvas);
		setAllToggle(toggle);
	};

	public void disptab() {
		tab t = states.get(state);
		t.canvas = states.get(0).canvas;
		if(t!=null&&toggle&&t.canvas!=null) {
			if(t.canvas!=null)t.logic();
		}
		if (t!=null&&toggle&&t.visible&&t.canvas!=null) {

			//canvas.background(50);
			t.canvas.beginDraw();
			t.drawDragBox();
			t.drawBorder();
			t.boundingBox();
			t.drawButtons();
			t.drawMenus();
			t.drawDmenu();
			t.drawTextboxes();
			t.drawTextareas();
			t.drawSliderBoxes();
			t.drawTextBlocks();
			if(t.scrollable)t.drawSlider();
			int lcount = 0;
//			if(text!=null) {
//				t.canvas.textSize(12);
//				t.canvas.textFont(myFont);
//				int ty = -PApplet.floor(PApplet.map(tyoff,0,text.length*20,0,text.length))-1;
//				
//				if(ty<0)ty = 0;
//				
//				int t2y = ty + PApplet.floor(h/(textSize+8));
//				if(t2y>text.length-1)t2y = text.length;
//				if(applet.mousePressed&&text!=null)
//					applet.println("tab disptab text ty:",ty,t2y);
//				for(int i=ty;i<t2y;i++) {
//					t.canvas.text(text[i],(int) txoff ,40+(int) tyoff + 20 * i);
//					lcount++;
//				}
//			}
			t.canvas.endDraw();
			if(t.title==null)applet.image(t.canvas,(int) x,(int) y);
			else applet.image(t.canvas,(int) x,(int) y+(int) title.h);
			if(applet.mousePressed&&text!=null)
				applet.println("tab disptab text",text.length,lcount);
		}
		if((t!=null&&toggle)) {
			if(t.title!=null){
				t.drawTitle(true);
			}
		}
	};

	public void disptabDebug() {
		applet.fill(255);
		tab t = states.get(state);
		PApplet.println("display tab debug 00",state,states.size(),canvas,states.get(states.size()-1).canvas);

		PApplet.println("display tab debug 01");
		t.disptab(canvas);
		PApplet.println("display tab debug 02");
	};


	public void disptabs() {

		tab t = states.get(state);
		//applet.println(t.menus.size());
		if (toggle&&t!=null&&canvas!=null) {
			t.mouse = getMouse();
			mouse = getMouse();
			canvas.beginDraw();
			canvas.background(50);
			//for(int i=0;i<states.size();i++){
			//tab t1 = states.get(i);

			//if(i!=state&&toggle){
			//  t1.toggle = false;
			//  t1.visible = false;
			//  if(t1.title!=null)t1.title.toggle= true;
			//}else t1.toggle=true;}
			t.logic();
			t.boundingBox();
			t.drawButtons();
			t.drawMenus();
			t.drawTextboxes();
			t.drawTextareas();
			t.drawTable_s();
			//t.displayInnerTabs();
			t.drawText();
			t.drawTitle();
			t.drawBorder();
			t.drawDmenu();
			t.drawSliderBoxes();
			t.drawTextBlocks();
			//t.drawWindows();
			if (t!=null&&t.scrollable&&t.toggle)t.drawSlider();

			canvas.endDraw();
			applet.image(canvas,x,y);
		}
	};

	public void disptabs(PGraphics scene) {

		tab t = states.get(state);
		t.canvas = states.get(0).canvas;
		if(t!=null&&toggle&&t.canvas!=null) {
			if(t.canvas!=null)t.logic();
		}
		if (t!=null&&toggle&&t.visible&&t.canvas!=null) {

			//canvas.background(50);
			t.canvas.beginDraw();
			t.drawDragBox();
			t.drawBorder();
			t.boundingBox();
			t.drawButtons();
			t.drawMenus();
			t.drawDmenu();
			t.drawTextboxes();
			t.drawTextareas();
			t.drawSliderBoxes();
			t.drawTextBlocks();
			if(t.scrollable)t.drawSlider();
			if(text!=null) {
				t.canvas.textSize(12);
				t.canvas.textFont(myFont);
				int ty = PApplet.floor(PApplet.map(tyoff,0,text.length*20,0,text.length))-1;
				
				if(ty<0)ty = 0;
				
				int t2y = ty + PApplet.floor(h/textSize+8);
				if(t2y>text.length-1)t2y = text.length;
				for(int i=ty;i<t2y;i++) {
					t.canvas.text(text[i],txoff ,40+tyoff + 20 * i);
				}
			}
			t.canvas.endDraw();
			if(t.title==null)scene.image(t.canvas,x,y);
			else scene.image(t.canvas,x,y+title.h);

			if((t!=null&&toggle)) {
				if(t.title!=null){
					t.drawTitle(true);
				}
			}
			

		}

	};

	public void addState(int k){
		if(title==null){
			for(int i=0;i<k;i++){
				states.add(new tab(x,y,w,h,Bms));
			}}else{
				for(int i=0;i<k;i++){
					states.add(new tab(x,y,w,h,"hello"+i,Bms));
				}}
	};

	public void addState(int k,String[] names){

		for(int i=0;i<names.length;i++){
			tab t = new tab(x,y,w,h,names[i],Bms);
			t.toggle = true;
			t.visible = true;
			states.add(t);
		}
	};

	public void addState(String[] names){

		for(int i=0;i<names.length;i++){
			tab t = new tab(x,y,w,h,names[i],Bms);
			t.toggle = true;
			t.visible = true;
			states.add(t);
		}
	};

	public void addStates(String[] names){

		for(int i=0;i<names.length;i++){
			tab t = new tab(x,y,w,h,names[i],Bms);
			t.toggle = true;
			t.visible = true;
			states.add(t);
		}
	};

	public void setTitle(int i,String s){
		tab k = states.get(i);

		if(k.title!=null)k.title.label = s;
	};

	public Button add(int i,Button b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(i);
		b.parentCanvas = true;
		k.buttons.add(b);
		return b;
	};

	public Menu add(int i,Menu m){
		//clear();
		m.Bms = Bms;
		m.applet = applet;
		tab k = states.get(i);
		m.parentCanvas = true;
		k.menus.add(m);
		return m;
	};

	public Dropdown add(int i,Dropdown d){
		//clear();
		d.Bms = Bms;
		d.applet = applet;
		tab k = states.get(i);
		d.parentCanvas = true;
		k.dmenus.add(d);
		return d;
	};

	public TextBox add(int i,TextBox t){
		//clear();
		t.Bms = Bms;
		t.applet = applet;
		tab k = states.get(i);
		t.parentCanvas = true;
		k.textboxes.add(t);
		return t;
	};

	public TextArea add(int i,TextArea t){
		t.setTab(this);
		tab k = states.get(i);
		k.textareas.add(t);
		return t;
	};

	public textBlock add(int i,textBlock t){
		//clear();
		t.Bms = Bms;
		t.applet = applet;
		tab k = states.get(i);
		t.parentCanvas = true;
		k.textBlocks.add(t);
		return t;
	};

	public String add(int i,String s){
		//clear();
		// tab k = states.get(i);
		// k.textblock.add(s);
		return s;
	};

	public Table_ add(int i,Table_ t){
		//clear();
		t.Bms = Bms;
		t.applet = applet;
		tab k = states.get(i);
		t.parentCanvas = true;
		k.tables.add(t);
		return t;
	};

	public vectorText add(int i,vectorText s){
		tab k = states.get(i);
		s.setBms(this);
		k.wordVectors.add(s);

		return s;
	};

	public Img add(int i,Img s){
		tab k = states.get(i);
		s.setBms(this);
		k.imageProcessors.add(s);

		return s;
	};

	public Button add(Button b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(0);
		b.parentCanvas = true;
		k.buttons.add(b);
		return b;
	};

	public Button addToAll(Button b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(0);
		b.parentCanvas = true;
		for(int i=0;i<states.size();i++) {
			states.get(i).buttons.add(b);
		}

		return b;
	};

	public Button add(Button b,int i){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(i);
		b.parentCanvas = true;
		k.buttons.add(b);
		return b;
	};
	
	public Boundary addBoundary(Boundary b) {
		boundaries.add(b);
		return b;
	};

	public Menu add(Menu m){
		//clear();
		m.setBms(Bms);
		tab k = states.get(0);
		//	    m.x = x + m.bx;
		m.parentCanvas = true;
		m.localCanvas = canvas;
		k.menus.add(m);
		return m;
	};

	public Menu addToAll(Menu b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(0);
		b.parentCanvas = true;
		for(int i=0;i<states.size();i++) {
			states.get(i).menus.add(b);
		}

		return b;
	};

	public Dropdown add(Dropdown d){
		//clear();
		d.Bms = Bms;
		d.applet = applet;
		tab k = states.get(0);
		d.parentCanvas = true;
		k.dmenus.add(d);
		return d;
	};

	public Dropdown addToAll(Dropdown b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(0);
		b.parentCanvas = true;
		for(int i=0;i<states.size();i++) {
			states.get(i).dmenus.add(b);
		}

		return b;
	};

	public TextBox add(TextBox t){
		//clear();
		t.Bms = Bms;
		t.applet = applet;
		tab k = states.get(0);
		t.parentCanvas = true;
		k.textboxes.add(t);
		return t;
	};

	public TextBox addToAll(TextBox b){
		//clear();
		b.Bms = Bms;
		b.applet = applet;
		tab k = states.get(0);
		b.parentCanvas = true;
		for(int i=0;i<states.size();i++) {
			states.get(i).textboxes.add(b);
		}

		return b;
	};

	public TextArea add(TextArea t){
		t.setTab(this);
		tab k = states.get(0);
		k.textareas.add(t);
		return t;
	};

	public TextArea addToAll(TextArea b){
		b.setTab(this);
		tab k = states.get(0);
		for(int i=0;i<states.size();i++) {
			states.get(i).textareas.add(b);
		}

		return b;
	};

	public String add(String s){
		//clear();
		tab k = states.get(0);
		//k.textBlocks.add(s);
		return s;
	};

	public Table_ add(Table_ t){
		//clear();
		t.Bms = Bms;
		t.applet = applet;
		t.parentCanvas = true;
		tab k = states.get(0);
		k.tables.add(t);
		return t;
	};

	public sliderBox add(sliderBox s){
		//clear();
		s.setBms(Bms);
		s.parentCanvas = true;
		tab k = states.get(0);
		k.sliderBoxes.add(s);
		if(s.tooltip!=null) {
			s.tooltip.setBms(Bms);
		}
		//	    for (int i=0; i<k.sliderBoxes.size(); i++) {
		//	        sliderBox s1 = k.sliderBoxes.get(i);
		//	        s1.x = x+s1.bx;
		//	        s1.y = y+s1.by;
		//	    }
		s.setRadius(r1,r2,r3,r4);
		return s;
	};

	public sliderBox add(int i,sliderBox s){
		//clear();
		if(i<sliderBoxes.size()) {
			s.setBms(Bms);
			s.parentCanvas = true;
			tab k = states.get(0);
			k.sliderBoxes.add(s);
			if(s.tooltip!=null) {
				s.tooltip.setBms(Bms);
			}
			//	    for (int i=0; i<k.sliderBoxes.size(); i++) {
			//	        sliderBox s1 = k.sliderBoxes.get(i);
			//	        s1.x = x+s1.bx;
			//	        s1.y = y+s1.by;
			//	    }
			s.setRadius(r1,r2,r3,r4);
			return s;
		}else return null;

	};


	public vectorText add(vectorText s){
		s.setBms(this);
		wordVectors.add(s);

		return s;
	};

	public Img add(Img s){
		s.setBms(this);
		imageProcessors.add(s);

		return s;
	};

	public void clear(){
		textboxes = new ArrayList<TextBox>();
		grids = new ArrayList<Grid>();
		textareas = new ArrayList<TextArea>();
		//windows = new ArrayList<Window>();
		buttons = new ArrayList<Button>();
		images = new ArrayList<PImage>();
		temp_images = new ArrayList<PImage>();
		menus = new ArrayList<Menu>();
		dmenus = new ArrayList<Dropdown>();
		tables = new ArrayList<Table_>();
		links = new ArrayList<String>();
		textBlocks = new ArrayList<textBlock>();
		//processedText = new ArrayList<vectorText>();
		temp_text = new ArrayList<String[]>();
		temp_tables = new ArrayList<processing.data.Table>();
	};

	public void logic() {

		if (navigator!=null)navigator.disptabs();
		if (scrollable) {
			sliderh.mouse = getMouse();
			if (!sliderh.mdown) {
				//sliderv.mouseFunctions();
				//sliderv.set(-h+20, h-20, this, "window.y");
			}
			//sliderv.mouse = getMouse();
			if (!sliderv.mdown) {
				//sliderh.set(-w+10, w-10, this, "window.x");
				//sliderh.mouseFunctions();
			}
		}
		if(draggable&&Bms.sliderObject==null){

			if(!sliderv.mdown&&!sliderh.mdown&&title.pos()&&applet.mousePressed&&!mdown&&!drag&&Bms.currentObject==null){

				Bms.currentObject = this;

				if(title.label!=null)Bms.currentMouseObject = title.label;
				//applet.println("tab",Bms.currentMouseObject);
				mdown = true;
				docked = false;
				docking = false;
				dx = x - applet.mouseX;
				dy = y - applet.mouseY;
				bbx = applet.mouseX;
				bby = applet.mouseY;
				drag = true;
			}

			if(drag){
				x = applet.mouseX+dx;
				y = applet.mouseY+dy;
				if(bbx!=applet.pmouseX)bbx = 0;
				if(bby!=applet.pmouseY)bby = 0;
				if(title!=null)title.setPos(x, y);
				if(parentDock!=null&&parentDock.pos(new PVector(x,y))&&!docking){
					docking = true;
					parentDock.loc = itemLabel;
					parentDock.currentObject = this;
				}
			}

			if(!applet.mousePressed&&docking&&!docked){
				PApplet.println("docked");
				docked = true;
				docking = false;
				x = bx;
				y = by;

			}

			if(parentDock!=null) {
				if(drag&&!applet.mousePressed&&(Bms.dock.pos()||parentDock.pos())) {
					PApplet.println("docked off");
					drag = false;
					x = bx;
					y = by;
					if(title!=null)title.setPos(x,y);
					if(Bms.dock.pos()){
						Bms.dock.loc = itemLabel;
						Bms.dock.currentObject = this;
					}
				}
			}else {
				if(drag&&!applet.mousePressed&&(Bms.dock.pos())) {
					PApplet.println("docked off");
					x = bx;
					y = by;
					drag = false;
					if(title!=null)title.setPos(x,y);
					Bms.dock.loc = itemLabel;
					Bms.dock.currentObject = this;
				}
			}

			if(drag&&!applet.mousePressed&&Bms.dock.pos()) {
				PApplet.println("docked off");
				drag = false;
				if(title!=null)title.setPos(x,y);
			}

			if(!applet.mousePressed)mdown = false;
			if(!mdown){
				drag = false;
				if(Bms.currentObject == this)Bms.currentObject = null;
			}
		}
		if(title!=null&&bbx==applet.mouseX&&bby==applet.mouseY)
			title.toggleU(this,"visible",new PVector(applet.mouseX,applet.mouseY));

	};

	public void drawSliderBoxes(){
		
		for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {

			sliderBox s = null;
			if(states.get(state).sliderBoxes.get(i)!=null){
				s = states.get(state).sliderBoxes.get(i);

				if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
				if(scrollable&&vscroll&&sliderv!=null)s.y = s.by - sliderv.value;

				s.mouse = mouse;
				s.parentTab = this;
				s.draw(canvas);
			}
		}
	};

	public void drawSliderBoxes_(){
		for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {

			sliderBox s = states.get(state).sliderBoxes.get(i);
			if(scrollable&&vscroll&&sliderv!=null)s.menu.y = s.menu.by - sliderv.value;
			if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
			//			s.mouse = getMouse();
			s.mouse = getMouse();
			s.parentTab = this;
			s.draw();
		}
	};

	public void drawSliderBoxes(PGraphics canvas){

		if(canvas!=null){

			canvas.beginDraw();
			for (int i=states.get(state).sliderBoxes.size()-1;i>-1; i--) {

				sliderBox s = states.get(state).sliderBoxes.get(i);
				if(scrollable&&vscroll&&sliderv!=null)s.y = s.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)s.x = s.bx - sliderh.value;
				//				s.mouse = getMouse();
				s.mouse = mouse;
				s.parentTab = this;
				s.draw(canvas);
			}
			canvas.endDraw();
		}
	};


	public void drawTextBlocks(){
		for (int i=states.get(state).textBlocks.size()-1;i>-1; i--) {

			textBlock t = states.get(state).textBlocks.get(i);
			//t.mouse = getMouse();
			//t.parentTab = this;
			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			t.draw(canvas);
		}
	};

	public void drawTextBlocks(PGraphics canvas){
		for (int i=states.get(state).textBlocks.size()-1;i>-1; i--) {

			textBlock t = states.get(state).textBlocks.get(i);
			//t.mouse = getMouse();
			//t.parentTab = this;
			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			t.draw(canvas);
		}
	};

	public void drawTextBlocks(boolean b){
		canvas.beginDraw();
		for (int i=states.get(state).textBlocks.size()-1;i>-1; i--) {

			textBlock t = states.get(state).textBlocks.get(i);
			//t.mouse = getMouse();
			//t.parentTab = this;
			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			t.draw(canvas);
		}
		canvas.endDraw();

	};

	public void drawDragBox(){

		canvas.fill(0);
		canvas.noStroke();
		if(border)canvas.stroke(255);
		canvas.rect(x,y-5,w,5,r1,r2,r3,r4);

	};

	public void drawDragBox(PGraphics canvas){

		canvas.fill(0);
		canvas.noStroke();
		if(border)canvas.stroke(255);
		canvas.rect(x,y-5,w,5,r1,r2,r3,r4);

	};

	public void drawSlider() {
		//canvas.beginDraw();
		canvas.fill(0);
		if(vscroll&&sliderv!=null){
			//			sliderv.mouse = getMouse();
			sliderv.mouse = mouse;
			//if(posTab(getMouse())||sliderv.mdown)
			if(!sliderh.mdown)sliderv.mouseFunctions(mouse);
			sliderv.draw(canvas);
		}
		if(hscroll&&sliderh!=null){
			//			sliderh.mouse = getMouse();
			sliderh.mouse = mouse;
			//if(posTab(getMouse())||sliderh.mdown)
			if(!sliderv.mdown)sliderh.mouseFunctions(mouse);
			sliderh.draw(canvas);
		}

	};

	public void drawSlider(PGraphics canvas) {
		//canvas.beginDraw();
		canvas.fill(0);
		if(vscroll&&sliderv!=null){
			//			sliderv.mouse = getMouse();
			sliderv.mouse = mouse;
			//if(posTab(getMouse())||sliderv.mdown)
			if(!sliderh.mdown)sliderv.mouseFunctions(mouse);
			sliderv.draw(canvas);
		}
		if(hscroll&&sliderh!=null){
			//			sliderh.mouse = getMouse();
			sliderh.mouse = mouse;
			//if(posTab(getMouse())||sliderh.mdown)
			if(!sliderv.mdown)sliderh.mouseFunctions(mouse);
			sliderh.draw(canvas);
		}

	};

	public void drawText() {
	};

	public void drawDmenu() {
		boolean k = false;
		int id = -1;
		for (int i=states.get(state).dmenus.size()-1;i>-1; i--) {
			Dropdown d = states.get(state).dmenus.get(i);

			if(parentTab==null)d.mouse = getMouse();
			else d.mouse = getMouse(mouse);
			d.mouse = mouse;
			if(applet.mousePressed&&!dmdown&&d.toggle){
				id=i;
				dmdown = true;
			}
			if(scrollable&&vscroll&&sliderv!=null)d.y = d.by - sliderv.value;
			if(scrollable&&hscroll&&sliderh!=null)d.x = d.bx - sliderh.value;
			if(d.toggle== true&&id!=i)d.toggle=false;
			d.displayDropdown(canvas);
			dmdown = false;
		};
	};

	public void scrolllogic() {
	};

	public void drawTitle() {
		canvas.fill(255);
		if(mouse==null)mouse = getMouse();
		if(states.size()>0&&state<states.size()&&state>-1){
			if(states.get(state).title!=null){
				Button b = states.get(state).title;
				//				b.mouse = m;
				//				b.mouse = new PVector(applet.mouseX,applet.mouseY);
				b.draw();
			}}

		if(states.size()>0&&state<states.size()&&states.get(state).title!=null)
			states.get(state).title.clickU(new PVector (applet.mouseX,applet.mouseY));
	};

	public void drawTitle(boolean k) {
		applet.fill(255);
		if(mouse==null)mouse = getMouse();
		if(states.size()>0&&state<states.size()&&state>-1){
			if(states.get(state).title!=null){
				Button b = states.get(state).title;
				//				b.mouse = m;
				//				b.mouse = new PVector(applet.mouseX,applet.mouseY);
				b.draw();
			}}

		if(states.size()>0&&state<states.size()&&states.get(state).title!=null)
			states.get(state).title.clickU(new PVector (applet.mouseX,applet.mouseY));
	};

	public void drawTitle(PGraphics scene) {
		scene.fill(255);
		if(states.size()>0&&state<states.size()&&state>-1){
			if(states.get(state).title!=null){
				states.get(state).title.mouse = mouse;
				states.get(state).title.draw(scene);
			}}

		if(states.size()>0&&state<states.size()&&states.get(state).title!=null)states.get(state).title.toggleU(this, "toggle",mouse);
	};

	public void drawBorder() {
		if (border) {
			if(title==null) {
				canvas.stroke(255, 200);
				canvas.noFill();
				canvas.rect(0, 0, w, h,r1,r2,r3,r4);
			}else {
				canvas.stroke(255, 200);
				canvas.noFill();
				canvas.rect(0, title.h, w, h,r1,r2,r3,r4);
			}
		}
		canvas.noStroke();
	};

	public void drawBorder(PGraphics canvas) {
		if (border) {
			if(title==null) {
				canvas.stroke(255, 200);
				canvas.noFill();
				canvas.rect(0, 0, w, h,r1,r2,r3,r4);
			}else {
				canvas.stroke(255, 200);
				canvas.noFill();
				canvas.rect(0, title.h, w, h,0,0,r3,r4);
			}
		}
		canvas.noStroke();
	};

	public void boundingBox() {
		if(canvas!=null){
			if(title==null) {
				canvas.noStroke();
				canvas.fill(255);
				canvas.rect(0, 0, w, h,r1,r2,r3,r4);
				canvas.fill(Bms.tabcol);
				if(localTheme)canvas.fill(tabcol);
				canvas.rect(0, 0, w, h,r1,r2,r3,r4);
			}else {
				canvas.noStroke();
				canvas.fill(255);
				canvas.rect(0, 0, w, h-title.h,0,0,r3,r4);
				canvas.fill(Bms.tabcol);
				if(localTheme)canvas.fill(tabcol);
				canvas.rect(0, 0, w, h-title.h,0,0,r3,r4);
			}

		}
	};

	public void boundingBox(PGraphics canvas) {
		if(canvas!=null){
			if(title==null) {
				canvas.noStroke();
				canvas.fill(255);
				canvas.rect(0, 0, w, h,r1,r2,r3,r4);
				canvas.fill(Bms.tabcol);
				if(localTheme)canvas.fill(tabcol);
				canvas.rect(0, 0, w, h,0,0,r3,r4);
			}else {
				canvas.noStroke();
				canvas.fill(255);
				canvas.rect(0, y, w, h,0,0,r3,r4);
				canvas.fill(Bms.tabcol);
				if(localTheme)canvas.fill(tabcol);
				canvas.rect(0, 0, w, h,0,0,r3,r4);
			}
		}
	};

	//important
	public void getFile(){
		//PImage t_img ;

		//Image t_Img;
		//String[] t_text;
		//processing.data.Table t_table;

		//ArrayList<Integer> k = new ArrayList<Integer>();

		//if(file.length()>0){
		//  if(file.endsWith("jpg")|| file.endsWith("jpeg") ||file.endsWith("png")||file.endsWith("bmp")||
		//      file.endsWith("gif")||file.endsWith("JPG")||file.endsWith("JPEG")||file.endsWith("PNG")||
		//      file.endsWith("BMP")||file.endsWith("GIF")){

		//      t_img = loadImage(file);
		//      if(!temp_images.contains(t_img))temp_images.add(t_img);
		//      //if(!temp_images.contains(t_img))images.add(t_img);
		//      Image img = new Image(t_img);
		//      if(!processedImages.contains(img))processedImages.add(img);
		//  }

		//  if(file.endsWith("txt")){

		//      t_text = loadStrings(file);
		//      if(!temp_text.contains(t_text))temp_text.add(t_text);
		//  }

		//  if(file.endsWith("csv")){

		//      t_table = loadTable(file);
		//      if(!temp_tables.contains(t_table))temp_tables.add(t_table);
		//  }

		//  if(file.endsWith("doc")){

		//      t_text = WordStream(file);
		//      if(!temp_text.contains(t_text))temp_text.add(t_text);
		//  }
		//  if(file.endsWith("mp3")|| file.endsWith("aiff") ||file.endsWith("ogg")||file.endsWith("flac")||
		//      file.endsWith("m4a")||file.endsWith("flac")){

		//      t_table = loadTable(file);
		//      if(!temp_tables.contains(t_table))temp_tables.add(t_table);
		//  }
		//}
	};

	public void drawImages(){

		for (int i=0; i<states.get(state).images.size(); i++) {
			PImage p = states.get(state).images.get(i);
			canvas.image(p,x,y);
		}

	};

	public void drawDynamicImages(ArrayList<PImage> p,int k){

		tab t = states.get(state);
		if(t.toggle&&t.visible){
			canvas.beginDraw();
			int borderSize = 4;
			for (int i=p.size()-1;i>-1; i--) {
				PImage p1 = p.get(i);
				canvas.stroke(0);
				canvas.strokeWeight(borderSize);

				canvas.noFill();
				if(i==0){
					canvas.rect((p1.width+20) * i-borderSize*2 - p1.width+20-35,40-borderSize*2,p1.width+borderSize*4,p1.height+borderSize*4);
				}
				canvas.stroke(255,0,0);
				//float x = 
				if(0==k-i){
					canvas.rect((p1.width+20) * i-borderSize,40-borderSize*2,p1.width+borderSize*4,p1.height+borderSize*4);
				}
				canvas.stroke(0);
				canvas.rect((p1.width+20) * i-borderSize-p1.width+20-35,40-borderSize,p1.width+borderSize*2,p1.height+borderSize*2);
				canvas.image(p1,(p1.width+20) * i-p1.width+20-35,40);
			}
			//if(t.scrollable)t.drawSlider();
			canvas.endDraw();
			applet.image(canvas,x,y);
		}
	};

	public void drawButtons() {
		applet.fill(255);

		for (int i=0; i<buttons.size(); i++) {

			Button b = buttons.get(i);
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)b.y = b.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)b.x = b.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)b.y = title.h + b.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)b.x = title.h + b.bx - sliderh.value;
			}

			b.mouse =  mouse;
			b.parentCanvas = true;
			b.draw(canvas);
			//			b.highlight(mouse);
			//			b.self_click2(mouse);
		}
	};

	public void drawButtons(PGraphics scene) {
		scene.fill(255);

		for (int i=0; i<buttons.size(); i++) {
			Button b = buttons.get(i);
			b.mouse =  mouse;
			b.parentCanvas = true;
			b.draw(scene);
			b.highlight(mouse);
			//			b.self_click2(mouse);

			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)b.y = b.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)b.x = b.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)b.y = title.h + b.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)b.x = title.h + b.bx - sliderh.value;
			}
		}
	};

	public void drawMenus() {
		for (int i=0; i<menus.size(); i++) {

			Menu m = menus.get(i);

			m.setParentCanvas(canvas);
			//			if(scrollable&&vscroll&&sliderv!=null)m.y = m.by - sliderv.value;
			//			if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)m.y = m.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)m.y = title.h + m.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
			}
			m.mouse = mouse;
//			m.mouse = getMouse();
//			m.setMouse();

			m.draw(canvas);
			m.click(canvas);

//			if(m.classicBar) {
//				for(int j=0;j<m.items.size();j++){
//					Button b = m.items.get(j);
//					b.clickU(m.mouse);
//				}
//			}
		}
	};

	public void drawMenus(PGraphics canvas) {
		for (int i=0; i<menus.size(); i++) {

			Menu m = menus.get(i);

			m.setParentCanvas(canvas);
			//			if(scrollable&&vscroll&&sliderv!=null)m.y = m.by - sliderv.value;
			//			if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)m.y = m.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)m.x = m.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)m.y = title.h + m.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)m.x = title.h + m.bx - sliderh.value;
			}
			m.mouse = mouse;
			m.mouse = getMouse();
			m.setMouse();

			m.draw(canvas);
			m.click(canvas);

//			if(m.classicBar) {
//				for(int j=0;j<m.items.size();j++){
//					Button b = m.items.get(j);
//					b.clickU(m.mouse);
//				}
//			}
		}
	};

	public void drawTextboxes() {
		for (int i=0; i<textboxes.size(); i++) {
			TextBox t = textboxes.get(i);
			
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)t.y = title.h + t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = title.h + t.bx - sliderh.value;
			}
			if(parentTab==null) {
				t.mouse = getMouse();
			}
			else {
//				mouse.x -=parentTab.x;
//				mouse.y -=parentTab.y;
//				if(applet.mousePressed)applet.println("tab drawtbxs",parentTab.x,parentTab.y);
				t.mouse = getMouse(parentTab.mouse);
			}
			t.draw(canvas);
		}
	};

	public void drawTextboxes(PGraphics canvas) {
		for (int i=0; i<textboxes.size(); i++) {
			TextBox t = textboxes.get(i);
			//			if(parentTab==null)t.mouse = mouse;
			//			else t.mouse = getMouse(mouse);
			t.mouse = getMouse();
			t.mouse.x += t.x+x;
			t.mouse.y += t.y+y;
			//			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			//			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)t.y = title.h + t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = title.h + t.bx - sliderh.value;
			}
			t.draw(canvas);
		}
	};

	public void drawTextareas() {
		for (int i=0; i<textareas.size(); i++) {
			TextArea t = textareas.get(i);
			t.mouse = mouse;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)t.y = title.h + t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = title.h + t.bx - sliderh.value;
			}
			t.getKey(false);
			t.draw(true);
		}
	};

	public void drawTextareas(PGraphics canvas) {
		for (int i=0; i<textareas.size(); i++) {
			TextArea t = textareas.get(i);
			t.mouse = mouse;
			//			if(parentTab==null)t.mouse = getMouse();
			//			else t.mouse = getMouse(mouse);
			//			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			//			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)t.y = title.h + t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = title.h + t.bx - sliderh.value;
			}
			//			t.toggle= true;
			t.getKey(true);
			t.draw(canvas);
		}
	};

	public void drawWindows() {
		for (int i=0; i<windows.size(); i++) {
			Window w = windows.get(i);
			//w.toggle = true;

			w.displayGrid();
		}
	};

	public void drawTable_s() {
		for (int i=0; i<tables.size(); i++) {
			Table_ t = tables.get(i);
			//			if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
			//			if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			if(title==null) {
				if(scrollable&&vscroll&&sliderv!=null)t.y = t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = t.bx - sliderh.value;
			}else {
				if(scrollable&&vscroll&&sliderv!=null)t.y = title.h + t.by - sliderv.value;
				if(scrollable&&hscroll&&sliderh!=null)t.x = title.h + t.bx - sliderh.value;
			}
			t.draw();

		}
	};

	public void displayInnerTabs() {
		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			t.disptabs();
		}
	};

	public void setAllvScroll() {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setvScroll();
		}
	};

	public void setAllhScroll() {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.sethScroll();
		}
	};

	public void setAllvScroll(float a,float b) {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setvScroll(a,b);
		}
	};

	public void setAllhScroll(float a,float b) {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.sethScroll(a,b);
		}
	};

	public void setAllvScroll(int a,int b) {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setvScroll(a,b);
		}
	};

	public void setAllhScroll(int a,int b) {
		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.sethScroll(a,b);
		}
	};


	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;

		if(title!=null)title.r1 = a;
		if(title!=null)title.r2 = a;

		for (int i=0; i<buttons.size(); i++) {
			Button d = buttons.get(i);
			d.setRadius(a);
		}

		for (int i=0; i<dmenus.size(); i++) {
			Dropdown d = dmenus.get(i);
			d.setRadius(a);
		}

		for (int i=0; i<menus.size(); i++) {
			Menu d = menus.get(i);
			d.setRadius(a);
		}

		for (int i=0; i<sliderBoxes.size(); i++) {
			sliderBox s = sliderBoxes.get(i);
			s.menu.setRadius(a);
		}

	};

	public void setAllRadius(float a) {
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;

		if(title!=null)title.r1 = a;
		if(title!=null)title.r2 = a;

		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setRadius(a);
		}
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;

		if(title!=null) {
			title.r1 = a;
			title.r2 = b;
			title.r3 = c;
			title.r4 = d;
		}

		for (int i=0; i<buttons.size(); i++) {
			Button k = buttons.get(i);
			k.setRadius(a,b,c,d);
		}

		for (int i=0; i<dmenus.size(); i++) {
			Dropdown d1 = dmenus.get(i);
			d1.setRadius(a,b,c,d);
		}

		for (int i=0; i<menus.size(); i++) {
			Menu m = menus.get(i);
			m.setRadius(a,b,c,d);
		}

		for (int i=0; i<sliderBoxes.size(); i++) {
			sliderBox s = sliderBoxes.get(i);
			s.menu.setRadius(a,b,c,d);
		}

	};

	public void setAllRadius(float a,float b,float c,float d) {
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;

		if(title!=null)title.r1 = a;
		if(title!=null)title.r2 = b;

		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setRadius(a,b,c,d);
		}
	};

	public void setAllAllignment(float a,float b,float c,float d) {
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;

		if(title!=null)title.r1 = a;
		if(title!=null)title.r2 = b;

		for (int i=0; i<states.size(); i++) {
			tab s = states.get(i);
			s.setRadius(a,b,c,d);
		}
	};

	public void setAlignment(String s){

		if(s=="CENTER"||s=="center"||s=="Center"){
			if(title!=null){
				title.txoff = (title.w-applet.textWidth(title.label))/2;

			}

			for (int i=0; i<windows.size(); i++) {
				Window w = windows.get(i);
				//w.x = 5
			}

			for (int i=0; i<tables.size(); i++) {
				Table_ t = tables.get(i);
				t.x = 5;
				//(b.w-applet.textWidth(b.label))/2-((b.w-applet.textWidth(b.label))/2)/2
				t.x = (w-t.w)/2;
			}
			for (int i=0; i<textboxes.size(); i++) {
				TextBox t = textboxes.get(i);
				t.x = (w-t.w)/2;
			}

			for (int i=0; i<textareas.size(); i++) {
				TextArea t = textareas.get(i);
				t.x = (w-t.w)/2;
			}

			for (int i=0; i<menus.size(); i++) {
				Menu m = menus.get(i);
				m.x = 5;
				m.x = (w-m.w)/2;
			}

			for (int i=0; i<buttons.size(); i++) {
				Button b = buttons.get(i);
				b.x = (w-b.w)/2;
			}

			for (int i=0; i<sliderBoxes.size(); i++) {
				sliderBox s1 = sliderBoxes.get(i);
				s1.x = (w-s1.w)/2;
				s1.menu.x = (w-s1.w)/2;
			}

			for (int i=0; i<dmenus.size(); i++) {
				Dropdown d = dmenus.get(i);
				d.x = (w-d.w)/2;
				d.txoff = d.w/2-applet.textWidth(d.label)/2;
				for (int j=0; j<dmenus.get(i).items.size(); j++) {
					Button d1 = dmenus.get(i).items.get(j);
					d1.x = (w-d1.w)/2;
					d1.txoff = (d1.w-applet.textWidth(d1.label))/2;
				}
			}
		}

		if(s=="RIGHT"||s=="right"||s=="Right"){

			if(title!=null){
				title.txoff = (title.w-applet.textWidth(title.label))/2-((title.w-applet.textWidth(title.label))/2)/2;

			}

			for (int i=0; i<windows.size(); i++) {
				Window w = windows.get(i);
				//w.x = 5
			}

			for (int i=0; i<tables.size(); i++) {
				Table_ t = tables.get(i);
				t.x = 5;
				t.x = (w-t.w)/2-((w-t.w)/2)/2;
			}
			for (int i=0; i<textboxes.size(); i++) {
				TextBox t = textboxes.get(i);
				t.x = (w-t.w)/2-((w-t.w)/2)/2;
			}

			for (int i=0; i<textareas.size(); i++) {
				TextArea t = textareas.get(i);
				t.x = 5;
				t.x =(int)  ((w-t.w)-((w-t.w))/4);
			}

			for (int i=0; i<menus.size(); i++) {
				Menu m = menus.get(i);
				m.x = 5;
				m.x = (w-m.w)-((w-m.w))/4;
			}

			for (int i=0; i<buttons.size(); i++) {
				Button b = buttons.get(i);
				b.x = (w-b.w)-((w-b.w))/4;
			}

			for (int i=0; i<sliderBoxes.size(); i++) {
				sliderBox s1 = sliderBoxes.get(i);
				s1.x = (w-s1.w)-((w-s1.w))/4;
			}

			for (int i=0; i<dmenus.size(); i++) {
				Dropdown d = dmenus.get(i);
				d.x = (w-d.w)-((w-d.w))/4;
				//for (int j=0; j<dmenus.get(i).items.size(); j++) {
				//  Button d1 = dmenus.get(i).items.get(j);
				//  d1.x = (w-d1.w)-((w-d1.w))/4;
				//}
			}
		}

		if(s=="LEFT"||s=="left"||s=="Left"){

			if(title!=null){
				title.txoff = 5;

			}

			for (int i=0; i<windows.size(); i++) {
				Window w = windows.get(i);
				w.x = 5;
			}

			for (int i=0; i<tables.size(); i++) {
				Table_ t = tables.get(i);
				t.x = 5;
			}
			for (int i=0; i<textboxes.size(); i++) {
				TextBox t = textboxes.get(i);
				t.x = 5;
			}

			for (int i=0; i<textareas.size(); i++) {
				TextArea t = textareas.get(i);
				t.x = 5;
			}

			for (int i=0; i<menus.size(); i++) {
				Menu m = menus.get(i);
				m.x = 5;
			}

			for (int i=0; i<buttons.size(); i++) {
				Button b = buttons.get(i);
				b.x = 5;
			}

			for (int i=0; i<sliderBoxes.size();i++){
				sliderBox s1 = sliderBoxes.get(i);
				s1.x = 5;
			}

			for (int i=0; i<dmenus.size(); i++) {
				Dropdown d = dmenus.get(i);
				d.x = 5;
				d.txoff = 0;
				for (int j=0; j<dmenus.get(i).items.size(); j++) {
					Button d1 = dmenus.get(i).items.get(j);
					d1.x = 5;
					d1.txoff = 0;
				}
			}
		}
	};

	public void selfToggle(int i,int j){
		if(i<menus.size()&&j<menus.get(i).items.size()){
			PVector mouse = getMouse();
			menus.get(i).toggle(j,mouse);

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found");

		}

	};
	

	public void spToggle1(int i,int j,Object o,String s,String[] ss) {
		if(i<menus.size()&&j<menus.get(i).items.size()) menus.get(i).sptoggle(j,o,s,ss,mouse);
	};
	
	public void spToggle(int i,int j,Object o,String s,String[] ss) {
		if(i<menus.size()&&j<menus.get(i).items.size()) menus.get(i).sptoggle(j,o,s,ss,mouse);
	};
	
	public boolean toggle(int i) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<buttons.size())return buttons.get(i).click(mouse);
		else return false;
	};

	public boolean toggle(int i,int j) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<menus.size()&&j<menus.get(i).items.size())return getButton(i,j).click(mouse);
		else return false;
	};

	public boolean toggle(int i,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size())return buttons.get(i).toggleU(o,s,mouse);
		else return false;
	};

	public boolean toggle(int i,int j,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		
		if(i<menus.size()&&j<menus.get(i).items.size())return getButton(i,j).toggleU(o,s,mouse);
		else return false;
	};

	public boolean toggleU(int i) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<buttons.size())return buttons.get(i).click(mouse);
		else return false;
	};

	public boolean toggleU(int i,int j) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<menus.size()&&j<menus.get(i).items.size())return getButton(i,j).click(mouse);
		else return false;
	};

	public boolean toggleU(int i,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size())return buttons.get(i).toggle(o,s,mouse);
		else return false;
	};

	public boolean toggleU(int i,int j,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).toggle(j,o,s,mouse);
		else return false;
	};
	
	public boolean toggleD(int i) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<buttons.size())return buttons.get(i).clickD(mouse);
		else return false;
	};

	public boolean toggleD(int i,int j) {
		if(mouse==null)mouse = getMouse();
		if(mouse!=null&&i<menus.size()&&j<menus.get(i).items.size())return getButton(i,j).clickU(mouse);
		else return false;
	};

	public boolean toggleD(int i,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size())return buttons.get(i).toggleD(o,s,mouse);
		else return false;
	};

	public boolean toggleD(int i,int j,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).toggleD(j,o,s,mouse);
		else return false;
	};

	public void toggleAllButtons(){
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).click(mouse);
		}

	};

	public void toggleMenuButtons(int i){
		if(i<menus.size()) {
			menus.get(i).toggleAll();
		}
	};

	public void toggleAllMenuButtons(){
		for(int i=0;i<menus.size();i++) {
			menus.get(i).toggleAll(mouse);
		}
	};

	public boolean click(int i) {
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size())return getButton(i).clickU(mouse);
		else return false;
	};

	public boolean click(int i,Object o,String s) {
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size())return getButton(i).toggleU(o,s,mouse);
		else return false;
	};

	public boolean click(int i,PVector m) {
		if(m==null)m = getMouse();
		if(i<buttons.size())return getButton(i).clickU(m);
		else return false;
	};

	public boolean click(int i,int j) {
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickU(mouse);
		else return false;
	};

	public boolean click(int i,int j,PVector m) {
		if(m==null)m = getMouse();
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickU(m);
		else return false;
	};

	public boolean clickAll() {
		boolean k = false;
		if(mouse==null)mouse = getMouse();
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).clickU(mouse))k = true;
		}
		return k;
	};

	public boolean clickAll(PVector m) {
		boolean k = false;
		if(m==null)m = getMouse();
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).clickU(m))k = true;
		}
		return k;
	};

	public boolean clickAllMenus() {
		boolean k = false;
		if(mouse==null)mouse = getMouse();
		for(int i=0;i<menus.size();i++) {
			if(menus.get(i).clickAll(mouse))k = true;
		}
		return k;
	};

	public boolean clickAllMenus(PVector m) {
		boolean k = false;
		for(int i=0;i<menus.size();i++) {
			if(menus.get(i).clickAll(m))k = true;
		}
		return k;
	};



	public void cycle(int a,int b){
		if(a<buttons.size())buttons.get(a).cycle(b);
	};

	public void cycle(int a,int b,PVector m){
		if(a<buttons.size())buttons.get(a).cycle(b,m);
	};

	public void cycle(int a,int b,Object o,String s){
		if(a<buttons.size())buttons.get(a).cycle(o,s,b);
	};

	public void cycle(int a,int b,PVector m,Object o,String s){
		if(a<buttons.size())buttons.get(a).cycle(o,s,b,m);
	};

	public void cycle(int a,int b, int c){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(c);
	};

	public void cycle(int a,int b,int c,PVector m){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(c,m);
	};

	public void cycle(int a,int b, int c,Object o,String s){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(o,s,c);
	};

	public void cycle(int a,int b,int c,PVector m,Object o,String s){
		if(a<menus.size()&&b<menus.get(a).items.size())menus.get(a).items.get(a).cycle(o,s,c,m);
	};
	
	public boolean getToggleS(int i,Object o,String s){
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size()){

			Button b = buttons.get(i);

			b.click(o,s,mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};

	public boolean getToggleS(int i,int j,Object o,String s){
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&j<menus.get(i).items.size()){

			Button b = menus.get(i).items.get(j);
			b.click(0,s,mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};
	
	public boolean getToggleS(int i){
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size()){

			Button b = buttons.get(i);

			b.click(mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};

	public boolean getToggleS(int i,int j){
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&j<menus.get(i).items.size()){

			Button b = menus.get(i).items.get(j);
			b.click(mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};

	public boolean getToggle(int i){
		if(mouse==null)mouse = getMouse();
		if(i<buttons.size()){

			Button b = buttons.get(i);

//			b.clickU(mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};

	public boolean getToggle(int i,int j){
		if(mouse==null)mouse = getMouse();
		if(i<menus.size()&&j<menus.get(i).items.size()){

			Button b = menus.get(i).items.get(j);
//			b.click(mouse);
			return b.toggle;

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"button or menu not found.");}
			else if(applet.mousePressed)PApplet.println("tab: button or menu not found.");
			return false;

		}
	};

	public float getValue(int i,int j){
		if(getSlider(i,j)!=null){

			Slider b = sliderBoxes.get(i).menu.sliders.get(j);

			return b.value;

		}else {

			if(title!=null)if(applet.mousePressed){PApplet.println("tab",title.label,"slider or sliderBox not found.");}
			else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");
			return -99999;

		}
	};

	public void setValue(int i,int j,float start,float end){
		if(getSlider(i,j)!=null){

			Slider b = sliderBoxes.get(i).menu.sliders.get(j);

			b.set(start,end);

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
			else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");

		}
	};

	public void setValue(int i,int j,float start,float end,Object o,String field){
		if(getSlider(i,j)!=null){

			Slider b = sliderBoxes.get(i).menu.sliders.get(j);

			b.set(start,end);

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
			else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");

		}
	};

	public void setIntValue(int i,int j,int start,int end){
		if(getSlider(i,j)!=null){

			Slider b = sliderBoxes.get(i).menu.sliders.get(j);

			b.setint(start,end);

		}else {

			if(title!=null){if(applet.mousePressed)PApplet.println("tab",title.label,"slider or sliderBox not found.");}
			else if(applet.mousePressed)PApplet.println("tab: slider or sliderBox not found.");

		}
	};

	public void set(int i,float a,float b) {
		if(i<sliders.size())sliders.get(i).set(a,b);
	};

	public void setInt(int i,int a,int b) {
		if(i<sliders.size())sliders.get(i).setint(a,b);
	};

	public void set(int i,float a,float b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).set(a,b,o,s);
	};

	public void setInt(int i,int a,int b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).setint(a,b,o,s);
	};

	public void set(int i,int j,int a,float b) {
		if(getSlider(i,j)!=null)sliderBoxes.get(i).set(j,a,b);
	};

	public void set(int i,int j,float a,float b) {
		if(getSlider(i,j)!=null)sliderBoxes.get(i).set(j,a,b);
	};

	public void setInt(int i,int j,int a,int b) {
		if(getSlider(i,j)!=null)sliderBoxes.get(i).setint(j,a,b);
	};

	public void set(int i,int j,float a,float b,Object o,String s) {
//		if(applet.mousePressed)applet.println("tab set",o,s);
		if(getSlider(i,j)!=null)sliderBoxes.get(i).set(j,a,b,o,s);
	};

	public void setInt(int i,int j,int a,int b,Object o,String s) {
		if(getSlider(i,j)!=null)sliderBoxes.get(i).setint(j,a,b,o,s);
	};



	public void setBorder() {
		border = true;
	};

	public void setAllBorder(boolean k) {
		border = true;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			b.border = true;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.border = true;
		}
		for(int i=0;i<sliderBoxes.size();i++) {
			sliderBox s = sliderBoxes.get(i);
			s.setAllBorder(k);
		}
		for(int i=0;i<menus.size();i++) {
			Menu m = menus.get(i);
			m.setAllBorder(k);
		}
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.setAllBorder(k);
		}
		for(int i=0;i<states.size();i++) {
			tab b = states.get(i);
			b.setAllBorder(k);
		}
	};

	public void setTransparency(float a) {
		transparency = a;
	};


	public void setAllTransparency(float a) {
		transparency = a;
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			b.transparency = a;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider b = sliders.get(i);
			b.transparency = a;
		}
		for(int i=0;i<menus.size();i++) {
			Menu m = menus.get(i);
			m.setAllTransparency(a);
		}
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.setAllTransparency(a);
		}
		for(int i=0;i<sliderBoxes.size();i++) {
			sliderBox b = sliderBoxes.get(i);
			b.setAllTransparency(a);
		}
		for(int i=0;i<states.size();i++) {
			tab b = states.get(i);
			b.setAllTransparency(a);
		}
	};

	public void setvScroll(float a,float b) {
		sliderv.set(a,b);
	};

	public void sethScroll(float a,float b) {
		sliderh.set(a,b);
	};

	public void setPos(float a,float b) {
		bx = a;
		by = b;
		x = a;
		y = b;
	};

	public void setDraggable(boolean k) {
		for(int i=0;i<states.size();i++) {
			tab t =  states.get(i);
			t.draggable = k;
		}
	};

	public void setAllVisible(boolean k){
		for(int i=0;i<states.size();i++) {
			tab t =  states.get(i);
			t.visible = k;
		}
	};

	public void setAllToggle(boolean k){
		for(int i=0;i<states.size();i++) {
			tab t =  states.get(i);
			t.toggle = k;
		}
	};

	public Button getButton(int i) {
		if(i<buttons.size())return buttons.get(i);
		else return null;
	};

	public Button getButton(int i,int j) {
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).items.get(j);
		else return null;
	};

	public boolean getPos(int i) {
		if(i<buttons.size())return buttons.get(i).pos(getMouse());
		else return false;
	};

	public boolean getPos(int i,int j) {
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).items.get(j).pos(getMouse());
		else return false;
	};

	public sliderBox getSliderBox(int i) {
		if(i<sliderBoxes.size())return sliderBoxes.get(i);
		else return null;
	};

	public Menu getMenu(int i) {
		if(i<menus.size())return menus.get(i);
		else return null;
	};

	public Dropdown getDropDown(int i) {
		if(i<dmenus.size())return dmenus.get(i);
		else return null;
	};

	public Slider getSlider(int i) {
		if(i<sliders.size())return sliders.get(i);
		else return null;
	};

	public Slider getSlider(int i,int j) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).sliders.size())return sliderBoxes.get(i).sliders.get(j);
		else return null;
	};

	public tab getTabByLabel(String s) {
		tab t = null;
		for(int i=0;i<states.size();i++) {
			if(states.get(i).title!=null&&states.get(i).title.label==s) {
				t =  states.get(i);
				state = i;
				break;
			}
		}
		return t;
	};

	public tab getTabByID(int s) {
		if(s<states.size())return states.get(s);
		else return null;
	};

	public tab getState(int s) {
		if(s<states.size())return states.get(s);
		else return null;
	};

	public tab getCurrentTab() {
		if(state<states.size())return states.get(state);
		else return null;
	};

	public void displayImgProcessors(){
		for(int i=0;i<imageProcessors.size();i++){

			Img b = imageProcessors.get(i);
			b.menus();

		}
	};

	public void displaywordVectors(){
		for(int i=0;i<wordVectors.size();i++){

			vectorText b = wordVectors.get(i);
			b.display();

		}
	};

	public void printText(String[] s, float a,float b) {

		canvas.beginDraw();
		canvas.fill(0);
		if(vscroll&&sliderv!=null)b = b - sliderv.value;
		if(hscroll&&sliderh!=null)a = a - sliderh.value;
		for(int i=0;i<s.length;i++) {
			canvas.text(s[i],x + a,y + b);
		}
		canvas.endDraw();
		//		if(applet.mousePressed)applet.println("word vectors display text",s.length);

	};

	public void printText(String[] s, float a,float b,int c) {

		canvas.beginDraw();
		canvas.fill(c);
		if(vscroll&&sliderv!=null)b = b - sliderv.value;
		if(hscroll&&sliderh!=null)a = a - sliderh.value;
		for(int i=0;i<s.length;i++) {
			canvas.text(s[i],x + a,y + b);
		}
		canvas.endDraw();
	};

	public void printText(String s,float a,float b,int c) {

		canvas.beginDraw();
		canvas.fill(c);
		if(vscroll&&sliderv!=null)b = b - sliderv.value;
		if(hscroll&&sliderh!=null)a = a - sliderh.value;
		canvas.text(s,x + a,y + b);
		canvas.endDraw();

	};

	boolean posTab() {
		
		if(parentTab!=null) {
			return mouse.x>x-parentTab.x&&mouse.x<x+w-parentTab.x
					&&mouse.y>y-parentTab.y&&mouse.y<y-parentTab.y;
		}else {
			 return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
		}
	};

	boolean posTab(PVector m) {
		if(parentTab!=null) {
			return m.x>x-parentTab.x&&m.x<x+w-parentTab.x
					&&m.y>y-parentTab.y&&m.y<y-parentTab.y;
		}else {
			return m.x>x&&m.x<x+w&&m.y>y-5&&m.y<y;
		}
	};

	boolean posTabd() {
		if(parentTab!=null) {
			return m.x>x-parentTab.x&&m.x<x+w-parentTab.x
					&&m.y>y-parentTab.y&&m.y<y-parentTab.y;
		}else {
			return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y-5&&applet.mouseY<y;
		}
	};

	boolean posTabd(PVector m) {
		if(parentTab!=null) {
			return m.x>x-parentTab.x&&m.x<x+w-parentTab.x
					&&m.y>y-parentTab.y&&m.y<y-parentTab.y;
		}else {
			return m.x>x&&m.x<x+w&&m.y>y-5&&m.y<y;
		}
	};

	public PVector getMouse(){
		if(parentTab!=null) {
			return new PVector(mouse.x-x-parentTab.x,mouse.y-y-parentTab.y);
		}else {
			if(title==null)return new PVector(applet.mouseX-x,applet.mouseY-y);
			else return new PVector(applet.mouseX-x,applet.mouseY-y-title.h);
		}
	};
	
	public PVector getrMouse(){
		if(title==null)return new PVector(applet.mouseX-x,applet.mouseY-y);
		else return new PVector(applet.mouseX-x,applet.mouseY-y-title.h);
		
	};

	public PVector getMouse(boolean k){
		return new PVector(applet.mouseX-x,applet.mouseY-y);
	};

	PVector getMouse2(){
		 return new PVector(applet.mouseX-x,applet.mouseY-y-title.h);
	};

	public PVector getMouse(PVector m){
		if(title==null)return new PVector(m.x-x,m.y-y);
		else return new PVector(m.x-x,m.y-y-title.h);
	};
	
	public PVector getMouse3(PVector m){
		if(title==null)return new PVector(m.x-x,m.y-y);
		else return new PVector(m.x-x,m.y-y-title.h);
	};

};
