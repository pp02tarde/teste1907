package web;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FornecedorDao;
import entidades.Fornecedor;
@WebServlet("/adicionarFornecedor")

public class AdicionarFornecedorServlet extends HttpServlet{

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String Cnpj = request.getParameter("cnpj");
		String Endereco = request.getParameter("endereco");
		String dataFundacao = request.getParameter("dataFundacao");

		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setNome(nome);
		fornecedor.setCnpj(Cnpj);
		

		Date dataConvertida;
		try {
		    dataConvertida = new SimpleDateFormat("dd/MM/yyyy").parse(dataFundacao);
		    fornecedor.setData_fundacao(dataConvertida);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		FornecedorDao dao = new FornecedorDao();
		dao.salvar(fornecedor);

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Nome: " + fornecedor.getNome() + "</br>");
		out.println("Cnpj: " + fornecedor.getCnpj() + "</br>");
		out.println("Endereco: " + fornecedor.getEndereco() + "</br>");
		out.println("Data Fundacao: " + fornecedor.getData_fundacao() + "</br>");
		out.println("</body>");
		out.println("</html>");
	    }
	
		
		
	}
	
	

