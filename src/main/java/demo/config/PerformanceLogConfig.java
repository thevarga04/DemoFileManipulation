package demo.config;

import demo.aop.PerformanceLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PerformanceLogConfig {

  @Bean
  public PerformanceLog performanceLog() {
    return new PerformanceLog();
  }


}
