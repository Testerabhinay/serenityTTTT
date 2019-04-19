package com.studentapp.utils;

import java.util.concurrent.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ReuseableSpecifications 
{
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpecification;
	
	public static ResponseSpecBuilder resspec;
	public static ResponseSpecification responseSpecification;
	
	public static RequestSpecification getGenericRequestSpec()
	{
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestSpecification = rspec.build();
		
		return requestSpecification;
		
	}
	
	public static ResponseSpecification getGenericResponseSpec()
	{
		resspec = new ResponseSpecBuilder();
		resspec.expectHeader("Content-Type","application/json;charset=UTF-8");
		resspec.expectHeader("Transfer-Encoding","chunked");
		resspec.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
		responseSpecification = resspec.build();
		return responseSpecification;
		
	}
}
