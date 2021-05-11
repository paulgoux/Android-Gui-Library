package androidGui;

import processing.core.PApplet;

public class dialogueBox{

	public BMScontrols Bms;
	public PApplet applet;
	public float x,bx,y,by,w,h;
	tab main;
	Button ok,no,close,cancel,help,browse;
	Dropdown choice;
	boolean state,dialogueChoice;


	public dialogueBox(float a,float b,float c,float d,BMScontrols bms) {
		main = new tab(x,y,w,h,bms);
		main.toggle = true;


	};

	public dialogueBox(float a,float b,float c,float d,String s,BMScontrols bms) {
		main = new tab(x,y,w,h,s,bms);
		main.toggle = true;


	};

	public void setButtonsV(){

	};

	public void setButtonsH(float a,float b){

	};

	public void $ok(float x,float y,float w,float h) {

		if(ok==null) {
			ok = new Button(x,y,w,h,Bms);
		}else {
			ok.setVars(x,y,w,h);
		}
		main.add(ok);

	};

	public void $ok(float x,float y,float w,float h,String s) {

		if(ok==null) {
			ok = new Button(x,y,w,h,s,Bms);
		}else {
			ok.setVars(x,y,w,h,s);
		}
		main.add(ok);

	};

	public void $close(float x,float y,float w,float h,String s) {
		if(close==null) {
			close = new Button(x,y,w,h,s,Bms);
		}else {
			close.setVars(x,y,w,h,s);
		}
		main.add(close);
	};

	public void $close(float x,float y,float w,float h) {
		if(close==null) {
			close = new Button(x,y,w,h,Bms);
		}else {
			close.setVars(x,y,w,h);
		}
		main.add(close);
	};

	public void $no(float x,float y,float w,float h,String s) {
		if(no==null) {
			no = new Button(x,y,w,h,s,Bms);
		}else {
			no.setVars(x,y,w,h,s);
		}
		main.add(no);
	};

	public void $no(float x,float y,float w,float h) {
		if(no==null) {
			no = new Button(x,y,w,h,Bms);
		}else {
			no.setVars(x,y,w,h);
		}
		main.add(no);
	};

	public void $browse(float x,float y,float w,float h,String s) {
		if(browse==null) {
			browse = new Button(x,y,w,h,Bms);
		}else {
			browse.setVars(x,y,w,h);
		}
		main.add(browse);
	};

	public void $browse(float x,float y,float w,float h) {
		if(browse==null) {
			browse = new Button(x,y,w,h,Bms);
		}else {
			browse.setVars(x,y,w,h);
		}
		main.add(browse);
	};

	public void $cancel(float x,float y,float w,float h,String s) {
		if(cancel==null) {
			cancel = new Button(x,y,w,h,s,Bms);
		}else {
			cancel.setVars(x,y,w,h,s);
		}
		main.add(cancel);
	};

	public void $cancel(float x,float y,float w,float h) {
		if(cancel==null) {
			cancel = new Button(x,y,w,h,Bms);
		}else {
			cancel.setVars(x,y,w,h);
		}
		main.add(cancel);
	};

	public void $help(float x,float y,float w,float h,String s) {
		if(no==null) {
			no = new Button(x,y,w,h,s,Bms);
		}else {
			no.setVars(x,y,w,h,s);
		}
		main.add(help);
	};

	public void $help(float x,float y,float w,float h) {
		if(no==null) {
			no = new Button(x,y,w,h,Bms);
		}else {
			no.setVars(x,y,w,h);
		}
		main.add(help);
	};

	public void addDD(float x,float y,float w,float h,String s,String[] s1) {
		if(choice==null) {
			choice = new Dropdown(x,y,w,h,s,s1,Bms);
		}
		main.add(choice);
	};

	public void addButton(float x,float y,float w,float h,String s) {

		Button b1 = new Button(x,y,w,h,s,Bms);
		main.add(b1);
	};

	public void addButton(float x,float y,float w,float h) {

		Button b1 = new Button(x,y,w,h,Bms);
		main.add(b1);
	};
	
	public void add(Button b1) {
		main.add(b1);
	};
	
	public void add(Dropdown d1) {
		main.add(d1);
	};



	public void draw() {
		main.displayTab();
		if(close!=null)close.toggle(main,"toggle");
		if(ok!=null) {
			ok.toggle(main,"toggle");
			ok.toggle(this,"dialogueChoice");
		}
		if(browse!=null)browse.toggle(main,"toggle");
		if(no!=null)no.toggle(main,"toggle");
		if(cancel!=null)cancel.toggle(main,"toggle");
		if(help!=null)help.toggle(main,"toggle");
		
	};



};
