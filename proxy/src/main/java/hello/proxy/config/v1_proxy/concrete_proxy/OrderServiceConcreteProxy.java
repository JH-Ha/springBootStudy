package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

  private final OrderServiceV2 target;
  private final LogTrace logTrace;

  public OrderServiceConcreteProxy(OrderServiceV2 orderServiceV2, LogTrace logTrace) {
    super(null);
    this.target = orderServiceV2;
    this.logTrace = logTrace;
  }

  @Override
  public void orderItem(String itemId) {
    TraceStatus status;
    try {
      status = logTrace.begin("orderService");
      target.orderItem(itemId);
      logTrace.end(status);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
