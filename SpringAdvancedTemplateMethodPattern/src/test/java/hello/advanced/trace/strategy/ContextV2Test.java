package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  @Test
  void strategyV1() {
    ContextV2 context1 = new ContextV2();
    context1.execute(new StrategyLogic1());

    ContextV2 context2 = new ContextV2();
    context2.execute(new StrategyLogic2());
  }

  @Test
  void strategyV2() {
    ContextV2 context = new ContextV2();
    context.execute(() -> log.info("business logic 1"));
  }
}
