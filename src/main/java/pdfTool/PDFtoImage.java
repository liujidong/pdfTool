package pdfTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.spire.pdf.PdfDocument;
import javax.imageio.ImageIO;

public class PDFtoImage {
    public static void main(String[] args) throws IOException {
    	if(args==null || args.length==0) {
    		System.out.print("PDFtoImage xxx.pdf");
    		System.exit(-1);
    	}
        //实例化PdfDocument类的对象，并加载测试文档
        PdfDocument doc = new PdfDocument();
        String filePath = args[0];
        doc.loadFromFile(filePath);

        //遍历文档，并将文档保存为png格式的图片
        BufferedImage image;
        String filePathName = filePath.substring(0,filePath.lastIndexOf("."));
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            image = doc.saveAsImage(i);
            File file = new File( String.format(filePathName+"-img-%d.png", i));
            ImageIO.write(image, "PNG", file);
        }

        doc.close();
    }
}
