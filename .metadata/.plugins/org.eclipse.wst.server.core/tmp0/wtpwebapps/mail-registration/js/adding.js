$("document").ready(function(){
	$(".button").on('click', function(){
		var name = $(".name").val();
		var pass = $(".pass").val();
		if(name&pass!=null){
			var data = "name="+name+"&pass="+pass;	
			$.ajax({
				type:'POST',
				data: data,
				url:"",
				success: function (result){
					alert("Поптыка "+result);
				}
			});
		}
		else{
			alert("Поля почта и/или пароль пустые! Попробуйте еще раз");
		}
	});
});