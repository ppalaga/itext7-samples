/**
 * This example was written by Bruno Lowagie.
 */
package com.itextpdf.samples.sandbox.events;

import com.itextpdf.core.events.Event;
import com.itextpdf.core.events.IEventHandler;
import com.itextpdf.core.events.PdfDocumentEvent;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfName;
import com.itextpdf.core.pdf.PdfNumber;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.AreaBreak;
import com.itextpdf.model.element.Paragraph;
import com.itextpdf.samples.GenericTest;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class PageOrientations extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/events/page_orientations.pdf";

    /* Constants form itext5 */
    public static final PdfNumber INVERTEDPORTRAIT = new PdfNumber(180);
    public static final PdfNumber LANDSCAPE = new PdfNumber(90);
    public static final PdfNumber PORTRAIT = new PdfNumber(0);
    public static final PdfNumber SEASCAPE = new PdfNumber(270);

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PageOrientations().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(DEST)));
        PageOrientationsEventHandler eventHandler = new PageOrientationsEventHandler();
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, eventHandler);
        Document doc = new Document(pdfDoc);
        doc.add(new Paragraph("A simple page in portrait orientation"));
        eventHandler.setOrientation(LANDSCAPE);
        doc.add(new AreaBreak());
        doc.add(new Paragraph("A simple page in landscape orientation"));
        eventHandler.setOrientation(INVERTEDPORTRAIT);
        doc.add(new AreaBreak());
        doc.add(new Paragraph("A simple page in inverted portrait orientation"));
        eventHandler.setOrientation(SEASCAPE);
        doc.add(new AreaBreak());
        doc.add(new Paragraph("A simple page in seascape orientation"));
        pdfDoc.close();
    }


    public static class PageOrientationsEventHandler implements IEventHandler {
        protected PdfNumber orientation = PORTRAIT;

        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            docEvent.getPage().put(PdfName.Rotate, orientation);
        }
    }
}