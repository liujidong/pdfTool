package pdfTool;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.ArrayList;  
  
import com.lowagie.text.Document;  
import com.lowagie.text.DocumentException;  
import com.lowagie.text.pdf.PdfCopy;  
import com.lowagie.text.pdf.PdfImportedPage;  
import com.lowagie.text.pdf.PdfReader;  
public class MyPDFUtil {

	public static void main(String[] args) {
		if(args.length<3) {
			System.out.println("usage:java XXX.pdf fromNum endNum");
			System.exit(0);
		}
	    int fromNum = Integer.parseInt(args[1]);
	    int endNum = Integer.parseInt(args[2]);
	    String newName = args[0].replace(".pdf", fromNum+"_"+endNum+".pdf");
	      //partitionPdfFile("D:\\mag_test\\test_pdf.pdf","Chapter04.pdf", 11,23);
	    partitionPdfFile(args[0],newName,fromNum,endNum);
	    }  
	      
	    /** 
	     * 截取pdfFile的第from页至第end页，组成一个新的文件名 
	     * @param pdfFile 
	     * @param subfileName 
	     * @param from 
	     * @param end 
	     */  
	    public static void partitionPdfFile(String pdfFile,  
	            String newFile, int from, int end) {  
	        Document document = null;  
	        PdfCopy copy = null;          
	        try {  
	            PdfReader reader = new PdfReader(pdfFile);            
	            int n = reader.getNumberOfPages();            
	            if(end==0){  
	                end = n;  
	            }  
	            ArrayList<String> savepaths = new ArrayList<String>();  
	            String staticpath = pdfFile.substring(0, pdfFile.lastIndexOf("\\")+1);  
	            String savepath = staticpath+ newFile;  
	            savepaths.add(savepath);  
	            document = new Document(reader.getPageSize(1));  
	            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));  
	            document.open();  
	            for(int j=from; j<=end; j++) {  
	                document.newPage();   
	                PdfImportedPage page = copy.getImportedPage(reader, j);  
	                copy.addPage(page);  
	            }  
	            document.close();  
	  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch(DocumentException e) {  
	            e.printStackTrace();  
	        }  
	}

}
