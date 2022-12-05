package telran.java45.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {

	private static final long serialVersionUID = -5082181692941191427L;
	
	public UserExistsException(String login) {
		super("User " + login + " exists");
	}

}
