package library;

import java.util.Scanner;

public class BookMgt {
	private static Scanner scan;
	private static BookShelf bookShelf;
	private static FamilyLogin login = new FamilyLogin();		//로그인 객체 생성

	public static void main(String[] args) {
		boolean start = false;		//로그인 성공 여부에 따른 프로그램 시작 여부를 결정할 변수
		int cnt = 0;				//로그인 실패 횟수를 기록할 변수
		bookShelf = new BookShelf();
		scan = new Scanner(System.in);
		
		for(int i = 0; i < 3; i++) {					//3번 반복
			System.out.print("id를 입력하세요 : ");
			String id = scan.nextLine();
			System.out.print("password를 입력하세요 : ");
			String password = scan.nextLine();
			
			try{
				if(login.IsMyFamily(id, password)) {	//id와 password가 같으면
					start = true;						//while문 시작 가능
					break;								//for loop를 나옴
				}
			}
			catch(NullPointerException e) {				//IsMyFamily()에서 id가 비어있으면
				System.out.println(e.getMessage());		//메세지를 출력하고 카운트를 올림
				cnt++;
			}
			catch(NumberFormatException e) {			//IsMyFamily()에서 id와 password가 다르면
				System.out.println(e.getMessage());		//메세지를 출력하고 카운트를 올림
				cnt++;
			}
		}
		
		if(cnt == 3)			//로그인 3번 실패시
			System.out.println("로그인에 실패했습니다.");
		
		while (start) {			//로그인에 성공한 경우만 시작
			int menu = showMenu();
			
			while(menu == -1) {		//메뉴 번호에 오류가 있으면 반복해서 메뉴 번호를 받음
				menu = showMenu();
			}
			
			if (menu == 7)
				break;

			switch(menu) {
			case 1: insertBookInfo(); break;
			case 2: showAllBookInfo(); break;
			case 3: selectBookInfo(); break;
			case 4: updateBookInfo(); break;
			case 5: removeBookFromBookShelf(); break;
			case 6: clearBookShelf(); break;
			}
			
			System.out.println();
		}

		System.out.println("프로그램을 종료합니다.");
		scan.close();
	}

	public static int showMenu() {
		int menu = -1;
		System.out.println("========================================================");
		System.out.println("1.책 추가 2.모든 책 검색 3.책 검색 4.책 수정 5.책 삭제 6.모든 책 삭제 7.종료");
		System.out.println("========================================================");
			
		System.out.print("메뉴를 선택하세요 >> ");
		try{
			menu = Integer.parseInt(scan.nextLine());
			
			//입력받은 메뉴 번호가 범위 안의 숫자가 아닐 경우 예외 강제 발생
			if(!(menu == 1 || menu == 2 || menu == 3 || menu == 4 || menu == 5 || menu == 6 || menu == 7))
				throw new ArrayIndexOutOfBoundsException("메뉴 번호 범위 안의 숫자를 입력하세요!(1~7)");
		}
		catch(NumberFormatException e) {			//입력받은 것이 숫자가 아닐 경우
			System.out.print("숫자를 입력하세요!(1~7)");
		}
		catch(ArrayIndexOutOfBoundsException e) {	//입력받은 수가 범위 내의 숫자가 아닐 경우
			System.out.print(e.getMessage());
		}
		
		System.out.println();
		return menu;
	}
	
	public static void insertBookInfo() {
		System.out.println("책의 정보를 입력하세요.(제목, 출판사, 책종류 순으로 입력)");
		System.out.print("제목 >> ");
		String title = scan.nextLine();
		System.out.print("출판사 >> ");
		String publisher = scan.nextLine();
		System.out.print("책종류 >> ");
		String type = scan.nextLine();
		
		System.out.println();
		System.out.println("저자 정보를 입력하세요.(이름, 생년월일, 성별(남:1, 여:2), 경력 순으로 입력)");
		System.out.print("이름 >> ");
		String name = scan.nextLine();
		System.out.print("생년월일 >> ");
		String birthdate = scan.nextLine();
		System.out.print("성별 >> ");
		int g = Integer.parseInt(scan.nextLine());
		System.out.print("경력 >> ");
		String career = scan.nextLine();
		
		Gender gender = Gender.UNKNOWN;
		if(g == 1) gender = Gender.MAN;
		else if(g == 2) gender = Gender.WOMAN;
		
		Author author = new Author(name, birthdate, gender, career);
		Book book = new Book(title, publisher, type, author);
		
		bookShelf.addBook(book);
	}
	
	public static void showAllBookInfo() {
		String[] bookInfos = bookShelf.getBooks();
		
		String deco = new String(new char[60]).replace("\0", "=");
		System.out.println(deco);
		System.out.println(String.format("총 %d권의 책이 있습니다.", bookShelf.getBookCount()));
		System.out.println(deco);
		
		for(String s : bookInfos){
			System.out.println(s);
		}
	}
	
	public static void selectBookInfo() {
		System.out.print("검색할 책의 제목을 입력하세요 >> ");
		String title = scan.nextLine();
		
		Book selectedBook = bookShelf.selectBook(title);
		if(selectedBook == null){
			System.out.println("검색된 책이 없습니다.");
			return;
		}
		
		System.out.println(selectedBook.printDetatil());		
	}

	public static void updateBookInfo() {
		System.out.print("수정할 책의 제목을 입력하세요 >> ");
		String title = scan.nextLine();

		Book selectedBook = bookShelf.selectBook(title);
		if(selectedBook == null){
			System.out.println("수정할 책이 없습니다.");
			return;
		}
		
		System.out.print("새 제목을 입력하세요 >> ");
		String newtitle = scan.nextLine();
		
		System.out.print("출판사도 수정하시겠습니까?(y or n) >> ");
		String confirm = scan.nextLine().toLowerCase();
		
		if(confirm.equals("y")){
			System.out.print("새 출판사를 입력하세요 >> ");
			String newpublisher = scan.nextLine();
			
			selectedBook.update(newtitle, newpublisher);
		}
		else{
			selectedBook.update(newtitle);
		}
		
		System.out.println("수정이 완료되었습니다.");
	}

	public static void removeBookFromBookShelf() {
		System.out.print("삭제할 책의 제목을 입력하세요 >> ");
		String title = scan.nextLine();

		Book selectedBook = bookShelf.selectBook(title);
		if(selectedBook == null){
			System.out.println("삭제할 책이 없습니다.");
			return;
		}
		
		System.out.println(selectedBook.printDetatil());		
		System.out.print("책을 정말 삭제하세겠습니까?(y or n) >> ");
		String confirm = scan.nextLine().toLowerCase();
		
		if(confirm.equals("n")) return;
		
		int msg = bookShelf.deleteBook(title);
		if(msg == 1){
			System.out.println("책을 삭제하였습니다.");
		}
	}
	
	public static void clearBookShelf() {
		System.out.print("책을 모두 삭제하세겠습니까?(y or n) >> ");
		String confirm = scan.nextLine().toLowerCase();
		
		if(confirm.equals("y")){
			bookShelf.clearBookShelf();
			System.out.println("책을 모두 삭제하였습니다.");
		}
	}	
}
