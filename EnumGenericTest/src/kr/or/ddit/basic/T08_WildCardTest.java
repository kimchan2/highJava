package kr.or.ddit.basic;

import java.util.Arrays;

public class T08_WildCardTest {
	
	/*
	 * 모든 과정 등록 메서드
	 * @param course
	 */
	public static void registerCourse(Course<?> course) {
		System.out.println(course.getName() + " : 수강생 : " + Arrays.toString(course.getStudents()));
	}

	/*
	 * 학생과정 등록 메서드
	 * @param course
	 */
	public static void registerStudent(Course<? extends Student> course) {
		System.out.println(course.getName() + " : 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/*
	 * 직장인 과정 등록 메서드
	 * @param course
	 */
	public static void registerWorker(Course<? super Worker> course) {
		System.out.println(course.getName() + " : 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course("일반과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course("직장인과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = new Course("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		/////////////////////////////////////////////////////////////
		// 모든 과정 등록
		registerCourse(personCourse);
		registerCourse(workerCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		
		// 학생과정 등록
		//registerStudent(personCourse);
		//registerStudent(workerCourse);
		registerStudent(studentCourse);
		registerStudent(highStudentCourse);
		
		// 직장인 과정 등록
		registerWorker(personCourse);
		registerWorker(workerCourse);
		//registerWorker(studentCourse);
		//registerWorker(highStudentCourse);
	} 
}


/*
 * 일반인
 */
class Person{
	String name; // 이름

	public Person(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}

/*
 * 직장인
 */
class Worker extends Person{

	public Worker(String name) {
		super(name);
	}
}

/*
 * 학생
 */
class Student extends Person{

	public Student(String name) {
		super(name);
	}	
}

/*
 * 고등학생
 */
class HighStudent extends Student{

	public HighStudent(String name) {
		super(name);
	}	
}

class Course<T> {
	private String name; // 코스명
	private T[] students; // 수강생(제너릭 배열)
	
	/*
	 * 생성자
	 * @param name 과정명
	 * @param capacity 총원
	 */
	public Course(String name, int capacity) {
		super();
		this.name = name;
		// 타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후, 타입 파라미터 배열로 캐스팅 처리해야 함.
		students = (T[])(new Object[capacity]);
	}

	// 코스명 조회
	public String getName() {
		return name;
	}
	
	// 수강생 조회
	public T[] getStudents() {
		return students;
	}
	
	// 수강생 등록
	public void add(T t) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) { // 아직 등록하지 않은(빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}













