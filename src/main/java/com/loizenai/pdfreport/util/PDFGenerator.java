package com.loizenai.pdfreport.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
import com.loizenai.pdfreport.model.Component;
import com.loizenai.pdfreport.model.Customer;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<Customer> customers) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(3);
        	// Add PDF Table Header ->
			Stream.of("ID", "First Name", "Last Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Customer customer : customers) {
            	PdfPCell idCell = new PdfPCell(new Phrase(customer.getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(customer.getFirstName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(customer.getLastName())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public static ByteArrayInputStream customerPDFReportOptimal(com.loizenai.pdfreport.model.Module module, List<Customer> customers) throws Exception{
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
            
            PdfPTable table = new PdfPTable(3);
        	// Add PDF Table Header ->
			Stream.of("ID", "First Name", "Last Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Customer customer : customers) {
            	PdfPCell idCell = new PdfPCell(new Phrase(customer.getId().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(customer.getFirstName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(customer.getLastName())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Table optimal", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
//			com.itextpdf.text.List list = new com.itextpdf.text.List(); 
        	// Add PDF Table Header ->
//        	list.add(module.getModuleName());
//        	
//        	com.itextpdf.text.List list2 = new com.itextpdf.text.List();
//        	Component component = new Component(2L, "COmponent_2", null);
//        	list2.add(component.getComponentName());
        	
        	com.itextpdf.text.List topLevel = new com.itextpdf.text.List();
            ListItem item1 = new ListItem();
            item1.add("module level");
                com.itextpdf.text.List secondLevel = new com.itextpdf.text.List();
                secondLevel.add("component level 1");
                ListItem subItem2 = new ListItem();
                subItem2.add(new Paragraph("component level 2"));
                    com.itextpdf.text.List thirdLevel = new com.itextpdf.text.List();
                    thirdLevel.add("property 1: value---------------");
                    thirdLevel.add("property 2: value---------------");
                    
                    thirdLevel.add(table);
                   
                subItem2.add(thirdLevel);
                secondLevel.add(subItem2);
            item1.add(secondLevel);
            topLevel.add(item1);
            document.add(topLevel);
            
           
//            document.add(table);
            
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	 public static ByteArrayInputStream createPdf() throws IOException, DocumentException {
	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, out);
	        document.open();
	        
	        // This is how not to do it (but it works anyway):
	        
	        // We create a list:
	        com.itextpdf.text.List list = new com.itextpdf.text.List();                
	        list.add(new ListItem("Item 1"));
	        list.add(new ListItem("Item 2"));
	        list.add(new ListItem("Item 3"));
	        
	        // We wrap this list in a phrase:     
	        Phrase phrase = new Phrase();
	        phrase.add(list);
	        // We add this phrase to a cell
	        PdfPCell phraseCell = new PdfPCell();
	        phraseCell.addElement(phrase);           
	        
	        // We add the cell to a table:
	        PdfPTable phraseTable = new PdfPTable(2);
	        phraseTable.setSpacingBefore(5);
	        phraseTable.addCell("List wrapped in a phrase:");
	        phraseTable.addCell(phraseCell);

	        // We wrap the phrase table in another table:
	        Phrase phraseTableWrapper = new Phrase();
	        phraseTableWrapper.add(phraseTable);
	        
	        // We add these nested tables to the document:
	        document.add(new Paragraph("A list, wrapped in a phrase, wrapped in a cell, wrapped in a table, wrapped in a phrase:"));
	        document.add(phraseTableWrapper);
	        
	        // This is how to do it:
	        
	        // We add the list directly to a cell:
	        PdfPCell cell = new PdfPCell();
	        cell.addElement(list);
	        
	        // We add the cell to the table:
	        PdfPTable table = new PdfPTable(2);
	        table.setSpacingBefore(5);
	        table.addCell("List placed directly into cell");
	        table.addCell(cell);
	        
	        // We add the table to the document:
	        document.add(new Paragraph("A list, wrapped in a cell, wrapped in a table:"));
	        document.add(table);
	        
	        // Avoid adding tables to phrase (but it works anyway):
	        
	        Phrase tableWrapper = new Phrase();
	        tableWrapper.add(table);document.add(new Paragraph("A list, wrapped in a cell, wrapped in a table, wrapped in a phrase:"));
	        document.add(tableWrapper);
	        
	        document.close();
	        return new ByteArrayInputStream(out.toByteArray());
	    }
	 
	 
	 public static ByteArrayInputStream customerPDFReportDEMO(List<Customer> customers) {
			Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        try {
	        	
	        	PdfWriter.getInstance(document, out);
	            document.open();
	        	
				// Add Text to PDF file ->
				Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
				Paragraph para = new Paragraph( "Customer Table", font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);
	        	
	        	PdfPTable table = new PdfPTable(3);
	        
	        	// Add PDF Table Header ->
//				Stream.of("ID", "First Name", "Last Name")
//				    .forEach(headerTitle -> {
//				          PdfPCell header = new PdfPCell();
//				          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//				          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				          header.setHorizontalAlignment(Element.ALIGN_CENTER);
//				          header.setBorderWidth(2);
//				          header.setPhrase(new Phrase(headerTitle, headFont));
//				          table.addCell(header);
//				    });
	            
	            for (Customer customer : customers) {
	            	PdfPCell idCell = new PdfPCell(new Phrase(customer.getId().toString()));
	            	idCell.setPaddingLeft(4);
	            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            	idCell.setBorder(0);
	                table.addCell(idCell);

	                PdfPCell firstNameCell = new PdfPCell(new Phrase(customer.getFirstName()));
	                firstNameCell.setPaddingLeft(4);
	                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(firstNameCell);

	                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(customer.getLastName())));
	                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                lastNameCell.setPaddingRight(4);
	                table.addCell(lastNameCell);
	            }
	            document.add(table);
	            
	            document.close();
	        }catch(DocumentException e) {
	        	logger.error(e.toString());
	        }
	        
			return new ByteArrayInputStream(out.toByteArray());
		}
}