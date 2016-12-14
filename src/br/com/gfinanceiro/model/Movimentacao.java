package br.com.gfinanceiro.model;

public class Movimentacao {
	
	private String dsc;
	private String cliente_fornecedor;
	private double valor;
	private String tipo_movimentacao;
	private String data_cadastro;
	private String nome_categoria;
	private int id;
	private int cliente_idcliente;
	private int fornecedor_idfornecedor;
	private int categoria_idcategoria;
	private double entrada;
	private double saida;
	
	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public double getSaida() {
		return saida;
	}

	public void setSaida(double saida) {
		this.saida = saida;
	}

	public int getCliente_idcliente() {
		return cliente_idcliente;
	}

	public void setCliente_idcliente(int cliente_idcliente) {
		this.cliente_idcliente = cliente_idcliente;
	}

	public int getFornecedor_idfornecedor() {
		return fornecedor_idfornecedor;
	}

	public void setFornecedor_idfornecedor(int fornecedor_idfornecedor) {
		this.fornecedor_idfornecedor = fornecedor_idfornecedor;
	}

	public int getCategoria_idcategoria() {
		return categoria_idcategoria;
	}

	public void setCategoria_idcategoria(int categoria_idcategoria) {
		this.categoria_idcategoria = categoria_idcategoria;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getCliente_fornecedor() {
		return cliente_fornecedor;
	}

	public void setCliente_fornecedor(String cliente_fornecedor) {
		this.cliente_fornecedor = cliente_fornecedor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo_movimentacao() {
		return tipo_movimentacao;
	}

	public void setTipo_movimentacao(String tipo_movimentacao) {
		this.tipo_movimentacao = tipo_movimentacao;
	}

	public String getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
