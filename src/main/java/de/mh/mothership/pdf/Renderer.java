package de.mh.mothership.pdf;

import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import static com.google.common.base.Throwables.propagate;

public class Renderer {

    private static final Logger LOG = LoggerFactory.getLogger(Renderer.class);

    public void generate(String htmlDocument, OutputStream output) {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlDocument);
        renderer.layout();
        try {
            renderer.createPDF(output);
        } catch (DocumentException | IOException e) {
            propagate(e);
        }
    }
}
