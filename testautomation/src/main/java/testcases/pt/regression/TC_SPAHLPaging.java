package testcases.pt.regression;

import org.testng.Reporter;
import org.testng.annotations.Test;

import testbases.pt.TB_Regression;

public class TC_SPAHLPaging extends TB_Regression{
	
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
