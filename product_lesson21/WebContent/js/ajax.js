$(document).ready(function (){ //Инициализация (общ.)
	
	$(".but").on('click', function(){ //Клик по кнопке поиска
		var type = "type="+$(".type_select :selected").val();//Выбранный сорт	
		$.ajax({
			type:'POST',
			data: type,
			url: "/product_lesson21/Searching",
			success: function(data){	//Парсим JSON и выводим список
				var	product = JSON.parse(data);
				$(".result").empty();
				$(".result").append("<option class='resOpt'id='1' value='"+product.id+"'></option>");
				$("#1.resOpt").text(
						 " Название: "+product.name
						+", Сорт: "+product.type
						+", Вес, г: "+product.weight
						+", Цена, руб: "+product.price);
				$(".result :last").attr("selected", "selected");
			}
		});	
		$(".toCart").show();
	});
	
	$(".toCart").on('click', function(){ //Клик на кнопку добавления в корзину
		var item = "id="+$(".result :selected").val(); //Выбранный товар
		$.ajax({
			type: 'POST',
			data: item,
			url: "/product_lesson21/addCart",
			success: function(data){
				alert("Товар был успешно добавлен в корзину!");
			}
		});
	});
});