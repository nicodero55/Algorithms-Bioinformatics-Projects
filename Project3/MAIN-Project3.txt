
/**
 * Dynamic Programming Sequence Alignment: 
 * A set of methods that define multiple ways of 
 * performing a Sequence Alignment of two sequences. 
 * Methods include a Dynamic Programming approach, using Needleman-Wunsch,
 * and a Brute Force method of all viable alignments. 
 * 
 * User: Nicolas Escudero
 * Algorithms for Bioinformatics EN.605.620
 * 11/18/2025
 * Version 1.0
 */

import java.util.*;
import java.io.*;

public class Proj3_CompiledCode_NicolasEscudero {

/**
 * Method used to read a File that the user initially inputs prior to code execution. 
 * The File document is read through the use of the Scanner commands, and only
 * integer values are taken out of the input File. These numbers are stored in a List.
 * 
 * @param filename: Name of the file to be read into algorithm. 
 * @return inputArr: List<String> of each sequence string within an input file. 
 * 
 */
	public static List<String> readFile(String filename) throws IOException {
		
		List<String> inputArr = new ArrayList<>();
		File newData = new File(filename);
// if string contains equals sign prior to it, then extract the value after 
		
		try (Scanner newScan = new Scanner(newData).useDelimiter("\\s+")) {
				while (newScan.hasNext()) {
					String d = newScan.next();
					
					if (!d.isBlank() && !d.contains("=") && !d.matches(".*\\d.*")) {
						 if (!d.matches("[ACTG]+")) {
						        System.err.println("Input error: Valid base pairs only (A, C, T, or G)");
						        List<String> error = new ArrayList<>();
						        return error;
						    }
						 else {
						inputArr.add(d);}
					}
				}
		}
		catch (IOException errIO) { // Handles input errors 
			System.err.println("File Error: File Not Found, Please Try Again.");
			errIO.printStackTrace();
		}
		
		return inputArr;
	}
	

	
	public static void main(String[] args) throws IOException{
		// Input Stream Initialization
		Scanner newScan = new Scanner(args[0]); 
		String inputFile = newScan.nextLine();
		
		newScan.close();
		
		//initiate file reading 
		List<String> data = (readFile(inputFile));    
		System.out.println(data);
		
		
		/// DP METHOD EXECUTION ///
		long startdp = System.nanoTime(); // Timekeeping method start
		
		//Compare every pair of strings 
		String DP_Full = "";
		for (int i = 0; i < data.size() - 1; i++) {
			for (int j = i + 1; j < data.size(); j++) {
				
				String temp = (DP(data.get(i), data.get(j)));
				DP_Full = DP_Full + temp + "\n";
			}
		}
		
		long stopdp = System.nanoTime(); // Timekeeping method stop\
		


	/// BRUTE METHOD EXECUTION ///
		
		long startbtf = System.nanoTime(); // Timekeeping method start
		//Compare every pair of strings 
		String Brute_Full = "";

		for (int i1 = 0; i1 < data.size() - 1; i1++) {
			for (int j = i1 + 1; j < data.size(); j++) {
				
				String temp = (Brute(data.get(i1), data.get(j)));
				Brute_Full = Brute_Full + temp + "\n";

			}
		}
		long stopbtf = System.nanoTime(); // Timekeeping method stop

		
	// PRINTING ONTO FILE//
	try {
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter("output.txt"));
		fileWrite.write("OUTPUT:\n");
		
		String[] DP_OutList = DP_Full.split("\n"); 
		String[] Brute_OutList = Brute_Full.split("\n"); 
		int ctMatch = 0;
		int ctLCS1 = 1;
		int ctLCS2 = 2;
		int ctCount = 3;
		int ctBLCS = 0;
		int ctB = 1;
		
		for (int i = 1; i <= data.size() - 1; i++) {
			for (int j = i + 1; j <= data.size(); j++) {
		fileWrite.write("\n\nDP LCS Sequence " + i + " AND " + j + ":\n"  + DP_OutList[ctMatch]);
		fileWrite.write("\nOptimal Aligned Sequence: \n"  + DP_OutList[ctLCS1] + "\n" + DP_OutList[ctLCS2]);
		fileWrite.write("\nCounted Steps for Alignment: \n"  + DP_OutList[ctCount]);

		ctMatch = ctMatch + 4;
		ctLCS1 = ctLCS1 + 4;
		ctLCS2 = ctLCS2 + 4;
		ctCount = ctCount + 4;
		
		
		
		fileWrite.write("\n\nBrute Force LCS Sequence " + i + " AND " + j + ":\n"  + Brute_OutList[ctBLCS]);
		fileWrite.write("\nCounted Steps for Alignment: \n"  + Brute_OutList[ctB]);		
		ctBLCS = ctBLCS + 2;
		ctB = ctB + 2;
		
		fileWrite.write("\n\n-----------------------------------");
			}
		}
			
		long t= stopdp - startdp; 
		long ti= stopbtf - startbtf; 
		fileWrite.write("\n\nDP Method Total Time: " + t);
		fileWrite.write("\n\nBrute Force Method Total Time: " + ti);
		
		fileWrite.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	
	} // END MAIN

	
/**
 * Dynamic Programming Method: This method is designed to achieve the most 
 * optimal sequence alignment, using a form of the Needleman-Wunsch Dynamic 
 * Programming method. Comparing two given DNA sequences results at a time, 
 * the method outputs the optimal LCS of the two sequences, adding appropriate
 * gaps when necessary. 
 * 
 * @param S1: Input String 1 for comparison.
 * @param S2: Input String 2 for comparison.
 * 
 * @return lcsOutput: The optimal LCS of the two input sequences. 
 */
	public static String DP(String S1, String S2) {
		int L1 = S1.length(); //size of first string
		int L2 = S2.length(); //size of second string 
		int count = 0;
		
		int[][] dpTable = new int[L1+1][L2+1];
		String lcsOutput = " "; 
		
		// table building
		for (int i = 1; i <= L1; i++) {
			for (int j = 1; j <= L2; j++) {
				if (S1.charAt(i-1) == S2.charAt(j-1)) { //if identical characters are detected 
					dpTable[i][j] = dpTable[i-1][j-1] + 1; // current table index is the diagonal + 1
					count = count + 1;
				}
				
				else {
					dpTable[i][j] = Math.max(dpTable[i-1][j], dpTable[i][j-1]);
					count = count + 1;
				}
				
			}
			
		}
		
		// Reconstruction method 
//		int len = L1 + L2;
		int i = L1; int j = L2;
//		int x = len; int y = len;
		String lcs_S1 = ""; 
		String lcs_S2 = "";
		String lcs_match = " ";
		
		// Use DP array to backtrack through string matches
		while (!(i == 0) && !(j == 0)) {
			
			if ( (S1.charAt(i-1) == S2.charAt(j-1))) {
				
				lcs_S1 = S1.charAt(i-1) + lcs_S1;
				lcs_S2 = S2.charAt(j-1) + lcs_S2;
				
				lcs_match = S1.charAt(i-1) + lcs_match;
				
				i--;
				j--;
				
				count = count + 1;
				
			}
			else if (dpTable[i-1][j] == dpTable[i][j]) {  
				
				lcs_S1 = S1.charAt(i-1) + lcs_S1;
				lcs_S2 = "-" + lcs_S2;
				
				i--;
				
				count = count + 1;
				
			}
			else if (dpTable[i][j-1] == dpTable[i][j]) {
						
				lcs_S1 = "-" + lcs_S1;
				lcs_S2 = S2.charAt(j-1) + lcs_S2; 
				
				j--;
				
				count = count + 1;

			}
			
		}
		
//		System.out.println("DP Method Count: " + count);
		String dpMCount = Integer.toString(count);
//		l
		lcsOutput = lcs_match + "\n" + lcs_S1 + "\n" + lcs_S2 + "\n" + dpMCount;
		return lcsOutput;
	} // END DP METHOD //
	
	
/**
 * Brute Force Method: This method is designed to achieve the most 
 * optimal sequence alignment, using a Brute Force strategy. 
 * Also comparing two given DNA sequences results at a time, 
 * the method outputs the optimal LCS of the two sequences by testing 
 * and comparing the similarity of all possible alignments, then determining 
 * the maximum alignment of these. 
 * 
 * @param S1: Input String 1 for comparison.
 * @param S2: Input String 2 for comparison.
 * 
 * @return bruteLCS: The optimal LCS of the two input sequences. 
 */
	public static String Brute(String S1, String S2) {

		int count = Math.max(S1.length(), S2.length()); // need length of string for base case
		int tot_count = 0; // counting number of comparisons
		
		List<String> brute_x = new ArrayList<>();
		List<String> brute_y = new ArrayList<>(); // The empty list arrays, for comparison
	
		brute_x = makeList(S1, brute_x, count);
		brute_y = makeList(S2, brute_y, count); //call new method for making all gap combinations 
		
	
		int maxMatch = 0;
		String bruteLCS = " ";
		
		int l1 = brute_x.size();
		int l2 = brute_y.size();
		System.out.println("Total comparisons expected: " + l1 + " x " + l2 + " = " + (l1*l2));
		
		// now compare each brute_x set. The more matches, the higher score 
		for (int i = 0; i < l1; i++) {
			for (int j = 0; j < l2; j++) {
				tot_count = tot_count + 1;


				String v1 = brute_x.get(i);
				String v2 = brute_y.get(j);
				int cMatch = 0;
				String bruteTemp = "";
				int min_vLength = Math.min(v1.length(),v2.length());
			
				
				
				for (int p = 0; p < min_vLength; p++) {
					
					if ( (v1.charAt(p) != '-') && (v2.charAt(p) != '-' ) ) { // no comparing gaps
						if (v1.charAt(p) == v2.charAt(p)) {
							cMatch = cMatch + 2; // add 1 for every matching character
							bruteTemp = bruteTemp + v1.charAt(p);
						}
					}	
					
				}
				if (cMatch > maxMatch) {
					maxMatch = cMatch; // replace current maximum match 
					bruteLCS = bruteTemp;
				}
			}
		}
		bruteLCS = bruteLCS + "\n" + Integer.toString(tot_count);
		System.out.println("Brute Force Method Count: " + tot_count);
		return bruteLCS;
	}
	
/**
 * Making Alignment Lists: This method is designed to create an ArrayList
 * element, each corresponding to all of the possible combinations of 
 * gaps and sequence alignment structures possible for a given sequence. 
 * 
 * @param S: Input String reference
 * @param bruteList: Running list of alignment sequences. 
 * @param count: current count, used for recursive calls. 
 * 
 * @return bruteList: Running list of alignment sequences. 
 */
	public static List<String> makeList(String S, List<String> bruteList, int count) {

		// BASE CASE 
		if (count == 0) {
//			System.out.println("TEST BASE");
			return bruteList;
		}
		
		// OTHERWISE CASE 
		else {
			
//			System.out.println("TEST ELSE");
			//Perform insertions 
			for (int i = 0; i < S.length(); i++) {
				StringBuilder temp = new StringBuilder(S);
				temp.insert(i, '-'); // insert into appropriate place
				
//				count--; //decrease count by 1. 
				
				// obtain temp string, add into bigger List 
				if (!bruteList.contains(temp.toString()) ) { // prevent duplicates 
					bruteList.add(temp.toString());
				}
				
								
				//recursively call function 
//				System.out.println("TEST time 4 recursive: " + temp);
//				System.out.println("TEST count " + count);
				bruteList = makeList(temp.toString(), bruteList, count-1);
			}
			
		}
		
		return bruteList ;
	}
	
} // END CLASS //
