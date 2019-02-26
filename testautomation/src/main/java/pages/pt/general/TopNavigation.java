package pages.pt.general;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;

public class TopNavigation{
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(TopNavigation.class);
	
	public TopNavigation(WebDriver _driver) {
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver,FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT),this);
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
	private List<WebElement> lGlobalNav;
	
	@FindAll({@FindBy(how=How.CSS, using=".pt-globalNav .container .nav"), @FindBy(how=How.CSS, using="#navWrap .nav")})
	private WebElement globalNav;
	
	@FindBy(how=How.ID, using="q")
	private WebElement searchInput;
	
	@FindAll({@FindBy(how=How.CSS, using=".pt-globalNav .navbar-form .btn"), @FindBy(how=How.ID, using="busGlo")})
	private WebElement searchButton;
	
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
	
	public void clickVuelos() {
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
	
	public void typeSearch(String inputData) {
		searchInput.sendKeys(inputData);
	}
	
	public void typeEnterSearch() {
		searchInput.sendKeys(Keys.ENTER);
	}
	
	public void typeAndSubmitSearch(String inputData) {
		searchInput.sendKeys(inputData);
		searchInput.sendKeys(Keys.ENTER);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
}