package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

  @Bean
  public OrderControllerV2 orderController(LogTrace logTrace) {
    OrderControllerV2 controllerV2 = new OrderControllerV2(orderService(logTrace));
    return new OrderControllerConcreteProxy(controllerV2, logTrace);
  }

  @Bean
  public OrderServiceV2 orderService(LogTrace logTrace) {
    OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepository(logTrace));
    return new OrderServiceConcreteProxy(orderServiceV2, logTrace);
  }

  @Bean
  public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
    OrderRepositoryV2 repositoryV2 = new OrderRepositoryV2();
    return new OrderRepositoryConcreteProxy(repositoryV2, logTrace);
  }
}
