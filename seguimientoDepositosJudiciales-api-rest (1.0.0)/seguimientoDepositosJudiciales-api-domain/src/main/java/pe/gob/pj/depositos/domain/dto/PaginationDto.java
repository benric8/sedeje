package pe.gob.pj.depositos.domain.dto;

import java.io.Serializable;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import pe.gob.pj.depositos.domain.model.sij.DepositoJudicial;




public class PaginationDto implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Setter @Getter
	private Integer totalRecords;
	
	@Setter @Getter
	private Integer rows;
	
	@Setter @Getter
	private Integer currentPage;
 
	@Setter @Getter
	private Integer startPage;
	
	@Setter @Getter
	private Integer finalPage;
	
	@Setter @Getter
	private List<DepositoJudicial> lista;
	
	
}
