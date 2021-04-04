package com.loizenai.pdfreport.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.loizenai.pdfreport.model.BaseObject;
import com.loizenai.pdfreport.model.Component;
import com.loizenai.pdfreport.model.Customer;
import com.loizenai.pdfreport.model.MapModuleComponent;
import com.loizenai.pdfreport.model.Module;
import com.loizenai.pdfreport.model.PolicyUse;

public class PDFGenerator2 {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator2.class);
	 
	public static void main(String[] args) {
	
//		Module module2 = new Module(88, "module1", null);
//		get(module2, "moduleName");
		PDFGenerator2.customerPDFReportDEMO(null);
	}
	
	public static<T> void get(BaseObject obj, String fieldPath) {
        
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true); 
           
			try {
				Object value = field.get(obj);
				 System.out.println(field.getName() + "=" + value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
          
        }
    }
	 public static ByteArrayInputStream customerPDFReportDEMO(List<Customer> customers) {
			Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        List<PolicyUse> policyUses = new ArrayList<>();
	        policyUses.add(new PolicyUse(11l, "policy_use_1", "123"));
	        policyUses.add(new PolicyUse(12l, "policy_use_2", "333"));
	        policyUses.add(new PolicyUse(13l, "policy_use_3", Strings.EMPTY));
	        policyUses.add(new PolicyUse(14l, "policy_use_4", Strings.EMPTY));
	        policyUses.add(new PolicyUse(15l, "policy_use_5", "[{}]"));
	        
	        Component component = new Component(3l, "component", policyUses);
	        MapModuleComponent mapModuleComponent = new MapModuleComponent(2l, "mapModule_component", component);
	        Module module = new Module(1, "module1", mapModuleComponent);
	        
	        List<PolicyUse> policyUses2 = new ArrayList<>();
	        policyUses2.add(new PolicyUse(55l, "policy_use_55", Strings.EMPTY));
	        policyUses2.add(new PolicyUse(56l, "policy_use_56", Strings.EMPTY));
	        policyUses2.add(new PolicyUse(57l, "policy_use_57", Strings.EMPTY));
	        policyUses2.add(new PolicyUse(58l, "policy_use_58", Strings.EMPTY));
	        policyUses2.add(new PolicyUse(59l, "policy_use_59", Strings.EMPTY));
	        
	        Component component2 = new Component(60l, "component", policyUses2);
	        MapModuleComponent mapModuleComponent2 = new MapModuleComponent(61l, "mapModule_component", component2);
	        Module module2 = new Module(88, "module1", mapModuleComponent2);
	        List<Module> modules = new ArrayList<>();
	        modules.add(module);
	        modules.add(module2);
	        	        
	        try {
	        	
	        	PdfWriter.getInstance(document, out);
	            document.open();
	        	
				// Add Text to PDF file ->
				Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
				Paragraph para = new Paragraph( "Customer Table", font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);
	        	
	        	PdfPTable table = new PdfPTable(4);
	        	handleViewDataPdf(table, modules);
	        
	            document.add(table);
	            
	            document.close();
	        }catch(DocumentException e) {
	        	logger.error(e.toString());
	        }
	        
			return new ByteArrayInputStream(out.toByteArray());
		}
	 
	 private static void handleViewDataPdf(PdfPTable table, List<Module> modules ) {
		 //loop module
		 int startPoint = 0;
		 int max = 4;
		 
		 //module
		 for(Module module: modules) {
			 startPoint = 0;
			 PdfPCell value = new PdfPCell(new Phrase(getValueObjectByField("moduleName", module)));
			 table = loopCellTable(startPoint, table, value, max);
			
			 startPoint++;
			 
			 //component
			 Component component = module.getMapModuleComponent().getComponent();
			 PdfPCell comCell = new PdfPCell(new Phrase(getValueObjectByField("componentName", component)));
			 table = loopCellTable(startPoint, table, comCell, max);
			 startPoint++;
			 
			 //property
			 for( PolicyUse policyUse: component.getPolicyUse()) {
				 PdfPCell comPolicyUse = new PdfPCell(new Phrase(getValueObjectByField("policyName", policyUse)));
				 PdfPCell valueCLob = new PdfPCell(new Phrase(getValueObjectByField("valueClob", policyUse)));
				 table = loopCellTable(startPoint, startPoint+1, table, comPolicyUse, valueCLob, max);
			 }
		 }
	 }
        
		private static String getValueObjectByField(String fieldParam, BaseObject baseObject) {

			
			
			try {
				
				for (Field field : baseObject.getClass().getDeclaredFields()) {
		            field.setAccessible(true); 
		           
					try {
						if(field.getName().equals(fieldParam)) {
							Object value = field.get(baseObject);
							return value.toString();
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		          
		        }
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			return Strings.EMPTY;
		}
		
		private static void initCss(PdfPTable table, PdfPCell cell) {
			cell.setPaddingLeft(4);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			cell.setBorderWidthTop(1f);
			 table.addCell(cell);
		}
		
		private static PdfPTable loopCellTable(int cellNotEmpty, PdfPTable table, PdfPCell cell, int maxColumn) {
			PdfPCell cellEmpty = new PdfPCell(new Phrase(Strings.EMPTY));
			for(int i = 0; i < maxColumn; i++) {
				if(i == cellNotEmpty) {
					initCss(table, cell);
					continue;
				}
				initCss(table, cellEmpty);
			}
			return table;
		}
		
		private static PdfPTable loopCellTable(int cellNotEmpty, int cellValueClob, PdfPTable table, PdfPCell cell, PdfPCell valueClob, int maxColumn) {
			PdfPCell cellEmpty = new PdfPCell(new Phrase(Strings.EMPTY));
			for(int i = 0; i < maxColumn; i++) {
				if(i == cellNotEmpty) {
					initCss(table, cell);
					continue;
				}
				if(i == cellValueClob) {
//					if("[{}]".equals(valueClob)) {
						PdfPCell cellTable = new PdfPCell(cellTabel());
						initCss(table, cellTable);
//					}else {
//						initCss(table, valueClob);
//					}
					
					continue;
				}
				initCss(table, cellEmpty);
			}
			
			return table;
		}
		
		private static int pointTable() {
			return 3;
		}
		
		private static PdfPTable cellTabel() {
			PdfPTable tableChild = new PdfPTable(3);
        	// Add PDF Table Header ->
			Stream.of("ID", "First Name", "Last Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          tableChild.addCell(header);
			    });
            
            
            	PdfPCell idCell = new PdfPCell(new Phrase("ITEM 1"));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	tableChild.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase("ITEM 2"));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableChild.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase("ITEM 3"));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                tableChild.addCell(lastNameCell);
                return tableChild;
            
		}
}