package com.tp.avanzado.avanzado;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{
    public static void main( String[] args )
    {
    	ObjectMapper mapper = new ObjectMapper();
    	File file = new File("C:\\temp\\ejemplo.txt");
    	File fileOut = new File("C:\\temp\\ejemplo-out-enteros.json");
    	
    	try {
			Persona[] personas = mapper.readValue(file, Persona[].class);
			List<PersonaCambiado> per = new ArrayList<PersonaCambiado>();
			PersonaCambiado personita=null;
			
			for (Persona persona : personas) {
				personita=new PersonaCambiado();
				//per.add(new PersonaCambiado().setNombreCompleto(persona.getNombre() + " "+ persona.getApellido()));
				personita.setNombreCompleto(persona.getNombre()+" "+persona.getApellido());
				personita.setEdad(persona.getApellido().length());
				per.add(personita);
				System.out.println("nombre: "+persona.getNombre());
				System.out.println("apellido: "+persona.getApellido());
			}
			
			mapper.writeValue(fileOut, per);
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	URL url;
    	DecimalFormat df2 = new DecimalFormat(".##");
    	//df2.format(input)
    	
		try {
			url = new URL(args[0]);
		

    	try {
			Valores[] var = mapper.readValue(url, Valores[].class);
			
			for (Valores valores : var) {
				System.out.println(valores.getName());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
}
