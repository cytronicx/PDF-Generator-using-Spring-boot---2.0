package com.example.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.util.PdfGenerator;



@RestController
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/export-to-pdf")
    public void generatePdfFile(@RequestParam("userId") int userId,HttpServletResponse response) throws IOException 
    {
    	 Student student = studentService.getStudentById(userId);
              if (student != null) 
                {
                    pdfGenerator.generate(student);
                    return; // PDF generated, exit the method
                }
            
        

        // Handle the case when the student is not found or div content is invalid
        response.getWriter().println("Unable to generate PDF. Invalid user input or student not found.");
    }
   
        
    }
  
        

    
