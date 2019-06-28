package org.javatutor.AWSServices;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;
import software.amazon.awssdk.services.ec2.model.StartInstancesRequest;
import software.amazon.awssdk.services.ec2.model.StopInstancesRequest;

public class EC2ClientExample {

	public static void main(String[] args) {
		
		String ami_id = "ami-0889b8a448de4fc44";
		
		Ec2Client ec2Client = Ec2Client.create();
		
		
		// create and run an instance
		RunInstancesRequest runInstancesRequest = RunInstancesRequest.builder()
			    .imageId(ami_id)
			    .instanceType(InstanceType.T2_MICRO)
			    .maxCount(1)
			    .minCount(1)
			    .build();
		
		RunInstancesResponse instances = ec2Client.runInstances(runInstancesRequest);
		
		String instanceId = instances.reservationId(); // i-0efdcd41276a2520f
		System.out.println("Ec2 instance started successfully with Id :" + instanceId);

		//String instanceId = "i-0efdcd41276a2520f";
		
		// Stopping an ec2 instance
		/*ec2Client.stopInstances(StopInstancesRequest.builder().instanceIds(instanceId).build());
		System.out.println("Ec2 instance stoped successfully!!!!");*/

		// Starting an ec2 instance
		/*ec2Client.startInstances(StartInstancesRequest.builder().instanceIds(instanceId).build());
		System.out.println("Ec2 instance started successfully!!!!");*/
		
		
	}
	
}
