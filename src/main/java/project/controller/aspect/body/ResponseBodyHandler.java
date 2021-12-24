package project.controller.aspect.body;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import project.dto.ResultDto;

@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            MethodParameter methodParameter,
            Class<? extends HttpMessageConverter<?>> aClass
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object o,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse
    ) {
        if (o == null) {
            new ResultDto("success");
        }
        return new ResultDto(o);
    }
}
