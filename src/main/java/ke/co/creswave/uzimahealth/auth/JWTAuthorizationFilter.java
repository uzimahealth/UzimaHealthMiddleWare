package ke.co.creswave.uzimahealth.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.google.gson.Gson;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		StringBuffer logString = new StringBuffer("Authorization Request:");
		String remoteAddress = req.getRemoteAddr();
		logString.append(remoteAddress + " ");
		StringBuffer out = new StringBuffer();
		Enumeration names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Enumeration values = req.getHeaders(name); // support multiple
														// values
			if (values != null) {
				while (values.hasMoreElements()) {
					String value = (String) values.nextElement();
					out.append("\n" + name + ": " + value);
				}
			}
		}

		logString.append(out.toString());
		log.debug("header info: " + logString.toString());

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			try {
				String user = Jwts.parser().setSigningKey(SecurityConstants.SECRET.getBytes())
						.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();

				Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET.getBytes())
						.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
				int userId = (Integer) claims.getBody().get("userId");
				int userType = (Integer) claims.getBody().get("userType");
				int admin = (Integer) claims.getBody().get("admin");

				req.setAttribute("userId", userId);
				req.setAttribute("userType", userType);
				req.setAttribute("admin", admin);

				UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(req, res);
			} catch (MalformedJwtException ex) {
				this.onUnsuccessfulAuthentication(req, res, new AuthenticationCredentialsNotFoundException(""));
			} catch (ExpiredJwtException ex) {
				this.onTokenExpired(req, res, ex);
			} catch (Exception ex) {
				ex.printStackTrace();
				this.onUnsuccessfulAuthentication(req, res, new AuthenticationCredentialsNotFoundException(""));
			}

		}

	}

	public void onTokenExpired(HttpServletRequest req, HttpServletResponse res, ExpiredJwtException ex) {
		try {
			res.setHeader("Content-Type", "application/json");
			PrintWriter out;
			out = res.getWriter();
			InvalidToken invalidToken = new InvalidToken();
			invalidToken.setError("Unauthorized");
			invalidToken.setMessage("Token expired");
			invalidToken.setStatus("401");
			invalidToken.setPath(req.getRequestURI());
			Gson gson = new Gson();
			out.print(gson.toJson(invalidToken));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUnsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException auth) {
		try {
			super.onUnsuccessfulAuthentication(req, res, auth);
			res.setHeader("Content-Type", "application/json");
			PrintWriter out;
			out = res.getWriter();
			InvalidToken invalidToken = new InvalidToken();
			invalidToken.setError("Unauthorized");
			invalidToken.setMessage("Invalid Token");
			invalidToken.setStatus("401");
			invalidToken.setPath(req.getRequestURI());
			Gson gson = new Gson();
			out.print(gson.toJson(invalidToken));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			try {
				String user = Jwts.parser().setSigningKey(SecurityConstants.SECRET.getBytes())
						.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();

				if (user != null) {

					return new UsernamePasswordAuthenticationToken(user, null,
							(Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
				}
			} catch (MalformedJwtException ex) {
				throw new MalformedJwtException("Invalid token");
			}
			return null;
		}
		return null;
	}
}