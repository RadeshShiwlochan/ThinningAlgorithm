import java.util.Scanner;
import java.io.*;
public class ThinningAlgrthm {

	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	protected int[][] firstAry;
	protected int[][] secondAry;
	private boolean changeflag;
	private int cycleCount;
	
	public ThinningAlgrthm(String inputFile) {
		try {
			Scanner input = new Scanner(new File(inputFile));
			numRows = input.nextInt();
			numCols = input.nextInt();
			minVal  = input.nextInt();
			maxVal  = input.nextInt();
			
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
			    }
			}
			readFile.close();
		} catch (IOException exc) {
			System.out.println(exc);
		}
	}
	
	public void prettyPrint(String outputFile) {
		
		try {
			int pixel_value;
			PrintWriter  printToFile = new PrintWriter(new File(outputFile));
			//read in the input file
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
			printToFile.flush();
			printToFile.close();
		}catch(IOException ioe) {
			System.out.println(ioe);
		}
	}//prettyPrint method
	
	public void DoThinning(int rowIndex, int colIndex) {
		
	}
	
	public void NorthThinning() {
		for(int i = 1; i < numRows + 2; ++i) {
			for(int j = 1; j < numCols + 2; ++j) {
				if(firstAry[i][i] > 1 && 
				   firstAry[i - 1][j] == 0) {
					DoThinning(i,j);
					break;
				}
			}
		}
	}
	
	public void SouthThinning() {
		for(int i = 1; i < numRows + 2; ++i) {
			for(int j = 1; j < numCols + 2; ++j) {
				if(firstAry[i][i] > 1 &&
				   firstAry[i + 1][j] == 0) {
					DoThinning(i,j);
					break;
				}
			}
		}
	}
	
	public void EastThinning() {
		for(int i = 1; i < numRows + 2; ++i) {
			for(int j = 1; j < numCols + 2; ++j) {
				if(firstAry[i][i] > 1 && 
				   firstAry[i][j + 1] == 0) {
					DoThinning(i,j);
					break;
				}
			}
		}
	}
	
	public void WestThinning() {
		for(int i = 1; i < numRows + 2; ++i) {
			for(int j = 1; j < numCols + 2; ++j) {
				if(firstAry[i][i] > 1 && 
				   firstAry[i][j - 1] == 0) {
					DoThinning(i,j);
					break;
				}
			}
		}	
	}
	
	public void copyAry() {
		firstAry = secondAry;
//		for(int i = 0; i < numRows + 2; ++i) {
//			for(int j = 0; j < numCols + 2; ++j) {
//				firstAry[i][j] = secondAry[i][j];
//			}
//		}
	}
	
	public boolean checkNghbrsNotZero(int rowIndex, int colIndex) {
		int counter = 0;
		if(firstAry[rowIndex - 1][colIndex - 1] > 1)
			counter++;
		if(firstAry[rowIndex - 1][colIndex] > 1)
			counter++;
		if(firstAry[rowIndex - 1][colIndex + 1] > 1)
			counter++;
		if(firstAry[rowIndex][colIndex - 1] > 1)
			counter++;
		if(firstAry[rowIndex][colIndex] > 1)
			counter++;
		if(firstAry[rowIndex][colIndex + 1] > 1)
			counter++;
		if(firstAry[rowIndex + 1][colIndex - 1] > 1)
			counter++;
		if(firstAry[rowIndex + 1][colIndex] > 1)
			counter++;
		if(firstAry[rowIndex + 1][colIndex + 1] > 1)
			counter++;
		if(counter >= 3)
			return true;
		return false;
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
	
	public void printFirstArr(String outputFile) {
		try {
			PrintWriter printToFile 
						= new PrintWriter(new File(outputFile));
			int rowSize = numRows + 2, colSize = numCols + 2;
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
	
	public void printsecondtArr(String outputFile2) {
		try {
			PrintWriter printnum 
						= new PrintWriter(new File(outputFile2));
			int rowSize = numRows + 2, colSize = numCols + 2;
			for(int i = 0; i < rowSize; ++i) {
				for(int j = 0; j < colSize; ++j) {
					printnum.print(secondAry[i][j] + " ");
				}
				printnum.println();
			}
			printnum.flush();
			printnum.close();
		}catch(IOException ioe) {
			System.out.println(ioe);
		}
	}//printFirstArr
	
}
