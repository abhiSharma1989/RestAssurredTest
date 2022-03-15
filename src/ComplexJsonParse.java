import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// Code to find the course
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the First Course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//Print All course titles and their respective Prices
		for(int i = 0; i<count; i++) {
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
		}
		
		//Print Number of copies sold by RPA Course
		for(int i = 0; i<count; i++) {
			String courseTitles = js.get("courses["+i+"].title");
			if(courseTitles.equals("Appium"))
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		

	}

}
