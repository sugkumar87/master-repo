package org.javatutor.AWSServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ClientExample {
	
	public static void main(String args[]) {
		
		Region region = Region.AP_SOUTH_1;
		S3Client s3 = S3Client.builder().region(region).build(); 
		
		// create bucket
		String bucket = "bucket" + System.currentTimeMillis();
		CreateBucketRequest createBucketRequest = CreateBucketRequest
		        .builder()
		        .bucket(bucket)
		        .createBucketConfiguration(CreateBucketConfiguration.builder()
		                                                            .locationConstraint(region.id())
		                                                            .build())
		        .build();
		s3.createBucket(createBucketRequest);
		
		
		// list buckets
		ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
		ListBucketsResponse listBucketsResponse =  s3.listBuckets(listBucketsRequest);
		listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
		
		
		// put an object to a bucket
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucket).key("Object1").build();
		try {
			s3.putObject(putObjectRequest, RequestBody.fromFile(createSampleFile()));
		} catch (AwsServiceException | SdkClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get an object from a bucket
		s3.getObject(GetObjectRequest.builder().bucket(bucket).key("Object1").build(),
						ResponseTransformer.toFile(Paths.get("TestFile")));
				
		
		
		//String bucket = "bucket1554715685371";
		
		// Delete an object from a bucket
		DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucket).key("Object1").build();
		s3.deleteObject(deleteObjectRequest);
		
		// Delete bucket
		DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucket).build();
		DeleteBucketResponse deleteBucketResponse = s3.deleteBucket(deleteBucketRequest);
		System.out.println(deleteBucketResponse.responseMetadata().toString());
		
	}
	
	

	/*private static void readFile(ResponseInputStream<GetObjectResponse> responseInputStream) {
	
		
		System.out.println(responseInputStream);
		
	}*/



	private static File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.close();

        return file;
    }

}
