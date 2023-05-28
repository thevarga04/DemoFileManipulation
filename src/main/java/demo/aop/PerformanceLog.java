package demo.aop;


import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class PerformanceLog {

  private final ThreadLocal<AtomicInteger> depth = ThreadLocal.withInitial(AtomicInteger::new);


  @Pointcut("execution(* demo.api..*.*(..))")
  public void restMethods() {
  }

  @Around("restMethods()")
  public Object performanceLog(ProceedingJoinPoint pjp) throws Throwable {
    final long start = System.nanoTime();

    try {
      depth.get().getAndIncrement();
      return pjp.proceed();
    } finally {
      if (log.isInfoEnabled()) {
        final long duration = (System.nanoTime() - start) / 1_000_000L;
        final var className = pjp.getTarget().getClass().getSimpleName();
        final var methodName = pjp.getSignature().getName();

        log.info("%d,%d,%s.%s".formatted(depth.get().get(), duration, className, methodName));
      }
      depth.get().getAndDecrement();
    }
  }

  public void unload() {
    depth.remove();
  }


}
