package com.kuanghui.springboot.dao;

import com.kuanghui.springboot.pojo.Department;
import com.kuanghui.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees;
    @Autowired
    DepartmentDao departmentDao;
    static {
        employees=new HashMap<>();
        employees.put(1001,new Employee(1001,"AA","1122@QQ.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"BB","1122@11.com",0,new Department(101,"教学部")));
        employees.put(1003,new Employee(1003,"CC","1122@11.com",1,new Department(101,"教学部")));
        employees.put(1004,new Employee(1004,"DD","1122@11.com",1,new Department(101,"教学部")));
        employees.put(1005,new Employee(1005,"EE","1122@11.com",0,new Department(101,"教学部")));
    }

    //逐渐自增
    private static Integer initId=1006;
    public void addEmployee(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getEmployees(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void removeEmployee(Integer id){
        employees.remove(id);
    }


}
