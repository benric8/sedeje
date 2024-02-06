package pe.gob.pj.depositos.infraestructure.client.services.impl;

import java.io.Serializable;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniec;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniecPortType;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniecResponse;
import pe.gob.pj.client.reniec.consultas.ws.ConsultaReniecService;
import pe.gob.pj.depositos.domain.utils.ProjectConstants;
import pe.gob.pj.depositos.domain.utils.ProjectProperties;
import pe.gob.pj.depositos.domain.utils.ProjectUtils;
import pe.gob.pj.depositos.infraestructure.client.dtos.PersonaReniecDTO;
import pe.gob.pj.depositos.infraestructure.client.services.ConsultaReniecWsService;


@Log4j2
@Service("consultaReniecWsService")
public class ConsultaReniecWsServiceImpl implements Serializable, ConsultaReniecWsService{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PersonaReniecDTO consultaReniecPorDni(String cuo, String dni) throws Exception {
		log.info("Iniciamos la consulta a reniec: ");
		String ENDPOINT = ProjectProperties.getInstance().getEndPointReniec();
		int TIMEOUT = ProjectProperties.getInstance().getTimeOutReniec();
		String usuarioReniec = ProjectProperties.getInstance().getUsuarioReniec();
		String dniConsultante = ProjectProperties.getInstance().getDniConsultanteReniec();
		ConsultaReniecPortType portType = getPortReniec(ENDPOINT, TIMEOUT);

		ConsultaReniec consultaReniecRequest = new ConsultaReniec();
		consultaReniecRequest.setReqDni(dni);
		consultaReniecRequest.setReqDniConsultante(dniConsultante);
		consultaReniecRequest.setReqTipoConsulta(ProjectConstants.STRING_DOS);
		consultaReniecRequest.setReqUsuario(usuarioReniec);
		consultaReniecRequest.setReqIp(ProjectUtils.getIp());

		ConsultaReniecResponse response = new ConsultaReniecResponse();
		Holder<String> resTrama = new Holder <String>() ;
		Holder<String> resCodigo = new Holder <String>() ;
		Holder<String> resDescripcion = new Holder <String>();
		Holder<String> resTotalRegistros = new Holder <String>();
		Holder<String> resPersona= new Holder <String>();
		Holder<byte[]> resFoto = new Holder <byte[]>();
		Holder<byte[]> resFirma =  new Holder <byte[]>();
		Holder<String> resListaPersonas = new Holder <String>();

		portType.consultaReniec(consultaReniecRequest.getReqTrama(), consultaReniecRequest.getReqDniConsultante(), consultaReniecRequest.getReqTipoConsulta(), consultaReniecRequest.getReqUsuario(), consultaReniecRequest.getReqIp(), consultaReniecRequest.getReqDni(), consultaReniecRequest.getReqNombres(), 
				consultaReniecRequest.getReqApellidoPaterno(), consultaReniecRequest.getReqApellidoMaterno(), consultaReniecRequest.getReqNroRegistros(), consultaReniecRequest.getReqGrupo(), consultaReniecRequest.getReqDniApoderado(), consultaReniecRequest.getReqTipoVinculoApoderado(), 
				resTrama, resCodigo, resDescripcion, resTotalRegistros, resPersona, resFoto, resFirma, resListaPersonas);
		
		response.setResCodigo(resCodigo.value);
		response.setResDescripcion(resDescripcion.value);
		response.setResFirma(resFirma.value);
		response.setResFoto(resFoto.value);
		response.setResListaPersonas(resListaPersonas.value);
		response.setResPersona(resPersona.value);
		response.setResTotalRegistros(resTotalRegistros.value);
		response.setResTrama(resTrama.value);
		log.info("Fin de la consulta a reniec: ");
		return cargarPersonaReniecResponseBean(response);
	}

	public ConsultaReniecPortType getPortReniec(String endpoint, int timeout) {
		ConsultaReniecService service = new ConsultaReniecService();
		ConsultaReniecPortType port = service.getConsultaReniec();
		
		BindingProvider bp = (BindingProvider)port;
		// Se asigna el endpoint
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		// Se asigna los tiempos de timeout de la consulta al WS
		bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", timeout * 1000);
		bp.getRequestContext().put("com.sun.xml.ws.request.timeout", timeout * 1000);
		return port;
	}
	
	
	public  PersonaReniecDTO cargarPersonaReniecResponseBean(ConsultaReniecResponse consultaReniecResponse) {

		String[] arr_pers = consultaReniecResponse.getResPersona().split("\t");
		PersonaReniecDTO persona = new PersonaReniecDTO();
		
		int i = 0;

		try {
			persona.setNumeroDocumenoIdentidad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFlagVerificacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoPaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoMaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoCasado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombres(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstCiv(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setGraInst(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstatura(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setSexo(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustNroDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomPad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomMad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaInscripcion(arr_pers[i++]);
		} catch (Exception e) {
		}
		;
		try {
			persona.setFechaExpedicion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaFallecimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setConsVot(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecCad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setRestric(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPrefDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNroDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setInterior(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setUrbanizacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEtapa(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setManzana(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLote(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreDptoPisoInt(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreUrbCondResid(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFoto(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFirma(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma1(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma2(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFoto(consultaReniecResponse.getResFoto());
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFirma(consultaReniecResponse.getResFirma());
		} catch (Throwable ex) {
		}
		;
		return persona;
	}

}
