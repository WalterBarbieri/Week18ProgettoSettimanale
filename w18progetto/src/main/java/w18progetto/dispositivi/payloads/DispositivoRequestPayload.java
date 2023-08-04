package w18progetto.dispositivi.payloads;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import w18progetto.dispositivi.StatoDispositivo;
import w18progetto.dispositivi.TipoDispositivo;

@Getter
@Setter
@ToString
public class DispositivoRequestPayload {
	private TipoDispositivo tipo;
	private StatoDispositivo stato;
	private UUID utenteId;

	public DispositivoRequestPayload(TipoDispositivo tipo, StatoDispositivo stato) {
		this.tipo = tipo;
		this.stato = stato;
	}

}
