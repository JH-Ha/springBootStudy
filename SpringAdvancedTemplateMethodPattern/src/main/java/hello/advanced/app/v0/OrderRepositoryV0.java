package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
  public void save(String itemId){
    if(itemId.equals("ex")){
      throw new IllegalStateException("exception !!");
    }
    sleep(1000);
  }
  private void sleep(int millis){
    try{
      Thread.sleep(millis);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

}
