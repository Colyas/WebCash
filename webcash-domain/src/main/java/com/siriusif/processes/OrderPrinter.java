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
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class OrderPrinter {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPrinter.class);
	
	public static void main(String[] args){
		LOGGER.info("Print begin");
		printFromCollection();
//		printFromDB();
		LOGGER.info("Print done");
	}

	private static void printFromCollection() {
		int pageWidth = 132;
		try{
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(SampleDataProvider.getSampleOrders(),false); //создаем коллекцию Jasper Report Bean Collection
			JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/PrOrder.jrxml");
			jasperDesign.setName("The dynamically generated report");
		    jasperDesign.setPageWidth(pageWidth);
		    jasperDesign.setColumnWidth(112);
		    jasperDesign.setColumnSpacing(0);
		    jasperDesign.setLeftMargin(10);
		    jasperDesign.setRightMargin(10);
		    jasperDesign.setTopMargin(10);
		    jasperDesign.setBottomMargin(50);
		    jasperDesign.setIgnorePagination(true);
		    
		    
//		    JRDesignField field = new JRDesignField();
//		    field.setName("total");
//		    field.setValueClass(java.lang.Double.class);
//		    jasperDesign.addField(field);
//
//		    field = new JRDesignField();
//		    field.setName("suborders");
//		    field.setValueClass(java.lang.String.class);
//		    jasperDesign.addField(field);
//
//		    field = new JRDesignField();
//		    field.setName("Amount");
//		    field.setValueClass(java.lang.String.class);
//		    jasperDesign.addField(field);

		    //some code

		    //Detail
		    JRDesignBand band = new JRDesignBand();
		    band.setHeight(40);

		    JRDesignStaticText staticText = new JRDesignStaticText();
		    staticText.setX(0);
		    staticText.setY(0);
		    staticText.setWidth(60);
		    staticText.setHeight(20);
		    staticText.setMode(ModeEnum.OPAQUE);
		    staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
		    staticText.setPositionType(PositionTypeEnum.FLOAT);
		    staticText.setText("Ціна: ");
		    staticText.getLineBox().getLeftPen().setLineWidth(1);
		    staticText.getLineBox().getRightPen().setLineWidth(1);
		    staticText.getLineBox().getTopPen().setLineWidth(1);
		    staticText.getLineBox().setLeftPadding(10);
		    band.addElement(staticText);

//		    JRDesignTextField textField = new JRDesignTextField();
//		    textField.setX(60);
//		    textField.setY(0);
//		    textField.setWidth(60);
//		    textField.setHeight(20);
//		    textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
//		    JRDesignExpression expression = new JRDesignExpression();
//		    expression.setText("$F{total}");
//		    textField.getLineBox().getPen().setLineWidth(1);
//		    textField.setExpression(expression);
//		    textField.getLineBox().getTopPen().setLineWidth(1);
//		    textField.getLineBox().getRightPen().setLineWidth(1);
//		    textField.getLineBox().getBottomPen().setLineWidth(1);
//		    textField.getLineBox().setLeftPadding(10);
//		    band.addElement(textField);

		    staticText = new JRDesignStaticText();
		    staticText.setX(0);
		    staticText.setY(20);
		    staticText.setWidth(60);
		    staticText.setHeight(20);
		    staticText.setMode(ModeEnum.OPAQUE);
		    staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
		    staticText.setText("Name: ");
		    staticText.getLineBox().getLeftPen().setLineWidth(1);
		    staticText.getLineBox().getBottomPen().setLineWidth(1);
		    staticText.getLineBox().getRightPen().setLineWidth(1);
		    staticText.getLineBox().setLeftPadding(10);
		    band.addElement(staticText);

//		    textField = new JRDesignTextField();
//		    textField.setStretchWithOverflow(true);
//		    textField.setX(60);
//		    textField.setY(20);
//		    textField.setWidth(200);
//		    textField.setHeight(20);
//		    textField.setPositionType(PositionTypeEnum.FLOAT);
//		    expression = new JRDesignExpression();
//		    expression.setText("$F{suborders}");
//		    textField.setExpression(expression);
//		    textField.getLineBox().getRightPen().setLineWidth(1);
//		    textField.getLineBox().getBottomPen().setLineWidth(1);
//		    textField.getLineBox().setLeftPadding(10);
//		    band.addElement(textField);

		    ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
			JasperReport report = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), beanCollectionDataSource); // заполняем датасет отчета данными из коллекции
//			JasperPrintManager.printReport(jasperPrint, true);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/report2.pdf");
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
