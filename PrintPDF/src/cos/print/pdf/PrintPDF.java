package cos.print.pdf;

import java.io.*;  
import javax.print.*;  
import javax.print.attribute.*;   
   
public class PrintPDF{  
   
	public static void main(String args[]) {

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("src/resources/firstdoc.pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (fileInputStream == null) {
			return;
		}
		DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc myDoc = new SimpleDoc(fileInputStream, docFlavor, null);
		PrintRequestAttributeSet printRequestAttr = new HashPrintRequestAttributeSet();
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(
				docFlavor, printRequestAttr);

		PrintService myPrinter = null;
		for (int i = 0; i < printServices.length; i++) {
			String printerName = printServices[i].toString();
			System.out.println("Printer found: " + printerName);
			myPrinter = printServices[i];
		}

		if (myPrinter != null) {
			DocPrintJob job = myPrinter.createPrintJob();
			try {
				job.print(myDoc, printRequestAttr);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("printer can't found");
		}
	}
}  