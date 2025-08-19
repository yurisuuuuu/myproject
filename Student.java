/*	멤버필드는 모두 String으로 작성하기
	name;
	age;
	studentNum;
	major;
*/
package member;

public class Student {
//	1. 변수
	private String name;
	private String age;
	private String studentNum;
	private String major;
	
//	2. 생성자
	public Student(){}
	
	/**
	 * 
	 * @param name
	 * @param age
	 * @param studentNum
	 * @param major
	 */
	
	
	public Student(String name, String age, String studentNum, String major) {
		super();
		this.name = name;
		this.age = age;
		this.studentNum = studentNum;
		this.major = major;
	}

	
//	3. getter/setter
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getStudentNum() {
		return studentNum;
	}
	
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}

	
//	4. toString
	@Override
	public String toString() {
		
		
		return " [Student Info] \n 이름 : " + name + "\n 나이 : " + age + "\n 학번 : " + studentNum + "\n 학과 : " + major +"\n";
	}
	
	// Test branch edit
}
