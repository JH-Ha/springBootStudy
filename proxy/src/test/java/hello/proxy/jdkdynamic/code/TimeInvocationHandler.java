package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

  private final Object target;

  public TimeInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    log.info("execute timeProxy");
    long startTime = System.currentTimeMillis();
    Object result = method.invoke(target, args);
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("finished timeProxy resultTime={}", resultTime);
    return result;
  }
}
