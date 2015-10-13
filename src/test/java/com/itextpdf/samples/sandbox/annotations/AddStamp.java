/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/29229629/how-to-add-a-printable-or-non-printable-bitmap-stamp-to-a-pdf
 */
package com.itextpdf.samples.sandbox.annotations;

import com.itextpdf.basics.geom.Rectangle;
import com.itextpdf.basics.image.ImageFactory;
import com.itextpdf.canvas.PdfCanvas;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfName;
import com.itextpdf.core.pdf.PdfReader;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.pdf.annot.PdfStampAnnotation;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.element.Image;
import com.itextpdf.samples.GenericTest;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Ignore
@Category(SampleTest.class)
public class AddStamp extends GenericTest {
    public static final String SRC = "./src/test/resources/sandbox/annotations/hello.pdf";
    public static final String IMG = "./src/test/resources/sandbox/annotations/itext.png";
    public static final String DEST = "./target/test/resources/sandbox/annotations/add_stamp.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddStamp().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(new FileInputStream(SRC)),
                new PdfWriter(new FileOutputStream(DEST)));

        Image img = new Image(ImageFactory.getImage(IMG));
        float w = img.getImageScaledWidth();
        float h = img.getImageScaledHeight();
        Rectangle location = new Rectangle(36, 770 - h, w, h);
        PdfStampAnnotation stamp = new PdfStampAnnotation(pdfDoc, location)
                .setStampName(new PdfName("ITEXT"));
        img.setFixedPosition(0, 0);
        PdfCanvas cb = new PdfCanvas(pdfDoc.getFirstPage());
        // TODO There is no PdfCanvas#createAppearance()
        // PdfAnnotationAppearance app = new PdfAnnotationAppearance();
        // app.addImage(img);
        // stamp.setAppearance(PdfName.N, app);
        // TODO There is no PdfAnnotation.FLAGS_PRINT flag
        // stamp.setFlags(PdfAnnotation.FLAGS_PRINT);

        pdfDoc.getFirstPage().addAnnotation(stamp);
        pdfDoc.close();
    }

}