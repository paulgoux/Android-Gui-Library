package androidGui;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.Amplitude;
import processing.sound.AudioIn;
import processing.sound.FFT;
import processing.sound.SoundFile;

public class audioData{
	public BMScontrols Bms;
	public PApplet applet;
	public PApplet app;

	public Amplitude amp;
	public SoundFile audioFile = null;

	public FFT fft;
	public AudioIn in;
	public processing.sound.Sound  s;
	public fileInput inputFile,inputFolder;
	public fileOutput output;
	public String fileLocation,folderLocation;
	public int slices = 1,bands,frame,frame2,frames=0,count,slice;
	public String []menuLabels = {"Open File","Open Folder","Save","Restore tabs"};
	public String []rLabels = {"Run","Run All"};
	public Menu menu,run;
	public tab sliders,workFlow,spectrumImg;

	public float[] spectrum;
	public ArrayList<PVector> audio = new ArrayList<PVector>();
	public ArrayList<ArrayList<PVector>> audio2 = new ArrayList<ArrayList<PVector>>();
	public ArrayList<ArrayList<PVector>> visualiser = new ArrayList<ArrayList<PVector>>();
	public ArrayList<PImage> audioSlices = new ArrayList<PImage>();

	public PImage specImg;
	public boolean constructImage,Debug,analysed,sliced;
	public boolean update,toggle;

	public audioData(PApplet app){
		this.app = app;
		initTabs();
	};

	public audioData(PApplet app,int bands){
		this.app = app;
		this.bands = bands;
		initTabs();
	};

	public audioData(PApplet app,int bands,String file){

		spectrum = new float[bands];
		this.bands = bands;
		this.app = app;
		s = new processing.sound.Sound (app);
		amp = new Amplitude(app);
		audioFile = new SoundFile(app,file);
		// Create an Input stream which is routed into the Amplitude analyzer
		fft = new FFT(app, bands);

		//amp.input(audioFile);
		fft.input(audioFile);
		audioFile.rate((float) 1.5);
		initTabs();
	};

	public audioData(PApplet app,int bands,int Slices,String file){

		this.slices = Slices;
		spectrum = new float[bands];
		this.bands = bands;
		this.app = app;
		s = new processing.sound.Sound (app);
		amp = new Amplitude(app);
		audioFile = new SoundFile(app,file);
		// Create an Input stream which is routed into the Amplitude analyzer
		fft = new FFT(app, bands);

		//amp.input(audioFile);
		fft.input(audioFile);
		audioFile.rate((float) 1.5);
	};

	public void  initTabs(){

		spectrumImg = new tab(0,applet.height - 170,applet.width,150,"Spectrum",Bms);
		sliders = new tab(applet.width-100,68,100,applet.height - 240,"Menu",Bms);
		menu = new Menu(0,0+20,100,menuLabels,Bms);
		menu.w = 100;
		sliders.add(menu);
		workFlow = new tab(0,68,100,applet.height - 240,"Workflow",Bms);
		TextArea m1 = new TextArea(0,20,100,applet.height - 280,Bms);
		run = new Menu(0,workFlow.h-20,100,rLabels,Bms);
		run.w = 100;
		workFlow.add(m1);
		workFlow.add(run);
		Button b = sliders.menus.get(0).items.get(0);
		Button b1 = sliders.menus.get(0).items.get(1);
		//b = menu.items.get(0);
		inputFile = new fileInput(b,Bms);
		inputFile.x = sliders.x;
		inputFile.y = sliders.y+20;
		inputFile.w = sliders.w;
		inputFile.h = 20;

		inputFolder = new fileInput(b1,true,Bms);
		inputFolder.x = sliders.x;
		inputFolder.y = sliders.y+40;
		inputFolder.w = sliders.w;
		inputFolder.h = 20;

		output = new fileOutput();
		spectrumImg.toggle = true;
		sliders.toggle = true;
		workFlow.toggle = true;
		spectrumImg.draggable = true;
		spectrumImg.scrollable = true;
		workFlow.draggable = true;
		sliders.draggable = true;
		sliders.scrollable = true;
		//workFlow.scrollable = true;
		//canvas = createGraphics(width-200,height - 190);

	};

	public void  load(){


		// Create an Input stream which is routed into the Amplitude analyzer


	};

	// public void  display(PGraphics canvas){
	//   drawMenus();
	// };

	public void  drawMenus(){
		inputFile.listen();
		inputFolder.listen();
		sliders.displayTab();
		//menu.draw();
		spectrumImg.displayTab();
		workFlow.displayTab();
		//inputFile.listen();
	};

	public void  set(int bands,int Slices,String file){

		this.slices = Slices;
		spectrum = new float[bands];
		this.bands = bands;
		s = new processing.sound.Sound (app);
		amp = new Amplitude(applet);
		audioFile = new SoundFile(applet,file);
		// Create an Input stream which is routed into the Amplitude analyzer
		fft = new FFT(applet, bands);

		//amp.input(audioFile);
		fft.input(audioFile);
		audioFile.rate((float) 1.5);
	};

	public void  set(int bands,String file){

		spectrum = new float[bands];
		this.bands = bands;
		s = new processing.sound.Sound (applet);
		amp = new Amplitude(applet);
		audioFile = new SoundFile(applet,file);
		// Create an Input stream which is routed into the Amplitude analyzer
		fft = new FFT(app, bands);

		//amp.input(audioFile);
		fft.input(audioFile);
		audioFile.rate((float) 1.5);
	};

	public void  init(){
		if(count==0)audioFile.play();

		if(audioFile.isPlaying())analyse();
		else  analysed = true;
		//---------------------------------------------------------------------------------------------------
		// Map vertical mouse position to volume.
		float amplitude = applet.map(applet.mouseY, 0, applet.height, 0.4f, 0.0f);

		// Instead of setting the volume for every oscillator individually, we can just
		// control the overall output volume of the whole Sound library.
		s.volume(1);
		//------------------------------------------------------------------------------------------------------

		if(!constructImage&&analysed){
			construct();
		}else if(constructImage){
			display();
		}

		debug();
		if(audioFile.isPlaying())count++;
	};

	public void  analyse(){
		if(audioFile.isPlaying()){
			fft.analyze(spectrum);
			audio2.add(new ArrayList<PVector>());
			visualiser.add(new ArrayList<PVector>());
			for(int i = 0; i < bands; i++){
				// The result of the FFT is normalized
				// draw the line for frequency band i scaling it up by 5 to get more amplitude.
				float x = PApplet.map(i,0,bands,0,applet.width);
				float y = PApplet.map( (spectrum[i]),0,1,0,applet.height);

				float x1 = i;
				float y1 = PApplet.map( (spectrum[i]),0,(float) 0.001,0,255);

				applet.colorMode(PConstants.HSB);
				applet.stroke(i,applet.random(255),applet.random(255));
				applet.fill(i,applet.random(255),applet.random(255));
				audio.add(new PVector(x,y));
				audio2.get(frame).add(new PVector(x,y));
				visualiser.get(frame).add(new PVector(x1,y1));
				//vertex(k,-y);
				applet.stroke(x1,applet.random(255),applet.random(255));
				//vertex(bar.x,bar.y);
				applet.line(x,(float) applet.height,x,applet.height -y);
				frames ++;
			}
			//endShape(OPEN);
			applet.fill(255);
			applet.text("playing",100,100);
			frame ++;
		}
	};

	public void  construct(){
		applet.colorMode(PConstants.RGB);
		applet.fill(255);
		applet.text("Constructing",100,110);
		if(!sliced){
			specImg = applet.createImage(bands,frame,PConstants.RGB);
			specImg.loadPixels();
			//loadPixels();

			for(int i = 0; i < visualiser.size(); i++){
				ArrayList<PVector> b = visualiser.get(i);
				for(int j = 0; j < b.size(); j++){

					int pos = j+i*b.size();
					PVector p = b.get(j);

					specImg.pixels[pos] = applet.color(255-p.y,PApplet.map(i,0,255,0,applet.height),255-p.y);

					//if(pos<pixels.length)pixels[pos] = color(applet.random(255),0,0);
				}}
			//updatePixels();
			specImg.updatePixels();
			sliced = true; 
		}

		constructImage = true;
	};

	public void display(){


		if(sliced){
			applet.image(specImg,0,0);
			int n = (int)(specImg.width*specImg.height)/slices;

			if(audioSlices.size()<slices){
				for(int i=0;i<slices;i++)
					audioSlices.add(null);
			}
			for(int i=0;i<slices;i++){

				PImage img = applet.createImage(specImg.width,specImg.height/slices,PConstants.RGB);
				img.loadPixels();
				int k = i * img.pixels.length;
				int p1 = i * (512+1);
				for(int j=0;j<img.pixels.length;j++){
					int p = k + j;
					if(p<specImg.pixels.length)img.pixels[j] 
							= specImg.pixels[p];
				}
				img.updatePixels();
				audioSlices.set(i,img);
			}}

		for(int i = 0; i < slices; i++){
			//image(audioSlices.get(i),0,i*audioSlices.get(i).height);
			applet.stroke(0);
			applet.strokeWeight(1);
			applet.noFill();
			applet.rect(0,i*frame/slices,bands,frames/slices);
		}

		applet.fill(255);


		applet.text(audioSlices.size() + " slices",100,110);
		applet.text("stopped",100,100);

	};

	public void debug(){
		if(Debug){
			applet.fill(0);
			applet.text(applet.frameRate,200,200);
			applet.text("frames " + frames,200,210);
			applet.text("frame " + frame,200,220);
			applet.text(audio2.size(),200,230);
			applet.text("visualiser size " + visualiser.size(),200,240);
			if(visualiser.size()>0)applet.text("visualiser[0] " + visualiser.get(0).size(),200,250);
			if(specImg!=null)applet.text("img height " + specImg.height,200,260);

		}
	};

};
