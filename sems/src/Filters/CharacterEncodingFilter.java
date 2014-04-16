package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharacterEncodingFilter implements Filter {
	FilterConfig config;
	final String defaultCharSet = "UTF-8";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain next) throws IOException, ServletException {
		
		String charset = config.getInitParameter("charset");
		if(config != null){
			request.setCharacterEncoding(charset);
		}else{
			request.setCharacterEncoding(defaultCharSet);
		}	
	
		next.doFilter(request, response);

	}

	  @Override
	public void init(FilterConfig config) throws ServletException {
	  	this.config = config;
	  }

}
