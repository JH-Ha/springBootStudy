package hello.advanced.app.v5;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

  private final LogTrace trace;

  public void save(String itemId) {

    AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
      @Override
      protected Void call() {
        if (itemId.equals("ex")) {
          throw new IllegalStateException("exception !!");
        }
        sleep(1000);
        return null;
      }
    };
    template.execute("OrderRepository.save()");

  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
