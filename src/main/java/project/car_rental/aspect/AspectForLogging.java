package project.car_rental.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AspectForLogging {

    @Before("execution(* project.car_rental.controller.*.*(..))" + "|| execution(* project.car_rental.service.*.*.*(..))"
            + "|| execution(* project.car_rental.service.*.*.*())")
    public void beforeLogger(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info(
                "Method {} executed with {} arguments",
                joinPoint.getStaticPart().getSignature(),
                joinPoint.getArgs()
        );
    }

    @AfterThrowing(value = "execution(* project.car_rental.controller.*.*(..))" + "|| execution(* project.car_rental.service.*.*.*(..))"
            + "|| execution(* project.car_rental.service.*.*.*())" + "|| execution(* project.car_rental.model.*.*.*())"
            + "|| execution(* project.car_rental.model.*.*.*())", throwing = "error")
    public void errorLogger(JoinPoint joinPoint, Throwable error) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.error(
                "Method {} executed with {} arguments throw {}",
                joinPoint.getStaticPart().getSignature(),
                joinPoint.getArgs(), error
        );
    }

    @AfterReturning(value = "execution(* project.car_rental.controller.*.*(..))" + "|| execution(* project.car_rental.service.*.*.*(..))"
            + "|| execution(* project.car_rental.service.*.*.*())", returning = "result")
    public void afterReturningLogger(JoinPoint joinPoint, Object result) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info(
                "Method {} returned {}",
                joinPoint.getStaticPart().getSignature().getName(),
                result
        );
    }
}
