//package study.exhange.api.osl;
//
//import java.util.Collections;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@SpringBootApplication
//public class TestApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TestApplication.class, args);
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
//        // timeout
//        httpRequestFactory.setReadTimeout(10000 * 10);
//        // timeout
//        httpRequestFactory.setConnectTimeout(2000 * 10);
//        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
//        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
//            request.getHeaders().set("User-Agent",
//                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");
//            return execution.execute(request, body);
//        };
//        restTemplate.setInterceptors(Collections.singletonList(interceptor));
//        return restTemplate;
//    }
//
//}
//
