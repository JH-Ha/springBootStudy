package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

  final private OrderServiceV1 target;
  final private LogTrace logTrace;

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
