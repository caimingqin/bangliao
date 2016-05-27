package com.bl.common.exception;
//package com.bl.common.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
///**
// * 
// * 异常处理类,根据从底层向上抛出的异常类型相应转到不同的异常处理页面 
// *
// * @author Jiarong
// * @date 2014年11月2日 下午10:01:52 
// * @version 0.0.1
// *
// */
//public class ExceptionHandler implements HandlerExceptionResolver {
//	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception ex) {
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("ex", ex);
//		// 根据不同错误转向不同页面
//		if(ex instanceof BusinessException) {
//			BusinessException be = (BusinessException) ex;
//			StringBuffer sf = new StringBuffer("------BusinessException-----");
//			sf.append("code:").append(be.getCode());
//			log.error(sf.toString());
//			log.error(be.getCode(),ex);
//			return new ModelAndView("/tbsp/error/error-business", model);
//		}else if(ex instanceof ValidationException) {
//			log.error("---ParameterException--",ex);
//			return new ModelAndView("/tbsp/error/error-parameter", model);
//		} else {
//			log.error("---error--",ex);
//			return new ModelAndView("/tbsp/error/error", model);
//		}
//	}
//}
