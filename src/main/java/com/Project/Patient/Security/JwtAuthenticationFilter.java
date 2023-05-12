package com.Project.Patient.Security;
//
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
//
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
//
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	@Autowired
	private UserDetailsService userDetailsService;
//	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
//	
//	
//	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//	
//		
//		//1.Get TOken
		System.out.println(request);
		
		String requestToken=request.getHeader("Authorization");
		
		System.out.println("this is request token generated "+requestToken);
		
		String username=null;
		
		String token=null;
//		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token=requestToken.substring(7);
		try {
			username=this.jwtTokenHelper.getUserNameFromToken(token);}
			catch(IllegalArgumentException e){
				System.out.println("unable to get token");
			}catch(ExpiredJwtException e)
			{
				System.out.println("jwt Expired");
			}catch(MalformedJwtException e) {
			System.out.println("invalid token");
			}
			
		}
		
	
		else {
			System.out.println("jwttoken dosent begin with bearer");
		     }
		
//		
//		//now once we get the token we'll validate it 
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//			
//			
			UserDetails userDetails =this.userDetailsService.loadUserByUsername(username);
//			
//			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
//				//working fine
//				//so now authenticate
//				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
			System.err.println("invlaid token");
		}
		}else {
			System.out.println("username is null or context is not nulll");
		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
		filterChain.doFilter(request, response);
//		
//		
//		
//		
	}
}
