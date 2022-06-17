package com.doordash.doordash;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CustomerController {

    @GetMapping(path = "/dynamoDb/getCustomerInfo/{customerId}")
    public String getCustomer(@PathVariable String customerId) {
    //public void connectDynamodb() {
        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        //System.out.println(customerId);

        String email1 = null;

      /*  AmazonDynamoDB client3 = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-east-1"))
                .build();
*/
        AmazonDynamoDB client3 = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", "us-east-1"))
                .build();
        System.out.println("test2");

        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("customerId", new AttributeValue().withS(customerId));
        //key.put("customerId", new AttributeValue().withS("c10002"));
        //key.put("SongTitle", new AttributeValue().withS("Call Me Today"));

        GetItemRequest request = new GetItemRequest()
                .withTableName("customer")
                .withKey(key);

        try {
            GetItemResult result = client3.getItem(request);
            if (result.getItem() != null) {
                AttributeValue email = result.getItem().get("email");
                AttributeValue firstName = result.getItem().get("email");
                AttributeValue lastName = result.getItem().get("email");

                //email1 = email.getS();
                //System.out.println("email " + email.getS());

                Customer customer = new Customer(customerId,email.getS(), firstName.getS(), lastName.getS() );
                        return customer.toString();

            }
        } catch (Exception e) {
            System.err.println("Unable to retrieve data: ");
            System.err.println(e.getMessage());
        }
        return "Customer Not Found";
    }
}
