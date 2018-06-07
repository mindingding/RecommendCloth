package com.example.demo;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DemoController {

	RestTemplate restTemplate = new RestTemplate();
	static JsonNode temp;
	@RequestMapping("/weather")
	public @ResponseBody JsonNode parseweather() {

		HttpClient httpclient = new DefaultHttpClient();
		
		String lat1 = "37.1373888900";
		String lon1 = "127.0657778000";
		String appKeyId = "a6e79dea-d294-4b5f-a08a-82eee06a2faf";

		String callUrl = setLocation(lat1, lon1); // 위치 설정 (위도, 경도, return mode) 날씨인지, 대기정보인지...

		try {

			HttpGet httpGet = new HttpGet(callUrl);
			httpGet.addHeader("appKey", appKeyId); // 발급받은 키를 헤더에 태워서 같이 날려줘야 함..

			HttpResponse response = httpclient.execute(httpGet);

			InputStream in;
			in = response.getEntity().getContent();
			printByInputStream(in);
			in.close();
			
			
		} catch (ClientProtocolException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		return temp;
	}

	public static String setLocation(String lat, String lon) {

		String callUrl = "";

		callUrl = "https://api2.sktelecom.com/weather/current/minutely?version=1" + "&lat=" + lat + "&lon=" + lon;

		return callUrl;
	}

	public static void printByInputStream(InputStream is) {
		byte[] buf = new byte[2048];
		StringBuffer sb = new StringBuffer();
		int len = -1;
		try {
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				sb.append(new String(buf, 0, len));
			}

			System.out.println(sb.toString());

			parseWeather(sb.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void parseWeather(String weatherInfo) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(weatherInfo);
			
			// System.out.println(root.path("weather"));
			
			//System.out.println(root.path("weather").path("minutely").get(0).findValue("temperature")); // 여러 정보가 오지만.. 난
			temp = root.path("weather").path("minutely").get(0).findValue("temperature");
			// System.out.println(root.path("result"));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
