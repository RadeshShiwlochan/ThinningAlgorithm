import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends ThinningAlgrthm {


	public Main(String inputFile) {
		super(inputFile);
	}

	public static void main(String [] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		String outputFile2 = args[2];
		try {
			PrintWriter printToFile = new PrintWriter(new File(outputFile2));
			ThinningAlgrthm thinningAlg = new ThinningAlgrthm(inputFile);
			thinningAlg.zeroFramed(thinningAlg.firstAry);
			thinningAlg.zeroFramed(thinningAlg.secondAry);
			thinningAlg.loadImage(inputFile);
			
			thinningAlg.prettyPrint(printToFile);
			thinningAlg.ThinImage(printToFile);
			//prettyPrint result
			thinningAlg.printResult(outputFile);
			//thinningAlg.printsecondtArr(outputFile2);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
}
