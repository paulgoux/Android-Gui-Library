package androidGui;


import processing.core.PApplet;
import processing.core.PConstants;

public class Neuron {
	public BMScontrols Bms;
	public PApplet applet;
	public Neuron [] inputs; // Strores the neurons from the previous layer
	public float [] weights,biases;
	public float output,error,errorW,errorB,bias;
	public float [] outputs;
	public Network NN;

	public Neuron() {
	};
	
	public Neuron(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		
	};

	public Neuron(Neuron [] p_inputs,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		inputs = new Neuron [p_inputs.length];
		weights = new float [p_inputs.length];
		biases = new float [p_inputs.length];
		bias = applet.random(-1.0f, 1.0f);

		error = 0.0f;
		errorB = 0.0f;
		errorW = 0.0f;

		for (int i = 0; i < inputs.length; i++) {

			inputs[i] = p_inputs[i];
			weights[i] = applet.random(-1.0f,1.0f);
			biases[i] = applet.random(-1.0f,1.0f);
		}
	};
	
	public Neuron(Network nn) {
		Bms = nn.Bms;
		applet = nn.applet;
		NN = nn;
	};

	public Neuron(Neuron [] p_inputs,Network nn) {
		Bms = nn.Bms;
		applet = nn.applet;
		NN = nn;

		inputs = new Neuron [p_inputs.length];
		weights = new float [p_inputs.length];
		biases = new float [p_inputs.length];
		bias = applet.random(-1.0f, 1.0f);

		error = 0.0f;
		errorB = 0.0f;
		errorW = 0.0f;

		for (int i = 0; i < inputs.length; i++) {

			inputs[i] = p_inputs[i];
			weights[i] = applet.random(-1.0f,1.0f);
			biases[i] = applet.random(-1.0f,1.0f);
		}
	};

	public void respond() {

		float input = 0.0f;
		float bias = 0.0f;
		for (int i = 0; i < inputs.length; i++) {

			input += inputs[i].output * weights[i] + biases[i];
		}
		output = lookupSigmoid(input);
		error = 0.0f;
	};

	public void respondDeep(){
		float input = 0.0f;
		float bias = 0.0f;
		for (int i = 0; i < inputs.length; i++) {
			input += inputs[i].output * weights[i] + biases[i];
		}
		output = lookupSigmoid(input);
		error = 0;
	};

	public void setError(float desired) {
		error = desired - output;
	};

	public void train() {

		float delta = (float) ((1.0 - output) * (1.0 + output) * error * NN.LEARNING_RATE);

		for (int i = 0; i < inputs.length; i++) {

			inputs[i].error += (weights[i] )* error;
			weights[i] += (inputs[i].output ) * delta;
		}
	};

	public void display() {
		applet.stroke(200);
		applet.rectMode(PConstants.CENTER);
		applet.fill(128 * (1 - output));
		applet.rect(0, 0, 16, 16);
	};
	
	

	// once the sigmoid has been set up, this function accesses it:
	float lookupSigmoid(float x) {
	  
	  return NN.g_sigmoid[PApplet.constrain((int) PApplet.floor((float) ((x + 5.0) * 2.0)), 0, NN.nn-1)];
	};

};
