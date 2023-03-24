package demo.service;

import static demo.util.Placeholder.ID;
import static demo.util.Placeholder.NAME;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DemoService {

  private static final String SOURCE_PATH = "src/main/resources/BigSample.docx";


  public Resource getFileAsResource() throws IOException {
    return new ByteArrayResource(manipulateOOXML());
  }


  private byte[] manipulateOOXML() throws IOException {
    try (
        var document = new XWPFDocument(new FileInputStream(SOURCE_PATH));
        var outputStream = new ByteArrayOutputStream()
    ) {
      for (final var p : document.getParagraphs()) {
        for (final var run : p.getRuns()) {
          var text = run.getText(0);
          if (text != null) {
            if (text.contains(ID.placeholder)) {
              ID.replace(run, text);
            }

            if (text.contains(NAME.placeholder)) {
              NAME.replace(run, text);
            }
          }
        }
      }
      document.write(outputStream);
      return outputStream.toByteArray();
    }
  }
}
