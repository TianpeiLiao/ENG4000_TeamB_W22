package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.Math;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.DatasetsDAO;
import dao.RecordsDAO;
import dao.UsersDAO;
import model.Elder;
import model.Random_Forest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	RecordsDAO records;
	UsersDAO usersdao;
	DatasetsDAO datasetdao;
	private static final long serialVersionUID = 1L;
    private boolean result;  
    private static ArrayList<Elder> elders = new ArrayList<Elder>();
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Register() throws ClassNotFoundException {
        super();
        datasetdao = new DatasetsDAO();
        records = new RecordsDAO();
        usersdao = new UsersDAO();
    }
    
    

    public static ArrayList<Elder> get_elders(){
    	return elders;
    }
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("out") != null && request.getParameter("out").equals("html")) {
			String f_name=null,l_name=null,url=null,gender=null;
			String phone_num=null,ec_num=null,age=null;
			
			
			if(request.getParameter("f_name")!= null && !request.getParameter("f_name").equals("")) {
				f_name = request.getParameter("f_name");			
			}
			if(request.getParameter("l_name")!= null && !request.getParameter("l_name").equals("")) {
				l_name = request.getParameter("l_name");			
			}
			if(request.getParameter("phone_num")!= null && !request.getParameter("phone_num").equals("")) {
				phone_num = request.getParameter("phone_num");	
				
			}
			if(request.getParameter("ec_num")!= null && !request.getParameter("ec_num").equals("")) {
				ec_num = request.getParameter("ec_num");			
			}
			if(request.getParameter("age")!= null && !request.getParameter("age").equals("")) {
				age =request.getParameter("age");			
			}
			if(request.getParameter("url")!= null && !request.getParameter("url").equals("")) {
				url =request.getParameter("url");			
			}
			if(request.getParameter("gender")!= null && !request.getParameter("gender").equals("")) {
				gender =request.getParameter("gender");			
			}
					
			Elder elder = new Elder();
			Random_Forest random_forest = new Random_Forest(elder);
			elder.setRandom_forest(random_forest);
			elder.setAge(age);
			elder.setEmergency_contact(ec_num);
			elder.setPhone_num(phone_num);
			elder.setF_name(f_name);
			elder.setL_name(l_name);
			elder.setGender(gender);
			elder.setUrl(url);
			elders.add(elder);
			result = true;
			int id = 0;
			int dataset_id = 0;
			
			try {
				id = usersdao.getID(f_name + "_" + l_name, gender, Integer.parseInt(age));
				
			} catch (NumberFormatException | SQLException | NamingException e1) {
				e1.printStackTrace();
			}
			try {
				if(id==-1) {
					id=usersdao.newId();
					usersdao.insert(id, f_name + "_" + l_name, gender, Integer.parseInt(age), phone_num);
				}
				
				dataset_id =  records.getDatasetID(id, new Date(Calendar.getInstance().getTimeInMillis()));
				if(dataset_id==-1) {
					dataset_id = datasetdao.newDatasetId();
					records.insert(id, dataset_id);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			elder.setDataset_id(dataset_id);
			
			
			
			
		}
		if(result) {
			Load_Html html=null;
			try {
				html = new Load_Html(request,response,elders);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			html.refresh_template();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
