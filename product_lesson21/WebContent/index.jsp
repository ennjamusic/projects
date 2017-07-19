<%@page import="DBCon.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<script src="js/jquery-3.2.1.js"></script>
		<script src="js/ajax.js"></script>
		<title>Товары</title>
	</head>
	<body>
		<%!
			String items [] = {"Белый хлеб","Ржаной хлеб","Цельнозерновой хлеб","Сдоба"};
		%>
		<div class="main-wrapper">
			<div class="header">
				<div class="title">Поиск</div>
			</div>
			<div class="content">		
				<div class="panel">
					<p class="type">Выберите сорт:</p>
					<select name="type" class="type_select">
						<option value="1"><%=items[0]%></option>
						<option value="2"><%=items[1]%></option>
						<option value="3"><%=items[2]%></option>
						<option value="4"><%=items[3]%></option>
					</select>
					<input type="submit" class="but" value="Найти">
				</div>
				<div class="out">
					<select size="10" class="result"></select>	
					<br><br>
					<input type="submit" class="toCart" value="Добавить в корзину" />
					<br><br>
					<form action="/product_lesson21/Checkout" method="POST">
						<input type="submit" class="checkout" value="Офомить заказ" />
					</form>
				</div>
			</div>
		</div>
	</body>
</html>