package entidades;

import java.util.Date;

public class Fornecedor {

	private int id;
	private String nome;
	private String cnpj;
	private String endereco;
	private Date data_fundacao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getData_fundacao() {
		return data_fundacao;
	}
	public void setData_fundacao(Date data_fundacao) {
		this.data_fundacao = data_fundacao;
	}
	
}
