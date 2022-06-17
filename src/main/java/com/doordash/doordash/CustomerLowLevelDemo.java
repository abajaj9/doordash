package com.doordash.doordash;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

import java.util.HashMap;

public class CustomerLowLevelDemo {

    public static void main(String[] args) {
    //public void connectDynamodb() {
        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

      /*  AmazonDynamoDB client3 = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-1"))
                .build();
*/
        AmazonDynamoDB client3 = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", "us-east-1"))
                .build();

        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("customerId", new AttributeValue().withS("c10002"));
        //key.put("field2", new AttributeValue().withS("value"));

        GetItemRequest request = new GetItemRequest()
                .withTableName("customer")
                .withKey(key);

        try {
            GetItemResult result = client3.getItem(request);
            if (result.getItem() != null) {
                AttributeValue email = result.getItem().get("email");
                System.out.println("email " + email.getS());
            }
        } catch (Exception e) {
            System.err.println("Unable to retrieve data: ");
            System.err.println(e.getMessage());
        }
    }
}
