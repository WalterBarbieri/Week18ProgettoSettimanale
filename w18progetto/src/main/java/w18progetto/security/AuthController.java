package w18progetto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w18progetto.utenti.Utente;
import w18progetto.utenti.UtenteService;
import w18progetto.utenti.payloads.UtenteLoginPayload;
import w18progetto.utenti.payloads.UtenteRequestPayload;
import w18progettoexceptions.UnauthorizeException;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UtenteService us;

	@Autowired
	private JWTTools jt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente creaUtente(@RequestBody UtenteRequestPayload body) {
		return us.creaUtente(body);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteLoginPayload body) {
		Utente utente = us.findByEmail(body.getEmail());
		if (body.getPassword().equals(utente.getPassword())) {
			String token = jt.createToken(utente);
			System.out.println(token);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			throw new UnauthorizeException("Errore durante il login, controllare le credenziali inserite");
		}
	}
}
