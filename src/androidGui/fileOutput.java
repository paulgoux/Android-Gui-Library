package androidGui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import processing.core.PApplet;

public class fileOutput {

	public BMScontrols Bms;
	public PApplet applet;
	public PrintWriter output;
	public boolean save, onMouseUp, mdown, debug, append, appendFile, match,append2,overWrite;
	public int counter, counter2;
	public File file;
	public File[] SDcards ; 
	public String location, filePath,folderPath = "";
	String text = "oioijsofoivnsoindv";
	Activity activity;
	Context context;
	
	fileOutput() {
		appendFile = true;
	};

	fileOutput(boolean a,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		overWrite = true;
		appendFile = true;
		Activity activity = applet.getActivity();
		Context context = activity.getApplicationContext();
		SDcards = context.getExternalFilesDirs(null);
	};
	
	fileOutput(BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		Activity activity = applet.getActivity();
		Context context = activity.getApplicationContext();
		SDcards = context.getExternalFilesDirs(null);
//		checkLocation(location);
		//open();
	};


	//currently unused
	fileOutput(String location,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		Activity activity = applet.getActivity();
		Context context = activity.getApplicationContext();
		SDcards = context.getExternalFilesDirs(null);
		checkLocation(location);
		open();
	};
	
	fileOutput(String location, boolean m,BMScontrols bms) {

		Bms = bms;
		applet = bms.applet;
		appendFile = true;
		Activity activity = applet.getActivity();
		Context context = activity.getApplicationContext();
		SDcards = context.getExternalFilesDirs(null);
		checkLocation(location);
		//open();
		//file = applet.dataFile(location);
		//filePath = file.getPath();
	};
	
	public void generateNoteOnSD(Context context, String sFileName, String sBody) {
	    try {
	        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
	        if (!root.exists()) {
	            root.mkdirs();
	        }
	        File gpxfile = new File(root, sFileName);
	        FileWriter writer = new FileWriter(gpxfile);
	        writer.append(sBody);
	        writer.flush();
	        writer.close();
	        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private String readFromFile(Context context) {

	    String ret = "";

	    try {
	        InputStream inputStream = context.openFileInput("config.txt");

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append("\n").append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	    	applet.println("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	    	applet.println("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}

	void checkLocation(String location){

		int count = 0;
		folderPath = SDcards[0].toString()+"//" +folderPath;
		for(int i=location.length()-1;i>-1;i--){
			char c = location.charAt(i);

			if(c=='\\'){
				folderPath = location.substring(0,i);
				this.location = location.substring(i,location.length());
				count ++;
				break;
			}
		}
		if(count==0)this.location = location;

		folderPath = SDcards[0].toString();
		this.location = this.location.replace("\\","");
		String s1 = folderPath;
		String s2 = this.location.replace("\\","");
		applet.println("folder",folderPath,"file",location);


		applet.println("checkLocation:",s1 + "/" + counter + "/" + s2);
	};

	void update(String folder, String file,int counter ){
		//filePath = folder + "\\" + file;
		this.folderPath = folder ;
		this.location = file;
		this.counter = counter;
		appendFile = false;
		overWrite = true;
	};

	void saveData() {
		if (mdown()) {
			checkFile( location, append);
		}
		if (mdown)
			output.println(applet.mouseX + ",+ " + applet.mouseY);
		close();
	};

	void open() {
		checkFile(location, append);
	};

	void write(String s) {
		//function to write without overwriting or append
		if(output!=null)
		{
			applet.println("success",s);
			output.println(s);
			output.flush();
		}
		else applet.print("Create Save File");
	};

	void write(float f) {
		output.println(applet.str(f));
	};

	void write_(String s) {
		//function to overwrite file
		output.print(s);
		if(output!=null){
			applet.println("success",s);
			output.flush(); // Writes the remaining data to the file
			output.close(); // Finishes the file
		}else applet.println("failed",location,folderPath);
	};

	void write(String []s) {
		String s1 = "";
		for (int i=0; i<s.length; i++) {
			s1 += s[i];
		}
		if(s1!=null&&output!=null)output.println(s1);
	};

	void close() {
		if(output!=null){
			output.flush(); // Writes the remaining data to the file
			output.close(); // Finishes the file
		}else applet.println(location,folderPath);
	};

	boolean onMouseUp() {
		boolean k = false;
		if (pos()&&!applet.mousePressed&&onMouseUp) {
			onMouseUp = false;
		} else if (pos()&&applet.mousePressed&&!onMouseUp) {
			output.println(counter);
			onMouseUp = true;
			k = false;
		} else if (onMouseUp&&!applet.mousePressed) {
			k = true;
			onMouseUp = false;
			counter ++;
		}

		return k;
	};

	boolean mdown() {
		boolean k= false;
		if (mdown)k = false;
		if (applet.mousePressed&&!mdown) {
			mdown = true;
			k = true;
		}
		if (!applet.mousePressed)mdown = false;    
		return k;
	};

	boolean pos() {
		return applet.mouseX>0&&applet.mouseX<applet.width&&applet.mouseY>0&&applet.mouseY<applet.height;
	};

	void checkFile(String location, boolean append) {
		if (appendFile) {

			file = applet.dataFile(folderPath + "/" + location);
			filePath = file.getPath();
			filePath = filePath.replace(location, "");
			String[] list = null;
			if(debug)applet.println("checkFile");
			//applet.println(filePath);
			if (listNames(filePath)!=null&&!match) {

				list = listNames(filePath);
				boolean b = false;
				for(int j=Bms.maxFolderSize;j>-1;j--){
					//applet.println(j);
					if(b)break;
					counter = j;
					for (int i=list.length-1;i>-1; i--) {

						int n =   Integer.parseInt(list[i]);
						if (j == n){
							//applet.println(j + ", " + list[i]);
							counter = j;
							b = true;
							break;
						}}}
				match = true;
				if(!b)counter = -1;
				if(counter>=0)counter = counter + 1;
				else counter = 0;

				//applet.println(counter);
			}
			else{
				file = applet.dataFile(folderPath + "/" + location);
				filePath = file.getPath();
				filePath = filePath.replace(location, ""); 
			}
			file = applet.dataFile(folderPath + "/" + counter + location);
			filePath = file.getPath();
			appendFile = false;
			append = true;
		}
		if(file!=null&&(!file.exists()&&!append2)) {
			//output = applet.createWriter("/data/" + folderPath + "/" + counter + location);
			//applet.println("folderpath " + folderPath + "\\" + counter+"\\" + location);
		}

		if(overWrite||output == null){
			output = applet.createWriter(folderPath + "/" + counter + "/"+ location);
			//applet.println("new folderpath " + folderPath + counter + "\\" + location);
			file = applet.dataFile(folderPath + "/" + counter + "/" + location);
			filePath = file.getPath();
			filePath = filePath.replace(location, ""); 
		}
		if (debug) applet.println(filePath);
		try {

			FileWriter fw = new FileWriter(file, append);///true = append
			BufferedWriter bw = new BufferedWriter(fw);
			output = new PrintWriter(bw);
			applet.println("success",output);
		}
		catch(IOException ioe) {
			applet.println("Exception ");
			ioe.printStackTrace();
			applet.println(filePath);
		}
	};

	String[] listNames(String dir) {

		if(dir==null)return null;
		File file  = new File(dir);
		if (file.isDirectory()) {
			String names[] = file.list();
			return names;
		} else {
			// If it's not a directory
			return null;
		}
	};

	int totalFiles(String dir) {
		File file  = new File(dir);
		if (file.isDirectory()) {
			String names[] = file.list();
			return names.length;
		} else {
			// If it's not a directory
			return -1;
		}
	};

	String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return null;
	};
	
	
};