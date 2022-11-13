package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

  @Override
  public void save(String itemId) {
    if(itemId.equals("ex")){
      throw new IllegalStateException("exception");
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}