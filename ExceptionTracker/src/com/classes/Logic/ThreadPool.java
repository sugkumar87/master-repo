package com.classes.Logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {
	
	ExecutorService executorService;
	Map<String,  Future<Map<String, Long>>> instanceFutureMap = new HashMap<>();
	Map<String, Map<String, Long>> instanceExceptionMap = new HashMap<>(); 
	
	
	public Map<String, Map<String, Long>> submitJobs(List<LogFileReader> instanceList) throws InterruptedException, ExecutionException{
		
		executorService = Executors.newFixedThreadPool(instanceList.size());
		
		for(LogFileReader instance : instanceList){
			instanceFutureMap.put(instance.getInstanceName(), executorService.submit(instance));
			//instanceExceptionMap.put(instance.getInstanceName(), executorService.submit(instance).get());
		}
		
		Set<String> instanceSet = instanceFutureMap.keySet();
		
		for(String instance : instanceSet){
			while(!instanceFutureMap.get(instance).isDone()){
				Thread.sleep(3000);
			}
			instanceExceptionMap.put(instance, instanceFutureMap.get(instance).get());
		}
		
		executorService.shutdown();
		
		return instanceExceptionMap;
		
	  /*Set<Entry<String, Map<String, Long>>> entrySet = instanceExceptionMap.entrySet();
		
	  for(Entry<String, Map<String, Long>> entry : entrySet){
		  System.out.println("Instance name : " + entry.getKey());
		  System.out.println("Exceptions in the logs : ");
		  Set<Entry<String, Long>> exceptionSet =  entry.getValue().entrySet();
		  for(Entry<String, Long> exceptionEntry : exceptionSet){
			  System.out.println("Exception :"+  exceptionEntry.getKey() + " Occurence :" + exceptionEntry.getValue());
		  }
		  
	  }*/
	  
	  
	}
	
}
