package com.pinellus.zootest.pdf.style;

 
public class TableBuilder {
     
	/*
	// create table
    public static PdfPTable createTable(Domande domande) throws DocumentException {
 
        // create 6 column table
        PdfPTable table = new PdfPTable(3);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{0.3f, 0.3f, 1.4f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase(domande.getDomanda(),font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(3);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
 
        //-----------------Table Cells Label/Value------------------
 
        // 1st Row
        table.addCell(createCheckBoxCell(1));
        table.addCell(createLabelCell("a"));
        table.addCell(createValueCell(domande.getA()));
 
        // 2nd Row
        table.addCell(createCheckBoxCell(2));
        table.addCell(createLabelCell("b"));
        table.addCell(createValueCell(domande.getB()));
        
        // 3nd Row
        table.addCell(createCheckBoxCell(3));
        table.addCell(createLabelCell("c"));
        table.addCell(createValueCell(domande.getC()));
        
 
        return table;
    }
 
    // create cells
    private static PdfPCell createLabelCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);
 
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
 
        // set style
        Style.labelCellStyle(cell);
        return cell;
    }
 
    // create cells
    private static PdfPCell createValueCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
 
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
 
        // set style
        Style.valueCellStyle(cell);
        return cell;
    }
    
 // create cells
    private static PdfPCell createCheckBoxCell(int i){
        
        
        PdfPCell cell = new PdfPCell();
        cell.setCellEvent(new CheckBox("cb" + i));
        
        // set style
        Style.valueCellStyle(cell);
        return cell;
    }
    
    */
    
}
