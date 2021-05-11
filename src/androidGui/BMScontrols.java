package androidGui;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import ketai.camera.KetaiCamera;
import processing.core.*;
import processing.opengl.PShader;
import android.Manifest; 
import android.content.pm.PackageManager; 
import android.os.Build; 
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import android.app.Activity;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class BMScontrols{
	public PApplet applet;
	public int Mcount;
	public BMScamera camera;
	public int maxFolderSize = 100,mouseButton;
	public HashMap<Object,String> booleans = new HashMap<Object,String>();
	public int col , bgcol, bcol, tcol, fcol, hcol,toggleCol,tabcol,sliderbgcol,keyCode;
	public float r1,r2,r3,r4,number1,number2,number3,number4,tsize = 12,textSize = 12,borderSize;
	public boolean updated,autoSave,globalDown,debug,borders,terrain3d,showGrid,showBoundaries,showCam,showShapes,
	showWordVectors,showImgProcessors,showMenus = true,showEntities,dockUpdateE,dockUpdateB,dockUpdateI,
	dockUpdateW,dockUpdateA,showNetworks,border,updateKey,keyPressed,getKey,key1,kp;
	public String clipBoard;
	public String currentMouseObject;
	public Object currentObject;
	public Menu menuObject,file,reset,resetDialogue,shapes;
	public Window fmenu;
	public Slider sliderObject;
	//objectSelected;
	public Dropdown dropDownObject;
	public String[] Lines;
	public Button yes,no;
	public Dock dock;
	public tab resetDialogueTab,themeSettings;
	public float autoSaveTimeout = 30,transparency;
	public String absolutePath;
	public PFont font;
	char key;

	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Quad> qgrid = new ArrayList<Quad>();
	public ArrayList<Boundary> boundaries = new ArrayList<Boundary>();

	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<Menu> menus = new ArrayList<Menu>();
	public ArrayList<Dropdown> dmenus = new ArrayList<Dropdown>();

	public ArrayList<Slider> sliders = new ArrayList<Slider>();
	public ArrayList<sliderBox> sliderBoxes = new ArrayList<sliderBox>();

	public ArrayList<Grid> grids = new ArrayList<Grid>();
	public ArrayList<Table_> tables = new ArrayList<Table_>();

	public ArrayList<PImage> images = new ArrayList<PImage>();
	public ArrayList<String> links = new ArrayList<String>();

	public ArrayList<Network> neuralNetworks = new ArrayList<Network>();

	public ArrayList<textBlock> textBlocks = new ArrayList<textBlock>();
	public ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();
	public ArrayList<TextArea> textAreas = new ArrayList<TextArea>();

	public ArrayList<Scene> scenes = new ArrayList<Scene>();
	public ArrayList<Window> windows = new ArrayList<Window>();
	public ArrayList<tab> tabs = new ArrayList<tab>();

	public ArrayList<Img> imageProcessors = new ArrayList<Img>();
	public ArrayList<vectorText> wordVectors = new ArrayList<vectorText>();

	public Boundary bb;
	public Window main;
	public fileInput File,Folder;
	public Menu menu;
	public String Location;
	//String[] cameras = Capture.list();
	public String[] files;

	//Capture Cam;
	public Dropdown dd;


	public BMScontrols(PApplet applet) {
		this.applet = applet;
//		font = applet.createFont("LeelawadeeUI-12.vlw");
		initColors();
	};

	public BMScontrols(PApplet applet,boolean k) {
		this.applet = applet;
//		font = applet.loadFont("LeelawadeeUI-12.vlw");
		initColors();
		begin();
	};

	public BMScontrols(PApplet applet,boolean k,boolean k1) {
		this.applet = applet;
		font = applet.loadFont("Dialog.plain-48.vlw");
		initColors();
		begin();
		createCamera();
	};

	public void initColors(){
		
		absolutePath = new String(Environment.getExternalStorageDirectory().getAbsolutePath()); 
		applet.println("bms absolutePath",absolutePath);
		col = applet.color(0, 255, 73);
		bgcol = applet.color(255);
		bcol = applet.color(255);
		tcol = applet.color(0); 
		fcol = applet.color(0, 255, 73);
		hcol = applet.color(0, 255, 73,100);
		toggleCol = applet.color(55, 164, 160);
		tabcol = applet.color(0, 150, 255);
		sliderbgcol = applet.color(255);
	};

	public void add(Object o,String globalVar,boolean localVar){

		booleans.put(o,globalVar);

	};

	public void draw(){
		// fill(255);

		// text("hello",100,100);
	};

	public void begin(){
		//	      PApplet.println("bms",applet);
		File = new fileInput(this);
		Folder = new fileInput(true,this);
		setupDock();
		if(debug)PApplet.println("dock");
		setupReset();
		if(debug)PApplet.println("reset");
		setupWindows();
		if(debug)PApplet.println("windows");
		setupMenus();
		if(debug)PApplet.println("menus");
		setupTabs();
		if(debug)PApplet.println("menus");
	};

	public void setupTabs() {
		if(debug)applet.println("bms setupt 00");
		themeSettings = new tab(0,200,200,400,"Theme",this);
		String [] s1 = {"red","green","blue"};
		float [] v1 = {52, 235, 225};
		sliderBox sl2 = new sliderBox(50,40,90,90,10,s1,v1,this);
		if(debug)applet.println("bms setupm 02");
		sl2.menu.draggable = false;
		sl2.tooltip = null;
		sl2.setPieSquare();
		sl2.setStart(0);
		sl2.setEnd(255);
		themeSettings.setvScroll();
		themeSettings.draggable = true;
		themeSettings.add(sl2);
		if(debug)applet.println("bms setupm 03");
		dock.add(themeSettings);
		add(themeSettings);
		if(debug)applet.println("bms setupm 04");
	};


	public void setupDock(){
		dock = new Dock(0,applet.height -22,applet.width,24,this);

	};


	public void setupWindows(){
		Boundary b = new Boundary(this);
		if(debug)PApplet.println("setupWindows 0");
		main = new Window(23,20,applet.width-46,applet.height-90-20,this);
		main.init(this);
		applet.println("Bms setupw 00" ,b);
		if(debug)PApplet.println("setupWindows 1");
		fmenu = new Window(200,200,200,200,absolutePath,this);
		if(debug)PApplet.println("setupWindows 2");
		fmenu.setRadius(10);
		PApplet.println("setupWindows 3");
		fmenu.rapidAccess = true;
		PApplet.println("setupWindows 4");
		windows.add(fmenu);
		PApplet.println("setupWindows 4.1");
		dock.add(fmenu);
		PApplet.println("setupWindows 4.2");
		PApplet.println("setupWindows 5");
		PApplet.println(fmenu);
	};

	public void setupMenus(){
		if(debug)applet.println("bms setupm 00");
		String [] flabels = {"Settings","Camera","Window","Reset"};
		file = new Menu(20,0,50,70,"File",flabels,this);

		menus.add(file);
		if(debug)applet.println("bms setupm 00");

		//----------------------file -----------------------------------
		if(file!=null){
			String []glabels = {"Terrain","Img","Cam","Video","Audio","Text"};
			file.items.get(0).submenu  = new Menu(file.items.get(0).x+file.items.get(0).w,file.items.get(0).y,70,glabels,this);
			file.setLink(0);
		}
		if(debug)applet.println("bms setupm 00");
	};


	public void setupReset(){
		resetDialogue = new Menu(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);

		resetDialogue.visible = true;
		resetDialogue.highlightable = false;
		resetDialogue.free = true;
		yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
		yes.border = false;
		yes.toggleBox = true;
		yes.Bms = this;
		yes.applet = applet;
		no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
		no.Bms = this;
		no.applet = applet;
		no.border = false;
		no.toggleBox = true;
		resetDialogue.add(yes);
		resetDialogue.add(no);
		resetDialogue.setBms(this);
	};

	public void setupReset2(){
		resetDialogueTab = new tab(applet.width/2 - 136,applet.height/2 - 22,275,45,"Are you sure you want to exit?",this);

		resetDialogue.highlightable = false;
		resetDialogue.free = true;
		yes = new Button(applet.width/2 - 136 ,applet.height/2 +2,resetDialogue.w/2,20,"Yes",this);
		yes.border = false;
		yes.toggleBox = true;
		yes.Bms = this;
		yes.applet = applet;
		no = new Button(yes.x + yes.w,applet.height/2 +2,resetDialogue.w/2,20,"No",this);
		no.Bms = this;
		no.applet = applet;
		no.border = false;
		no.toggleBox = true;

		resetDialogue.items.add(yes);
		resetDialogue.items.add(no);
		yes.parent = resetDialogue;
		no.parent = resetDialogue;
	};


	public void loadImg(){

	};

	public void drawWindows(){
		for(int i=0;i<windows.size();i++){

			Window r = windows.get(i);
			r.displayGrid();

		}
	};

	public void drawGrids(){
		//		  if(debug&&applet.mousePressed)PApplet.println("bms displayGrid",grids.get(0));
		for(int i=0;i<grids.size();i++){

			Grid g = grids.get(i);
			g.grid_functions();

		}
	};

	public void drawBoundaries(){
		if(debug&&applet.mousePressed)PApplet.println("bms displayGrid",boundaries.get(0));
		for(int i=0;i<boundaries.size();i++){

			Boundary b = boundaries.get(i);
			if(b.type==-1)b.draw();
			else b.draw2();

		}
	};

	public void drawImgp(){
		for(int i=0;i<imageProcessors.size();i++){

			Img b = imageProcessors.get(i);
			b.menus();

		}
	};

	public void displayWordVectors(){
		for(int i=0;i<wordVectors.size();i++){

			vectorText b = wordVectors.get(i);
			b.display();

		}
	};

	public void createCamera() {
		camera = new BMScamera(this,1440,720);
	};
	
	public void keyBoardLogic() {
		if(key1&&getKey&&!applet.mousePressed){
		      getKey = false;
//		      key += applet.key;
		      kp = true;
		    }else {
//		      key = "";
		      kp = false;
		      key1 = false;
		    }
		    if(kp)keyPressed = true;
		    else keyPressed = false;
	};

	public void run(){
		toggle(0,0,themeSettings,"toggle");
		toggle(0,2,fmenu,"open");

		if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
		themeFunctions();
		applet.background(bgcol);
		globalLogic();
		drawEntities();
		drawButtons();
		mainFunctions();
		if(showGrid)drawGrids();
		if(showBoundaries)drawBoundaries();
		drawTextBoxes();
		drawSliderBoxes();
		drawSliders();
		drawDropDowns();
		drawTabs();


		for(Menu menu : menus)menu.click();

		dock.logic();

		if(camera!=null&&getToggle(0,1)){
			camera.displayCam();
			camera.camLogic();
		}
		//		  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();

		drawMenus();
		drawWindows();
		dock.drawItems();

	};

	public void drawDropDowns() {
		for(int i=0;i<dmenus.size();i++) {
			Dropdown d = dmenus.get(i);
			d.applet = applet;
			d.displayDropdown();
		}
	};

	public void run(int i){
		mouseButton = i;
		keyBoardLogic();
		toggle(0,0,themeSettings,"toggle");
		toggle(0,2,fmenu,"open");
		themeSettings.setvScroll(0, 200);
		if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
		themeFunctions();
		applet.background(bgcol);
		if(debug)PApplet.println("bms display 01");
		globalLogic();

		if(debug)PApplet.println("bms display 2");
		if(showGrid)drawGrids();
		if(debug)PApplet.println("bms display 3");

		if(showBoundaries) {
			drawBoundaries();
			if(dock.getItem("sliders")!=null) {
				dockUpdateB = true;
			}
		}else if(dockUpdateB) {
			//			dock.removeItem("sliders");
			dockUpdateB = false;
		}
		if(showEntities) {
			drawEntities();
			if(dock.getItem("sliders")!=null) {
				dockUpdateE = true;
			}
		}else if(dockUpdateE) {
			dock.removeItem("sliders");
			dockUpdateE = false;
		}
		if(showNetworks)drawNetworks();
		if(debug)PApplet.println("bms display 04");
		if(showWordVectors)displayWordVectors();
		if(debug)PApplet.println("bms display 4.5");
		if(showImgProcessors)drawImgp();
		if(debug)PApplet.println("bms display 4.5");
		drawButtons();
		if(debug)PApplet.println("bms display 05");
		mainFunctions();
		if(debug)PApplet.println("bms display 06");
		drawTextBoxes();
		if(debug)PApplet.println("bms display 07");
		drawSliderBoxes();
		if(debug)PApplet.println("bms display 08");
		drawSliders();
		if(debug)PApplet.println("bms display 09");
		drawDropDowns();
		if(debug)PApplet.println("bms display 10");
		drawTabs();
		if(debug)PApplet.println("bms display 11");
		for(Menu menu : menus)menu.click();

		dock.logic();

		if(camera!=null&&getToggle(0,1)){
			camera.displayCam();
			camera.camLogic();
		}
		//			  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();

		if(showMenus)drawMenus();
		if(debug)PApplet.println("bms display 13");
		drawWindows();
		if(debug)PApplet.println("bms display 14");
		dock.drawItems();
		if(debug)PApplet.println("bms display 16");

		if(!applet.mousePressed)keyPressed = false;
	};

	public void run(int i,char j,int k){
		key = j;
		keyCode = k;
		toggle(0,0,themeSettings,"toggle");
		toggle(0,2,fmenu,"open");
		themeSettings.setvScroll(0, 200);
		if(camera!=null&&camera.settings!=null)toggle(0,1,camera.settings,"toggle");
		themeFunctions();
		applet.background(bgcol);
		if(debug)PApplet.println("bms display 01");
		globalLogic();

		if(debug)PApplet.println("bms display 2");
		if(showGrid)drawGrids();
		if(debug)PApplet.println("bms display 3");

		if(showBoundaries) {
			drawBoundaries();
			if(dock.getItem("sliders")!=null) {
				dockUpdateB = true;
			}
		}else if(dockUpdateB) {
			//			dock.removeItem("sliders");
			dockUpdateB = false;
		}
		if(showEntities) {
			drawEntities();
			if(dock.getItem("sliders")!=null) {
				dockUpdateE = true;
			}
		}else if(dockUpdateE) {
			dock.removeItem("sliders");
			dockUpdateE = false;
		}
		if(showNetworks)drawNetworks();
		if(debug)PApplet.println("bms display 04");
		if(showWordVectors)displayWordVectors();
		if(debug)PApplet.println("bms display 4.5");
		if(showImgProcessors)drawImgp();
		if(debug)PApplet.println("bms display 4.5");
		drawButtons();
		if(debug)PApplet.println("bms display 05");
		mainFunctions();
		if(debug)PApplet.println("bms display 06");
		drawTextBoxes();
		if(debug)PApplet.println("bms display 07");
		drawSliderBoxes();
		if(debug)PApplet.println("bms display 08");
		drawSliders();
		if(debug)PApplet.println("bms display 09");
		drawDropDowns();
		if(debug)PApplet.println("bms display 10");
		drawTabs();
		if(debug)PApplet.println("bms display 11");
		for(Menu menu : menus)menu.click();

		dock.logic();

		if(camera!=null&&getToggle(0,1)){
			camera.displayCam();
			camera.camLogic();
		}
		//			  else if(camera!=null&&camera.cam.isFlashEnabled())camera.cam.disableFlash();

		if(showMenus)drawMenus();
		if(debug)PApplet.println("bms display 13");
		drawWindows();
		if(debug)PApplet.println("bms display 14");
		dock.drawItems();
		if(debug)PApplet.println("bms display 16");
		mouseButton = i;
	};


	public void toggleAllButtons(){
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).clickU();
		}

	};

	public void toggleMenuButtons(int i){
		if(i<menus.size())menus.get(i).toggleAll();
	};

	public void toggleAllMenuButtons(){
		for(int i=0;i<menus.size();i++) {
			menus.get(i).toggleAll();
		}
	};

	public void toggle(int i){
		if(i<=buttons.size())buttons.get(i).clickU();
	};

	public void toggle(int i,PVector m){
		if(i<=buttons.size())buttons.get(i).clickU(m);
	};

	public void toggle(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggleU(j);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};

	public void toggle(int i,int j,PVector m){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggleU(j,m);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};


	public void toggle(int i,Object o,String s){
		if(i<=buttons.size())buttons.get(i).toggleU(o,s);
	};

	public void toggle(int i,int j,Object o,String s){
		if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).toggleU(j,o,s);
	};
	
	public void toggleD(int i){
		if(i<=buttons.size())buttons.get(i).clickD();
	};

	public void toggleD(int i,PVector m){
		if(i<=buttons.size())buttons.get(i).clickD(m);
	};

	public void toggleD(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggleD(j);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};

	public void toggleD(int i,int j,PVector m){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			menus.get(i).toggleD(j,m);
		}else {
			PApplet.println("BMS: button or menu not found..");
		}
	};


	public void toggleD(int i,Object o,String s){
		if(i<=buttons.size())buttons.get(i).toggleU(o,s);
	};

	public void toggleD(int i,int j,Object o,String s){
		if(i<=menus.size()&&j<menus.get(i).items.size())menus.get(i).toggleD(j,o,s);
	};

	public void drawButtons(){
		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);

			b.draw();
		};
	};


	public void themeFunctions() {
		if(themeSettings!=null) {
			Menu m1 = themeSettings.sliderBoxes.get(0).menu;
			Slider r = m1.sliders.get(0);
			Slider g = m1.sliders.get(1);
			Slider b = m1.sliders.get(2);
			bgcol = applet.color(r.value,g.value,b.value);
		}
	};

	public void mainFunctions(){
		if(debug)PApplet.println("bms mainf 01");
		fmenu.displayGrid();
		if(debug)PApplet.println("mainf 02");
		if(showShapes)main.renderPublic();
		if(debug)PApplet.println("mainf 03");
	};

	public void drawTabs(){
		for(int i=0;i<tabs.size();i++){
			tab t = tabs.get(i);

			t.displayTab();
		};
	};

	public void drawEntities(){
		for(int i=0;i<entities.size();i++){
			Entity t = entities.get(i);
			t.buttonLogic();
			t.runh();
			t.life();
		}
	};

	public void drawTextBoxes(){
		for(int i=0;i<textBoxes.size();i++){
			TextBox t = textBoxes.get(i);
			t.draw();
		}
	};

	public void displayTextBlocks(){
		for(int i=0;i<textBlocks.size();i++){
			textBlock t = textBlocks.get(i);
			t.draw();
		}
	};

	public void drawTextareas() {
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.draw(true);
		}
	};

	public void manageClipBoard(){
		//if(getTextFromClipboard ()!=clipBoard)clipBoard = getTextFromClipboard ();
	};



	public void reload(){
		if(buttons.get(0).toggle&&!updated){
			applet.frameCount = -1;
			updated = true;
		}
	};

	public void buttonLogic(){

		file.toggleU(6);
		yes.clickU();
		no.clickU();
		String [] gridm = {"forward","backward","pause"};
		Menu grid = file.items.get(2).submenu;
		Menu attractor = file.items.get(5).submenu;



		//----------Shapes------------------------
		String [] shapes_bool = {"toggle","connectedlines","circle","square","tri","bezier","none"};

		// for(int i=0;i<shapes_bool.length;i++){
		//   shapes.sptoggle(i,main,shapes_bool[i],shapes_bool);
		// }
		//shapes.sptoggle(main,shapes_bool);
		shapes.sptoggle(0,main,"toggle",shapes_bool);
		shapes.sptoggle(1,main,"connectedlines",shapes_bool);
		shapes.sptoggle(2,main,"circle",shapes_bool);
		shapes.sptoggle(3,main,"square",shapes_bool);
		shapes.sptoggle(4,main,"tri",shapes_bool);
		shapes.sptoggle(5,main,"poligon",shapes_bool);
		shapes.sptoggle(6,main,"complex",shapes_bool);
		shapes.sptoggle(7,main,"bezier",shapes_bool);
		shapes.sptoggle(8,main,"spline",shapes_bool);
		shapes.sptoggle(9,main,"none",shapes_bool);
		shapes.sptoggle(11,main,"path",shapes_bool);

		String [] ld = {"draw","edit"};

		main.pallete.toggleU(0,main,"fill");
		main.pallete.toggleU(1,main,"gravity");
		main.pallete.toggleU(2,main,"friction");
		main.pallete.toggleU(3,main,"velocity");
		main.pallete.toggleU(4,main,"connect");
		main.pallete.toggleU(5,main,"amendBoundary");
		main.pallete.toggleU(6,main,"amendInnerBoundary");
		main.pallete.toggleU(7,main,"amendAvoidance");
		main.pallete.toggleU(8,main,"amendCohesion");
		main.pallete.toggleU(9,main,"followBoundary");
		main.pallete.toggleU(10,main,"follow");
		main.pallete.toggleU(11,main,"dashed");
		main.pallete.toggleU(12,main,"border");
		main.pallete.toggleU(13,main,"amendAvoidance");
		main.pallete.toggleU(14,main,"showgrid");
		main.pallete.toggleU(15,main,"hidemenu");
		main.pallete.sptoggle(16,main,"edit",ld);
		main.pallete.toggleU(17,main,"reset");
		//main.complexsub.toggle(0,main,"cfinish");

	};

	public void drawSliderBoxes(){
		for(int i=0;i<sliderBoxes.size();i++){

			sliderBox s = sliderBoxes.get(i);
			if(s.visible)s.draw();
			//s.menu.drawSliders();

		}
	};

	public void drawSliders(){
		for(int i=0;i<sliders.size();i++){

			Slider s = sliders.get(i);
			s.draw();
			s.mouseFunctions();
		}
	};


	public void drawMenus(){

		Mcount = 0;
		for(int i=0;i<menus.size();i++){

			Menu m = menus.get(i);
			m.draw();
		}
	};

	public void boundariesNscenes(){

		//------------------------------------------------------
		//Boundaries--------------------------------------------
	};

	public void drawNetworks() {
		for(int i=0;i<neuralNetworks.size();i++){

			Network n = neuralNetworks.get(i);
			n.run(100);
		}

	};


	public void autoSave(){

		if(autoSave){
			saveText();
		}

	};

	public void load(){

	};

	public void saveText(){

		for(int i=0;i<textBoxes.size();i++){
			TextBox t = textBoxes.get(i);

			t.save();
		}

		for(int i=0;i<textAreas.size();i++){
			TextArea t = textAreas.get(i);

			t.save();
		}
	};

	public void saveSliderBox(){

		for(int i=0;i<sliderBoxes.size();i++){
			sliderBox s = sliderBoxes.get(i);

			s.save();
		}
	};

	public void saveButtons(){

		for(int i=0;i<buttons.size();i++){
			Button b = buttons.get(i);

			b.save();
		}
	};

	public void saveMenu(){

		for(int i=0;i<menus.size();i++){
			Menu m = menus.get(i);

			m.save();
		}

		for(int i=0;i<textAreas.size();i++){
			TextArea t = textAreas.get(i);

			t.save();
		}
	};


	public void saveTabs(){

		for(int i=0;i<tabs.size();i++){
			tab t = tabs.get(i);

			t.save();
		}

		for(int i=0;i<tabs.size();i++){
			tab t = tabs.get(i);

			t.save();
		}
	};

	public void saveImgp(){

		for(int i=0;i<textBoxes.size();i++){
			TextBox t = textBoxes.get(i);

			t.save();
		}
	};

	public void save(){

	};

	public void start(Object o,boolean localVar){
		Field field = null;

		String s = "hello";

		try{
			//field = o.getClass().getfield(s);
			field = applet.getClass().getField(s); 
		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}}

	public void eventListener(){

	};

	public void toggle(Object o,String globalVar,boolean localVar){
		Field field = null;

		try{

			field = applet.getClass().getField(globalVar);


		}catch (NullPointerException e) {
		}catch (NoSuchFieldException e) {
		}

	};

	public void globalLogic(){
		//if(dropDownObject!=null)applet.println(dropDownObject.label);
		if(dropDownObject!=null&&!dropDownObject.dclick){
			dropDownObject = null;
		}
		if(applet.mousePressed) globalDown = true;
		else {
			currentMouseObject = null;
			currentObject = null;
			globalDown = false;
		}

	};

	public boolean getToggle(int i){

		if(i<buttons.size()){
			Button b = buttons.get(i);

			if(b.toggle)return true;
			else return false;
		}else {
			PApplet.println("BMS: menu not found");
			return false;
		}
	};

	public boolean getToggle(int i,int j){

		if(i<menus.size()&&j<menus.get(i).items.size()){

			Button b = menus.get(i).items.get(j);

			if(b.toggle)return true;
			else return false;

		}else {

			PApplet.println("BMS: button or menu not found.");
			return false;

		}
	};

	public Button add(int i,Button b){
		//clear();
		b.Bms = this;
		b.applet = applet;
		buttons.add(b);
		return b;
	};

	public Menu add(int i,Menu m){
		//clear();
		m.Bms = this;
		m.applet = applet;
		menus.add(m);
		return m;
	};

	public Dropdown add(int i,Dropdown d){
		//clear();
		d.Bms = this;
		d.applet = applet;
		dmenus.add(d);
		return d;
	};

	public TextBox add(int i,TextBox t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textBoxes.add(t);
		return t;
	};

	public TextArea add(int i,TextArea t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textAreas.add(t);
		return t;
	};

	public textBlock add(int i,textBlock t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textBlocks.add(t);
		return t;
	};

	public String add(int i,String s){
		//clear();
		// tab k = states.get(i);
		// textblock.add(s);
		return s;
	};

	public Table_ add(int i,Table_ t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		tables.add(t);
		return t;
	};

	public Button add(Button b){
		//clear();
		b.Bms = this;
		b.applet = applet;
		buttons.add(b);
		return b;
	};

	public Menu add(Menu m){
		//clear();
		m.Bms = this;
		m.applet = applet;
		//		    for(int i=0;i<m.items.size();i++) {
		//		    	Button b1 = m.items.get(i);
		//		    	b1.setBms(this);
		//		    }
		//		    for(int i=0;i<m.items.size();i++) {
		//		    	Slider b1 = m.sliders.get(i);
		//		    	b1.applet = applet;
		//		    	b1.Bms = this;
		//		    	b1.initColors();
		//		    }
		menus.add(m);
		return m;
	};

	public Entity add(Entity e){
		e.setBms(this);
		entities.add(e);

		return e;
	};

	public Dropdown add(Dropdown d){
		//clear();
		d.setBms(this);
		dmenus.add(d);
		return d;
	};

	public Network add(Network n) {
		neuralNetworks.add(n);
		return n;
	};

	public TextBox add(TextBox t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textBoxes.add(t);
		return t;
	};

	public TextArea add(TextArea t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		textAreas.add(t);
		return t;
	};

	public String add(String s){
		//clear();
		//textBlocks.add(s);
		return s;
	};

	public Table_ add(Table_ t){
		//clear();
		t.Bms = this;
		t.applet = applet;
		tables.add(t);

		return t;
	};

	public sliderBox add(sliderBox s){
		s.setBms(this);
		sliderBoxes.add(s);

		return s;
	};

	public Slider add(Slider s){
		s.setBms(this);
		sliders.add(s);

		return s;
	};

	public tab add(tab t){
		tabs.add(t);
		if(t.title!=null)PApplet.println("bms",t.title.label);
		return t;
	};

	public Window add(Window s){
		s.Bms = this;
		s.applet = applet;
		windows.add(s);
		return s;
	};

	public Grid add(Grid s){
		s.Bms = this;
		s.applet = applet;
		grids.add(s);
		dock.add(s.sliderSettings);
		return s;
	};

	public Boundary add(Boundary b){
		b.Bms = this;
		b.applet = applet;
		boundaries.add(b);
		return b;
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

	public void setRadius(float a){
		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
		if(dock!=null)dock.setRadius(a);
		if(fmenu!=null)fmenu.setRadius(a);

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
			if(s.tooltip!=null)s.tooltip.setRadius(a);
		}

		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			t.setRadius(a);
		}
		for (int i=0; i<textBlocks.size(); i++) {
			textBlock t = textBlocks.get(i);
			t.setRadius(a);
		}
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.setRadius(a);
		}
		for (int i=0; i<textBoxes.size(); i++) {
			TextBox t = textBoxes.get(i);
			t.setRadius(a);
		}
	};

	public void setRadius(float a,float b,float c,float d){
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;

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
			if(s.tooltip!=null)s.tooltip.setRadius(a,b,c,d);
		}

		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			t.setRadius(a);
		}

		for (int i=0; i<textBlocks.size(); i++) {
			textBlock t = textBlocks.get(i);
			t.setRadius(a,b,c,d);
		}
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.setRadius(a,b,c,d);
		}
		for (int i=0; i<textBoxes.size(); i++) {
			TextBox t = textBoxes.get(i);
			t.setRadius(a,b,c,d);
		}
	};

	public void set(int i,float a,float b) {
		if(i<sliders.size())sliders.get(i).set(a,b);
	};

	public void setInt(int i,int a,int b) {
		if(i<sliders.size())sliders.get(i).setint(a,b);
	};

	public void set(int i,float a,float b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).set(a,b,0,s);
	};

	public void setInt(int i,int a,int b,Object o,String s) {
		if(i<sliders.size())sliders.get(i).setint(a,b,o,s);
	};

	public void set(int i,int j,float a,float b) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b);
	};

	public void setInt(int i,int j,int a,int b) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b);
	};

	public void set(int i,int j,float a,float b,Object o,String s) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).set(a,b,0,s);
	};

	public void setInt(int i,int j,int a,int b,Object o,String s) {
		if(i<sliderBoxes.size()&&j<sliderBoxes.get(i).menu.sliders.size())sliderBoxes.get(i).menu.sliders.get(j).setint(a,b,o,s);
	};


	public void setAllBorder(boolean k) {
		for(int i=0;i<buttons.size();i++) {
			Button b = buttons.get(i);
			b.border = k;
		}
		for(int i=0;i<sliders.size();i++) {
			Slider s = sliders.get(i);
			s.border = k;
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
		for(int i=0;i<tabs.size();i++) {
			tab b = tabs.get(i);
			b.setAllBorder(k);
		}
	};


	public void setTransparency(float a) {
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
		for(int i=0;i<tabs.size();i++) {
			tab b = tabs.get(i);
			b.setAllTransparency(a);
		}
	};

	public void setLabel(int i,String s) {
		if(i<buttons.size())buttons.get(i).label = s;
	};

	public void setMenuLabel(int i,String s) {
		if(i<menus.size())menus.get(i).label = s;
	};

	public void setTabLabel(int i,String s) {
		if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = s;
	};

	public void removeTabLabel(int i,String s) {
		if(i<tabs.size()&&tabs.get(i).title!=null)tabs.get(i).title.label = "";
	};

	public void setTabCol(int i,int c) {
		if(i<tabs.size()&&tabs.get(i).title!=null) {
			tabs.get(i).tabcol = c;
			tabs.get(i).localTheme = true;
		}
	};

	public void setDropdownLabel(int i,String s) {
		if(i<dmenus.size()&&dmenus.get(i).title!=null)tabs.get(i).title.label = s;
	};

	public void setTabvScroll(int i,float a,float b) {
		if(tabs.size()>i&&tabs.get(i).sliderv!=null)tabs.get(i).sliderv.set(a,b);
	};

	public void setTabhScroll(int i,float a,float b) {
		if(tabs.size()>i&&tabs.get(i).sliderh!=null)tabs.get(i).sliderh.set(a,b);
	};

	public void setShader(PShader shader) {
		camera.shader = shader;
	};

	public void readCam() {
		camera.read();
	};

	public tab getTab(int i) {
		tab t = null;
		if(i<tabs.size())t =  tabs.get(i);
		return t;
	};

	public tab getTabByLabel(String s) {
		tab t = null;
		for(int i=0;i<tabs.size();i++) {
			if(tabs.get(i).title!=null&&tabs.get(i).title.label==s) {
				t =  tabs.get(i);
				break;
			}
		}
		return t;
	};

	public Boundary getBoundary(int i) {
		Boundary t = null;
		if(i<menus.size())t =  boundaries.get(i);
		return t;
	};

	public Img getImgp(int i) {
		Img t = null;
		if(i<imageProcessors.size())t =  imageProcessors.get(i);
		return t;
	};

	public vectorText getWordVector(int i) {
		vectorText t = null;
		if(i<wordVectors.size())t =  wordVectors.get(i);
		return t;
	};

	public Menu getMenu(int i) {
		Menu t = null;
		if(i<menus.size())t =  menus.get(i);
		return t;
	};

	public Dropdown getdMenu(int i) {
		Dropdown t = null;
		if(i<dmenus.size())t =  dmenus.get(i);
		return t;
	};



	public Button getButtons(int i) {
		Button t = null;
		if(i<buttons.size())t =  buttons.get(i);
		return t;
	};

	public Table_ getTable(int i) {
		Table_ t = null;
		if(i<tables.size())t =  tables.get(i);
		return t;
	};

	public TextBox getTextBox(int i) {
		TextBox t = null;
		if(i<textBoxes.size())t =  textBoxes.get(i);
		return t;
	};

	public TextArea getTextArea(int i) {
		TextArea t = null;
		if(i<textAreas.size())t =  textAreas.get(i);
		return t;
	};

	public void initShapes() {
		Boundary b = new Boundary(this);
		main.Boundaries.add(b);
	};

	public void cycle(int a,int b){
		if(a<buttons.size())buttons.get(a).cycle(b);
	};

	public void cycle(int a,int b,PVector m){
		if(a<buttons.size())buttons.get(a).cycle(b,m);
	};

	public void cycle(int a,int b,Object o,String s){
		if(a<buttons.size()) {
			buttons.get(a).cycle(o,s,b);
			if(applet.mousePressed)PApplet.println("bms cycle object",a,b);
		}else if(applet.mousePressed)PApplet.println("bms cycle object no such button");

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

	public boolean click(int i) {
		if(i<buttons.size())return getButton(i).clickU();
		else return false;
	};

	public boolean click(int i,int j) {
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickU();
		else return false;
	};

	public boolean click(int i,PVector m) {
		if(i<buttons.size())return getButton(i).clickU(m);
		else return false;
	};

	public boolean click(int i,int j,PVector m) {
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickU(m);
		else return false;
	};
	
	public boolean clickD(int i) {
		if(i<buttons.size())return getButton(i).clickD();
		else return false;
	};

	public boolean clickD(int i,int j) {
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickD();
		else return false;
	};

	public boolean clickD(int i,PVector m) {
		if(i<buttons.size())return getButton(i).clickD(m);
		else return false;
	};

	public boolean clickD(int i,int j,PVector m) {
		if(i<menus.size()&&getButton(i,j)!=null)return getButton(i,j).clickD(m);
		else return false;
	};

	public boolean clickAll() {
		boolean k = false;
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).clickU())k = true;
		}
		return k;
	};

	public boolean clickAll(PVector m) {
		boolean k = false;
		for(int i=0;i<buttons.size();i++) {
			if(buttons.get(i).clickU(m))k = true;
		}
		return k;
	};

	public boolean clickAllMenus() {
		boolean k = false;
		for(int i=0;i<menus.size();i++) {
			if(menus.get(i).clickAll())k = true;
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

	public Button getButton(int i) {
		if(i<buttons.size())return buttons.get(i);
		else return null;
	};

	public Button getButton(int i,int j) {
		if(i<menus.size()&&j<menus.get(i).items.size())return menus.get(i).items.get(j);
		else return null;
	};

	public void getKeys() {
		for (int i=0; i<textAreas.size(); i++) {
			TextArea t = textAreas.get(i);
			t.getKey();
		}
		for (int i=0; i<tabs.size(); i++) {
			tab t = tabs.get(i);
			for (int j=0; j<t.textareas.size(); j++) {
				TextArea t1 = t.textareas.get(j);
				t1.getKey();
			}

		}
	};

	public void keyPressed() {
		if(!getKey){
			key1 = true;
			getKey = true;

		}
	};
};