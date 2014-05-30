//package com.hensel.commander.crypto;
//
///**
//* Title:   �bung 1.1 (Kodierung und Komprimierung)
//* Copyright:     Copyright (c) 2005
//* Organisation:  TFH Berlin
//* @author Raik Frindte & Daniel Hensel
//* @version 1.0
//* Created on: 13.10.2005
//*/
//
//
//
//import thread.LoadThread;
//import java.io.*;
//import java.util.*;
//
//import javax.swing.JTextArea;
//
//class MyException extends Exception{
//  public MyException(String e){
//    super(e);
//  }
//}
//
//public abstract class Crypto {
//
//
//  public static boolean Vergleiche(String name) throws MyException {
//  File inputFile = new File(name);
//  File keyFile = new File("key.txt");
//  long file1= inputFile.length();
//  long file2= keyFile.length();
//  if(file1==file2)
//  return true;
//  else
//  throw new MyException("Die Schluessellaenge und Dateilaenge stimmen nicht ueberein!!");
//
//  }
//
//
//
//
//
//	   /***********************************************************
// 		*														  *
// 		*						Verschl�sselung					  *
// 		*														  *
// 		***********************************************************/
//
//  	// Verschl�sselung funktioniert
//	public static void Verschluesselung(JTextArea textArea, String name, LoadThread t) throws IOException {
//		
//		MyWindow.prog_bar.setValue(0);
//		File inputFile = new File(name);
//		File keyFile = new File("Commander/crypto_temp/key.txt");
//		File codeOutputFile = new File("Commander/crypto_temp/codeoutput.txt");
//		FileInputStream in = new FileInputStream(inputFile);
//		FileOutputStream codeOut = new FileOutputStream(codeOutputFile);
//		FileOutputStream keyOut = new FileOutputStream(keyFile);
//		String codeinputString = new String();
//		textArea.setText("Verschluesselung erfolgt :\n");
//		int fileLength = (int)inputFile.length();
//		int temp;
//		int index = 0;
//
//		int keyText;
//		int c;
//	    Random r = new Random(5);
//
//
//	 	while( (c=in.read()) !=-1 ) {
//	 		
//			keyText=r.nextInt(1000);
//			keyOut.write(keyText);
//			temp = c^keyText;
//			codeOut.write(c^keyText);
//			textArea.append(Integer.toString(c^keyText));
//			//textArea.paintImmediately(textArea.getBounds());
//			//codeoutputString+=c^keyText;
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//			//System.out.println("Prozent : "+(++index/(double)fileLength)*100+" %");
//			//System.out.println("Prozent : "+(++index%100+" %"));
//			
//			if(t.isCanceled()){
//				in.close();
//				codeOut.close();
//				keyOut.close();
//				textArea.append("\nVerschluesselung abgebrochen ....");
//				textArea.paintImmediately(textArea.getBounds());
//				return;
//			}
//			
//		}
//
//		in.close();
//		codeOut.close();
//		keyOut.close();
//		textArea.append("\nVerschluesselung erfolgreich");
//		//return codeoutputString;
//		MyWindow.prog_bar.setValue(0);
//
//	}
//	
//	
//	// Verschl�sselung muss nochmal �berarbeitet werden
//	public static void Verschluesselung2(JTextArea textArea, String filename, LoadThread t ) throws IOException {
//
//		
//		File keyFile = new File("Commander/crypto_temp/key_"+filename+".key");
//		
//		File codeOutputFile = new File("Commander/crypto_temp/codeoutput_"+filename+".txt");
//		//FileInputStream in = new FileInputStream(inputFile);
//		FileOutputStream codeOut = new FileOutputStream(codeOutputFile);
//		FileOutputStream keyOut = new FileOutputStream(keyFile);
//		char inputString[] = textArea.getText().toCharArray();
//		int fileLength = (int)inputString.length;
//		textArea.setText("");
//		//textArea.append("Verschluesselter Abschnitt:\n");
//		int index = 0;
//                int cryptoText;
//		int keyText;
//		int c;
//	    Random r = new Random(5);
//
//
//	 	for(int i=0;i<=inputString.length-1;i++ ) {
//			keyText=r.nextInt(1000);
//                        cryptoText = inputString[i]^keyText;
//			codeOut.write(cryptoText);
//			textArea.append(Integer.toString((cryptoText)));
//                        //textArea.append(""+(char)cryptoText);
//			//textArea.append(String.copyValueOf(Character.toChars((cryptoText))));
//			keyOut.write(keyText);
//			//textArea.paintImmediately(textArea.getBounds());
//			//codeoutputString+=c^keyText;
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//			
//			if(t.isCanceled()){
//				keyOut.close();
//				codeOut.close();
//				textArea.append("\nVerschluesselung abgebrochen ....");
//				textArea.paintImmediately(textArea.getBounds());
//				return;
//			}
//		}
//
//		//in.close();
//		codeOut.close();
//		keyOut.close();
//		
//		System.out.println("Verschluesselung erfolgreich");
//		//return textArea;
//		MyWindow.prog_bar.setValue(0);
//
//	}
//
//
//        public static void Verschluesselung3(JTextArea textArea, String filename, LoadThread t ) throws IOException {
//
//
//		File keyFile        = new File("Commander/crypto_temp/key_"+filename+".key");
//                File encryptedFile  = new File("Commander/crypto_temp/encrypted_"+filename+".ecf");
//		File codeOutputFile = new File("Commander/crypto_temp/codeoutput_"+filename+".txt");
//		//FileInputStream in = new FileInputStream(inputFile);
//		FileOutputStream codeOut    = new FileOutputStream(codeOutputFile);
//		FileOutputStream keyOut     = new FileOutputStream(keyFile);
//                FileOutputStream encrypted  = new FileOutputStream(encryptedFile);
//		char inputString[] = textArea.getText().toCharArray();
//		int fileLength = (int)inputString.length;
//		textArea.setText("");
//		//textArea.append("Verschluesselter Abschnitt:\n");
//		int index = 0;
//
//		int keyText;
//		int c;
//	    Random r = new Random(5);
//
//
//	 	for(int i=0;i<=inputString.length-1;i++ ) {
//			keyText=r.nextInt(1000);
//			//codeOut.write(inputString[i]^keyText);
//			textArea.append(Integer.toString((inputString[i]^keyText)));
//                        encrypted.write(inputString[i]^keyText);
//                        encrypted.write(keyText);
//			//keyOut.write(keyText);
//			//textArea.paintImmediately(textArea.getBounds());
//			//codeoutputString+=c^keyText;
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//
//			if(t.isCanceled()){
//				keyOut.close();
//				codeOut.close();
//                                encrypted.close();
//				textArea.append("\nVerschluesselung abgebrochen ....");
//				textArea.paintImmediately(textArea.getBounds());
//				return;
//			}
//		}
//
//		//in.close();
//		codeOut.close();
//		keyOut.close();
//                encrypted.close();
//
//		System.out.println("Verschluesselung erfolgreich");
//		//return textArea;
//		MyWindow.prog_bar.setValue(0);
//
//	}
//
//
//
//	   /***********************************************************
// 		*														  *
// 		*					 Entschluesselung					  *
// 		*														  *
// 		***********************************************************/
//
//
//	public static void Entschluesselung(JTextArea textArea,String name , LoadThread t) throws IOException {
//
//		MyWindow.prog_bar.setValue(0);
//
//		System.out.println("Entschluesselung startet");
//		File codeOutputFile = new File("Commander/crypto_temp/codeoutput_"+name+".txt");
//		File keyFile = new File("Commander/crypto_temp/key_"+name+".key");
//		//File decodeOutputFile = new File("crypto_temp/decodeoutput.txt");
//		FileInputStream keyIn = new FileInputStream(keyFile);
//		FileInputStream codeIn = new FileInputStream(codeOutputFile);
//	    //FileOutputStream decodeOut = new FileOutputStream(decodeOutputFile);
//	    String decodeOutputString = new String();
//		textArea.setText("");
//	    int d;
//	    int f;
//            int index = 0;
//             long fileLength = keyFile.length();
//
//	    while( ((f=codeIn.read()) & (d=keyIn.read())) !=-1 ) {
//	    	
//	    	if(t.isCanceled()){
//	    		//decodeOut.close();
//	    		codeIn.close();
//	    		keyIn.close();
//	    		textArea.append("\nEntschluesselung abgebrochen ...");
//				textArea.paintImmediately(textArea.getBounds());
//	    		return;
//	    	}
//	    	
//			//decodeOut.write(f^d);
//			textArea.append(""+(char)(f^d));
////			textArea.append("\ntoBinaryString() :"+Integer.toBinaryString(f^d));
////			textArea.append("\ntoHexString() :"+Integer.toHexString(f^d));
////			textArea.append("\ntoOctalString() :"+Integer.toOctalString(f^d));
//			//textArea.append(new String(f^d));
//			//textArea.paintImmediately(textArea.getBounds());
//                        MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//
//
//		}
//		//decodeOut.close();
//		codeIn.close();
//		keyIn.close();
//		System.out.println("Entschluesselung erfolgreich");
//                MyWindow.prog_bar.setValue(0);
//		//return decodeOutputString;
//		
//	}
//
//	
//	
//	public static void Entschluesselung2(JTextArea textArea,String filename , LoadThread t) throws IOException {
//		
//		MyWindow.prog_bar.setValue(0);
//		char[] inputString = textArea.getText().toCharArray();
//		String inputKey = new String();
//		int fileLength = inputString.length;
//		System.out.println("Entschluesselung startet");
//		//File codeOutputFile = new File(filename);
//		File keyFile = new File("Commander/crypto_temp/key_"+filename+".key");
//		File decodeOutputFile = new File("Commander/crypto_temp/decodeoutput.txt");
//		FileInputStream keyIn = new FileInputStream(keyFile);
//		//FileInputStream codeIn = new FileInputStream(codeOutputFile);
//	    FileOutputStream decodeOut = new FileOutputStream(decodeOutputFile);
//	    
//		textArea.setText("Entschluesselung erfolgt :\n");
//		int index =0;
//
//	    int d;
//	    int f;
//	    
//	    while((f=keyIn.read()) !=-1){
//	    	inputKey+=f;
//	    }
//	    
//	    char[] inputKeyAsChar = inputKey.toCharArray();
//	    
//	    System.out.println("InputStringLaenge : "+inputString.length);
//	    System.out.println("InputKeyLaenge : "+inputKeyAsChar.length);
//	    
//	    for(int i=0;i<fileLength;i++){
//	    	 
//	    
//	    	
//	    	if(t.isCanceled()){
//
//	    		keyIn.close();
//	    		//textArea.append("\nEntschluesselung abgebrochen ...");
//				textArea.paintImmediately(textArea.getBounds());
//	    		return;
//	    	}
//	    	
//			//decodeOut.write(f^d);
//			textArea.append(""+(char)(inputString[i]^inputKeyAsChar[i]));
////			textArea.append("\ntoBinaryString() :"+Integer.toBinaryString(f^d));
////			textArea.append("\ntoHexString() :"+Integer.toHexString(f^d));
////			textArea.append("\ntoOctalString() :"+Integer.toOctalString(f^d));
//			//textArea.append(new String(f^d));
//			//textArea.paintImmediately(textArea.getBounds());
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//		}
//
//		keyIn.close();
//		System.out.println("Entschluesselung erfolgreich");
//		//return decodeOutputString;
//		MyWindow.prog_bar.setValue(0);
//		
//	}
//
//
//
//	   /***********************************************************
//	 	*														  *
//	 	*					 Komprimierung 						  *
//	 	*														  *
// 		***********************************************************/
//
//	public static void Komprimierung(JTextArea textArea,String name, LoadThread t) throws IOException {
//
//
//		MyWindow.prog_bar.setValue(0);
//		File inputFile = new File(name);
//		File komprOutputFile = new File("Commander/crypto_temp/komproutput.txt");
//		FileOutputStream komprOut = new FileOutputStream(komprOutputFile);
//		FileInputStream in = new FileInputStream(inputFile);
//		StringBuffer StrBu = new StringBuffer();
//		int abbruch=0;
//		int abbruch2=0;
//		int i=-1;
//		int biteinlesen;
//		char gecastetesBit;
//		char temp;
//		int count=1;
//		int x=0;
//		int testvari=1;
//		char zeichen1='a';
//		char zeichen2='b';
//		char zeichen3='c';
//		char[] charArray = new char[3];
//		charArray[0]='^';
//		charArray[1]='~';
//		charArray[2]='#';
//		textArea.setText("Komprimierung erfolgt :\n");
//		int index = 0;
//		int fileLength = (int)inputFile.length();
//
//		//�berpr�fung ob das ersetzende Zeichen im Text vorhanden ist
//		while (abbruch2==0) {
//			abbruch=0;
//			i++;
//
//			while(((biteinlesen = in.read()) != -1) & (abbruch==0)) {
//				gecastetesBit = (char)biteinlesen;
//				StrBu.append(gecastetesBit);
//				
//				if(gecastetesBit==charArray[i]) {
//					abbruch=1;
//				}
//			}
//			if (abbruch==0){
//				abbruch2=1;
//			}
//		}
//
//		char flag = charArray[i];
//		komprOut.write(charArray[i]);
//		textArea.append(Character.toString(flag));
//		//textArea.paintImmediately(textArea.getBounds());
//
//		for(int j = x+1; j < StrBu.length(); j++) {
//			zeichen1=StrBu.charAt(x);
//			zeichen2=StrBu.charAt(j);
//
//			if(j <= StrBu.length()-2) {
//				zeichen3=StrBu.charAt(j+1);
//		    }
//			else {
//		  		zeichen3=flag;
//			}
//			if(zeichen1==zeichen2) {
//				count++;
//			}
//			else {
//				if((count==1) && ((StrBu.charAt(0)) != StrBu.charAt(1)) && (testvari==1)){
//					komprOut.write(StrBu.charAt(0));
//					textArea.append(Character.toString(StrBu.charAt(0)));
//					//textArea.paintImmediately(textArea.getBounds());
//					testvari=0;
//				}
//				StringBuffer counter=new StringBuffer();
//				counter.append(count);
//
//				if(count>2) {
//					komprOut.write(charArray[i]);
//					textArea.append(Character.toString(charArray[i]));
//					//textArea.paintImmediately(textArea.getBounds());
//					for(int h=0;h<=counter.length()-1;h++){
//						
//				    	if(t.isCanceled()){
//				    		in.close();
//				    		komprOut.close();
//				    		textArea.append("\nKomprimierung abgebrochen ...");
//							//textArea.paintImmediately(textArea.getBounds());
//				    		return;
//				    	}
//						komprOut.write(counter.charAt(h));
//						textArea.append(Character.toString(counter.charAt(h)));
//						//textArea.paintImmediately(textArea.getBounds());
//					}
//					komprOut.write(charArray[i]);
//					komprOut.write(zeichen1);
//					komprOut.write(zeichen2);
//					
//					textArea.append(Character.toString(charArray[i]));
//					textArea.append(Character.toString(zeichen1));
//					textArea.append(Character.toString(zeichen2));
//					//textArea.paintImmediately(textArea.getBounds());
//				}
//				if((count==1) && (zeichen2!=zeichen3)){
//					komprOut.write(zeichen2);
//					textArea.append(Character.toString(zeichen2));
//					//textArea.paintImmediately(textArea.getBounds());
//				}
//
//				count=1;
//			}
//			x++;
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//	    	if(t.isCanceled()){
//	    		in.close();
//	    		komprOut.close();
//	    		textArea.append("\nKomprimierung abgebrochen ...");
//				textArea.paintImmediately(textArea.getBounds());
//	    		return;
//	    	}
//		}
//		komprOut.close();
//		//in.close();
//		MyWindow.prog_bar.setValue(0);
//	}
//
//
//
//	   /***********************************************************
// 		*														  *
// 		*					 Dekomprimierung					  *
// 		*														  *
// 		***********************************************************/
//
//
//	public static void Dekomprimierung(JTextArea textArea,String name,  LoadThread t) throws IOException{
//
//		MyWindow.prog_bar.setValue(0);
//		File komprOutputFile = new File(name);
//	  	File dekomprOutputFile = new File("Commander/crypto_temp/dekomproutput.txt");
//	  	FileOutputStream dekomprOut = new FileOutputStream(dekomprOutputFile);
//	  	FileInputStream in = new FileInputStream(komprOutputFile);
//	  	StringBuffer StrBu = new StringBuffer();
//	  	StringBuffer StrBu2 = new StringBuffer();
//	  	int biteinlesen;
//	  	char gecastetesBit;
//	  	int count=1;
//		char zeichen1='q';
//		int testvari=1;
//		int index = 0;
//		int fileLength = (int)komprOutputFile.length();
//		int anzahl=0;
//	  	int laenge=0;
//	  	int coun1=1;
//	  	int zahl2;
//	  	String zahl;
//	  	textArea.setText("Dekomprimierung erfolgt :\n");
//
//	  	while((biteinlesen = in.read()) != -1){
//		    gecastetesBit = (char)biteinlesen;
//	  		StrBu.append(gecastetesBit);
//		}
//
//		char flag=StrBu.charAt(0);
//		for(int j=1;j<StrBu.length();j++){
//			
//			MyWindow.prog_bar.setValue((int)(((double)++index/fileLength)*100));
//	    	if(t.isCanceled()){
//	    		in.close();
//	    		dekomprOut.close();
//	    		textArea.append("\nDekomprimierung abgebrochen ...");
//				textArea.paintImmediately(textArea.getBounds());
//	    		return;
//	    	}
//			
//			if(flag!=StrBu.charAt(j)){
//				dekomprOut.write(StrBu.charAt(j));
//	    		textArea.append(Character.toString(StrBu.charAt(j)));
//				//textArea.paintImmediately(textArea.getBounds());
//			}
//			else{
//				coun1=1;
//				while((StrBu.charAt(j+coun1))!=flag){
//					StrBu2.append(StrBu.charAt(j+coun1));
//					coun1++;
//				}
//				j=j+StrBu2.length()+2;
//				zahl=StrBu2.toString();
//				zahl2=Integer.parseInt(zahl);
//				StrBu2.delete(0,(StrBu2.length()));
//				for(int i=0;i<zahl2;i++){
//					dekomprOut.write(StrBu.charAt(j));
//		    		textArea.append(Character.toString(StrBu.charAt(j)));
//					//textArea.paintImmediately(textArea.getBounds());
//				}
//			}
//		}
//		dekomprOut.close();
//	  	in.close();
//	  	
//		MyWindow.prog_bar.setValue(0);
//	 }
// }

