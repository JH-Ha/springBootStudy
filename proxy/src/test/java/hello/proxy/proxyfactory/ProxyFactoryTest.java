package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("If target has a interface, then use JDK dynamic proxy")
  void interfaceProxy(){
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

  @Test
  @DisplayName("If target has no interface, then use CGLIB")
  void concreteProxy(){
    ConcreteService target = new ConcreteService();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.call();

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  @DisplayName("If use ProxyTargetClass option, use CGLIB regardless of existence of interface")
  void proxyTargetClass(){
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }
}
