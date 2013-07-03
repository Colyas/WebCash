package com.siriusif.report.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.siriusif.model.Order;
import com.siriusif.report.OrderReport;
import com.siriusif.service.model.OrderDao;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component(value = "orderReport")
public class OrderReportImpl implements OrderReport {

	private static Logger LOGGER = Logger.getLogger(OrderReportImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Override
	public void orderFromFreeMarkerToHTML(long orderId, String reportName) throws IOException,
			TemplateException, DocumentException, PrintException {
		LOGGER.info("CS   ||    " + orderId);
		Order order = orderDao.find(orderId);

		Configuration configuration = new Configuration();
//		TODO create loader
		Template template = configuration
				.getTemplate("src/main/resources/" + reportName + ".ftl");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("order", order);
//		TODO create loader
		Writer file = new FileWriter(new File(
				"src/main/resources/" + reportName + ".html"));
		template.process(data, file);
		file.flush();
		file.close();
	}

	@Override
	public void orderHTMLToPDF(String reportName) throws DocumentException, IOException,
			ParserConfigurationException, SAXException {
		ITextRenderer renderer = new ITextRenderer();
		renderer.getFontResolver().addFont("C:/WINDOWS/Fonts/Tahoma.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		String inputFile = "src/main/resources/" + reportName + ".html";
		URL url = new File(inputFile).toURI().toURL();
		URLConnection con = url.openConnection();
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		String charset = m.matches() ? m.group(1) : "Windows-1251";
		Reader r = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder buf = new StringBuilder();
		while (true) {
			int ch = r.read();
			if (ch < 0)
				break;
			buf.append((char) ch);
		}
		String strHTML = buf.toString();
		LOGGER.info(strHTML);
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(
				strHTML.getBytes("UTF-8")));
		renderer.setDocument((org.w3c.dom.Document) doc, null);
//		TODO create loader
		File file = new File("src/main/resources/" + reportName + ".pdf");
		OutputStream os = new FileOutputStream(file);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
		LOGGER.info("File write to PDF");
	}

	@Override
	@SuppressWarnings("unused")
	public void printPDFOrder(String reportName) throws IOException, PrintException {
		FileInputStream fileInputStream = null;
//		TODO create loader
		fileInputStream = new FileInputStream(
				"src/main/resources/" + reportName + ".pdf");
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
			LOGGER.info("Printer found: " + printerName);
			myPrinter = printServices[i];
		}

		if (myPrinter != null) {
			DocPrintJob job = myPrinter.createPrintJob();
			job.print(myDoc, printRequestAttr);
		} else {
			LOGGER.info("printer can't found");
		}
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
