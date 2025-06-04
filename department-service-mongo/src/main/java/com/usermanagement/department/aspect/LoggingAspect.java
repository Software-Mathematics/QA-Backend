//package com.usermanagement.department.aspect;
//
//
//import lombok.extern.log4j.Log4j2;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import java.util.Arrays;
//
//@Aspect
//@Log4j2
//@Component
//public class LoggingAspect {
//
//	 private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
//	  @Around("execution(* com.usermanagement.department.service.controller.*.*(..)))")
//	    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
//	    {
//	        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//
//	        String className = methodSignature.getDeclaringType().getSimpleName();
//	        String methodName = methodSignature.getName();
//
//	        final StopWatch stopWatch = new StopWatch();
//
//	        stopWatch.start();
//	        Object result = proceedingJoinPoint.proceed();
//	        stopWatch.stop();
//
//	        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
//
//	        return result;
//	    }
//
//	  @Pointcut("within(@org.springframework.stereotype.Repository *)" +
//		        " || within(@org.springframework.stereotype.Service *)" +
//		        " || within(@org.springframework.web.bind.annotation.RestController *)")
//		    public void springBeanPointcut() {
//
//		    }
//	  @Pointcut("within(com.usermanagement.department.service.*.*)" +
//		        " || within(com.usermanagement.department.service.service.*.*)" +
//		        " || within(com.usermanagement.department.service.controller.*.*)")
//		    public void applicationPackagePointcut() {
//	  }
//
////	  @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
////	    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
////	        LOGGER.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
////	            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
////	    }
//	  //@Around("execution(* com.role_service.service.*(..)))")
//	  //@Around("applicationPackagePointcut() && springBeanPointcut()")
////	    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
////		  Object result = joinPoint.proceed();
////	        if (LOGGER.isDebugEnabled()) {
////	        	LOGGER.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
////	                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
////	        }
////
////	        return result;
////	    }
//
//	  @Around("springBeanPointcut()")
//	    public Object logArounds(ProceedingJoinPoint joinPoint) throws Throwable {
//
//			  LOGGER.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
//	                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//
//	        try {
//	            Object result = joinPoint.proceed();
//
//	            	LOGGER.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
//	                    joinPoint.getSignature().getName(), result);
//
//	            return result;
//	        } catch (IllegalArgumentException e) {
//	        	LOGGER.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
//	                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//	            throw e;
//	        }
//	    }
//
//
//}
