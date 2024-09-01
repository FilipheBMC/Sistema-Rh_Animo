package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Colaborador;
import model.Grupo;
import model.Setor;


public class Serializador {

	public static void salvarObjetos() {
		try {
			FileOutputStream fos = new FileOutputStream("objetos.data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			Colaborador[] listaCursos = new DaoColaborador().obterListaObjetos();
			oos.writeObject(listaCursos);

			Grupo[] listagrupo = new DaoGrupo().obterListaObjetos();
			oos.writeObject(listagrupo);

			Setor[] listaSetor = new DaoSetor().obterListaObjetos();
			oos.writeObject(listaSetor);


			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void recuperarObjetos() {
		try {
			FileInputStream fis = new FileInputStream("objetos.data");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Colaborador[]  listaColaborador         = (Colaborador[])ois.readObject();
			Grupo[] listaGrupo        = (Grupo[])ois.readObject();
			Setor[] listaSetor          = (Setor[])ois.readObject();
			
			new DaoColaborador().adicionarTodos(listaColaborador);
			new DaoGrupo().adicionarTodos(listaGrupo);
			new DaoSetor().adicionarTodos(listaSetor);

			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
