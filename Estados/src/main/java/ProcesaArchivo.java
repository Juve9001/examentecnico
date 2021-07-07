import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcesaArchivo {
	/* Creando un mapa con String, dentro de ella una lista de strings */
	private Map<String, List<String>> mapaEstados = new HashMap<String, List<String>>();
	private List<String> listaEstados = llenarEstados();

	public void leerArchivo() {
		/* Declarando un cadena */
		String cadena;
		/* Referenciando al objeto a crear */
		FileReader f = null;
		;
		BufferedReader b = null;
		try {
			/* Instanciamos y ubicamos la direccion del archivo */
			f = new FileReader("C:\\sts\\workspace\\Estados\\Examen tecnico\\Direcciones.txt");
			/*
			 * BufferedReader es una clase de Java para leer el texto de una secuencia de
			 * entrada (como un archivo) almacenando en el búfer caracteres que leen a la
			 * perfección caracteres, matrices o líneas
			 */
			b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				System.out.println(cadena);
				String[] lineaAux = cadena.split("\\|");
				for (String estado : listaEstados) {
					if (lineaAux[4].equalsIgnoreCase(estado)) {
						if (mapaEstados.get(estado) == null) {
							List<String> lineas = new ArrayList<String>();
							lineas.add(cadena);
							mapaEstados.put(estado, lineas);
						} else {
							mapaEstados.get(estado).add(cadena);
						}
					}
				}
			}
			b.close();

			generarArchivos(mapaEstados);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void generarArchivos(Map<String, List<String>> listas) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		for (Map.Entry<String, List<String>> entry : listas.entrySet()) {
			System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
			try {
				fichero = new FileWriter("C:\\\\sts\\\\workspace\\\\Estados\\\\Examen tecnico\\\\ARCHIVO_" + entry.getKey().toUpperCase() + ".txt");
				pw = new PrintWriter(fichero);
				for (String linea : entry.getValue())
					pw.println(linea);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	private List<String> llenarEstados() {
		List<String> estados = new ArrayList<String>();
		estados.add("Puebla");
		estados.add("Baja California Sur");
		estados.add("Ciudad de México");
		estados.add("Zacatecas");
		return estados;
	}

}
