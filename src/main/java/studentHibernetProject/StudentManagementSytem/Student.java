package studentHibernetProject.StudentManagementSytem;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Student {
	@Id
	 private int id;
	 private String name;
	 private int age;
	 private String gender;
	 private int year;
	 private int Semester;
	 private double cgpa;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSemester() {
		return Semester;
	}
	public void setSemester(int semester) {
		Semester = semester;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", year=" + year
				+ ", Semester=" + Semester + ", cgpa=" + cgpa + "]";
	}
	
	
	}
	
	

