package games.lab4.config;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class pdf {
    public void createPDF(String messageToPDF) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.setLeading(14.5F);
        contentStream.newLineAtOffset(25,700);
        contentStream.showText(messageToPDF);
        contentStream.endText();
        contentStream.close();


        document.save(new File("C://newPdf.pdf"));
        document.close();
    }
}
