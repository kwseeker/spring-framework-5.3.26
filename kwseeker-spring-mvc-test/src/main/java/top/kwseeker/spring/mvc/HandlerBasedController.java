package top.kwseeker.spring.mvc;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HandlerBasedController implements HttpRequestHandler {

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Request HandlerBasedController");
		request.getRequestDispatcher("/WEB-INF/views/success3.jsp").forward(request, response);
	}
}