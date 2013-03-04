package com.siriusif.processes;

import java.io.InputStream;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siriusif.model.Suborder;
import com.siriusif.sampledata.SampleDataProvider;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.util.JRDateLocaleConverter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * It's my sandbox for print evaluation.
 * 
 * @author Colya Surudin
 *
 */
public class OrderPrinter {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPrinter.class);
	
	public static void main(String[] args){
		LOGGER.info("Print begin");
		printFromCollection();
//		printFromDB();
		LOGGER.info("Print done");
	}

//	private static void printFromCollection() {
//		int pageWidth = 132;
//		try{
//			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(SampleDataProvider.getSampleOrders(),false); //создаем коллекцию Jasper Report Bean Collection
//			JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/PrOrder.jrxml");
//			jasperDesign.setName("The dynamically generated report");
//		    jasperDesign.setPageWidth(pageWidth);
//		    jasperDesign.setColumnWidth(112);
//		    jasperDesign.setColumnSpacing(0);
//		    jasperDesign.setLeftMargin(10);
//		    jasperDesign.setRightMargin(10);
//		    jasperDesign.setTopMargin(150);
//		    jasperDesign.setBottomMargin(50);
//		    jasperDesign.setIgnorePagination(true);
//		    jasperDesign.setProperty("net.sf.jasperreports.default.pdf.encoding", "Cp1250");
//		    
//		    
////		    JRDesignField field = new JRDesignField();
////		    field.setName("total");
////		    field.setValueClass(java.lang.Double.class);
////		    jasperDesign.addField(field);
////
////		    field = new JRDesignField();
////		    field.setName("suborders");
////		    field.setValueClass(java.util.List.class);
////		    jasperDesign.addField(field);
////
////		    field = new JRDesignField();
////		    field.setName("Amount");
////		    field.setValueClass(java.lang.String.class);
////		    jasperDesign.addField(field);
//
//		    //some code
//
//		    //Detail
//		    JRDesignBand band = new JRDesignBand();
//		    band.setHeight(40);
//
//		    JRDesignStaticText staticText = new JRDesignStaticText();
//		    staticText.setX(0);
//		    staticText.setY(0);
//		    staticText.setWidth(60);
//		    staticText.setHeight(20);
//		    staticText.setMode(ModeEnum.OPAQUE);
//		    staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
//		    staticText.setPositionType(PositionTypeEnum.FLOAT);
//		    staticText.setText("Ціна: ");
//		    staticText.getLineBox().getLeftPen().setLineWidth(1);
//		    staticText.getLineBox().getRightPen().setLineWidth(1);
//		    staticText.getLineBox().getTopPen().setLineWidth(1);
//		    staticText.getLineBox().setLeftPadding(10);
//		    band.addElement(staticText);
//
////		    JRDesignTextField textField = new JRDesignTextField();
////		    textField.setX(60);
////		    textField.setY(0);
////		    textField.setWidth(60);
////		    textField.setHeight(20);
////		    textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
////		    JRDesignExpression expression = new JRDesignExpression();
////		    expression.setText("$F{total}");
////		    textField.getLineBox().getPen().setLineWidth(1);
////		    textField.setExpression(expression);
////		    textField.getLineBox().getTopPen().setLineWidth(1);
////		    textField.getLineBox().getRightPen().setLineWidth(1);
////		    textField.getLineBox().getBottomPen().setLineWidth(1);
////		    textField.getLineBox().setLeftPadding(10);
////		    band.addElement(textField);
//
//		    staticText = new JRDesignStaticText();
//		    staticText.setX(0);
//		    staticText.setY(20);
//		    staticText.setWidth(60);
//		    staticText.setHeight(20);
//		    staticText.setMode(ModeEnum.OPAQUE);
//		    staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
//		    staticText.setText("Name: ");
//		    staticText.getLineBox().getLeftPen().setLineWidth(1);
//		    staticText.getLineBox().getBottomPen().setLineWidth(1);
//		    staticText.getLineBox().getRightPen().setLineWidth(1);
//		    staticText.getLineBox().setLeftPadding(10);
//		    band.addElement(staticText);
//
////		    textField = new JRDesignTextField();
////		    textField.setStretchWithOverflow(true);
////		    textField.setX(60);
////		    textField.setY(20);
////		    textField.setWidth(60);
////		    textField.setHeight(20);
////		    textField.setPositionType(PositionTypeEnum.FLOAT);
////		    expression = new JRDesignExpression();
////		    expression.setText("$F{suborders}.size()");
////		    textField.setExpression(expression);
////		    textField.getLineBox().getRightPen().setLineWidth(1);
////		    textField.getLineBox().getBottomPen().setLineWidth(1);
////		    textField.getLineBox().setLeftPadding(10);
////		    band.addElement(textField);
//
//		    ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
//			JasperReport report = JasperCompileManager.compileReport(jasperDesign);
//			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), beanCollectionDataSource); // заполняем датасет отчета данными из коллекции
//			JasperPrintManager.printReport(jasperPrint, true);
////			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/report2.pdf");
//		}
//		catch(JRException e){
//			LOGGER.error("Error while processing jasper report", e);
//		}
//	}

	private static void printFromCollection() {
		int pageWidth = 132;
		try{
//			Master report
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(SampleDataProvider.getSampleOrders(),false); //создаем коллекцию Jasper Report Bean Collection
//			subreport
			JRDataSource subordersDataSrc = new JRBeanCollectionDataSource(SampleDataProvider.getSampleOrder().getSuborders(),false);
			
			JasperDesign jasperDesignOrder = JRXmlLoader.load("src/main/resources/OrderPrinter.jrxml");
			jasperDesignOrder.setName("order");
			jasperDesignOrder.setPageWidth(pageWidth);
			jasperDesignOrder.setColumnWidth(112);
			jasperDesignOrder.setColumnSpacing(0);
			jasperDesignOrder.setLeftMargin(10);
			jasperDesignOrder.setRightMargin(10);
			jasperDesignOrder.setTopMargin(20);
			jasperDesignOrder.setBottomMargin(50);
			jasperDesignOrder.setIgnorePagination(true);
			jasperDesignOrder.setProperty("net.sf.jasperreports.default.pdf.encoding", "Cp1250");
			
			JRDesignBand header = new JRDesignBand();
		    header.setHeight(60);
		    
		    JRDesignStaticText staticText = new JRDesignStaticText();
		    staticText.setX(0);
		    staticText.setY(0);
		    staticText.setWidth(pageWidth);
		    staticText.setHeight(20);
		    staticText.setFontSize(12);
		    staticText.setBold(true);
		    staticText.setMode(ModeEnum.OPAQUE);
		    staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		    staticText.setPositionType(PositionTypeEnum.FLOAT);
		    staticText.setText("Назва кафе");
		    header.addElement(staticText);
		    
		    staticText = new JRDesignStaticText();
		    staticText.setX(0);
		    staticText.setY(20);
		    staticText.setWidth(pageWidth);
		    staticText.setHeight(15);
		    staticText.setMode(ModeEnum.OPAQUE);
		    staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		    staticText.setPositionType(PositionTypeEnum.FLOAT);
		    staticText.setText("Адреса кафе");
		    header.addElement(staticText);
		    
		    staticText = new JRDesignStaticText();
		    staticText.setX(0);
		    staticText.setY(35);
		    staticText.setWidth(pageWidth);
		    staticText.setHeight(15);
		    staticText.setMode(ModeEnum.OPAQUE);
		    staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		    staticText.setPositionType(PositionTypeEnum.FLOAT);
		    staticText.setText("РАХУНОК №");
		    header.addElement(staticText);
		    
			JasperDesign jasperDesignSuborder = JRXmlLoader.load("src/main/resources/OrderPrinter_suborder.jrxml");
		    jasperDesignSuborder.setName("suborder");
			jasperDesignSuborder.setPageWidth(pageWidth);
			jasperDesignSuborder.setColumnWidth(112);
			jasperDesignSuborder.setColumnSpacing(0);
			jasperDesignSuborder.setLeftMargin(10);
			jasperDesignSuborder.setRightMargin(10);
			jasperDesignSuborder.setTopMargin(20);
			jasperDesignSuborder.setBottomMargin(50);
			JRDesignSubreport designSubreport = new  JRDesignSubreport(jasperDesignSuborder);
			jasperDesignSuborder.setProperty("net.sf.jasperreports.default.pdf.encoding", "Cp1250");
			
			JRDesignBand footer = new JRDesignBand();
			footer.setHeight(60);
			
			JRDesignStaticText staticTextSub = new JRDesignStaticText();
		    staticTextSub.setX(0);
		    staticTextSub.setY(0);
		    staticTextSub.setWidth(pageWidth);
		    staticTextSub.setHeight(20);
		    staticTextSub.setFontSize(12);
		    staticTextSub.setBold(true);
		    staticTextSub.setMode(ModeEnum.OPAQUE);
		    staticTextSub.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		    staticTextSub.setPositionType(PositionTypeEnum.FLOAT);
		    staticTextSub.setText("Назва кафе");
		    footer.addElement(staticTextSub);
			
		    ((JRDesignSection) jasperDesignOrder.getDetailSection()).addBand(header);
			JasperReport report = JasperCompileManager.compileReport(jasperDesignOrder);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), beanCollectionDataSource); // заполняем датасет отчета данными из коллекции
			JasperPrintManager.printReport(jasperPrint, true);
//			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/report2.pdf");
		}
		catch(JRException e){
			LOGGER.error("Error while processing jasper report", e);
		}
	}
	
	
	private static void printFromDB() {
		try{
			JasperReport report = JasperCompileManager.compileReport("src/main/resources/order.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/order.pdf");
		}
		catch(JRException e){
			LOGGER.error("Error while processing jasper report", e);
		}
	}

}
