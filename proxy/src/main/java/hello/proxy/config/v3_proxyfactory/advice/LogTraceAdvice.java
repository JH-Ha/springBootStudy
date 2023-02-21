package hello.proxy.config.v3_proxyfactory.advice;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogTraceAdvice implements MethodInterceptor {

  private final LogTrace logTrace;

  public LogTraceAdvice(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    TraceStatus status;
    try {
      Method method = invocation.getMethod();
      String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = logTrace.begin(message);
      // target 호출
      Object result = invocation.proceed();
      logTrace.end(status);
      return result;
    } catch (Exception e) {
      throw e;
    }
  }
}
