package pdfTool;

import java.awt.geom.Point2D;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfMargins;

public class SplitPDF {

	public static void main(String[] args) {
		if(args.length<3) {
			System.out.println("usage:java XXX.pdf fromNum endNum");
			System.exit(0);
		}
	    int fromNum = Integer.parseInt(args[1]);
	    int endNum = Integer.parseInt(args[2]);
	    if(endNum-fromNum+1>10) {
			System.out.println("warning:The free version is ONLY limited to 10 pages of PDF");
			System.exit(0);
	    }
		//加载需要拆分的PDF文档 
	    PdfDocument doc = new PdfDocument(); 
	    doc.loadFromFile(args[0]); 
	  
	    //新建1个PDF输出文档
	    PdfDocument newpdf = new PdfDocument(); 
	    PdfPageBase page; 
	  
	    //将原PDF文档的第fromNum到endNum页拆分，并保存到newpdf
	    for(int i = fromNum-1;i<endNum;i++) 
	    { 
	      page = newpdf.getPages().add(doc.getPages().get(i).getSize(), new PdfMargins(0)); 
	      doc.getPages().get(i).createTemplate().draw(page, new Point2D.Float(0,0)); 
	    } 
	    newpdf.saveToFile(args[0].replace(".pdf", fromNum+"_"+endNum+".pdf")); 
	    
	  } 

}
