package br.com.poo.sysfi.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.poo.sysfi.service.business.ListaService;

@SuppressWarnings("serial")
public class EntityFrame extends JFrame{
	protected ListaService services = new ListaService();
	
	protected boolean validarServices() {
		if(services != null && services.getClienteService() != null
	            && services.getFuncionarioService() != null
	            && services.getContaSimplesService() != null
	            && services.getContaSimplesService() != null
	            && services.getUnidadeService() != null) {
			return true;
		}
		services = new ListaService();
		JOptionPane.showMessageDialog(null, "Algum serviço não foi instânciado corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}
