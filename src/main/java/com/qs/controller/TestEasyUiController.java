package com.qs.controller;

import com.qs.entity.Student;
import com.qs.util.DataResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/easyui")
public class TestEasyUiController {
    @RequestMapping(value = "/test")
    public String testDataGrid() {
        return "/easy/test";
    }

    @RequestMapping(value = "/test2")
    public String testDataGridForAjax() {
        return "/easy/test2";
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public DataResult<Student> getUserJson() {
        Student student = new Student("123", "zhangsan");
        Student student2 = new Student("1234", "lisi");

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student2);

        DataResult<Student> data = new DataResult<Student>();
        data.setRows(students);
        data.setTotal(students.size());
        return data;
    }

}
