package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버변수로 갖는
 * 		Student 클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 		
 * 		이 Student 객체들은 List에 저장하여 관리한다.
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 하시오.)
 * 		(학번 정렬기준은 Student 클래스 자체에서 제공하도록 하고,
 * 		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */

public class T08_StudentTest {

	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student(2020060206, "변학도", 53, 88, 61));
		stuList.add(new Student(2020060205, "홍길동", 90, 80, 70));
		stuList.add(new Student(2020060204, "성준향", 92, 83, 77));
		stuList.add(new Student(2020060203, "이순신", 99, 99, 99));
		stuList.add(new Student(2020060202, "강감찬", 92, 93, 98));
		stuList.add(new Student(2020060201, "일지매", 53, 88, 61));

		System.out.println("정렬 전 :" );
		for(int i = 0; i < stuList.size(); i++) {
			System.out.println(stuList.get(i));
		}
		System.out.println("----------------------------------");
		
		Collections.sort(stuList); // 정렬하기
		
		System.out.println("학번의 오름차순으로 정렬 후 : ");
		for(Student mem : stuList) {
			System.out.println(mem);
		}
		System.out.println("----------------------------------");

		Collections.sort(stuList, new SortSumDesc()); // 정렬하기
		
		System.out.println("총점의 역순(총점이 같으면 학번의 내림차순)으로 정렬 후 : ");
		for(Student mem : stuList) {
			System.out.println(mem);
		}
		System.out.println("----------------------------------");
		
		System.out.println("등수 입력한 후 : ");
		for(int i = 0; i < stuList.size(); i++) {
			stuList.get(i).setRank(i+1);
		}
		for(Student mem : stuList) {
			System.out.println(mem);
		}
		
	}
}



class Student implements Comparable<Student>{
	private int studentNum;
	private String name;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
	private int sumScore;
	private int rank;
	
	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}
	

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", name=" + name + ", koreanScore=" + koreanScore
				+ ", englishScore=" + englishScore + ", mathScore=" + mathScore + ", sumScore=" + sumScore + ", rank="
				+ rank + "]";
	}

	public Student(int studentNum, String name, int koreanScore, int englishScore, int mathScore) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.koreanScore = koreanScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.sumScore = this.koreanScore + this.englishScore + this.mathScore;
	}

	@Override
	public int compareTo(Student student) {
		
		if( this.getStudentNum() > student.getStudentNum() ) {
			return 1;
		}
		else if( this.getStudentNum() < student.getStudentNum() ) {
			return -1;
		}
		else return 0;
	}
	
}


class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		
		if( stu1.getSumScore() > stu2.getSumScore()) {
			return -1;
		}
		else if( stu1.getSumScore() < stu2.getSumScore()) {
			return 1;
		}
		else {
			if( stu1.getStudentNum() > stu2.getStudentNum()) {
				return -1;
			}
			else if( stu1.getStudentNum() < stu2.getStudentNum()) {
				return 1;
			}
			else return 0;
		}
	}

}












