package pdfTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.spire.pdf.PdfDocument;
import javax.imageio.ImageIO;

public class PDFtoImage {
    public static void main(String[] args) throws IOException {
        //实例化PdfDocument类的对象，并加载测试文档
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile("系统测试.pdf");

        //遍历文档，并将文档保存为png格式的图片
        BufferedImage image;
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            image = doc.saveAsImage(i);
            File file = new File( String.format("ToImage-img-%d.png", i));
            ImageIO.write(image, "PNG", file);
        }

        doc.close();
    }
}
