package com.ehsn.web.autocomplete.fighting;

import com.ehsn.autocomplete.fighting.ECEntity;
import com.ehsn.autocomplete.fighting.EntityFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/autocomplete")
public class AutoComplete  extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String entity=request.getParameter("entity");
        String query = request.getParameter("query");
        System.out.println("test");
        System.out.println("entity:"+entity);
        System.out.println("query:"+query);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        ECEntity ecEntity = EntityFactory.getInstance().lookupEntity(entity);
        PrintWriter writer = response.getWriter();



        if(entity!=null) {
            try {
                List<String> result = ecEntity.query(query);


                writer.write("<ul>");


                for(String str:result) {
                    writer.write("<li>"+str+"</li>");
                }



                writer.write("</ul>");
                writer.flush();
            }
            catch(Exception err) {
                err.printStackTrace();
            }

        }


    }
}
