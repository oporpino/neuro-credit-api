package br.com.neurotech.neurocreditapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NeuroCreditApiApplicationTests {

  @Test
  void contextLoads() {
    System.out.println("Context loads");

    // expect not raise any exception
    assertDoesNotThrow(() -> NeuroCreditApiApplication.main(new String[] {}));
  }

}
