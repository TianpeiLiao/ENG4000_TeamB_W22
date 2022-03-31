package ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import dao.DatasetsDAO;
import model.Elder;
import model.Random_Forest;

public class Load_Html {
	DatasetsDAO datasetsDao;
	HttpServletRequest request;
	HttpServletResponse response;
	final static int refresh_rate = 1;
	static int anamoly_timer = 0;
	
	public Load_Html(HttpServletRequest request, HttpServletResponse response, ArrayList<Elder> elders) throws ClassNotFoundException {
		datasetsDao = new DatasetsDAO();
		this.request = request;	
		this.response = response;	
	}
	
	 public static String readUrl(String url) throws Exception{
	        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
	        StringBuffer str = new StringBuffer();

	        String readLineStr = null;
	        while((readLineStr = br.readLine()) != null) {
	            str.append(readLineStr).append("\r\n");
	        }
	        return str.toString();
	 }
	 
	 
	 private double[] get_acc(String url) throws Exception {
		 if(anamoly_timer!=0)
				anamoly_timer+=1;
		 	double[] accs = new double[4];
		 	accs[0] = 0;
			accs[1] = 0;
			accs[2] = 0;
			accs[3] = 0;
		 	String json = readUrl("http://"+url + "/get?accX&accY&accZ");
			JSONObject jsonBuffer = new JSONObject(json);
			JSONObject buffer =  (JSONObject) jsonBuffer.get("buffer");

			JSONObject bufferAccX = (JSONObject) buffer.get("accX");
			JSONObject bufferAccY = (JSONObject) buffer.get("accY");
			JSONObject bufferAccZ = (JSONObject) buffer.get("accZ");	
			
			
			JSONArray accX = (JSONArray) bufferAccX.get("buffer");
			JSONArray accY = (JSONArray) bufferAccY.get("buffer");
			JSONArray accZ =  (JSONArray) bufferAccZ.get("buffer");
			
			
			if(!JSONObject.NULL.equals(accX.get(0)) && !JSONObject.NULL.equals(accY.get(0)) && !JSONObject.NULL.equals(accZ.get(0))){
				double accXd = (double) accX.get(0);
				double accYd = (double) accY.get(0);
				double accZd = (double) accZ.get(0);
				double acc = Math.sqrt(accXd*accXd + accYd*accYd + accZd*accZd);
				accs[0] = accXd;
				accs[1] = accYd;
				accs[2] = accZd;
				accs[3] = acc;
			}
				
			
			
			
			

			
			return accs;
	 }
	
	
	

	public void refresh_template() throws IOException {
		
		response.setHeader("refresh", "0.25");
//		response.setIntHeader("refresh", refresh_rate);
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = null;
		double[] acc = new double[4];		
	
		out = response.getWriter();
		out.println("<!DoCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style type=\"text/css\">");
		out.println("fieldset.inline {");
		out.println("display: inline;");
		out.println(" }");
		out.println("</style>");
		
		out.println("<link rel=\"StyleSheet\" href=\"res/mc.css\" type=\"text/css\" title=\"cse4413\" media=\"screen, print\"/>");
		out.println("<title>Care</title>");
		out.println("<script type=\"text/javascript\" src=\"scripts/mc.js\">;</script>");
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<header>");
		out.println("<h3>Long Term Care Homes</h3>");
		out.println("</header>");
		
//		out.print("<h3> <a href='http://localhost:8080/ENG4000_Demo/Database.html'>Access to Database</a> </h3>");
		
		for(Elder elder: Register.get_elders()) {
			
			
			
			try {				
				acc = get_acc(elder.getUrl());	
				elder.add_acc(acc);
//				datasetsDao.insert(elder.getDataset_id(), acc[0],  acc[1],  acc[2], elder.getRandom_forest().get_state());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.println("<fieldset class='inline'>");
			out.println("<legend>Name:" + elder.getF_name() + " "+ elder.getL_name()+ "</legend>");
			out.println("<article>");
			out.println("<h5>");
			out.println("Phone number:" + elder.getPhone_num());
			out.println("</h5>");
			out.println("<h5>");
			out.println("Emergency contact number:" + elder.getEmergency_contact());
			out.println("</h5>");
			out.println("<h5>");
			out.println("Gender:" + elder.getGender());
			out.println("</h5>");
			out.println("<h5>");
			out.println("Age:" + elder.getAge());
			out.println("</h5>");
			
			 
			if(!elder.isAnomaly()) {
				String elder_state = elder.getRandom_forest().get_state();
				String elder_gender = elder.getGender().toLowerCase();
				
				if(elder_state.equals("normal_running") && elder_gender.equals("male")) {
					out.println("<center><h2 style=\"color:red;\">" + "Potential Anomaly: Running or possible fall" +"</h2></center>");
					out.println("<IMG SRC='man_running.gif' style='width:300px; height:300px;'>");
					anamoly_timer +=1;
//					out.println("<script>");
//					out.println("function test() {alert(\"Anomaly\");}")    ;
//					 out.println("window.addEventListener('load', event => {test();});");
//					out.println("<\script>");
				}
				else if(elder_state.equals("normal_walking") && elder_gender.equals("male")) {
					out.println("<center><h2>" + elder_state +"</h2></center>");
					out.println("<IMG SRC='man_walking.gif' style='width:300px; height:300px;'>");
				}
				else if(elder_state.equals("normal_staying") && elder_gender.equals("male")) {
					out.println("<center><h2>" + elder_state +"</h2></center>");
					out.println("<IMG SRC='man_standing.gif' style='width:300px; height:300px;'>");
				}
				
				if(elder_state.equals("normal_running") && elder_gender.equals("female")) {
					out.println("<center><h2 style=\"color:red;\">" + "Potential Anomaly: Running or possible fall" +"</h2></center>");
					out.println("<IMG SRC='girl_running.gif' style='width:300px; height:300px;'>");
				}
				else if(elder_state.equals("normal_walking") && elder_gender.equals("female")) {
					out.println("<center><h2>" + elder_state +"</h2></center>");
					out.println("<IMG SRC='girl_walking.gif' style='width:300px; height:300px;'>");
				}
				else if(elder_state.equals("normal_staying") && elder_gender.equals("female")) {
					out.println("<center><h2>" + elder_state +"</h2></center>");
					out.println("<IMG SRC='girl_standing.gif' style='width:300px; height:300px;'>");
				}
				System.out.println ("Normal acc:" + acc[3]+" accX:" + acc[0]+" accY:" + acc[1]+" accZ:" + acc[2]);
				if(anamoly_timer>20)
					out.println("<img src onerror='warning()'>");
				
			}
//			else {
//				String elder_anomoly_state =  elder.getRandom_forest().getAnomaly_state();
//				out.println("<center><h2 style=\"color:red;\">" + elder_anomoly_state +"</h2></center>");
//				if(elder_anomoly_state.equals("anomaly: might drop_phone")) {
//					out.println("<IMG SRC='drop_phone.gif' style='width:300px; height:300px;'>");
//				}
//				System.out.println ("Anomaly acc:" + acc[3]+" accX:" + acc[0]+" accY:" + acc[1]+" accZ:" + acc[2]);
//			}
			
			out.println("</article>");
			out.println("</fieldset>");
			
			System.out.println(anamoly_timer);
		}
						
			
			out.println("</body>");
			out.println("</html>");
			
	}
}
