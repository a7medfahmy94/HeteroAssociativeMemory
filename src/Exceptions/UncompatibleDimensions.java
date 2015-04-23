package Exceptions;

public class UncompatibleDimensions extends Exception{
	private String errorMessage = "Dimension rules violated!!";
	public UncompatibleDimensions() {}
	public String what(){
		return errorMessage;
	}
}
