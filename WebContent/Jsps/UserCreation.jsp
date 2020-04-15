<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>Create an User</title>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="stylesheet" href="../Css/PageStyle.css">
			<script language="JavaScript" type="text/javascript">
				function submitFormToCancel()
				{
				  	document.userRegistrationContainer.cancelOperation.value = 'goBackHome' ;
				}
			</script>
		</head>
		<body>
			<form id="UserRegistration" name="userRegistrationContainer" action="../UserCreationServlet" method="post">
				<jsp:include page="UserRegistrationWindow.jsp"></jsp:include>
				<table>
					<tr>
						<td>
							<input type="submit" style="width: 150px;
											height: 40px;
											background-color: #4dff4d;
											border: none;
											color: black;
											padding: 15px 32px;
											text-align: center;
											text-decoration: blink;
											display: inline-block;
											font-size: 15px;
											margin: 4px 2px;
											cursor: pointer;" value="Add">
							<input type="submit" style="width: 150px;
											height: 40px;
											background-color: #4dff4d;
											border: none;
											color: black;
											padding: 15px 32px;
											text-align: center;
											text-decoration: blink;
											display: inline-block;
											font-size: 15px;
											margin: 4px 2px;
											cursor: pointer;" onclick="javascript:submitFormToCancel()" value="Cancel">
						</td>
					</tr>
				</table>
				<input type="hidden" name="cancelOperation" >
			</form> 
		</body>
	</html>