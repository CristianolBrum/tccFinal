package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@SessionScoped
public class SaveImageFromUrl implements Serializable {

   
  

        private static final long serialVersionUID = 626953318628565053L;

        // A PDF to download


        /**
         * This method reads PDF from the URL and writes it back as a response.
         *
     * @param nome
         * @throws IOException
         */
        public void downloadPdf(String nome) throws IOException {
            // Get the FacesContext
            String PDF_URL = "C:\\Users\\Usuario\\Documents\\pdf\\"+nome;
            FacesContext facesContext = FacesContext.getCurrentInstance();

            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            // Set response headers
            response.reset();   // Reset the response in the first place
            response.setHeader("Content-Type", "application/pdf");  // Set only the content type
            // Read PDF contents
            try ( // Open response output stream
                    OutputStream responseOutputStream = response.getOutputStream()) {
                // Read PDF contents
                URL url = new URL(PDF_URL);
                // Read PDF contents and write them to the output
                try (InputStream pdfInputStream = url.openStream()) {
                    // Read PDF contents and write them to the output
                    byte[] bytesBuffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
                        responseOutputStream.write(bytesBuffer, 0, bytesRead);
                    }
                    
                    // Make sure that everything is out
                    responseOutputStream.flush();
                    
                    // Close both streams
                }
            }

            // JSF doc: 
            // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated 
            // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
            // as soon as the current phase is completed.
            facesContext.responseComplete();

        }

    }

