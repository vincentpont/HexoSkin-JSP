   package controller;  
import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
    
    
    public class ControllerServlet extends HttpServlet {  
    	
    	
        protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
        	

            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            String name=request.getParameter("name");  
            String password=request.getParameter("password");  
            
            
              
            
        }  
      
        @Override  
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
                throws ServletException, IOException {  
            doPost(req, resp);  
        }  
        
    }  