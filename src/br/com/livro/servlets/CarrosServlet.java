package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.model.Carro;
import br.com.livro.model.ListaCarros;
import br.com.livro.service.CarroService;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarroService carroService = new CarroService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		List<Carro> carros = carroService.getCarros();
		ListaCarros lista = new ListaCarros();
		lista.setCarros(carros);
		// Gera o XML
		//String xml = JAXBUtil.toXML(lista);
		// Escreve o XML na response do servlet com application/xml
		//ServletUtil.writerXML(resp, xml);
		
		// Gera o Json
		//String json = JAXBUtil.toJSON(lista); 
		// Escreve o Json na response do servlet com application/json
		//ServletUtil.writeJSON(resp, json);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		// Escreve o JSON na response do servlet com application/json
		ServletUtil.writeJSON(resp, json);
		
	}
}
