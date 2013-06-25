package com.siriusif;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.lowagie.text.DocumentException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Good;
import com.siriusif.model.Group;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import static com.siriusif.model.helpers.TestHelper.money;

/**
 * This console app present here only for quick and dirty testing
 *
 */
public class App {
	private static Logger LOGGER = Logger.getLogger(App.class);
	public static Group[] groups;
	
	public static void main2(String[] args) throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException, IOException, TemplateException{
		LOGGER.info("Import started.");
		groups = Helper.fromJson("/grouplist.json",Group[].class);
		for(Group group : groups){
			group.fixReferencesToParentGroup();
		}
		LOGGER.info("Import allready.");
		for (int i=0; i< groups.length; i++){
			Group currentGroup = groups[i];
			LOGGER.info("Група "+i+" : " + currentGroup.getName());
			printGoodsOfGroup(currentGroup);
			printSubgroupsAndTheirGoods(currentGroup);
		}
		
		Configuration configuration = new Configuration();
		

		Template template = configuration.getTemplate("src/main/resources/group.ftl");
//		Template template = configuration.getTemplate("D:/Java/group.ftl");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("groups", groups);
		
		Writer out = new OutputStreamWriter(System.out);
        template.process(data, out);
        out.flush();
        
        Writer file = new FileWriter (new File("src/main/resources/group.xhtml"));
        template.process(data, file);
        file.flush();
        file.close();
		
//		Group currentGroup = groups[1];
//		LOGGER.info("Група : " + currentGroup.getgName());
//		printSubgroupsAndTheirGoods(currentGroup);
	}

	private static void printSubgroupsAndTheirGoods(Group currentGroup) {
		for(int s = 0; s < currentGroup.getSubGroups().size(); s++){
			Group currentSubGroup = currentGroup.getSubGroups().get(s);
			LOGGER.info(currentSubGroup.getParentGroup().getName() + ">>" + currentSubGroup.getName());
			printSubgroupsAndTheirGoods(currentSubGroup);
			printGoodsOfGroup(currentSubGroup);
		}
	}

	private static void printGoodsOfGroup(Group currentGroup) {
		for(int g = 0; g < currentGroup.getGoods().size(); g++){
			Good currentGood =currentGroup.getGoods().get(g);
			LOGGER.info(currentGood.getParentGroup().getName()+":" + currentGood.getName());
		}
	}
	
	public static void main1(String[] args){
		BigDecimal val1 = money(15.25);
		BigDecimal val2 = money(10.00);
		int val3 = val1.compareTo(val1);
		System.out.println(val3);
	}
	
	public static void main(String[] args) 
            throws IOException, DocumentException {
        String inputFile = "src/main/resources/group.xhtml";
        String url = new File(inputFile).toURI().toURL().toString();
        String outputFile = "src/main/resources/firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        
        os.close();
    }

}
