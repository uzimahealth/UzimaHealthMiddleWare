package ke.co.creswave.uzimahealth.auth;

import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import ke.co.creswave.uzimahealth.model.AccountCredentials;
import ke.co.creswave.uzimahealth.utils.Utilities;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private AccountCredentials accountCredentials;
	
	public AccountCredentials getAccountCredentials() {
		return accountCredentials;
	}

	public void setAccountCredentials(AccountCredentials accountCredentials) {
		this.accountCredentials = accountCredentials;
	}

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AccountCredentials accountCredentials) {
		this.authenticationManager = authenticationManager;
		this.accountCredentials = accountCredentials;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
			creds.setUserName(Utilities.formatNumber(creds.getUserName()));
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
					creds.getPassword(),new ArrayList<GrantedAuthority>()));
		} catch (IOException e) {
			e.printStackTrace();
			res.addHeader("Content-Type", "application/json");
			PrintWriter out;
			try {
				out = res.getWriter();
				InvalidInput invalidInput = new InvalidInput();
				invalidInput.setError("Invalid Input Error");
				invalidInput.setPath(req.getRequestURI());
				invalidInput.setMessage("username, password and accountType should be sent as json");
				invalidInput.setStatus("failed");
				Gson gson = new Gson();
				out.print(gson.toJson(invalidInput));
				out.flush();
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		UzimaHealthUser user = (UzimaHealthUser) auth.getPrincipal();

		String token = Jwts.builder().setSubject(((UzimaHealthUser) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.claim("userId", user.getUserId()).claim("userType", user.getUserType()).claim("admin", user.getAdmin())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes()).compact();
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("Content-Type", "application/json");

		PrintWriter out;
		out = res.getWriter();
		ValidToken validToken = new ValidToken();
		validToken.setToken(token);
		validToken.setStatus("Ok");
		validToken.setRights(user.getRights());
		validToken.setExpiresAfter(SecurityConstants.EXPIRATION_TIME + "");
		Gson gson = new Gson();
		out.print(gson.toJson(validToken));
		out.flush();
		out.close();

	}
}