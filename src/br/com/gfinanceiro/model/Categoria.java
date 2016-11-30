package br.com.gfinanceiro.model;

public class Categoria {

	private int idcategoria;
	private String nome;
	private int tipoCategoria;
	private String dtaCadastro;
	private int ativo;
	
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(int tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	public String getDtaCadastro() {
		return dtaCadastro;
	}
	public void setDtaCadastro(String dtaCadastro) {
		this.dtaCadastro = dtaCadastro;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", nome=" + nome + ", tipoCategoria=" + tipoCategoria
				+ ", dtaCadastro=" + dtaCadastro + ", ativo=" + ativo + "]";
	}

	
	
}


