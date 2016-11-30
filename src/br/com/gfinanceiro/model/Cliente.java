package br.com.gfinanceiro.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.runtime.JSONListAdapter;

import java.util.Arrays;
import java.util.Date;


public class Cliente  {
	private int idcliente;
	private String nome;
	private String cpf;
	private int ativo;
	private String data_cadastro;
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
		return "Cliente [idcliente=" + idcliente + ", nome=" + nome + ", cpf=" + cpf + ", ativo=" + ativo
				+ ", data_cadastro=" + data_cadastro + "]";
	}
	
	
	
	
}
