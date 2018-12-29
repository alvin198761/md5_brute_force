package org.alvin.md5;

import java.util.regex.Pattern;

public class Test {

	public static void main(String [] args){
		String text ="test String $tex_t;";

//		Pattern pattern =Pattern.compile("^((public)|(privete)|(protected)).+$");
		System.out.println(text.matches("^((public)|(private)|(protected))\\s[\\s\\w$;]+$"));
	}
}
