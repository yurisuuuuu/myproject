package member;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentControl {
	Scanner scan = new Scanner(System.in);
	List<Student> students = new ArrayList<>(); // 학생정보를 담고 있는 리스트
	StudentExpel studentExpel = new StudentExpel(); // 삭제된 정보를 담고있는 클래스의 객체 생성
	private static int lastId = 0; // 학생학번 마지막으로 생성된 번호 저장
	
	// 초기값
	void init_a() {
		String[][] studentData = {
			{"서만두", "15", "컴퓨터공학과"}, // 20251230001
			{"서사랑", "15", "경영학과"}, // 20254560002
			{"서초롱", "17", "컴퓨터공학과"} // 20251230003
		};
		
		for (String[] data : studentData) {
			String name = data[0];
			String age = data[1];
			String major = data[2];
			
			String studentId = StudentControl.generateId(major);
			Student s = new Student(name, age, studentId, major);
			students.add(s);
		}
	}
	
//	학생 학번 생성 : 20251230001 형태
	public static String generateId(String department) {
//		현재연도
		int enrollmentYear = Year.now().getValue();
		
//		학과코드
		String departmentCode = getDepartmentCode(department);
		
//		마지막 생성 번호 증가
		lastId++;
		
		return String.format("%d%s%04d", enrollmentYear, departmentCode, lastId);
	}
	
//	학과 코드 생성
	public static String getDepartmentCode(String department) {
		switch (department) {
		case "컴퓨터공학과" :
			return "123";
		case "경영학과" :
			return "456";
		default : 
			return "000";
		}
	}

	// 기본 메뉴 출력메소드
	String menuView() {
		System.out.println("================ MAIN MENU ===============");
		System.out.println(" 1. 추가, 2. 수정, 3. 삭제, 4. 보기, 기타. 종료");
		System.out.println("==========================================");
		System.out.println("메뉴를 선택해주세요 ! (1~4 (Exit : 0)) : ");
		String sel = scan.nextLine().trim();
		
		System.out.println("------------------------------------------");

		return sel; // 입력된 데이터를 리턴
	}

	void studentPlay(String sel) {
		// 화면 메뉴출력 1. 추가 , 2. 수정, 3. 삭제, 4. 보기, 기타. 종료
		switch (sel) {
		case "1": // 추가메소드()
			addStudent();
			break;
		case "2": // 수정메소드()
			updateView();
			break;
		case "3": // 삭제메소드()
			deleteStudent();
			break;
		case "4": // 보기메소드()
			studentView();
			break;
		default:
			System.out.println("프로그램을 종료합니다");
			System.exit(0);
		}
	}
	

	// 보기 메소드
	void studentView() {
	//1. 전체보기, 2. 학번검색, 3.삭제정보, 기타. 메뉴");
		System.out.println("보기메뉴를 선택하셨습니다.");
		System.out.println("============= SHOW STUDENT ===============");
		System.out.println("\n 1. 전체보기, 2. 학번검색, 3. 삭제정보, 기타. 메뉴 \n");
		System.out.println("==========================================");
		System.out.println("메뉴를 선택해주세요 ! (1~3 (Main Menu : 0)) : ");
		
		String sel = scan.nextLine().trim();
		System.out.println("------------------------------------------");

		switch (sel) {
		case "1" : // 전체보기
			viewAllStudents();
			break;
		case "2" : // 학번검색
			findStudentByNum();
			break;
		case "3" : // 삭제정보
			viewExpelStudents();
			break;
		default :
			System.out.println("메인 메뉴로 돌아갑니다.");
			break;
		}
	}
	
	// 삭제정보 보기 메소드
	private void viewExpelStudents() {
		// TODO Auto-generated method stub
		System.out.println("삭제정보 메뉴를 선택하셨습니다.");		
		System.out.println("============ DELETE STUDENT ==============");
		System.out.println(studentExpel.expelStudents);
		System.out.println("==========================================");
		
	}

	// 학번으로 찾기 메소드
	private void findStudentByNum() {
		// TODO Auto-generated method stub
		int index;
		String numInput = "";
		
		while (true) {
			System.out.println("보고싶은 학생의 학번을 입력해주세요 : ");
			numInput = scan.nextLine().trim();
			System.out.println("------------------------------------------");
			index = searchStudents(numInput);
			
			if (index != -1) {
				System.out.println();
				System.out.println("========== SHOW CHOICE STUDENT ===========");
				System.out.println("\n " + students.get(index).toString() + "\n");
				System.out.println("========================================== \n");
				break;
			} else {
				System.out.println("Error! 입력하신 학번의 학생을 찾을 수 없습니다! 다시 확인후 입력해주세요! ");
				System.out.println("------------------------------------------");
			}
		}

	}

	// 학생 전체보기 메소드
	private void viewAllStudents() {
		// TODO Auto-generated method stub
		System.out.println("전체보기 메뉴를 선택하셨습니다.");
		System.out.println("=========== SHOW ALL STUDENT =============");
		if (students.isEmpty()) { // 학생정보가 있는지 먼저 확인
			System.out.println("등록된 학생이 없습니다.");
		} else {
			for (Student s : students) {
				System.out.println("\n" + s.toString());
			}
		}
		System.out.println("==========================================");
	}

	// 추가 메소드
	private void addStudent() {
		// 정보를 입력받아 students 배열에 추가
		System.out.println("추가 메뉴를 선택하셨습니다.");
		String name = "";
		String age = "";
		String major = "";
		
		System.out.println("============== ADD STUDENT ===============");
		while (true) { // 이름입력
			System.out.println("\n추가할 학생의 이름을 입력해주세요 : ");
			name = scan.nextLine().trim();
			
			if (name.matches("^[a-zA-Zㄱ-힣]+$")) { // 문자만 입력가능 (한글, 영문)
				break;
			} else {				
				System.out.println("------------------------------------------");
				System.out.println("Error! 문자만 입력해주세요!");
				System.out.println("------------------------------------------");
			}
		}
		System.out.println("------------------------------------------");
		
		while (true) { // 나이입력
			System.out.println("추가할 학생의 나이를 입력해주세요 : ");
			age = scan.nextLine().trim();
			
			if (age.matches("^[0-9]+$")) { // 숫자만 입력가능
				break;
			} else {
				System.out.println("------------------------------------------");
				System.out.println("Error! 숫자만 입력해주세요!");
				System.out.println("------------------------------------------");
			}
		}
		System.out.println("------------------------------------------");
		
		while (true) { // 학과입력
			System.out.println("추가할 학생의 학과를 입력해주세요 : ");
			major = scan.nextLine().trim();
			
			if (!getDepartmentCode(major).equals("000")) {
				break;
			} else {
				System.out.println("------------------------------------------");
				System.out.println("Error! 학과 목록에 없는 학과 입니다! 확인후 다시 입력해주세요!");
				System.out.println("------------------------------------------");
			}
		}
		System.out.println("==========================================");
		
//		학생 객체 생성 및 추가
		String studentId = StudentControl.generateId(major);
		Student s = new Student(name, age, studentId, major);
		students.add(s);
		
		System.out.println("\n----------------- PREVIEW ----------------");
		System.out.println("\n 이름 : " + name + "\n 나이 : " + age + "\n 학번 : " + studentId + "\n 학과 : " + major + "\n");
		System.out.println("------------------------------------------");
		System.out.println("학생 정보가 성공적으로 추가되었습니다.");
	}

	// 수정 메인 메소드 : 학번으로 검색하고 있으면 수정메소드호출 없으면 재입력
	public void updateView() {
		int index;
		String studentId = "";

		System.out.println("수정하기 메뉴를 선택하셨습니다");
		while (true) {
			System.out.println("============== MODIFY MENU ===============");
			System.out.print("[학생정보 수정] 학번을 입력하세요 : ");
			studentId = scan.nextLine().trim();
			index = searchStudents(studentId); // 학번으로 검색
			if (index >= 0) { // 인덱스값이 있다면
				Student modifyStudent = students.get(index);
				System.out.println("------------------------------------------");
				System.out.println("'" + modifyStudent.getStudentNum() + "'" + " 학번에 해당하는 학생 정보를 수정하시겠습니까 ? (y or n)" );
				String modifyAnswer = scan.nextLine().trim();
				
				if (modifyAnswer.equals("y")) {
					updateStudent(index); // 수정
					break;					
				} else {
					System.out.println("취소하셨습니다.");
				}
				
			} else {
				System.out.println("Error! 입력하신 학번의 학생을 찾을 수 없습니다! 다시 확인후 입력해주세요!"); // 입력오류시 재입력
			}
		}
	}

	// 검색 메소드(학번 기준)
	int searchStudents(String studentNum) {
		for (int index=0; index<students.size(); index++) {
			Student s = students.get(index);
			if (s.getStudentNum().equals(studentNum)) {
				return index; // 검색된 학생정보의 인덱스, 없다면 -1을 리턴
			}
		}
		return -1;
	}

	// 수정 메소드
	boolean updateStudent(int index) {
		// 수정할 항목 선택 [1. 이름] [2. 나이] [3. 학번] [4. 전공] 
		Student modifyStudent = students.get(index);
		
		while (true) {
			System.out.println("\n----------------- PREVIEW ----------------");
			System.out.println("\n" + modifyStudent + "\n");
			System.out.println("------------------------------------------");
			
			System.out.println("\n수정을 원하는 메뉴룰 선택해주세요 ! (1~4) : \n");
			
			System.out.println("============= MODIFY CHOICE ==============");
			System.out.println("\n 1. 이름, 2. 나이, 3. 학번, 4. 전공 0.종료 \n");
			System.out.println("==========================================");
			String modifyChoice = scan.nextLine().trim();
			
			if (modifyChoice.equals("0")) {
				System.out.println("수정하기를 종료합니다.");
				break;
			}
			
			switch (modifyChoice) {
			case "1" :
				while (true) {
					System.out.println("새로운 이름을 입력해주세요 : ");
					String newName = scan.nextLine().trim();
					
					if (modifyStudent.getName().equals(newName)) {
						System.out.println("------------------------------------------");
						System.out.println("Error! 기존 이름과 동일합니다 ! 다시 입력해주세요 !");
						System.out.println("------------------------------------------");
					} else {					
						modifyStudent.setName(newName);
						System.out.println("------------------------------------------");
						System.out.println("입력하신 이름 " + newName + "(으)로 변경되었습니다." );
						System.out.println("------------------------------------------");
						break;
					}	
				}
				break;
			case "2" :
				while (true) {
					System.out.println("새로운 나이를 입력해주세요 : ");
					String newAge = scan.nextLine().trim();
					
					if (newAge.matches("^[0-9]+$")) {
						modifyStudent.setAge(newAge);
						System.out.println("------------------------------------------");
						System.out.println("입력하신 나이 " + newAge + "로 변경되었습니다.");
						System.out.println("------------------------------------------");
						break;
					} else {
						System.out.println("------------------------------------------");
						System.out.println("Error! 숫자만 입력해주세요!");
						System.out.println("------------------------------------------");
					}
				}
				break;
			case "3" :
				while (true) {
					System.out.println("새로운 학번으로 변경하시겠습니까 ? (y or n) ");
					String modifyAnswr = scan.nextLine().trim();
					
					if (modifyAnswr.equals("y")) {
						String newStudentId = StudentControl.generateId(modifyStudent.getMajor());
						modifyStudent.setStudentNum(newStudentId);
						System.out.println("새로운 학번 " + newStudentId + "로 변경되었습니다.");
					} else {
						System.out.println("취소하셨습니다.");
						break;
					}
				}
				break;
			case "4" :
				while (true) {
					System.out.println("새로운 전공을 입력해주세요 : ");
					String newMajor = scan.nextLine().trim();
					
					if (getDepartmentCode(newMajor).equals("000")) {
						modifyStudent.setAge(newMajor);
						System.out.println("------------------------------------------");
						System.out.println("입력하신 나이 " + newMajor + "로 변경되었습니다.");
						System.out.println("------------------------------------------");
						break;
					} else {
						System.out.println("------------------------------------------");
						System.out.println("Error! 학과 목록에 없는 학과 입니다! 확인후 다시 입력해주세요!");
						System.out.println("------------------------------------------");
					}
				}
				break;
			default : 
				System.out.println("Error! 잘못 선택하셨습니다! 메뉴에 있는 번호만 입력해주세요!");
				break;
			}
			System.out.println("==========================================");
		}
		return true;			
	}

	// 삭제 메소드
	void deleteStudent() {
		//[학생정보 삭제] 학번을 입력해주세요 : 
		System.out.println("삭제하기 메뉴를 선택하셨습니다.");
		
		System.out.println("============== DELETE MENU ===============");
		
		while (true) {
			System.out.println("삭제를 원하는 학생의 학번을 입력해주세요 : ");
			String deletStudentInput = scan.nextLine().trim();
			int index = searchStudents(deletStudentInput);
			if (index != -1) {
				Student sDelete = students.get(index);
				System.out.println("------------------------------------------");
				System.out.println(sDelete.getName() + " 학생의 정보를 정말 삭제하시겠습니까 ? (y or n) :");
				String realDeleteAnswer = scan.nextLine().trim().toLowerCase();
				System.out.println("------------------------------------------");

				if (realDeleteAnswer.equals("y")) {
//					리무브 전 학생삭제클래스 리스트에 저장해두기
					studentExpel.addExpelStudents(sDelete);
					
//					저장후 리무브
					students.remove(index);
				} else {
					System.out.println("삭제하기가 취소되었습니다.");
				}
				break;
				
			} else {
				System.out.println("------------------------------------------");
				System.out.println("Error! 입력하신 학번의 학생을 찾을 수 없습니다! 다시 확인후 입력해주세요!");
				System.out.println("------------------------------------------");
			}
		}			
		System.out.println("==========================================");
	}

}
