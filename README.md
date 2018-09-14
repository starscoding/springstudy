# Spring 项目学习

----------

## Javaweb集成Spring框架

- ### 1.修改web.xml

		<!--整合spring-->
		<listener>
	        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	    </listener>

	    <context-param>
	        <param-name>contextConfigLocation</param-name>
	        <param-value>classpath:applicationContext.xml</param-value>
	    </context-param>

- ### 2.创建applicationContent.xml

	    <?xml version="1.0" encoding="UTF-8"?>
	       <beans xmlns="http://www.springframework.org/schema/beans"

	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"

	       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"

	       default-lazy-init="false" default-autowire="byName">

	       <!--开启注解-->
	       <context:component-scan base-package="com.azxx"/>
	       <!--加载配置文件-->
	       <context:property-placeholder location="classpath*:/appConfig.properties" />
	     </beans>

- ### 3.测试

- **3.1 创建TestDI.java**

		@Component
		public class TestDI {

			//注入配置中的值
		    @Value("${jdbc.driver}")
		    private String testproperteis;

		    private static final Logger logger = LogManager.getLogger(TestDI.class);

		    public TestDI() {
		        System.out.println(">>>>>>>>> enter TestDI class constructor");
		    }

		    public int add(int a ,int b){
		        return a+b;
		    }

		    public void testMethod(){
		        System.out.println(this.testproperteis);
		    }

		}

- **3.2 创建测试类**

		/**
		 * 将所需加载的xml文件指定为locations的value。如：@ContextConfiguration(locations = { "classpath:Application-Redis.xml" })
		 */
		@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
		@RunWith(SpringJUnit4ClassRunner.class)
		public class SpringTestBase extends AbstractJUnit4SpringContextTests {
		}

		public class TestDITest extends SpringTestBase {

		    @Autowired
		    private TestDI testDI;

		    @Test
		    public void TestAdd(){
		        Assert.assertEquals(4,testDI.add(1,2));
		    }

		    @Test
		    public void TestAdd2(){
		        Assert.assertEquals(3,testDI.add(1,2));
		    }

		    @Test
		    public void testMethod(){
		        System.out.println("");
		        testDI.testMethod();
		    }
		}

- **3.3 创建配置文件db.properties**

		jdbc.driver=com.mysql.jdbc.Driver
		jdbc.url=jdbc:mysql://127.0.0.1:3306/springstudy?characterEncoding=utf-8
		jdbc.username=root
		jdbc.password=shouwang13



## 整合SpringMVC

- ### 1.修改web.xml,增加以下内容

	    <!--configure the setting of springmvcDispatcherServlet and configure the mapping-->
	    <servlet>
	        <servlet-name>springmvc</servlet-name>
	        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	        <init-param>
	            <param-name>contextConfigLocation</param-name>
	            <param-value>classpath:springmvc.xml</param-value>
	        </init-param>
	        <!--它的值必须是一个整数，表示servlet被加载的先后顺序
	        如果该元素的值为负数或者没有设置，则容器会当Servlet被请求时再加载
	        如果值为正整数或者0时，表示容器在应用启动时就加载并初始化这个servlet，值越小，servlet的优先级越高，就越先被加载。值相同时，容器就会自己选择顺序来加载。
	        -->
	         <load-on-startup>1</load-on-startup>
	    </servlet>

	    <servlet-mapping>
	        <servlet-name>springmvc</servlet-name>
	        <url-pattern>/</url-pattern>
	    </servlet-mapping>

- ### 2.创建springmvc.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
		       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
		       xmlns:context="http://www.springframework.org/schema/context"
		       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd ">

		    <!-- 启用spring mvc 注解 -->
		    <context:annotation-config/>

		    <!-- 设置使用注解的类所在的jar包 -->
		    <context:component-scan base-package="com.azxx"/>

		    <!-- 完成请求和注解POJO的映射 -->
		    <!--<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter"/>-->
		    　　
		    <!-- 对转向页面的路径解析。prefix：前缀， suffix：后缀 -->
		    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" p:prefix="/jsp/" p:suffix=".jsp"/>
		</beans>

### 整合SpringTest
### 整合SpringJDBC
### 整合Mybatis
### 整合Spring Batch
### 整合Spring Security/Apache Shiro
### 整合Spring Session
