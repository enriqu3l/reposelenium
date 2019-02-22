package pages.pt.paquetes.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;
import pages.pt.hoteles.HLGlobal;

public class PRLWidget extends HLGlobal {
	private WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = LogManager.getLogger(PRLWidget.class);

	public PRLWidget(WebDriver _driver) {
		super(_driver);
		this.driver = _driver;
		this.wait = new WebDriverWait(_driver, FWConfig.WAIT_PT);
		PageFactory.initElements(new AjaxElementLocatorFactory(_driver, FWConfig.WAITPF_PT), this);
	}

	// --------------- Widget Elements Package  ----------
	@FindBy(how = How.ID, using = "RB_ap_origin_flight")
	private WebElement widgetInputOrigin;
	
	@FindBy(how=How.ID, using="dateFrom")
	private WebElement widgetInputStartDate;
	
	@FindBy(how=How.ID, using="dateTo")
	private WebElement widgetInputEndDate;

	// ++++++++++++++++++++++++++ WIDGET FUNCTIONS ++++++++++++++++++++++++++++++++++++
	public String getOrigin() {
		return widgetInputOrigin.getAttribute("value").trim();
	}
	
	public void setOrigin(String origin) {
		widgetInputOrigin.sendKeys(origin);
	}
	
	public String getStartDate() {
		return widgetInputStartDate.getAttribute("value").trim();
	}
	
	public void setStarDate(String startDate) {
		widgetInputStartDate.sendKeys(startDate);
	}
	
	public String getEndDate() {
		return widgetInputEndDate.getAttribute("value").trim();
	}
	
	public void setEndDate(String endDate) {
		widgetInputEndDate.sendKeys(endDate);
	}
	
	//Me faltan agregar los campos de adultos, niños y edades niños
}