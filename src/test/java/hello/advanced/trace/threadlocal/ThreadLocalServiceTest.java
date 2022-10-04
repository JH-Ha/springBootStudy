package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

  private ThreadLocalService fieldService = new ThreadLocalService();

  @Test
  void field() throws InterruptedException {
    log.info("main start");
    Runnable userA = () ->{
      fieldService.logic("userA");
    };
    Runnable userB = () ->{
      fieldService.logic("userB");
    };

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(100);;
    threadB.start();
    threadA.join();
    threadB.join();
  }
  private void sleep(int mills){
    try{
      Thread.sleep(mills);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
