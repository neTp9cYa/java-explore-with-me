package ru.practicum.stats.client.aspect;


import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.practicum.stats.client.service.StatService;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CollectRequestStatisticAspect {

    private final StatService statService;

    @AfterReturning(pointcut = "@annotation(ru.practicum.stats.client.aspect.CollectRequestStatisticAnnotaion)")
    public void afterReturning(JoinPoint joinPoint) {
        final HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        statService.addHit(request);
    }
}
