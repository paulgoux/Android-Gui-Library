package androidGui;

import processing.core.PApplet;

public class Permission{
	  
	  PApplet parent;

	  public Permission(PApplet pParent,String permissionName) {
	    parent = pParent;
	    parent.requestPermission("android.permission."+permissionName, "onPermissionResult", this);
	    parent.println(permissionName);
	  };

	  public void onPermissionResult(boolean granted) {
	    if (!granted) {
	      PApplet.println("User did not grant camera permission. Camera is disabled.");
	    }
	  };

	};
