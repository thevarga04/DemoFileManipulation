package demo.util;

import java.time.Instant;
import java.time.LocalDate;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public enum Placeholder {
  ID("{{id}}") {
    @Override
    public void replace(XWPFRun run, String text) {
      text = text.replace(placeholder, String.valueOf(Instant.now().toEpochMilli()));
      run.setText(text, 0);
    }
  },

  NAME("{{name}}") {
    @Override
    public void replace(XWPFRun run, String text) {
      text = text.replace(placeholder, LocalDate.now().getDayOfWeek().toString());
      run.setText(text, 0);
    }
  },

  ;

  public final String placeholder;


  Placeholder(String placeholder) {
    this.placeholder = placeholder;
  }


  public abstract void replace(XWPFRun run, String text);

  @Override
  public String toString() {
    return placeholder;
  }
}
