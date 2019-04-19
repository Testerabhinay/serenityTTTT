package reference;

import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CopyOfStudentsCRUDTest extends TestBase
{
	static String firstName = "CAPUSER"+TestUtils.getRandomValue();
	static String lastName = "CAPUSER"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"glss@gmail.com";
	static int studentID;
	
	@Title("This test will create a New Student")
	@Test
	public void test001()
	{
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.post("")
		.then()
		.log()
		.all()
		.statusCode(201);
	}
	
	@Title("Verify if the student was added")
	@Test
	public void test002()
	{
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		HashMap<String,Object> value = SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+firstName+p2);
		//.path("findAll{it.firstName==''}.get(0)");
		System.out.println("FirstName variable value :"+firstName);
		System.out.println("p1 value :"+p1);
		System.out.println("p2 value :"+p2);
		System.out.println("The Value is: "+value);
		
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
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.put("/"+studentID)
		.then()
		.log()
		.all();
		
		
		HashMap<String,Object> value = SerenityRest.rest().given()
				.when()
				.get("/list")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path(p1+firstName+p2);
		
		System.out.println("After updating the student using put :"+value);
		
		
	}
	
	
	@Title("Verify If the Student is deleted or not")
	@Test
	public void test004()
	{
		SerenityRest.rest().given()
		.when()
		.delete("/"+studentID);
		
		SerenityRest.rest().given()
		.when()
		.get("/" + studentID)
		.then()
		.log()
		.all()
		.statusCode(404);
	}
	
}
