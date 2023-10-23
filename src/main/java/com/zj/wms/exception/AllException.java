package com.zj.wms.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕捉类
 * 
 * @author Bean
 *
 */
@ControllerAdvice
public class AllException {

	// 角色权限异常捕捉
	@ExceptionHandler(value = UnauthorizedException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public String roleException(UnauthorizedException e) {
		return "角色权限不够！！！";
	}

	// 其它异常异常捕捉
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
//	public String allException(Exception e) {
//		return "系統出现异常！！！";
//	}

}