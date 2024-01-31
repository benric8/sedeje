package pe.gob.pj.depositos.domain.port.repository;

import java.util.List;

import pe.gob.pj.depositos.domain.dto.PaginationDto;
import pe.gob.pj.depositos.domain.model.sij.DepositoJudicial;
import pe.gob.pj.depositos.domain.model.sij.OrdenPago;

public interface ConsultaRepositoryPort {
	
	public List<DepositoJudicial> consultarDeposito(String cuo, String numeroExpediente) throws Exception;
	public PaginationDto consultarDeposito(String cuo, String numeroExpediente,Integer page, Integer rows) throws Exception;
	public List<OrdenPago> consultarOrdenesPago(String cuo, String codigoDeposito) throws Exception;
}
