package com.siriusif.processes;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siriusif.sampledata.SampleDataProvider;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OrderPrinter {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPrinter.class);
	
	public static void main(String[] args){
		LOGGER.info("Print begin");
		printFromCollection();
//		printFromDB();
		LOGGER.info("Print done");
	}

	private static void printFromCollection() {
		try{
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(SampleDataProvider.getSampleOrders(),false); //создаем коллекцию Jasper Report Bean Collection
//			JasperReport report = JasperCompileManager.compileReport("src/main/resources/report1.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/report1.jasper", new HashMap<String, Object>(), beanCollectionDataSource); // заполняем датасет отчета данными из коллекции
			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/report1.pdf");
		}
		catch(JRException e){
			e.getStackTrace();
		}
	}

	private static void printFromDB() {
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
