package tn.esprit.tic.springproj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

    // @Before advice
    @Before("execution(* tn.esprit.tic.springproj.service.*.*(..))")
    public void logBefore() {
        System.out.println("Executing @Before advice: Method is about to execute.");
    }

    // @After advice
    @After("execution(* tn.esprit.tic.springproj.service.*.*(..))")
    public void logAfter() {
        System.out.println("Executing @After advice: Method has executed.");
    }

    // @Around advice
    @Around("execution(* tn.esprit.tic.springproj.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Executing @Around advice: Before method execution.");
        Object result = joinPoint.proceed(); // Proceed with the method execution
        System.out.println("Executing @Around advice: After method execution.");
        return result;
    }

    // @AfterReturning advice
    @AfterReturning(pointcut = "execution(* tn.esprit.tic.springproj.service.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Executing @AfterReturning advice: Method returned value - " + result);
    }

    // @AfterThrowing advice
    @AfterThrowing(pointcut = "execution(* tn.esprit.tic.springproj.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        System.out.println("Executing @AfterThrowing advice: Exception thrown - " + exception.getMessage());
    }
}