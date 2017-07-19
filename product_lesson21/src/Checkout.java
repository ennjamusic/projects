

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBCon.*;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cookie cookies[];
	private int id[];
	private DBConnect db;
	private ResultSet orderResult;
	private String product [][] = new String[10][4];
	private int orderCount;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cookies = request.getCookies();
		id = new int [cookies.length];
		
		
		//Считываем ID из cookies
		for(int i=0; i<cookies.length; i++){
			if (!cookies[i].getName().equals("JSESSIONID")){
				id[i] = Integer.parseInt(cookies[i].getValue());
			}
		}
		for(int k=0;k<cookies.length; k++){ //обнуляем все куки
			cookies[k].setMaxAge(0);
		}
		try{
			db = new DBConnect();
			db.getCon(); //Установка соединения
			String query = "";
			
			query = "TRUNCATE TABLE cart";
			db.update(query); //Избавляемся от устаревших данных
			
			
			for(int i=0; i<id.length; i++){
				query = "INSERT INTO cart (product_id) "
						+ "VALUES ("+id[i]+")";
				db.update(query); //Помещаем заказы в бд
			}
			
			query = "SELECT " // Запрос
					+ "t1.name_product, "
					+ "t2.p_type_name, "
					+ "t1.weight_product, "
					+ "t1.price_product "
					+ "FROM products AS t1 "
					+ "INNER JOIN product_type AS t2 "
					+ "ON t1.id_p_type = t2.id_p_type "
					+ "INNER JOIN cart AS t3 "
					+ "ON t1.id_product = t3.product_id";
			orderResult = db.select(query);
			
			orderCount = 0; //счетчик строк
			while(orderResult.next()){
				for (int j=0; j<4; j++){
					product[orderCount][j] = orderResult.getString(j+1);
				}
				orderCount++;
			}
			formDocument(response);	//Формируем html для вывода результата	
			db.close(db.getCon());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				db.close(db.getCon());
			} catch (SQLException e) {}
		}
	}
	
	public void formDocument(HttpServletResponse response) throws IOException{ 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String titles[] = {"Название", "Сорт", "Вес, г", "Цена, руб"}; //Названия колонок
		int sum = 0; //Общая сумма
		
		String page = "";
		page+="<link href='/product_lesson21/css/cart.css' type='text/css' rel='stylesheet' />";
		
		page+="<div class='header'>";
			page+="<span class='title'>";
				page+="<b>Корзина</b>";
			page+="</span>";
		page+="</div>";
		page+="<div class='out'>";
			page+="<div class='row'>";
			for(int i=0; i<4; i++){
				page+="<span class='cell'>";
					page+="<b>"+titles[i]+"</b>";
				page+="</span>";
			}
			page+="</div>";
			for(int i=0; i<orderCount; i++){
				page+="<div class='row'>";
				for(int j=0; j<4; j++){
					page+="<span class='cell'>";
						page+=product[i][j];
					page+="</span>";
				}
				page+="</div>";
			}
			for(int i=0; i<orderCount; i++){
				sum+= Integer.parseInt(product[i][3]);
			}
			page+="<br><br><br>";
			page+="<span class='cell'>";
				page+="<b>Общая сумма</b> :"+sum+" рублей";
			page+="</span>";
		page+="</div>";
		page+="<br><br><br>";
		page+="<a href='/product_lesson21'>";
			page+="Вернуться к поиску";
		page+="</a>";
		
		out.println(page); //Вывод страницы
	}
}