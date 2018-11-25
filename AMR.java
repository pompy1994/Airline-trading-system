package AMR17S2;

import java.io.*;
import java.io.FileNotFoundException;

//Main function 
public class AMR {

	public static void main(String[] args) throws FileNotFoundException {
	memberProcessor mp = new memberProcessor(args);
	mp.readMem();
	mp.readIns();
	mp.saveResult();
	mp.outReport();
	}

}

