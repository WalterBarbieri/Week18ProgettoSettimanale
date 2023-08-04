package w18progetto.runners;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import w18progetto.dispositivi.DispositivoService;
import w18progetto.dispositivi.StatoDispositivo;
import w18progetto.dispositivi.TipoDispositivo;
import w18progetto.dispositivi.payloads.DispositivoRequestPayload;
import w18progetto.utenti.Utente;
import w18progetto.utenti.UtenteService;
import w18progetto.utenti.payloads.UtenteRequestPayload;

@Component
public class GeneralRunner implements CommandLineRunner {

	@Autowired
	UtenteService us;
	@Autowired
	DispositivoService ds;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		for (int i = 0; i < 50; i++) {
			String username = faker.ancient().god();
			String nome = faker.name().firstName();
			String cognome = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(6, 15);
			UtenteRequestPayload rndUtente = new UtenteRequestPayload(username, nome, cognome, email, password);
//			us.creaUtente(rndUtente);
		}

		for (int i = 0; i < 150; i++) {
			Random rnd = new Random();
			TipoDispositivo rndTipo = TipoDispositivo.values()[rnd.nextInt(TipoDispositivo.values().length)];
			StatoDispositivo rndStato = StatoDispositivo.values()[rnd.nextInt(StatoDispositivo.values().length)];
			DispositivoRequestPayload rndDispositivo = new DispositivoRequestPayload(rndTipo, rndStato);
			int rndInt = rnd.nextInt(100);
			if (rndInt > 20) {
				Utente rndUtente = us.rndUtente();
				rndDispositivo.setUtenteId(rndUtente.getId());
			}
//			ds.creaDispositivo(rndDispositivo);

		}
	}

}
