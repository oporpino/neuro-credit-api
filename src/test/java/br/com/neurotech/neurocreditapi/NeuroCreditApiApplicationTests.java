package br.com.neurotech.neurocreditapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.h2.console.enabled=false"
})
class NeuroCreditApiApplicationTests {

  @Test
  void contextLoads() {
    // Just test if the Spring context loads successfully
  }
}
