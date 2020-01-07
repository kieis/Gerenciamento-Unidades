package br.com.poo.sysfi.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	private int dia;
	private int mes;
	private int ano;
	private static final DateFormat DATAFORMATO = new SimpleDateFormat ("dd/MM/yyyy");
	
	public Data(int dia, int mes, int ano){
		setData(dia + "/" + mes + "/" + ano);
	}
	
	public Data(String data){
		setData(data);
	}
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAno() {
		return ano;
	}
	
	public boolean isDataValida() {
		if(getDia() == 0 || getMes() == 0 || getAno() == 0) {
			return false;
		}
		
		return true;
	}
	
	private void setData(String data) {
		try {
			DateFormat diaFormato = new SimpleDateFormat("dd"),
					   mesFormato = new SimpleDateFormat("MM"),
					   anoFormato = new SimpleDateFormat("yyyy");
			Date dataVerificar = DATAFORMATO.parse(data);
			this.dia = Integer.parseInt(diaFormato.format(dataVerificar));
			this.mes = Integer.parseInt(mesFormato.format(dataVerificar));
			this.ano = Integer.parseInt(anoFormato.format(dataVerificar));
		}catch (java.text.ParseException evt ) { }
	}
	
	public static int getDataDiff(String data1, String data2) {
		Date date1 = null, date2 = null;
		int diasContados = 0;

		try {
			date1 = DATAFORMATO.parse(data1);
			date2 = DATAFORMATO.parse(data2);	
		}catch (java.text.ParseException evt ) { }
		
		if(date1 != null && date2 != null) {
			long dt = 0L;
			if(date1.getTime() > date2.getTime()) {
				dt = (date1.getTime() - date2.getTime()) + 3600000;
			}else {
				dt = (date2.getTime() - date1.getTime()) + 3600000;
			}
			diasContados = (int)(dt / 86400000L);
		}

		return diasContados;
	}
	
	public static String getDataAtual() {
		String dataFormatada = "";
		Date dataAtual = new Date();
		if(dataAtual != null) {
			dataFormatada = DATAFORMATO.format(dataAtual);
		}
		return dataFormatada;
	}
	
	public String toString(){
		return getDia() + "/" + getMes() + "/" + getAno();
	}
}
