package library;

import java.util.HashMap;

public class FamilyLogin {
	private  HashMap<String, String> family = new HashMap<String, String>();
	
	public FamilyLogin() {		//기본 생성자
		family.put("admin", "oop123");
	}
	
	public boolean IsMyFamily(String id, String password) {
		boolean val = false;
		if(id.isEmpty())	throw new NullPointerException("id가 존재하지 않습니다.");		//id가 비어있으면 예외 발생
		if(!id.equals(password)) throw new NumberFormatException("패스워드가 맞지 않습니다.");	//id와 password가 다르면 예외 발생
		if(id.equals(password))			//id와 password가 같으면 true 반환, 그 외에는 false 반환
			val = true;
		return val;
	}
}