package androidGui;

import ketai.ui.KetaiKeyboard;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PVector;

public class TextArea{
	BMScontrols Bms;
	PApplet applet;
	float x,y,w,h,bx,by,textSize = 12,spacing = 2,txoff,tyoff,lxpos,r1,r2,r3,r4;
	int last,lines,xpos,ypos,counter,blinkRate,keyDelay = 20;
	String [] textArea;
	String text = "",blankLine="",tempLine = "",label = "";
	boolean update,blink,visible = true,vScroll,hScroll;
	boolean mdown,mdown2,toggle,border,bgFill,localTheme,setMouse,keyBoardVisbile,getKey;
	char currentKey,key;
	int keyCode;
	int col;
	PVector mouse;
	PGraphics canvas,parentCanvas;
	tab parentTab;
	PFont myFont;

	public TextArea(float x_,float y_,float w_,float h_,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = x_;
		bx = x;
		y = y_;
		by = y;
		w = w_;
		h = h_;

		//applet.textSize(textSize);
		canvas = applet.createGraphics((int) w+10,(int) h+10);
		myFont = Bms.font;
//		myFont = applet.createFont("Arial", 12);
		//		applet.println(w,h,x,y,canvas.width);

		//while(applet.textWidth(blankLine)<w-applet.textWidth("")

	};

	public TextArea(float x_,float y_,float w_,float h_,String Label,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		label = Label;
		x = x_;
		bx = x;
		y = y_;
		by = y;
		w = w_;
		h = h_;

		canvas = applet.createGraphics((int) w,(int) h);
		myFont = Bms.font;
		//		applet.println(w,h,x,y,canvas.width);
		//applet.textSize(textSize);
		//while(applet.textWidth(blankLine)<w-applet.textWidth("")

	};

	public void draw(){
		logic();
		getCursor();
		//getKey();
		applet.fill(255);
		applet.strokeWeight(0);
		applet.rect(x,y,w,h,r1,r2,r3,r4);
		applet.stroke(0);
		applet.strokeWeight(2);

//		if(myFont!=null)applet.textFont(myFont);
		lxpos = 0;
		if(textArea!=null&&ypos<textArea.length&&xpos<=textArea[ypos].length())lxpos = applet.textWidth(textArea[ypos].substring(0,xpos));
		if(applet.frameCount%30==0&&blink)blink = false;
		else if(applet.frameCount%60==0&&!blink)blink = true;
		if(blink)applet.line(x+lxpos,y+ypos*(textSize+spacing),x+lxpos,y+ypos*(textSize+spacing)+textSize);
		if(label!=null){
			applet.fill(col);
			applet.text(label,x,y+ypos*(textSize+spacing));
		}

		if(textArea!=null){
			for(int i=0;i<textArea.length;i++){
				applet.fill(0,0,255,50);
				applet.noStroke();
				applet.rect(x,y+(textSize+spacing)*i,w,textSize);
			}
			for(int i=0;i<textArea.length;i++){
				String s = textArea[i];
				applet.fill(col);
				applet.text(s,x,y+(textSize+spacing)*i+textSize);
			}
		}
	};

	public void draw(boolean k){
		if(parentCanvas==null)draw(canvas);
		else draw(parentCanvas,true);
	};

	public void draw(PGraphics canvas){
		if(parentCanvas ==null){

			logic(true);
			getCursor(true);
			//getKey();

			if(canvas!=null){

				canvas.beginDraw();
				canvas.fill(255);
				canvas.strokeWeight(0);
				canvas.rect(0,0,w,h,r1,r2,r3,r4);
				canvas.stroke(0);
				canvas.strokeWeight(2);
//				canvas.textFont(myFont);
				if(label!=null){
					canvas.fill(col);
					canvas.text(label,0,0+(textSize));
					//applet.println(label);
				}
				applet.textSize(textSize);
				lxpos = 0;
				if(textArea!=null&&ypos<textArea.length&&xpos<=textArea[ypos].length())lxpos = applet.textWidth(textArea[ypos].substring(0,xpos));
				if(applet.frameCount%30==0&&blink)blink = false;
				else if(applet.frameCount%60==0&&!blink)blink = true;
				if(blink)canvas.line(0+lxpos,0+ypos*(textSize+spacing),0+lxpos,0+ypos*(textSize+spacing)+textSize);

				if(textArea!=null){

					canvas.fill(0,0,255,50);
					canvas.noStroke();
					for(int i=0;i<textArea.length;i++){

						canvas.rect(0,0+(textSize+spacing)*i,w,textSize);
					}
					canvas.fill(col);
					canvas.textSize(textSize);
//					canvas.textFont(myFont);
					for(int i=0;i<textArea.length;i++){
						String s = textArea[i];

						canvas.text(s,0+txoff,0+(textSize+spacing)*i+textSize+tyoff);
					}
				}
				canvas.endDraw();
				applet.image(canvas,x,y);
				//applet.println(canvas.width);
			}
		}else{
			draw(parentCanvas,true);
		}
	};

	public void draw(PGraphics scene,boolean b1){

		if(scene !=null){

			logic(true);
			getCursor(true);
			if(canvas!=null&&visible){
				canvas.beginDraw();
				canvas.fill(255);
				canvas.strokeWeight(0);
				canvas.rect(0,0,w,h,r1,r2,r3,r4);
				canvas.stroke(0);
				canvas.strokeWeight(2);
				if(label!=null){
//					canvas.textFont(myFont);
					canvas.textSize(textSize);
					canvas.fill(0);

					canvas.text(label,0,0+(textSize));
				}
				applet.textSize(textSize);
				//lxpos = 0;
				if(textArea!=null&&xpos>-1&&ypos>-1&&ypos<textArea.length
						&&xpos<=textArea[ypos].length())
					lxpos = applet.textWidth(textArea[ypos].substring(0,xpos));
				if(applet.frameCount%30==0&&blink)blink = false;
				else if(applet.frameCount%30==0&&!blink)blink = true;
				if(blink&&toggle)canvas.line(lxpos,0+ypos*(textSize+spacing),0+lxpos,0+ypos*(textSize+spacing)+textSize);

				if(textArea!=null){
					canvas.fill(0,0,255,50);
					canvas.noStroke();
					for(int i=0;i<textArea.length;i++){

						canvas.rect(0,0+(textSize+spacing)*i,w,textSize);
					}
					canvas.fill(col);
					canvas.textSize(textSize);
//					canvas.textFont(myFont);
					for(int i=0;i<textArea.length;i++){
						String s = textArea[i];

						canvas.text(s,0+txoff,0+(textSize+spacing)*i+textSize+tyoff);
					}
				}
				canvas.endDraw();
				//				scene.beginDraw();
				scene.image(canvas,(int)x,(int)y);
				//				scene.endDraw();
				//				if(b1)applet.image(scene,0,0);
			}
		}
	};

	public void update(){
		if(update){
			if(ypos==last){

			}else{
				int l = text.length()-1;
				textArea[last] = textArea[last]+text.charAt(l);
			}

			update = false;
		}
	};

	public void keyLogic(){

	};

	public void logic(){
		if(textArea ==null){
			textArea = new String[1];
			textArea[0] = "";

		}
		if(textArea!=null&&textArea.length>0){
			lines = textArea.length-1;
			last = textArea[lines].length()-1;
		}

		if(pos()&&applet.mousePressed&&!mdown&&!toggle) {

			toggle = true;
			mdown = true;
			KetaiKeyboard.toggle(applet);
		}

		if(pos()&&applet.mousePressed&&!mdown&&toggle) {

			toggle = false;
			mdown = true;
			KetaiKeyboard.toggle(applet);
		}


		if(vScroll){

		}
		if(hScroll){

		}
		if(mdown&&applet.mousePressed) {
			//			KetaiKeyboard.toggle(applet);
			mdown=false;
		}
	};

	public void logic(boolean b1){
		if(textArea ==null){
			textArea = new String[1];
			textArea[0] = "";

		}
		if(label!=null&&toggle)label = null;

		//if(pos(mouse))applet.println(mouse.x,mouse.y);
		if(pos(mouse)&&applet.mousePressed&&!mdown) {
			toggle = true;
			mdown = true;
			KetaiKeyboard.toggle(applet);
		}
		if(pos(mouse)&&applet.mousePressed&&!mdown&&!toggle) {

			toggle = true;
			mdown = true;
			KetaiKeyboard.toggle(applet);
		}

		if(vScroll){

		}
		if(hScroll){

		}
		if(mdown&&!applet.mousePressed) {
			//			KetaiKeyboard.toggle(applet);
			mdown=false;
		}
	};

	public void logic(PVector mouse){
		if(mouse!=null){
			if(textArea ==null){
				textArea = new String[1];
				textArea[0] = "";

			}
			if(textArea!=null&&textArea.length>0){
				lines = textArea.length-1;
				last = textArea[lines].length()-1;
			}

			if(pos(mouse)&&applet.mousePressed&&!mdown) {
				toggle = true;
				mdown = true;
				KetaiKeyboard.toggle(applet);
			}
			if(pos(mouse)&&applet.mousePressed&&!mdown&&!toggle) {
				
				toggle = true;
				mdown = true;
				KetaiKeyboard.toggle(applet);
			}


			if(vScroll){

			}
			if(hScroll){

			}
		}
		if(mdown&&!applet.mousePressed) {
			//			KetaiKeyboard.toggle(applet);
			mdown=false;
		}
	};

	public void getKey(){

		boolean b1 = ypos*(textSize+spacing)<h-(textSize+spacing)*2;
		boolean b2 = lxpos<w;
		if(applet.key!=applet.CODED&&applet.key!=applet.BACKSPACE&&applet.key!=applet.ENTER){
			String s = "";
			s+= applet.key;
			b2 = lxpos<w - applet.textWidth(s);
		}

		if(b2&&!b1&&applet.key!=applet.ENTER)b1 = true;
		if(applet.key==applet.CODED&&b1&&b2){
			applet.textSize(textSize);
			if(applet.key!=applet.CODED&&applet.key!=applet.BACKSPACE&&applet.key!=applet.ENTER){
				currentKey = applet.key;


				if(textArea==null){
					if(applet.key!=applet.CODED)

					textArea = new String[1];
					textArea[0] = text;
					xpos = text.length();
				}else{
					if(ypos<textArea.length&&ypos>-1){
						if(xpos==tempLine.length()){
							if(applet.key!=applet.CODED)
								applet.println("textArea key 04",applet.key,applet.keyCode);
							tempLine += currentKey;

							textArea[ypos] = tempLine;
							xpos = textArea[ypos].length();
							setText(textArea);
						}else if(xpos<tempLine.length()){
							if(applet.key!=applet.CODED)
								applet.println("textArea key 05",applet.key,applet.keyCode);
							String s1 = tempLine.substring(0,xpos);
							String s2 = "";
							if(xpos>2)textArea[ypos].substring(xpos-2,textArea[ypos].length()-1);
							else if(textArea[ypos].length()>0)
								textArea[ypos].substring(0,textArea[ypos].length()-1);

							tempLine = s1+currentKey;

							for(int i=xpos;i<textArea[ypos].length();i++){
								char a = textArea[ypos].charAt(i);
								tempLine+= a;
							}
							textArea[ypos] = tempLine;
							xpos =s1.length()+1;
							setText(textArea);
						}

					}
				}

			}
			else if(applet.key==applet.BACKSPACE){
				backSpace();
			}else if(applet.key==applet.ENTER){
				enter();
			}
			arrowKeys();
		}
	};

	public void getKey(boolean k1){
		boolean b3 = false;
		if(keyCode==19||keyCode==20||keyCode==21||keyCode==22)b3 = true;
		if(Bms.keyPressed) {
			key = applet.key;
//			currentKey = key;
			keyCode = applet.keyCode;
			applet.println("textArea getKey key:",key,keyCode);
		}
		
		if(applet.mousePressed&&!mdown2) {
			mdown2 = true;
		}		

		boolean b1 = ypos*(textSize+spacing)<h-(textSize+spacing)*2;
		boolean b2 = lxpos<w;
		
		if(Bms.keyPressed&&keyCode!=67&&key!=applet.ENTER&&!b3){
			
			String s1 = "";
			s1 += key;
			b2 = lxpos<w - applet.textWidth(s1);
		}
		if(b2&&!b1&&key!=applet.ENTER)b1 = true;
		
		
		if(Bms.keyPressed&&b1&&b2&&!b3){
			applet.textSize(textSize);
			if(keyCode!=67&&key!=applet.ENTER){
				addChar();
			}
			else if(keyCode==67){
				backSpace();
			}else if(key==applet.ENTER){
				enter();
			}
		}
		if(Bms.keyPressed&&b3)arrowKeys();
	};
	
	public void addChar() {
		currentKey = key;
		if(textArea==null){

			textArea = new String[1];
			textArea[0] = text;
			xpos = text.length();
		}else{
			if(ypos<textArea.length&&ypos>-1){
				if(xpos==tempLine.length()){
					tempLine += currentKey;

					textArea[ypos] = tempLine;
					xpos = textArea[ypos].length();
					setText(textArea);
				}else if(xpos<tempLine.length()){
					String s1 = tempLine.substring(0,xpos);
					String s2 = "";
					if(xpos>2)textArea[ypos].substring(xpos-2,textArea[ypos].length()-1);
					else if(textArea[ypos].length()>0)
						textArea[ypos].substring(0,textArea[ypos].length()-1);

					tempLine = s1+currentKey;

					for(int i=xpos;i<textArea[ypos].length();i++){
						char a = textArea[ypos].charAt(i);
						tempLine+= a;
					}
					textArea[ypos] = tempLine;
					xpos =s1.length()+1;
					setText(textArea);
				}

			}
		}
	};


	public void backSpace(){
		if(textArea!=null){
			applet.println("backspace",xpos,textArea[ypos].length());
			if(textArea.length==1||ypos==0){
				if(xpos==textArea[0].length()){
					if(xpos>0){
						tempLine = textArea[0].substring(0,textArea[0].length()-1);
						xpos = tempLine.length();
						textArea[0] = tempLine;
					}

				}else if(xpos>0){
					tempLine = textArea[0].substring(0,xpos-1);
					xpos = tempLine.length();
					for(int i=xpos+1;i<textArea[0].length();i++){
						tempLine+= textArea[0].charAt(i);
					}
					textArea[0] = tempLine;
				}
			}else{

				if(xpos==textArea[ypos].length()||xpos==0){

					if(xpos>0){

						tempLine = textArea[ypos].substring(0,textArea[ypos].length()-1);
						xpos = tempLine.length();
						textArea[ypos] = tempLine;
					}else{
						String s2 = textArea[ypos].substring(xpos,textArea[ypos].length());
						String[] temp = new String[textArea.length-1];
						//temp[ypos-1] = textArea[ypos-1];
						for(int i=0;i<ypos;i++){
							temp[i] = textArea[i];
						}
						temp[ypos-1]+= s2;
						for(int i=ypos+1;i<textArea.length;i++){
							temp[i-1] = textArea[i];
						}
						//
						tempLine = textArea[ypos];
						xpos = textArea[ypos-1].length();
						tempLine += s2;
						for(int i=0;i<textArea[ypos].length();i++){
							char a = textArea[ypos].charAt(i);
							tempLine += a;
						}
						textArea = temp;
						ypos --;
					}

				}else{
					if(xpos>0){
						tempLine = textArea[ypos].substring(0,xpos-1);
						xpos = tempLine.length();
						for(int i=xpos+1;i<textArea[ypos].length();i++){
							tempLine+= textArea[ypos].charAt(i);
						}
						textArea[ypos] = tempLine;
					}else{
						tempLine = textArea[ypos-1].substring(0,textArea[ypos-1].length());
						//xpos = tempLine.length();
						for(int i=xpos+1;i<textArea[ypos].length();i++){
							tempLine+= textArea[ypos].charAt(i);
						}
						textArea[ypos] = tempLine;
					}
				}
			}
		}
	};

	public void enter(){
		if(ypos ==textArea.length-1){
			if(xpos==tempLine.length()){
				String []temp = new String[textArea.length+1];
				for(int i=0;i<temp.length;i++){
					if(i<textArea.length)temp[i] = textArea[i];
					else {
						temp[i] = "";
					}
				}
				tempLine = "";
				textArea = temp;
				setText(textArea);
				ypos = textArea.length-1;
			}else{
				String []temp = new String[textArea.length+1];
				for(int i=0;i<textArea.length;i++){
					temp[i] = textArea[i];
				}
				String s1 = "",s2 = "";
				//for(int i=0;i<ypos-1;i++){
				//  temp[i] = textArea[i];
				//}
				temp[ypos] = textArea[ypos].substring(0,xpos);
				//temp[ypos] = "";
				temp[temp.length-1] = textArea[ypos].substring(xpos,textArea[ypos].length());


				tempLine = temp[ypos+1];
				//xpos = tempLine.length();
				textArea = temp;
				setText(textArea);
				ypos = textArea.length-1;
				//xpos = 0;
			}
			xpos = 0;

		}else{
			String []temp = new String[textArea.length+1];

			if(xpos==textArea[ypos].length()){

				for(int i=0;i<ypos+1;i++){
					temp[i] = textArea[i];
				}
				temp[ypos+1] = "";
				for(int i=ypos+2;i<temp.length;i++){
					temp[i] = textArea[i-1];
				}
				tempLine = "";
				textArea = temp;
				setText(textArea);
				ypos ++;
				xpos = 0;
			}
			else{
				for(int i=0;i<ypos;i++){
					temp[i] = textArea[i];
				}

				for(int i=ypos+1;i<textArea.length+1;i++){
					temp[i] = textArea[i-1];
				}

				if(xpos>textArea[ypos].length())xpos = textArea[ypos].length();
				temp[ypos] = textArea[ypos].substring(0,xpos);
				//xpos = temp[ypos].length();
				temp[ypos+1] = textArea[ypos].substring(xpos,textArea[ypos].length());
				for(int i=ypos+2;i<temp.length;i++){
					temp[i] = textArea[i-1];
				}
				tempLine = temp[ypos];
				textArea = temp;
				setText(textArea);
				//xpos = tempLine.length();
				xpos = 0;
				ypos ++;
			}

		}
	};

	public void arrowKeys(){
		if(keyCode==21&&xpos>0){
			xpos--;
		}else if(keyCode==21&&xpos==0&&ypos>0){
			ypos--;
			xpos = textArea[ypos].length();
			tempLine = textArea[ypos];
		}else if(keyCode==22&&xpos<textArea[ypos].length()){
			xpos++;
		}else if(keyCode==22&&xpos==textArea[ypos].length()&&ypos<textArea.length-1){
			xpos = 0;
			ypos++;
			tempLine = textArea[ypos];
		}else if(keyCode==19&&ypos>0){
			ypos--;
			tempLine = textArea[ypos];
			if(tempLine=="")xpos = 0;
			if(xpos>tempLine.length())xpos = tempLine.length();
		}else if(keyCode==20&&ypos<textArea.length-1){
			ypos++;
			tempLine = textArea[ypos];
			if(tempLine=="")xpos = 0;
			if(xpos>tempLine.length())xpos = tempLine.length();
		}
	};

	public void setText(String[] s){
		text = "";
		for(int i=0;i<s.length;i++){
			String s1 = s[i];
			if(s.length>0&&s1.length()>0&&s1.charAt(s1.length()-1)!='\n')s1 += "\n";
			//s[i] = s1;
			text+=s1;
		}
	};

	boolean pos(){
		boolean k = false;
		float max = 0;
		if(textArea!=null)max = textArea.length*(textSize+spacing);
		if(parentCanvas==null)return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
		else return applet.mouseX>x&&applet.mouseX+x<x+w&&applet.mouseY>y&&applet.mouseY+y<y+h;
	};

	boolean lpos(){
		boolean k = false;
		float max = textArea.length*(textSize+spacing);
		if(parentCanvas==null)return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+max;
		else return applet.mouseX>x&&applet.mouseX+x<x+w&&applet.mouseY>y&&applet.mouseY+y<y+max;

	};

	public boolean pos(PVector m){
		boolean k = false;
		if(m ==null)m = setMouse();
		if(parentCanvas==null)return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+h;
		else return m.x>x&&m.x+x<x+w&&m.y>y&&m.y+y<y+h;
	};

	public boolean lpos(PVector m){
		boolean k = false;
		float max = textArea.length*(textSize+spacing);
		if(parentCanvas==null)return m.x>x&&m.x<x+w&&m.y>y&&m.y<y+max;
		else return m.x>x&&m.x+x<x+w&&m.y>y&&m.y+y<y+max;
	};

	public int getCursor(){
		int k = -1;
		int kx = -1;
		if(pos()){
			//			if(applet.mousePressed)toggle = true;
			if(lpos()&&applet.mousePressed){
				//canvas.beginDraw();
				applet.textSize(textSize);
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(applet.mouseY,y,y+max,0,textArea.length));

				if(k<textArea.length){

					ypos = k;

					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;
						if(applet.mouseX<x+tw1&&applet.mouseX>x)break;
						else kx = i;
						if(i==textArea[k].length()-1&&applet.mouseX>x+tw1&&applet.mouseX>x)kx = textArea[k].length();
					}
					//canvas.endDraw();
					if(kx>-1)xpos = kx;
					tempLine = textArea[k];
					if(tempLine.length()==0)xpos = 0;
				}
				if(textArea!=null&&k<textArea.length&&textArea[k].length()==0)xpos = 0;
			}}
		//		else if(applet.mousePressed)toggle = false;

		return k;
	};

	public int getCursor(boolean b1){
		int k = -1;
		int kx = -1;
		if(pos(mouse)){
			//			if(applet.mousePressed)toggle = true;
			if(lpos(mouse)&&applet.mousePressed){
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(mouse.y,y,y+max,0,textArea.length));


				if(k<textArea.length){
					//int 
					ypos = k;
					applet.textSize(textSize);
					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;
						if(mouse.x<x+tw1&&mouse.x>x)break;
						else kx = i;

						if(i==textArea[k].length()-1&&mouse.x>x+tw1&&mouse.x>x)kx = textArea[k].length();
					}
					if(kx>-1)xpos = kx;

					//if(tempLine.length()==0)xpos = 0;
				}
				if(textArea!=null&&k<textArea.length&&textArea[k].length()==0)xpos = 0;
			}}
		//		else if(applet.mousePressed)toggle = false;

		return k;
	};

	public int getCursor(PVector mouse){
		int k = -1;
		int kx = -1;
		if(pos(mouse)){
			//			if(applet.mousePressed)toggle = true;
			if(lpos(mouse)&&applet.mousePressed){
				float max = textArea.length*(textSize+spacing);
				float tw = applet.textWidth(textArea[ypos]);
				k = applet.floor(applet.map(mouse.y,y,y+max,0,textArea.length));


				if(k<textArea.length){

					ypos = k;
					applet.textSize(textSize);
					String s = "";
					for(int i=0;i<textArea[k].length();i++){
						s += textArea[k].charAt(i);
						float tw1 = applet.textWidth(s)-5;

						if(mouse.x<x+tw1&&mouse.x>x)break;
						else kx = i;

						if(i==textArea[k].length()-1&&mouse.x>x+tw1&&mouse.x>x)kx = textArea[k].length();
					}
					if(kx>-1)xpos = kx;
				}
				tempLine = textArea[k];
			}}
		//		else if(applet.mousePressed)toggle = false;

		return k;
	};

	public PVector setMouse(){
		if(mouse==null){
			mouse = new PVector(applet.mouseX,applet.mouseY);
		}
		else {

			mouse.x = applet.mouseX;
			mouse.y = applet.mouseY;
		}
		return mouse;
	};

	public void addLine() {
		String []temp = new String[textArea.length];
		for(int i=0;i<textArea.length;i++) {
			temp[i] = textArea[i];
		}
		tempLine = "";
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};

	public void addLineAt(int a) {
		String []temp = new String[textArea.length];
		for(int i=0;i<a;i++) {
			temp[i] = textArea[i];
		}
		temp[a] = "";
		tempLine = "";
		for(int i=a;i<textArea.length;i++) {
			temp[i] = textArea[i-1];
		}
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};


	public void addLineAt(int a,int j) {
		String []temp = new String[textArea.length];
		for(int i=0;i<a;i++) {
			temp[i] = textArea[i].substring(0,j);
		}
		temp[a] = "";
		for(int i=xpos;i<textArea[ypos].length();i++) {
			temp[a] += textArea[ypos].charAt(i);
		}
		tempLine = temp[a];
		for(int i=a;i<textArea.length;i++) {
			temp[i] = textArea[i-1];
		}
		textArea = temp;
		ypos = textArea.length-1;
		textArea[ypos] = "";
		xpos = 0;

	};

	//public float

	public void setRadius(float a) {

		r1 = a;
		r2 = a;
		r3 = a;
		r4 = a;
	};

	public void setRadius(float a,float b,float c,float d) {

		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
	};

	public void setTab(tab t){
		Bms = t.Bms;
		applet = t.applet;
		parentTab = t;
		parentCanvas = t.canvas;
		setMouse = true;
	};

	public void reset() {
		textArea = new String[1];
		textArea[0] = "";
		tempLine = "";
		text = "";
		xpos = 0;
		ypos = 0;


	};

	public void setPos(float a,float b) {

		x = a;
		bx = a;
		y = b;
		by = b;
	};

	public String getValue() {
		float a;
		if(tempLine.length()>0) {
			return tempLine;
		}else return null;
	};

	public void save() {


	};

};