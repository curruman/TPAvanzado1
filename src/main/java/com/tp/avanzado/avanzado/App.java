package com.tp.avanzado.avanzado;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class App {
	public static void main(String[] args) {
		File ruta = new File("C:\\tp");
		if (!ruta.exists()) {
			ruta.mkdirs();
		}
		File fileOut = new File(ruta + "\\salida.json");// "C:\\tp\\salida.json");
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * File file = new File("C:\\temp\\ejemplo.txt"); File fileOut = new
		 * File("C:\\temp\\ejemplo-out-enteros.json");
		 * 
		 * try { Persona[] personas = mapper.readValue(file, Persona[].class);
		 * List<PersonaCambiado> per = new ArrayList<PersonaCambiado>();
		 * PersonaCambiado personita=null;
		 * 
		 * for (Persona persona : personas) { personita=new PersonaCambiado();
		 * //per.add(new PersonaCambiado().setNombreCompleto(persona.getNombre()
		 * + " "+ persona.getApellido()));
		 * personita.setNombreCompleto(persona.getNombre()+" "+persona.
		 * getApellido()); personita.setEdad(persona.getApellido().length());
		 * per.add(personita);
		 * System.out.println("nombre: "+persona.getNombre());
		 * System.out.println("apellido: "+persona.getApellido()); }
		 * 
		 * mapper.writeValue(fileOut, per);
		 * 
		 * 
		 * } catch (JsonParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (JsonMappingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		URL url;
		// DecimalFormat df2 = new DecimalFormat("0.##");
		// df2.format(input)
		// Double price = 5.000;
		// DecimalFormat format = new DecimalFormat("0.##");
		// System.out.println(format.format(price));
		List<ValoresSimplificados> valoresS = new ArrayList<>();
		try {
			url = new URL(args[0]);
			Valores[] var;
			try {
				var = mapper.readValue(url, Valores[].class);
				ValoresSimplificados simple = null;
				for (Valores valores : var) {
					simple = new ValoresSimplificados();
					if (valores.getTicker() == null) {
						valores.setTicker("null");
						simple.setTicker(valores.getTicker());
					} else {
						simple.setTicker(valores.getTicker());
					}
					simple.setPrice((new BigDecimal(Double.toString(valores.getPrice())).stripTrailingZeros()));
					simple.setIsin(valores.getId());
					valoresS.add(simple);
					// simple.setPrice(Double.parseDouble((format.format(valores.getPrice()).replace(",","."))));
					// simple.setPrice(new
					// Double(format.format(valores.getPrice())).doubleValue());
					// System.out.println((new
					// BigDecimal(Double.toString(valores.getPrice())).stripTrailingZeros()));
					// simple.setPrice((new
					// BigDecimal(Double.toString(valores.getPrice())).stripTrailingZeros()).doubleValue());
					// System.out.println(simple.getPrice());

					//System.out.println(valores.getName());

				}

				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				mapper.writeValue(fileOut, valoresS);

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
