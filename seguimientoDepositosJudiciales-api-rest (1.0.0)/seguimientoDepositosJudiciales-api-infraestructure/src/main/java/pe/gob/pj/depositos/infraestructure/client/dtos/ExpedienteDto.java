package pe.gob.pj.depositos.infraestructure.client.dtos;

import java.io.Serializable;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExpedienteDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long numeroIncidente;
	private Long numeroUnico;
	private String codConexion;
	
    public String numeroExpediente;
    public String fecha;
    private String nombreInstancia;
    private String nombreProvincia;
    private String nombreJuzgado;
 
    private String juez;
    private String especialista;
    private String proceso;
    private String materia;
    private String nombreDistrito;
    
    private String nombreEspecialidad;
    private String descripcionSede;
    
}
