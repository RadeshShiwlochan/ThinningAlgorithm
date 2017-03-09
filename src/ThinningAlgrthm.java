import java.util.Scanner;
import java.io.*;

public class ThinningAlgrthm {

	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	protected int[][] firstAry;
	protected int[][] secondAry;
	private boolean changeFlag;
	private int cycleCount;
	
	public ThinningAlgrthm(String inputFile) {
		try {
			Scanner input = new Scanner(new File(inputFile));
			numRows = input.nextInt();
			numCols = input.nextInt();
			minVal  = input.nextInt();
			maxVal  = input.nextInt();
			changeFlag = false;
			cycleCount = 0;
			
			int rowSize = numRows + 2, colSize = numCols + 2;
			firstAry = new int[rowSize][colSize];
			secondAry = new int[rowSize][colSize];
			
			for(int i = 0; i < rowSize; ++i) {
				for(int j = 0; j < colSize; ++j) {
					firstAry[i][j] = 0;
					secondAry[i][j] = 0;
				}
			}
			input.close();
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}//constructor
	
	public void zeroFramed(int[][] array) {
		for(int i = 0; i <= numRows +1; i++) {
			array[i][0]           = array[i][1];
			array[i][numCols + 1] = array[i][numCols];
		}
		//framing top to bottom
		for(int j = 0; j <= numCols + 1; j++) {
			 array[0][j]           = array[1][j];
			 array[numRows + 1][j] = array[numRows][j];
		}
	}//zeroFramed
	
	public void loadImage(String inputFile ) {
		try {
			Scanner readFile = new Scanner(new File(inputFile));
			int pixel_val = -1;
			
			for(int x = 0; x <= 3; x++)
				pixel_val = readFile.nextInt();
			
			for(int i = 1; i <= numRows; ++i) {
				for(int j = 1; j <= numCols; j++) {
					pixel_val = readFile.nextInt();
					firstAry[i][j] = pixel_val;
					secondAry[i][j] = pixel_val;
			    }
			}
			readFile.close();
		} catch (IOException exc) {
			System.out.println(exc);
		}
	}
	
	public void prettyPrint(PrintWriter printToFile) {
		
		int pixel_value;
		printToFile.println("Input Image Pretty Print");
		printToFile.println();
		for(int i = 1; i <= numRows + 1; i++) {
			for(int j = 1; j <= numCols + 1; j++) {
				pixel_value = firstAry[i][j];
				if(pixel_value == 1) 
					printToFile.print(pixel_value + " ");
				else 
				    printToFile.print("  ");
			}
			printToFile.println();
		}
		printToFile.println();
	}//prettyPrint method
	
	public void DoThinning(int rowIndex, int colIndex) {
		
		if(checkNghbrsNotZero(rowIndex,colIndex)) {
			secondAry[rowIndex][colIndex] = 0;
			changeFlag = true;
			cycleCount++;
			if(cycleCount == 6)
				cycleCount = 0;
		}	
	}
	
	public boolean checkNghbrsNotZero(int rowIndex, int colIndex) {
		int counter = 0;
		int [] nghbrArr = loadNghbrs(rowIndex,colIndex);
		for(int i = 0; i < 9; ++i) {
			if(nghbrArr[i] > 0)
				counter++;
		}
	
		if(counter <= 3) {
			return false;
		}
		
		if(counter == 5 || counter == 6 ) {
			if(nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 1 || 
			   nghbrArr[1] == 1                                         ||
			   nghbrArr[6] == 1 && nghbrArr[8] == 1 && nghbrArr[4] == 1) {
				return true;
			}
		}
		
		if(counter == 5 || counter == 6 ) {
			if(nghbrArr[0] == 1 && nghbrArr[8] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[1] == 1 && nghbrArr[7] == 1 ||
			   nghbrArr[2] == 1 && nghbrArr[6] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[3] == 1 && nghbrArr[5] == 1 && nghbrArr[7] == 0 ||  
			   nghbrArr[0] == 1 && nghbrArr[6] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[1] == 0                                         ||
			   nghbrArr[6] == 1 && nghbrArr[8] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[2] == 1 && nghbrArr[1] == 0 ||
			   
			   nghbrArr[1] == 1 && nghbrArr[6] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[1] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[7] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[7] == 1 && nghbrArr[5] == 0 ||
			   
			   nghbrArr[2] == 1 && nghbrArr[3] == 1 && nghbrArr[1] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[5] == 1 && nghbrArr[1] == 0 ||
			   nghbrArr[6] == 1 && nghbrArr[5] == 1 && nghbrArr[7] == 0   ) {
	
				return false;
			}	
		}
		
		if(counter == 4) {
			if(nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[4] == 1 ||
			   nghbrArr[0] == 1 && nghbrArr[4] == 1 && nghbrArr[2] == 1	  ) {
				return true;
			}
		}
		
		if(counter == 4) {
			if(nghbrArr[0] == 1 && nghbrArr[8] == 1 ||
			   nghbrArr[1] == 1 && nghbrArr[7] == 1 ||
			   nghbrArr[2] == 1 && nghbrArr[6] == 1 ||
			   nghbrArr[3] == 1 && nghbrArr[5] == 1 && nghbrArr[7] == 0 ||  
			   nghbrArr[0] == 1 && nghbrArr[6] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[4] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[6] == 1 && nghbrArr[8] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[2] == 1 && nghbrArr[1] == 0 ||
			   
			   nghbrArr[1] == 1 && nghbrArr[6] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[1] == 1 && nghbrArr[8] == 1 && nghbrArr[5] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[7] == 1 && nghbrArr[3] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[7] == 1 && nghbrArr[5] == 0 ||
			   
			   nghbrArr[2] == 1 && nghbrArr[3] == 1 && nghbrArr[1] == 0 ||
			   nghbrArr[2] == 1 && nghbrArr[8] == 1 && nghbrArr[7] == 0 ||
			   nghbrArr[0] == 1 && nghbrArr[5] == 1 && nghbrArr[1] == 0 ||
			   nghbrArr[6] == 1 && nghbrArr[5] == 1 && nghbrArr[7] == 0    ) {
				return false;
			}	
		}
		return true;
	}
	
	public void NorthThinning(PrintWriter printToFile) {
		for(int i = 1; i < numRows + 2; ++i) {
			for(int j = 1; j < numCols + 2; ++j) {
				if(firstAry[i][j] > 0 && 
				   firstAry[i - 1][j] == 0) {
					DoThinning(i,j);
				}
			}
		}
		copyAry();
		if(cycleCount == 0 || cycleCount == 3 || cycleCount == 5)
			prettyPrintProgress(printToFile, "North Thinning results");
			
	}
	
	public void SouthThinning(PrintWriter printToFile) {
		for(int i = 1; i < numRows + 1; ++i) {
			for(int j = 1; j < numCols + 1; ++j) {
				if(firstAry[i][j] > 0 &&
				   firstAry[i + 1][j] == 0) {
					DoThinning(i,j);
				}
			}
		}
		copyAry();
		if(cycleCount == 0 || cycleCount == 3 || cycleCount == 5)
			prettyPrintProgress(printToFile, "South Thinning results");
	}
	
	public void EastThinning(PrintWriter printToFile) {
		for(int i = 1; i < numRows + 1; ++i) {
			for(int j = 1; j < numCols + 1; ++j) {
				if(firstAry[i][j] > 0 && 
				   firstAry[i][j + 1] == 0) {
					DoThinning(i,j);
				}
			}
		}
		copyAry();
		if(cycleCount == 0 || cycleCount == 3 || cycleCount == 5)
			prettyPrintProgress(printToFile, "East Thinning results");
	}
	
	public void WestThinning(PrintWriter printToFile) {
		for(int i = 1; i < numRows + 1; ++i) {
			for(int j = 1; j < numCols + 1; ++j) {
				if(firstAry[i][j] > 0 && 
				   firstAry[i][j - 1] == 0) {
					DoThinning(i,j);
				}
			}
		}
		copyAry();
		if(cycleCount == 0 || cycleCount == 3 || cycleCount == 5)
			prettyPrintProgress(printToFile, "West Thinning results");
	}
	
	public void copyAry() {
		for(int i = 0; i < numRows + 2; ++i) {
			for(int j = 0; j < numCols + 2; ++j) {
				firstAry[i][j] = secondAry[i][j];
			}
		}
	}
	
	public int[] loadNghbrs(int rowIndex, int colIndex) {
		int nghbrArr[] = new int[9];
		nghbrArr[0] = firstAry[rowIndex - 1][colIndex - 1]; 
		nghbrArr[1] = firstAry[rowIndex - 1][colIndex];
		nghbrArr[2] = firstAry[rowIndex - 1][colIndex + 1];
		nghbrArr[3] = firstAry[rowIndex][colIndex - 1];
		nghbrArr[4] = firstAry[rowIndex][colIndex];
		nghbrArr[5] = firstAry[rowIndex][colIndex + 1];
		nghbrArr[6] = firstAry[rowIndex + 1][colIndex - 1];
		nghbrArr[7] = firstAry[rowIndex + 1][colIndex];
		nghbrArr[8] = firstAry[rowIndex + 1][colIndex + 1];
		return nghbrArr;
	}
	
	public void ThinImage(PrintWriter printToFile) {
		
		NorthThinning(printToFile);
		SouthThinning(printToFile);
		EastThinning(printToFile);
		WestThinning(printToFile);
		
		while(changeFlag) {
			changeFlag = false; 
			NorthThinning(printToFile);
			SouthThinning(printToFile);
			EastThinning(printToFile);
			WestThinning(printToFile);
			if(!changeFlag)
				break;
		 }
	}
	
	public void printResult(String outputFile) {
		try {
			PrintWriter printToFile 
						= new PrintWriter(new File(outputFile));
			
			printToFile.println("Final Result of the Thinning Algorithm");
			printToFile.println();
			int rowSize = numRows + 2, colSize = numCols + 2;
			
			printToFile.println(numRows + "  " + numCols + "  " +
			minVal + "  " + maxVal);
			printToFile.println();
			for(int i = 0; i < rowSize; ++i) {
				for(int j = 0; j < colSize; ++j) {
					printToFile.print(firstAry[i][j] + " ");
				}
				printToFile.println();
			}
			printToFile.flush();
			printToFile.close();
		}catch(IOException ioe) {
				System.out.println(ioe);
		}
	}//printFirstArr
	
	public void prettyPrintProgress(PrintWriter printToFile, String whichMethod) {
		
		int rowSize = numRows + 2, colSize = numCols + 2, pixel_value;
		printToFile.println(whichMethod);
			
		for(int i = 1; i < rowSize; i++) {
			for(int j = 1; j < colSize; j++) {
				pixel_value = secondAry[i][j];
				if(pixel_value == 1) 
					printToFile.print(pixel_value + " ");
				else 
					printToFile.print("  ");
			}
			printToFile.println();
		}
	}//printFirstArr	
}
