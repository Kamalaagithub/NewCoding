

import java.util.*;
import java.util.stream.Collectors;

public class MainEmployee {
    
    public static void main(String[] args){

        List<String> citiesWorkedIn1 = new ArrayList<String>();
        citiesWorkedIn1.add("Pune");
        citiesWorkedIn1.add("Mumbai");
        citiesWorkedIn1.add("Noida");
        citiesWorkedIn1.add("Bangalore");

        EmployeeList e1 = new EmployeeList(1,"Code",citiesWorkedIn1 );
        
        List<String> citiesWorkedIn2 = new ArrayList<String>();
        citiesWorkedIn2.add("Pune");
        citiesWorkedIn2.add("Mumbai");
        citiesWorkedIn2.add("Nagpur");
        citiesWorkedIn2.add("Chennai");

        EmployeeList e2 = new EmployeeList(2,"Decode",citiesWorkedIn2 );

        List<String> citiesWorkedIn3 = new ArrayList<String>();
        citiesWorkedIn3.add("Pune");
        citiesWorkedIn3.add("Mumbai");
        citiesWorkedIn3.add("Coimbatore");
        citiesWorkedIn3.add("Gujarat");

        EmployeeList e3 = new EmployeeList(3,"Barcode",citiesWorkedIn3);

        List<EmployeeList> employeeList = new ArrayList<EmployeeList>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        System.out.println(employeeList);

        /*List<Integer> ids = new ArrayList<Integer>();

        for(EmployeeList employee:employeeList)
        {
            ids.add(employee.getId());
        }
        System.out.println(ids);
        */

        List<Integer> ids = employeeList.stream().map(emp -> emp.getId()).collect(Collectors.toList());

        System.out.println(ids);
        
    }
}
