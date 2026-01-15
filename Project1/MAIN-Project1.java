import java.util.*;
import java.io.*;

public class ORDINARY {

	/// METHOD FOR FILE READING ///
	public static List<String> readFile(String filename) throws FileNotFoundException {
		
		List<String> L = new ArrayList<>();
		File newData = new File(filename);
		
		try (Scanner newScan = new Scanner(newData).useDelimiter("\\s+")) {
			while (newScan.hasNextLine()) {
				String dat = newScan.nextLine();
				
				// filter out unwanted characters, clean input
				String[] tokens = dat.trim().split("\\n");
				
				for (String token : tokens) {
					if (!token.isBlank()) {
						L.add(token);
					}
				}
			}
		}
		catch (FileNotFoundException err) { // Handles input mistakes 
			System.out.println("File Not Found");
			err.printStackTrace();
		}
		
		
		return L;
	}
	
	/// METHOD FOR FILE PARTITION /// 
	public static List<int[][]> dataSplit(List<String> data) throws FileNotFoundException {
		
		List<int[][]> C = new ArrayList<>(); //Output matrix
		
		int i = 0;
		int n = 0;
		int[][] A = new int[n][n]; // initialize matrices  
		int[][] B = new int[n][n];
		
		while (i < data.size()) {
			
			n = Integer.parseInt(data.get(i)); // make n = whatever character appears first in search.
					
			A = new int[n][n]; // initialize matrices for nxn 
			B = new int[n][n];
			
			int j = i + 1;  // Start extracting needed elements based on n size matrix
			
			String temp = new String(); // Store elements into a temp string
			
			for (int k = 0; k < 2*n; k++) {
				temp = temp + data.get(j + k) + " "; 
			}
					
			int k = 0;

			// Start adding values into matrix A
			for (int p = 0; p < n; p++) { 			
				for (int q = 0; q < n; q++) { 
					char c = temp.charAt(k); // character tracker for individual element string
					while (c == ' ') {			
						k = k + 1;
						c = temp.charAt(k);
					}
									
					if (c != ' ') { // removes white space
				
						String sc = new String(); // used for digits >= 10
						
						// Considering digits >=10
						while ((temp.charAt(k) != ' ') && (k + 1 < temp.length())) {

							sc = "" + sc + c;
							k = k + 1;
							c = temp.charAt(k);
						} 
						int num = Integer.parseInt(sc); // convert to int
						A[p][q] = num; // add into appropriate matrix spot
						k = k + 1;
					}
				}
			}
						
			// Do the same for matrix B
			for (int p = 0; p < n; p++) { 
				for (int q = 0; q < n; q++) {
					char c = temp.charAt(k);

					while (c == ' ') {
						k = k + 1;
						c = temp.charAt(k);
					}
					
					if (c != ' ') {

						String sc = new String();
						while ((temp.charAt(k) != ' ') && (k + 1 <= temp.length())) {
							if (k + 1 == temp.length()) {
								sc = "" + sc + c;
								break; //exits loop on the last element.
							}
							
							sc = "" + sc + c;
							k = k + 1;
							c = temp.charAt(k);
						} 

						int num = Integer.parseInt(sc); 
						B[p][q] = num;
						k = k + 1;
					}
				}	
			}			
			
	
			C.add(A); // Add to output matrix C
	
			C.add(B);

			i = i + (2*n + 1); // Set new position for next determinant, i = 2n + 1
		} //while loop end
		return C;
	} // code end
	
	
	/// MAIN METHOD ///
	public static void main(String[] args) throws FileNotFoundException {
	    
		// Input Stream initialization
		Scanner scanFile = new Scanner(System.in);
		
		String inputFile = scanFile.nextLine();
		
		scanFile.close();
		
		// initiate file reading
		List<String> data = (readFile(inputFile)); // read the file input 
	    List<int[][]> inputMats = dataSplit(data); // use the partitioning method 
	    
	    // Divide the inputs
	    for (int p = 0; p < inputMats.size(); p+=2) {
	    	
	    	// Initialize A, B Matrix Pairs. Also C matrix, and size n
	    	int[][] A = inputMats.get(p);
	    	int[][] B = inputMats.get(p + 1);
	    
	    	
	    	int n = A.length;	
	    	
	    
		   		long startNaive = System.nanoTime(); // Timekeeping method start
	    		
	   	    int[][] naiveMat = Naive(A, B, n); // Perform Naive multiplication 
	    		
	   	    long stopNaive = System.nanoTime(); // Timekeeping method stop
	    	    		
	    	    
    		long startPart = System.nanoTime(); // Timekeeping method start
	    
    		int[][] partMat = Partition(A,B,n); // Perform Strassen Multiplication
	   
	    	long stopPart = System.nanoTime(); // Timekeeping method stop

	    	    // Time Calculations  	 
	   
	    	long timeNaive = (stopNaive - startNaive);
	    	    
	    	long timePart = (stopPart - startPart);

	    	System.out.println(n + "x" + n);
	    	System.out.println("Naive Method Output: "); formatMat(naiveMat);
	    	
	    	System.out.println("Strassen Method Output: "); formatMat(partMat);
	    	
	    	System.out.println("Naive Calculation Time: " + timeNaive + "ms");
	   
	    	System.out.println("Strassen Calculation Time: " + timePart + "ms");	    
	 
	    }
	}

	/// Naive Method of Multiplication ///
	static int[][] Naive(int[][]A, int[][]B, int n) {

		// Create new solutions matrix C 
		int[][] C = new int[n][n];
		
		// Create multiplication 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
				
				}
			}
		}
	
		return C;
	}
	

	/// MATRIX SUMMATION ///
	public static int[][] SumMat(int[][] A, int[][] B) {
	    int rowA = A.length;
	    int colA = A[0].length;

	    // Check for dimension mismatch
	    if (rowA != B.length || colA != B[0].length) {
	        throw new IllegalArgumentException("Matrix dimensions must match.");
	    }

	    int[][] result = new int[rowA][colA];

	    for (int i = 0; i < rowA; i++) {
	        for (int j = 0; j < colA; j++) {
	            result[i][j] = A[i][j] + B[i][j];
	        }
	    }

	    return result;
	}
	
	/// MATRIX SUBTRACTION ///
	public static int[][] DiffMat(int[][] A, int[][] B) {
	    int rowA = A.length;
	    int colA = A[0].length;

	    // Check for dimension mismatch
	    if (rowA != B.length || colA != B[0].length) {
	        throw new IllegalArgumentException("Matrix dimensions must match.");
	    }

	    int[][] result = new int[rowA][colA];

	    for (int i = 0; i < rowA; i++) {
	        for (int j = 0; j < colA; j++) {
	            result[i][j] = A[i][j] - B[i][j];
	        }
	    }

	    return result;
	}
	
	
	/// Formatted Matrix ///
	public static void formatMat(int[][] A) {
	    System.out.println("[");
	    for (int[] i : A) {
	        System.out.print("  [");
	        for (int j = 0; j < i.length; j++) {
	            System.out.print(i[j]);
	            if (j < i.length - 1) System.out.print(", ");
	        }
	        System.out.println("],");
	    }
	    System.out.println("]");
	}
	
	
	
	/// STRASSEN METHOD ///
	static int[][] Partition(int[][] A, int[][] B, int n) {
		
		int subs = n/2;
		int[][] Cfinal = new int[n][n]; // Initialize final output array

		// Base Case: n == 1
		if (n == 1) {
			
		Cfinal[0][0] = Cfinal[0][0] + (A[0][0] * B[0][0]);
			
		return Cfinal;
		}
	
		// STEP 1: Partitioning // 
		else if (n != 1) {
			
			// Make list of matrices for partitioning
			List<int[][]> storeMatA = new ArrayList<>();
			List<int[][]> storeMatB = new ArrayList<>();
			
			
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
			
				// new variable for storing current A,B,C[][]
				int [][] tempA = new int[subs][subs];
				int [][] tempB = new int[subs][subs];
				
				
				for (int s = 0; s < subs; s++) {
					for (int r = 0; r < subs; r++) {
					
						// Fill in matrices
						tempA[s][r] =  A[subs*i+ s][subs*j + r];
						tempB[s][r] =  B[subs*i+ s][subs*j + r];
						
					}
				}
				
				// Add to List arrays
				storeMatA.add(tempA);
				storeMatB.add(tempB);
				
			}		
		} // end FOR loop, Partition portion
		
		// STRASSEN Step 2 //
		
		// Partition into the A1.1, A1.2...
		int[][] A1_1 = new int[subs][subs];
		A1_1 = storeMatA.get(0);
		
		int[][] A1_2 = new int[subs][subs];
		A1_2 = storeMatA.get(1);
		
		int[][] A2_1 = new int[subs][subs];
		A2_1 = storeMatA.get(2);
		
		int[][] A2_2 = new int[subs][subs];
		A2_2 = storeMatA.get(3);
		
		// B Partition
		int[][] B1_1 = new int[subs][subs];
		B1_1 = storeMatB.get(0);
		
		int[][] B1_2 = new int[subs][subs];
		B1_2 = storeMatB.get(1);
		
		int[][] B2_1 = new int[subs][subs];
		B2_1 = storeMatB.get(2);
		
		int[][] B2_2 = new int[subs][subs];
		B2_2 = storeMatB.get(3);
		
		// STEP 3: STRASSEN SUMS //
		
		int[][] S1 = DiffMat(B1_2, B2_2);
		int[][] S2 = SumMat (A1_1, A1_2);
		int[][] S3 = SumMat (A2_1, A2_2);
		int[][] S4 = DiffMat(B2_1, B1_1);
		int[][] S5 = SumMat (A1_1, A2_2);
		int[][] S6 = SumMat (B1_1, B2_2);
		int[][] S7 = DiffMat(A1_2, A2_2);
		int[][] S8 = SumMat (B2_1, B2_2);
		int[][] S9 = DiffMat(A1_1, A2_1);
		int[][] S10 = SumMat(B1_1, B1_2);

		// STEP 4: STRASSEN PRODUCTS //
		
		int[][] P1 = Partition(A1_1, S1, subs);
		int[][] P2 = Partition(S2, B2_2, subs);
		int[][] P3 = Partition(S3, B1_1, subs);
		int[][] P4 = Partition(A2_2, S4, subs);
		int[][] P5 = Partition(S5, S6, subs);
		int[][] P6 = Partition(S7, S8, subs);
		int[][] P7 = Partition(S9, S10, subs);

		// STEP 5: C MATRIX //
		int[][] C1_1 = new int[subs][subs];
		int[][] C1_2 = new int[subs][subs];
		int[][] C2_1 = new int[subs][subs];
		int[][] C2_2 = new int[subs][subs];
		
		C1_1 = SumMat((DiffMat((SumMat((SumMat(C1_1, P5)) , P4)) , P2)) , P6);
		
		C1_2 = SumMat(SumMat(C1_2 , P1) , P2);
		
		C2_1 = SumMat(SumMat(C1_2 , P3) , P4);
		
		C2_2 = DiffMat((DiffMat((SumMat((SumMat(C2_2, P5)) , P1)) , P3)) , P7);	
		
		// FINAL OUTPUT C
		List <int[][]>storeFinalC = new ArrayList<>();
		storeFinalC.add(C1_1);
		storeFinalC.add(C1_2);
		storeFinalC.add(C2_1);
		storeFinalC.add(C2_2);
		
		for (int s = 0; s < subs; s++) {			
			for (int r = 0; r < subs; r++) {			
				Cfinal[subs*0 + s][subs*0 + r] = C1_1[s][r];
			}
		}
		
		for (int s = 0; s < subs; s++) {
			for (int r = 0; r < subs; r++) {	
				Cfinal[subs*0 + s][subs*1 + r] = C1_2[s][r];		
			}		
		}
		
		for (int s = 0; s < subs; s++) {
			for (int r = 0; r < subs; r++) {	
				Cfinal[subs*1 + s][subs*0 + r] = C2_1[s][r];
			}		
		}
			for (int s = 0; s < subs; s++) {
				for (int r = 0; r < subs; r++) {
					Cfinal[subs*1 + s][subs*1 + r] = C2_2[s][r];					
					}
				}
			
		} // END of else if !=2
		
		return (Cfinal); 
	}

} // end class
