package com.siriusif.model;

import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class OrderPrinter {
	public static void main(String[] args){
		try{
			JasperReport report = JasperCompileManager.compileReport("src/main/resources/order.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/order.pdf");
		}
		catch(JRException e){
			e.getStackTrace();
		}
	}

}
