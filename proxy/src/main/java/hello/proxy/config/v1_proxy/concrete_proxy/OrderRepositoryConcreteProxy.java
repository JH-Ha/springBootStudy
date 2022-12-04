package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

  private final OrderRepositoryV2 orderRepositoryV2;
  private final LogTrace logTrace;

  @Override
  public void save(String itemId) {
    TraceStatus status;
    try {
      status = logTrace.begin("OrderRepository.request()");
      // target 호출
      orderRepositoryV2.save(itemId);
      logTrace.end(status);
    } catch (Exception e) {
      throw e;
    }
  }
}
