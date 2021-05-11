package androidGui;

import java.io.File;
import java.util.ArrayList;

public class fileUtils {
	public static String[] listFileNames(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			String names[] = file.list();
			return names;
		} else {
			// If it's not a directory
			return null;
		}
	};

	public static ArrayList<String> trimNames(String []dir) {
		ArrayList<String> temp = new ArrayList<String>();
		//if(dir==null)return null;

		for (int i=0; i<dir.length; i++) {
			int count = 0;
			String s = dir[i];
			for (int j=0; j<dir[i].length(); j++) {

				char t = dir[i].charAt(j);

				if ((t=='.'&&j==0)||t=='{')count ++;
			}
			if (count==0&&s!="")temp.add(s);
		}

		return temp;
	};

	// This function returns all the files in a directory as an array of File objects
	// This is useful if you want more info about the file
	public static File[] listFiles(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			return files;
		} else {
			// If it's not a directory
			return null;
		}
	};

	// Function to get a list of all files in a directory and all subdirectories

	public static ArrayList<File> listFilesRecursive(String dir) {
		ArrayList<File> fileList = new ArrayList<File>(); 
		recurseDir(fileList, dir);
		return fileList;
	};

	// Recursive function to traverse subdirectories
	public static  void recurseDir(ArrayList<File> a, String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			// If you want to include directories in the list
			a.add(file);  
			File[] subfiles = file.listFiles();
			for (int i = 0; i < subfiles.length; i++) {
				// Call this function on all files in this directory
				recurseDir(a, subfiles[i].getAbsolutePath());
			}
		} else {
			a.add(file);
		}
	};
	
	public static  String[] listNames2( String dir) {
		File file = new File(dir);
		String []s = null;
		if (file.isDirectory()) {
			// If you want to include directories in the list
			File[] subfiles = file.listFiles();
			if(subfiles!=null) {
				s = new String [subfiles.length];
				for (int i = 0; i < subfiles.length; i++) {
					// Call this function on all files in this directory
					s[i] = subfiles[i].getAbsolutePath();
				}}
			return s;
		}else return null;
	};

	public static String[] listNames(String dir) {

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

	public static int totalFiles(String dir) {
		File file  = new File(dir);
		if (file.isDirectory()) {
			String names[] = file.list();
			return names.length;
		} else {
			// If it's not a directory
			return -1;
		}
	};

	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1);
		else return null;
	};
};
