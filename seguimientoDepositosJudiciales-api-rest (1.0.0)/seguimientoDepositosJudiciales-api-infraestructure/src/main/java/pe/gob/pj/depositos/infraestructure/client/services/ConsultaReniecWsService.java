package pe.gob.pj.depositos.infraestructure.client.services;

import pe.gob.pj.depositos.infraestructure.client.dtos.PersonaReniecDTO;

public interface ConsultaReniecWsService {
	public PersonaReniecDTO consultaReniecPorDni(String cuo, String dni) throws Exception;
}
