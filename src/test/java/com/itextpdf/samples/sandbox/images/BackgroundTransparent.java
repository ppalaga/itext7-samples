/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/27241731/change-background-image-in-itext-to-watermark-or-alter-opacity-c-sharp-asp-net
 */
package com.itextpdf.samples.sandbox.images;

import com.itextpdf.basics.geom.PageSize;
import com.itextpdf.basics.image.Image;
import com.itextpdf.basics.image.ImageFactory;
import com.itextpdf.canvas.PdfCanvas;
import com.itextpdf.canvas.PdfGraphicsState;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.Paragraph;
import com.itextpdf.samples.GenericTest;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileOutputStream;

@Ignore
@Category(SampleTest.class)
public class BackgroundTransparent extends GenericTest {
    public static final String IMAGE = "./src/test/resources/sandbox/images/berlin2013.jpg";
    public static final String DEST = "./target/test/resources/sandbox/images/background_transparent.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BackgroundTransparent().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc, PageSize.A4.rotate());
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        Image image = ImageFactory.getImage(IMAGE);
        canvas.saveState();
        PdfGraphicsState state = new PdfGraphicsState();
        // TODO Implement setFillOpacity(float)
        // state.setFillOpacity(0.6f);
        // TODO Implement setGraphicsState
        // canvas.setGraphicsState(state);
        // image.setTransparency((new int[]{ 0xF0, 0xFF }));
        canvas.addImage(image, 0, 0, PageSize.A4.rotate().getWidth(), false);
        // canvas.restoreState();
        doc.add(new Paragraph("Berlin!"));
        doc.close();
    }
}
