package pl.sgnit.week9homework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMeasureAspect {

    @Before("@annotation(pl.sgnit.week9homework.aspect.TimeMeasure)")
    private void startCounting(JoinPoint joinPoint) {
        System.out.println("start: "+joinPoint.getSignature().getName());
    }

    @After("@annotation(pl.sgnit.week9homework.aspect.TimeMeasure)")
    private void stopCounting(JoinPoint joinPoint) {
        System.out.println("stop: "+joinPoint.getSignature().getName());
    }
}
