package org.qainfolabs.behaviour.testarea;

import org.junit.Test;

public class TestEnumUse {

	@Test
	public void useEnum(){
		System.out.println(TestEnum.MONDAY.getMyName());
		System.out.println(TestEnum.TUESDAY.getMyName());
		System.out.println();
	}
	
	@Test
	public void testThread(){
		ThreadClass t1 = new ThreadClass();
		t1.run();
		ThreadClass t2 = new ThreadClass();
		t2.run();
	}
}
