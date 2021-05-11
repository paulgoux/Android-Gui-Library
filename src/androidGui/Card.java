package androidGui;

import processing.core.PApplet;
import processing.core.PImage;

public class Card extends imgDataset{
	public BMScontrols Bms;
	public PApplet applet;
	public float x,y;
	public int id;
	public PImage image;

	public Card(PImage Image, float xx,float yy,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy; 
		id = cards.size();
		image = new PImage(25,25);
		image.loadPixels();
		for(int i=0;i<25;i++){
			for(int j=0;j<25;j++){

				int pos = j + i * 25;
				int pos2 = (int) ((x+j) + (y+i) * Image.width); 
				image.pixels[pos] = Image.pixels[pos2];
			}}
		image.updatePixels();

	}

	public void draw(){
		applet.fill(0);
		//text(test.size(),x, y+10);
		applet.image(image,x,y);
	}

	public void save(){

	};

	public void load(){

	};

};
