package ua.com.alevel.security;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
public class HasRoleAspect {

    @Around("execution(* *.*(..)) && @annotation(hasRole)")
    public Object hasRoleAspect(ProceedingJoinPoint joinPoint, HasRole hasRole) {
        try {
            String value = hasRole.value();
            System.out.println("value = " + value);
            Principal principal = Session.getInstance().getCurrentPrincipal();
            if (principal == null) {
                throw new RuntimeException("not auth");
            }
            System.out.println("principal = " + principal);
            List<String> roles = principal.getRoleGroup();
            List<String> currentRoles = Arrays.asList(hasRole.value().split(","));
            Set<String> existRoles = roles.stream().filter(currentRoles::contains).collect(Collectors.toSet());
            System.out.println("existRoles = " + existRoles);
            if (CollectionUtils.isEmpty(existRoles)) {
                throw new RuntimeException("not authentication");
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
