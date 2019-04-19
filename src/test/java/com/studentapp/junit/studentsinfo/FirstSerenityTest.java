package com.studentapp.junit.studentsinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest
{
	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI = "http://localhost:8085/student";
	}
	
	@Title("This title will give the list of student information")
	@Test
	public void Getallstudents()
	{
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	//@Test
	public void ThisisFailing()
	{
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}
	
	/*@Pending
	@Test
	public void PendingTest()
	{
		
	}
	
	@Ignore
	@Test
	public void SkippedTest()
	{
		
	}
	
	@Test
	public void TestError()
	{
		System.out.println("This is an Error :"+1/0);
	}
	
	//@Test
	public void fileDoesNotExist() throws Exception
	{
		File file = new File("E:\\SerenityCourse\\Maven.txt");
		FileReader fr = new FileReader(file);
		System.out.println(fr.read());
	}
	
	
	//@net.thucydides.core.annotations.Manual
	@Test
	public void Manual()
	{
		System.out.println("Hello");
		
	}*/
	
	
	
}
