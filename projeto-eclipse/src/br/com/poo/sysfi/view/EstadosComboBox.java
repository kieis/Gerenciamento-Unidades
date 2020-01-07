package br.com.poo.sysfi.view;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class EstadosComboBox extends JComboBox<String>{
	public EstadosComboBox() {
		initComponents();
	}
	
	public void initComponents() {
		addItem("Acre (AC)");
		addItem("Alagoas (AL)");
		addItem("Amazonas (AM)");
		addItem("Bahia (BA)");
		addItem("Ceará (CE)");
		addItem("Distrito Federal (DF)");
		addItem("Espírito Santo (ES)");
		addItem("Goiás (GO)");
		addItem("Maranhão (MA)");
		addItem("Mato Grosso (MT)");
		addItem("Mato Grosso do Sul (MS)");
		addItem("Minas Gerais (MG)");
		addItem("Pará (PA)");
		addItem("Paraíba (PB)");
		addItem("Paraná (PR)");
		addItem("Pernambuco (PE)");
		addItem("Piauí (PI)");
		addItem("Rio de Janeiro (RJ)");
		addItem("Rio Grande do Norte (RN)");
		addItem("Rio Grande do Sul (RS)");
		addItem("Rondônia (RO)");
		addItem("Roraima (RR)");
		addItem("Santa Catarina (SC)");
		addItem("São Paulo (SP)");
		addItem("Sergipe (SE)");
		addItem("Tocantins (TO)");
	}
}
