package pages.pt;

import org.openqa.selenium.WebDriver;

import pages.pt.autobuses.HomeAutobusesPage;
import pages.pt.autos.HomeAutosPage;
import pages.pt.checkout.PayMethodPage;
import pages.pt.checkout.ThankYouPage;
import pages.pt.cruceros.HomeCrucerosPage;
import pages.pt.general.TopNavigation;
import pages.pt.grupos.HomeGruposPage;
import pages.pt.home.HomePage;
import pages.pt.hoteles.HomeHotelesPage;
import pages.pt.hoteles.HotelListPage;
import pages.pt.hoteles.ResDetailPage;
import pages.pt.hoteles.RoomListPage;
import pages.pt.ofertas.HomeOfertasPage;
import pages.pt.paquetes.HomePaquetesPage;
import pages.pt.paquetes.PackageListPage;
import pages.pt.paquetes.PackageResDetailPage;
import pages.pt.paquetes.PackageRoomListPage;
import pages.pt.tours.HomeToursPage;
import pages.pt.traslados.HomeTrasladosPage;
import pages.pt.vuelos.HomeVuelosPage;

public class Pages {
	private static WebDriver driver;
	
	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}
	
	public static Browser browser() {
		return new Browser(driver);
	}
	
	public static TopNavigation topnav() {
		return new TopNavigation(driver);
	}
	
	public static HomePage home(){
		return new HomePage(driver);
	}
	
	public static HomeHotelesPage hoteles() {
		return new HomeHotelesPage(driver);
	}
	
	public static HomePaquetesPage paquetes() {
		return new HomePaquetesPage(driver);
	}
	
	public static HomeVuelosPage vuelos() {
		return new HomeVuelosPage(driver);
	}
	
	public static HomeTrasladosPage traslados() {
		return new HomeTrasladosPage(driver);
	}
	
	public static HomeToursPage tours() {
		return new HomeToursPage(driver);
	}
	
	public static HomeAutosPage autos() {
		return new HomeAutosPage(driver);
	}
	
	public static HomeAutobusesPage autobuses() {
		return new HomeAutobusesPage(driver);
	}
	
	public static HomeCrucerosPage cruceros() {
		return new HomeCrucerosPage(driver);
	}
	
	public static HomeOfertasPage ofertas() {
		return new HomeOfertasPage(driver);
	}
	
	public static HomeGruposPage grupos() {
		return new HomeGruposPage(driver);
	}
	
	//++++++++++ Hoteles +++++++++++++
	public static HotelListPage hotelList(){
		return new HotelListPage(driver);
	}
	
	public static RoomListPage roomList(){
		return new RoomListPage(driver);
	}
	
	public static ResDetailPage resDetail(){
		return new ResDetailPage(driver);
	}
	
	public static PayMethodPage payMethod(){
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYou(){
		return new ThankYouPage(driver);
	}
	
	//++++++++++ Paquetes +++++++++++++
	public static PackageListPage packageList(){
		return new PackageListPage(driver);
	}
	
	public static PackageRoomListPage packageRoomList(){
		return new PackageRoomListPage(driver);
	}
	
	public static PackageResDetailPage packageResDetail() {
		return new PackageResDetailPage(driver);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++++++++ En caso de que no se quiera usar la funcion setDriver ++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static TopNavigation topnav(WebDriver driver) {
		return new TopNavigation(driver);
	}
	
	public static HomePage home(WebDriver driver){
		return new HomePage(driver);
	}
	
	public static HomeHotelesPage hoteles(WebDriver driver) {
		return new HomeHotelesPage(driver);
	}
	
	public static HomePaquetesPage paquetes(WebDriver driver) {
		return new HomePaquetesPage(driver);
	}
	
	public static HomeVuelosPage vuelos(WebDriver driver) {
		return new HomeVuelosPage(driver);
	}
	
	public static HomeTrasladosPage traslados(WebDriver driver) {
		return new HomeTrasladosPage(driver);
	}
	
	public static HomeToursPage tours(WebDriver driver) {
		return new HomeToursPage(driver);
	}
	
	public static HomeAutosPage autos(WebDriver driver) {
		return new HomeAutosPage(driver);
	}
	
	public static HomeAutobusesPage autobuses(WebDriver driver) {
		return new HomeAutobusesPage(driver);
	}
	
	public static HomeCrucerosPage cruceros(WebDriver driver) {
		return new HomeCrucerosPage(driver);
	}
	
	public static HomeOfertasPage ofertas(WebDriver driver) {
		return new HomeOfertasPage(driver);
	}
	
	public static HomeGruposPage grupos(WebDriver driver) {
		return new HomeGruposPage(driver);
	}
	
	//++++++++++ Hoteles +++++++++++++
	public static HotelListPage hotelList(WebDriver driver){
		return new HotelListPage(driver);
	}
	
	public static RoomListPage roomList(WebDriver driver){
		return new RoomListPage(driver);
	}
	
	public static ResDetailPage resDetail(WebDriver driver){
		return new ResDetailPage(driver);
	}
	
	public static PayMethodPage payMethod(WebDriver driver){
		return new PayMethodPage(driver);
	}
	
	public static ThankYouPage thankYou(WebDriver driver){
		return new ThankYouPage(driver);
	}
	
	//++++++++++ Paquetes +++++++++++++
	public static PackageListPage packageList(WebDriver driver){
		return new PackageListPage(driver);
	}
	
	public static PackageRoomListPage packageRoomList(WebDriver driver){
		return new PackageRoomListPage(driver);
	}
	
	public static PackageResDetailPage packageResDetail(WebDriver driver) {
		return new PackageResDetailPage(driver);
	}
}
