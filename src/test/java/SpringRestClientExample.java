

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.springrest.model.Employee;

public class SpringRestClientExample {
	
	private static final String URL="http://localhost:8080/SpringRestExample/employees/"; 

	public static void main(String[] args) {
		getEmployee();
		updateEmployee();
	}
	
	public static void getEmployee(){
		RestTemplate restClient=new RestTemplate();		
		String result=restClient.getForObject(URL+"1", String.class);
		System.out.println("The Name>>>>>>"+result);
	}
	
	public static void createEmployee(){
			
		
		Employee emp=new Employee(5, "dileep", 34);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);

		restTemplate.postForObject(URL, request, Employee.class);

		
		
		
	}
	
	
public static void updateEmployee(){
			
		
		Employee emp=new Employee(1, "dileep", 34);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);

		restTemplate.put(URL+"1", emp);

		
		
		
	}
	

}
