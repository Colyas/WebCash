package com.siriusif.managed.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.GroupDao;
import com.siriusif.service.model.SaleDao;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import static com.siriusif.jsf.utils.JSFHelper.jsf;

//import static com.siriusif.model.helpers.TestHelper.*;

/**
 * @author csurudin
 * 
 */
@ManagedBean(name = "orderBean")
@ViewScoped
public class OrderBean {

	private static Logger LOGGER = Logger.getLogger(OrderBean.class);

	private Order order;

	@ManagedProperty(value = "#{orderProcess}")
	private OrderProcess orderProcess;

	@ManagedProperty(value = "#{groupDao}")
	private GroupDao groupDao;

	@ManagedProperty(value = "#{saleDao}")
	private SaleDao saleDao;

	private List<Group> groups;

	private long orderId;

	private long goodId;

	private long suborderId;

	private long saleId;

	private BigDecimal change;

	private BigDecimal moneyFromClient;

	private boolean card;

	/**
	 * get order id from http request
	 * view opened order
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("starting view");

		HttpServletRequest request = jsf().getRequest();
		String orderIdStr = request.getParameter("order_id");
		orderId = Long.parseLong(orderIdStr);
		LOGGER.info("Recieved order id: " + orderId);
		order = orderProcess.getOrder(orderId);
		suborderId = order.getSuborders().get(0).getId();
	}

	/**
	 * @return
	 *         view groups and goods
	 */
	public List<Group> getGroups() {
		groups = groupDao.list();
		for (Group group : groups) {
			LOGGER.debug(" | " + group.getName());
			LOGGER.debug(" | " + group.getGoods().size());
		}
		LOGGER.debug(" || " + groups.size());
		return groups;
	}

	/**
	 * Perform payment and close order
	 * 
	 * @param evt
	 */
	public void payOrder(ActionEvent evt) {
		// TODO : Ask if customer has a discount
		// TODO : Ask about payment amount
		orderProcess.closeOrder(orderId, order.getTotal(), isCard());
		jsf().redirectTo("/webcash/pages/hall_use.jsf");
	}

	/**
	 * Payment choose of Order: cash or credit card
	 * 
	 * @param event
	 */
	public void choosePaymentOrder(ValueChangeEvent event) {
		String choice = (String) event.getNewValue();
		LOGGER.info("Payment choose of Order: " + choice);
		if ("payCard".equals(choice)) {
			card = true;
			LOGGER.info("Is card: " + card);
		} else {
			LOGGER.info("Is card: " + card);
			LOGGER.info("Payment choose of Order: " + choice);
		}
	}

	/**
	 * Add selected good to order.
	 * 
	 * @param evt
	 */
	public void addGood(ActionEvent evt) {
		LOGGER.debug(evt.toString());
		Good good = (Good) evt.getComponent().getAttributes()
				.get("selectedGood");
		goodId = good.getId();
		LOGGER.info("Good id is: " + goodId);
		LOGGER.info("Suborder id is: " + suborderId);
		for (Suborder suborder : order.getSuborders()) {
			LOGGER.info("Suborders of order: " + suborder.getId());
		}
		order = orderProcess.addGoodsToOrder(goodId, orderId, suborderId);
	}

	public void activeSuborderId(ActionEvent event) {
		LOGGER.debug("On click: " + event.toString());
		Suborder suborder = (Suborder) event.getComponent().getAttributes()
				.get("selectedSuborder");
		suborderId = suborder.getId();
		LOGGER.info("Suborder id: " + suborderId);
	}

	/**
	 * add new suborder to order
	 */
	public void addNewSuborder() {
		order = orderProcess.addSuborder(orderId);
		suborderId = order.getSuborders()
				.get(orderProcess.countOfSuborders(orderId) - 1).getId();
	}

	public void editAmount(ValueChangeEvent event) {
		BigDecimal newAmount = (BigDecimal) event.getNewValue();
		Sale sale = (Sale) event.getComponent().getAttributes()
				.get("selectedSale");
		saleId = sale.getId();
		orderProcess.uptadeSale(saleId, newAmount);
	}

	public void calculateChange(ValueChangeEvent event) {
		moneyFromClient = (BigDecimal) event.getNewValue();
		LOGGER.info("money From Client " + moneyFromClient);
		change = moneyFromClient.subtract(order.getTotal());
		LOGGER.info("change" + change);
	}

	public void deleteSale(ActionEvent event) {
		Sale sale = (Sale) event.getComponent().getAttributes()
				.get("selectedSale");
		saleId = sale.getId();
		orderProcess.deleteSale(saleId);
		order = orderProcess.getOrder(orderId);
	}

	public void printOrder(ActionEvent event) throws IOException,
			TemplateException, DocumentException, PrintException {
		order = orderProcess.getOrder(orderId);

		Configuration configuration = new Configuration();
		configuration.getDefaultEncoding();
		configuration.setDefaultEncoding("UTF-8");

		configuration.setObjectWrapper(new DefaultObjectWrapper());

		Template template = configuration
				.getTemplate("src/main/resources/order.ftl");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("order", order);

		Writer out = new OutputStreamWriter(System.out);
		template.process(data, out);
		out.flush();

		Writer file = new FileWriter(new File(
				"src/main/webapp/pages/order_print.xhtml", "UTF-8"));
		Environment env = template.createProcessingEnvironment(data, file);
		env.setOutputEncoding("UTF-8");
		env.process();
		template.process(data, file);
		file.flush();
		file.close();
	}

	@SuppressWarnings("unused")
	public void orderPdfPrint(ActionEvent event) throws IOException,
			DocumentException, PrintException {
		String inputFile = "src/main/webapp/pages/order_print.xhtml";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "src/main/resources/order_print.pdf";
		OutputStream os = new FileOutputStream(outputFile);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		renderer.layout();
		renderer.createPDF(os);

		os.close();

		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream("src/main/resources/firstdoc.pdf");
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

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public SaleDao getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
	}

	public Order getOrder() {
		return order;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public OrderProcess getOrderProcess() {
		return orderProcess;
	}

	public void setOrderProcess(OrderProcess orderProcess) {
		this.orderProcess = orderProcess;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public long getSuborderId() {
		return suborderId;
	}

	public void setSuborderId(long suborderId) {
		this.suborderId = suborderId;
	}

	public BigDecimal getMoneyFromClient() {
		moneyFromClient = order.getTotal();
		return moneyFromClient;
	}

	public void setMoneyFromClient(BigDecimal moneyFromClient) {
		this.moneyFromClient = moneyFromClient;
	}

	public boolean isCard() {
		return card;
	}

	public void setCard(boolean card) {
		this.card = card;
	}
}
