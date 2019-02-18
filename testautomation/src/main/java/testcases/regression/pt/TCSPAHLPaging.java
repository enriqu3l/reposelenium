package testcases.regression.pt;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testbases.TBRegressionSPAHL;

public class TCSPAHLPaging extends TBRegressionSPAHL{
	
	@Test (enabled=true)
	public void test1(){
		Reporter.log("Starting test1");
		logger.info("Starting test1");
	}
	
	@Test (enabled=true)
	public void test2(){
		Reporter.log("Starting test2");
		logger.info("Starting test2");
	}
	
	@Test (enabled=true)
	public void test3(){
		Reporter.log("Starting test3");
		logger.info("Starting test3");
	}
	
	@Test (enabled=true)
	public void test4(){
		Reporter.log("Starting test4");
		logger.info("Starting test4");
	}
}
