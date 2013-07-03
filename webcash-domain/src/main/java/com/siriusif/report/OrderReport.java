package com.siriusif.report;

import java.io.IOException;

import javax.print.PrintException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

import freemarker.template.TemplateException;

public interface OrderReport {

	void printOrder(long orderId, String reportName) throws IOException,
			TemplateException, DocumentException, PrintException,
			ParserConfigurationException, SAXException;

}
