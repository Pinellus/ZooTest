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
import com.itextpdf.layout.ColumnDocumentRenderer;
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
public class PdfCreator {
	
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
        
        /*
         * In this case, you are creating a document with pages in the A4 format (595 x 842 user units) and margins of 36 user units. As the bottom margin is only 36 user units, your content risks overlapping with the line drawn at 50 user units from the bottom.

		   You should add one more line where you create the Document:

		   document.setMargins(36, 36, 55, 36);
		   Now you have a bottom margin of 55 user units and the line you draw at 50 user units no longer overlaps.
         */
        
        try (Document document = new Document(pdf, new PageSize(PageSize.A4))) {
            
        	Rectangle[] columns = {
                    new Rectangle(20, 20, 270, 770),
                    new Rectangle(298, 20, 270, 770)
            };
        	document.setRenderer(new ColumnDocumentRenderer(document, columns));
        	document.setMargins(36, 20, 20, 36);
        	
        	pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(document));
        	
        	int count = 1;
        	for (int i = 0; i < num; i++) {
        	    Domanda d = domandeList.get(i);
        		
        	    //Setto l'ordine
        	    d.setOrder(count);
        	    
        	    listDomande.add(d);
        	    
        		float[] columnWidths = {1, 2};
	        	Table table = new Table(columnWidths);
	        	table.setWidthPercent(100);

	        	//Non permette alla tabella di essere splittata su piu pagine
	        	table.setKeepTogether(true);
	        	
	        	PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
	        	
	        	Cell cell = new Cell()
		                .setTextAlignment(TextAlignment.CENTER)
		                .setBackgroundColor(Color.LIGHT_GRAY)
		                .setFontSize(fontSize)
		                .setWidth(20)
		                .add("#"+count);
		            table.addCell(cell);
	        	
	            Cell cell2 = new Cell()
	                .setTextAlignment(TextAlignment.LEFT)
	                .setBackgroundColor(Color.LIGHT_GRAY)
	                .setFontSize(fontSize)
	                .setFont(bold)
	                .add(d.getDomanda());
	            table.addCell(cell2);
	            
	            table.addCell(new Cell().setWidth(20).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("A"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getA()));
	            
	            table.addCell(new Cell().setWidth(20).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("B"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getB()));
	            
	            table.addCell(new Cell().setWidth(20).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER).add("C"));
	            table.addCell(new Cell().setFontSize(fontSize).add(d.getC()));
	            
	            Cell cellb = new Cell(1, 2)
	            		.setBorder(Border.NO_BORDER)
	            		.setHeight(10);
	                table.addCell(cellb);
	            
	            document.add(table);
	            
	            //document.add(new Paragraph(""));
	            count++;
        	}
        	
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
