<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1">

<title>The Weather Of Your City</title>
<body>
	<h2>
		Hello

		<s:property value="email" />
		
		write a city, please!
	</h2>




	<s:form action="welcome.action" method="post">
		<s:textfield name="city" key="label.city" />
		<s:submit method="execute" key="label.welcome" />
	</s:form>

</body>
</html>