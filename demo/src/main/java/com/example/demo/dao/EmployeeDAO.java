package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Employees;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    private static List<String> globalHours = new ArrayList<String>();
    
    static 
    {
        list.getEmployeeList().add(new Employee(1, "LokeshUpdate", "Gupta", "howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
        
        globalHours.add("0.25");
        globalHours.add("0.50");
        globalHours.add("0.75");
        
        globalHours.add("5.00");
        globalHours.add("5.25");
        globalHours.add("5.50");
        globalHours.add("5.75");
        
        
        globalHours.add("1.00");
        globalHours.add("1.25");
        globalHours.add("1.50");
        globalHours.add("1.75");
        
        
        
        globalHours.add("3.00");
        globalHours.add("3.25");
        globalHours.add("3.50");
        globalHours.add("3.75");
        
        globalHours.add("4.00");
        globalHours.add("4.25");
        globalHours.add("4.50");
        globalHours.add("4.75");
        
        globalHours.add("10.00");
        globalHours.add("10.25");
        globalHours.add("10.50");
        globalHours.add("10.75");
        
        
        globalHours.add("6.00");
        globalHours.add("6.25");
        globalHours.add("6.50");
        globalHours.add("6.75");
        
        globalHours.add("12.00");
        globalHours.add("12.25");
        globalHours.add("12.50");
        globalHours.add("12.75");
        
        globalHours.add("7.00");
        globalHours.add("7.25");
        globalHours.add("7.50");
        globalHours.add("7.75");
        
        
        
        
        globalHours.add("8.00");
        globalHours.add("8.25");
        globalHours.add("8.50");
        globalHours.add("8.75");
        
        globalHours.add("9.00");
        globalHours.add("9.25");
        globalHours.add("9.50");
        globalHours.add("9.75");
        
        
        
        globalHours.add("11.00");
        globalHours.add("11.25");
        globalHours.add("11.50");
        globalHours.add("11.75");
        
        
        globalHours.add("2.00");
        globalHours.add("2.25");
        globalHours.add("2.50");
        globalHours.add("2.75");
        
        
                
    }
    
    public Employees getAllEmployees() 
    {
        return list;
    }
    
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
    
    public List<String> getHourList(Integer itemsPerPage,Integer pgNo,String sort){
    	List<String> hoursList = null;
    	try {
    		
    		hoursList = new ArrayList<String>();
    		
    		Integer startNo = ((pgNo-1)*itemsPerPage)+1;
    		Integer endNo = (pgNo*itemsPerPage);
    		
    		Integer hoursSize = globalHours.size();
    		
    		
    		if(endNo>hoursSize) {
    			endNo = hoursSize;
    		}
    		
    		if(sort.equals("asc")) {
    		Collections.sort(globalHours, new Comparator<String>(){
    			@Override
    			public int compare(final String o1, String o2) {
    			String hoursValue1 = o1;
    			String hoursValue2 = o2;
    			if (hoursValue1 != null && hoursValue2 != null) {
    			double d1 = Double.valueOf(hoursValue1.substring(hoursValue1.lastIndexOf(' ') + 1));
    			double d2 = Double.valueOf(hoursValue2.substring(hoursValue2.lastIndexOf(' ') + 1));
    			return Double.compare(d1, d2);
    			} else {
    			return -1;
    			}
    			}
    			});
    		}else {
    			Collections.sort(globalHours,  Collections.reverseOrder(new Comparator<String>(){
    				@Override
    				public int compare(final String o1, String o2) {
    				String hoursValue1 = o1;
    				String hoursValue2 = o2;
    				if (hoursValue1 != null && hoursValue2 != null) {
    				double d1 = Double.valueOf(hoursValue1.substring(hoursValue1.lastIndexOf(' ') + 1));
    				double d2 = Double.valueOf(hoursValue2.substring(hoursValue2.lastIndexOf(' ') + 1));
    				return Double.compare(d1, d2);
    				} else {
    				return -1;
    				}
    				}
    				}));
    		}
    		
    		
    		int counter=0;
    		for(String obj : globalHours) {
    			counter++;
    			if(counter>=startNo && counter<=endNo) {
    				hoursList.add(obj);
    			}
    		}
    		
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	return hoursList;
    }
}
