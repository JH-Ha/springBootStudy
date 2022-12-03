package hello.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TimeProxy extends ConcreteLogic{

  final private ConcreteLogic concreteLogic;

  @Override
  public String operation() {
    log.info("time proxy 실행");
    long startTime = System.currentTimeMillis();
    String result = concreteLogic.operation();
    long endTime = System.currentTimeMillis();
    log.info("execution time : {}", (endTime - startTime));

    return result;
  }
}
