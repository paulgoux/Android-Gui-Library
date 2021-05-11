package androidGui;

import java.lang.reflect.Method;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class GridImg extends Grid {
	BMScontrols Bms;
	PApplet applet;
	String []imgboxLabels = {"Normal", "Hide", "Graph", "Attractor", "Forward", "Backwards", 
			"Pause", "Heading", "Save", "Load", "Contour", "Mouse", "Reset", 
			"Populate Boundaries", "Spawn Entities", "Debug"};
	Menu imgbox;
	sliderBox imgsliderList;

	public GridImg() {
	};

	public GridImg(float xx, float yy, int ww, int hh, ArrayList<PImage> Images,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		x = xx;
		y = yy;
		xback = x;
		yback = y;
		w = ww;
		h = hh;
		rows = ww;
		cols = hh;
		bordersize = 1;
		images = Images;
		Cols = cols;
		Rows = rows;
		img = images.get(0);
		squares2 = new Grid_space[rows][cols];

		imgbox = new Menu(applet.width-90, applet.height/2, 70, imgboxLabels,bms);
		imgbox.draggable = true;

		Button btn73 = imgbox.items.get(9);

		imgbox.items.get(9).submenu = new Menu(btn73.x, btn73.y, btn73.w, btn73.h,Bms);

		for (int i=0; i<images.size(); i++) {

			Button a =  new Button(btn73.x-btn73.w, btn73.y - 20 * i, imgbox.w, 20, applet.str(i),Bms);
			a.id = i;
			a.parent = imgbox.items.get(9).submenu;
			buttons.add(a);
			a.parent = imgbox.items.get(9).submenu;
			imgbox.items.get(9).submenu.items.add(a);
		}
		//search = new Window( 50,50,200,100,"C:\\Users\\public\\");
		//search.launchable = false;
	};

	public void save() {
	};

	public void load() {
	};

	public void img_init() {

		if (img!=null&&!init) {
			imrows = img.height;
			rows = img.height;
			cols = img.width;
			imcols = img.width;
			imw = img.width;
			imh = img.height;
			totalpixels = imrows*imcols;
		}
		if (toggle&&img!=null) {

			if (imsquares.size()<rows*cols) {
				for (int yy = 0; yy < img.height; yy++) {
					for (int xx = 0; xx< img.width; xx++) {

						int num = xx + yy * img.width;

						Grid_space a = new Grid_space(x+xx, y+yy, 1, 1, num, xx, yy,Bms);
						a.col = img.pixels[xx + yy * img.width];
						a.col2 = a.col;
						a.col3 = a.col;
						a.col4 = a.col;
						a.col5 = a.col;
						a.parent = this;
						a.image = true;
						imsquares.add(a);
					}
				}
			} else init = true;
		}

		if (imreset) init = false;
	};

	public void img_functions() {

		applet.fill(255);
		kernel_modifier();
		//imgopen();
		img_init();
		img_draw();
	};

	public void img_draw() {

		if (img!=null&&toggle) {
			imgbox.draw();
			imgsliderList.draw();
			imgreset = false;
			if (normal_img) draw_img();
			if (mouse_img) draw_imgmouse();
			imgreset = false;
			if (blur_img) draw_imgblur();
			imgreset = false;
			if (linedetect_img) draw_imglinedetect();
			imgreset = false;
			if (dot_img) draw_imgdot();
			imgreset = false;
			if (bright) draw_brightness();
			imgreset = false;
			if (sharp) draw_sharpness();
			imgreset = false;
			if (!normal_img&&!mouse_img&&!blur_img&&!linedetect_img&&!dot_img&&!bright&&!sharp||!imtoggle) convolved = false;
			if (imgsliderList.menu.sltoggle) convolved = false;
		}
	};

	public void draw_img() {

		float count = 0;
		if (!reset) {
			img.loadPixels();
			for (int i=1; i<imsquares.size(); i++) {
				Grid_space a = imsquares.get(i);
				img.pixels[a.id] = applet.color(a.col);

				if (count == imsquares.size()-1) reset = true;
			}
			img.updatePixels();
		}

		applet.image(img, 23, y, img.width, img.height);
	};

	public void move() {
	}

	public void draw_imgblur() {
	};

	public void set(PImage a) {

		img = a;
		img1 = a;
	};

	public void draw_imgmouse() {
		img.loadPixels();
		//img1.loadPixels();
		for (int i=1; i<imsquares.size(); i++) {
			Grid_space a = imsquares.get(i);

			float d = applet.dist(a.x, a.y, applet.mouseX, applet.mouseY);
			float m = applet.map(d, 0, mdist, 255, 0);

			float r = applet.red(a.col2);
			float g = applet.green(a.col2);
			float b = applet.blue(a.col2);
			img.pixels[a.id] = applet.color(r, g, b, m);
			//img1.pixels[a.id] = a.col2;
		}
		img.updatePixels();
		//img1.updatePixels();
		applet.image(img, x, y, imw, imh);
	};

	public void draw_imglinedetect() {
		int count = 0;
		if (imgsliderList.menu.sltoggle) convolved = false;  
		if (!convolved) {
			img.loadPixels();
			//img1.loadPixels();
			applet.fill(255);
			applet.text(hkernel1d[0], 1000, 600);
			for (int i=0; i<imsquares.size(); i++) {

				Grid_space a = imsquares.get(i);

				float temp_color = 0;

				if (!a.neighboured)a.get_neighbours(this);

				for (int j=0; j<a.kernel1d.length; j++) {

					Grid_space a1 = a.kernel1d[j];
					float c = hkernel1d[j];
					float d = vkernel1d[j];
					float left = difflkernel1d[j];
					float right = diffrkernel1d[j];
					float up = diffukernel1d[j];
					float down = diffdkernel1d[j];

					if (a1!=null) {
						temp_color += c * applet.color(a1.col3);
						temp_color += d * applet.color(a1.col3);
						temp_color += left * applet.color(a1.col3);
						temp_color += right * applet.color(a1.col3);
						temp_color += down * applet.color(a1.col3);
						temp_color += up * applet.color(a1.col3);
					}
				}
				count ++;

				temp_color /=5;

				if (temp_color>threshold) {
					img.pixels[a.id] = applet.color(255);
				} else {
					img.pixels[a.id] = applet.color(0);
				}
				//img1.pixels[a.id] = a.col3;
				if (count==totalpixels)convolved = true;
			}
			img.updatePixels();
			//img1.updatePixels();
		}
		applet.image(img, x, y, img.width, img.height);
	};
	//revise code
	//	  public void img_workflow() {
	//
	//	    if(workflow!=null)workflow.displayList();
	//	    if (workflow.done) {
	//	      for (int i=0; i<workflow.tasks.size(); i++) {
	//
	//	        String task = workflow.tasks.get(i);
	//
	//	        Class  aClass = Grid.class;
	//	        Method method = null;
	//	        try {
	//
	//	          method = aClass.getMethod("task", null);
	//	        }
	//	        catch (NullPointerException e) {
	//	        }
	//	        catch (NoSuchMethodException e) {
	//	        }
	//	      }
	//	    }
	//	  };

	public void imgopen() {
		if (menu) {

			//search.toggle=true;
			//search.display_grid();
			if (applet.mousePressed)link = search.currentf;
			if (link!=null) {

				if (link.endsWith("jpg")|| link.endsWith("jpeg") ||link.endsWith("png")||link.endsWith("bmp")||
						link.endsWith("gif")||link.endsWith("JPG")||link.endsWith("JPEG")||link.endsWith("PNG")||
						link.endsWith("BMP")||link.endsWith("GIF")) {

					//if (imgtemp==null||updateimage)
					img = applet.loadImage(link);

					//applet.image(img,100,200);
					imsquares = new ArrayList<Grid_space>();
					im_init = false;
					link = null;
				}
			}
		} 
		//else search.toggle=false;
	};

	public void draw_brightness() {
		int count = 0;
		if (!convolved) {
			img.loadPixels();
			for (int i=0; i<imsquares.size(); i++) {

				Grid_space a = imsquares.get(i);

				float temp_color = 0, bright = 255;

				float r = applet.red(a.col4);
				float g = applet.green(a.col4);
				float b = applet.blue(a.col4);

				if (!a.neighboured)a.get_neighbours(this);
				if (count==0)a.convolved = false;
				//if(!a.convolved){

				for (int j=0; j<a.kernel1d.length; j++) {

					Grid_space a1 = a.kernel1d[j];
					float left = difflkernel1d[j];
					float right = diffrkernel1d[j];
					float up = diffukernel1d[j];
					float down = diffdkernel1d[j];

					if (a1!=null) {
						bright += applet.brightness(a1.col4)*left;
						bright += applet.brightness(a1.col4)*right;
						bright += applet.brightness(a1.col4)*up;
						bright += applet.brightness(a1.col4)*down;
					}
				}
				count ++;
				if (bright>threshold)img.pixels[a.id] = applet.color(255);
				else img.pixels[a.id] = applet.color(0);
				if (count==imsquares.size()-1)convolved = true;
			}

			img.updatePixels();
		}
		applet.image(img, x, y, img.width, img.height);
	};

	public void draw_sharpness() {
		int count = 0, c2 = 0;
		if (!convolved) {
			img.loadPixels();

			for (int i=0; i<imsquares.size(); i++) {

				Grid_space a = imsquares.get(i);

				float r = 0, g =0, b = 0;

				//float r = red(a.col5),g = green(a.col5),b = blue(a.col5);

				if (!a.neighboured)a.get_neighbours(this);
				if (count==totalpixels)convolved = true;
				for (int j=0; j<a.kernel1d.length; j++) {

					Grid_space a1 = a.kernel1d[j];
					float c = hkernel1d[j];
					float d = vkernel1d[j];

					if (a1!=null) {
						r += c*applet.red(a1.col5);
						r += d*applet.red(a1.col5);
						g += c*applet.green(a1.col5);
						g += d*applet.green(a1.col5);
						b += c*applet.blue(a1.col5);
						b += d*applet.blue(a1.col5);
						c2 ++;
					}
					count ++;
				}
				img.pixels[a.id] = applet.color(r, g, b);
				if (count==totalpixels)convolved = true;
			}

			img.updatePixels();
		}
		applet.image(img, x, y, img.width, img.height); 
		if (imgsliderList.menu.sltoggle) convolved = false;
	};

	public void img_refresh() {
		if (!refreshed) {
			img.loadPixels();
			for (int i=1; i<imsquares.size(); i++) {
				Grid_space a = imsquares.get(i);
				//img.pixels[a.id] = a.col;
			}
			img.updatePixels();
			refreshed = true;
		}
	};

	public void draw_imgdot() {

		img.loadPixels();
		for (int i=0; i<imsquares.size(); i++) {
			Grid_space a = imsquares.get(i);
			img.pixels[a.id] = a.col;
			applet.noStroke();
			applet.fill(a.col);
			applet.rect(a.x, a.y, 1, 1);
		}
		img.updatePixels();
	};
};
