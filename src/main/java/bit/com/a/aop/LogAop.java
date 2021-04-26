package bit.com.a.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {
	
	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// System.out.println(signatureStr + "시작");
		
		try {
			Object obj = joinpoint.proceed();	// 실행시
			
			System.out.println("loggerAOP:" + signatureStr + " 메소드가 실행 " + new Date());
			
			return obj;
			
			
		}finally {
		//	System.out.println("실행 후:" + System.currentTimeMillis());
		//	System.out.println(signatureStr + "종료");	// 실행후
		}		
	}

}









