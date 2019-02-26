package utility;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class BasicUtils {

	/**
	 * @param url      - String - Url en la que se desea buscar
	 * @param expected - String - Valor a evaluar que este presente en la URL
	 * @param encoding - Boolean - Para indicar si se quiere codificar el parametro
	 *                 antes de buscarlo en la URL
	 * @author enrique.lopez
	 */
	public static boolean checkUrlContains(String url, String expected, boolean encoding) {
		String expected2 = expected;
		if (encoding) {
			try {
				expected2 = URLEncoder.encode(expected, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return url.contains(expected2);
	}

	/**
	 * Esta funcion revisa que un determinado valor se encuentre en un parametro de
	 * una url
	 * 
	 * @param url       - Url en la que se desea validar la existencia del valor
	 * @param paramName - Parametro que se desea obtener de la url
	 * @param expected  - Valor que se desea comparar con el valor del parametro
	 *                  obtenido
	 * @return
	 */
	public static boolean checkValueOnUrlParam(String url, String paramName, String expected) {
		System.out.println(
				"checkValueOnUrlParam-> url:(" + url + ") paramName:(" + paramName + ") expected:(" + expected + ")");
		String urlParamValue = getParamValue(url, paramName);
		System.out.println("checkValueOnUrlParam-> urlParamValue:(" + urlParamValue + ")");
		String expectedValue = expected;
		if (paramName.equals("checkin") || paramName.equals("checkout")) {
			System.out.println("checkValueOnUrlParam-> comparing dates...");
			LocalDate ldUrlParamValue = convertToLocalDate(urlParamValue);
			LocalDate ldExpectedValue = convertToLocalDate(expectedValue);
			return ldUrlParamValue.equals(ldExpectedValue);
		}
		return urlParamValue.equals(expectedValue);
	}

	public static String getParamValue(String url, String paramName) {
		List<NameValuePair> queryParams = null;
		try {
			queryParams = new URIBuilder(url).getQueryParams();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return queryParams.stream().filter(param -> param.getName().equalsIgnoreCase(paramName))
				.map(NameValuePair::getValue).findFirst().orElse("");
	}

	// Esta funcion devuelve un map con todos los parametros
	public static Map<String, List<String>> getQueryParams(String url) {
		try {
			Map<String, List<String>> params = new HashMap<String, List<String>>();
			String[] urlParts = url.split("\\?");
			if (urlParts.length > 1) {
				String query = urlParts[1];
				for (String param : query.split("&")) {
					String[] pair = param.split("=");
					String key = URLDecoder.decode(pair[0], "UTF-8");
					String value = "";
					if (pair.length > 1) {
						value = URLDecoder.decode(pair[1], "UTF-8");
					}

					List<String> values = params.get(key);
					if (values == null) {
						values = new ArrayList<String>();
						params.put(key, values);
					}
					values.add(value);
				}
			}
			return params;
		} catch (UnsupportedEncodingException ex) {
			throw new AssertionError(ex);
		}
	}

	/**
	 * Devuelve una URL de tipo:
	 * ->https://www.pricetravel.com/hoteles/cancun-area?chekin=2019-02-20&checkout=2019-02-23&placetype=3&placeid=69364&source=SPA-Hotel-List&rooms=1&room1.adults=2&room1.kids=&room1.agekids=&agekids=
	 * 
	 * @param host      - example: www.pricetravel.com
	 * @param path      - example: hoteles/cancun-area
	 * @param startDate - example: 2019-03-01
	 * @param endDate   - example: 2019-03-03
	 * @param placetype - example: 3
	 * @param placeid   - example: 69364
	 * @param source    - example: SPA-Hotel-List
	 * @param page      - example: 1
	 * @param rooms     - exmaple: 1
	 * @param adults    - example: 2
	 * @return
	 */
	public static String createUrlLandingPageHL(String host, String path, String startDate, String endDate, String placetype,
			String placeid, String source, String page, String rooms, String adults) {
		String checkin = startDate;
		String checkout = endDate;
		try {
			checkin = URLEncoder.encode(startDate, "UTF-8");
			checkout = URLEncoder.encode(endDate, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		URIBuilder builder = new URIBuilder();
		builder.setScheme("https");
		builder.setHost(host.toLowerCase().trim());
		builder.setPath(path.toLowerCase().trim());
		builder.addParameter("checkin", checkin.trim());
		builder.addParameter("checkout", checkout.trim());
		builder.addParameter("placetype", placetype.trim());
		builder.addParameter("placeid", placeid.trim());
		builder.addParameter("source", source.trim());
		builder.addParameter("page", page.trim());
		builder.addParameter("rooms", rooms.trim());
		builder.addParameter("room1.adults", adults.trim());
		builder.addParameter("room1.kids", "");
		builder.addParameter("room1.agekids", "");
		builder.addParameter("agekids", "");
		String url = "";
		try {
			url = builder.build().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (url.isEmpty()) {
			System.out.println("Error al construir la url");
		}
		return url;
	}

	public static String createUrlLandingPageHL(List<String> data) {
		String host = data.get(1);
		String path = data.get(2) + "/" + data.get(3);
		int startDay = Integer.parseInt(data.get(4).trim());
		String startDate = LocalDate.now().plusDays(startDay).toString();
		int endDay = Integer.parseInt(data.get(4).trim()) + Integer.parseInt(data.get(5).trim());
		String endDate = LocalDate.now().plusDays(endDay).toString();
		String placetype = data.get(6);
		String placeid = data.get(7);
		String source = data.get(8);
		String page = data.get(9);
		String rooms = data.get(10);
		String adults = data.get(11);

		String url = createUrlLandingPageHL(host, path, startDate, endDate, placetype, placeid, source, page, rooms, adults);
		return url;
	}

	// Makes a simple scrooll in order to see the buttons, is not relevant!!!
	// JavascriptExecutor jse = (JavascriptExecutor)driver;
	// jse.executeScript("window.scrollBy(0,700)");
	// -----------------------------------------------------

	// ----------- DATE & TIME Functions -------------------
	// Funciona a partir de Java version 8
	public static String getCurrentDatePlusMonth(int plusMonth) {
		if (plusMonth > 12) {
			plusMonth = 12;
		} // Aqui lo limito a máximo un año de avance
		LocalDate futureDate = LocalDate.now().plusMonths(plusMonth);
		return futureDate.toString("dd/MM/yyyy");
	}

	public static int toMonthNumber(String month) {
		month = month.trim();
		// Lanza una excepcion si el parametro no es valido
		switch (month) {
		case "ENERO":
			return 1;
		case "FEBRERO":
			return 2;
		case "MARZO":
			return 3;
		case "ABRIL":
			return 4;
		case "MAYO":
			return 5;
		case "JUNIO":
			return 6;
		case "JULIO":
			return 7;
		case "AGOSTO":
			return 8;
		case "SEPTIEMBRE":
			return 9;
		case "OCTUBRE":
			return 10;
		case "NOVIEMBRE":
			return 11;
		case "DICIEMBRE":
			return 12;
		case "JANUARY":
			return 1;
		case "FEBRUARY":
			return 2;
		case "MARCH":
			return 3;
		case "APRIL":
			return 4;
		case "MAY":
			return 5;
		case "JUNE":
			return 6;
		case "JULY":
			return 7;
		case "AUGUST":
			return 8;
		case "SEPTEMBER":
			return 9;
		case "OCTOBER":
			return 10;
		case "NOVEMBER":
			return 11;
		case "DECEMBER":
			return 12;
		default:
			throw new IllegalArgumentException("FIF>>>toMonthNumber->El parametro: " + month + ", no es valido!.");
		}
	}

	public static String toMonthString(int month) {
		String monthString = "";

		switch (month) {
		case 1:
			monthString = "JANUARY";
			break;
		case 2:
			monthString = "FEBRUARY";
			break;
		case 3:
			monthString = "MARCH";
			break;
		case 4:
			monthString = "APRIL";
			break;
		case 5:
			monthString = "MAY";
			break;
		case 6:
			monthString = "JUNE";
			break;
		case 7:
			monthString = "JULY";
			break;
		case 8:
			monthString = "AUGUST";
			break;
		case 9:
			monthString = "SEPTEMBER";
			break;
		case 10:
			monthString = "OCTOBER";
			break;
		case 11:
			monthString = "NOVEMBER";
			break;
		case 12:
			monthString = "DECEMBER";
			break;
		default:
			monthString = "Invalid month";
			break;
		}
		return monthString;
	}

	/**
	 * Convierte String Date, del formato "dd/MM/yyyy" al formato "yyyy/MM/dd" Esta
	 * funcion solo acepta el formato: "dd/MM/yyyy"
	 * 
	 * @param date String
	 * @return Regresa el String con el formato "yyyy/MM/dd"
	 */
	public static String fromyyyyMMddToddMMyyyyFormat(String date) {
		LocalDate ld = LocalDate.parse(date, DateTimeFormat.forPattern("dd/MM/yyyy"));
		return ld.toString("yyyy/MM/dd");
	}

	/**
	 * Esta funcion solo acepta el formato: " mes yyyy ", "mes yyyy" o "mesyyyy"
	 * 
	 * @param monthYear String
	 * @return Regresa el String con el formato "dd/MM/yyyy"
	 */
	public static String toddMMyyyyFormat(String monthYear) {
		// Esta funcion solo acepta el formato: " mes yyyy ", "mes yyyy" o "mesyyyy"
		String monthYearTrimed = monthYear.trim();
		String monthWord = monthYearTrimed.substring(0, monthYearTrimed.length() - 4).trim().toUpperCase();
		int month = BasicUtils.toMonthNumber(monthWord);
		int year = Integer.parseInt(monthYearTrimed.substring(monthYearTrimed.length() - 4, monthYearTrimed.length()));

		String monthString = "";
		if (Integer.toString(month).length() == 1) {
			monthString = "0" + month;
		} else {
			monthString = Integer.toString(month);
		}

		return "01/" + monthString + "/" + year;
	}

	public static int monthDiference(String expectedDate, String actualDate) {
		// Esta funcion solo acepta el formato dd/MM/yyyy!!
		LocalDate eDate = LocalDate.parse(expectedDate, DateTimeFormat.forPattern("dd/MM/yyyy"));
		LocalDate aDate = LocalDate.parse(actualDate, DateTimeFormat.forPattern("dd/MM/yyyy"));

		int yearDifference = eDate.getYear() - aDate.getYear();
		int monthDifference = eDate.getMonthOfYear() - aDate.getMonthOfYear();
		int TotalMountDifference = 0;
		if (yearDifference != 0 || monthDifference != 0) {
			TotalMountDifference = monthDifference + (yearDifference * 12);
		}
		return TotalMountDifference;
	}

	/**
	 * Esta funcion comvierte solo fechas del tipo "yyyy-MM-dd" "yyyy/MM/dd" y
	 * "dd/MM/yyyy" Cualquier otro formato devolvera una excepcion o null
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate convertToLocalDate(String date) {
		LocalDate ld = null;
		if (date.contains("-")) {
			ld = LocalDate.parse(date.trim());
		} else if (date.contains("/")) {
			if (date.indexOf("/") >= 4) {
				ld = LocalDate.parse(date.trim(), DateTimeFormat.forPattern("yyyy/MM/dd"));
			} else if (date.indexOf("/") <= 2) {
				ld = LocalDate.parse(date.trim(), DateTimeFormat.forPattern("dd/MM/yyyy"));
			}
		}
		return ld;
	}
}