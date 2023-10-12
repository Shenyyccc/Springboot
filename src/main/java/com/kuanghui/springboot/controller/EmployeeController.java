package com.kuanghui.springboot.controller;

import com.kuanghui.springboot.dao.DepartmentDao;
import com.kuanghui.springboot.dao.EmployeeDao;
import com.kuanghui.springboot.pojo.Department;
import com.kuanghui.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/employees")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employees",employees);
        return "emp/list";
    }

    @GetMapping("/toAdd")
    public String toAddpage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departs",departments);
        return "emp/add";
    }

    @PostMapping("/employees")
    public String addEmlpy(Employee employee){
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        System.out.println("===>addEmployee:"+employee);
        employeeDao.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/toModify/{id}")
    public String toUpdateEmp(Model model, @PathVariable("id")String id){
        Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
        Collection<Department> departments = departmentDao.getDepartments();
        Department department = employee.getDepartment();
        model.addAttribute("employ",employee);
        model.addAttribute("departs",departments);
        return "emp/update";
    }

    @RequestMapping("/updateEmp")
    public String updateEmp(Employee employee){
         employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
         employeeDao.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/toDelete/{id}")
    public String toDeleteEmp(@PathVariable("id")int id){
        employeeDao.removeEmployee(id);
        return "redirect:/employees";
    }

}
