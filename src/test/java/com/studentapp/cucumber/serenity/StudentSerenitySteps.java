package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class StudentSerenitySteps 
{
	@Step("Creating student with firstName: {0},lastName:{1}, email:{2}, courses:{3}")
	public ValidatableResponse createStudent(String firstName,String lastName,String programme,String email, 
			List<String> courses)
	{
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
	}
	
	@Step("Getting the student information with firstName :{0}")
	public HashMap<String,Object> getStudentInfoByFirstName(String firstName)
	{
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		return SerenityRest.rest().given()
				.when()
				.get("/list")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path(p1+firstName+p2);
				//.path("findAll{it.firstName==''}.get(0)");
	}
	
	
	
	@Step("Updating student with studentID:{0} firstName:{1},lastName:{2},email:{3},programme :{4},courses :{5}")
	public ValidatableResponse updateStudent(int studentid, String firstName, String lastName, String email, 
			String programme, List<String> courses)
	{
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
				
		
		return SerenityRest.rest().given()
				.spec(ReuseableSpecifications.getGenericRequestSpec()).log().all()
				.when()
				.body(student)
				.put("/"+studentid)
				.then();
	}
	
	@Step("Deleting student information with ID : {0}")
	public void deleteStudent(int studentID)
	{
		SerenityRest.rest().given()
		.when()
		.delete("/"+studentID);
		
	}
	
	@Step("Getting information of the student with ID :{0}")
	public ValidatableResponse getStudentById(int studentID)
	{
		return SerenityRest.rest().given().when().get("/"+studentID).then();
		
	}
	
	
	
	
	
	
}
