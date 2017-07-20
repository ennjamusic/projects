$(document).ready(function(){
	$(".button").on('click', function(){
		var name = $(".name").val();
		var pass = $(".pass").val();
		alert(name+' ^ '+pass);
		if(name!=''&pass!=''){
			var data = "name="+name+"&pass="+pass;	
			$.ajax({
				type:'POST',
				data: data,
				url:"/AddEmail/",
				success: function (result){
					alert("Попытка "+result);
				}
			});
		}
		else{
			alert("Поля почта и/или пароль пустые! Попробуйте еще раз");
		}
	});
});