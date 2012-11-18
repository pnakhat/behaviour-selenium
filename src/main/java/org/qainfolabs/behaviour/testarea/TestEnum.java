package org.qainfolabs.behaviour.testarea;

public enum TestEnum {
	
	MONDAY("My Name is Monday"),TUESDAY("My Name is Tuesday");
	
	
	private String myName;

	private TestEnum(String s){
		this.setMyName(s);
		
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	

}
