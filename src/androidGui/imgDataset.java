package androidGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class imgDataset{
	BMScontrols Bms;
	PApplet applet;
	PImage image ;
	imgDataset dataset;

	ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<Card> train = new ArrayList<Card>();
	ArrayList<Card> test = new ArrayList<Card>();

	public imgDataset(){


	};

	public imgDataset(PImage image, int n){
		if(cards.size()<n){
			for (int i = 0; i < image.height-25; i+=25) {
				for (int j = 0; j < image.width; j+=25) {

					Card c = new Card(image,j,i);
					int pos = cards.size() + 1;
					cards.add(c);
					if(pos%5==0) test.add(c);
					else train.add(c);
				}}}
	};

	public imgDataset(PImage image, int n,int offset){
		if(cards.size()<n){
			for (int i = 0; i < image.height-offset; i+=offset) {
				for (int j = 0; j < image.width; j+=offset) {

					Card c = new Card(image,j,i);
					//c =  new Card(image);
					int pos = cards.size() + 1;
					cards.add(c);
					if(pos%5==0) test.add(c);
					else train.add(c);
				}}}
	};

	public imgDataset(PImage image, int n,int xoffset,int yoffset){
		if(cards.size()<n){
			for (int i = 0; i < image.height-yoffset; i+=yoffset) {
				for (int j = 0; j < image.width; j+=xoffset) {

					Card c = new Card(image,j,i);
					int wnum = image.width/xoffset;
					int pos = cards.size() + 1;
					cards.add(c);
					if(pos%5==0) test.add(c);
					else train.add(c);
				}}}
	};

	public imgDataset(ArrayList<PImage> images){
		if(cards.size()<images.size()){
			for (int i = 0; i < images.size(); i++) {

				Card c = new Card(image,0,i);
				int pos = cards.size() +1;
				cards.add(c);
				if(pos%5==0) test.add(c);
				else train.add(c);
			}}
	};

	public void save(){

	};

	public void load(){

	};

	public void draw(){
		for(Card c: cards){
			c.draw();
		}
	};

	public void draw_train(){
		for(Card c: train){
			c.draw();
		}
	};

	public void draw_test(){
		for(Card c: test){
			c.draw();
		}
	};



	class Card{

		float x,y;
		int id;
		PImage image;

		Card(PImage Image, float xx,float yy){

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

};
