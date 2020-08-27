package pdfTool;

import java.io.FileWriter;
import java.io.IOException;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.bookmarks.PdfBookmark;
import com.spire.pdf.bookmarks.PdfBookmarkCollection;
import com.spire.pdf.general.PdfDestination;
import com.spire.pdf.widget.PdfPageCollection;

public class PdfBookmarkTest {
	private static PdfPageCollection pages= null;

	//ReadBookmarks方法
	static void ReadBookmarks(PdfBookmarkCollection bookmarks,StringBuilder builder) throws IOException {
	 
	    if (bookmarks.getCount() > 0)
	    {
	        for(PdfBookmark bookmark : (Iterable<PdfBookmark>) bookmarks)
	        {
	            builder.append(bookmark.getTitle()+"\t");
    			System.out.println(bookmark.getTitle());
    			PdfDestination destination = bookmark.getDestination();
        		if(null != destination) {
        			//builder.append(destination.getPageNumber());
        			System.out.println(destination.getPageNumber());
        			System.out.println(getPageNumber(destination.getPage()));
        		}
        		builder.append("\r\n");
	            ReadBookmarks(bookmark,builder);
	            System.out.println("------------------------------------");
	        }
	    }
	}
	static void showMarks(PdfDocument pdf,String[] args) throws Exception{
		//获取书签的集合
		PdfBookmarkCollection bookmarks = pdf.getBookmarks();
		 
		StringBuilder builder = new StringBuilder();
		 
		//调用ReadBookmarks方法读取书签标题
		ReadBookmarks(bookmarks, builder);
		 
		//写入到文本文件
		FileWriter fw = new FileWriter(args[0]+".txt");
		try {
		    fw.write(builder.toString());
		    fw.flush();
			fw.close();
			System.out.println("write bookmarks to "+args[0]+".txt");
		} catch (IOException e) {
		    e.printStackTrace();

		}		
	}
	private static int getPageNumber(PdfPageBase page) {
		for(int i=0;i<pages.getCount();i++) {
			if(pages.get(i) == page) {
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception{
		if(args.length<2) {
			System.out.println("usage:java XXX.pdf show/update");
			System.exit(0);
		}

		//加载PDF
		PdfDocument pdf = new PdfDocument();
		System.out.println(args[0]);
		pdf.loadFromFile(args[0]);
		pages = pdf.getPages();
		System.out.println(pages.getCount());
		if("show".equalsIgnoreCase(args[1])) { 
			showMarks(pdf, args);
		}
	}
}
