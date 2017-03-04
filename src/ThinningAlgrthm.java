import java.util.Scanner;
import java.io.*;
public class ThinningAlgrthm {

	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	private int[][] firstAry;
	private int[][] secondAry;
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
			
			for(int i = 0; i <= rowSize; ++i) {
				for(int j = 0; j <= colSize; ++j) {
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
		for(int i = 0; i <= numRows + 1; i++) {
			array[i][0]           = array[i][1];
			array[i][numCols + 1] = array[i][numCols];
		}
		//framing top to bottom
		for(int j = 0; j <= numCols + 1; j++) {
			 array[0][j]           = array[1][j];
			 array[numRows + 1][j] = array[numRows][j];
		}
	}
	
}
