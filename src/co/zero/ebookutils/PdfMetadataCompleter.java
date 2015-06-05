//=======================================================================
// ARCHIVO Main.java
// FECHA CREACIÓN: 20/09/2014
//=======================================================================
package co.zero.ebookutils;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * Utility class to find and extract the ISBN number from a ebook
 * @author Hernán Tenjo
 * @version 1.0
 */
public class PdfMetadataCompleter {
    /**
     * Method for exploratory purpose
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
    	PDDocument document = PDDocument.load("Apress.Beginning.POJOs.From.Novice.to.Professional.Mar.2006.pdf");
    	PDDocumentCatalog catalog = document.getDocumentCatalog();
    	List<?> pdfPages = catalog.getAllPages();
    	PDFTextStripper textStripper = new PDFTextStripper();
    	String textContent;
    	
    	for(int i=1; i<= pdfPages.size(); i++){
    		textStripper.setStartPage(i);
        	textStripper.setEndPage(i);
        	textContent = textStripper.getText(document);
        	
        	if(textContent.contains("ISBN")){
        		for(String line : StringUtils.split(textContent, "\n")){
        			if(StringUtils.contains(line, "ISBN")){
        				System.out.println(line);
        			}
        		}
        		
        		break;
        	}
    	}
    }
}