package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.EmployeeDAO;
import com.hspedu.mhl.domain.Employee;

//业务层，该类完成employee表的各种操作
public class EmployeeService {
    //定义一个employeedao
        private EmployeeDAO employeeDAO = new EmployeeDAO();
        //方法，根据empid和pwd返回一个employeebyid对象
    public Employee getEmployeeByIdAndPwd(String empId,String pwd){
        Employee employee =
                employeeDAO.querySingle("select * from employee where empId=? and pwd =?", Employee.class, empId, pwd);
        return employee;
    }
}
