package androidGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Input{

	public BMScontrols Bms;
	public PApplet applet;

	public Network NN;
	public float x,y,w = 100,h = 100,sSize = 8;
	public ArrayList<ArrayList<PVector>> points = new ArrayList<ArrayList<PVector>> ();
	public boolean mdown, update,onExit,exit,complete,debug;

	public int xmin = 1000,ymin = 1000,xmax,ymax,output,nxOffset = 10,pxOffset = 10,nyOffset = 10,
			pyOffset = 10;
	public PImage image,croppedImage;
	public int col = 0,col1 = 255;
	public nnCard card;

	public Input(float x,float y,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		this.x = x;
		this.y = y;

	};

	public Input(float x,float y,Network nn) {
		NN = nn;
		Bms = nn.Bms;
		applet = nn.applet;

		this.x = x;
		this.y = y;

	};

	void logic(){
	    
	    if(applet.mousePressed&&pos()&&!mdown){
	      mdown = true;
	      points.add(new ArrayList<PVector>());
	    }
	    
	    if(applet.mousePressed&&pos()&&mdown)onExit = false;
	    
	    if(mdown&&!pos()&&!onExit){
	      points.add(new ArrayList<PVector>());
	      onExit = true;
	      complete = false;
	    }
	    if(mdown&&!applet.mousePressed){
	      update = true;
	      mdown = false;
	      exit = false;
	      onExit = false;
	    }
	    if(points.size()==0)NN.data.input = null;
	    if(!pos()&&applet.mousePressed&&!NN.drawMenu.pos(1)){
	       xmin = 1000;ymin = 1000;xmax = 0;ymax = 0; 
	       croppedImage = null;
	    }
	  };
	  
	  void getPoints(){
	    logic();
	    if(mdown&&pos()){
	      float x1 = applet.mouseX;
	      float y1 = applet.mouseY;
	      float x2 = applet.pmouseX;
	      float y2 = applet.pmouseY;
	    
	    if(x1!=x2||y1!=y2){
	      points.get(points.size()-1).add(new PVector(x1,y1));
	    }
	    else if(points.get(points.size()-1).size()==0){
	      points.get(points.size()-1).add(new PVector(x1,y1));
	    }}
	    
//	    if(!pos()&&applet.mousePressed&&!mdown&&!NN.reset.pos()&&!NN.test.pos()){
//	      //points = new ArrayList<ArrayList<PVector>>();
//	      //complete = false;
//	    }
	    if(NN.drawMenu.toggle(0)){
	      complete = false;
	      points = new ArrayList<ArrayList<PVector>>();
	      image = null;
	      croppedImage = null;
	      xmin = 1000;ymin = 1000;xmax = 0;ymax = 0; 
	    }
	  };
	  
	  void draw(){
	    
	    getPoints();
	    applet.strokeWeight(1);
	    applet.fill(col);
	    applet.noStroke();
	    applet.stroke(col1);
	    applet.rect(x-1,y-1,w+2,h+2);
	    
	    if(points.size()>0){
	      
	    	applet.strokeWeight(sSize);
	     applet.stroke(col1);
	     
	     for(int i=0;i<points.size();i++){
	       if(points.get(i).size()>1){
	         for(int j=0;j<points.get(i).size()-1;j++){
	         PVector p = points.get(i).get(j);
	         PVector p1 = new PVector(-1,-1);
	         if(i<points.size())p1 = points.get(i).get(j+1);
	         applet.line(p.x,p.y,p1.x,p1.y);
	         
	         }}else if(points.get(i).size()==1){
	             PVector p = points.get(i).get(0);
	             applet.point(p.x,p.y);
	             int loc = (int)p.x-(int)x + ((int)p.y - (int)y)*(int)w;
	     }}}
	     
	     if(update||mdown){
	       
	     image = applet.createImage((int)(w),(int)(h),applet.RGB);
	     image.loadPixels();
	     applet.loadPixels();
	     
	     for(int i=0;i<h;i++){
	       for(int j=0;j<w;j++){
	         
	         int p = j+i*(int)w;
	         int p1 = (int)x+(int)y*applet.width+j+i*applet.width;
	         
	         image.pixels[p] = applet.pixels[p1];
	       }}
	       image.updatePixels();
	       
	     }
	     crop();
	  };
	  
	  void crop(){
		  applet.fill(255);
	    if(image!=null){
	      
	      for(int i=0;i<h;i++){
	        for(int j=0;j<w;j++){
	          
	          int p = j+i*(int)w;
	          int c = applet.color(image.pixels[p]);
	          
	          if(applet.red(c)==col1){
	            
	            if(xmin > j )xmin = j;
	            
	            if(ymin > i )ymin = i;
	            
	            if(xmax < j )xmax = j;
	            
	            if(ymax < i )ymax = i;
	            
	          }}}
	          
	         if(debug){
	        	 applet.image(image,200,0);
	           
	        	 applet.strokeWeight(7);
	        	 applet.stroke(0,255,0);
	        	 applet.point( xmin+200,ymin);
	        	 applet.stroke(255,0,0);
	        	 applet.point( xmax+200,ymax);
	        	 applet.fill(255);
	        	 int c = 0;
	           if((applet.mouseX+applet.mouseY*applet.width)<applet.pixels.length)
	        	   c = applet.pixels[applet.mouseX+applet.mouseY*applet.width];
	           applet.fill(0);
	           applet.text(applet.red(c),300,100);
	           applet.text(xmin,300,110);
	           applet.text(xmax,300,120);
	         }
	         if(update&&points.size()>0){
	           xmin -= nxOffset;
	           ymin -= nyOffset;
	           xmax += pxOffset;
	           ymax += pyOffset;
	           if(xmax>xmin){
	             if(ymax > ymin)croppedImage = applet.createImage(xmax - xmin,ymax - ymin,applet.RGB);
	             else croppedImage = applet.createImage(xmax - xmin,ymin - ymax ,applet.RGB);
	           }else{
	             if(ymax > ymin)croppedImage = applet.createImage(xmin - xmax,ymax - ymin  ,applet.RGB);
	             else croppedImage = applet.createImage(xmin - xmax,ymin - ymax ,applet.RGB);
	           }
	        
	        croppedImage.loadPixels();
	        
	        for(int i=ymin;i<ymax;i++){
	          for(int j=xmin;j<xmax;j++){
	            
	            int p = (j-xmin) + (i-ymin) * (croppedImage.width);
	            int p1 = j + i * (int)w;
	            
	            if(p<croppedImage.pixels.length&&p1<image.pixels.length&&p>=0&&p1>=0)croppedImage.pixels[p] = image.pixels[p1];
	        }}
	        update = false;
	        complete = false;
	      }
	        
	        if(croppedImage!=null&&!complete){
	          int k = 28;
	          croppedImage.resize(k,k);
	          complete = true;
	        }
	        if(croppedImage!=null&&debug){
	          applet.stroke(col1);
	          applet.noFill();
	          applet.strokeWeight(1);
	          applet.rect(199,99,15,15);
	          applet.image(croppedImage,200,100);
	        }
	    }
	  };
	  
	  boolean pos(){
	    return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	  };
	  
	  
	};
	

