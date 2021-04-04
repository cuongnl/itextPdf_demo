package com.loizenai.pdfreport.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loizenai.pdfreport.model.Customer;
import com.loizenai.pdfreport.util.PDFGenerator;
import com.loizenai.pdfreport.util.PDFGenerator2;


@RestController
@RequestMapping("/api/pdf")
public class CustomerRestAPIsTest {
	

    @GetMapping(value = "/test",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customerReport() throws IOException {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new com.loizenai.pdfreport.model.Customer(1,  "nguyen lien", "cuong");
        Customer customer2 = new com.loizenai.pdfreport.model.Customer(1,  "nguyen lien", "cuong2");
        Customer customer3 = new com.loizenai.pdfreport.model.Customer(1,  "nguyen lien", "cuong3");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        
        com.loizenai.pdfreport.model.Module module = new com.loizenai.pdfreport.model.Module(1, "module_name", null);
        ByteArrayInputStream bis = null;
		try {
			bis = PDFGenerator2.customerPDFReportDEMO(customers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    
}