
public class Main extends ThinningAlgrthm {


	public Main(String inputFile) {
		super(inputFile);
	}

	public static void main(String [] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		String outputFile2 = args[2];
		ThinningAlgrthm thinningAlg = new ThinningAlgrthm(inputFile);
		thinningAlg.zeroFramed(thinningAlg.firstAry);
		thinningAlg.zeroFramed(thinningAlg.secondAry);
		thinningAlg.loadImage(inputFile);
		thinningAlg.prettyPrint(outputFile2);
		thinningAlg.printFirstArr(outputFile);
		//thinningAlg.printsecondtArr(outputFile2);
	}
}
