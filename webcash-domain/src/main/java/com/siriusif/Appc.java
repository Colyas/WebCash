package com.siriusif;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class Appc {

    /** The resulting PDF file. */
    public static final String RESULT
        = "src/main/resources/firstdoc.pdf";
    /** The path to the font. */
    public static final String FONT
        = "C:/WINDOWS/Fonts/Arialbd.ttf";
    /** Movie information. */
    public static final String[][] MOVIES = {
        {
            "Cp1252",
            "A Very long Engagement (France)",
            "directed by Jean-Pierre Jeunet",
            "Un long dimanche de fian\u00e7ailles"
        },
        {
            "Cp1250",
            "No Man's Land (Bosnia-Herzegovina)",
            "Directed by Danis Tanovic",
            "Nikogar\u0161nja zemlja"
        },
        {
            "Cp1251",
            "You I Love (Russia)",
            "directed by Olga Stolpovskaja and Dmitry Troitsky",
            "\u042f \u043b\u044e\u0431\u043b\u044e \u0442\u0435\u0431\u044f"
        },
        {
            "Cp1253",
            "Brides (Greece)",
            "directed by Pantelis Voulgaris",
            "\u039d\u03cd\u03c6\u03b5\u03c2"
        }
    };
    
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException 
     * @throws IOException 
     * @throws    DocumentException 
     * @throws    IOException
     * @throws com.lowagie.text.DocumentException 
     */
    public void createPdf(String filename) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        // step 1
//    	renderer.createPDF(os);
    	String inputFile = "src/main/resources/group.xhtml";
    	String url = new File(inputFile).toURI().toURL().toString();
    	ITextRenderer renderer = new ITextRenderer();
    	renderer.setDocument(url);
    	renderer.layout();
    	Document document = new Document();
    	String newFile = renderer.toString();
        // step 2
//        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        BaseFont bf;
            bf = BaseFont.createFont(FONT, newFile, BaseFont.EMBEDDED);
            document.add(new Paragraph(newFile, new Font(bf, 12)));
            document.addCreator(newFile);
            document.add(Chunk.NEWLINE);
        
//		String outputFile = "src/main/resources/order_print.pdf";
//		OutputStream os = new FileOutputStream(outputFile);


//		os.close();
        // step 5
        document.close();
        renderer.setDocument((org.w3c.dom.Document) document, url);
        System.out.println("CS:   " + renderer.toString());
        System.out.println("CS:   " + url);
    }
    
    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException, DocumentException {
        try {
			new Appc().createPdf(RESULT);
		} catch (com.lowagie.text.DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}