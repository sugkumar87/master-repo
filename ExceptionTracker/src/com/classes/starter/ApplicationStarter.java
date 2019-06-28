package com.classes.starter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.classes.Logic.LogFileReader;
import com.classes.Logic.ThreadPool;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class ApplicationStarter {

	static Properties bConnectedProperties;
	
	static{
		bConnectedProperties = new Properties();
		try {
			bConnectedProperties.load(new FileInputStream("src/bConnectedInstance.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) { 
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yesturday = Calendar.getInstance();
    	yesturday.add(Calendar.DATE, -1);
    	String logFileDateStr = format.format(yesturday.getTime());
    	
    	LogFileReader.setUsername(bConnectedProperties.getProperty("username"));
    	LogFileReader.setPassword(bConnectedProperties.getProperty("password"));
    	LogFileReader.setPort(Integer.parseInt(bConnectedProperties.getProperty("default_port")));
    	LogFileReader.setLogFileDateStr(logFileDateStr);
		 
    	List<LogFileReader> instanceList = new ArrayList<>();
    	
    	LogFileReader CIGNA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cigna"), bConnectedProperties.getProperty("instance_ip_cigna"), bConnectedProperties.getProperty("logfilepath_cigna"));
    	LogFileReader ALMS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_alms"), bConnectedProperties.getProperty("instance_ip_alms"), bConnectedProperties.getProperty("logfilepath_alms"));
    	LogFileReader HIPNY_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hipny"), bConnectedProperties.getProperty("instance_ip_hipny"), bConnectedProperties.getProperty("logfilepath_hipny"));
    	LogFileReader CEDARS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_cedars"), bConnectedProperties.getProperty("instance_ip_cedars"), bConnectedProperties.getProperty("logfilepath_cedars"));
    	LogFileReader KSG_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ksg"), bConnectedProperties.getProperty("instance_ip_ksg"), bConnectedProperties.getProperty("logfilepath_ksg"));
    	LogFileReader HNHRA_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_hnhra"), bConnectedProperties.getProperty("instance_ip_hnhra"), bConnectedProperties.getProperty("logfilepath_hnhra"));
    	LogFileReader IBCHCR_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ibchcr"), bConnectedProperties.getProperty("instance_ip_ibchcr"), bConnectedProperties.getProperty("logfilepath_ibchcr"));
    	LogFileReader OHCS_FileReader = new LogFileReader(bConnectedProperties.getProperty("instance_name_ohcs"), bConnectedProperties.getProperty("instance_ip_ohcs"), bConnectedProperties.getProperty("logfilepath_ohcs"));
    	
    	instanceList.add(CIGNA_FileReader);
    	instanceList.add(ALMS_FileReader);
    	instanceList.add(HIPNY_FileReader);
    	instanceList.add(CEDARS_FileReader);
    	instanceList.add(KSG_FileReader);
    	instanceList.add(HNHRA_FileReader);
    	instanceList.add(IBCHCR_FileReader);
    	instanceList.add(OHCS_FileReader);
    	
    	ThreadPool threadPool = new ThreadPool();
    	
    	try {
			threadPool.submitJobs(instanceList);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	
	}

}
