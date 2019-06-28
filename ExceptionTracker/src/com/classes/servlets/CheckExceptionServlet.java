package com.classes.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import com.classes.Logic.LogFileReader;
import com.classes.Logic.ThreadPool;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class CheckExceptionServlet extends HttpServlet {
	
	static Properties bConnectedProperties;
	static Properties regexPatternProperties;
	
	@Override
	public void init() throws ServletException {
		bConnectedProperties = new Properties();
		regexPatternProperties = new Properties();
		try {
			bConnectedProperties.load(this.getClass().getClassLoader().getResourceAsStream("bConnectedInstance_prod.properties"));
			regexPatternProperties.load(this.getClass().getClassLoader().getResourceAsStream("instances_regex_pattern.properties"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Invoked!!!");
		PrintWriter out = response.getWriter();

		  //create Json Object
		  JSONObject json = new JSONObject();

		    // put some value pairs into the JSON object .
		    json.put("test", "success");
		    		    // finally output the json string
		    
		    out.write(json.toString());
	}*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//String searchReqJson = req.getParameter("data");
		String searchReqJson = "{\"date\": \"2016-06-22\", \"instances\": {\"IBCHCR\": false, \"CEDARS\": false, \"HIPNY\":false, \"CIGNA\":false, \"OHCS\":false, \"HNHRA\":false, \"KSG\":true, \"ALMS\":false}}";
		
		JSONObject jsonObject = new JSONObject(searchReqJson);
		
		String logFileDateStr = (String) jsonObject.getString("date");
		JSONObject instances = jsonObject.getJSONObject("instances");
		
		/*DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yesturday = Calendar.getInstance();
    	yesturday.add(Calendar.DATE, -1);
    	String logFileDateStr = format.format(yesturday.getTime());*/
		
    	Map<String, Map<String, Long>> instanceExceptionMap = null;
    	
    	LogFileReader.setUsername(bConnectedProperties.getProperty("username"));
    	LogFileReader.setPassword(bConnectedProperties.getProperty("password"));
    	LogFileReader.setPort(Integer.parseInt(bConnectedProperties.getProperty("default_port")));
    	LogFileReader.setLogFileDateStr(logFileDateStr);
		 
    	List<LogFileReader> instanceList = new ArrayList<>();
    	
    	
    	if(instances.getBoolean("CIGNA")){
    		LogFileReader CIGNA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cigna"), bConnectedProperties.getProperty("instance_ip_cigna"), bConnectedProperties.getProperty("logfilepath_cigna"),
					regexPatternProperties.getProperty("search_pattern_cigna"), regexPatternProperties.getProperty("skipped_pattern_cigna"));
    		instanceList.add(CIGNA_FileReader);
    	}
    	
    	if(instances.getBoolean("ALMS")){
    		LogFileReader ALMS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_alms"), bConnectedProperties.getProperty("instance_ip_alms"), bConnectedProperties.getProperty("logfilepath_alms"),
					regexPatternProperties.getProperty("search_pattern_alms"), regexPatternProperties.getProperty("skipped_pattern_alms"));
    		instanceList.add(ALMS_FileReader);
    	}
    	
    	if(instances.getBoolean("HIPNY")){
    		LogFileReader HIPNY_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hipny"), bConnectedProperties.getProperty("instance_ip_hipny"), bConnectedProperties.getProperty("logfilepath_hipny"),
					regexPatternProperties.getProperty("search_pattern_hipy"), regexPatternProperties.getProperty("skipped_pattern_hipny"));
    		instanceList.add(HIPNY_FileReader);
    	}
    	
    	if(instances.getBoolean("CEDARS")){
    		LogFileReader CEDARS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cedars"), bConnectedProperties.getProperty("instance_ip_cedars"), bConnectedProperties.getProperty("logfilepath_cedars"), 
					regexPatternProperties.getProperty("search_pattern_cedars"), regexPatternProperties.getProperty("skipped_pattern_cedars"));
    		instanceList.add(CEDARS_FileReader);
    	}
    	
    	if(instances.getBoolean("KSG")){
    		LogFileReader KSG_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ksg"), bConnectedProperties.getProperty("instance_ip_ksg"), bConnectedProperties.getProperty("logfilepath_ksg"),
					regexPatternProperties.getProperty("search_pattern_ksg"), regexPatternProperties.getProperty("skipped_pattern_ksg"));
    		instanceList.add(KSG_FileReader);
    	}
    	
    	if(instances.getBoolean("HNHRA")){
    		LogFileReader HNHRA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hnhra"), bConnectedProperties.getProperty("instance_ip_hnhra"), bConnectedProperties.getProperty("logfilepath_hnhra"), 
					regexPatternProperties.getProperty("search_pattern_hnhra"), regexPatternProperties.getProperty("skipped_pattern_hnhra"));
    		instanceList.add(HNHRA_FileReader);
    	}
    	
    	if(instances.getBoolean("IBCHCR")){
    		LogFileReader IBCHCR_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ibchcr"), bConnectedProperties.getProperty("instance_ip_ibchcr"), bConnectedProperties.getProperty("logfilepath_ibchcr"),
					regexPatternProperties.getProperty("search_pattern_ibchcr"), regexPatternProperties.getProperty("skipped_pattern_ibchcr"));
    		instanceList.add(IBCHCR_FileReader);
    	}
    	
    	if(instances.getBoolean("OHCS")){
    		LogFileReader OHCS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ohcs"), bConnectedProperties.getProperty("instance_ip_ohcs"), bConnectedProperties.getProperty("logfilepath_ohcs"),
					regexPatternProperties.getProperty("search_pattern_ohcs"), regexPatternProperties.getProperty("skipped_pattern_ohcs"));
    		instanceList.add(OHCS_FileReader);
    	}
    	
    	
    	
    	
    	
    	/*LogFileReader CIGNA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cigna"), bConnectedProperties.getProperty("instance_ip_cigna"), bConnectedProperties.getProperty("logfilepath_cigna"),
    										regexPatternProperties.getProperty("search_pattern_cigna"), regexPatternProperties.getProperty("skipped_pattern_cigna"));
    	LogFileReader ALMS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_alms"), bConnectedProperties.getProperty("instance_ip_alms"), bConnectedProperties.getProperty("logfilepath_alms"),
    										regexPatternProperties.getProperty("search_pattern_alms"), regexPatternProperties.getProperty("skipped_pattern_alms"));
    	LogFileReader HIPNY_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hipny"), bConnectedProperties.getProperty("instance_ip_hipny"), bConnectedProperties.getProperty("logfilepath_hipny"),
    										regexPatternProperties.getProperty("search_pattern_hipy"), regexPatternProperties.getProperty("skipped_pattern_hipny"));
    	LogFileReader CEDARS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cedars"), bConnectedProperties.getProperty("instance_ip_cedars"), bConnectedProperties.getProperty("logfilepath_cedars"), 
    										regexPatternProperties.getProperty("search_pattern_cedars"), regexPatternProperties.getProperty("skipped_pattern_cedars"));
    	LogFileReader KSG_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ksg"), bConnectedProperties.getProperty("instance_ip_ksg"), bConnectedProperties.getProperty("logfilepath_ksg"),
    										regexPatternProperties.getProperty("search_pattern_ksg"), regexPatternProperties.getProperty("skipped_pattern_ksg"));
    	LogFileReader HNHRA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hnhra"), bConnectedProperties.getProperty("instance_ip_hnhra"), bConnectedProperties.getProperty("logfilepath_hnhra"), 
    										regexPatternProperties.getProperty("search_pattern_hnhra"), regexPatternProperties.getProperty("skipped_pattern_hnhra"));
    	LogFileReader IBCHCR_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ibchcr"), bConnectedProperties.getProperty("instance_ip_ibchcr"), bConnectedProperties.getProperty("logfilepath_ibchcr"),
    										regexPatternProperties.getProperty("search_pattern_ibchcr"), regexPatternProperties.getProperty("skipped_pattern_ibchcr"));
    	LogFileReader OHCS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ohcs"), bConnectedProperties.getProperty("instance_ip_ohcs"), bConnectedProperties.getProperty("logfilepath_ohcs"),
    										regexPatternProperties.getProperty("search_pattern_ohcs"), regexPatternProperties.getProperty("skipped_pattern_ohcs"));
    	
    	instanceList.add(CIGNA_FileReader);
    	//instanceList.add(ALMS_FileReader);
    	instanceList.add(HIPNY_FileReader);
    	instanceList.add(CEDARS_FileReader);
    	instanceList.add(KSG_FileReader);
    	instanceList.add(HNHRA_FileReader);
    	instanceList.add(IBCHCR_FileReader);
    	instanceList.add(OHCS_FileReader);*/
    	
    	ThreadPool threadPool = new ThreadPool();
    	
    	try {
    		instanceExceptionMap = threadPool.submitJobs(instanceList);
    		
    		String json = new Gson().toJson(instanceExceptionMap);
    		System.out.println(json);
    		
    		if(instanceExceptionMap != null){
    			HttpSession session = req.getSession();
    			session.setAttribute("instanceExceptionMap", instanceExceptionMap);
    			resp.sendRedirect("result.jsp");
    		}
    		
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
