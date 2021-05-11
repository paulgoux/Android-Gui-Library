package androidGui;

import processing.core.PApplet;

public class nnCard { // This class contains all the functions to format and save the data
	public BMScontrols Bms;
	public PApplet applet;
	public int id,type;
	public float [] inputs,outputs;
	public int output;
	public nnCard tempnnCard;

	public nnCard(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		inputs = new float [784]; // the images are a grid of 14x14 pixels which makes for a total of 196
		outputs = new float[10]; // the number of possible outputs; from 0 to 9
	};

	public nnCard(float []inputs_,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		inputs = inputs_; // the images are a grid of 14x14 pixels which makes for a total of 196
		outputs = new float[10]; // the number of possible outputs; from 0 to 9
	};

	public nnCard(float []inputs_, int id,int type,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		this.id = id;
		this.type = type;
		inputs = inputs_; // the images are a grid of 14x14 pixels which makes for a total of 196
		outputs = new float[10]; // the number of possible outputs; from 0 to 9
	};

	public void imageLoad(byte [] images, int offset) { // Images is an array of 1,960,000 bytes, each one representing a pixel (0-255) of the 10,000 * 14x14 (196) images
		// We know one image consists of 196 bytes so the location is: offset*196
		for (int i = 0; i < 784; i++) {
			inputs[i] = (int) (images[i+offset]) / 128.0f - 1; // We then store each pixel in the array inputs[] after converting it from (0 - 255) to (+1 - -1) as they vary on the greyscale 
		}
	};

	public void labelLoad(byte [] labels, int offset) {  // Labels is an array of 10,000 bytes, each representing the answer of each image

		output = (int) (labels[offset]);

		for (int i = 0; i < 10; i++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
			if (i == output) {
				outputs[i] = 1.0f;
			} else {
				outputs[i] = -1.0f;
			}
		}
	}

};

