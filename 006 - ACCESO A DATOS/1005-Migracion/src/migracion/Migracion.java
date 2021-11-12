/**
 * Practica de migracion de una BBDD creada en SQLITE AL SGBD MariaDB
 */
package migracion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author jovian(Jorge Victoria Andreu)
 * @version 1
 * @since 12nov2021
 *
 */
public class Migracion {
	
	//variables globales
	private static Connection connection = null;			//para la conexion a la BBDD
	//creamos un arrayList donde vamos a cosntruir los inserts que vamos a utilizar en el ejercicio2
	ArrayList<String> createTables = new ArrayList<String>();

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		ejercicio1(); 									   //llamada al metodo para iniciar el primer ejercicio

	}

	private static void ejercicio1() throws SQLException {
		
		//variables locales
		//en esta variable vamos a constrir los createTables cogiendo metadatos de veterinaria.db
		String cadena = "";
		String tabla = "";
		String catalogo = "";
		String esquema = "";
		ResultSet resul;
		ResultSet columnas;
		ResultSet primaryKeys;
		ResultSet foreignKeys;
		int contador;
		
		
		//intentamos la conexion a la BBDD veterinaria.db
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:veterinaria.db");
			if (connection != null) System.out.println("Conexion a veterinaria");
			
			//obtenemos los metadatos de la BBDD veterinaria
			DatabaseMetaData dbmd = connection.getMetaData();
			
			//cogemos los metadatos de la tabla
			resul = dbmd.getTables(null, "veterinaria.db",null , null);
		    
			
			//mientras haya datos, vamos leyendo. 
			while(resul.next()) {
				//cogemos metadatos de la tabla. Primero el nombre de la tabla y lo almacenamos en la cadena
				cadena = "CREATE TABLE ";
				tabla = resul.getString("TABLE_NAME");
				catalogo = resul.getString("TABLE_CAT");
				esquema = resul.getString("TABLE_SCHEM");
				cadena = cadena + resul.getString("TABLE_NAME") + " ( ";
				
				//cogemos metadatos de la columna para siguiendo con la construccion de la cadena
				columnas = dbmd.getColumns(null, "veterinaria.db", tabla, null);
				
				//mientras haya datos vamos leyendo y construyendo la tabla
				while(columnas.next()) {
					cadena = cadena + " " + columnas.getString("COLUMN_NAME"); 
					cadena = cadena + " " + columnas.getString("TYPE_NAME");
					if( columnas.getString("IS_NULLABLE").equals("NO")) cadena = cadena + " NOT NULL";
					cadena = cadena + " , ";
				}
				
				//pasamos las primary key
				primaryKeys = dbmd.getPrimaryKeys(catalogo, esquema, tabla);
				cadena = cadena + " CONSTRAINT" + " PK_" + tabla + " PRIMARY KEY (";
				//mientras haya datos vamos leyendo y construyendo la tabla
				while(primaryKeys.next()) {
					contador=0;
					if (contador == 0) cadena = cadena + primaryKeys.getString("COLUMN_NAME");
					else cadena = cadena + ", " + primaryKeys.getString("COLUMN_NAME");
					contador++;
				}
				cadena = cadena + " ) ";
				
				//cogemos las foreign keys
				foreignKeys = dbmd.getExportedKeys(catalogo, esquema, tabla);
				
				while(foreignKeys.next()) {
					
					String primaryKeyColumnName = foreignKeys.getString("PKCOLUMN_NAME");
					String primaryKeyTableName = foreignKeys.getString("PKTABLE_NAME");
		            String foreignKeyTableName = foreignKeys.getString("FKTABLE_NAME");
		            String foreignKeyColumnName = foreignKeys.getString("FKCOLUMN_NAME");
		            
		            System.out.println(primaryKeyColumnName + " " + primaryKeyTableName + " "+ " " + foreignKeyTableName +" " + foreignKeyColumnName);
		            
		            if (primaryKeyColumnName.length() > 0) cadena = cadena + ", CONSTRAINT FK_" + foreignKeyTableName + 
		            		" FOREIGN KEY (" + foreignKeyColumnName + ") REFERENCES " + foreignKeyTableName + "(" + primaryKeyColumnName + ")";
				}
				
				
				System.out.println(cadena);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido realizar la conexion veterinaria"); 
		}
		
		
		
	}

}
