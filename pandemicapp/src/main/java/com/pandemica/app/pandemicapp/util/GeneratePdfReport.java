package com.pandemica.app.pandemicapp.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pandemica.app.pandemicapp.entity.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

public class GeneratePdfReport {
	
	 private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

	    public static ByteArrayInputStream vaccinationReport(List<Patient> patients) {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {

	            PdfPTable table = new PdfPTable(3);
	            table.setWidthPercentage(80);
	            table.setWidths(new int[]{6, 6, 3});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;


	            hcell = new PdfPCell(new Phrase("Name", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Date", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Venue", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            for (Patient p : patients) {

	                PdfPCell cell;

	                cell = new PdfPCell(new Phrase(p.getName().toString()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(p.getDate().toString()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(String.valueOf(p.getVenue())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	            }
	            
	            Font font = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 24, BaseColor.BLACK);
	            Chunk chunk = new Chunk("COVID-19 VACCINATION CERTIFICATE", font);
	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(new Paragraph(chunk));
	            document.add( Chunk.NEWLINE );
	            document.add(table);
	            document.close();

	        } catch (DocumentException ex) {

	            logger.error("Error occurred: {0}", ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	}

