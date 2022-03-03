package library;

import java.util.ArrayList;

public class BookShelf {
	private ArrayList<Book> books = new ArrayList<Book>();		//ArrayList 객체 생성
	
	public int getBookCount() {			//책 권수는 size메소드로 받음
		return books.size();
	}

	public String[] getBooks() {
		String[] booksInfoStr = new String[books.size()];	//booksInfoStr 배열을 책 권수 길이로 생성
		for(int i = 0; i < books.size(); i++) {
			booksInfoStr[i] = books.get(i).print();			//각각의 요소에 책 정보 넣음
		}
		
		return booksInfoStr;
	}
	
	public Book selectBook(String title) {
		for(int i = 0; i < books.size(); i++) {				//책 권수만큼 돌며
			if(books.get(i).getTitle().equals(title)) {		//제목이 같으면
				return books.get(i);						//그 책을 반환
			}
		}
		
		return null;
	}
	
	public int selectedBookIndex(String title) {
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().equals(title)) {		//제목이 같으면
				return i;									//책 위치(인덱스)를 반환
			}
		}
		
		return -1;
	}

	public void addBook(Book book) {
		books.add(book);						//요소 추가하는 메소드 이용
	}
	
	public int deleteBook(String title) {
		if(books.size() == 0) return 0;		//책장에 책이 없으면 0 반환
		
		int selectedIndex = selectedBookIndex(title);
		if(selectedIndex == -1) return -1;	//책장에 title제목을 가진 책이 없으면 -1 반환
		
		books.remove(selectedIndex);		//둘다 아니면 그 책을  책장에서 삭제
		return 1;							//1 반환
	}
	
	public void clearBookShelf() {	
		books.clear();					//ArrayList 비우는 메소드 이용
	}
}
