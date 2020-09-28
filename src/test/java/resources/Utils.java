package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public RequestSpecification RequestSpecification() throws IOException {
	    PrintStream logFile = new PrintStream("log.txt");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalProps("baseURI"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(logFile))
				.addFilter(ResponseLoggingFilter.logResponseTo(logFile))
				.setContentType(ContentType.JSON).build();
		
		return req;
	}
	
	public static String getGlobalProps(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\triver\\eclipse-workspace\\ApiFramework\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);
	    String value = prop.getProperty(key);
	    return value;
	}

}
