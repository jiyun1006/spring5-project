package myspring.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	GenericXmlApplicationContext factory;
	
	@BeforeEach
	void container() {
		// Bean Container 객체 생성
		factory = new GenericXmlApplicationContext("classpath:spring-beans.xml");
	}
	
	@AfterEach
	void close() {
		// Bean Container 객체 닫기
		factory.close();
	}

	@Test
	void hello() {

		// Hello Bean을 Container 에게 요청하기 Bean의 id와 Type를 기준으로 Bean을 요청하기
		Hello helloById = (Hello) factory.getBean("hello");
		Hello helloByType = factory.getBean("hello", Hello.class);

		System.out.println(helloById == helloByType);

		// assertSame() 메서드 사용
		assertSame(helloById, helloByType);

		// setName() Injection을 검증하기
		assertEquals("Hello 스프링", helloByType.sayHello());

		// setPrinter() Injection을 검증하기
		helloByType.print();

		// StringPrinter Bean을 요청
		Printer printer = factory.getBean("strPrinter", Printer.class);

		assertEquals("Hello 스프링", printer.toString());

	}
}
