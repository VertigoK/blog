package uniflow.blog.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice   //프로젝트 전역(모든 controller)에서 발생하는 예외를 처리하게 함
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  //모든 예외 처리 (특정 예외만 지정 가능)
    public String handleArgumentHandler(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

}
