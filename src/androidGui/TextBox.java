package androidGui;

import java.util.ArrayList;

import ketai.ui.KetaiKeyboard;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class TextBox {
	BMScontrols Bms;
	PApplet applet;
	int id, cols, rows, size, t, timer = 31,timer2 = 201, blkrate = 30, t1 = blkrate, t2 = blkrate, start, end, hcount,
			index, lindex, vindex = -1, hindex = -1, windex, vpos = 0, hpos, sltcounter, vcount = 0,delay=10,
			delay2 = 20,maxCount = 30,maxCount2 = 200;
	public float x, y, w, h, bx,by,bw,bh, textsize = 10, twidth = 0, posx, posy, tposx, tposy, strposx, strposy, 
			tbwidth,strwidth, strwidth2, strheight, cursorx, cursory, crwidth, totalwidth,xoff,xOffset,yOffset,r1,r2
			,r3,r4;
	String label, text, label_backup, cboard = "";
	public boolean drag, resize, hover, border, background, hidden, fill = true, init, ready, label_bool, clear,
			copied, tbox = false, full, tsize,dragtext,mdown, onfocus,showLabel,parentCanvas,firstPress,getChar,
			selectAll,ctrl,copyClipboard,clipboardToggle,debug,kdown,kdown2,kup,toggle;
	public Menu toolBox;
	public Button child;
	public ArrayList<Letter> textbox = new ArrayList<Letter>();
	ArrayList<Letter> tm0 = new ArrayList<Letter>();
	ArrayList<Letter> tm1 = new ArrayList<Letter>();
	public String textBox = "";
	String temp = "";
	public float value;
	ArrayList<Integer> counted = new ArrayList<Integer>();
	ArrayList<Integer> lines = new ArrayList<Integer>();
	ArrayList<Integer> dragh = new ArrayList<Integer>();
	ArrayList<String> saveValues = new ArrayList<String>();
	Letter b = null;
	PVector mouse;
	public int col , col2;

	Window parent;

	public TextBox(float X, float Y, float WW, float HH, int Cols) {

		x = X;
		y = Y;
		w = WW;
		h = HH;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		cols = Cols;
		totalwidth = w;
		size = textbox.size();
		textsize = h-4;
		lines.add(0);
		tbox = false;
	};

	public TextBox(float X, float Y, float WW, float HH, int Cols, String Label) {

		x = X;
		y = Y;
		w = WW;
		h = HH;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		cols = Cols;

		totalwidth = w;
		size = textbox.size();
		label = Label;
		label_backup = Label;
		lines.add(0);
		label_bool = true;
		//textBox = label;
		showLabel = true;
		textsize = h-4;
		toolBox = new Menu(x+1, y-20, 200-1, 20);
	};

	public TextBox(float X, float Y, float WW, float HH, int Cols,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		x = X;
		y = Y;
		w = WW;
		h = HH;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		cols = Cols;

		totalwidth = w;
		size = textbox.size();
		lines.add(0);
		textsize = h-4;
		initColors();
		//toolBox = new Menu(x+1, y-20, 200-1, 20,bms);
		//toolBox.setBms(bms);
	};

	public TextBox(float X, float Y, float WW, float HH, int Cols, String Label,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		x = X;
		y = Y;
		w = WW;
		h = HH;
		bx = x;
		by = y;
		bw = w;
		bh = h;
		cols = Cols;

		totalwidth = w;
		size = textbox.size();
		label = Label;
		label_backup = Label;
		lines.add(0);
		label_bool = true;
		//textBox = label;
		showLabel = true;
		textsize = h-4;
		initColors();
		//toolBox = new Menu(x+1, y-20, 200-1, 20,bms);
		//toolBox.setBms(bms);
	};

	public TextBox(float X, float Y, float WW, float HH, int Cols, Boolean N) {

		x = X;
		y = Y;
		w = WW;
		h = HH;
		bx = x;
		by = y;
		bw = w;
		bh = h;

		cols = Cols;
		totalwidth = w;
		size = textbox.size();
		lines.add(0);
		label_bool = true;
		textsize = h;
		tbox = N;
	};


	public void init() {

	};

	public void initColors() {

		col = applet.color(0, 255, 73);
		col2 = applet.color(0, 255, 73,100);
	};

	public void draw() {
		//		if(applet.mousePressed)applet.println("textbox draw");
		if(debug)applet.println("tbox draw start");
		twidth = 0;
		box();
		if(debug)applet.println("tbox draw start");
		getCursor();
		if(debug)applet.println("tbox draw start");
		mpos();
		if(debug)applet.println("tbox draw start");
		nav();
		if(debug)applet.println("tbox draw start");
		conditionals();
		if(debug)applet.println("tbox draw start");
		highlight();
		if(debug)applet.println("tbox draw start");
		selectall();
		if(debug)applet.println("tbox draw start");
		error();
		if(debug)applet.println("tbox draw start");
		getKey();
		if(debug)applet.println("tbox draw start");
		if (textBox!=""&&Bms.key== PConstants.ENTER) {

			try {
				value = (float) Double.parseDouble(textBox);
			}catch(NumberFormatException nfe) {
				applet.println("textbox no number");
			}
		}
		//debug();
		if(showLabel&&label!=null){
			applet.textSize(textsize);
			applet.fill(0);
			applet.text(label,x,y+textsize - 5);
			applet.textSize(12);
			if(mouse!=null&&applet.mousePressed&&pos()||toggle)showLabel = false;
			else if(applet.mousePressed&&pos())showLabel = false;

		}
		//----------------------------------------------------- amend letters
		xOffset = 0;
		if(debug)applet.println("tbox draw label");

		float c1 = 0;
		int cindex = -1;
		twidth = cursorx;

		for (int i=0; i<textBox.length(); i++) {

			Letter a = null;
			String b = PApplet.str(textBox.charAt(i));

			if (textbox.size()==textBox.length()) {
				a = textbox.get(i);
			} else {
				a = new Letter(b, x, y,Bms);
				textbox.add(a);
			}

			float c2 = applet.textWidth(textBox.substring (0, i));

			if (a!=null) {
				strwidth2 = (c2 + applet.textWidth(a.l)<w)? c2 + applet.textWidth(a.l): c2%w + applet.textWidth(a.l);
				//if (c1 + a.w > w)c1=0;
				if (c1==0) {
					cindex++;
					a.id = i;
					a.vpos = cindex;
					boolean k = lines.contains(i);
					if (!k)lines.add(i);
				}
			}

			if (a==null&&b!=null) {
				a = new Letter(b, x, y,Bms);
				if (a.id!=i)a.id = i;
				a.y = y;
				a.x = x+c1;
				a.vpos = cindex;
				textbox.add(a);
			}

			if (a.l!=b) { 
				textbox.get(i).l = b;
				a.w = applet.textWidth(b);
				if (a.id!=i)a.id = i;
				a.y = y;
				a.x = x+c1;
				a.vpos = cindex;
			}
			applet.fill(a.col2);
			applet.textSize(textsize);

			if(a.x + xOffset<x||a.x+xOffset>x+w)a.visible = false;
			if(a.visible)applet.text(a.l, a.x, a.y+textsize);
			c1 += applet.textWidth(a.l);
			applet.textSize(12);
			if (selectAll&&toggle&&dragh.size()==textBox.length()) {

				if (dragh.get(i)!= i) dragh.set(i, i);
			}
		}

		//-------------------------------------------------
		if(dragh.size()>0){
			for (int j=0; j<dragh.size(); j++) {
				Letter b = textbox.get(dragh.get(j));
				applet.fill(0, 0, 255, 125);
				applet.noStroke();
				applet.rect(b.x, b.y, b.w, b.h,r1,r2,r3,r4);
			}}
		if (!applet.mousePressed)dragh = new ArrayList<Integer>();
		applet.strokeWeight(1);
		applet.noStroke();
		//	    if(applet.frameCount%10==0)applet.println("textbox drawend");
	};

	public void draw(PGraphics canvas) {

		twidth = 0;
		box(canvas);
		getCursor(canvas);
		mpos(mouse);
		nav();
		conditionals(canvas);
		highlight(canvas);
		selectall();
		error(canvas);
		if(applet.mousePressed&&!pos(mouse))toggle = false;
		if(toggle)getKey(canvas);

		if (textBox!=""&&Bms.key== PConstants.ENTER) {
			//	    	try {
			//	    		value = (float) Double.parseDouble(textBox);
			//	    	}catch(NumberFormatException nfe) {
			//	    		applet.println("textbox no number");
			//	    	}
		}
		//debug();
		if(showLabel){
			canvas.textSize(textsize);
			canvas.fill(0);
			canvas.text(label,x,y+textsize);
			canvas.textSize(12);
			if(applet.mousePressed&&pos(mouse))showLabel = false;
		}
		//----------------------------------------------------- amend letters
		xOffset = 0;
		for (int i=0; i<textBox.length(); i++) {
			Letter a = null;
			if(a!=null&&a.x>x+w)xOffset += applet.textWidth(a.l)+3;
		}

		float c1 = 0;
		int cindex = -1;
		twidth = cursorx;

		for (int i=0; i<textBox.length(); i++) {

			Letter a = null;
			String b = PApplet.str(textBox.charAt(i));

			if (textbox.size()==textBox.length())a = textbox.get(i);
			else {
				a = new Letter(b, x, y,Bms);
				textbox.add(a);
			}

			float c2 = applet.textWidth(textBox.substring (0, i));

			if (a!=null) {
				strwidth2 = (c2 + applet.textWidth(a.l)<w)? c2 + applet.textWidth(a.l): c2%w + applet.textWidth(a.l);
				if (c1==0) {
					cindex++;
					a.id = i;
					a.vpos = cindex;
					boolean k = lines.contains(i);
					if (!k)lines.add(i);
				}}

			if (a==null&&b!=null) {
				a = new Letter(b, x, y,Bms);
				if (a.id!=i)a.id = i;
				a.y = y;
				a.x = x+c1;
				a.vpos = cindex;
				textbox.add(a);
			}

			if (a.l!=b) { 
				textbox.get(i).l = b;
				a.w = applet.textWidth(b);
				if (a.id!=i)a.id = i;
				a.y = y;
				a.x = x+c1;
				a.vpos = cindex;
			}
			canvas.fill(a.col2);
			canvas.textSize(textsize);

			if(a.x + xOffset<x||a.x+xOffset>x+w)a.visible = false;
			if(a.visible)canvas.text(a.l, a.x, a.y+textsize);
			c1 += applet.textWidth(a.l)+3;
			canvas.textSize(12);
			if (selectAll&&toggle&&dragh.size()==textBox.length()) {

				if (dragh.get(i)!= i) dragh.set(i, i);
			}}

		//-------------------------------------------------
		if(dragh.size()>0){
			for (int j=0; j<dragh.size(); j++) {
				Letter b = textbox.get(dragh.get(j));
				canvas.fill(0, 0, 255, 125);
				canvas.noStroke();
				canvas.rect(b.x, b.y, b.w, b.h,r1,r2,r3,r4);
			}}
		if (!applet.mousePressed)dragh = new ArrayList<Integer>();
		canvas.strokeWeight(1);
		canvas.noStroke();
	};

	public void selectall() {
		applet.fill(0);
		//if(selectAll){applet.fill(0);applet.text("Select all", 100,200);}
		if (toggle&&ctrl&&selectAll)applet.text("Select all", 100, 200);
		//	    if(applet.frameCount%10==0)applet.println("textbox selectall");
	};

	public void save(){

	};

	public void load(){

	};

	String getValue() {
		return textBox;
	};

	ArrayList getObject() {
		return textbox;
	};

	public void highlight() {
		float c = 0;
		int d = 0;

		if (textbox.size()>0) {

			for (int i=0; i<textbox.size(); i++) {
				Letter a = textbox.get(i);
				applet.noStroke();
				if (a.pos()) {
					a.col = applet.color(255, 255, 0);
					applet.fill(a.col);
					applet.rect(a.x, a.y, a.w, a.h);
				} else a.col  = applet.color(0);
				if (applet.mousePressed&&a.pos()) {
					vindex = a.vpos;
					hindex = a.id;
					cursorx = a.x-x+a.w;
					cursory = a.y;
					boolean n = dragh.contains(i);

					if (!n) dragh.add(i);
					//applet.text(a.id, a.x + x, a.y);
				}}}
		//	    if(applet.frameCount%10==0)applet.println("textbox highlight");
	};

	public void highlight(PGraphics canvas) {
		float c = 0;
		int d = 0;

		if (textbox.size()>0) {

			for (int i=0; i<textbox.size(); i++) {
				Letter a = textbox.get(i);
				canvas.noStroke();
				if (a.pos(mouse)) {
					a.col = applet.color(255, 255, 0);
					canvas.fill(a.col);
					canvas.rect(a.x, a.y, a.w, a.h);
				} else a.col  = applet.color(0);
				if (applet.mousePressed&&a.pos(mouse)) {
					vindex = a.vpos;
					hindex = a.id;
					cursorx = a.x-x+a.w;
					cursory = a.y;
					boolean n = dragh.contains(i);

					if (!n) dragh.add(i);
					//applet.text(a.id, a.x + x, a.y);
				}}}
	};


	public void error() {
		//boolean tsize = true;
		if (Bms.clipBoard!=null&&Bms.clipBoard.length()>0&&applet.textWidth(Bms.clipBoard)+applet.textWidth(textBox)*textsize/12>totalwidth&&copyClipboard&&clipboardToggle)tsize = true;

		if (toggle) {
			if (tsize) {
				String message = "ClipBoard too large";
				float l = applet.textWidth(message);
				applet.stroke(0);
				applet.fill(255);
				applet.rect(applet.width/2-l/2+200, applet.height/2-textsize, l*2, textsize*2,r1,r2,r3,r4);
				applet.fill(0);
				applet.text(message, applet.width/2+200, applet.height/2);
			}

			if (tsize&&applet.mousePressed)tsize = false;
			//	      if(applet.frameCount%10==0)applet.println("textbox error");
		}
	};

	public void error(PGraphics canvas) {
		//boolean tsize = true;
		if (Bms!=null&&Bms.clipBoard!=null&&Bms.clipBoard.length()>0&&applet.textWidth(Bms.clipBoard)+applet.textWidth(textBox)*textsize/12>totalwidth&&copyClipboard&&clipboardToggle)tsize = true;

		if (toggle) {
			if (tsize) {
				String message = "ClipBoard too large";
				float l = applet.textWidth(message);
				canvas.stroke(0);
				canvas.fill(255);
				canvas.rect(applet.width/2-l/2+200, applet.height/2-textsize, l*2, textsize*2,r1,r2,r3,r4);
				canvas.fill(0);
				canvas.text(message, applet.width/2+200, applet.height/2);
			}

			if (tsize&&applet.mousePressed)tsize = false;
		}
	};

	public void getKey(){

		if(toggle){
			//	    	if(applet.frameCount%10==0)applet.println("textbox getkeys tart");
			String clipboard = null;
			Letter current = null;

			String a = getChar();
			String tm = "";
			String tm2 = "";
			tm0 = new ArrayList<Letter>();
			tm1 = new ArrayList<Letter>();
			float current_width = applet.textWidth(textBox);

			if(textbox.size()>0&&hindex!=-1)current = textbox.get(hindex);
			if(textbox.size()>0&&hindex==-1)current = textbox.get(textbox.size()-1);

			if(!clipboardToggle)cboard = "";

			if(cboard != Bms.clipBoard&&copyClipboard){ 
				clipboard = Bms.clipBoard;
				cboard = Bms.clipBoard;
			}
			else{ clipboard = null;}
			float delay = delay2;
			if(!copyClipboard&&!clipboardToggle){

				timer --;
				Letter l = null;
				if(a!=null) l = new Letter(a,x+strwidth,y + vcount * textsize,Bms);
				if(a!=null&&getChar&&Bms.key!=PConstants.BACKSPACE&&(current_width+ l.w<totalwidth)){

					l.id = hindex +1;

					if(hindex==-1){
						if(textbox.size()==0){
							l.y = y;
							l.x = x;
							textbox.add(l);
							textBox += a;
						}
						else if(l.vpos < rows||l.x+l.w<x+w){
							l.y = y + vcount * textsize;
							cursory = l.y;
							textbox.add(l);
							textBox += a;

						}}else{

							Letter end = textbox.get(textbox.size()-1);
							Letter n = new Letter(a,cursorx,cursory,Bms);
							if((hindex!=textbox.size()-1||hindex!=-1)&&getChar){

								Letter b = textbox.get(hindex);
								l.id = b.id ;

								n.x = cursorx ;
								if(hindex-1> 0)l.y = y + textbox.get(hindex-1).vpos * textsize;
								else l.y = y; cursory = y;

								if(hindex<textBox.length())tm = textBox.substring ( 0, hindex + 1);
								tm += a;
								if(hindex<textBox.length())tm2  = textBox.substring ( hindex + 1, textBox.length()  );
								textBox = tm + tm2;
								hindex ++;
								if(hindex < textbox.size()-1)cursorx = textbox.get(hindex+1).x;
							}}


				}
				else if(applet.keyPressed && Bms.key==PConstants.BACKSPACE&&getChar){delete();}
			}
			else if(copyClipboard&&clipboardToggle&&!tsize){

				if(hindex>-1){
					if(hindex<textBox.length())tm = textBox.substring ( 0, hindex + 1  );
					if(hindex<textBox.length())tm2  = textBox.substring ( hindex + 1, textBox.length()  );
					tm += cboard;
					tm += tm2;

					textBox = tm;

				}else{

					for (int i=0;i<cboard.length();i++){
						String b = PApplet.str(cboard.charAt(i));
						textBox += b;
					}}
				clipboardToggle = false;
				copyClipboard = false;
			}}
		//	    if(applet.frameCount%10==0)applet.println("textbox getkey");
	};

	//	public void getKey(PGraphics canvas) {
	//
	//		if(pos(mouse)) {
	//			getKey();  
	//		}
	//	};

	public void getKey(PGraphics canvas) {

		Boolean k = false;
		if (pos(mouse)||toggle) {
			String clipboard = null;
			Letter current = null;

			float current_width = applet.textWidth(textBox)*textsize/12;

			if (textbox.size()>0&&hindex!=-1)current = textbox.get(hindex);
			if (textbox.size()>0&&hindex==-1)current = textbox.get(textbox.size()-1);

			if (!clipboardToggle)cboard = "";

			if (cboard != Bms.clipBoard&&copyClipboard) { 
				clipboard = Bms.clipBoard;
				cboard = Bms.clipBoard;
			} else clipboard = null;

			String a = getChar();
			String tm = "";
			String tm2 = "";
			tm0 = new ArrayList<Letter>();
			tm1 = new ArrayList<Letter>();

			if (!copyClipboard&&!clipboardToggle) {

				timer --;
				Letter l = null;

				if (a!=null) l = new Letter(a, x+strwidth, y + vcount * textsize,Bms);
				if (a!=null&&getChar&&!exclusion()&&Bms.key!=PConstants.BACKSPACE&&Bms.key!=PConstants.ENTER) {

					l.id = hindex +1;

					if (hindex==-1) {
						if (textBox.length()<cols) {
							l.y = y + vcount * textsize;
							cursory = l.y;
							textbox.add(l);
							textBox += a;
						}} else {

							Letter end = textbox.get(textbox.size()-1);
							Letter n = new Letter(a, cursorx, cursory,Bms);
							if ((hindex!=textbox.size()-1||hindex!=-1)&&getChar) {

								Letter b = textbox.get(hindex);
								l.id = b.id ;

								n.x = cursorx ;
								if (hindex-1> 0)l.y = y + textbox.get(hindex-1).vpos * textsize;
								else l.y = y; 
								cursory = y;

								tm = textBox.substring ( 0, hindex + 1);
								tm += a;
								tm2  = textBox.substring ( hindex + 1, textBox.length()  );
								textBox = tm + tm2;
								hindex ++;
								if (hindex < textbox.size()-1)cursorx = textbox.get(hindex+1).x;
							}
						}

					if (timer<=0)timer = 21;

				} else if (applet.keyPressed && Bms.key==PConstants.BACKSPACE&&getChar) {
					delete();
				} else if (applet.keyPressed && Bms.keyCode==PConstants.ENTER&&getChar) {
					if(textBox!="")value = Float.parseFloat(textBox);
				}
			} else if (copyClipboard&&clipboardToggle&&getChar) {
				if (!tsize) {
					if (timer<=0) {
						timer = 21;
					}
					if (hindex>-1) {
						tm = textBox.substring ( 0, hindex  );
						tm2  = textBox.substring ( hindex, textBox.length()  );

						if (hindex>-1) {
							if (hindex<textBox.length())tm = textBox.substring ( 0, hindex + 1  );
							if (hindex<textBox.length())tm2  = textBox.substring ( hindex + 1, textBox.length());
							tm += cboard;
							tm += tm2;

							textBox = tm;
						}
					} else {

						for (int i=0; i<cboard.length(); i++) {
							String b = PApplet.str(cboard.charAt(i));
							textBox += b;
						}}}}}
	};

	public void dragText(){

	};

	public void getCursor() {

		getClick();
		//cursor(HAND);
		Boolean k = false;
		if(mouse!=null&&pos())k = true;
		if (vindex>-1) {
		}
		if (hindex==-1) {
			if (textbox.size()==0) {
				cursory = y;
				cursorx = 1;
				vindex = 0;
			} else {
				if (textbox.size()>0&&hindex<textbox.size()) {
					cursory = y + textbox.get(textbox.size()-1).vpos * textsize;
					cursorx = textbox.get(textbox.size()-1).x + textbox.get(textbox.size()-1).w*(textsize/12)-x;
				}
			}
		}
		if (hindex>-1) {
			if (textbox.size()>hindex+1) {
				cursorx = textbox.get(hindex).x + textbox.get(hindex).w*(textsize/12) - x;
				cursory = y + textbox.get(hindex).vpos * textsize;
			}
		}
		if ((pos()||toggle)||k) {
			//cursor(POINT);
			if (t1>0) {
				t1 --;
				if (strheight<y+h) {
					applet.stroke(0);
					applet.strokeWeight(1);
					applet.line(x+cursorx, cursory, x+cursorx, cursory+textsize);
				}
				if (t1<=0)t2 = blkrate;
			}
			if (t2>0&&t1<=0) {
				t2--;
				if (t2<=0)t1 = blkrate;
			}
		} 
		//	    if(applet.frameCount%10==0)applet.println("textbox getcursor");
		//else cursor(ARROW);

	};

	public void getCursor(PGraphics canvas) {

		getClick(mouse);
		//cursor(HAND);
		if (vindex>-1) {
		}
		if (hindex==-1) {
			if (textbox.size()==0) {
				cursory = y;
				cursorx = 1;
				vindex = 0;
			} else {
				if (textbox.size()>0&&hindex<textbox.size()) {
					cursory = y + textbox.get(textbox.size()-1).vpos * textsize;
					cursorx = textbox.get(textbox.size()-1).x + textbox.get(textbox.size()-1).w*(textsize/12)-x;
				}
			}
		}
		if (hindex>-1) {
			if (textbox.size()>hindex+1) {
				cursorx = textbox.get(hindex).x + textbox.get(hindex).w*(textsize/12) - x;
				cursory = y + textbox.get(hindex).vpos * textsize;
			}
		}
		if ((pos(mouse)||toggle)) {
			//cursor(POINT);
			if (t1>0) {
				t1 --;
				if (strheight<y+h) {
					canvas.stroke(0);
					canvas.strokeWeight(1);
					canvas.line(x+cursorx, cursory, x+cursorx, cursory+textsize);
				}
				if (t1<=0)t2 = blkrate;
			}
			if (t2>0&&t1<=0) {
				t2--;
				if (t2<=0)t1 = blkrate;
			}
		} 
		//else cursor(ARROW);

	};

	public void delete() {
		Letter current = null;

		if (textbox.size()>0&&hindex!=-1)current = textbox.get(hindex);
		if (textbox.size()>0&&hindex==-1)current = textbox.get(textbox.size()-1);

		String tm1 = "", tm2 = "";

		String tma;
		if (applet.keyPressed && Bms.key == PConstants.BACKSPACE) {
			if (hindex==-1) {
				if (current!=null&&current.id>0) {
					if (lines.size()-1>0&&current.vpos<lines.size()-1) {
						vcount --;
						lines.remove(lines.size()-1);
					}
					hindex = current.id;
					textbox.remove(textbox.size()-1);
					textBox = textBox.substring ( 0, textBox.length()-1  );
				} else {
					textbox = new ArrayList<Letter>();
					textBox = "";
				}
			} else {
				if (current!=null&&current.id>-1) {
					//if(current!=null&&lines.get(lines.size()-1) > textbox.get(textbox.size()-1).id-1) lines.remove(lines.size()-1);
					if (lines.size()>0&&textbox.get(textbox.size()-1).vpos<lines.size()-1) {
						vcount --;
						lines.remove(lines.size()-1);
					}
					hindex = current.id-1;
					//hindex --;
					textbox.remove(textbox.size()-1);
					if (current.id >0) {
						tm1 = textBox.substring ( 0, hindex+1 );
						tm2 = textBox.substring ( hindex +2, textBox.length()  );
					} else if (current.id==0) {
						tm1 = "";
						tm2 = textBox.substring ( 0, textBox.length()  );
					}
					textBox = tm1 + tm2;
				}
			}
		}
	};

	public void paste() {

		for (int i=0; i<cboard.length(); i++) {
			String a = PApplet.str(cboard.charAt(i));
			getKey();
		}
	};

	public void box() {
		//		  if(applet.frameCount%10==0)applet.println("textbox box start");
		if (!hidden) {
			//------------------------------- border
			applet.fill(255);
			//	      if (border)applet.stroke(col2);
			//	      else applet.noStroke();
			//-------------------------------label
			if (label!=null&&!clear) {
				applet.fill(0);
				applet.text(label, x, y+textsize);
			}
			//---------------------------------fill
			//	      if (fill)if (pos())applet.fill(255);
			//	      else applet.fill(0);
			//	      else applet.noFill();
			applet.fill(255);

			applet.rect(x, y, w, h,r1,r2,r3,r4);
		}
		//	    if(applet.frameCount%10==0)applet.println("textbox box end");
	};

	public void box(PGraphics canvas) {
		if (!hidden) {
			//------------------------------- border
			if (border)canvas.stroke(col2);
			else canvas.noStroke();
			//-------------------------------label
			if (label!=null&&!clear) {
				canvas.fill(col2);
				canvas.text(label, x, y+textsize);
			}
			//---------------------------------fill
			if (fill)if (pos(mouse))canvas.fill(255);
			else canvas.fill(0);
			else canvas.noFill();
			canvas.fill(255);

			canvas.rect(x, y, w, h,r1,r2,r3,r4);
		}
	};

	Letter sort(ArrayList<Letter>a) {
		Letter k = a.get(0);

		for (int i=0; i<a.size(); i++) {

			if (k.id>a.get(i).id) {
				k = a.get(i);
			}
		}
		return k;
	};

	public void conditionals(){

		if (vpos<=0) vpos = 0;
		if (selectAll&&toggle)if (dragh.size()!=textBox.length())dragh.add(0);
		if(firstPress&&kdown)firstPress = false;
		if(applet.keyPressed){
			if(timer>0)timer --;
			kup = false;
			if(!kdown) {
				firstPress = true;
				kdown = true;
			}
		}else if(kdown) {
			kup = true;
			kdown = false;
		}
		if(kup&&timer2>0) {
			timer2 --;
		}
		if (lines.size()>rows)lines.remove(lines.size()-1);
		if (lines.size()<=1)vcount = 0;
		if (toggle)clear = true;
		if (applet.mousePressed&&(!pos()))hindex = -1;
		if (applet.mousePressed&&(pos())) {
//			applet.println("textbox condi 00");
			KetaiKeyboard.toggle(applet);
			toggle = true;
			mdown = true;
		}
		if (applet.mousePressed&&(!pos())&&!mdown) {
//			applet.println("textbox condi 01");
			toggle = false;
			mdown = true;
		}
		if(!applet.mousePressed)mdown = false;
		if(toggle){
			if(timer==maxCount||timer==0||firstPress) {
				getChar = true;
			}else getChar = false;
			if(timer2<=0&&kup) {
				kup = false;
				timer2 = maxCount2+5;
			}
			if(timer<=0&&kdown) {
				timer = maxCount+5;

			}
			if(!applet.keyPressed) {
				kdown = false;
				timer = maxCount+5;
			}
		}
		//	    if(applet.frameCount%10==0)applet.println("textbox getkey");
	};

	public void conditionals(PGraphics canvas) {

		if (vpos<=0) vpos = 0;
		if (selectAll&&toggle)if (dragh.size()!=textBox.length())dragh.add(0);
		if(firstPress&&kdown)firstPress = false;
		if(applet.keyPressed){
			if(timer>0)timer --;
			kup = false;
			if(!kdown) {
				firstPress = true;
				kdown = true;
			}
		}else if(kdown) {
			kup = true;
			kdown = false;
		}
		if(kup&&timer2>0) {
			timer2 --;
		}
		if (lines.size()>rows)lines.remove(lines.size()-1);
		if (lines.size()<=1)vcount = 0;
		if (toggle)clear = true;
		if (applet.mousePressed&&(!pos(mouse)))hindex = -1;
		if (applet.mousePressed&&(pos(mouse))) {
			KetaiKeyboard.toggle(applet);
			toggle = true;
			mdown = true;
		}
		if (applet.mousePressed&&(!pos(mouse))&&!mdown) {
			toggle = false;
			mdown = true;
		}
		if(!applet.mousePressed)mdown = false;
		if(toggle){
			if(timer==maxCount||timer==0||firstPress) {
				getChar = true;
			}else getChar = false;
			if(timer2<=0&&kup) {
				kup = false;
				timer2 = maxCount2+5;
			}
			if(timer<=0&&kdown) {
				timer = maxCount+5;

			}
			if(!applet.keyPressed) {
				kdown = false;
				timer = maxCount+5;
			}
		}
	};

	@SuppressWarnings("static-access")
	public void constants() {
		strheight = (applet.floor(applet.textWidth(textBox)/(w)));
		strwidth = ((applet.textWidth(textBox))%(w));
	};

	boolean exclusion() {
		boolean k = false;

		if (Bms.keyCode==37||Bms.keyCode==38||Bms.keyCode==39||Bms.keyCode==40||Bms.keyCode==33||Bms.keyCode==34||Bms.keyCode==17||Bms.keyCode==18||Bms.keyCode==9) {
			k = true;
		} else if (Bms.keyCode==20||Bms.keyCode==16||Bms.keyCode==255||Bms.keyCode==91||Bms.keyCode==36||Bms.keyCode==35||Bms.keyCode==45||Bms.keyCode==46||Bms.keyCode==8) {
			k = true;
		}
		return k;
	};



	public void select_All() {
		if (selectAll) {
		}
	};

	public void debug() {
		applet.fill(255);
		applet.text(vpos, 140, 110);

		applet.text("lines " + lines.size(), 140, 140);
		applet.text(totalwidth, 140, 160);
		if (hindex>-1) {
			applet.text(hindex, 140, 120);
		} else {
			applet.text(hindex, 140, 120);
		}
		if (vcount>-1)applet.text(vcount, 140, 130);
		//if(clipBoard!=""){applet.text(clipBoard,140,100);}
		for (int i=0; i<lines.size(); i++) {
			applet.text(lines.get(i), x+x, y+40+10*i);
		}
	};



	String getChar() {
		String a = null;

		if (applet.keyPressed&&getChar) {
			a  = PApplet.str(Bms.key);
		}
		if (Bms.keyCode==9) a = null;

		return a;



	}

	char getchar() {
		char a = 'k';

		if (applet.keyPressed) {
			a  = (Bms.key);
		}
		if (Bms.keyCode == 8) {
		}
		pos();
		return a;
	};

	public boolean pos() {

		float X = applet.mouseX;
		float Y = applet.mouseY;

		return X > x && X < x + w && Y > y && Y < y + h;
	};

	boolean pos(PVector m) {

		float X = m.x;
		float Y = m.y;

		return X > x && X < x + w && Y > y && Y < y + h;
	};

	public void calc_lwidth() {
		for (int i=0; i<strheight+1; i++) {
			for (int j=0; j<w; j++) {
			}
		}
	};

	public void insert() {
		if (hindex>-1) {
		} else {
		}
	};

	public void mpos() {
		float X = applet.mouseX;
		float Y = applet.mouseY;
		float ww = w/cols;
		float hh = h/rows;

		if (pos())posx = PApplet.floor(X/ww)*ww-10;
		posy = PApplet.floor(Y/hh)*hh;
		//	    if(applet.frameCount%10==0)applet.println("textbox mpos");
	};

	public void mpos(PVector m) {
		float X = m.x;
		float Y = m.y;
		float ww = w/cols;
		float hh = h/rows;

		if (pos(m))posx = PApplet.floor(X/ww)*ww-10;
		posy = PApplet.floor(Y/hh)*hh;
	};

	public boolean getClick() {
		if (pos() && applet.mousePressed&&!toggle&&!mdown) {
			KetaiKeyboard.toggle(applet);
			toggle = true;
			mdown = true;
		}
		if (pos() && applet.mousePressed&&toggle&&!mdown) {
			toggle = false;
			mdown = true;
		}
		return toggle;
	};

	public boolean getClick(PVector m) {

		if (pos(m) && applet.mousePressed&&!toggle&&!mdown) {
			KetaiKeyboard.toggle(applet);
			toggle = true;
			mdown = true;
		}
		if (pos(m) && applet.mousePressed&&toggle&&!mdown) {
			toggle = false;
			mdown = true;
		}
		return toggle;
	};

	public void nav() {
		if (hindex>=textbox.size()-1) {
			hindex  =-1;
		}
		if (textbox.size()>0) {
			if (hindex>=1) {
				boolean k = false;
				if (applet.keyPressed && Bms.keyCode == 37) {
					k = true;
				}
				if(!applet.keyPressed)k = false;
				if (k&&(getChar||timer==21)) {
					hindex --;
				}
			}
			if (hindex<=textbox.size()-1&&hindex>-1) {
				if (applet.keyPressed && Bms.keyCode == 39 &&getChar) { 
					hindex ++;
				}
			}
			//if(applet.keyPressed && Bms.key == UP && strheight >0) hindex += vpos *2;
			//if(applet.keyPressed && Bms.key == DOWN && strheight )

			if (hindex==-1) {
				if (applet.keyPressed && Bms.keyCode == 37 &&getChar) {
					hindex = textbox.size()-2;
				}
				//	        if(applet.frameCount%10==0)applet.println("textbox nav emd");
				//if(applet.keyPressed && Bms.keyCode == 39 &&getChar){ hindex = 0;}
			}
		}
	};

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

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
	};
};


