package com.example.javastudy.safe;
import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.example.javastudy.designMode.Response;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;

import static com.example.javastudy.safe.BusinessCode.PARAM_ILLEGAL;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());


//    /**
//     * 入参非法异常捕获
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({BusinessException.class})
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Response handleServiceException(BusinessException e) {
//        log.error("系统异常,{}", e);
//        BusinessCode code = new BusinessCode(e.getCode(), e.getMsg());
//        return Response.fail(code, e.getMsg());
//    }
//
//
//    /**
//     * 系统异常捕获
//     *
//     * @param e
//     * @return
//     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleOtherException(HttpServletRequest request,Exception e) {
        log.error("系统异常,{}", e);
        if (e instanceof BindException){
            BindException ex = (BindException) e;
            return Response.error(Integer.parseInt(PARAM_ILLEGAL.getCode()), ex.getMessage());
        }
        return Response.error(400, "系统异常");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Response handleException(BusinessException e) {

        return Response.error(Integer.parseInt(e.getCode()), e.getMsg());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        if (StringUtils.isBlank(msg)) {
            msg = PARAM_ILLEGAL.getMsg();
        }
        return Response.error(400, msg);
    }


    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    public Response handleException(ValidateException e) {
        String msg = StringUtils.isBlank(e.getMessage()) ? PARAM_ILLEGAL.getMsg() : e.getMessage();
        return Response.error(Integer.parseInt(PARAM_ILLEGAL.getCode()), msg);
    }

//    @ExceptionHandler(Throwable.class)
//    @ResponseBody
//    public Response handleException(Throwable e, HttpServletRequest request) {
//
//        logger.error("An error occurred! uri: " + request.getRequestURI(), e);
//        return error(SERVER_ERROR);
//    }

}