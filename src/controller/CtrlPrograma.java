package controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Colaborador;
import model.Grupo;
import model.Setor;
import model.dao.DaoColaborador;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;
import model.dao.Serializador;
import viewer.JanelaAdicionarColaborador;
import viewer.JanelaConsultarColaboradores;
import viewer.JanelaPrincipal;

public class CtrlPrograma extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//

	// JANELAS
	private JanelaPrincipal janela;
	private JanelaConsultarColaboradores janelaColaboradores;
	private JanelaAdicionarColaborador janelaAdicionarColaborador;

	// CTRLS
	public CtrlConsultarColaborador CtrlConsultarColaborador;
	public CtrlAdicionarGrupo ctrlAdicionarGrupo;
	public CtrlConsultarGrupo ctrlConsultarGrupo;
	public CtrlSetor ctrlSetor;

	//
	// MÉTODOS
	//
	public CtrlPrograma() {
		super(null);
	}

	/**
	 * Método que indica o que o controlador irá executar no início de suas ações.
	 */
	public void iniciar() {
		// Recupera os objetos previamente salvos
		// (não esqueça de ver se este método está interagindo com
		// todos os DAO's do sistema)
		Serializador.recuperarObjetos();
		// Instanciando um objeto da classe JanelaPrincipal
		this.janela = new JanelaPrincipal(this);
	}

	// ~~~~~~~~~~ CTRLS DE CONSULTAR ~~~~~~~~~~

	// ----------- CTRL CONSULTAR COLABORADORES ------------------------

	/*
	 * Iniciando janela para consultar colaboradores
	 * 
	 */

	public void iniciarConsultarColaboradores() {
		if (CtrlConsultarColaborador == null)
			this.CtrlConsultarColaborador = new CtrlConsultarColaborador(this);
	}

	// Finalizando a janela

	public void informarFimDeConsultarColaboradores() {
		this.CtrlConsultarColaborador = null;
	}

	// ---------------------------------------------------------------

	// ----------- CTRL CONSULTAR GRUPOS ------------------------

	public void inicilizarConsultarGrupo() {
		if (this.ctrlConsultarGrupo == null)
			this.ctrlConsultarGrupo = new CtrlConsultarGrupo(this);
	}

	public void informarFimDeConsultarGrupo() {
		this.ctrlConsultarGrupo = null;
	}

	// ---------------------------------------------------------------

	// ----------- CTRL SETOR ------------------------

	public void inicializarSetor() {
		if (this.ctrlSetor == null)
			this.ctrlSetor = new CtrlSetor(this);
	}

	public void informarFimSetor() {
		this.ctrlSetor = null;
	}

	// ---------------------------------------------------------------

	// ----------- CTRL EXPORTAR DADOS ---------------

	public void exportarDados() {
		DaoSetor daoSetor = new DaoSetor();
		DaoGrupo daoGrupo = new DaoGrupo();
		DaoColaborador daoColaborador = new DaoColaborador();

		Setor[] listaSetor = daoSetor.obterListaObjetos();
		Grupo[] listaGrupo = daoGrupo.obterListaObjetos();
		Colaborador[] listaColaboradores = daoColaborador.obterListaObjetos();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha o local e o nome para salvar o arquivo JSON");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo JSON (*.json)", "json"));

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			if (!fileToSave.getAbsolutePath().endsWith(".json")) {
				fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
			}

			try (FileWriter writer = new FileWriter(fileToSave)) {
				writer.write(gerarJson(listaSetor, listaGrupo, listaColaboradores));
				System.out.println("Dados exportados com sucesso para " + fileToSave.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Exportação cancelada pelo usuário.");
		}
	}

	private String gerarJson(Setor[] setores, Grupo[] grupos, Colaborador[] colaboradores) {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{\n");

		jsonBuilder.append("\"setores\": [\n");
		for (int i = 0; i < setores.length; i++) {
			jsonBuilder.append(setorParaJson(setores[i]));
			if (i < setores.length - 1)
				jsonBuilder.append(",");
			jsonBuilder.append("\n");
		}
		jsonBuilder.append("],\n");

		jsonBuilder.append("\"grupos\": [\n");
		for (int i = 0; i < grupos.length; i++) {
			jsonBuilder.append(grupoParaJson(grupos[i]));
			if (i < grupos.length - 1)
				jsonBuilder.append(",");
			jsonBuilder.append("\n");
		}
		jsonBuilder.append("],\n");

		jsonBuilder.append("\"colaboradores\": [\n");
		for (int i = 0; i < colaboradores.length; i++) {
			jsonBuilder.append(colaboradorParaJson(colaboradores[i]));
			if (i < colaboradores.length - 1)
				jsonBuilder.append(",");
			jsonBuilder.append("\n");
		}
		jsonBuilder.append("]\n");

		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}

	private String setorParaJson(Setor setor) {
		return "  {\"codigo\": " + setor.getCodSetor() + ", \"nome\": \"" + setor.getNomeSetor() + "\"}";
	}

	private String grupoParaJson(Grupo grupo) {
		return "  {\"codigo\": \"" + grupo.getCodigoGrupo() + "\", \"nome\": \"" + grupo.getNome() + "\"}";
	}

	private String colaboradorParaJson(Colaborador colaborador) {
		return "  {\"nome\": \"" + colaborador.getNome() + "\", \"sexo\": \"" + colaborador.getSexo()
				+ "\", \"setor\": \"" + colaborador.getSetor().getCodSetor() + "\", \"grupo\": \""
				+ colaborador.getCodigoGrupo().getCodigoGrupo() + "\"}";
	}

	// ----------- CTRL IMPORTAR DADOS ---------------

//    public void importarDados() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Escolha o arquivo JSON para importar");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo JSON (*.json)", "json"));
//
//        int userSelection = fileChooser.showOpenDialog(null);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToOpen = fileChooser.getSelectedFile();
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
//                StringBuilder jsonBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    jsonBuilder.append(line);
//                }
//
//                String jsonContent = jsonBuilder.toString();
//                lerJson(jsonContent);
//                System.out.println("Dados importados com sucesso do arquivo " + fileToOpen.getAbsolutePath());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Importação cancelada pelo usuário.");
//        }
//    }
//
//    private void lerJson(String jsonContent) {
//        String[] partes = jsonContent.split("(?<=\\}),(?=\\{)");
//        for (String parte : partes) {
//            if (parte.contains("\"setores\"")) {
//                Setor[] setores = parseSetores(parte);
//                // Processar setores (adicionar a alguma lista ou banco de dados)
//            } else if (parte.contains("\"grupos\"")) {
//                Grupo[] grupos = parseGrupos(parte);
//                // Processar grupos
//            } else if (parte.contains("\"colaboradores\"")) {
//                Colaborador[] colaboradores = parseColaboradores(parte);
//                // Processar colaboradores
//            }
//        }
//    }
//
//    private Setor[] parseSetores(String json) {
//        // Código de parsing manual para converter o JSON de setores para Setor[]
//        // Parse usando substring e replace para limpar as chaves JSON e dividir os elementos
//        return new Setor[0]; // Exemplo simplificado
//    }
//
//    private Grupo[] parseGrupos(String json) {
//        // Código de parsing manual para converter o JSON de grupos para Grupo[]
//        return new Grupo[0]; // Exemplo simplificado
//    }
//
//    private Colaborador[] parseColaboradores(String json) {
//        // Código de parsing manual para converter o JSON de colaboradores para Colaborador[]
//        return new Colaborador[0]; // Exemplo simplificado
//    }

	public void importarDados() {
		System.out.println("Não consegui.");
	}

	/**
	 * Método para encerrar programa
	 **/
	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	/**
	 * Método main
	 */
	public static void main(String[] args) {
		new CtrlPrograma();
	}

}
