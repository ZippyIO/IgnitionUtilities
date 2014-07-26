package com.zippy.ig.common;

import net.minecraft.util.StatCollector;

public class Lang {
	  
	  public static final String prefix = "ignitionutilities.";

	  public static String localize(String s) {
	    return localize(s, true);
	  }

	  public static String localize(String s, boolean appendIG) {
	    if(appendIG) {
	      s = prefix + s;
	    }
	    return StatCollector.translateToLocal(s);
	  }

	  public static String[] localizeList(String string) {
	    String s = localize(string);
	    return s.split("\\|");
	  }

	}