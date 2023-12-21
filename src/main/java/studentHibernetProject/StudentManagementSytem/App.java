package studentHibernetProject.StudentManagementSytem;
import java.util.List;

import java.util.Scanner;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

/**
 * DAO class
 *
 */
public class App
{
	static Configuration conf;
	static SessionFactory factory;
	static Session session;
	
      public void insert() {
    	//class reads both the entity and config file
          
        Student s = new Student();
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter the student id : ");
        int id= sc.nextInt();
        s.setId(id);
        sc.nextLine();
        System.out.println("Enter the student name : ");
        String name= sc.nextLine();
        s.setName(name);
        System.out.println("Enter the student age : ");
        int age= sc.nextInt();
        s.setAge(age);
        sc.nextLine();
        System.out.println("Enter the student gender : ");
        String gender= sc.nextLine();
        s.setGender(gender);
        System.out.println("Enter the student current year : ");
        int year= sc.nextInt();
        s.setYear(year);
        System.out.println("Enter the student current semester : ");
        int semester= sc.nextInt();
        s.setSemester(semester);
        System.out.println("Enter the student cgpa : ");
        double cgpa= sc.nextDouble();
        s.setCgpa(cgpa);
        
        session.save(s);
    	Transaction tx = session.beginTransaction();
    	//saving data permanently

    	tx.commit();
    	
    	  session.close();
          factory.close();
        
      }
    public void readStudentById(int id)
  	{
  		/*
	    	 * get() ->return null if object is not present
	    	 * load() ->throws exception ObjectNotFound if object is not present
	    	 */
          Student s1 = session.get(Student.class,id);
         
          System.out.println("------------");
          System.out.println("Student Details");
          System.out.println(s1);
  	}
    
    
    public void deleteByID(int id)
  	 {
  		Student s1  = session.get(Student.class,id);
  		Transaction tr = session.beginTransaction();
  		/*delete() -> return NullPointerException if object is not exist.
  		 *remove() -> return NullPointerException if object is not exist. 
  		 */
  		String name = s1.getName();
  		//session.delete(s1);
  		session.remove(s1);
  		tr.commit();
  		System.out.println("Student "+name+" is removed.");
  	 }
    
    public void ReadAllRecords(){
		 System.out.println("-----------------------");
        System.out.println("All Student Details");
        String hqlQuery="from Student";
        
        //set of data =list format
        //convert array data into list
        
        List<Student> data =session.createQuery(hqlQuery,Student.class).list();
        //System.out.println("data"); 
        for(Student d : data)
        {
        	int i=1;
        	System.out.println("****student Detail "+ i+"******");
        	System.out.println(d);
			System.out.println("....................................");
			i++;
        }
	}
    
    public void update() {
    	Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the student ID to update
        System.out.print("Enter the student ID to update: ");
        int studentId = scanner.nextInt();

        // Fetch the student by ID
        Student studentToUpdate = session.get(Student.class, studentId);

        if (studentToUpdate != null) {
            // Prompt user for the attribute to update
            System.out.println("Which attribute do you want to update?");
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. gender");
            System.out.println("4. year");
            System.out.println("5. semester");
            System.out.println("6. cgpa");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the updated name: ");
                    String updatedName = scanner.next();
                    studentToUpdate.setName(updatedName);
                    break;
                case 2:
                    System.out.print("Enter the updated age: ");
                    int updatedAge = scanner.nextInt();
                    studentToUpdate.setAge(updatedAge);
                    break;
                case 3:
                	System.out.print("Enter the updated gender: ");
                    String updatedGender = scanner.next();
                    studentToUpdate.setName(updatedGender);
                    break;
                case 4:
                	 System.out.print("Enter the updated year: ");
                     int updatedYear = scanner.nextInt();
                     studentToUpdate.setAge(updatedYear);
                     break;
                case 5:
                	 System.out.print("Enter the updated semester: ");
                     int updatedSemester = scanner.nextInt();
                     studentToUpdate.setAge(updatedSemester);
                     break;
                case 6:
                	 System.out.print("Enter the updated cgpa: ");
                     int updatedcgpa = scanner.nextInt();
                     studentToUpdate.setAge(updatedcgpa);
                     break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            // Begin a transaction
            Transaction transaction = session.beginTransaction();

            try {
                // Update the student entity in the database
                session.update(studentToUpdate);

                // Commit the transaction
                transaction.commit();
                System.out.println("Student details updated successfully.");
            } catch (Exception e) {
                // Handle exceptions or roll back the transaction if an error occurs
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                // Close the session
                session.close();
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }

        scanner.close();
	
    }
    
    public void Deleteallrecords() {
    
  		Transaction tx = session.beginTransaction();
    	try {
    	    Query deleteQuery = session.createQuery("DELETE FROM Student");
    	    int rowCount = deleteQuery.executeUpdate();
    	    System.out.println("Number of records deleted from Student table: " + rowCount);

    	    tx.commit();
    	} catch (Exception e) {
    	    if (tx != null) {
    	        tx.rollback();
    	    }
    	    e.printStackTrace();
    	} finally {
    	    session.close();
    	}
    }
        
     public static void main( String[] args )
      {

			//step1
			conf= new Configuration();
			conf.configure();
			//step2
			factory = conf.buildSessionFactory();
			session = factory.openSession();
			Scanner sc = new Scanner(System.in);
			App read = new App();
      		System.out.println("Choose operation for Student:");       	
			System.out.println("1. Insert");
            System.out.println("2. Read student data by ID");
            System.out.println("3. Read All student data");
            System.out.println("4. Remove student by ID");
            System.out.println("5. Update Student data");
            System.out.println("6. Delete All Student records");
    	    int choice = sc.nextInt();
    	    int id;
    	    switch (choice) {
    	    case 1:read.insert();
    	    break;
    	    case 2: System.out.println("Enter the student id");
    	    id = sc.nextInt();
    	    read.readStudentById(id);
    	    				
    	    break;
    	    				
    	    case 3:read.ReadAllRecords();
    	    				
    	    break;
    	    case 4:System.out.println("Enter the Student id");
    	    id = sc.nextInt();
    	    read.deleteByID(id);
    	    
    	    case 5:read.update();
    	    break;
    	    
    	    case 6:read.Deleteallrecords();
    	    break;

    	    default: System.out.println("Thank You");
    	    break;
    	   
    	    }       
      }
	
}
