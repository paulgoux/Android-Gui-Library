package androidGui;

import android.os.Environment;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.io.File;

import android.Manifest; 
import android.content.pm.PackageManager; 
import android.os.Build; 
import android.os.Build.VERSION_CODES; 
import android.app.Activity; 

public class imageSaver{
	Permission wstorage,rstorage;
	Activity act;
	public PApplet p;
	boolean imageSaved;
	public int counter;
	public String folderName = "MyImageFolder",ext,fileName, absolutePath;
	public String imageFile = "MyImage.png";
	public PImage img;
	File file,file2;

	public imageSaver(PApplet applet){
		p = applet;
		wstorage = new Permission(p,"WRITE_EXTERNAL_STORAGE");
		act = p.getActivity();
		getExt(imageFile);
	};

	public imageSaver(PApplet applet,String s1, String s2){
		folderName = s1;
		imageFile = s2;
		p = applet;
		wstorage = new Permission(p,"WRITE_EXTERNAL_STORAGE");
		act = p.getActivity();
		getExt(imageFile);
	};

	public void logic() {
		if(true) {
			img = p.get();
			checkLocation(folderName, imageFile);
			save();
			counter++;
		}
	};

	public void logic(PGraphics canvas) {
		if(true) {
			
			img = canvas.get();
			checkLocation(folderName, imageFile);
			save();
			counter ++;
			
			
		}
	};


	public void checkLocation(String sf, String tf) { 
	    boolean k = false;
	    try { 
	      absolutePath = new String(Environment.getExternalStorageDirectory().getAbsolutePath()); 
	      file = new File(absolutePath+"/"+sf); 
	      PApplet.println("checking file1",file,counter);
	      if (!file.exists()) { 
	        boolean success = true; 
	        success = file.mkdirs();
	        img.save(file+"/"+fileName+"."+ext);
	      } else k = true;
	      if(counter>-1)k=true;
	      boolean k1 = false;
	      while(k&&counter<100&&!k1) {
	        try { 
	          file2 = new File(absolutePath+"/"+folderName+"/"+fileName+counter+"."+ext); 
	          PApplet.println("checking file2",file2);
	          if (file2.exists()) { 
	            counter++;
	          } else {
	            k = false;
	            k1 = true;
	            break;
	          }
	        } 
	        catch (Exception e) { 
	          PApplet.println("Error while saving file: " + e);
	        }
	      }
	      if(k1){
	        PApplet.println("file",file);
	        PApplet.println("Fname",folderName);
	        PApplet.println("fileName",fileName);
	        PApplet.println("counter",counter);
	        PApplet.println("ext",ext);
	        if(counter>-1)img.save(file+"/"+fileName+counter+"."+ext);
	        imageSaved = true;
	        PApplet.println("File saved successfully.");
	      }
	    } 
	    catch (Exception e) { 
	      PApplet.println("Error while saving file: " + e);
	    }
	  };
	
	void getExt(String location){

		int count = 0;
		fileName = location.substring(0,location.indexOf("."));
		ext = location.replace(fileName,"");
	    ext = ext.replace(".","");
	    ext = ext.replace(fileName,"");
	};

	public void save() {
		img.save(file+"/"+fileName+counter+"."+ext);
	};
};