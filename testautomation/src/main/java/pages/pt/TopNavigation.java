package pages.pt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TopNavigation extends HomePage{

	public TopNavigation(WebDriver _driver) {
		super(_driver);
	}
	
	//-------------------- TOP LINKS -----------------------
	@FindAll({@FindBy(how=How.CSS, using=".navbar-header a"), @FindBy(how=How.CSS, using="#logo a")})
	private WebElement logo;
	
	@FindBy(how=How.ID, using="phoneLang-toggle")
	private WebElement phoneLang;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Puntos de Atención")
	private WebElement puntosDeAtencion;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Revista")
	private WebElement revista;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Consulta tu itinerario")
	private WebElement consultaItinerario;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Ayuda")
	private WebElement ayuda;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Iniciar sesión")
	private WebElement iniciarSesion;
	
	//-------------------- GLOBAL NAV-----------------------
	@FindBy(how=How.CSS, using=".pt-globalNav .container .nav li")
	@CacheLookup
	private List<WebElement> lGlobalNav;
	
	@FindBy(how=How.CSS, using=".pt-globalNav .container .nav")
	@CacheLookup
	private WebElement globalNav;
	
	private By hoteles = new By.ByLinkText("Hoteles");
	private By paquetes = new By.ByLinkText("Paquetes");
	private By vuelos = new By.ByLinkText("Vuelos");
	private By traslados = new By.ByLinkText("Traslados");
	private By tours = new By.ByLinkText("Tours");
	private By autos = new By.ByLinkText("Autos");
	private By autobuses = new By.ByLinkText("Autobuses");
	private By cruceros = new By.ByLinkText("Cruceros");
	private By ofertas = new By.ByLinkText("Ofertas");
	private By grupos = new By.ByLinkText("Grupos");
	
	public void clickHoteles() {
		globalNav.findElement(hoteles).click();
	}
	
	public void clickPaquetes() {
		globalNav.findElement(paquetes).click();
	}
	
	public void click() {
		globalNav.findElement(vuelos).click();
	}
	
	public void clickTraslados() {
		globalNav.findElement(traslados).click();
	}
	
	public void clickTours() {
		globalNav.findElement(tours).click();
	}
	
	public void clickAutos() {
		globalNav.findElement(autos).click();
	}
	
	public void clickAutobuses() {
		globalNav.findElement(autobuses).click();
	}
	
	public void clickCruceros() {
		globalNav.findElement(cruceros).click();
	}
	
	public void clickOfertas() {
		globalNav.findElement(ofertas).click();
	}
	
	public void clickGrupos() {
		globalNav.findElement(grupos).click();
	}
	
	public void clickLogo() {
		logo.click();
	}
	
	public void clickPuntosDeAtencion() {
		puntosDeAtencion.click();
	}
	
	public void clickRevista() {
		revista.click();
	}
	
	public void clickConsultaItinerario() {
		consultaItinerario.click();
	}
	
	public void clickAyuda() {
		ayuda.click();
	}
	
	public void clickIniciarSesion() {
		iniciarSesion.click();
	}
}