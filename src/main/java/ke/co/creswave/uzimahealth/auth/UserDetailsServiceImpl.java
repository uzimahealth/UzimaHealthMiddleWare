package ke.co.creswave.uzimahealth.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ke.co.creswave.uzimahealth.model.AccountCredentials;
import ke.co.creswave.uzimahealth.repository.AccountCredentialsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountCredentialsRepository accountCredentialsRepository;

	public UzimaHealthUser loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountCredentials user = accountCredentialsRepository.findByUserName(username);
		if (user != null) {
			UzimaHealthUser user2 = new UzimaHealthUser(user.getUserName(), user.getPassword(),
					new ArrayList<GrantedAuthority>());
			user2.setUserId(user.getId());
			return user2;
		} else
			throw new UsernameNotFoundException(username);
	}

}
