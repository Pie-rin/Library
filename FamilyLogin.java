package library;

import java.util.HashMap;

public class FamilyLogin {
	private  HashMap<String, String> family = new HashMap<String, String>();
	
	public FamilyLogin() {		//�⺻ ������
		family.put("admin", "oop123");
	}
	
	public boolean IsMyFamily(String id, String password) {
		boolean val = false;
		if(id.isEmpty())	throw new NullPointerException("id�� �������� �ʽ��ϴ�.");		//id�� ��������� ���� �߻�
		if(!id.equals(password)) throw new NumberFormatException("�н����尡 ���� �ʽ��ϴ�.");	//id�� password�� �ٸ��� ���� �߻�
		if(id.equals(password))			//id�� password�� ������ true ��ȯ, �� �ܿ��� false ��ȯ
			val = true;
		return val;
	}
}