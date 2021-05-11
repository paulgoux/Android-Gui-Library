package androidGui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Button{
	public BMScontrols Bms;
	public PApplet applet;
	public PGraphics canvas;
	public float x,y,bx,by,w,h,size,textSize = 1,xoff,yoff,bsize,tsize = 12,tyoff,txoff,tmax = 2,tw,th,borderSize;
	public int id,toggle2,type,counter,tcount;
	public float scalew,scaleh,r1,r2,r3,r4,rx,ry,transparency;

	public String label,blabel;
	public PImage img;
	public boolean drag,resize, radio,update,border = true,vertical,horizontal,gif,Img,value,textright,textbtm,textleft,
			textup,texttoggle,animate = true,toggleb,mdown,m1down,sdown,visible = true,plain = true,labelVisible = true
			,up,right,down,classicBar,toggleBox,mdown2,textcheck,parentCanvas,subLeft,click,mclick,m2down,toggle;
	public boolean globalTheme = true,mdown1,mup,localTheme,localText,localBorder,localFill,localTrans,click1;
	public int fcol,bcol,hcol,col,tcol,col1 = fcol,toggleCol,borderCol = 0;
	public Menu parent;
	public Menu submenu;
	public Window subwindow;
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public HashMap<String,Boolean> values = new HashMap<String,Boolean>();
	public String Text = "" ;
	public TextBox textbox;
	public PVector mouse;
	public tab parentTab;
	public Dropdown dMenu;
	public tooltip info;

	public Button(float xx, float yy, float ww, float hh, String Label){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		blabel = label;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;

	};



	public Button(float xx, float yy, float ww, float hh, String Label,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		label = Label;
		blabel = label;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;

	};

	public Button(float xx, float yy, float ww, float hh){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;

	};

	public Button(float xx, float yy, float ww, float hh,BMScontrols bms){
		Bms = bms;
		applet = bms.applet;

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;
		initColors();
	};

	public Button(float xx, float yy, float ww, float hh,int Cols){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;
		value = true;
		textbox = new TextBox(x,y,ww+1,hh,Cols);

	};

	public Button(float xx, float yy, float ww, float hh,int Cols, String Label){

		x = xx;
		y = yy;
		bx = x;
		by = y;
		w = ww;
		h = hh;
		size = 1;
		textSize = hh/2+hh/5;
		bsize = tsize;
		label = Label;
		blabel = label;
		value = true;
		textbox = new TextBox(x,y,ww+1,hh,Cols,Label);

	};

	public Button(){

	};

	public void save(){

	};

	public void load(){

	};

	public void draw2(){
		applet.fill(bcol);
		if(globalTheme&&!localFill)applet.fill(Bms.bcol);
		applet.rect(x,y,w,h);
	};

	public void btn(){
		if(!radio&&!classicBar){
			applet.stroke(255);
			if(border)applet.strokeWeight(borderSize);
			if(globalTheme&&Bms.border)applet.strokeWeight(Bms.borderSize);
			if(parent!=null&&type!=0){
				x = parent.x;
				//y = parent.y;
			}
			applet.fill(255);
			if(Bms.transparency>-1)applet.fill(255,Bms.transparency);
			applet.rect(x,y,w-2,h-2,r1,r2,r3,r4);
			applet.fill(col);
			applet.rect(x ,y,w,h,r1,r2,r3,r4);

			applet.textSize(textSize);
			if(globalTheme&&!localText)applet.textSize(Bms.textSize);

			if(label!=null){

				if(scaleh>0){
					applet.pushMatrix();
					applet.translate(0,y*scaleh+h/2);
					applet.scale(1,scaleh);
				}
				if(scalew>0){
					applet.pushMatrix();
					applet.translate(x*scalew,0);
					applet.scale(scalew,1);
				}
				applet.fill(tcol);
				if(globalTheme&&!localText)applet.fill(Bms.tcol);
				applet.textSize(textSize);
				if(globalTheme&&!localText)applet.textSize(Bms.textSize);
				// if(!textbtm&&!textright&&!textup)applet.text(label,x+5+txoff,y+13+tyoff+5);
				if(textbtm)applet.text(label,x+txoff,y+h+tyoff+tsize);
				applet.textSize(12);
				if(scaleh>0){
					applet.scale(1,1/scaleh);
					applet.popMatrix();
				}
				if(scalew>0){
					applet.scale(-scalew,1/scalew);
					applet.popMatrix();
				}

			}
			if(value){

				if(textup)textbox.y     = y - h;
				if(textbtm)textbox.y    = y + h;
				if(textleft) textbox.x  = x - w;
				if(textright) textbox.x = x + w;
				Text = textbox.textBox;

				if(pos()||textbox.pos()||textbox.toggle)texttoggle=true;
				else texttoggle = false;
				if(texttoggle)textbox.draw();
				if(textbox.pos()&&applet.mousePressed&&parent!=null){ parent.draw();}
				else parent.toggle = false;
			}
			if(img!=null){
				applet.image(img,x,y+1,w,h);
			}}
	};

	public void initColors() {

		//		  fcol = applet.color(0, 255, 73);
		//		  bcol = applet.color(0,100);
		//		  hcol = applet.color(255,50);
		//		  col = fcol;
		//		  tcol = applet.color(255);
		//		  col1 = fcol;
		//		  toggleCol = applet.color(50,0);

		col = applet.color(0, 255, 73);
		bcol = applet.color(255);
		tcol = applet.color(255); 
		fcol = applet.color(0, 255, 73);
		hcol = applet.color(0, 255, 73,100);
		toggleCol = applet.color(55, 84, 63);
	};

	public void drawPlain() {
		if(plain){
			applet.fill(0);
			applet.stroke(Bms.col);
			if(!Bms.border)applet.noStroke();

			if(w>h){
				applet.fill(255);
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				applet.fill(col);
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				if(label!=null){

					applet.fill(tcol);
					if(globalTheme&&!localText)applet.fill(Bms.tcol);
					applet.textSize(textSize);
					if(globalTheme&&!localText)applet.textSize(Bms.textSize);

					if(up)applet.text(label,x+txoff,y-3+tyoff);
					if(right)applet.text(label,x+w+2,y+tyoff);
					if(down)applet.text(label,x,y+tsize*2+2+tyoff);
					else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
					applet.textSize(12);
				}}
			else{
				applet.fill(255);
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				applet.fill(col);
				applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){
					applet.fill(tcol);
					if(globalTheme&&!localText)applet.fill(Bms.tcol);
					applet.textSize(textSize);
					if(globalTheme&&!localText)applet.textSize(Bms.textSize);
					if(up)applet.text(label,x+txoff,y-3+tyoff);
					if(right)applet.text(label,x+w+2+txoff,y+tyoff);
					if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
					else applet.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
					applet.textSize(12);
				}}}
	};

	public void drawPlain(PGraphics canvas) {
		if(plain){
			canvas.fill(0);
			canvas.stroke(Bms.col);
			if(!Bms.border)canvas.noStroke();
			//		      canvas.ellipse(mouse.x,mouse.y,20,20);
			if(w>h){
				canvas.fill(255);
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				canvas.fill(col);
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				if(label!=null){

					canvas.fill(tcol);
					if(globalTheme&&tcol==-1)canvas.fill(Bms.tcol);
					canvas.textSize(textSize);
					if(globalTheme&&!localText)canvas.textSize(Bms.textSize);

					if(up)canvas.text(label,x+txoff,y-3+tyoff);
					if(right)canvas.text(label,x+w+2,y+tyoff);
					if(down)canvas.text(label,x,y+tsize*2+2+tyoff);
					else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
					canvas.textSize(12);
				}}
			else{
				canvas.fill(255);
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
				canvas.fill(col);
				canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);

				if(label!=null){
					canvas.textSize(textSize);
					if(globalTheme&&!localText)canvas.textSize(Bms.textSize);
					if(up)canvas.text(label,x+txoff,y-3+tyoff);
					if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
					if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
					else canvas.text(label,x + 5 +txoff,y+w/2+tsize/2+tyoff);
					canvas.textSize(12);
				}}}
	};

	public void radioLogic() {

	}

	public void drawClassicRadio(){
		if(radio){
			applet.stroke(0);
			if(!Bms.border)applet.noStroke();
			applet.fill(255);
			applet.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
			applet.fill(col);
			if(pos())applet.fill(Bms.hcol);
			applet.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				applet.textSize(textSize);
				if(globalTheme&&!localText)applet.textSize(Bms.textSize);
				applet.fill(Bms.tcol);
				if(globalTheme&&!localText)applet.fill(tcol);
				if(up)applet.text(label,x + txoff,y-3+ tyoff);
				if(right)applet.text(label,x+w+2+ txoff,y+ tyoff);
				if(down)applet.text(label,x+ txoff,y+tsize*2+2+ tyoff);
				else applet.text(label,x +5 + txoff,y + tsize + tyoff);
				applet.textSize(12);
			}

			applet.fill(0);
			if(toggle)applet.fill(255);
			applet.ellipseMode(PConstants.CENTER);
			applet.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		}
	};

	//revise code
	public void drawClassicBar() {
		if(classicBar){
			applet.fill(0);
			applet.stroke(Bms.col);
			if(!Bms.border)applet.noStroke();

			applet.fill(255);
			applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
			applet.fill(col);
			applet.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
			if(label!=null){

				applet.fill(tcol);
				if(globalTheme&&!localText)applet.fill(Bms.tcol);
				applet.textSize(textSize);
				if(globalTheme&&!localText)applet.textSize(Bms.textSize);

				if(up)applet.text(label,x+txoff,y-3+tyoff);
				if(right)applet.text(label,x+w+2+txoff,y+tyoff);
				if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
				else applet.text(label,x +5 + txoff,y+tsize + tyoff + 4);
				applet.textSize(12);
			}
		}

	};

	public void drawTogglebox() {
		if(toggleBox){
			applet.stroke(Bms.col);
			if(!Bms.border)applet.noStroke();

			applet.fill(255);
			applet.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			applet.fill(col);
			applet.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				applet.fill(tcol);
				if(globalTheme&&!localText)applet.fill(Bms.tcol);
				applet.textSize(textSize);
				if(globalTheme&&!localText)applet.textSize(Bms.textSize);
				if(up)applet.text(label,x+txoff,y-3+tyoff);
				if(right)applet.text(label,x+w+2+txoff,y+tyoff);
				if(down)applet.text(label,x+txoff,y+tsize*2+2+tyoff);
				else applet.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
				applet.textSize(12);
			}
			applet.fill(col);

			if(!toggle){
				applet.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				applet.fill(Bms.tcol);
				if(globalTheme&&!localText)applet.fill(tcol);
				applet.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}else{
				applet.fill(Bms.fcol);
				if(globalTheme&&!localFill)applet.fill(fcol);
				applet.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				applet.fill(tcol);
				if(globalTheme&&!localText)applet.fill(Bms.tcol);
				applet.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}
		}
	};

	public void drawClassicRadio(PGraphics canvas){
		if(radio){
			canvas.stroke(0);
			if(!Bms.border)canvas.noStroke();

			canvas.fill(Bms.col);

			canvas.fill(255);
			canvas.rect(x + rx + xoff,y+yoff,w,h,r1,r2,r3,r4);
			canvas.fill(col);
			if(pos(mouse))applet.fill(Bms.hcol);
			canvas.rect(x +rx + xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				canvas.textSize(tsize);
				canvas.fill(tcol);
				if(globalTheme&&!localText)canvas.fill(Bms.tcol);
				if(up)canvas.text(label,x + txoff,y-3+ tyoff);
				if(right)canvas.text(label,x+w+2+ txoff,y+ tyoff);
				if(down)canvas.text(label,x+ txoff,y+tsize*2+2+ tyoff);
				else canvas.text(label,x +5 + txoff,y + tsize + tyoff);
				canvas.textSize(12);
			}

			canvas.fill(0);
			if(toggle)canvas.fill(255);
			canvas.ellipseMode(PConstants.CENTER);
			canvas.ellipse(x+rx+w/2+xoff ,y+h/2+yoff,h-5,h-5);
		}
	};

	//revise code
	public void drawClassicBar(PGraphics canvas) {
		if(classicBar){
			canvas.fill(0);
			canvas.stroke(Bms.col);
			if(!Bms.border)canvas.noStroke();

			canvas.fill(255);
			canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
			canvas.fill(col);
			canvas.rect(x+xoff,y+yoff,w,h,r1,r2,r3,r4);
			if(label!=null){

				canvas.fill(tcol);
				if(globalTheme&&!localText)canvas.fill(Bms.tcol);
				canvas.textSize(tsize);
				if(globalTheme&&!localText)canvas.textSize(Bms.tsize);
				if(up)canvas.text(label,x+txoff,y-3+tyoff);
				if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
				if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
				else canvas.text(label,x +5 + txoff,y+tsize + tyoff + 4);
				canvas.textSize(12);
			}
		}
	};

	public void drawTogglebox(PGraphics canvas) {
		if(toggleBox){
			canvas.stroke(Bms.col);
			if(!Bms.border)canvas.noStroke();

			canvas.fill(255);
			canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			canvas.fill(col);
			canvas.rect(x + rx+xoff,y+yoff,w,h,r1,r2,r3,r4);

			if(label!=null){
				canvas.fill(tcol);
				if(globalTheme&&!localText)canvas.fill(Bms.tcol);
				canvas.textSize(tsize);
				if(globalTheme&&!localText)canvas.textSize(Bms.tsize);
				if(up)canvas.text(label,x+txoff,y-3+tyoff);
				if(right)canvas.text(label,x+w+2+txoff,y+tyoff);
				if(down)canvas.text(label,x+txoff,y+tsize*2+2+tyoff);
				else canvas.text(label,x + 5 + txoff,y+tsize + tyoff + 4);
				canvas.textSize(12);
			}
			canvas.fill(col);

			if(!toggle){
				canvas.rect(x + rx+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				canvas.fill(tcol);
				if(globalTheme&&!localText)canvas.fill(Bms.tcol);
				canvas.text("OFF",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}else{
				canvas.fill(fcol);
				if(globalTheme&&!localFill)canvas.fill(Bms.fcol);
				canvas.rect(x+rx+w/2+xoff,y,w/2+yoff,h,r1,r2,r3,r4);
				canvas.fill(tcol);
				if(globalTheme&&!localText)canvas.fill(Bms.tcol);
				canvas.text("ON",x+rx+w+10+txoff,y+tsize+tyoff+4);
			}
		}
	};

	public void draw(){
		logic();
		applet.textSize(12);
		highlight();
		//	    btn();
		if(plain)drawPlain();
		else if(radio)drawClassicRadio();
		else if(classicBar)drawClassicBar();
		else if(toggleBox)drawTogglebox();

		if(subpos())toggle2 = 1;
		else toggle2 = 0;

		applet.strokeWeight(1);
		if(info!=null)info.draw();
	};

	public void logic(){
		if(animate){
			if(pos()||toggle){
				if(bsize<tsize+tmax) bsize += 0.5;
			}else if(bsize>tsize)bsize -= 0.5;
		}
		if(radio||toggleBox){
		}

	};

	public void logic(PVector m){
		if(animate){
			if(pos(m)||toggle){
				if(bsize<tsize+tmax) bsize += 0.5;
			}else if(bsize>tsize)bsize -= 0.5;

		}
		if(radio||toggleBox){
		}

	};

	public void draw(PGraphics canvas) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		logic(mouse);
		highlight(mouse);
		canvas.textSize(12);
		if(plain)drawPlain(canvas);
		else if(radio)drawClassicRadio(canvas);
		else if(classicBar)drawClassicBar(canvas);
		else if(toggleBox)drawTogglebox(canvas);

		if(subpos(mouse))toggle2 = 1;
		else toggle2 = 0;

		canvas.strokeWeight(1);
		if(info!=null)info.draw();

	};

	public void radio(){

		radio = true;
	};



	public void labelcheck(float a){

		if(applet.textWidth(label)>a){
			for(int i=10;i<label.length();i++){
				float lw = applet.textWidth(label.substring(0,i)+ " ...");
				if(lw>a){
					label = label.substring(0,i-1) + "...";
					textcheck = true;
					break;
				}
			}}
		else textcheck = true;

	};

	public boolean pos(){
		if(radio||toggleBox){
			return x +rx < applet.mouseX && applet.mouseX < x + w+rx && y < applet.mouseY && applet.mouseY < y + h+2;
		}else{
			return x  < applet.mouseX && applet.mouseX < x + w && y < applet.mouseY && applet.mouseY < y + h+2;
		}

	};

	boolean radioPos(){
		//return false;
		return x +applet.textWidth(label)+20 < applet.mouseX && applet.mouseX < x + w+applet.textWidth(label)+20 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos(PVector m){
		if(radio||toggleBox){

			return rx+x  < m.x && m.x < x+rx + w && y < m.y && m.y < y + h+2;

		}else{
			return x  < m.x && m.x < x + w && y < m.y && m.y < y + h+2;
		}

	};

	boolean pos(PGraphics m){
		if(radio||toggleBox){

			return rx+x  < mouse.x && mouse.x < x+rx + w && y < mouse.y && mouse.y < y + h+2;

		}else{
			return x  < mouse.x && mouse.x < x + w && y < mouse.y && mouse.y < y + h+2;
		}
	};

	boolean pos2(){

		return x < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos2(PVector m){

		return x < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	};

	boolean pos3(){

		return x + applet.textWidth(label)+5 < applet.mouseX && applet.mouseX < x + w + applet.textWidth(label)+5 && y < applet.mouseY && applet.mouseY < y + h+2;
	};

	boolean pos3(PVector m){

		return x + applet.textWidth(label)+5 < m.x && m.x < x + w + applet.textWidth(label)+5 && y < m.y && m.y < y + h+2;
	};

	public void getValue(){
		//if(key='ENTER')
	};

	public void setName(String a){
		label = a;
	};

	String getName(){
		String a = "";
		if (label!=null) a = label;

		return a;
	};

	boolean mouseDown(){
		boolean k = false;
		if(pos()&&applet.mousePressed)k = true;
		if(!applet.mousePressed)k = false;
		return k;
	};

	public boolean clickD(){
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click){
			click = true;
			k = true;
		}else if(click&&!applet.mousePressed){
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle)toggle = true;
		return k;
	};

	boolean clickD(PVector m){
		boolean k = false;
		if (pos(m)&&applet.mousePressed&&!click){
			click = true;
			k = true;
		}else if(click&&!applet.mousePressed){
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle) toggle = true;
		return k;
	};
	
	public boolean clickU(){
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click){
			click = true;
//			k = true;
		}else if(click&&!applet.mousePressed){
			k = true;
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle)toggle = true;
		return k;
	};

	public boolean clickU(PVector m){
		boolean k = false;
		if (pos(m)&&applet.mousePressed&&!click){
			click = true;
//			k = true;
			
		}else if(click&&!applet.mousePressed){
			k = true;
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle) toggle = true;
		return k;
	};
	
	public boolean click(){
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click){
			click = true;
			k = true;
		}else if(click&&!applet.mousePressed){
//			k = true;
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle)toggle = true;
		return k;
	};

	boolean click(PVector m){
		boolean k = false;
		if (pos(m)&&applet.mousePressed&&!click){
			click = true;
			k = true;
		}else if(click&&!applet.mousePressed){
//			k = true;
			click = false;
		}
		if(k&&toggle)toggle = false;
		else if(k&&!toggle) toggle = true;
		return k;
	};
	
	public boolean click(Object a,String b){
		boolean k = clickU();
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};

	public boolean click(Object a,String b,PVector m){
		boolean k = clickU(m);
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};

	public boolean clickU(Object a,String b){
		boolean k = clickU();
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};

	public boolean clickU(Object a,String b,PVector m){
		boolean k = clickU(m);
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};
	
	public boolean clickD(Object a,String b){
		boolean k = clickD();
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};

	public boolean clickD(Object a,String b,PVector m){
		boolean k = clickD(m);
		if(parent!=null){
			if(k&&parent.toggle){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}}
		else{
			if(k){
				Field field = null;

				try{
					field = a.getClass().getField(b); 

					field.set(a, toggle); 
				}catch (NullPointerException e) {
				}catch (NoSuchFieldException e) {
				}catch (IllegalAccessException e) {
				}}
		}
		return k;
	};

	public void reverseclick(Object a,String b){
		if(parent!=null){
			//if(!pos()&&parent.toggle&&applet.mousePressed)mdown = true;
			if(pos()&&parent.toggle&&applet.mousePressed&&!mdown)mdown = true;

			if(mdown)toggle = true;
			else toggle = false;
			if(mdown && !applet.mousePressed){mdown = false;}

			Field field = null;

			try{
				field = a.getClass().getField(b); 

				if(mdown)field.set(a, true); 
				else field.set(a, false);
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		else{
			if(pos()&&applet.mousePressed&&!mdown)mdown = true;

			if(mdown){
				toggle = true;
			}
			else toggle = false;
			if(mdown && !applet.mousePressed){mdown = false;}

			Field field = null;

			try{
				field = a.getClass().getField(b); 

				if(!mdown)field.set(a, true); 
				else field.set(a, false);
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}
		}
	};

	public void latch(Object a,String b){
		PVector m = new PVector(applet.mouseX,applet.mouseY);
		if(mouse!=null)m = mouse;
		if(parent!=null) {
			if(pos(m)&&parent.toggle&&applet.mousePressed) {mdown = true;mup = false;}
			if(!applet.mousePressed){mdown = false;mup = true;}
		}else {
			if(pos(m)&&applet.mousePressed) {mdown = true;mup = false;}
			if(!applet.mousePressed){mdown = false;mup = true;}
		}

		if(mdown&&!toggle||mup&&toggle) {

			Field field = null;

			try{
				field = a.getClass().getField(b); 

				if(mdown) {
					toggle = true;
					field.set(a, toggle); 
				}
				if(mup) {
					toggle = false;
					field.set(a, toggle); 
				}
			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		if(!applet.mousePressed)mdown = false;
	};


	public boolean mousePressed() {
		boolean k = false;
		PVector n = new PVector(applet.mouseX,applet.mouseY);
		if(mouse!=null)n = mouse;
		if(parent!=null) {
			if (parent.toggle&&pos(n)&&applet.mousePressed&&!mdown)mdown = true;
			if(mdown&&!m2down){
				k = true;
				m2down = true;
			}
		}else {
			if (pos(n)&&applet.mousePressed&&!mdown)mdown = true;
			if(mdown&&!m2down){
				k = true;
				m2down = true;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
			m2down = false;
		}
		return k;

	};

	public boolean mousePressed(PVector m) {
		boolean k = false;
		PVector n = new PVector(applet.mouseX,applet.mouseY);
		if(m==null)n = mouse;
		if(parent!=null) {
			if (parent.toggle&&pos(m)&&applet.mousePressed&&!mdown)mdown = true;
			if(mdown&&!m2down){
				m2down = true;
				k = true;
			}

		}else {
			if (pos(m)&&applet.mousePressed&&!mdown)mdown = true;
			if(mdown&&!m2down){
				m2down = true;
				k = true;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
			m2down = false;
		}
		return k;

	};

	public boolean gettoggle() {

		return toggle;
	};

	public boolean getToggle() {
		clickU();

		return toggle;
	};

	public boolean getToggle(PVector m) {
		clickU(m);

		return toggle;
	};
	
	public boolean toggle() {
		click();
		return toggle;
	};

	public boolean toggle(PVector m) {
		click(m);
		return toggle;
	};
	
	public boolean toggleD(Object a, String b){
		boolean k = clickD();
		if(parent!=null&&parent.toggle&&k||parent==null&&k) {
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 

			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		if(!applet.mousePressed){
			mdown = false;
		}
		return k;
	};

	public void toggleD(Object a, String b,PGraphics c){
		if(parent!=null){
			if(parent.toggle&&clickD(mouse)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 

					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("np np",c,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("np nsf",c,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("np ia",c,a,b);
				}}}else{

					if(clickD()){

						Field field = null;

						try{

							field = a.getClass().getField(b); 

							field.set(a, toggle);
						}catch (NullPointerException e) {
							PApplet.println("p np",c,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("p nsf",c,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("p ia",c,a,b);
						}}}
		if(!applet.mousePressed)mdown = false;
	};

	public boolean toggleD(Object a, String b,PVector m){
		
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		boolean k = clickD(m);
		if(parent!=null&&parent.toggle&&k||parent==null&&k){
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 

			}catch (NullPointerException e) {
				PApplet.println("toggle pv np np",m,a,b);
			}catch (NoSuchFieldException e) {
				PApplet.println("toggle pv np nsf",m,a,b);
			}catch (IllegalAccessException e) {
				PApplet.println("toggle pv np ia",m,a,b);
			}}
		if(!applet.mousePressed)mdown = false;
		return k;
	};

	public void toggleD(Object a, String b,PVector m,boolean k){
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(parent.toggle&&clickD(m)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					boolean k1 = field.getBoolean(a);
					if(toggle&&!k1)toggle = false;
					else if(!toggle&&k)toggle = true;
					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}}else{

					if(clickD(m)){

						Field field = null;

						try{

							field = a.getClass().getField(b); 
							field = a.getClass().getField(b); 
							boolean k1 = field.getBoolean(a);
							if(toggle&&!k1)toggle = false;
							else if(!toggle&&k)toggle = true;
							field.set(a, toggle); 
						}catch (NullPointerException e) {
							PApplet.println("toggle pv p np",m,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("toggle pv p nsf",m,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("toggle pv p ia",m,a,b);
						}}}
		if(!applet.mousePressed)mdown = false;
	};


	public boolean toggleU(Object a, String b){
		boolean k = clickU();
		if(parent!=null&&parent.toggle&&k||parent==null&&k) {
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 

			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		return k;
	};

	public void toggleU(Object a, String b,PGraphics c){
		if(parent!=null){
			if(parent.toggle&&clickU(mouse)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 

					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("np np",c,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("np nsf",c,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("np ia",c,a,b);
				}}}else{

					if(clickU()){

						Field field = null;

						try{

							field = a.getClass().getField(b); 

							field.set(a, toggle);
						}catch (NullPointerException e) {
							PApplet.println("p np",c,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("p nsf",c,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("p ia",c,a,b);
						}}}
	};

	public boolean toggleU(Object a, String b,PVector m){
		
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		boolean k = clickU(m);
		
		if(parent!=null&&parent.toggle&&k||parent==null&&k){
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 
				applet.println("button toggleU",b,toggle);
			}catch (NullPointerException e) {
				PApplet.println("toggle pv np np",m,a,b);
			}catch (NoSuchFieldException e) {
				PApplet.println("toggle pv np nsf",m,a,b);
			}catch (IllegalAccessException e) {
				PApplet.println("toggle pv np ia",m,a,b);
			}}
		return k;
	};

	public void toggleU(Object a, String b,PVector m,boolean k){
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(parent.toggle&&clickU(m)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					boolean k1 = field.getBoolean(a);
					if(toggle&&!k1)toggle = false;
					else if(!toggle&&k)toggle = true;
					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}}else{

					if(clickU(m)){

						Field field = null;

						try{

							field = a.getClass().getField(b); 
							field = a.getClass().getField(b); 
							boolean k1 = field.getBoolean(a);
							if(toggle&&!k1)toggle = false;
							else if(!toggle&&k)toggle = true;
							field.set(a, toggle); 
						}catch (NullPointerException e) {
							PApplet.println("toggle pv p np",m,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("toggle pv p nsf",m,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("toggle pv p ia",m,a,b);
						}}}
	};
	
	public boolean toggle(Object a, String b){
		boolean k = clickU();
		if(parent!=null&&parent.toggle&&k||parent==null&&k) {
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 

			}catch (NullPointerException e) {
			}catch (NoSuchFieldException e) {
			}catch (IllegalAccessException e) {
			}}
		return k;
	};

	public void toggle(Object a, String b,PGraphics c){
		if(parent!=null){
			if(parent.toggle&&clickU(mouse)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 

					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("np np",c,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("np nsf",c,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("np ia",c,a,b);
				}}}else{

					if(clickU()){

						Field field = null;

						try{

							field = a.getClass().getField(b); 

							field.set(a, toggle);
						}catch (NullPointerException e) {
							PApplet.println("p np",c,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("p nsf",c,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("p ia",c,a,b);
						}}}
	};

	public boolean toggle(Object a, String b,PVector m){
		
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		boolean k = clickU(m);
		if(parent!=null&&parent.toggle&&k||parent==null&&k){
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle); 

			}catch (NullPointerException e) {
				PApplet.println("toggle pv np np",m,a,b);
			}catch (NoSuchFieldException e) {
				PApplet.println("toggle pv np nsf",m,a,b);
			}catch (IllegalAccessException e) {
				PApplet.println("toggle pv np ia",m,a,b);
			}}
		return k;
	};

	public void toggle(Object a, String b,PVector m,boolean k){
		if(m==null)m = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(parent.toggle&&clickU(m)){
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					boolean k1 = field.getBoolean(a);
					if(toggle&&!k1)toggle = false;
					else if(!toggle&&k)toggle = true;
					field.set(a, toggle); 

				}catch (NullPointerException e) {
					PApplet.println("toggle pv np np",m,a,b);
				}catch (NoSuchFieldException e) {
					PApplet.println("toggle pv np nsf",m,a,b);
				}catch (IllegalAccessException e) {
					PApplet.println("toggle pv np ia",m,a,b);
				}}}else{

					if(clickU(m)){

						Field field = null;

						try{

							field = a.getClass().getField(b); 
							field = a.getClass().getField(b); 
							boolean k1 = field.getBoolean(a);
//							if(toggle&&!k1)toggle = false;
//							else if(!toggle&&k)toggle = true;
							field.set(a, toggle); 
						}catch (NullPointerException e) {
							PApplet.println("toggle pv p np",m,a,b);
						}catch (NoSuchFieldException e) {
							PApplet.println("toggle pv p nsf",m,a,b);
						}catch (IllegalAccessException e) {
							PApplet.println("toggle pv p ia",m,a,b);
						}}}
	};

	public void sptoggle(Object a, String b,String [] c){
		if(clickU()){
			mdown = false;
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				field.set(a, toggle);
			}catch (NoSuchFieldException e) {
				PApplet.println("nsf");
			}catch (NullPointerException e) {
				PApplet.println("npe");
			}catch (IllegalAccessException e) {
				PApplet.println("iae");
			}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						parent.items.get(i).toggle = false;
						//applet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};

	public void sptoggle(Object a, String b,String [] c,PVector m){


		if(clickU(m)){
			mdown = false;
			Field field = null;

			try{

				field = a.getClass().getField(b); 

				if(toggle){

					field.set(a, true); 
				}else {
					field.set(a, false);
					//applet.println("button ",next,a," ");
				}}catch (NoSuchFieldException e) {
					PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						parent.items.get(i).toggle = false;
						//applet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};

	public void sptoggle2(Object a, String b,String [] c){

		if(clickU() && parent.toggle){
			Field field = null;

			try{

				field = a.getClass().getField(b); 

					field.set(a, toggle);
					//applet.println("button ",next,a," ");
				}catch (NoSuchFieldException e) {
					PApplet.println("nsf");
				}catch (NullPointerException e) {
					PApplet.println("npe");
				}catch (IllegalAccessException e) {
					PApplet.println("iae");
				}

			for(int i=0;i<c.length;i++){
				String next = c[i];
				if(next!=b){
					//applet.println("button ",next,b," ");
					try{
						field = a.getClass().getField(next); 
						field.set(a, false); 
						PApplet.print(field.get(a)," ");
					}catch (NullPointerException e) {
						PApplet.println("nsf");
					}catch (NoSuchFieldException e) {
						PApplet.println("npe");
					}catch (IllegalAccessException e) {
						PApplet.println("iae");
					}
				}}}
	};


	public void cycle(int b){
		if(parent!=null){
			if(pos() && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(int b,PVector m){
		if(parent!=null){
			if(pos(m) && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(int b,PGraphics canvas){
		if(parent!=null){
			if(pos(mouse) && parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				counter ++;

				if(counter==b)counter = 0;
			}
		}

		if(!applet.mousePressed){
			mdown = false;
		}
	};

	public void cycle(Object a,String b,int c) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		else mouse = new PVector(applet.mouseX,applet.mouseY);

		if(parent!=null){
			if(pos(mouse)&&parent.toggle&&counter<c&&applet.mousePressed&&!mdown){
				counter ++;
				mdown = true;
				PApplet.println("button cycle mdown");
			}

			if(pos(mouse)&&parent.toggle&&counter==c&&applet.mousePressed&&!mdown){
				counter = 0;
				mdown = true;
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){

				mdown1 = true;

				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle parent iae");
				}}}
		else {

			if(pos(mouse)&&applet.mousePressed&&counter<c&&!mdown){
				counter ++;
				mdown = true;
				PApplet.println("button cycle mdown");
			}

			else if(pos(mouse)&&applet.mousePressed&&counter==c&&!mdown){
				counter = 0;
				mdown = true;
				PApplet.println("button cycle mdown");
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){
				mdown1 = true;
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle no parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle no parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle no parent iae");
				}
			}
		}
		if(!applet.mousePressed) {
			mdown = false;
			mdown1 = false;
		}
	};

	public void cycle(Object a,String b,int c,PVector mouse) {
		if(mouse==null)mouse = new PVector(applet.mouseX,applet.mouseY);
		if(parent!=null){
			if(pos(mouse)&&parent.toggle&&applet.mousePressed&&!mdown){
				mdown = true;
				PApplet.println("button cycle pv mdown");
			}
			if(pos(mouse) && mdown&&!mdown1){

				mdown1 = true;

				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle pv parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle pv parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle pv parent iae");
				}}}
		else {

			if(pos(mouse)&&applet.mousePressed&&counter<c&&!mdown){
				counter++;
				mdown = true;
			}

			else if(pos(mouse)&&applet.mousePressed&&counter==c&&!mdown){
				counter = 0;
				mdown = true;
			}
			if(!applet.mousePressed)mdown = false;

			if(pos(mouse) && mdown&&!mdown1){
				mdown1 = true;
				Field field = null;

				try{

					field = a.getClass().getField(b); 
					field.set(a, counter); 
				}catch (NullPointerException e) {
					PApplet.println("Button cycle pv no parent npe");
				}catch (NoSuchFieldException e) {
					PApplet.println("Button cycle pv no parent nfe");
				}catch (IllegalAccessException e) {
					PApplet.println("Button cycle pv no parent iae");
				}
			}
		}
		if(!applet.mousePressed) {
			mdown = false;
			mdown1 = false;
		}
	};


	public void highlight2(){
		col = Bms.col;
	}

	public void highlight2(PVector m){
		col = Bms.col;
	}

	public void highlight3(){
		if(pos()) {
			col = hcol;
			if(globalTheme&&!localFill)col = Bms.hcol;
		}
		else {
			col = fcol;
			if(globalTheme&&!localFill)col = Bms.fcol;

		}

	};

	public void highlight3(PVector m){
		if(pos(m)) {
			col = Bms.hcol;
		}
		else {
			col = Bms.fcol;
		}

	};

	public void highlight(){

		if(toggle){
			col = toggleCol;
			if(globalTheme&&!localFill)col = Bms.toggleCol;
			if(pos()){

				col = hcol;
				if(globalTheme&&!localFill)col = Bms.hcol;
			}
		}else{

			if(pos()){
				col = hcol;
				if(globalTheme&&!localFill)col = Bms.hcol;
			}else{
				col = fcol;
				if(globalTheme&&!localFill)col = Bms.fcol;
			}

		}
	};

	public void highlight(PVector m){

		if(toggle){
			col = toggleCol;
			if(globalTheme&&!localFill)col = Bms.toggleCol;
			if(pos(mouse)){

				col = hcol;
				if(globalTheme&&!localFill)col = Bms.hcol;
			}
		}else{
			col = fcol;
			if(globalTheme&&!localFill)col = Bms.fcol;
			if(pos(mouse)){

				col = hcol;
				if(globalTheme&&!localFill)col = Bms.hcol;
			}
		}
	};

	public void setRadio() {
		plain = false;
		toggleBox = false;
		classicBar = false;
		radio = true;
	};

	public void setToggleBox() {
		plain = false;
		toggleBox = true;
		classicBar = false;
		radio = false;
	};

	public void setclassicBar() {
		plain = false;
		toggleBox = false;
		classicBar = true;
		radio = false;
	};

	public void setPlain() {
		plain = plain;
		toggleBox = false;
		classicBar = false;
		radio = false;
	};

	public void setLabelOff() {
		labelVisible = false;
	};

	boolean subpos(){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return applet.mouseX> x + w-20+xoff&& applet.mouseX < x + w&& applet.mouseY > y&& applet.mouseY < y + h;

	};

	boolean subpos(PVector m){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return m.x> x + w-20+xoff&& m.x < x + w&& m.y > y&& m.y < y + h;

	};

	boolean subposLeft(){
		float X = 0;
		float Y = 0;
		if(parent!=null){
			X = parent.x;
			Y = parent.y;
		}

		return applet.mouseX> x&& applet.mouseX < x + 20&& applet.mouseY > y&& applet.mouseY < y + h;

	};

	boolean subposLeft(PVector m){

		return m.x> x&& m.x < x + 20&& m.y > y&& m.y < y + h;

	};

	public void setPos(float a,float b) {
		x = a;
		bx = a;
		y = b;
		by = b;
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

	public void setClassicBar() {

		classicBar = true;
		toggleBox = false;
		radio = false;
		plain = false;

	};

	public void setVars(float a,float b,float c,float d,String s) {

		x = a;
		y = b;
		bx = x;
		by = y;
		w = c;
		h = d;
		label = s;
	};

	public void setVars(float a,float b,float c,float d) {

		x = a;
		y = b;
		bx = x;
		by = y;
		w = c;
		h = d;

	};

	public void setBorder(boolean k) {
		border = k;
		localBorder = k;
	};

	public void setBorder(float a) {
		border = true;
		borderSize = a;
		localBorder = true;
	};

	public void setBorder(float a,int b) {
		border = true;
		borderSize = a;
		borderCol = b;
		localBorder = true;
	};

	public void setBorderCol(int b) {
		border = true;
		borderCol = b;
		localBorder = true;
	};

	public void setBorderSize(int a) {

		border = true;
		borderSize = a;
		localBorder = true;
	};

	public void setTextSize(float a){
		textSize = a;
		localText = true;
	};

	public void setTextColor(int a){
		tcol = a;
		localText = true;
	};

	public void setText(float a,int b){
		textSize = a;
		tcol = b;
		localText = true;
	};

};

