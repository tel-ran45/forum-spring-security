package telran.java45.security;

import java.time.LocalDate;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	final UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
		String[] roles = userAccount.getRoles().stream()
							.map(r -> "ROLE_" + r)
							.toArray(String[]::new);
		boolean passwordNonExpired = userAccount.getPasswordExpDate().isAfter(LocalDate.now());
		return new UserProfile(username, userAccount.getPassword(), 
				AuthorityUtils.createAuthorityList(roles), passwordNonExpired);
	}

}
