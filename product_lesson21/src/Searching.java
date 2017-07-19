

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBCon.*; //������ ������ ����������� ��
/**
 * Servlet implementation class Searching
 */
@WebServlet("/Searching")
public class Searching extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnect db;
	ResultSet productResult;
	int productCount=0;
	
	String data; // ������ ��� �������� ������
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_p_type = request.getParameter("type"); //�������� id ���� ������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/text");
		PrintWriter out = response.getWriter();
		String query = "SELECT " // ������
				+ "t1.id_product, "
				+ "t1.name_product, "
				+ "t2.p_type_name, "
				+ "t1.weight_product, "
				+ "t1.price_product "
				+ "FROM products AS t1 INNER JOIN product_type AS t2 "
				+ "ON t1.id_p_type = t2.id_p_type "
				+ "WHERE t1.id_p_type = '"+id_p_type+"'";
		try {
			db = new DBConnect();
			db.getCon(); //����������� � ��
		
		//	data+="{\"product\" : ["; 
			
			int i = -1; //������� ������ ������� �� ��������
			
			data="";	//�������� ������������ ������ ��� JSON
			
			String product[][] = new String [10][5]; //������ ������� ������
			productResult = db.select(query); //���������� �������
			while (productResult.next()){
				data+="{";
				i++;
				//�������� �������� ������ � ���������� ����������� ������
				product[i][0] = productResult.getString(1);
				data+="\"id\":"+product[i][0]+", ";
				
				product[i][1] = productResult.getString(2);
				data+="\"name\":\""+product[i][1]+"\", ";
				
				product[i][2] = productResult.getString(3);
				data+="\"type\":\""+product[i][2]+"\", ";
				
				product[i][3] = productResult.getString(4);
				data+="\"weight\":\""+product[i][3]+"\", ";
				
				product[i][4] = productResult.getString(5);
				data+="\"price\":"+product[i][4];
			
				data+="}";				
			}
		//	data+="]}";
			
			out.println(data);//���������� ���������� ������
			
			//db.close(db.getCon());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		/*finally{
			try {
				//db.close(db.getCon());
				//db = null;//��������� ����������
			} 
		//	catch (SQLException e1) {e1.printStackTrace();}
		}*/
	}
}