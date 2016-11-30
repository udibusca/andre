package br.com.gfinanceiro.model;

public class Fornecedor {

	private int idfornecedor;
	private String nome;
	private String cnpj;
	private int ativo;
	private String data_cadastro;

	public int getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(int idfornecedor) {
		this.idfornecedor = idfornecedor;
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

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	@Override
	public String toString() {
		return "Fornecedor [idfornecedor=" + idfornecedor + ", nome=" + nome + ", cnpj=" + cnpj + ", ativo=" + ativo
				+ ", data_cadastro=" + data_cadastro + "]";
	}

}
