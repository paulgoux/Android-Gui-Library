package androidGui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import processing.core.PApplet;

public class fileInput{
	BMScontrols Bms;
	PApplet applet;
	public String value;
	public String location;
	public boolean click = false,folder,counted,fileSelect,openWindow;
	public float x,y,w,h;
	public Window window;
	public String [] values;
	public File []files;
	public ArrayList<String>fileNames = new ArrayList<String>();
	public HashMap<String, ArrayList<Integer>> extensions = new HashMap<String, ArrayList<Integer>>();

	public fileInput(){
	};

	public fileInput(boolean b){
		folder = true;
	};

	public fileInput(Button b,boolean a){

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
	};

	public fileInput(Button b,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	};

	public fileInput(Button b,boolean a,Object o){

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		
	};

	public fileInput(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
	};

	public fileInput(boolean b,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public fileInput(Button b,boolean a,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public fileInput(Button b,boolean a,Object o,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;

		folder = true;
		window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",bms);
		window.launchable = false;
	};

	public String getFile(){

		String s = value;
		value = null;
		return s;
	};

	public void setLink(Button b){
		x = b.x;
		y = b.y;
		w = b.w;
		h = b.h;
	};

	public String getFolder(){
		String s = value;
		value = null;
		return s;
	};

	public void saveLocation(String location){
		this.location = location;
	};

	public void listen(){
		boolean k = false;
		if(!folder){
			if(click()){
				if(window!=null)window.open = true;
				openWindow = true;
				fileSelect = true;
			}}else {
				if(click()){
					if(window!=null)window.open = true;
					openWindow = true;
					fileSelect = true;
				}}
		
		if(openWindow&&window.open) {
			
			window.windowLogic();
		}
		if(openWindow&&!window.open){
			openWindow = false;
			value = window.currentf;
			applet.println("file input listen location;",value);
		}
		if(window!=null)window.displayGrid();
		if(fileSelect&&value!=null)PApplet.println("Location: " + value);

	};
	
	public void listen(PVector m){
		boolean k = false;
//		if(window==null)window = new Window(200,200,200,200,"C:\\Users\\paul goux\\",Bms);
		if(!folder){
			if(click(m)){
				if(window!=null)window.open = true;
				openWindow = true;
				fileSelect = true;
			}}else {
				if(click(m)){
					if(window!=null)window.open = true;
					openWindow = true;
					fileSelect = true;
				}}
		
		if(openWindow&&window.open) {
			
			window.windowLogic();
		}
		if(openWindow&&!window.open){
			openWindow = false;
			value = window.currentf;
			applet.println("file input listen location;",value);
		}
		if(window!=null)window.displayGrid();
		if(fileSelect&&value!=null)PApplet.println("Location: " + Bms.Location);

	};

	public void listen1(){
		click();
		if(!folder){
			if(click){
				if(window!=null)window.open = true;
				openWindow = true;
				
			}
		}else {
			if(click){
				if(window!=null)window.open = true;
				openWindow = true;
				values = fileUtils.listNames(value);
			}
		}
		
		if(openWindow&&window.open) {
			window.windowLogic();
		}
		if(openWindow&&!window.open){
			openWindow = false;
			value = window.link;
		}
		if(window!=null)window.displayGrid();
	};

	public void listen_(){
		if(!folder) {

			//	    	selectInput("Select a file to process:", "fileSelected");
		}
		else {
			//	    	selectInput("Select a file to process:", "folderSelected");
		}
	};

	public void listExt() {

		if(values!=null&&!counted){
			for(int i=0;i<values.length;i++){

				File f = new File(values[i]);

				String type = fileUtils.getFileExtension(f);
				if(!extensions.containsKey(type)){
					ArrayList<Integer> n = new ArrayList<Integer>();
					n.add(i);
					extensions.put(type,n);
				}else extensions.get(type).add(i);
			}
			counted = true;
		}
	};

	public void getTextFiles(){


	};

	public void getImageFiles(){


	};

	public void getVideoFiles(){


	};

	public void getAudioFiles(){


	};

	public String []getFolder(String location) {

		String []names = fileUtils.listNames(location);

		if(names!=null&&!counted){
			for(int i=0;i<names.length;i++){

				File f = new File(names[i]);

				String type = fileUtils.getFileExtension(f);
				String[] m = PApplet.match(names[i], "ubyte");

				if(m==null){
					if(!extensions.containsKey(type)){
						ArrayList<Integer> n = new ArrayList<Integer>();
						n.add(i);
						extensions.put(type,n);
						fileNames.add(names[i]);
					}else{
						extensions.get(type).add(i);
						fileNames.add(names[i]);
					}}
				else{
					if(!extensions.containsKey("ubyte")){
						ArrayList<Integer> n = new ArrayList<Integer>();
						n.add(i);
						extensions.put("ubyte",n);
						fileNames.add(names[i]);
					}else{
						extensions.get(type).add(i);
						fileNames.add(names[i]);
					}}}}

		String []s = new String[fileNames.size()];

		for(int i=0;i<fileNames.size();i++){
			s[i] = fileNames.get(i);
		}
		return s;
	};

	public boolean click(){
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click){
			click = true;
			k = false;
		}else if(click&&!applet.mousePressed){
			k = true;
			click = false;
		}
		return k;
	};
	
	public boolean click(PVector m){
		boolean k = false;
		if (pos(m)&&applet.mousePressed&&!click){
			click = true;
			k = false;
		}else if(click&&!applet.mousePressed){
			k = true;
			click = false;
		}
		return k;
	};

	public  boolean pos(PVector mouse){
		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};
	
	public  boolean pos(){
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	public void fileSelected(File selection){
		if(selection != null)Bms.File.value = selection.getAbsolutePath();
	};

	public void folderSelected(File selection) {
		if(selection != null){
			Bms.File.value = selection.getAbsolutePath();
			Bms.Location = selection.getAbsolutePath();
		}
	};

	public void selectInput() {

	};

	public void selectFolder() {

	};
	
	public void getDim() {
		
	};
};
