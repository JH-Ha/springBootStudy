package hello.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteClient {

  final private ConcreteLogic concreteLogic;

  public void execute(){
    concreteLogic.operation();
  }
}
