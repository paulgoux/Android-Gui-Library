package androidGui;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/*

Charles Fried - 2017
ANN Tutorial 
Part #2

NETWORK

This class is for the neural network, which is hard coded with three layers: input, hidden and output

 */
public class Network {
	public BMScontrols Bms;
	public PApplet applet;
	public float LEARNING_RATE = 0.001f;
	public int SOBEL = 0, MAX_POOLING = 1, AVE_POOLING = 2, MIN_POOLING = 3, CANNY, PREWITT = 4, 
			GAUSSIAN_BLUR = 5;

	public int totalTrain, totalTest, totalRight, testCard, trainCard, matchingCC,maxPm;
	public float sucess = 0,x,y,w,h;

	public String type = "mnist";
	public PImage tempImage;
	public String link1 = "emnist-"+type+"-train-images-idx3-ubyte";
	public String link2 = "emnist-"+type+"-test-images-idx3-ubyte";
	public String link3 = "emnist-"+type+"-train-labels-idx1-ubyte";
	public String link4 = "emnist-"+type+"-test-labels-idx1-ubyte";

	public int [][]SobelV = {{1, 0, 1}, 
			{1, 0, 1}, 
			{1, 0, 1}};

	public int [][]SobelH = {{1, 1, 1}, 
			{0, 0, 0}, 
			{1, 1, 1}};

	int [][]Sobel00 = {{0, 1, 0}, 
			{1, 0, 1}, 
			{0, 1, 0}};

	int [][]Sobel01 = {{1, 0, 1}, 
			{0, 1, 0}, 
			{1, 0, 1}};

	int [][]maxPooling = {{1, 1}, 
			{1, 1}};

	int [][]gaussian = {{1, 0, 1}, 
			{0, 1, 0}, 
			{1, 0, 1}};

	public ArrayList<int [][]>convolutions = new ArrayList<int [][]>();
	public ArrayList<int [][]>countedConvolutions = new ArrayList<int [][]>();

	public byte []trainingData, testingData, testingLabels, trainingLabels;

	public Neuron [] input_layer, hidden_layer, output_layer, previous_layer, PVinputLayer, PVhiddenLayer, 
	PVoutputLayer;
	public ArrayList<Neuron []> hiddenLayers;
	public int bestIndex = 0, N, N1, inputs_, hidden_, outputs_, stacks_ = 1;
	public boolean deep, convolutionCC,convolutionWritten;
	public fileOutput output,convolutionOutput;
	public String loc = "nn\\positions.txt";
	public String loc1 = "nn\\convolutions.txt";

	public Button trainB, testB,reset, test, loadFile, saveFile, openFile,saveConvolutions,loadConvolutions,
	loadTrainingData,loadTestingData,loadTrainingLabels,loadTestingLabels;

	String menuLabels [] = {"Load File","Save to File","Open Folder","Save Convolutions","Open Convolutions",
			"Load Training Data","Load Testing Data", "Load Training Labels",
	"Load Testing Labels"};
	String drawMenuLabels [] = {"Reset","Test"};
	public Menu menu,drawMenu;
	public Input input;
	public fileInput convolutionInput;
	public Data data;

	public nnCard [] testingSet, trainingSet;
	public nnCard tempCard;

	public Network() {
		//		init2();
	};

	public Network(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		init2();
	};

	public Network(String location,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		loc = location;

		init();
	};

	public Network(int inputs, int hidden, int outputs,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		init();
		inputs_ = inputs;
		hidden_ = hidden;
		outputs_ = outputs;

		input_layer = new Neuron [inputs*inputs];
		hidden_layer = new Neuron [hidden*hidden];
		output_layer = new Neuron [outputs];
		String text = inputs + "," + hidden + "," + outputs;

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(this);
		}

		for (int j = 0; j < hidden_layer.length; j++) {
			hidden_layer[j] = new Neuron(input_layer,this);
			for (int i = 0; i < hidden_layer[j].inputs.length; i++) {
				//output.write(hidden_layer[j].inputs[i] + " " + hidden_layer[j].weights[i] + " " + hidden_layer[j].biases[i]);
			}
		}

		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k] = new Neuron(hidden_layer,this);
		}


		N = inputs;
		N1 = hidden;
	};


	public Network(int inputs, int hidden, int outputs, String s,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		loc = s;
		output = new fileOutput(loc,Bms);

		init();

		inputs_ = inputs;
		hidden_ = hidden;
		outputs_ = outputs;

		input_layer = new Neuron [inputs*inputs];
		hidden_layer = new Neuron [hidden*hidden];
		output_layer = new Neuron [outputs];

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(this);
		}

		for (int j = 0; j < hidden_layer.length; j++) {
			hidden_layer[j] = new Neuron(input_layer,this);
		}

		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k] = new Neuron(hidden_layer,this);
		}

		N = inputs;
		N1 = hidden;
	};

	public Network(int inputs, int hidden, int outputs, int n,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		inputs_ = inputs;
		hidden_ = hidden;
		outputs_ = outputs;
		stacks_ = n;

		init();
		PApplet.println("network const", loc);

		input_layer  = new Neuron [inputs*inputs];
		hiddenLayers = new ArrayList<Neuron []>();
		hidden_layer = new Neuron [hidden*hidden];
		output_layer = new Neuron [outputs];

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(this);
		}

		previous_layer = input_layer;

		for (int i=0; i<n; i++) {

			hidden_layer = new Neuron [hidden*hidden];
			for (int j = 0; j < hidden_layer.length; j++) {
				hidden_layer[j] = new Neuron(previous_layer,this);
			}
			previous_layer = hidden_layer;
			hiddenLayers.add(hidden_layer);
		}

		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k] = new Neuron(hiddenLayers.get(hiddenLayers.size()-1),this);
		}

		N = inputs;
		N1 = hidden;
	};

	public Network(int inputs, int hidden, int outputs, int n,String loc,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;

		this.loc = loc;
		output = new fileOutput(loc,true,Bms);

		inputs_ = inputs;
		hidden_ = hidden;
		outputs_ = outputs;
		stacks_ = n;

		init();

		input_layer  = new Neuron [inputs*inputs];
		hiddenLayers = new ArrayList<Neuron []>();
		hidden_layer = new Neuron [hidden*hidden];
		output_layer = new Neuron [outputs];

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(this);
		}

		previous_layer = input_layer;

		for (int i=0; i<n; i++) {

			hidden_layer = new Neuron [hidden*hidden];
			for (int j = 0; j < hidden_layer.length; j++) {
				hidden_layer[j] = new Neuron(previous_layer,this);
			}
			previous_layer = hidden_layer;
			hiddenLayers.add(hidden_layer);
		}

		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k] = new Neuron(hiddenLayers.get(hiddenLayers.size()-1),this);
		}

		N = inputs;
		N1 = hidden;
	};

	public Network(int inputs, int hidden, int outputs, int n, int [] operations,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;


		int [] temp = new int [1+(hidden-2)*(hidden-2)];
		for (int i=0; i<operations.length; i++) {
			int k = operations[i];

			switch(k) {
			//temp = new int[(hidden-2)*(hidden-2)];
			}
		}

		inputs_ = inputs;
		hidden_ = hidden;
		outputs_ = outputs;
		stacks_ = n;

		init();
		int inputNodes = 0;
		input_layer  = new Neuron [inputNodes];
		hiddenLayers = new ArrayList<Neuron []>();
		hidden_layer = new Neuron [hidden*hidden];
		output_layer = new Neuron [outputs];

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(this);
			//input_layer[i].outputs = new float[inputNodes];
		}

		previous_layer = input_layer;

		for (int i=0; i<n; i++) {

			hidden_layer = new Neuron [hidden*hidden];
			for (int j = 0; j < hidden_layer.length; j++) {
				hidden_layer[j] = new Neuron(previous_layer,this);
			}
			previous_layer = hidden_layer;
			hiddenLayers.add(hidden_layer);
		}

		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k] = new Neuron(hiddenLayers.get(hiddenLayers.size()-1),this);
		}

		N = inputs;
		N1 = hidden;
	};
	
	public void init() {
		setupSigmoid();
		menu = new Menu(applet.width - 150,160,70,menuLabels,Bms);
		menu.setclassicBar();
		drawMenu = new Menu(applet.width - 200,250,90,20,drawMenuLabels,Bms);
		drawMenu.setclassicBar();
		output = new fileOutput(loc,Bms);
		//		output.init();
		convolutionOutput = new fileOutput(loc1,Bms);
		convolutionInput = new fileInput(Bms);
		data = new Data(Bms);

		trainingData = applet.loadBytes(link1);
		testingData = applet.loadBytes(link2);
		trainingLabels = applet.loadBytes(link3);
		testingLabels = applet.loadBytes(link4);

		data.loadTrainingData(trainingData, 60000, 28);
		trainingSet = new nnCard[60000];
		testingSet = new nnCard[10000];

		for (int i=0; i<data.trainingData.length; i++) {
			trainingSet[i] = new nnCard(data.inputs.get(i), i, 0,Bms);
		}

		data.loadTestingData(trainingData, 10000, 28);
		for (int i=0; i<data.testingData.length; i++) {
			testingSet[i] = new nnCard(data.inputs.get(i), i, 1,Bms);
		}

		data.loadTrainingLabels(trainingLabels);

		for (int i=0; i<trainingSet.length; i++) {
			nnCard c = trainingSet[i];

			for (int j = 0; j < 10; j++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
				if (data.trainingLabels[i] == j)c.outputs[j] = 1.0f;
				else c.outputs[j] = -1.0f;
			}
		}

		data.loadTestingLabels(testingLabels);

		for (int i=0; i<testingSet.length; i++) {
			nnCard c = testingSet[i];
			c.output = data.trainingLabels[i];
			for (int j = 0; j < 10; j++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
				if (data.trainingLabels[i] == j)c.outputs[j] = 1.0f;
				else c.outputs[j] = -1.0f;
			}
		}
		float xx = applet.width - 150;
		float yy = 160;
		float spacing = 20;

		input = new Input(xx - 120, 200,this);
		trainB = new Button(applet.width*0.06f, applet.height*0.9f,50,20, "Train",Bms);
		testB = new Button(applet.width*0.11f, applet.height*0.9f,50,20, "Test",Bms);

		convolutions.add(SobelV);
		convolutions.add(SobelH);
		convolutions.add(Sobel00);
		convolutions.add(Sobel01);
		permute();
		maxPm = 512;
	};

	public void initExample() {
		setupSigmoid();
		menu = new Menu(applet.width - 150,160,70,menuLabels,Bms);
		menu.setclassicBar();
		drawMenu = new Menu(applet.width - 200,250,90,20,drawMenuLabels,Bms);
		drawMenu.setclassicBar();
		output = new fileOutput(loc,Bms);
		//		output.init();
		convolutionOutput = new fileOutput(loc1,false,Bms);
		convolutionInput = new fileInput(Bms);
		data = new Data(Bms);

		trainingData = applet.loadBytes(link1);
		testingData = applet.loadBytes(link2);
		trainingLabels = applet.loadBytes(link3);
		testingLabels = applet.loadBytes(link4);

		data.loadTrainingData(trainingData, 60000, 28);
		trainingSet = new nnCard[60000];
		testingSet = new nnCard[10000];

		for (int i=0; i<data.trainingData.length; i++) {
			nnCard c = new nnCard(data.inputs.get(i), i, 0,Bms);
			trainingSet[i] = c;
		}

		data.loadTestingData(trainingData, 10000, 28);
		for (int i=0; i<data.testingData.length; i++) {
			nnCard c = new nnCard(data.inputs.get(i), i, 1,Bms);
			testingSet[i] = c;
		}

		data.loadTrainingLabels(trainingLabels);

		for (int i=0; i<trainingSet.length; i++) {
			nnCard c = trainingSet[i];

			for (int j = 0; j < 10; j++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
				if (data.trainingLabels[i] == j)c.outputs[j] = 1.0f;
				else c.outputs[j] = -1.0f;
			}
		}

		data.loadTestingLabels(testingLabels);

		for (int i=0; i<testingSet.length; i++) {
			nnCard c = testingSet[i];
			c.output = data.trainingLabels[i];
			for (int j = 0; j < 10; j++) {  // We then set the correct index in output[] to +1 if it corresponds to the ouput and -1 if not
				if (data.trainingLabels[i] == j)c.outputs[j] = 1.0f;
				else c.outputs[j] = -1.0f;
			}
		}
		float xx = applet.width - 150;
		float yy = 160;
		float spacing = 20;

		input = new Input(xx - 120, 200,this);
		trainB = new Button(applet.width*0.06f, applet.height*0.9f,50,20, "Train",Bms);
		testB = new Button(applet.width*0.11f, applet.height*0.9f,50,20, "Test",Bms);

		convolutions.add(SobelV);
		convolutions.add(SobelH);
		convolutions.add(Sobel00);
		convolutions.add(Sobel01);
		permute();
		maxPm = 512;
	};

	public void init2() {
		output = new fileOutput(loc,Bms);
		convolutionOutput = new fileOutput(loc1,Bms);
		convolutionInput = new fileInput(Bms);

		data = new Data(Bms);

		float xx = applet.width - 150;
		float yy = 160;
		float spacing = 20;

		input = new Input(xx - 120, 200,this);
		// //loadData();
		trainB = new Button(applet.width*0.06f, applet.height*0.9f,40,20, "Train",Bms);
		testB = new Button(applet.width*0.11f, applet.height*0.9f,40,20, "Test",Bms);

		convolutions.add(SobelV);
		convolutions.add(SobelH);
		convolutions.add(Sobel00);
		convolutions.add(Sobel01);
		permute();
		maxPm = 512;
	};

	nnCard loadImage(PImage image) {

		tempCard = new nnCard(Bms);

		for (int i = 0; i < 784; i++) {
			if (applet.red(image.pixels[i])<50)tempCard.inputs[i] = -10;
			if (applet.red(image.pixels[i])>50)tempCard.inputs[i] = PApplet.map(applet.red(image.pixels[i])+20, 0, 255, -1, 1); 
			else tempCard.inputs[i] = -1;
			PApplet.map(applet.red(image.pixels[i]), 0, 255, -1, 1);
		}
		return tempCard;
	};

	PImage loadImage(PImage image, boolean bool) {

		tempImage = applet.createImage(inputs_, inputs_,PConstants.RGB);

		for (int i = 0; i < inputs_*inputs_; i++) {
			tempImage.pixels[i] = image.pixels[i];
		}
		return tempImage;
	};


	public void load() {
	};

	public void load(String s) {
	};

	public void save() {
		PApplet.println("Saving File..."+ loc);
		output.open();
		String text = "";
		if (stacks_ == 0)text = inputs_ + "," + hidden_ + "," + outputs_ + ",";
		else text = inputs_ + "," + hidden_ + "," + outputs_ + "," + stacks_;
		output.write(text);

		for (int i = 0; i < input_layer.length; i++) {
			if (input_layer[i].weights!=null)
				for (int j = 0; j < input_layer[i].weights.length; j++) {
					output.write(input_layer[i].inputs[j].output + " " + input_layer[i].weights[j] + " " + input_layer[i].biases[j]);
				} else 
					for (int j = 0; j < inputs_* inputs_; j++) {
						output.write(0 + " " + 0 + " " + 0);
					}
		}

		output.write("");

		for (int i=0; i<stacks_; i++) {

			for (int j = 0; j < hidden_layer.length; j++) {
				for (int k = 0; k < hidden_layer[j].inputs.length; k++) {
					output.write(hidden_layer[j].inputs[k].output + "," + hidden_layer[j].weights[k] + "," + hidden_layer[j].biases[k]);
				}
			}
		}

		output.write("");

		for (int k = 0; k < output_layer.length; k++) {
			for (int j = 0; j < output_layer[k].inputs.length; j++) {
				output.write(output_layer[k].inputs[j].output + "," + output_layer[k].weights[j] + "," + output_layer[k].biases[j]);
			}
		}
		output.close();
	};

	public void saveConvolutions(){
		PApplet.println("Saving File...");
		convolutionOutput.open();
		for(int i=0;i<convolutions.size();i++){
			for(int j=0;j<convolutions.get(i).length;j++){

				convolutionOutput.write(PApplet.str(convolutions.get(i)[j]));
				//convolutionOutput.write("hello");
			}
			convolutionOutput.write("");
		}
		convolutionOutput.close();
		PApplet.println("Saved!");
	};

	public void listen() {
		if (menu.items.get(0).pos())Bms.File.listen();
		if (Bms.File.value!=null) {
			loc = Bms.File.value;
			PApplet.println("User " + Bms.File.value);
			Bms.File.value = null;
			loadFile(Bms.File.value);
		}

		if (menu.items.get(4).pos())convolutionInput.listen();
		if (convolutionInput.value!=null) {
			loc1 = convolutionInput.value;
			PApplet.println("User " + convolutionInput.value);
			convolutionInput.value = null;
			//loadConvolutions(file.value);
		}
		//if(file.listen()!=null)
		//applet.println(file.listen());
	};

	public void loadFile(String s) {
	};

	public void respond(nnCard card) {

		if (!deep) {
			for (int i = 0; i < input_layer.length; i++) {
				input_layer[i].output = card.inputs[i];
			}
			// now feed forward through the hidden layer
			for (int j = 0; j < hidden_layer.length; j++) {
				hidden_layer[j].respond();
			}
			for (int k = 0; k < output_layer.length; k++) {
				output_layer[k].respond();
			}
		}
	};

	public void respond(PImage card) {

		card.loadPixels();

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i].output = card.pixels[i];
		}
		// now feed forward through the hidden layer
		for (int j = 0; j < hidden_layer.length; j++) {
			hidden_layer[j].respond();
		}
		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k].respond();
		}
	};

	public void respondDeep(nnCard card) {

		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i].output = card.inputs[i];
		}
		// now feed forward through the hidden layer
		for (int i = 0; i<hiddenLayers.size(); i++) {
			for (int j = 0; j < hiddenLayers.get(i).length; j++) {
				if (i<hiddenLayers.size()-1)hiddenLayers.get(i)[j].respondDeep();
				else hiddenLayers.get(i)[j].respond();
			}
		}

		for (int i = 0; i < output_layer.length; i++) {
			output_layer[i].respond();
		}
	};

	public void respondDeep(PImage card) {
		card.loadPixels();
		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i].output = card.pixels[i];
		}
		// now feed forward through the hidden layers
		for (int i = 0; i<hiddenLayers.size(); i++) {
			for (int j = 0; j < hiddenLayers.get(i).length; j++) {
				if (i<hiddenLayers.size()-1)hiddenLayers.get(i)[j].respondDeep();
				else hiddenLayers.get(i)[j].respond();
			}
		}

		for (int i = 0; i < output_layer.length; i++) {
			output_layer[i].respond();
		}
	};

	public void displayOutput() {

		// Draw the output layer
		float [] resp = new float [output_layer.length];
		float respTotal = 0.0f;

		for (int k = 0; k < output_layer.length; k++) {
			resp[k] = output_layer[k].output;
			respTotal += resp[k]+1;
		}

		applet.fill(0);

		for (int k = 0; k < output_layer.length; k++) {
			//applet.text(k%10, 100, 10);
			applet.text(k + "   " +PApplet.nfc(((output_layer[k].output+1)/respTotal)*100, 2) + "%", 115, 110+10*k);
			applet.strokeWeight(1);
		}
		float best = -1.0f;
		for (int i =0; i < resp.length; i++) {
			if (resp[i]>best) {
				best = resp[i];
				bestIndex = i;
			}
		}
	};

	public void getBest() {

		float [] resp = new float [output_layer.length];

		float respTotal = 0.0f;
		float best = -1.0f;

		for (int k = 0; k < output_layer.length; k++) {
			resp[k] = output_layer[k].output;

			respTotal += resp[k]+1;
			if (resp[k]>best) {
				best = resp[k];
				bestIndex = k;
			}
		}
	};

	public void displayGuess() {
		applet.fill(9);
		applet.textSize(30);
		applet.text(bestIndex, 10, 40);
		applet.textSize(12);
	};


	public void train(float [] outputs) {
		// adjust the output layer
		float best = -1.0f;
		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k].setError(outputs[k]);
			output_layer[k].train();
			if (output_layer[k].output > best) bestIndex = k;
		}

		// propagate back to the hidden layer
		for (int j = 0; j < hidden_layer.length; j++) {
			hidden_layer[j].train();
		}

		// The input layer doesn't learn: it is the input and only that
	};

	public void trainDeep(float [] outputs) {
		// adjust the output layer
		float best = -1.0f;
		for (int k = 0; k < output_layer.length; k++) {
			output_layer[k].setError(outputs[k]);
			output_layer[k].train();
			if (output_layer[k].output > best) bestIndex = k;
		}

		// propagate back to the hidden layer


		for (int i=hiddenLayers.size()-1; i>-1; i--) {
			for (int j = 0; j < hiddenLayers.get(i).length; j++) {
				hiddenLayers.get(i)[j].train();
			}
		}

		// The input layer doesn't learn: it is the input and only that
	};

	public void convolve() {
	}

	public void permute() {
		int [][]permutation = new int[3][3];
		if (!convolutionCC) {
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					int p = i + j * 3;

					permutation[i][j] = applet.floor(applet.random(0, 2));
				}
			}

			if (!convolutions.contains(permutation)) convolutions.add(permutation);

			if (!countedConvolutions.contains(permutation)){
				countedConvolutions.add(permutation);
				matchingCC ++;
			}

			if (matchingCC >= maxPm)convolutionCC = true;
		}

		if(convolutionCC&&convolutionWritten){};
	};

	public void displayCard(nnCard card, int x, int y) {
		int size = 28;
		int res = 7;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				int p = j+i*size;
				applet.noStroke();
				//stroke(0);
				applet.fill(128 * (1 - card.inputs[p]));
				applet.rect(x+j*(res+2),y+i*(res+2),res,res);
			}}

		//if (card.type == 0) {
		//  data.trainingData[card.id].resize(150, 0);
		//  applet.image(data.trainingData[card.id], x, y);
		//} else {
		//  data.testingData[card.id].resize(150, 0);
		//  applet.image(data.testingData[card.id], x, y);
		//}
	};

	public void displayCard(PImage a, int x, int y) {

		a.resize(150, 0);
		applet.image(a, x, y);
	};

	public void run(int n) {
		if (menu.click(1))save();
		//		if (menu.click(2))applet.exit();
		listen();
		if (trainB.getToggle()) {
			for (int i = 0; i < n; i++) {
				trainCard = (int) PApplet.floor(applet.random(0, trainingSet.length));
				//applet.println(trainCard);
				respondDeep(trainingSet[trainCard]);
				trainDeep(trainingSet[trainCard].outputs);
				totalTrain++;
			}
		} else if (testB.click()) {
			testCard = (int) PApplet.floor(applet.random(0, testingSet.length));
			respondDeep(testingSet[testCard]);

			getBest();
			if (bestIndex == testingSet[testCard].output) totalRight ++;
			totalTest ++;
		}
		displayOutput();
		if (trainingSet[trainCard]!=null)displayCard(trainingSet[trainCard], 210, 0);
		if (testingSet[testCard]!=null)displayCard(testingSet[testCard], 470, 0);
//		if(drawMenu.toggle(0))input.reset();
		trainB.draw();
		testB.draw();
		input.draw();
		if (drawMenu.items.get(1).click()&&input.croppedImage!=null) {
//			applet.println(input.croppedImage);
			tempCard = loadImage(input.croppedImage);
			tempImage = loadImage(input.croppedImage, true);
			respondDeep(tempCard);
			getBest();
			totalTest ++;
		}
		boolean b1 = drawMenu.click(1);
//		drawMenu.click();
		if(drawMenu.items.get(1).click())
			applet.println("No Image toggle");
		if(drawMenu.click(1))
			applet.println("No Image click");
		if (tempImage!=null)displayCard(tempImage, 730, 0);
		applet.fill(100);
		float xx = 90;
		float yy = 90;
		applet.text("Test card: #" + testCard, applet.width*0.18f, applet.height*0.89f);
		applet.text("Train card: " + trainCard, applet.width*0.18f, applet.height*0.93f);

		applet.text("Total train: " + totalTrain, applet.width*0.32f, applet.height*0.89f);
		applet.text("Total test: " + totalTest, applet.width*0.32f, applet.height*0.93f);

	    if (totalTest>0) sucess = (float)(totalRight)/(float)(totalTest);
	    applet.text("Success rate: " + applet.nfc(sucess, 2), applet.width*0.44f, applet.height*0.89f);
	    applet.text("Card label: " + testingSet[testCard].output, applet.width*0.44f, applet.height*0.93f);
		displayGuess();
		drawMenu.draw();
		menu.draw();
		if(menu.click(3))saveConvolutions();
		//		permute();
		applet.fill(0);
//		applet.text(applet.frameRate, 10, yy+20*6);
		applet.text(LEARNING_RATE, 10, yy+200);
	};

	public void trainDisplay() {
	};

	public void testDisplay() {
	};
	
	public int nn = 4000;
	public float [] g_sigmoid = new float [nn];

	public void setupSigmoid() {
	  
	  for (int i = 0; i < nn; i++) {
	    float x = (i / 2.0f) - 5.0f;
	    g_sigmoid[i] = (float) (2.0 / (1.0 + PApplet.exp((float) (-2.0 * x))) - 1.0);
	  }
	};
};

class pNetwork extends Network {

	ArrayList<Neuron []> hiddenLayers_;
	ArrayList<Neuron []> inputLayers_;
	ArrayList<Neuron []> outputLayers_;

	Neuron [][] hiddenLayers;
	Neuron [][] inputLayers;
	Neuron [][] outputLayers;
};


