package project.controller.aspect.body;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import project.dto.response.ResponseDto;

@RestControllerAdvice(basePackages = "project.controller")
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
        Object result;
        if (o == null) {
            result = new ResponseDto(true, "success");
        } else if (!(o instanceof ResponseDto)) {
            result = new ResponseDto(o);
        } else {
            result = o;
        }
        return result.toString();
    }
}
