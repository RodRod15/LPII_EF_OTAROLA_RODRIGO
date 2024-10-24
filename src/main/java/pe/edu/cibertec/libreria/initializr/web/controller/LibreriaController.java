package pe.edu.cibertec.libreria.initializr.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pe.edu.cibertec.libreria.initializr.web.db.MySQLDataSource;
import pe.edu.cibertec.libreria.initializr.web.model.ControladorUsuariosRepositorio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;



@Controller
public class LibreriaController {
	
	
	@Autowired
	private ControladorUsuariosRepositorio userRepo;
	
	@GetMapping("/login")
	public String login() {
		return "login";	
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro";	
	}
	
	@GetMapping("/index")
	public String home() {
		return "index";	
	}
	
	
	@GetMapping("/listar")
	public String listRole(Model model) {
		try {
			model.addAttribute("ltsUser", userRepo.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "listado";
	}
	
	@RequestMapping(value="/UserReport", method = RequestMethod.GET)
	public void usuarioReporte(HttpServletResponse response) throws JRException, IOException{
		InputStream is = this.getClass().getResourceAsStream("/reporteusuarios6m.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(is);
		Connection con = MySQLDataSource.getMySQLConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=reporteusuarios6m.pdf");
		OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
	
	
	}
}
