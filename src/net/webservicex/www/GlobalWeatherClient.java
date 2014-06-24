package net.webservicex.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Scanner;

import net.webservicex.www.GlobalWeatherStub.GetWeather;
import net.webservicex.www.GlobalWeatherStub.GetWeatherResponse;

import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;

public class GlobalWeatherClient {

	public static void main(String[] args) 
	{
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("inserisci la città: ");
			String cityName = br.readLine();
			System.out.println("inserisci la nazione: ");
			String countryName = br.readLine();
			GlobalWeatherStub stub = new GlobalWeatherStub();
			stub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED,false);
			GetWeather req = new GetWeather();
			req.setCityName(cityName);
			req.setCountryName(countryName);
			GetWeatherResponse res = stub.getWeather(req);
			String output = res.getGetWeatherResult();
			if (!output.equals("Data Not Found"))
				System.out.println("Informazioni metereologiche su " + cityName + " : " + output);
			else
				System.out.println("Dati non disponibili");
		} catch ( IOException e) {
			
			e.printStackTrace();
		}

	}

}
