package org.javatutor.AWSServices;

import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.RdsClientBuilder;
import software.amazon.awssdk.services.rds.model.RdsRequest;

public class RDSClientExample {

	public static void main(String[] args) {
		System.out.println("Hello World!!!");
		
		RdsClient rdsClient = RdsClient.create();
		
		
	}

}
