package com.siriusif;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.PdfDocument;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class HtmlToPDF {
	public static void main(String[] args) throws IOException,
	DocumentException, ParserConfigurationException, SAXException {
		ITextRenderer renderer = new ITextRenderer();
		renderer.getFontResolver().addFont("C:/WINDOWS/Fonts/TAHOMA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		String inputFile = "src/main/resources/group.html";
		URL url = new File(inputFile).toURI().toURL();
		URLConnection con = url.openConnection();
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		/* If Content-Type doesn't match this pre-conception, choose default and 
		 * hope for the best. */
		String charset = m.matches() ? m.group(1) : "UTF-8";
		Reader r = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder buf = new StringBuilder();
		while (true) {
		  int ch = r.read();
		  if (ch < 0)
		    break;
		  buf.append((char) ch);
		}
		String str = buf.toString();
		System.out.println(str);
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
		renderer.setDocument((org.w3c.dom.Document) doc, null);
		File file = new File("src/main/resources/firstdoc.pdf");
		OutputStream os = new FileOutputStream(file);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
		System.out.println("Yeesss!!!");
	}

}
