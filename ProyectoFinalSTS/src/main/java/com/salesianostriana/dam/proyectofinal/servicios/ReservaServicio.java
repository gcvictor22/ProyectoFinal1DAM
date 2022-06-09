package com.salesianostriana.dam.proyectofinal.servicios;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.salesianostriana.dam.proyectofinal.model.ReservaClase;
import com.salesianostriana.dam.proyectofinal.model.Usuario2;
import com.salesianostriana.dam.proyectofinal.repositorios.ReservaRepository;
import com.salesianostriana.dam.proyectofinal.servicios.base.ServicioBaseImpl;
/**
 * Clase que encapsula un servicio CRUD de ClaseGyms. Se trata de un "envoltorio"
 * del repositorio de ClaseGyms, ya que tendrá los mismos métodos, y ninguno más.
 * En ejemplos y tutoriales posteriores ofreceremos la posibilidad de crear un servicio
 * abstracto, del cual podrán extender los demás, para preocuparse solo de la 
 * lógica propia del servicio.
 * 
 * @author luismi
 *
 */
@Service
public class ReservaServicio extends ServicioBaseImpl<ReservaClase, Long, ReservaRepository>{

	public ReservaServicio(ReservaRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	public List<ReservaClase> buscarPorNombre (String nombre){
		
		return repositorio.findByNombreUsuarioContainsIgnoreCase(nombre);
	}
	
	public String mostrarMensajeTresReservas (String nombre) {
		List<ReservaClase> aux = buscarPorNombre(nombre);
		int contador = 0;
		double total = 0.0;
		
		for (int i = 0; i < aux.size(); i++) {
			total += aux.get(i).getPrecioTotal();
		}
		
		if(aux.size()>=6) {
			contador += 10;
			total = total - contador;
			
		}else if(aux.size()>=3) {
			contador += 5;
			total = total - contador;
			
		}else {
			total = total - contador;
		
		}
		
		return "A pagar: "+total+"€; Se han devuelto a "+nombre+" un total de "+contador+"€";
	}

	public void export(HttpServletResponse response, Usuario2 usuario, ReservaClase reserva) throws DocumentException, IOException {
		Document pdf = new Document(PageSize.A4);
	
		PdfWriter.getInstance(pdf, response.getOutputStream());
		
		pdf.open();
		
		Image imagen = Image.getInstance(reserva.getClase().getImgClase());
		
		Paragraph parrafo = new Paragraph("Nombre: " + usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+";\n"
				+ "NºTarjeta: **** **** **** "+usuario.getTarjeta().charAt(12)+usuario.getTarjeta().charAt(13)+usuario.getTarjeta().charAt(14)+usuario.getTarjeta().charAt(15)+";\n"
				+ "Importe: "+reserva.getClase().getPrecio()+"€;\n\n"
				+ "Clase: "+reserva.getClase().getNombreClase()+";\n"
				+ "Horario de la clase: "+reserva.getClase().getHorario()+";\n"
				+ "Fecha de reserva: "+reserva.getFechaReserva().toString());
		
		pdf.add(imagen);
		pdf.add(parrafo);
		pdf.close();
	}
	
}
