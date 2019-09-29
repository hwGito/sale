package com.sale.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sale.utils.TokenUtil;

import net.sf.json.JSONObject;

public class TokenFilter implements Filter{

	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequestuest, ServletResponse servletResponseesponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequestuest;
		HttpServletResponse response = (HttpServletResponse) servletResponseesponse;
		String token = request.getParameter("token");
		String uid = request.getParameter("uid");
		JSONObject rs = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//System.out.println("token拦截请求: " + request.getServletPath()+"|IP:"+request.getRemoteAddr());
		
		if(token==null || uid == null) {
			log.warn("[token拦截请求] " + request.getServletPath()+"  [IP] "+request.getRemoteAddr());
			PrintWriter out = response.getWriter();
			rs.put("code", 999);
			rs.put("message", "没有登录或登录失效");
			rs.put("data", "");
			out.write(rs.toString());
			out.flush();
			out.close();
		}else {
			if(TokenUtil.check(uid, token)) {
				chain.doFilter(servletRequestuest, servletResponseesponse);
			}else {
				log.warn("token拦截请求 => [接口]" + request.getServletPath()+"  [IP] "+request.getRemoteAddr());
				PrintWriter out = response.getWriter();
				rs.put("code", 999);
				rs.put("message", "没有登录或登录失效");
				rs.put("data", "");
				out.write(rs.toString());
				out.flush();
				out.close();
			}
		}
//		chain.doFilter(servletRequestuest, servletResponseesponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
