package com.classes.Logic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public final class LogFileReader_old implements Callable<Map<String, Long>>{
	
	private static String username;
	private static String password;
	private static int port;
	private static String logFileDateStr;
	
	String instanceName;
	String serverIp;
	String folderPath;
	

	public LogFileReader_old(String instanceName, String serverIp, String folderPath){
		this.instanceName = instanceName;
		this.serverIp = serverIp;
		this.folderPath = folderPath;
		
	}
	
	
	LogFileReader_old() {
		
	}
	
	public Map<String, Long> readLogs() throws JSchException, SftpException, IOException{
		Session session = null;
		ChannelSftp sftp = null;
		Map<String, Long> exceptionMap = new HashMap<>();
		String logFileName = null;
		
		System.out.println("Starting reading logs from instnance :"+ instanceName + " Time :" +  Calendar.getInstance().getTime());
		
		try{
		 	JSch jSch = new JSch();
	        session = jSch.getSession(username, serverIp, port);
	        session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        
	        
	        
	        sftp = (ChannelSftp) session.openChannel("sftp");
	     
	        
	        sftp.connect();
	        
	        sftp.cd(folderPath);
	        Vector fileList =  sftp.ls(folderPath);
	        
	        for(Object  s : fileList){
	        	String[] temp = s.toString().split(" ");
	        	if(temp[temp.length - 1].contains(logFileDateStr)){
	        		//System.out.println(temp[4]);
	        		logFileName = temp[temp.length - 1];
	        		break;
	        	}
	        }
	        
	       
	        InputStream stream = sftp.get(logFileName);
	        BufferedInputStream gzipStream = null;
	        if(logFileName.contains(".gz")){
	        	 gzipStream = new BufferedInputStream(new GZIPInputStream(stream));
	        }else{
	        	 gzipStream = new BufferedInputStream(stream);
	        }
	        
	        Reader decoder = new InputStreamReader(gzipStream);
	        BufferedReader reader = new BufferedReader(decoder);
	       
	        
	        String str =null;
	        long lineNo = 0;
	        while ((str=reader.readLine()) != null){
				lineNo++;
				if(str.contains("ERROR") && str.contains("Exception")){
					
					//System.out.println(str);
					/*String[] words = str.split("[STDERR] ");
					words[1] = words[1].replace("[STDERR] ", "");*/
					StringBuilder exceptionMsg = new StringBuilder("");
					boolean exceptionStrFound = false;
					String[] words = str.split(" ");
					for(String s : words){
						if(exceptionStrFound){
							exceptionMsg.append(" ").append(s);
							continue;
						}
						if(s.contains("Exception")){
							exceptionMsg.append(s);
							exceptionStrFound = true;
						}
					}
					//System.out.println(exceptionMsg);
					
					Long  value = exceptionMap.get(exceptionMsg.toString());
					if(value == null){
						 value= new Long(1);
						exceptionMap.put(exceptionMsg.toString(), value);
					}else{
						exceptionMap.put(exceptionMsg.toString(), value+1);
					}
				}
					
					//String[] words = str.split("Exception");
					//words[1] = words[1].replace("[STDERR] ", "");
					
					
					//String exceptionMsg = str.substring(pos+1);
					/*if(null != exceptionMsg &&  exceptionMsg.contains("Exception")){
							
					}*/
					
				//}
			}
			
			//System.out.println(exceptionMap);
	        
		}
		finally{
			if(null != sftp) sftp.disconnect();
			if(null != session) session.disconnect();
			
		}
		
		System.out.println("Done with reading logs from instnance :"+ instanceName + " Time :" + Calendar.getInstance().getTime());
		
		return exceptionMap;
	        
	        /*InputStream stream = sftp.get(ftp_src_file);
	        
	        InputStream gzipStream = new GZIPInputStream(stream);
	        Reader decoder = new InputStreamReader(gzipStream);
	        BufferedReader reader = new BufferedReader(decoder);*/
	        
	                
	        //BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	        
	        /*String str =null;
	        while ((str=reader.readLine()) != null){
	        	System.out.println(str);
	        }*/
	}

	@Override
	public Map<String, Long> call() throws Exception {
		return this.readLogs();
	}


	public static void setUsername(String username) {
		LogFileReader_old.username = username;
	}


	public static void setPassword(String password) {
		LogFileReader_old.password = password;
	}


	public static void setPort(int port) {
		LogFileReader_old.port = port;
	}


	public static void setLogFileDateStr(String logFileDateStr) {
		LogFileReader_old.logFileDateStr = logFileDateStr;
	}


	public String getInstanceName() {
		return instanceName;
	}
	
}
