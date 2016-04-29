package formatFiddle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

public class Main {

	public static void main(String[] args) {
		
		String str = "geoinfo	30.543251,104.047402";
		String[] strs = str.split("\\s+");
		
		for (int i = 0; i < strs.length; i++) {
			System.out.println(i + ":" + strs[i]);
		}
		
		

	}

}
