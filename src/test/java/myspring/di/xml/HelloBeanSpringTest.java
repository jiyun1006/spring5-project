package myspring.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class HelloBeanSpringTest {
	@Autowired
	Hello hello;

	// Bean의 Class Type으로 의존하는 객체를 찾음
	@Autowired
	@Qualifier("helloCons")
	Hello hello2;

	// Bean의 name(Id)으로 의존하는 객체를 찾음
	@Resource(name = "helloCons")
	Hello hello3;

	@Test @Disabled
	void helloBean() {
		assertEquals("Hello 스프링", hello.sayHello());

		assertEquals("Hello 생성자", hello2.sayHello());

		assertEquals("Hello 생성자", hello3.sayHello());

		hello2.print();

	}
	
	@Test
	void list() {
		 for(String value : hello2.getNames()) {
			 System.out.println(value);
		 }
		 
		 assertEquals(4, hello2.getNames().size());
		 assertEquals("스프링프레임워크",hello2.getNames().get(0));
	}
	
	

}
