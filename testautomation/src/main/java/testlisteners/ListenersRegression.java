package testlisteners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import utility.BasicUtils;

public class ListenersRegression implements ITestListener{
	private static Logger logger = LogManager.getLogger(ListenersRegression.class);

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("Starting onTestStart()");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("Starting onTestSuccess()");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("Starting onTestFailure()");
		logger.info("Fallo la prueba: "+result.getName());
		//Imprimir pantalla de la falla!
		BasicUtils.ScreenShot(BrowserFactory.driver, "FAILURE-"+result.getName(), FrameworkConfig.PATH_SCREENSHOOT_FAILURES);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("Starting onTestSkipped()");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info("Starting onTestFailedButWithinSuccessPercentage()");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info("Starting onStart()");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info("Starting onFinish()");
	}

}
