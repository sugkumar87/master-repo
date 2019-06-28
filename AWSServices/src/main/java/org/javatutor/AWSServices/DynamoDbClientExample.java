package org.javatutor.AWSServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

public class DynamoDbClientExample {

	public static void main(String[] args) {
		
		DynamoDbClient ddb = DynamoDbClient.create();
		
		
		// create Table in DynamoDB
		/*CreateTableRequest createTableRequest =
				CreateTableRequest.builder()
				.attributeDefinitions(AttributeDefinition.builder().attributeName("first_name")
						.attributeType(ScalarAttributeType.S).build())
				.keySchema(KeySchemaElement.builder().attributeName("first_name")
						.keyType(KeyType.HASH).build())
				.provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(new Long(10))
						.writeCapacityUnits(new Long(10)).build())
				.tableName("contact").build();

		CreateTableResponse createTableResponse = ddb.createTable(createTableRequest);
		System.out.println(createTableResponse.tableDescription().tableName());
		
		System.out.println("Table Created!!");*/
		
		
		// insert an element to the table
		/*HashMap<String, AttributeValue> item_values = new HashMap<>();
		item_values.put("first_name", AttributeValue.builder().s("Kumar").build());
		
		item_values.put("last_name", AttributeValue.builder().s("Sugandh").build());
		item_values.put("age", AttributeValue.builder().s("32").build());
		
		PutItemRequest putItemRequest = PutItemRequest.builder().tableName("contact")
				.item(item_values).build();
		
		ddb.putItem(putItemRequest);*/
		
		// retrieve an element from the table
		/*HashMap<String, AttributeValue> contactTablekey = new HashMap<>();
		contactTablekey.put("first_name", AttributeValue.builder().s("Kumar").build());
		
		GetItemRequest getItemRequest = GetItemRequest.builder().tableName("contact")
										.key(contactTablekey)
										.build();
		
		GetItemResponse getItemResponse = ddb.getItem(getItemRequest);
		Map<String, AttributeValue> items = getItemResponse.item();
		
		if(null != items){
			Set<String> keySet = items.keySet();
			for(String s : keySet) {
				System.out.println("Key :" + s + " value :" + items.get(s));
			}
		}*/
		
		// update an element in the table
		/*HashMap<String, AttributeValue> contactTablekey = new HashMap<>();
		contactTablekey.put("first_name", AttributeValue.builder().s("Kumar").build());
		
		HashMap<String, AttributeValueUpdate> attributesToUpdate = new HashMap<>();
		attributesToUpdate.put("middle_name", AttributeValueUpdate.builder().value(AttributeValue.builder().s("Gopaldas").build()).build());
		attributesToUpdate.put("last_name", AttributeValueUpdate.builder().value(AttributeValue.builder().s("SUGANDH").build()).build());
		UpdateItemRequest updateItemRequest = UpdateItemRequest.builder().tableName("contact")
												.key(contactTablekey)
												.attributeUpdates(attributesToUpdate)
												.build();
											
		UpdateItemResponse updateItemResponse = ddb.updateItem(updateItemRequest);*/
		
		// Delete an item from the table
		HashMap<String, AttributeValue> contactTablekey = new HashMap<>();
		contactTablekey.put("first_name", AttributeValue.builder().s("Kumar").build());
		DeleteItemRequest deleteItemRequest = DeleteItemRequest.builder().tableName("contact")
												.key(contactTablekey).build();
		
		ddb.deleteItem(deleteItemRequest);
		
		//Delete a Table
		DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder().tableName("contact").build();
		ddb.deleteTable(deleteTableRequest);
		System.out.println("Contact Table deleted!!");
		
	}

}
