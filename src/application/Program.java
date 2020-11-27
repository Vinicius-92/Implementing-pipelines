package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		List<Employee> emp = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = scanner.next();
		System.out.print("Enter salary: u$");
		double salary = scanner.nextDouble();
		System.out.print("Enter the initial letter of the name you want the sum of salarys: ");
		char letter = scanner.next().toUpperCase().charAt(0);
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			
			while(line != null) {
				String[] fields = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.println("Email of people whose salary is more than U$" + String.format("%.2f", salary) + ":");
			List<String> emails = emp.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			emails.forEach(System.out::println);
				
			Double salaryM = emp.stream()
					.filter(p -> p.getName().charAt(0) == letter)
					.map(p -> p.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with " + letter + ": U$"  + String.format("%.2f", salaryM));						
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		scanner.close();
	}
}
