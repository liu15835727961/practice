springboot-axis2项目是spring-boot集成axis2
	访问地址:127.0.0.1/services/nameService?wsdl
	http方式访问:http://localhost/services/nameService/getName?name=11
		services:在config中配置的ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new AxisServlet(), "/services/*");
		nameService:在配置文件WEB-INF\services\conf\META-INF下的配置文件中services.xml<service name="nameService"></service>	