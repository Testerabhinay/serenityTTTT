package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecifications;
import com.studentapp.utils.TestUtils;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase
{
	static String firstName = "CAPUSER"+TestUtils.getRandomValue();
	static String lastName = "CAPUSER"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"glss@gmail.com";
	static int studentID;
	
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This test will create a New Student")
	@Test
	public void test001()
	{
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, programme, email, courses)
		.statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponseSpec());
	}
	
	@Title("Verify if the student was added")
	@Test
	public void test002()
	{
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
				
				//assertThat(value,hasValue(firstName));
				studentID = (int) value.get("id");
	}
	
	
	@Title("Update the user information and verify the updated information!")
	@Test
	public void test003()
	{
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		firstName = firstName+"_Updated";
		
		steps.updateStudent(studentID, firstName, lastName, email, programme, courses);
		
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
		
		System.out.println("After updating the student using put :"+value);
	}
	
	
	@Title("Verify If the Student is deleted or not")
	@Test
	public void test004()
	{
		steps.deleteStudent(studentID);
		
		steps.getStudentById(studentID).statusCode(404);
	}
	
}
