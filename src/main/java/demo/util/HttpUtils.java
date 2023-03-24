package demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;

public class HttpUtils {

  private HttpUtils() {
  }


  public static HttpHeaders generateHttpHeader() {
    var header = new HttpHeaders();
    var contentDisposition = ContentDisposition
        .inline()
        .filename(generateFilename())
        .build();

    header.setContentDisposition(contentDisposition);
    return header;
  }

  private static String generateFilename() {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    var datetime = LocalDateTime.now().format(formatter);
    return "Sample_%s.docx".formatted(datetime);
  }


}
