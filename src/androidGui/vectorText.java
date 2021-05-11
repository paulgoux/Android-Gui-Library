package androidGui;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;

public class vectorText{
	public BMScontrols Bms;
	public PApplet applet;
	public String [] text = null;
	public String Sentence = "",sentence = "",Text="";
	public ArrayList<Word[]> ninputs = new ArrayList<Word[]>();
	public ArrayList<String> Sentences = new ArrayList<String>();
	public ArrayList<Word> newSentence = new ArrayList<Word>();
	public ArrayList<Word> builtSentence = new ArrayList<Word>();
	//ArrayList<Integer> sentences = new ArrayList<Integer>();
	public ArrayList<Word> frequency = new ArrayList<Word>();
	public ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<Word>> Words = new ArrayList<ArrayList<Word>>();
	public ArrayList<Word> Words1d = new ArrayList<Word>();
	public ArrayList<Word> Words1dsingle = new ArrayList<Word>();
	public ArrayList<Word> wordPool = new ArrayList<Word>();
	public ArrayList<String> words1d = new ArrayList<String>();
	public ArrayList<String> words1dsingle = new ArrayList<String>();
	public ArrayList<ArrayList<Integer>> sentences = new ArrayList<ArrayList<Integer>>();

	public ArrayList<Word> unsortedSearch = new ArrayList<Word>();
	public ArrayList<Word> sortedSearch = new ArrayList<Word>();
	public float max_size = 0,maxVariance,minVariance=10,maxFrequency;
	public fileInput inputFile,inputFolder;
	public fileOutput output;
	public String []menuLabels = {"Open File","Open Folder","Save Text","Save Data","Analyze","View Text",
								  "Find Similar","Create Sentence","Load Data","Restore tabs"};
	public String []rLabels = {"Run","Run All","Clear"};
	public String fileLocation,folderLocation, savedFileLocation;
	public Menu menu,run;
	public tab workFlow,readingPane,sliders;

	public Word start;

	public int sentence_counter = 0, word_counter = 0,word_total,char_counter,char_counter2,char_counter3,counter,
			counter2,counter3,mid,twords,nonRep_words,char_total,space_total,longest_sentence,fcounter;
	public boolean init = true,textcompleted,inputs_complete,separatewords,separateSentences,trimchars,createwordobj,
			textjoined,getvariance,foundS,mdown,finit;
	public boolean toggle,debug;
	public float my;

	public vectorText(String a,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		sentence = a;
		init();
		
	};

	public vectorText(String [] a,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		text = a;
		init();
	};

	public vectorText(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		init();
	};

	public vectorText(){

	};

	public void set(String a){
		sentence = a;
	};

	public void set(String [] a){
		text = a;
	};

	public void init(){
		
		sliders = new tab(applet.width-100,68,100,applet.height - 240,"Menu",Bms);

		menu = new Menu(0,0,100,menuLabels,Bms);
		menu.w = 100;
		sliders.add(menu);
		
		readingPane = new tab(0,68,applet.width-sliders.w-10,sliders.h,"Reading Pane",Bms);
		
		workFlow = new tab(0,readingPane.y+readingPane.h,applet.width,150,"Find Similar",Bms);
		String[] StateLabels = {"Create Sentence"};
		workFlow.addState(StateLabels);
		
		String[] mLabels = {"Ordered","Unordered"};
		Menu m1 = new Menu(20,20,190,20,"Type",mLabels,Bms);
		m1.setclassicBar();
		m1.setHorizontal();
		
		workFlow.add(0,m1);
		
		TextBox t1 = new TextBox(20,m1.y+m1.h+10,180,20,200,"Type word...",Bms);
		workFlow.add(0,t1);
		
		Button b1 = new Button(20,t1.y+t1.h+10,90,20,"Find",Bms);
		b1.setclassicBar();
		workFlow.add(0,b1);
		
		Button b2 = new Button(b1.x+b1.w+20,t1.y+t1.h+10,90,20,"Reset",Bms);
		b2.setclassicBar();
		workFlow.add(0,b2);
		
		String[] mLabels1 = {"Type 1","Type 2","Type 3","Type 4"};
		Menu m2 = new Menu(20,20,190,20,"Type A",mLabels1,Bms);
		m2.setclassicBar();
		m2.setHorizontal();
		workFlow.add(1,m2);
		
		t1 = new TextBox(20,m2.y+m2.h+10,180,20,200,"Type 1st word...",Bms);
		workFlow.add(1,t1);
		
		b1 = new Button(20,t1.y+t1.h+10,90,20,"Build",Bms);
		b1.setclassicBar();
		workFlow.add(1,b1);
		
		b2 = new Button(b1.x+b1.w+20,t1.y+t1.h+10,90,20,"Reset",Bms);
		b2.setclassicBar();
		workFlow.add(1,b2);
		
		Button b = sliders.getButton(0,0);

		inputFile = new fileInput(b,Bms);
		
		Button b4 = sliders.getButton(0,1);
		PApplet.println("vector init button",b4,b.x,b.y,b.w,b.h);
		inputFolder = new fileInput(b4,true,Bms);
		
		output = new fileOutput(Bms);
		
		readingPane.toggle = true;
		sliders.toggle = true;
		workFlow.toggle = true;
		readingPane.setvScroll();
		workFlow.draggable = true;
		readingPane.draggable = true;
		sliders.draggable = true;
		sliders.setvScroll();
		readingPane.setDraggable(true);

	};

	public void classString(BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		words.add(new ArrayList<String>());
		if(sentence_counter<sentence.length()){

			for(int i=0;i<sentence.length();i++){

				char a = sentence.charAt(i);

				if(a==' '||i==sentence.length()-1){
					String b = "";
					if(a==' ')b += sentence.substring(word_counter,i);
					else b += sentence.substring(word_counter,i+1);
					words.get(0).add(b);
					//sentence = sentence.substring(i,sentence.length());
					word_counter=i;
					if(i==0);word_counter =0;
					//System.out.applet.println(b);
				}
				sentence_counter ++;
			}}

		mid = PApplet.floor(sentence.length()/2);
		float cursorx = 0;
		for(int i=0;i<words.size();i++){
			String a = words.get(0).get(i);

			int dy = mid - i;

			//applet.text(dy,10,20+10*i);

			Word w = new Word(dy,0,i,cursorx,a.length(),a,Bms);

			if(!Words1dsingle.contains(w))Words1dsingle.add(w);
			cursorx+= applet.textWidth(a);
		}
	};

	public void classString(String sentence,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		String temp = "";
		words.add(new ArrayList<String>());
		if(sentence_counter<sentence.length()){

			for(int i=0;i<sentence.length();i++){

				char a = sentence.charAt(i);

				if(a==' '||i==sentence.length()-1){
					String b = "";
					if(a==' ')b += sentence.substring(word_counter,i);
					else b += sentence.substring(word_counter,i+1);
					words.get(0).add(b);
					word_counter=i;
					if(i==0);word_counter =0;
					//System.out.applet.println(b);
				}
				sentence_counter ++;
			}}

		mid = PApplet.floor(sentence.length()/2);
		float cursorx = 0;
		for(int i=0;i<words.size();i++){
			String a = words.get(0).get(i);

			int dy = mid - i;

			applet.text(dy,10,20+10*i);

			Word w = new Word(dy,0,i,cursorx,a.length(),a,Bms);

			if(!Words1dsingle.contains(w))Words1dsingle.add(w);
			cursorx += applet.textWidth(a);
		}
	};

	public void separateSentences_(){

		//push all text into one array
		if(sentence_counter<text.length){

			for(int j=0;j<text.length;j++){
				Text+= text[j];
				sentence_counter++;
			}}
		// loop through array and find key characters
		if(counter<Text.length()){

			for(int i=0;i<Text.length();i++){

				counter ++;

				char a = Text.charAt(i);
				String b = "";

				if(a=='.'){

					if(a=='.'||a=='!'||a=='?')b = Text.substring(char_counter,i+1);
					char_counter=i+1;
					Sentences.add(b);
					words.add(new ArrayList<String>());

				}
				else if(i==Text.length()-1){
					b = Text.substring(char_counter,i);
					char_counter=i;
					Sentences.add(b);
					words.add(new ArrayList<String>());
				}
				//System.out.applet.println(b);

			}}
	};

	public void trimChars_(){

	};

	public void separateWords_(){
		if(counter2<Sentences.size()){

			for(int i=0;i<Sentences.size();i++){

				String s = Sentences.get(i);
				s = s.replaceAll("\\s+","");
				char_counter2 = 0;

				for(int j=0;j<s.length();j++){

					char a = s.charAt(j);

					counter2++;
					String b = "";

					if(a==' '){
						b = s.substring(char_counter2,j);
						char_counter2 = j;
						words.get(i).add(b);
						words1d.add(b);
						word_total++;
						words.get(i).add(" ");
						words1d.add(" ");
						space_total++;
					}
					else if(a=='\''){
						b += "\' ";
						PApplet.println(b);
						words.get(i).add(b);

						words1d.add(b);
						char_counter2=j+1;
					}
					else if(a==','){
						b = s.substring(char_counter2,j);
						PApplet.println(b);
						words.get(i).add(b);

						words1d.add(b);
						char_counter2=j;
					}


					else if(a=='.'){
						b = s.substring(char_counter2,j-2);
						char_counter2 = j+1;
						words.get(i).add(b);
						words1d.add(b);
						words.get(i).add(".");
						words1d.add(".");
					}};counter2++;}}
	};

	public void createWordObject_(){
		if(Words1dsingle.size()<words1d.size()){

			for(int i=0;i<words.size();i++){

				ArrayList<String> s = words.get(i);
				char_counter2 = 0;

				mid = PApplet.floor(s.size()/2);
				float cursorx = 0;
				for(int j=0;j<s.size();j++){

					String a = s.get(j);

					Word word = new Word(j - mid,j,i,cursorx,a.length(),a,Bms);
					cursorx += applet.textWidth(a);
					Words1dsingle.add(word);
				}}}
	};

	public void classText(){

		separateSentences_();
		trimChars_();
		separateWords_();
		createWordObject_();

	};

	public void classTextsp(){
		while(!textcompleted){
			fcounter++;
			separateSentences();
//			trimChars();
			separateWords();
			createWordObject();
			getVariance();
		}
	};

	public void separateSentences(){
		if(sentence_counter<text.length||sentence_counter==0&&text.length>0){

			for(int j=0;j<text.length;j++){
				Text+= text[j] ;
				max_size += applet.textWidth(text[j]);
				sentence_counter++;
			}
			
//			s = s.replace("\r\n",". ");
//			max_size /= text.length;
		}else if(counter<Text.length()){

			for(int i=0;i<Text.length();i++){

				counter ++;

				char a = Text.charAt(i);
				String b = "";

				if(a=='.'){

					b = Text.substring(char_counter,i+1);
					if(b.length()>1){
						char_counter=i+1;
						Sentences.add(b);
						words.add(new ArrayList<String>());
					}}}}else separateSentences = true;
//		if(separateSentences&&sentences.size()==0) {
//			for(int i=0;i<text.length;i++){
//
////				counter ++;
//				Sentences.add(text[i]);
//				max_size += applet.textWidth(text[i]);
//				sentence_counter++;
//			}
//		}
	};

	public void trimChars(){

	};

	public void separateWords(){

		if(counter2<Sentences.size()&&separateSentences&&!separatewords
				||(counter2==0&&Sentences.size()>0)){

			for(int i=0;i<Sentences.size();i++){

				String s = Sentences.get(i);
				char_counter2 = 0;
				s = s.replace("\r\n",". ");
				for(int j=0;j<s.length();j++){

					char a = s.charAt(j);

					String b = "";

					if(a==','){
						b = s.substring(char_counter2,j)+",";

						if(b.length()>0){
							words.get(i).add(b);
							words.get(i).add(b);
						}
						//words1d.add(b);
						char_counter2 = j+1;
						char_total ++;
					}
					else if(a=='\n'){
						b = s.substring(char_counter2,j);
						char_counter2 = j+1;
						//b += " ";
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						word_total++;
						//words.get(i).add(" ");
						//words1d.add(" ");
						space_total++;
					}
					else if(a==' '){
						b = s.substring(char_counter2,j);
						char_counter2 = j+1;
						//b += " ";
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						word_total++;
						//words.get(i).add(" ");
						//words1d.add(" ");
						space_total++;
					}
					//else if(a==';'){
					//b = s.substring(char_counter2,j);
					//char_counter2 = j+1;
					//words.get(i).add(b);
					//words1d.add(b);
					//word_total++;
					////words.get(i).add(" ");
					////words1d.add(" ");
					//space_total++;
					//}
					else if(a=='?'){
						b = s.substring(char_counter2,j)+"?";
						char_counter2 = j+1;
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						word_total++;
						//words.get(i).add("?");
						//words1d.add("?");
						space_total++;
					}
					else if(a=='.'){
						b = s.substring(char_counter2,j)+".";
						char_counter2 = j+1;
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						//words.get(i).add(".");
						//words1d.add(".");
						space_total++;
					}
					else if(a=='!'){
						b = s.substring(char_counter2,j)+"!";
						char_counter2 = j+1;
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						//words.get(i).add("!");
						//words1d.add("!");
						char_total++;
					}
					else if(a=='-'){
						b = s.substring(char_counter2,j);
						char_counter2 = j+1;
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						//words.get(i).add("-");
						//words1d.add("-");
						char_total++;
					}
					else if(a==':'){
						b = s.substring(char_counter2,j);
						char_counter2 = j+1;
						if(b.length()>0){
							words.get(i).add(b);
							words1d.add(b);
						}
						//words.get(i).add(":");
						//words1d.add(":");
						char_total++;
					}
					//else if(a=='/'){
					//  b = s.substring(char_counter2,j);
					//  char_counter2 = j+1;
					//  words.get(i).add(b);
					//  words1d.add(b);
					//  //words.get(i).add("/");
					//  //words1d.add("/");
					//  char_total++;
					//}
					//else if(a=='\''){
					//  b = s.substring(char_counter2,j);
					//  char_counter2 = j;
					//  words.get(i).add("'");
					//  words1d.add("'");
					//  words.get(i).add("'");
					//  words1d.add("'");
					//  char_total++;
					//}
				};counter2++;if(i==Sentences.size()-1)separatewords = true;}}

	};

	public void createWordObject(){
		//applet.text(count,200,130);

		if(twords<words1d.size()&&words1d.size()>0||twords==0&&words1d.size()>0){
			for(int i=0;i<words.size();i++){
				longest_sentence += words.get(i).size();
			}

			longest_sentence /= words.size();

			ArrayList<String> temp = new ArrayList<String>();
			ArrayList<Word> temp2 = new ArrayList<Word>();
			int count = 0;
			for(int i=0;i<words.size();i++){

				ArrayList<String> s = words.get(i);
				char_counter2 = 0;

				mid = PApplet.floor(longest_sentence/2);
				float cursorx = 0;
				for(int j=0;j<s.size();j++){

					String a = s.get(j);
					int total = words.size() * s.size();
					float v =  mid - j;
					Word next = null;
					if(count<words1d.size()){ next = new Word(j - mid,j,i,cursorx,a.length(),a,count,words.size(),total,Bms);

					next.pos2.add(PApplet.abs(v*v));
					next.frequency = 1;
					next.cYpos.add(0);
					next.cXpos.add(0);
					next.ypos.add(i);
					//next.xpos.add(new ArrayList<Integer>());
					//next.xpos.get(0).add(j);
					next.xpos.add(j);

					if(applet.textWidth(Sentences.get(i))>1)
						next.sentenceSize.add(applet.textWidth(Sentences.get(i)));
					else next.sentenceSize.add(1.0f);
					Words1d.add(next);
					cursorx += applet.textWidth(a);

					if(!words1dsingle.contains(a)){
						Words1dsingle.add(next);
						words1dsingle.add(a);

					}else{
						int k = words1dsingle.indexOf(a);

						Word word =  Words1dsingle.get(k);
						//if(j<s.size()-1)word.nextWord.add(s.get(j+1));
						word.frequency ++;
						word.pos2.add(PApplet.abs(v*v));
						//word.occurence.set(j,1);
						if(applet.textWidth(Sentences.get(i))>1)
							word.sentenceSize.add(applet.textWidth(Sentences.get(i)));
						else word.sentenceSize.add(1.0f);
						//word.sentence_occurence.set(j,1);
						if(i>0&&Sentences.get(i-1).contains(a)){
							word.cYpos.add(word.cYpos.get(word.cYpos.size()-1)+1);
						}
						if(j>0){
							word.cXpos.add(word.cXpos.get(word.cXpos.size()-1)+1);
						}
						word.xpos.add(j);
						word.ypos.add(i);
						word.pos.add(next.p);
					}
					count++;
					twords++;
					if(count == words1d.size()){

						textcompleted = true;
					}}}}}
	};

	public void markovChain(){

	};

	public void getVariance(){
		int countv = 0;
		if(textcompleted&&!getvariance){
			

			for(int i=0;i<Words1dsingle.size();i++){
				Word w = Words1dsingle.get(i);
				countv ++;
				w.calcVariance();
				if(maxVariance<w.variance)maxVariance=w.variance;
				if(maxFrequency<w.frequency)maxFrequency=w.frequency;
				if(minVariance<w.variance)minVariance=w.variance;
				//w.setVariance();
				//for(int j=0;j<w.sentenceSize.size();j++){
				//  float v = w.sentenceSize.get(j);

				//  if(v>w.maxWordVariance)w.maxWordVariance = v;
				//}

			}

			applet.fill(255);
			for(int i=0;i<Words1dsingle.size();i++){
				Word w = Words1dsingle.get(i);
				w.setVariance();
			}

			if(countv>=Words1dsingle.size())getvariance = true;}
		else{
			//int count = 0;
			//for(int i=0;i<Words1dsingle.size();i++){
			//    Word w = Words1dsingle.get(i);
			//    //if(w.maxWordVariance==0){
			//      applet.text(w.maxWordVariance,400,50+10*count);
			//      count++;
			//    //}
			// }
		}
	};

	public void getEncodings(){
		applet.fill(255);
		if(getvariance){
			for(int i=0;i<Words1d.size();i++){

				Word w = Words1d.get(i);
				for(int j=0;j<w.sentence_occurence.size();j++){

					int k = w.occurence.get(j);
					applet.text(k,40+j*10,70+10*i);

				}}}
	};

	public void readText(){

		//	  float my = PApplet.map(applet.mouseY,0,text.length*12,0,applet.height);
		//	  for(int i=0;i<words.size();i++){
		//	    
		//	    ArrayList<String> s = words.get(i);
		//	    
		//	    float cursorx = 0;
		//	  
		//	    for(int j=0;j<s.size();j++){
		//	      
		//	      String s2 = s.get(j);
		//	      
		//	      applet.text(s2 + " ", cursorx ,230+10*i-my);
		//	      cursorx += applet.textWidth(s2);
		//	    }}

		for(int i=0;i<Sentences.size();i++){
			String s = Sentences.get(i);
			applet.fill(0);
			if(90+10*i<readingPane.y+readingPane.h)applet.text(s,10,90+10*i);
		}

		//for(int i=0;i<words1d.size();i++){
		//  String s = words1d.get(i);

		//  applet.text(s,10,90+10*i);
		//}
	};

	public void display(){
		if(toggle) {
			readingPane.displayTab();
			sliders.displayTab();
			workFlow.displayTab();
			
			inputFile.listen(sliders.getMouse());
			inputFolder.listen(sliders.getMouse());
			
			if(inputFile.value!=null) {
				text = applet.loadStrings(inputFile.value);
				readingPane.text = text;
				PApplet.println("vector display; load Text",inputFile.value,text);
				inputFile.value = null;
				
			}
			if(inputFolder.value!=null) {
				text = applet.loadStrings(inputFolder.value);
				inputFolder.value = null;
			}
			
			if(text!=null) {
				readingPane.setvScroll(0,text.length*12);
//				readingPane.sliderh.set(10,applet.width);
				readingPane.text = text;
				readingPane.txoff = 20;
				readingPane.tyoff = (int) (40 - readingPane.sliderv.value);
			}
			//else readingPane.text = null;
			/*{"Open File","Open Folder","Save Text","Save Data","Analyze",
				  "Find Similar","Load Data","Create Sentence","Restore tabs"}*/
			Button saveText = sliders.getButton(0,2);
			Button saveData = sliders.getButton(0,3);
			Button analyze = sliders.getButton(0,4);
			Button viewText = sliders.getButton(0,5);
			Button findSimilar = sliders.getButton(0,6);
			Button createSentence = sliders.getButton(0,7);
			Button loadData = sliders.getButton(0,8);
			Button resetWindows = sliders.getButton(0,9);
			
			if(sliders.click(0,6))workFlow.state = 0;
			
			if(sliders.click(0,7))workFlow.state = 1;
			
			tab t1 = workFlow.getState(0);
			t1.click(0,t1.getMouse());
			t1.click(1,t1.getMouse());
			tab t2 = workFlow.getState(1);
			t2.click(0,t1.getMouse());
			t2.click(1,t1.getMouse());
//			sliders.toggle(0,8);
//			if(sliders.getToggle(0,8))sliders.state = 2;
			
//			sliders.toggle(0,7);
//			if(sliders.getToggle(0,7))sliders.state = 2;
			
			if(saveText.click(sliders.getMouse())){

			}
			if(saveData.click(sliders.getMouse())){

			}
			if(analyze.click(sliders.getMouse())){
				PApplet.println("analyze");
				PApplet.println("words",words.size(),"text",text.length);
//				text = 
				classTextsp();
			}
			if(findSimilar.click(sliders.getMouse())){

			}
			if(createSentence.click(sliders.getMouse())){

			}
			if(resetWindows.click(sliders.getMouse())){

			}
			
			if(inputFile.value!=null){
				fileLocation = inputFile.value;
				inputFile.value = null;
				readingPane.sliderv.valuex = 0;
				readingPane.sliderh.valuex = 0;
			}
			if(inputFolder.values!=null){
				folderLocation = inputFolder.values[0];
				inputFolder.values = null;
			}
			if(fileLocation!=null){
				text = applet.loadStrings(fileLocation);
				savedFileLocation = fileLocation;
				fileLocation = null;
				//applet.println("file ", savedFileLocation);

			}
			if(folderLocation!=null){
				text = applet.loadStrings(folderLocation);
				savedFileLocation = folderLocation;
				folderLocation = null;
				//applet.println("first file ", folderLocation);

			}
		}
	};

	public void display(PGraphics canvas){
		if(toggle&&textcompleted){
			canvas.beginDraw();
			canvas.fill(0);
			float my = PApplet.map(applet.mouseY,0,text.length*12,0,applet.height);
			for(int i=0;i<words.size();i++){

				ArrayList<String> s = words.get(i);

				float cursorx = 0;

				for(int j=0;j<s.size();j++){

					String s2 = s.get(j);

					if(230+10*i-my<readingPane.y+readingPane.h)canvas.text(s2 + " ", cursorx ,230+10*i-my);
					cursorx += applet.textWidth(s2);
				}}
			canvas.endDraw();
		}else if(toggle){
			readingPane.displayTab();
			sliders.displayTab();
			workFlow.displayTab();
			//menu.draw();
			inputFile.listen();
			inputFolder.listen();
		}

	};

	public void plotText(){

		for(int i=0;i<Words1dsingle.size();i++){

			Word w = Words1dsingle.get(i);

			applet.fill(255);
			applet.stroke(0);
			applet.ellipse(w.x+w.variance + 400,w.y,5,5);
			//applet.text(w.variance,10,40+10*i);

		}

	};

	void findSimilar(String a){
	    
	    
        ArrayList<Word>temp = new ArrayList<Word>();
        
        if(Words1dsingle.size()==words1dsingle.size()){
          
    if(words1dsingle.contains(a)){
      
        int k = words1dsingle.indexOf(a);
        
        Word w1 = Words1dsingle.get(k);
        
    for(int i=0;i<Words1dsingle.size();i++){
      
      Word w2 = Words1dsingle.get(i);
      
     float deltav = PApplet.abs(w1.p - w2.p);
     float deltaf = PApplet.abs(w1.frequency - w2.frequency);
      
      applet.fill(255);
      if(deltaf < 0.000001&&w1.a!=w2.a)temp.add(w2);
      
    }}else{
      applet.fill(255);
      applet.text("Choose, new word",10,40);
    }}
    
    if(temp.size()>0){
      int y1 = 50;
      applet.fill(255);
      applet.text("|" + a + "|",10,y1);
      
      for(int i=0;i<temp.size();i++){
        
        Word w = temp.get(i);
        
        applet.text("|" + w.a + "|",10,50+10*i);
        applet.text(w.variance,100,50+10*i);
        applet.text(w.frequency,200,50+10*i);
      }
    }else{
      applet.fill(255);
        applet.text("Temp is empty",10,50);
    }
  };
  
  ArrayList<Word> findSimilar(String a,int b){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
          finit = true;
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        //unsortedSearch = new ArrayList<Word>();
        
        int k = words1dsingle.indexOf(a);
        Word w1 = Words1dsingle.get(k);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          ArrayList<String> s = words.get(w1.ypos.get(i));
          
          for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+b+1;j++){
            
            if(j>=0&&j<s.size()){
            int k2 = words1dsingle.indexOf(s.get(j));
            
            Word w2 = Words1dsingle.get(k2);
            if(w1.a!=w2.a){
              if(unsortedSearch.contains(w2)){
                int k1 = unsortedSearch.indexOf(w2);
                unsortedSearch.get(k1).searchFrequency++;
              }else if(w1.a!=w2.a+" "){
                w2.p = w1.xpos.get(i)+j;
                w2.searchFrequency++;
                unsortedSearch.add(w2);
            }}
         }}}
      
        foundS = true;
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
    if(textcompleted){
    if(foundS){
      temp = unsortedSearch;
    }
    
    }
    applet.fill(255);
    //println(0);
    applet.textSize(20);
    applet.text(temp.size(),20,50);
    applet.textSize(12);
    for(int i=0;i<temp.size();i++){
      applet.text("|"+temp.get(i).a+"| "+temp.get(i).frequency,20,100+20*i);
    }
    return temp;
  };
  
  ArrayList<Word> findSortedSimilar(String a,int b){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        //unsortedSearch = new ArrayList<Word>();
        
        int k = words1dsingle.indexOf(a);
        Word w1 = Words1dsingle.get(k);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          ArrayList<String> s = words.get(w1.ypos.get(i));
          
          for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+b+1;j++){
            
            if(j>=0&&j<s.size()){
            int k2 = words1dsingle.indexOf(s.get(j));
            
            Word w2 = Words1dsingle.get(k2);
            if(w1.a!=w2.a){
              if(unsortedSearch.contains(w2)){
                int k1 = unsortedSearch.indexOf(w2);
                unsortedSearch.get(k1).searchFrequency++;
              }else if(w1.a!=w2.a+" "){
                w2.p = w1.xpos.get(i)+j;
                w2.searchFrequency++;
                unsortedSearch.add(w2);
            }}}}}
      
        foundS = true;
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
      if(textcompleted){
        if(foundS){
          
          if(unsortedSearch.size()>0)applet.text(unsortedSearch.size(),10,40);
          else applet.text(sortedSearch.size(),10,40);
          
          Word w1 = null;
          
          while(unsortedSearch.size()>0){
            
            for(int j=unsortedSearch.size()-1;j>-1;j--){
              
              w1 = unsortedSearch.get(j);
              int n = -1;
              
              for(int i=unsortedSearch.size()-2;i>-1;i--){
                
                Word w2 = unsortedSearch.get(i);
                
                if(w1.searchFrequency>w2.searchFrequency){
                  w1 = w2;
                  n = i;
                }
              }
                
                if(n>-1)sortedSearch.add(unsortedSearch.remove(n));
                else if(unsortedSearch.size()>0)sortedSearch.add(unsortedSearch.remove(j));
          }
          
        }}
    
      if(sortedSearch.size()>0){
        
        temp = sortedSearch;
        if(sortedSearch.size()==0&&unsortedSearch.size()==0)temp = null;
        
      }}else {
      applet.text("Scanning not finnished", 10,50);
      temp = null;
    }
    applet.fill(255);
    //println("findsimsorted ",sortedSearch.size(),unsortedSearch.size());
    if(temp!=null){
      applet.textSize(20);
      applet.text(a,20,60);
      applet.textSize(12);
      for(int i=0;i<temp.size();i++){
        applet.text("|"+temp.get(i).a+"| "+temp.get(i).searchFrequency,20,100+20*i);
      }
    
    }
    return temp;
  };
  
  ArrayList<Word> findSimilar(String a,int b,float var){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        unsortedSearch = new ArrayList<Word>();
        
        int k = words1dsingle.indexOf(a);
        int y = 0;
        
        Word w1 = Words1dsingle.get(k);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          
          ArrayList<String> s = words.get(w1.ypos.get(i));
          
          for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+b+1;j++){
            
            boolean check = false;
            
            if(j>=0&&j<s.size()){
            int k2 = words1dsingle.indexOf(s.get(j));
            
            Word w2 = Words1dsingle.get(k2);
            float deltaf = PApplet.abs(w1.frequency - w2.frequency);
            
            if(w1.a!=w2.a){
              if(unsortedSearch.contains(w2)){
                int k1 = unsortedSearch.indexOf(w2);
                unsortedSearch.get(k1).searchFrequency++;
              }else if(w1.a!=w2.a+" "&&deltaf>var){
                w2.p = w1.xpos.get(i)+j;
                w2.searchFrequency++;
                unsortedSearch.add(w2);
            }}
            
            }}}
      
        foundS = true;
        //println("hello findsimilar end",foundS,unsortedSearch.size());
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
    if(textcompleted){
    if(foundS){
      temp = unsortedSearch;
      //println("hello findsimilar found",unsortedSearch.size());
    }
    
    }
    applet.fill(255);
    
    applet.textSize(20);
    applet.text(temp.size()+ " With Variance",20,50);
    applet.textSize(12);
    for(int i=0;i<temp.size();i++){
      applet.text("|"+temp.get(i).a+"|",20,100+20*i);
    }
    return temp;
  };
  
  ArrayList<Word> findSortedSimilar(String a,int b,float var){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        unsortedSearch = new ArrayList<Word>();
        
        int k = words1dsingle.indexOf(a);
        int y = 0;
        
        Word w1 = Words1dsingle.get(k);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          
          ArrayList<String> s = words.get(w1.ypos.get(i));
          
          for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+b+1;j++){
            
            boolean check = false;
            
            if(j>=0&&j<s.size()){
            int k2 = words1dsingle.indexOf(s.get(j));
            
            Word w2 = Words1dsingle.get(k2);
            float deltaf = PApplet.abs(w1.frequency - w2.frequency);
            
            if(w1.a!=w2.a){
              if(unsortedSearch.contains(w2)){
                int k1 = unsortedSearch.indexOf(w2);
                unsortedSearch.get(k1).searchFrequency++;
              }else if(w1.a!=w2.a+" "&&deltaf>var){
                w2.p = w1.xpos.get(i)+j;
                w2.searchFrequency++;
                unsortedSearch.add(w2);
            }}
            
            }}}
      
        foundS = true;
        //println("hello findsimilar end",foundS,unsortedSearch.size());
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
    if(textcompleted){
        if(foundS){
          
          if(unsortedSearch.size()>0)applet.text(unsortedSearch.size(),10,40);
          else applet.text(sortedSearch.size(),10,40);
          
          Word w1 = null;
          
          if(unsortedSearch.size()>0){
            
            for(int j=unsortedSearch.size()-1;j>-1;j--){
              
              w1 = unsortedSearch.get(j);
              int n = -1;
              
              for(int i=unsortedSearch.size()-2;i>-1;i--){
                
                Word w2 = unsortedSearch.get(i);
                
                if(w1.searchFrequency<w2.searchFrequency){
                  w1 = w2;
                  n = i;
                }}
                
                if(n>-1)sortedSearch.add(unsortedSearch.remove(n));
                else if(unsortedSearch.size()>0)sortedSearch.add(unsortedSearch.remove(j));
          }
          
        }}
    
      if(sortedSearch.size()>0){
        
        temp = sortedSearch;
        if(sortedSearch.size()==0&&unsortedSearch.size()==0)temp = null;
        
      }}else {
      applet.text("Scanning not finnished", 10,50);
      temp = null;
    }
    applet.fill(255);
    //println("findsimsorted ",sortedSearch);
    if(temp!=null){
      applet.textSize(20);
      applet.text(a,20,60);
      applet.textSize(12);
      for(int i=0;i<temp.size();i++){
        applet.text("|"+temp.get(i).a+"| "+temp.get(i).searchFrequency,20,100+20*i);
      }
    
    }
    return temp;
  };
  
  ArrayList<Word> findSimilar(String a,int b,int c){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
          finit = true;
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        
        int index = words1dsingle.indexOf(a);
        Word w1 = Words1dsingle.get(index);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          ArrayList<String> s = words.get(i);
             
              //println(s);
              for(int k=i;k<i+c+1;k++){
                 
                if(k>-1&&k<words.size()){
                  s = words.get(k);
              
          
              for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+1;j++){
                
                if(j>=0&&j<s.size()){
                int k2 = words1dsingle.indexOf(s.get(j));
                
                Word w2 = Words1dsingle.get(k2);
                if(w1.a!=w2.a){
                  if(unsortedSearch.contains(w2)){
                    int k1 = unsortedSearch.indexOf(w2);
                    unsortedSearch.get(k1).searchFrequency++;
                  }else if(w1.a!=w2.a+" "){
                    w2.p = w1.xpos.get(i)+j;
                    w2.searchFrequency++;
                    unsortedSearch.add(w2);
                }}
             }}}}}
      
        foundS = true;
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
    if(textcompleted){
    if(foundS){
      temp = unsortedSearch;
    }
    
    }
    applet.fill(255);
    //println(0);
    applet.textSize(20);
    applet.text(temp.size(),20,50);
    applet.textSize(12);
    for(int i=0;i<temp.size();i++){
      applet.text("|"+temp.get(i).a+"| "+temp.get(i).frequency,20,100+20*i);
    }
    return temp;
  };
  
  ArrayList<Word> findSortedSimilar(String a,int b,int c){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        if(!foundS&&!finit){
          unsortedSearch = new ArrayList<Word>();
          finit = true;
        }
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        
        
        int index = words1dsingle.indexOf(a);
        Word w1 = Words1dsingle.get(index);
        
        for(int i=0;i<w1.ypos.size();i++){
          
          ArrayList<String> s = words.get(i);
             
              //println(s);
              for(int k=i;k<i+c+1;k++){
                 
                if(k>-1&&k<words.size()){
                  s = words.get(k);
              
          
              for(int j=w1.xpos.get(i);j<w1.xpos.get(i)+1;j++){
                
                if(j>=0&&j<s.size()){
                int k2 = words1dsingle.indexOf(s.get(j));
                
                Word w2 = Words1dsingle.get(k2);
                if(w1.a!=w2.a){
                  if(unsortedSearch.contains(w2)){
                    int k1 = unsortedSearch.indexOf(w2);
                    unsortedSearch.get(k1).searchFrequency++;
                  }else if(w1.a!=w2.a+" "){
                    w2.p = w1.xpos.get(i)+j;
                    w2.searchFrequency++;
                    unsortedSearch.add(w2);
                }}
             }}}}}
      
        foundS = true;
      }}else{
        
        applet.text("Choose, new word",10,40);
      }}
    
    if(textcompleted){
        if(foundS){
          
          if(unsortedSearch.size()>0)applet.text(unsortedSearch.size(),10,40);
          else applet.text(sortedSearch.size(),10,40);
          
          Word w1 = null;
          
          while(unsortedSearch.size()>0){
            
            for(int j=unsortedSearch.size()-1;j>-1;j--){
              
              w1 = unsortedSearch.get(j);
              int n = -1;
              
              for(int i=unsortedSearch.size()-2;i>-1;i--){
                
                Word w2 = unsortedSearch.get(i);
                
                if(w1.searchFrequency<w2.searchFrequency){
                  w1 = w2;
                  n = i;
                }
              }
                
                if(n>-1)sortedSearch.add(unsortedSearch.remove(n));
                else if(unsortedSearch.size()>0)sortedSearch.add(unsortedSearch.remove(j));
          }
          
        }}
    
      if(sortedSearch.size()>0){
        
        temp = sortedSearch;
        if(sortedSearch.size()==0&&unsortedSearch.size()==0)temp = null;
        
      }}else {
      applet.text("Scanning not finnished", 10,50);
      temp = null;
    }
    applet.fill(255);
    //println("findsimsorted ",sortedSearch.size(),unsortedSearch.size());
    if(temp!=null){
      applet.textSize(20);
      applet.text(a,20,60);
      applet.textSize(12);
      for(int i=0;i<temp.size();i++){
        applet.text("|"+temp.get(i).a+"| "+temp.get(i).searchFrequency,20,100+20*i);
      }
    
    }
    return temp;
  };
  
  void findLeastSimilar(String a,float b){
      applet.fill(255);
        ArrayList<Word>temp = new ArrayList<Word>();
        
        if(textcompleted){
          
    if(words1dsingle.contains(a)){
      
      if(!foundS){
        int k = words1dsingle.indexOf(a);
        
        Word w1 = Words1dsingle.get(k);
        
    for(int i=0;i<Words1dsingle.size();i++){
      
      Word w2 = Words1dsingle.get(i);
      
     float deltav = PApplet.abs(w1.variance - w2.variance);
     float deltaf = PApplet.abs(w1.frequency - w2.frequency);
      
      if(deltav > b)unsortedSearch.add(w2);
      
    }foundS = true;}}else{
      
      applet.text("Choose, new word",10,40);
    }}
    
    if(textcompleted){
    if(foundS){
      if(unsortedSearch.size()>0)applet.text(unsortedSearch.size(),10,40);
      else applet.text(sortedSearch.size(),10,40);
      Word w1 = null;
      if(unsortedSearch.size()>0){
        
        
        for(int j=unsortedSearch.size()-1;j>-1;j--){
          w1 = unsortedSearch.get(j);
          int n = -1;
          for(int i=unsortedSearch.size()-2;i>-1;i--){
            Word w2 = unsortedSearch.get(i);
            
            if(w1.variance<w2.variance){
              w1 = w2;
              n = i;
            }}
            
            if(n>-1){
              
            sortedSearch.add(unsortedSearch.remove(n));
            
            }else if(unsortedSearch.size()>0)sortedSearch.add(unsortedSearch.remove(j));
      }
      
    }}
    
      if(sortedSearch.size()>0)
      for(int i=0;i<sortedSearch.size();i++){
        Word w = sortedSearch.get(i);
        
        applet.text('"'+w.a+'"',10,50+10*i);
        applet.text(w.variance,100,50+10*i);
        applet.text(w.frequency,200,50+10*i);
      }
       if(sortedSearch.size()==0&&unsortedSearch.size()==0) applet.text("Temp is empty",10,50);
    }
    else applet.text("Scanning not finnished", 10,50);
  };

	int getFrequency(String a){
		int i = -1;
		if(textcompleted){
			if(words1dsingle.contains(a)){
				int k = words1dsingle.indexOf(a);
				Word w = Words1dsingle.get(k);

				i = w.frequency;

			}else{
				applet.fill(255);
				applet.text("Choose another word!",300,70);
			}}
		else{
			applet.fill(255);
			applet.text("Scanning not finished",300,70);
		}

		applet.fill(255);
		applet.text(i,300,80);
		return i;
	};

	public void logic(){
		if(applet.mousePressed)mdown = true;
		if(!applet.mousePressed) mdown = false;

	};

	void createSentence(String Start,int size){
	    //applet.fill(255);
	    //  applet.text("Building Sentence",100,80);
	    if(mdown){
	      newSentence = new ArrayList<Word>();
	    }
	    else if(textcompleted){
	    //if(start == null && newSentence.size()==0){
	      //println("Loading1");
	     if(words1dsingle.contains(Start)){
	       
	       if(newSentence.size()==0){
	         start = Words1dsingle.get(words1dsingle.indexOf(Start));
	         newSentence.add(start);
	         //println("start");
	       }else if(start!=null){
	         float cursorx = 0;
	         
	         for(int i=0;i<100;i++){
	           foundS = false;
	           
	           wordPool = findSimilar(start.a,size);
	             
	             if(wordPool.size()>0){
	             Word nextWord = wordPool.get(PApplet.floor(applet.random(wordPool.size())));
	             newSentence.add(nextWord);
	             start = nextWord;
	             
	               if(nextWord.a.length()>1){
	                 char a = nextWord.a.charAt(nextWord.a.length()-1);
	               if(a=='!'||a=='?'||a=='.'){
	                 start = null;
	                 break;
	               }}else if(start.a=="!"||start.a=="?"||start.a=="."){
	            start = null;
	            break;
	          }}else {
	            start = null;
	            break;
	        }}
	        //start = null;
	      }}else{
	      applet.fill(255);
	      applet.text("Word Not Found",100,90);
	     }
	     
	     if (newSentence.size()>0){
	         applet.fill(255);
	         float cursorx = 0;
	         int h = 0;
	         if(start!=null)applet.text(start.a,100 ,70);
	         if(wordPool.size()==0)applet.text("No words in pool !",160 ,70);
	         for(int i=0;i<newSentence.size();i++){
	           
	           Word word = newSentence.get(i);
	           if(cursorx + applet.textWidth(word.a + " ")+100>applet.width){
	             h++;
	             cursorx = 0;
	           }
	            applet.text(word.a + " " ,100 +cursorx,90+(h*10));
	           cursorx += applet.textWidth(word.a + " ");
	           }
	       
	     }}
	    
	  };
	  
	  void createSentenceS(String Start,int size){
	    //applet.fill(255);
	    //  applet.text("Building Sentence",100,80);
	    if(mdown){
	      newSentence = new ArrayList<Word>();
	    }
	    else if(textcompleted){
	      
	     if(words1dsingle.contains(Start)){
	       
	       if(newSentence.size()==0){
	         start = Words1dsingle.get(words1dsingle.indexOf(Start));
	         newSentence.add(start);
	         //println("start");
	       }else if(start!=null){
	         float cursorx = 0;
	         //start = nextWord;
	         for(int i=0;i<100;i++){
	           foundS = false;
	           
	           wordPool = findSortedSimilar(start.a,1);
	             
	             if(wordPool.size()>0){
	             int kk = PApplet.floor(applet.random(wordPool.size()-1));
	             int first = 1;
	             if(wordPool.size()<2)first = 0;
	             int last = wordPool.size()-1;
	             Word nextWord = wordPool.remove(kk);
	             //start = nextWord;
	             newSentence.add(nextWord);
	             start = nextWord;
	             
	               if(nextWord.a.length()>1){
	                 char a = nextWord.a.charAt(nextWord.a.length()-1);
	               if(a=='!'||a=='?'||a=='.'){
	                 start = null;
	                 break;
	               }}else if(start.a=="!"||start.a=="?"||start.a=="."){
	            start = null;
	            break;
	          }}else {
	            start = null;
	            break;
	        }}
	        //start = null;
	      }}else{
	      applet.fill(255);
	      applet.text("Word Not Found",100,90);
	     }
	     
	     if (newSentence.size()>0){
	         applet.fill(255);
	         float cursorx = 0;
	         int h = 0;
	         if(start!=null)applet.text(start.a,100 ,70);
	         if(wordPool.size()==0)applet.text("No words in pool !",160 ,70);
	         for(int i=0;i<newSentence.size();i++){
	           
	           Word word = newSentence.get(i);
	           if(cursorx + applet.textWidth(word.a + " ")+100>applet.width){
	             h++;
	             cursorx = 0;
	           }
	            applet.text(word.a + " " ,100 +cursorx,90+(h*10));
	           cursorx += applet.textWidth(word.a + " ");
	           }
	       
	     }}
	    
	  };
	  
	  void createSentence(String Start,int size,float c){
	    
	    if(mdown){
	      newSentence = new ArrayList<Word>();
	    }
	    else if(textcompleted){
	      
	     if(words1dsingle.contains(Start)){
	       
	       if(newSentence.size()==0){
	         start = Words1dsingle.get(words1dsingle.indexOf(Start));
	         newSentence.add(start);
	       }else if(start!=null){
	         
	         float cursorx = 0;
	         
	         for(int i=0;i<100;i++){
	           foundS = false;
	           
	           wordPool = findSimilar(start.a,size,c);
	             
	             if(wordPool.size()>0){
	             Word nextWord = wordPool.get(PApplet.floor(applet.random(wordPool.size())));
	             newSentence.add(nextWord);
	             start = nextWord;
	             
	               if(nextWord.a.length()>1){
	                 char a = nextWord.a.charAt(nextWord.a.length()-1);
	               if(a=='!'||a=='?'||a=='.'){
	                 start = null;
	                 break;
	               }}else if(start.a=="!"||start.a=="?"||start.a=="."){
	            start = null;
	            break;
	          }}else {
	            start = null;
	            break;
	        }}
	      }}else{
	      applet.fill(255);
	      applet.text("Word Not Found",100,90);
	     }
	     
	     if (newSentence.size()>0){
	         applet.fill(255);
	         float cursorx = 0;
	         int h = 0;
	         if(start!=null)applet.text(start.a,100 ,70);
	         if(wordPool.size()==0)applet.text("No words in pool !",160 ,70);
	         for(int i=0;i<newSentence.size();i++){
	           
	           Word word = newSentence.get(i);
	           if(cursorx + applet.textWidth(word.a + " ")+100>applet.width){
	             h++;
	             cursorx = 0;
	           }
	            applet.text(word.a + " " ,100 +cursorx,90+(h*10));
	           cursorx += applet.textWidth(word.a + " ");
	           }
	       
	     }}
	    
	  };
	  
	  void createSentenceS(String Start,int size,float c){
	    
	    if(mdown){
	      newSentence = new ArrayList<Word>();
	    }
	    else if(textcompleted){
	      
	     if(words1dsingle.contains(Start)){
	       
	       if(newSentence.size()==0){
	         start = Words1dsingle.get(words1dsingle.indexOf(Start));
	         newSentence.add(start);
	       }else if(start!=null){
	         
	         float cursorx = 0;
	         
	         for(int i=0;i<100;i++){
	           foundS = false;
	           
	           wordPool = findSortedSimilar(start.a,size,c);
	             
	             if(wordPool.size()>0){
	             Word nextWord = wordPool.get(0);
	             newSentence.add(nextWord);
	             start = nextWord;
	             
	               if(nextWord.a.length()>1){
	                 char a = nextWord.a.charAt(nextWord.a.length()-1);
	               if(a=='!'||a=='?'||a=='.'){
	                 start = null;
	                 break;
	               }}else if(start.a=="!"||start.a=="?"||start.a=="."){
	            start = null;
	            break;
	          }}else {
	            start = null;
	            break;
	        }}
	      }}else{
	      applet.fill(255);
	      applet.text("Word Not Found",100,90);
	     }
	     
	     if (newSentence.size()>0){
	         applet.fill(255);
	         float cursorx = 0;
	         int h = 0;
	         if(start!=null)applet.text(start.a,100 ,70);
	         if(wordPool.size()==0)applet.text("No words in pool !",160 ,70);
	         for(int i=0;i<newSentence.size();i++){
	           
	           Word word = newSentence.get(i);
	           if(cursorx + applet.textWidth(word.a + " ")+100>applet.width){
	             h++;
	             cursorx = 0;
	           }
	            applet.text(word.a + " " ,100 +cursorx,90+(h*10));
	           cursorx += applet.textWidth(word.a + " ");
	           }
	       
	     }}
	    
	  };

	public void neural_inputs(){

		int count = 0;

		if(!inputs_complete){
			for(int i=0;i<words.size();i++){

				ArrayList<String> s = words.get(i);

				for(int j=0;j<s.size();j++){

					Word w = Words1d.get(count);
					count ++;

					int count2 = 0;
					for(int k=0;k<words.size();k++){

						ArrayList<String> s2 = words.get(k);

						for(int l=0;l<s.size();l++){

							Word w2 = Words1d.get(count2);

							Word[] n = {w,w2};

							ninputs.add(n);

						}}}}}if(count==Words1d.size())inputs_complete = true;
	};


	public void mostFrequent(){

	};

	float getVariance(String a){
		float i = -1;
		Word w1 = null;
		if(textcompleted){
			if(words1dsingle.contains(a)){
				int k = words1dsingle.indexOf(a);
				Word w = Words1dsingle.get(k);

				i = w.variance;
				w1 = w;

			}else{
				applet.fill(255);
				applet.text("Choose another word!",500,70);
			}}
		else{
			applet.fill(255);
			applet.text("Scanning not finished",500,70);
		}

		applet.fill(255);
		applet.text(i,500,80);
		if(w1!=null)applet.text(w1.frequency,500,90);
		return i;
	};

	public void getTFIDF(){

	};

	//public void normalize(Word w){

	//  for(int i=0;i<w.pos2.size();i++){


	//  }

	//};



	class Word{

		int x,y,p,t;
		float w,l,maxWordVariance,searchFrequency;
		String a;
		ArrayList<Integer> xpos = new ArrayList<Integer>();
		//ArrayList<ArrayList<Integer>> xpos = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ypos = new ArrayList<Integer>();
		ArrayList<Integer> cXpos = new ArrayList<Integer>();
		ArrayList<Integer> cYpos = new ArrayList<Integer>();
		ArrayList<Integer> flatRef = new ArrayList<Integer>();
		ArrayList<Integer> occurence = new ArrayList<Integer>();
		ArrayList<Integer> sentence_occurence = new ArrayList<Integer>();
		ArrayList<Float> sentenceSize = new ArrayList<Float>();
		float variance,sentenceSize_;
		int frequency;
		ArrayList<Integer> pos = new ArrayList<Integer>();
		ArrayList<Float> pos2 = new ArrayList<Float>();
		ArrayList<Word> previousWord = new ArrayList<Word>();
		ArrayList<Word> nextWord = new ArrayList<Word>();
		float textpos;
		boolean varianced,beginning,end;


		Word(int pp,int xx,int yy,float ww,int ll,String aa,BMScontrols bms){
			Bms = bms;
			applet = bms.applet;
			x = xx;
			y = yy;
			a = aa;
			p = pp;
			pos.add(pp);
			textpos = pp;
			//pos2.add(w);
			xpos.add(xx);
			ypos.add(yy);
			w = ww;
			l = ll;

		};

		Word(int pp,int xx,int yy,float ww,int ll,String aa,int tpos,int sentences,int total,BMScontrols bms){
			Bms = bms;
			applet = bms.applet;
			x = xx;
			y = yy;
			a = aa;
			p = pp;
			pos.add(pp);
			textpos = tpos;
			//for(int i=0;i<words1d.size();i++){
			//  occurence.add(0);
			//}
			//for(int i=0;i<longest_sentence;i++){
			//  sentence_occurence.add(0);
			//}

			//occurence.set(tpos,1);
			xpos.add(xx);
			ypos.add(yy);
			w = ww;
			l = ll;

		};

		Word(int pp,int xx,int yy,float v,float ww,int ll,String aa,int tpos,int sentences,int total,BMScontrols bms){
			Bms = bms;
			applet = bms.applet;
			x = xx;
			y = yy;
			a = aa;
			p = pp;
			variance = v;
			pos.add(pp);
			textpos = tpos;
			for(int i=0;i<words1d.size();i++){
				occurence.add(0);
			}
			for(int i=0;i<longest_sentence;i++){
				sentence_occurence.add(0);
			}

			occurence.set(tpos,1);
			xpos.add(xx);
			ypos.add(yy);
			w = ww;
			l = ll;

		};

		public void calcVariance(){

			if(!varianced){
				//max_size * Sentences.size();
				for(int i=0;i<pos2.size();i++){
					float v = pos2.get(i);
					float u = sentenceSize.get(i)*sentenceSize.get(i);
					variance += v;
					sentenceSize_ += u;
				}

				variance /= frequency;
				sentenceSize_ /= frequency;
				//if(maxWordVariance>0)
				//variance = applet.map(variance,0,maxWordVariance,0,1);
				//else applet.println(a);
				//variance = variance/frequency;

				for(int i=0;i<pos.size();i++){
					int v = pos.get(i);

					p += v;
					//applet.println(v);
					if(i==pos.size()-1){
						p/= pos.size();
						p = (int)PApplet.map(p,0,longest_sentence,0,1);
						//variance = applet.map(variance,0,max_size,0,1);
						varianced = true;
					}}}

		};

		public void setVariance(){
			//variance = applet.map(variance,0,maxVariance,0,100000);
			//variance = applet.map(variance,0,maxWordVariance*maxWordVariance,0,10);
		}


	};

	public void setBms(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
	};

	public void setBms(tab bms) {
		Bms = bms.Bms;
		applet = bms.Bms.applet;
	};
};
