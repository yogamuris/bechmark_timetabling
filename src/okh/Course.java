package okh;

import java.util.ArrayList;

public class Course {
	private ArrayList<Integer> students;
	private int id;
	
	public Course(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Integer> getStudents() {
		return this.students;
	}
	
	public void addStudent(int id_student) {
		this.students.add(id_student);
	}
	
}
