package com.salesianostriana.dam.proyectofinal.servicios;

import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
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
		
		Random rnd = new Random();
		int codReserva;
		
		do {
			codReserva = rnd.nextInt();
		}while(codReserva<=0);
		
		Document pdf = new Document(PageSize.A4);
	
		PdfWriter.getInstance(pdf, response.getOutputStream());
		pdf.open();
		
		Image imagen = Image.getInstance(reserva.getClase().getImgClase());
		imagen.setAlignment(2);
		
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuenteTitulo.setSize(24);
		
		Font distribucion = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		distribucion.setSize(15);
		
		Table tabla = new Table(2);
		Table tabla2 = new Table(2);
		
		Paragraph factura = new Paragraph("FACTURA\n\n",fuenteTitulo);
		factura.setAlignment(1);
		factura.setExtraParagraphSpace(3);
		
		Paragraph separador1 = new Paragraph("Datos del pago\n"
				+ "______________________________________________________________\n\n",distribucion);
		Paragraph separador2 = new Paragraph("Datos de la clase\n"
				+ "______________________________________________________________\n\n",distribucion);
		
		Paragraph datosEmpresa = new Paragraph("De:\n"
				+ "Nombre: Fit&Go S.L.\n"
				+ "Banco: ING Groep N.V.\n"
				+ "Codº Reserva: "+codReserva);
		
		Paragraph parrafo = new Paragraph("A:\n"
				+ "Nombre: " + usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2()+";\n"
				+ "NºTarjeta: **** **** **** "+usuario.getTarjeta().charAt(12)+usuario.getTarjeta().charAt(13)+usuario.getTarjeta().charAt(14)+usuario.getTarjeta().charAt(15)+";\n"
				+ "Importe: "+reserva.getClase().getPrecio()+"€;\n\n\n\n");
		
		Paragraph parrafo2 = new Paragraph("Clase: "+reserva.getClase().getNombreClase()+";\n"
				+ "Horario de la clase: "+reserva.getClase().getHorario()+";\n"
				+ "Fecha de reserva: "+reserva.getFechaReserva().toString()+";\n\n");
		
		Cell celda1 = new Cell(datosEmpresa);
		Cell celda2 = new Cell(parrafo);
		
		celda1.setBorder(0);
		celda2.setBorder(0);
		
		Cell celda3 = new Cell(parrafo2);
		Cell celda4 = new Cell(imagen);
		
		celda3.setBorder(0);
		celda4.setBorder(0);
		
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		
		tabla2.addCell(celda3);
		tabla2.addCell(celda4);
		
		tabla.setBorder(0);
		tabla2.setBorder(0);
		
		pdf.add(factura);
		pdf.add(separador1);
		pdf.add(tabla);
		pdf.add(separador2);
		pdf.add(tabla2);
		pdf.close();
	}
	
	public boolean permitirReserva (String nombre, LocalDate fecha) {
		List<ReservaClase> aux = buscarPorNombre(nombre);
		boolean permitir = true;
		
		
		for (int i = 0; i < aux.size(); i++) {
			if(aux.get(i).getNombreUsuario().equalsIgnoreCase(nombre) && aux.get(i).getFechaReserva().isEqual(fecha)) {
				permitir = false;
			}else {
				permitir = true;
			}
		}
		return permitir;
	}
	
}
