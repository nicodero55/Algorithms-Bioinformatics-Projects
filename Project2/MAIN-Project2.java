/**
 * Hash Table Comparisons: Method library containing several definitions for 
 * hashing schemes. Various hashing methods include cases for different division 
 * techniques, different hash functions, and different collision handling strategies.
 * 
 * User: Nicolas Escudero
 * Algorithms for Bioinformatics EN.605.620
 * 10/21/2025
 * Version 1.0
 *  
 */

import java.util.*;
import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;

public class Proj2_CompiledCode_NicolasEscudero {

private static List<String> file_array;
private static List<List<Integer>> chainMap; // make private chaining methods 
	
/**
 * Method used to read a File that the user initially inputs prior to code execution. 
 * The File document is read through the use of the Scanner commands, and only
 * integer values are taken out of the input File. These numbers are stored in a List.
 * 
 * @param filename: Name of the file to be read into algorithm. 
 * @return inputArr: List<Integer> of each numerical value within an input file. 
 * 
 */
public static List<Integer> readFile(String filename) throws IOException {
	
	List<Integer> inputArr = new ArrayList<>();
	File newData = new File(filename);

	
	try (Scanner newScan = new Scanner(newData).useDelimiter("\\s+")) {
			while (newScan.hasNextLine()) {
				String d = newScan.nextLine();
				
				String[] tok = d.trim().split("\\n");
				
				for (String toks : tok) {
					if (!toks.isBlank() && !toks.matches(".*[a-zA-Z].*") ) {
						int num = Integer.parseInt(toks);
						inputArr.add(num);
						}
				}
			}
	}
	catch (IOException errIO) { // Handles input errors 
		System.out.println("File Not Found, Please Try Again.");
		errIO.printStackTrace();
	}
	
	return inputArr;
}


/**
 * MAIN METHOD. All specific cases for function calls are called here. 
 * Outputs each test case in a readable file format. 
 * 
 * @param args: Text file to read into program. 
 *
 * 
 */
public static void main(String[] args) throws IOException {

	// Input Stream Initialization
	Scanner newScan = new Scanner(args[0]); 
	String inputFile = newScan.nextLine();
	
	newScan.close();
	
	//initiate file reading 
	List<Integer> data = (readFile(inputFile));
	
	
	/** FUNCTION CALLS START HERE **/
	
	file_array = new ArrayList<>();
	


	// Case 1 //
	List<String> case1 = new ArrayList<>();
	
	long startcase1 = System.nanoTime(); // Timekeeping method start
	case1 = divHash(data, file_array, 120, 120, 0, "Linear");
	long stopcase1 = System.nanoTime(); // Timekeeping method stop
	
	long t1 = stopcase1 - startcase1; 
	System.out.println("Time for Case 1: " + t1);
	
	// Case 2 //
	List<String> case2 = new ArrayList<>();
	
	long startcase2 = System.nanoTime(); // Timekeeping method start
	case2 = divHash(data, file_array, 120, 120, 0, "Quadratic"); 
	long stopcase2 = System.nanoTime(); // Timekeeping method stop
	
	long t2 = stopcase2 - startcase2; 
	System.out.println("Time for Case 2: " + t2);

	
	
	// Case 3 //
	List<String> case3 = new ArrayList<>();
	
	long startcase3 = System.nanoTime(); // Timekeeping method start
	case3 = divChain(data, file_array, 120, 120, 0); 
	long stopcase3 = System.nanoTime(); // Timekeeping method stop
	
	long t3 = stopcase3 - startcase3; 
	System.out.println("Time for Case 3: " + t3);

	

	// Case 4 //
	List<String> case4 = new ArrayList<>();
	
	long startcase4 = System.nanoTime(); // Timekeeping method start
	case4 = divHash(data, file_array, 127, 120, 0, "Linear"); 
	long stopcase4 = System.nanoTime(); // Timekeeping method stop
	
	long t4 = stopcase4 - startcase4; 
	System.out.println("Time for Case 4: " + t4);


	
	// Case 5 //
	List<String> case5 = new ArrayList<>();
	
	long startcase5 = System.nanoTime(); // Timekeeping method start
	case5 = divHash(data, file_array, 127, 120, 0, "Quadratic"); 
	long stopcase5 = System.nanoTime(); // Timekeeping method stop
	
	long t5 = stopcase5 - startcase5; 
	System.out.println("Time for Case 5: " + t5);

	
	// Case 6 //	
	List<String> case6 = new ArrayList<>();
	
	long startcase6 = System.nanoTime(); // Timekeeping method start
	case6 = divChain(data, file_array, 127, 120, 0); 
	long stopcase6 = System.nanoTime(); // Timekeeping method stop
	
	long t6 = stopcase6 - startcase6; 
	System.out.println("Time for Case 6: " + t6);


	// Case 7 //
	List<String> case7 = new ArrayList<>();
	
	long startcase7 = System.nanoTime(); // Timekeeping method start
	case7 = divHash(data, file_array, 113, 120, 0, "Linear"); 
	long stopcase7 = System.nanoTime(); // Timekeeping method stop
	
	long t7 = stopcase7 - startcase7; 
	System.out.println("Time for Case 7: " + t7);


	// Case 8 //
	List<String> case8 = new ArrayList<>();
	
	long startcase8 = System.nanoTime(); // Timekeeping method start
	case8 = divHash(data, file_array, 113, 120, 0, "Quadratic"); 
	long stopcase8 = System.nanoTime(); // Timekeeping method stop
	
	long t8 = stopcase8 - startcase8; 
	System.out.println("Time for Case 8: " + t8);

			
	// Case 9 //		
	List<String> case9 = new ArrayList<>();
	
	long startcase9 = System.nanoTime(); // Timekeeping method start
	case9 = divChain(data, file_array, 113, 120, 0); 
	long stopcase9 = System.nanoTime(); // Timekeeping method stop
	
	long t9 = stopcase9 - startcase9; 
	System.out.println("Time for Case 9: " + t9);


	// Case 10 //
	List<String> case10 = new ArrayList<>();
	
	long startcase10 = System.nanoTime(); // Timekeeping method start
	case10 = divHash(data, file_array, 41, 40, 3, "Linear"); 
	long stopcase10 = System.nanoTime(); // Timekeeping method stop
	
	long t10 = stopcase10 - startcase10; 
	System.out.println("Time for Case 10: " + t10);

	
	// Case 11 //
	List<String> case11 = new ArrayList<>();
	
	long startcase11 = System.nanoTime(); // Timekeeping method start
	case11 = divHash(data, file_array, 41, 40, 3, "Quadratic"); 
	long stopcase11 = System.nanoTime(); // Timekeeping method stop
	
	long t11 = stopcase11 - startcase11; 
	System.out.println("Time for Case 11: " + t11);

	
	// Case 12 //
	List<String> case12 = new ArrayList<>();
	
	long startcase12 = System.nanoTime(); // Timekeeping method start
	case12 = myHash(data, file_array, 123, 120, 0, "Linear"); 
	long stopcase12 = System.nanoTime(); // Timekeeping method stop
	
	long t12 = stopcase12 - startcase12; 
	System.out.println("Time for Case 12: " + t12);


	// Case 13 //
	List<String> case13 = new ArrayList<>();
	
	long startcase13 = System.nanoTime(); // Timekeeping method start
	case13 = myHash(data, file_array, 123, 120, 0, "Quadratic"); 
	long stopcase13 = System.nanoTime(); // Timekeeping method stop
	
	long t13 = stopcase13 - startcase13; 
	System.out.println("Time for Case 13: " + t13);

	
	// Case 14 //
	List<String> case14 = new ArrayList<>();
	
	long startcase14 = System.nanoTime(); // Timekeeping method start
	case14 = myChain(data, file_array, 123, 120, 0); 
	long stopcase14 = System.nanoTime(); // Timekeeping method stop
	
	long t14 = stopcase14 - startcase14; 
	System.out.println("Time for Case 14: " + t14);

	
	
	// PRINTING ONTO FILE // 
	
	try {
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter("output.txt"));
		fileWrite.write("OUTPUT:\n");
		
		// counter for formatting
		int f = 0;
		int cs = 1;
		
		for (String fin : case1) {
		
			if (cs == 11 || cs == 12) {
				if (fin == "newcase") { 
					fileWrite.write("\n\n" + "Output for case: " 
				+ cs + "\n"); // Separation for new case
					cs = cs + 1;
				}
				
				else {
					fileWrite.write(fin + "   "); // Add new value 
					f = f + 1;
					
					if (f%3 == 0) {
						fileWrite.write("\n"); // Separate by 3. 
					}
				}

			}
			else {
				if (fin == "newcase") { 
					fileWrite.write("\n\n" + "Output for case: " 
				+ cs + "\n"); // Separation for new case
					cs = cs + 1;
				}
				
				else {
					fileWrite.write(fin + "   "); // Add new value 
					f = f + 1;
					
					if (f%5 == 0) {
						fileWrite.write("\n"); // Separate by 5. 
					}
				}
			}
			
		}
		
		fileWrite.write("\nTime for Case 1: " + t1);
		fileWrite.write("\nTime for Case 2: " + t2);
		fileWrite.write("\nTime for Case 3: " + t3);
		fileWrite.write("\nTime for Case 4: " + t4);
		fileWrite.write("\nTime for Case 5: " + t5);
		fileWrite.write("\nTime for Case 6: " + t6);
		fileWrite.write("\nTime for Case 7: " + t7);
		fileWrite.write("\nTime for Case 8: " + t8);
		fileWrite.write("\nTime for Case 9: " + t9);
		fileWrite.write("\nTime for Case 10: " + t10);
		fileWrite.write("\nTime for Case 11: " + t11);
		fileWrite.write("\nTime for Case 12: " + t12);
		fileWrite.write("\nTime for Case 13: " + t13);
		fileWrite.write("\nTime for Case 14: " + t14);
		
		
		fileWrite.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
} // END main method


/**
 * Division Hashing Method: A method used to create a new HashMap via Division method.
 * The division method parameters, called in a separate method, 
 * are specified by the user prior to execution. 
 * 
 * @param input: Input array for analysis. 
 * @param modiv: The divisor of the hashing function. The input divided by this number dictates placement within the hash table.
 * @param hsize: Specified size of the hash table. 
 * @param load: Specified size of each bucket in the hash. 
 * @param col: Method of collision handling to use; Linear or Quadratic.
 * 
 * 
 * @return hashMap: The currently updated hashMap
 */
public static List<String> divHash(List<Integer> input, List<String> file_array,
		int modiv, int hsize, int load, String col)  {
	
	int l = input.size();
	
	// if load is defined, increase hsize by load
	if (load != 0) {
		hsize = hsize * load;
		modiv = modiv * load;
	}
	
	// initialize hash map w specified sizes 
	Map<Integer, Integer> hashMap = new HashMap<>(hsize);
	
	
	// for size of list, take each integer and do the following:
	for (int i = 0; i < l; i++) {
		// Reference external method for providing hash
		hashMap = divHashFunc(hashMap, input.get(i), hsize, modiv, col);
	}
	
	// For File making 
	
	// Add delimeter, to signal when a new case is to be added

		file_array.add("newcase");
		
	for (int i = 0; i < hsize; i++) {
		
		// if NULL, put a blank string
		if (hashMap.get(i) == null) {
			file_array.add("-----");
		}
		// otherwise, place value in array
		else {
			String str = String.valueOf(hashMap.get(i));
			file_array.add(str);
		} 
	}
	
	return file_array; // return the file array
}


/**
 * Chained Division Hashing Method: A method used to create a new Chaining Hash Map via Division method.
 * The division method parameters, called in a separate method, 
 * are specified by the user prior to execution. 
 * 
 * @param input: Input array for analysis. 
 * @param modiv: The divisor of the hashing function. The input divided by this number dictates placement within the hash table.
 * @param hsize: Specified size of the hash table. 
 * @param load: Specified size of each bucket in the hash. 
 * 
 * @return chainMap: The currently updated hashMap
 */
public static List<String> divChain(List<Integer> input, List<String> file_array,
		int modiv, int hsize, int load)  {	
				
	// Initialize new chaining map 
	chainMap = new ArrayList<>();
	
	// insert an array into each element of new chaining map  
	for (int i = 0; i < hsize; i++) {
		chainMap.add(new ArrayList<>());
	}
	
	// call new chaining method 
	for (int i = 0; i < input.size(); i++) {
	chainMap = divChainFunc(chainMap, input.get(i), hsize, modiv);
	}
		
	// For File making 

	// Add delimeter, to signal when a new case is to be added
	file_array.add("newcase");

	for (int i = 0; i < hsize; i++) {
		
		// if NULL, put a blank string
		if (chainMap.get(i).isEmpty()) {
			file_array.add("-----");
		}
		// otherwise, place value in array
		else {
			String str = String.valueOf(chainMap.get(i));
			file_array.add(str);
		} 
	}
	return file_array; // return the file array
}


/**
 * My Hashing Method: A method used to create a new HashMap and execute 
 * hashing by using a newly developed hashing technique. The hashing technique's
 * parameters, called in a separate method, are specified by the user prior to execution. 
 * 
 * @param input: Input array for analysis.
 * @param modiv: Multiplicity factor for this hashing function
 * @param hsize: Specified size of the hash table. 
 * @param load: Specified size of each bucket in the hash. 
 * @param col: Method of collision handling, Linear or Quadratic.
 *  
 * @return hashMap: The currently updated hashMap.
 */
public static List<String> myHash(List<Integer> input, List<String> file_array,
		int modiv, int hsize, int load, String col)  {
	
	int l = input.size();

	// initialize hash map with specified sizes
	Map<Integer, Integer> hashMap = new HashMap<>(hsize);
	
	// for size of list, take each integer and do the following:
	for (int i = 0; i < l; i++) {
		// hash function: see myHashFunc description
		// Reference external method for providing hash
		hashMap = myHashFunc(hashMap, input.get(i), hsize, modiv, col);
	}
	
	// For File making 
	
	// Add delimeter, to signal when a new case is to be added
	file_array.add("newcase");

	for (int i = 0; i < hsize; i++) {
		
		// if NULL, put a blank string
		if (hashMap.get(i) == null) {
			file_array.add("-----");
		}
		// otherwise, place value in array
		else {
			String str = String.valueOf(hashMap.get(i));
			file_array.add(str);
		} 
	}
	return file_array; // return the file array	
}


/**
 * My Chained Hashing Method: A method used to create a new HashMap and execute 
 * hashing by using a newly developed hashing technique. The hashing technique's
 * parameters, called in a separate method, are specified by the user prior to execution. 
 * 
 * @param input: Input array for analysis.
 * @param modiv: Multiplicity factor for this hashing function
 * @param hsize: Specified size of the hash table. 
 * @param load: Specified size of each bucket in the hash. 
 *  
 * @return chainMap: The currently updated hashMap.
 */
public static List<String> myChain(List<Integer> input, List<String> file_array,
		int modiv, int hsize, int load)  {
	
	// Initialize new chaining map 
		chainMap = new ArrayList<>();
		
	// insert an array into each element of new chaining map  
	for (int i = 0; i < hsize; i++) {
		chainMap.add(new ArrayList<>());
	}
	
	// call new chaining method 
	for (int i = 0; i < input.size(); i++) {
		chainMap = myChainFunc(chainMap, input.get(i), hsize, modiv);
	}
	
	// For File making 
	
	// Add delimeter, to signal when a new case is to be added
	file_array.add("newcase");
	
	for (int i = 0; i < hsize; i++) {
		
		// if NULL, put a blank string
		if (chainMap.get(i).isEmpty()) {
			file_array.add("-----");
		}
		// otherwise, place value in array
		else {
			String str = String.valueOf(chainMap.get(i));
			file_array.add(str);
		} 
	}
	
	return file_array; // return the file array
}


/**
 * Method used for defining the hash function using Division. 
 * User will specify divisor of the method prior to execution, in which the 
 * algorithm will divide the input value by the specified divisor. The result 
 * is used as the index for which bucket to hash to.  
 * 
 * @param hashMap: The hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param modiv: The specified modulo divisor, which is needed for hash function. 
 * @param col: Specify collision handling method.
 * 
 * @return hashMap: The previous hashMap, now with a new value inserted into 
 * one of its buckets. 
 */
public static Map<Integer, Integer> divHashFunc(Map<Integer,Integer> hashMap, 
		int input, int hsize, int modiv, String col) {
	int hval = input % modiv; // hash function for division
	
	// insert input value into appropriate hashMap bucket
	if (hashMap.get(hval) == null) {
		hashMap.put(hval, input);
	}
	
	
	else {
		// Collision Cases
		if (col == "Linear") {
			linCol(hashMap, input, hsize, hval);
		}
		if (col == "Quadratic") {
			quadCol(hashMap, input, hsize, hval);
		}
	}

	return hashMap;
}
///**



/**
 * Method used for defining the division hash function for Chain Method.
 * User will specify divisor of the method prior to execution, in which the 
 * algorithm will divide the input value by the specified divisor. The result 
 * is used as the index for which bucket to hash to, in a chain-like manner. 
 * 
 * @param chainMap: The chaining hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param modiv: The specified modulo divisor, which is needed for hash function. 
 * 
 * @return chainMap: The previous chainMap, now with a new value inserted or linked 
 * onto one of its buckets. 
 */

public static List<List<Integer>> divChainFunc(List<List<Integer>> chainMap, 
		int input, int hsize, int modiv) {
	
	int hval = input % modiv; // hash function for division
	// insert input value into appropriate bucket, or link to an already filled one
			
		// Loop back if key surpasses 120.
		if (hval >= hsize) {
			while (hval > hsize - 1) {
				hval = hval - (hsize - 1);
			}
		}
			
		chainMap.get(hval).add(input);

	return chainMap;
}


/**
 * Method used for defining my User-generated hash function.
 * The hash function is defined as the input value multiplied by some 
 * prime constant "123", followed by an extraction of the resulting 
 * number's middle two digits. These digits are then multiplied by another
 * constant, in order to obtain all possible buckets 1-120. 
 * 
 * @param hashMap: The hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param modiv: The specified modulo divisor, which is needed for hash function. 
 * @param col: Collision method to be used, between Linear or Quadratic. 
 *
 * @return hashMap: The previous hashMap, now with a new value inserted into 
 * one of its buckets. 
 */
public static Map<Integer, Integer> myHashFunc(Map<Integer,Integer> hashMap,
		int input, int hsize, int modiv, String col) {

	// hash function for multiplication method:
	String strnum = String.valueOf(input*modiv); // multiply input by specified number
	int lg = strnum.length(); // obtain string length for partitioning
	int first;
	int second;
	
	first = (lg/2) - 1; //Obtain middle 3 digits
	second = first + 3;
	

	
	double hval1 = Integer.parseInt(strnum.substring(first, second));
	int hval = (int) ((int) hval1); // constant chosen to achieve buckets

	// Handle looping to keep a value within hsize.
	if (hval >= hsize) {
		while (hval > hsize - 1) {
			hval = hval - (hsize - 1);
		}

	}

	// insert input value into appropriate hashMap bucket
		if (hashMap.get(hval) == null) {
			hashMap.put(hval, input);
		}
		else {
			// Collision Cases

			if (col == "Linear") {
				linCol(hashMap, input, hsize, hval);
			}
			if (col == "Quadratic") {
				quadCol(hashMap, input, hsize, hval);
			}
			
		}
				
return hashMap;
}


/**
 * Method used for defining my User-generated hash function with Chaining.
 * The hash function is defined as the input value multiplied by some 
 * prime constant "123", followed by an extraction of the resulting 
 * number's middle two digits. These digits are then multiplied by another
 * constant, in order to obtain all possible buckets 1-120. 
 * 
 * @param chainMap: The hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param modiv: The specified modulo divisor, which is needed for hash function. 
 *
 * @return chainMap: The previous hashMap, now with a new value inserted into 
 * or linked onto one of its buckets. 
 */
public static List<List<Integer>> myChainFunc(List<List<Integer>> chainMap, 
		int input, int hsize, int modiv) {
	
	// hash function for multiplication method:
		String strnum = String.valueOf(input*modiv); // multiply input by modiv
		int lg = strnum.length(); // obtain string length for partitioning
		int first;
		int second;
		
		// if statement for if the length of string size is only 1 
		first = (lg/2) - 1; // Obtain middle digits
		second = first + 3;
		
		double hval1 = Integer.parseInt(strnum.substring(first, second));
		int hval =  (int) ((int) hval1); // constant chosen to achieve buckets
		
		// Loop back if key surpasses hash size.
		if (hval >= hsize) {
			while (hval > hsize - 1) {
				hval = hval - (hsize - 1);
			}
		}
		
	// insert input value into appropriate hashMap bucket
	chainMap.get(hval).add(input);

	return chainMap;
}


/**
 * Collision Handling Method: Linear Probing 
 * This method is used to handle collisions in the hashMap by means of linear 
 * probing. If a collision happens at a certain bucket, this algorithm will signal 
 * to move the key up one increment. It will keep moving up one increment until an 
 * "empty" bucket is encountered, in which the value will then be placed in that bucket. 
 * 
 * @param hashMap: The hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param hval: The current key, or bucket number, of the hashMap. 
 * 
 * @return hashMap: Current hashmap.
 */
public static Map<Integer, Integer> linCol(Map<Integer, Integer> hashMap, 
		int input, int hsize, int hval) {
	// temp storage for later insertion 
	int hval2 = hval;
	
	// while the hash is still not NULL , increment index by one. 
	while (hashMap.get(hval2) != null) {
		hval2++;
		
		// Loop back if key surpasses hash size.
		if (hval2 >= hsize) {
			hval2 = 0;
		}
	}

	//If null, insert value into hash. 
	if (hashMap.get(hval2) == null) {
		hashMap.put(hval2,  input);
	}

	return hashMap;
}


/**
 * Collision Handling Method: Quadratic Probing
 * This method is used to handle collisions by means of a 
 * quadratic computation.
 * 
 * @param hashMap: The hashMap used to store our values.
 * @param input: The current value from our data to be placed in hashMap.
 * @param hval: The current key, or bucket number, of the hashMap. 
 * 
 * @return hashMap: Current hashmap.
 */
public static Map<Integer, Integer> quadCol(Map<Integer, Integer> hashMap, 
		int input, int hsize, int hval) {
	// temporary storage for later insertion 
	int hval2 = hval;
	int i = 1;

	while (hashMap.get(hval2) != null) {
		
			hval2 = hval2 + (i*i);
		
			// Loop back if key surpasses hash size.
			if (hval2 >= hsize) {
				while (hval2 > hsize - 1) {
					hval2 = hval2 - (hsize - 1);
				}
			}
	
			
			// check current bucket, if full then fill and return hashMap
			if (hashMap.get(hval2) == null) {
				hashMap.put(hval2,  input);
				return hashMap;
			}
			
			else {
				i = i + 1; // increase increment
			}
	}
	
	return hashMap;
	
}

} // END OF CODE


