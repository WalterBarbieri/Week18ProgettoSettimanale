package w18progetto.general;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w18progetto.dispositivi.Dispositivo;
import w18progetto.dispositivi.DispositivoService;
import w18progetto.utenti.Utente;
import w18progetto.utenti.UtenteRepository;
import w18progetto.utenti.UtenteService;

@Service
public class GeneralService {
	private final UtenteService us;
	private final DispositivoService ds;
	private final UtenteRepository ur;

	@Autowired
	public GeneralService(UtenteService us, DispositivoService ds, UtenteRepository ur) {
		this.us = us;
		this.ds = ds;
		this.ur = ur;
	}

	public void deleteUtente(UUID id) {
		Utente utente = us.findById(id);
		List<Dispositivo> dispositivi = ds.findByUtenteId(id);
		if (!dispositivi.isEmpty()) {
			for (Dispositivo dispositivo : dispositivi) {
				dispositivo.setUtente(null);
			}
		}
		ur.delete(utente);
	}
}
