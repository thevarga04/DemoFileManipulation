package demo.api;

import static demo.util.HttpUtils.generateHttpHeader;

import demo.service.DemoService;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRest {

  private final DemoService demoService;

  public DemoRest(DemoService demoService) {
    this.demoService = demoService;
  }


  @GetMapping("/demo")
  ResponseEntity<?> download() throws IOException {
    var resource = demoService.getFileAsResource();

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .headers(generateHttpHeader())
        .body(resource);
  }

}
