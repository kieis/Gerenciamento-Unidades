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
		addItem("Cear� (CE)");
		addItem("Distrito Federal (DF)");
		addItem("Esp�rito Santo (ES)");
		addItem("Goi�s (GO)");
		addItem("Maranh�o (MA)");
		addItem("Mato Grosso (MT)");
		addItem("Mato Grosso do Sul (MS)");
		addItem("Minas Gerais (MG)");
		addItem("Par� (PA)");
		addItem("Para�ba (PB)");
		addItem("Paran� (PR)");
		addItem("Pernambuco (PE)");
		addItem("Piau� (PI)");
		addItem("Rio de Janeiro (RJ)");
		addItem("Rio Grande do Norte (RN)");
		addItem("Rio Grande do Sul (RS)");
		addItem("Rond�nia (RO)");
		addItem("Roraima (RR)");
		addItem("Santa Catarina (SC)");
		addItem("S�o Paulo (SP)");
		addItem("Sergipe (SE)");
		addItem("Tocantins (TO)");
	}
}
