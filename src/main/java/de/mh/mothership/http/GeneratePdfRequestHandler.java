package de.mh.mothership.http;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import de.mh.mothership.pdf.Renderer;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.io.ByteStreams.copy;

class GeneratePdfRequestHandler extends AbstractHandler {

    private static final String DOCUMENT = "document";
    private final Renderer renderer;
    private final String welcomePage;

    @Inject
    GeneratePdfRequestHandler(Renderer renderer) {
        this.renderer = renderer;
        try (InputStream demoFormInputStream = getClass().getResourceAsStream("/demo-form.html")) {
            welcomePage = CharStreams.toString(new InputStreamReader(demoFormInputStream, Charsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            try(Writer w = httpServletResponse.getWriter()) {
                w.write(welcomePage);
            } catch (Exception e) {
                propagate(e);
            }
        } else {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String document = httpServletRequest.getParameter(DOCUMENT);
                renderer.generate(document, baos);

                ServletOutputStream outputStream = httpServletResponse.getOutputStream();
                copy(new ByteArrayInputStream(baos.toByteArray()), outputStream);
                outputStream.close();

            } catch (IOException e) {
                propagate(e);
            }
        }
    }
}
