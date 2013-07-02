package com.siriusif.process;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.print.PrintException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.siriusif.model.Order;

import freemarker.template.TemplateException;

public interface OrderProcess {
	
	int countOpenedForTableId(long TableId);

	Order newOrder(Long idTable);
	
	List<Order> listForTableId(long tableId);

	Order getOrder(long orderId);

	Order addGoodsToOrder(Long goodId, Long orderId, Long suborderId);

	Order addSuborder(long orderId);

	int countOfSuborders(long orderId);

	void uptadeSale(long saleId, BigDecimal newAmount);

	Order closeOrder(Long orderId, BigDecimal paid, boolean isCard);

	void deleteSale(long saleId);

	void orderFromFreeMarkerToHTML(long orderId) throws IOException,
			TemplateException, DocumentException, PrintException;

	void orderHTMLToPDF() throws DocumentException, IOException,
			ParserConfigurationException, SAXException;

	void printPDFOrder() throws IOException, PrintException;

}
