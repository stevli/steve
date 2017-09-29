package chatbotProject;

import java.util.Scanner;

public class ChatbotMain {

	public static void main(String[] args) {
		chatbot.startTalking();

	}
	public static Chatbot chatbot= new Chatbot();


	private static Scanner inputSource = new Scanner(System.in);
	
	public static int findKeyword(String searchString, String keyword, int startPsn) {
		  searchString=searchString.toLowerCase();
		  keyword=keyword.toLowerCase();
		  int psn=searchString.indexOf(keyword,startPsn);
		  while(psn>=0) {
			  if(keywordIsIsolated(psn,keyword,searchString)&& noNegations(searchString,psn)) 
				  return psn;
			  psn=searchString.indexOf(keyword, psn+1);  
		  }
		  return -1;
	}
	public static boolean keywordIsIsolated(int psn, String keyword, String s){
	   try {
	  	 return(s.substring(psn,psn+keyword.length()).equals(keyword)&&s.substring(psn-1,psn).compareTo("a")<0&&s.substring(psn+keyword.length(),psn+1+keyword.length()).compareTo("a")<0);
	   }
	   catch(StringIndexOutOfBoundsException e){
	  	 try {
	  		 return(s.substring(psn+keyword.length(),psn+1+keyword.length()).compareTo("a")<0);
	  	 }
	  	 catch(StringIndexOutOfBoundsException x){
	  		 return true;
	  	 }
	   }
	}
	
	public static boolean noNegations(String s, int psn){
		  final String[] words = {"no","never","not"};
		  boolean check=true;
		  for(int i=0;i<words.length;i++) {
			  try {
				   if(s.substring(psn-1-words[i].length(),psn-1).equals(words[i])) {
					  check=false;
				   }
			  }
			  catch(StringIndexOutOfBoundsException e) {
				  check=true;
			  }
		  }
		  return check;
	}
	
	
	public static String getInput(){
	  return inputSource.nextLine();
	}
	
	public static void print(String s){
	  multiLinePrint(s);
	}
	
	public static void multiLinePrint(String s){
	  String printString = "";
	int cutoff = 25;
	//this while loop last as long as there are words left in the original String
	while(s.length() > 0){
	
	String currentCut = "";
	String nextWord = "";
	
	//while the current cut is still less than the line length 
	//AND there are still words left to add
	while(currentCut.length()+nextWord.length() < cutoff && s.length() > 0){
	
	//add the next word
	currentCut += nextWord;
	
	//remove the word that was added from the original String
	s = s.substring(nextWord.length());
	
	//identify the following word, exclude the space
	int endOfWord = s.indexOf(" ");
	
	//if there are no more spaces, this is the last word, so add the whole thing
	if(endOfWord == -1) {
	endOfWord = s.length()-1;//subtract 1 because index of last letter is one les than length
	}
	
	//the next word should include the space
	nextWord = s.substring(0,endOfWord+1);
	}
	
	printString +=currentCut+"\n";
	
	}
	System.out.print(printString);
	}
	
	public static int getIntegerInput() {
	print("Please enter an integer.");
	String integerString = getInput();
	boolean isInteger = false;
	int value = 0;
	while(!isInteger){
	try{
	value = Integer.parseInt(integerString);
	//will not continue if an error above is thrown
	isInteger = true;//exits loop if entry is valid
	}catch(NumberFormatException e){
	print("You must enter an integer. You better try again.");
	integerString = getInput();
	}
	}
	return value;
	}
	
	}

