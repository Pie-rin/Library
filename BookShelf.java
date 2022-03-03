package library;

import java.util.ArrayList;

public class BookShelf {
	private ArrayList<Book> books = new ArrayList<Book>();		//ArrayList ��ü ����
	
	public int getBookCount() {			//å �Ǽ��� size�޼ҵ�� ����
		return books.size();
	}

	public String[] getBooks() {
		String[] booksInfoStr = new String[books.size()];	//booksInfoStr �迭�� å �Ǽ� ���̷� ����
		for(int i = 0; i < books.size(); i++) {
			booksInfoStr[i] = books.get(i).print();			//������ ��ҿ� å ���� ����
		}
		
		return booksInfoStr;
	}
	
	public Book selectBook(String title) {
		for(int i = 0; i < books.size(); i++) {				//å �Ǽ���ŭ ����
			if(books.get(i).getTitle().equals(title)) {		//������ ������
				return books.get(i);						//�� å�� ��ȯ
			}
		}
		
		return null;
	}
	
	public int selectedBookIndex(String title) {
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {		//������ ������
				return i;									//å ��ġ(�ε���)�� ��ȯ
			}
		}
		
		return -1;
	}

	public void addBook(Book book) {
		books.add(book);						//��� �߰��ϴ� �޼ҵ� �̿�
	}
	
	public int deleteBook(String title) {
		if(books.size() == 0) return 0;		//å�忡 å�� ������ 0 ��ȯ
		
		int selectedIndex = selectedBookIndex(title);
		if(selectedIndex == -1) return -1;	//å�忡 title������ ���� å�� ������ -1 ��ȯ
		
		books.remove(selectedIndex);		//�Ѵ� �ƴϸ� �� å��  å�忡�� ����
		return 1;							//1 ��ȯ
	}
	
	public void clearBookShelf() {	
		books.clear();					//ArrayList ���� �޼ҵ� �̿�
	}
}
