package springsprout.modules.main;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SignupControllerTest {

	SignupController signupController;
	
	@Test
	public void checkmail() throws Exception {
		signupController = new SignupController();
		String result = signupController.checkmail("whiteship@keesun.com");
		assertEquals("redirect:http://www.keesun.com", result);
		result = signupController.checkmail("");
		assertEquals("redirect:/index", result);
	}
}
