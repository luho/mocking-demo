package de.hoogle.weather.google;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import de.hoogle.weather.Forecast;
import de.hoogle.weather.ForecastDay;
import de.hoogle.weather.ParseException;

public class GoogleWeatherParser {

	private XPath xpath;
	private Forecast forecast;
	
	public GoogleWeatherParser() {
		xpath = XPathFactory.newInstance().newXPath();
	}
	
	public Forecast parse(String xml) {
		try {
			Node node = (Node) xpath.evaluate("/xml_api_reply/weather",
					new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))),
					XPathConstants.NODE);
			forecast = createForecast(node);
		} catch (XPathExpressionException e) {
			throw new ParseException(e);
		} catch (UnsupportedEncodingException e) {
			throw new ParseException(e);
		}
		return forecast;
	}
	
	private Forecast createForecast(Node node) throws XPathExpressionException {
		Date date = getForecastDate(node);
		forecast = new Forecast(date);
		addDays(node);
		return forecast;
	}
	
	private Date getForecastDate(Node node) throws XPathExpressionException {
		String dateString = (String) xpath.evaluate("forecast_information/forecast_date/@data",
				node, XPathConstants.STRING);
		return parseDate(dateString);
	}
	
	private Date parseDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateString);
		} catch (java.text.ParseException e) {
			throw new ParseException(e);
		}
	}
	
	private void addDays(Node node) throws XPathExpressionException {
		NodeList nodeList = (NodeList) xpath.evaluate("forecast_conditions", 
				node, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node day = nodeList.item(i);
			addDay(day);
		}
	}
	
	private void addDay(Node node) throws XPathExpressionException {
		String dayOfWeek = (String) xpath.evaluate("day_of_week/@data", node, XPathConstants.STRING);
		String low = (String) xpath.evaluate("low/@data", node, XPathConstants.STRING);
		String high = (String) xpath.evaluate("high/@data", node, XPathConstants.STRING);
		String description = (String) xpath.evaluate("condition/@data", node, XPathConstants.STRING);
		
		ForecastDay day = new ForecastDay(dayOfWeek, Integer.parseInt(low), Integer.parseInt(high), description);
		forecast.addDay(day);
	}
}
