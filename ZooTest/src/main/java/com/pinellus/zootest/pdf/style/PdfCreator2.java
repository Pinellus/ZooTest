package com.pinellus.zootest.pdf.style;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.test.annotations.WrapToTest;
import com.pinellus.zootest.domain.Domanda;

@WrapToTest
public class PdfCreator2 {
	
	public static void main(String[] args) throws IOException {
        //File file = new File("Test.pdf");
        //file.getParentFile().mkdirs();
    	//new createPdf().createPdf("Test.pdf");
    	
    }
 
    @SuppressWarnings("deprecation")
	public List<Domanda> Table (String dest, List<Domanda> domandeList, int num) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        List<Domanda> listDomande = new ArrayList<Domanda>();
        
        int fontSize = 8;
        
        try (Document document = new Document(pdf, new PageSize(PageSize.A4))) {
            
        	pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(document));
        	
        	//TABLE DEFINITION
        	float[] columnWidths = {10, 35, 10, 10, 35};
        	Table table = new Table(columnWidths);
        	table.setWidthPercent(100);
        	//Non permette alla tabella di essere splittata su piu pagine
        	table.setKeepTogether(true);
        	
        	int count = 1;
        	for (int i = 0; i < num; i += 2) {
        	    
        		Domanda d = domandeList.get(i);
        		
        	    Domanda d1 = domandeList.get(i+1);
        	    
        	    //Setto l'ordine 1
        	    d.setOrder(count);
        	    listDomande.add(d);
        	    
        	    //Setto l'ordine 2
        	    d1.setOrder(count+1);
        	    listDomande.add(d1);
        	    
	        	
	        	PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
	        	
	        	Cell cell = new Cell()
		                .setTextAlignment(TextAlignment.CENTER)
		                .setBackgroundColor(Color.LIGHT_GRAY)
		                .setFontSize(fontSize)
		                .add("#"+d.getOrder());
		            table.addCell(cell);
	        	
	            Cell cell2 = new Cell()
		                .setTextAlignment(TextAlignment.LEFT)
		                .setBackgroundColor(Color.LIGHT_GRAY)
		                .setFontSize(fontSize)
		                .setFont(bold)
		                .add(d.getDomanda());
		            table.addCell(cell2);
		            
		            
	            Cell cell3 = new Cell()
	                .setBorder(Border.NO_BORDER);
	            table.addCell(cell3);
	            
	            
	            
	            Cell cell4 = new Cell()
		                .setTextAlignment(TextAlignment.CENTER)
		                .setBackgroundColor(Color.LIGHT_GRAY)
		                .setFontSize(fontSize)
		                .add("#"+d1.getOrder());
		            table.addCell(cell4);
	        	
	            Cell cell5 = new Cell()
	                .setTextAlignment(TextAlignment.LEFT)
	                .setBackgroundColor(Color.LIGHT_GRAY)
	                .setFontSize(fontSize)
	                .setFont(bold)
	                .add(d1.getDomanda());
	            table.addCell(cell5);
	            
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("A"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getA()));
	            table.addCell(new Cell().setBorder(Border.NO_BORDER));
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("A"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d1.getA()));
	            
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("B"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getB()));
	            table.addCell(new Cell().setBorder(Border.NO_BORDER));
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("B"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d1.getB()));
	            
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("C"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getC()));
	            table.addCell(new Cell().setBorder(Border.NO_BORDER));
	            table.addCell(new Cell().setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("C"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d1.getC()));
	            
	            Cell cellb = new Cell(1, 5)
	            		.setBorder(Border.NO_BORDER);
	                table.addCell(cellb);
	            
	            //document.add(table);
	            
	            //document.add(new Paragraph(""));
	            count+=2;
        	}
        	
        	document.add(table);
        	pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new TextFooterEventHandler(document));
        }
        
        return listDomande;
    }
    
    
    protected class TextFooterEventHandler implements IEventHandler {
        protected Document doc;
 
        public TextFooterEventHandler(Document doc) {
            this.doc = doc;
        }
 
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
            Rectangle pageSize = docEvent.getPage().getPageSize();
            canvas.beginText();
            try {
                canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE), 8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            canvas.moveText((pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2 + doc.getLeftMargin(), pageSize.getTop() - doc.getTopMargin() + 10)
                    .showText("Test di Zoologia")
                    .moveText(0, (pageSize.getBottom() + doc.getBottomMargin()) - (pageSize.getTop() + doc.getTopMargin()) - 30)
                    .showText("this is a footer")
                    .endText()
                    .release();
           
        }
    }

}
