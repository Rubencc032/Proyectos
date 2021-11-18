/**
 * Practica de migracion de una BBDD creada en SQLITE AL SGBD MariaDB
 */
package migracion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private static ArrayList<String> createTables = new ArrayList<String>();
	//creamos un arrayList donde guardaremos los nombres de las tablas
	private static ArrayList<String> storeTables = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		ejercicio1(); 									   //llamada al metodo para iniciar el primer ejercicio
		ejercicio2();									   //llamada al metodo para iniciar el segundo ejercicio

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
				//cogemos metadatos de la tabla. Primero el nombre de la tabla y lo almacenamos en la cadena.
				//tambien almacenamos el nombre de la cadena en el arrayList storeTables
				cadena = "CREATE TABLE ";
				tabla = resul.getString("TABLE_NAME");
				catalogo = resul.getString("TABLE_CAT");
				esquema = resul.getString("TABLE_SCHEM");
				storeTables.add(tabla);
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
				foreignKeys = dbmd.getImportedKeys(catalogo, esquema, tabla);
				
				while(foreignKeys.next()) {
					
					//cogemos los distintos componentes que forman la foreign key
					String primaryKeyColumnName = foreignKeys.getString("PKCOLUMN_NAME");
					String primaryKeyTableName = foreignKeys.getString("PKTABLE_NAME");
		            String foreignKeyTableName = foreignKeys.getString("FKTABLE_NAME");
		            String foreignKeyColumnName = foreignKeys.getString("FKCOLUMN_NAME");
		            
		            //tenemos que ver si al menos uno de los strings no está vacio, lo que significa que hay una clave foranea
		            if (primaryKeyColumnName.length() > 0) cadena = cadena + ", CONSTRAINT FK_" + foreignKeyTableName + 
		            		" FOREIGN KEY (" + foreignKeyColumnName + ") REFERENCES " + primaryKeyTableName + "(" + primaryKeyColumnName + ")";
				}
				
				//cerramos la cadena del create table
				cadena = cadena + ");";
				
				//añadimos la cadena al arrayList de createTables
				createTables.add(cadena);
			}
		
			//si ha fallado la conexion a la BBDD, lo indic
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido realizar la conexion a veterinaria"); 
		}
		
	}
	
	
	private static void ejercicio2() throws SQLException {
		
		//variables locales
		//ruta de la BBDD y credencialea para la conexion
		String url = "jdbc:mariadb://localhost:3306/";
		String user = "star";
		String password = "wars";
		PreparedStatement sentencia;
		int rs = 0;
		boolean borrar = true;
		
		//intentamos la conexion
		try {
			connection = DriverManager.getConnection(url, user, password);
			//si la conexion es correcta, lo indicamos y seguimos con el ejercicio
			if(connection != null){
				//connection.setAutoCommit(false);
				System.out.println("Conexion con la BBDD MariaDB realizada con exito.");
				//vemos si hay elementos en el arrayList de storeTables y empezamos con la inyeccion de datos
				if(storeTables.size() > 0) {
					
					
					System.out.println("+++++++++++++++++++++++++++++++++");
					System.out.println("++Borrando la BBDD veterinaria.++");
					System.out.println("+++++++++++++++++++++++++++++++++");
					
					sentencia= connection.prepareStatement("drop database if exists veterinaria");
					sentencia.executeUpdate();
					
					System.out.println("+++++++++++++++++++++++++++++++++");
					System.out.println("++Creando la BBDD veterinaria.+++");
					System.out.println("+++++++++++++++++++++++++++++++++");
					
					sentencia= connection.prepareStatement("create database veterinaria");
					sentencia.executeUpdate();
					
					sentencia= connection.prepareStatement("use veterinaria");
					sentencia.executeUpdate();
					
					//TODO ver la manera de optimizar esto
					//en este caso se que necesito implementar primero el segungo item del arraylist
					//porque el primer item tiene una primayKey que hace referencia a la otra tabla, y da error
					//buscar solucion para automatizar esto
					while(createTables.size() > 0){
						for(int i = 0; i < createTables.size(); i++) {
							
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							System.out.println("  Borrando la tabla " + storeTables.get(i).toString());
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							
							sentencia= connection.prepareStatement("drop database if exists " + storeTables.get(i).toString());
							sentencia.executeUpdate();
				
							
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							System.out.println("  Creando la tabla " + storeTables.get(i).toString());
							System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
							
							System.out.println(createTables.get(i).toString());
			                try {
			                	 sentencia= connection.prepareStatement(createTables.get(i).toString());
			                	 rs = sentencia.executeUpdate();
			                }catch (SQLException e) {
			                	System.out.println(e);
			                	borrar = false;
			                }
			                if (borrar == true)createTables.remove(i);
			                else borrar = true;
			                
							
						}
					}
				 connection.commit();
				 
				 connection.setAutoCommit(true);
					
				}
				
			}
		//la conexion no es correcta, lo indicamos
		} catch (Exception e) {
			System.out.println("Conexion erronea");
		}
	}


}
