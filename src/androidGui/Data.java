package androidGui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

//import neuralNetwork.Card;
import processing.core.PApplet;
import processing.core.PImage;

public class Data{
	BMScontrols Bms;
	PApplet applet;
	HashMap<Integer,Integer> numbers = new HashMap<Integer,Integer>();

	String[] labels = {"0","1","2","3","4","5","6","7","8","9",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	//String[] labels = {"0","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
	//                   "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};       
	String []l;
	String location;
	byte[] imageB;
	public byte[] trainingLabels;
	byte[] testingLabels;
	float[] imageB_,trainingLabels_,testingLabels_;
	int[][] temp;
	public float[] temp2;
	public PImage image;
	public PImage input;
	public PImage [] images;
	public PImage [] trainingData;
	public PImage [] testingData;
	boolean debug,counter,counted;
	public fileOutput outPut;
	public ArrayList<float []> inputs;
	public ArrayList<float []> outputs;
	int output;
	public Card card;
	public ArrayList<String> fileNames = new ArrayList<String>();
	ArrayList<vectorText> textFiles = new ArrayList<vectorText>();
	public vectorText[] textFile;
//	public ArrayList<audioData> audioFiles = new ArrayList<audioData>();
//	public audioData[] audioFile;
	public HashMap<String, ArrayList<Integer>> extensions = new HashMap<String, ArrayList<Integer>>();

	public Data(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
	};

	public Data(String location,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		this.location = location;
		//outPut = new fileOutput(location);
	};

//	public Data(audioData audio,BMScontrols bms){
//		Bms = bms;
//		applet = bms.applet;
//		//location
//		//outPut = new fileOutput(location);
//	};

	public Data(byte[] image){

	};

	public void listExt() {

		String []names = fileUtils.listNames(location);

		if(names!=null&&!counted){
			for(int i=0;i<names.length;i++){

				File f = new File(names[i]);

				String type = fileUtils.getFileExtension(f);
				String[] m = applet.match(names[i], "ubyte");

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
					}}}
			counted = true;
		}
	};

	public void sortData(){

		for(int i=0;i<fileNames.size();i++){
			String s = fileNames.get(i);

			switch(s){

			case "txt" :
			case "doc" :
			case "DOC" :
			case "jpg" :
			case "JPG" :
			case "jpeg":
			case "JPEG":
			case "bmp" :
			case "BMP" :
			case "png" :
			case "PNG" :
			case "gif" :
			case "GIF" :
			case "mp3" :
			case "MP3" :
			case "wav" :
			case "ubyte" :

			}
		}
	};

//	public void loadAudioData(audioData data){
//
//
//	};
//
//	public void loadAudioData(audioData [] data){
//
//
//	};

//	public void loadAudioData(ArrayList<audioData> data){
//
//
//	};

	public void loadTextData(vectorText text){


	};

	public void loadTextData(vectorText[] text){


	};

	public void loadTrainingData(byte[] Image,int size){
		int k = 28;

		int[][]temp = new int[k][k];


		trainingData = new PImage[size];
		inputs = new ArrayList<float[]>();

		for(int i=0;i<size;i++){

			image = applet.createImage(k,k,applet.RGB);
			image.loadPixels();
			inputs.add(new float[k*k]);
			float[]temp2 = new float[k*k];
			for(int j=0;j<k*k;j++){
				image.pixels[j] = Image[j+16+k*k*i];
			}
			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					temp[h][j] = image.pixels[p];;

				}}

			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					image.pixels[p] =  (int) - applet.red(temp[j][h]);
					if(applet.red(image.pixels[p])>50)image.pixels[p] = (int) applet.map(applet.red(image.pixels[p])+20,0,255,-1,1); 
					else image.pixels[p] = -1;
					temp2[p] = 128 * (1 - (applet.red(image.pixels[p])));
					image.pixels[p] = (int) applet.red(temp[j][h]);

				}}

			image.updatePixels();
			inputs.set(i,temp2);
			trainingData[i] = image;
		}
	};

	public void loadTrainingData(byte[] Image,int size,int res){
		int k = res;

		if(temp==null||temp.length<k)temp = new int[k][k];

		trainingData = new PImage[size];
		image = applet.createImage(k,k,applet.RGB);
		inputs = new ArrayList<float[]>();

		for(int i=0;i<size;i++){

			
			image.loadPixels();
			inputs.add(new float[k*k]);
			if(temp2==null||temp2.length<k*k)temp2 = new float[k*k];
			for(int j=0;j<k*k;j++){
				image.pixels[j] = Image[j+16+k*k*i];
			}
			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					temp[h][j] = image.pixels[p];;

				}}

			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					image.pixels[p] =  (int) - applet.red(temp[j][h]);
					if(applet.red(image.pixels[p])>50)image.pixels[p] = (int) applet.map(applet.red(image.pixels[p])+20,0,255,-1,1); 
					else image.pixels[p] = -1;
					temp2[p] = 128 * (1 - (applet.red(image.pixels[p])));
					image.pixels[p] = (temp[j][h]);

				}}

			image.updatePixels();
			inputs.set(i,temp2);
			trainingData[i] = image;
		}
	};


	public void loadTestingData(byte[] Image,int size){
		int k = 28;

		testingData = new PImage[size];
		if(inputs==null)inputs = new ArrayList<float[]>();

		if(temp==null||temp.length<k)temp = new int[k][k];
		image = applet.createImage(k,k,applet.RGB);
		for(int i=0;i<size;i++){
			
			image.loadPixels();
			if(inputs.size()<i)inputs.add(new float[k*k]);
			if(temp2==null||temp2.length<k*k)temp2 = new float[k*k];

			for(int j=0;j<k*k;j++){
				image.pixels[j] = Image[j+16+k*k*i];
			}
			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					temp[h][j] = image.pixels[p];

				}}

			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					image.pixels[p] =  (int) - applet.red(temp[j][h]);
					if(applet.red(image.pixels[p])>50)image.pixels[p] = (int) applet.map(applet.red(image.pixels[p])+20,0,255,-1,1); 
					else image.pixels[p] = -1;
					temp2[p] = 128 * (1 - (applet.red(image.pixels[p])));

				}}
			image.updatePixels();
			inputs.set(i,temp2);
			testingData[i] = image;
		}
	};

	public void loadTestingData(byte[] Image,int size,int res){
		int k = res;

		testingData = new PImage[size];
		if(inputs==null)inputs = new ArrayList<float[]>();

		int [][] temp = new int[k][k];

		for(int i=0;i<size;i++){
			image = applet.createImage(k,k,applet.RGB);
			image.loadPixels();
			inputs.add(new float[k*k]);
			if(temp2==null||temp2.length<k*k)temp2 = new float[k*k];

			for(int j=0;j<k*k;j++){
				image.pixels[j] = Image[j+16+k*k*i];
			}
			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					temp[h][j] = image.pixels[p];

				}}

			for(int j=0;j<k;j++){
				for(int h=0;h<k;h++){

					int p = h + j * k;
					image.pixels[p] =  (int) - applet.red(temp[j][h]);
					if(applet.red(image.pixels[p])>50)image.pixels[p] = (int) applet.map(applet.red(image.pixels[p])+20,0,255,-1,1); 
					else image.pixels[p] = -1;
					temp2[p] = 128 * (1 - (applet.red(image.pixels[p])));

				}}
			image.updatePixels();
			inputs.set(i,temp2);
			testingData[i] = image;
		}
	};

	public void loadTestingLabels(byte [] Image){

		testingLabels = new byte[Image.length - 8];
		testingLabels_ = new float[Image.length - 8];

		for(int j=8;j<Image.length;j++){
			testingLabels[j-8] = Image[j];
		}
	};

	public void loadTrainingLabels(byte [] Image){

		trainingLabels = new byte[Image.length - 8];
		trainingLabels_ = new float[Image.length - 8];

		for(int j=8;j<Image.length;j++){
			trainingLabels[j-8] = Image[j];
		}
	};

	PImage threshold(PImage image){

		PImage tempCard = applet.createImage(image.width,image.height,applet.RGB);

		for (int i = 0; i < 784; i++) {
			if(applet.red(image.pixels[i])<50)tempCard.pixels[i] = 10;
			if(applet.red(image.pixels[i])>50)tempCard.pixels[i] = (int) applet.map(applet.red(image.pixels[i])+20,0,255,1,-1); 
			else tempCard.pixels[i] = 1;
			//applet.map(applet.red(image.pixels[i]),0,255,-1,1); 
		}
		return tempCard;
	};

};
