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
//		File ruta = new File("C:\\tp");
	//	if (!ruta.exists()) {
		//	ruta.mkdirs();
	//	}
		File fileOut = new File("c:\\tp\\salida.json");// "C:\\tp\\salida.json");
		ObjectMapper mapper = new ObjectMapper();

		URL url;
	
		List<ValoresSimplificados> valoresS = new ArrayList<>();
		try {
//url -------- https://raw.githubusercontent.com/mlennard-utn/tp_avanzado/master/mercado.json
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
