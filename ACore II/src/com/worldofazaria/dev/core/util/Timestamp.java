package com.worldofazaria.dev.core.util;

import java.text.SimpleDateFormat;

public class Timestamp {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy kk:mm:ss]");
	static SimpleDateFormat sdfSimple = new SimpleDateFormat("[kk:mm:ss]");
	
	public static String getTimestampFromLong(long l){
		return sdf.format(l);
	}
	public static String getTimestampSimple(long l){
		return sdfSimple.format(l);
	}
}
