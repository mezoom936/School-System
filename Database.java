package hw3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import models.Group;
import models.Lesson;
import models.Student;

public class Database {
	// Data fields 
	private String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu59";
	private Connection connection;
	private String username = "cs3220stu59";
	private String password = "nENCDKngU5Lb";
	
	public Database()
	{
		try 
		{
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		if(this.connection != null)
		{
			try
			{
				connection.close();
			}
			catch( SQLException e )
	        {
	            e.printStackTrace();
	        }
		}
	}
	
	public List<Student> getStudents()
	{
		List<Student> students = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			String sql = "select s.id, s.name, s.age, s.parent, s.email, g.name from students s left join s_groups g on s.group_id = g.id";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setStudentName(rs.getString(2));
				student.setStudentAge(rs.getInt(3));
				student.setParentName(rs.getString(4));
				student.setStudentEmail(rs.getString(5));
				student.setgName(rs.getString(6));
				students.add(student);
			}
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return students;
	}
	
	public List<Lesson> getLessonsGroup()
	{
		List<Lesson> lessons = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			String sql = "select l.id, l.curr_time, l.length, g.name from lessons l left join students s on s.id =  l.st left join s_groups g on s.group_id = g.id";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Lesson lesson = new Lesson();
				lesson.setId(rs.getInt(1));
				lesson.setDate(rs.getTime(2));
				lesson.setLength(rs.getInt(3));
				lesson.setGroupName(rs.getString(4));
				lessons.add(lesson);
			}
			stmt.close();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return lessons;
	}
	
	public List<Lesson> getLessonsStudent()
	{
		List<Lesson> lessons = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			String sql2 = "select l.id, l.curr_time, l.length, s.name from lessons l left join students s on s.id =  l.st";
			ResultSet rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				Lesson lesson = new Lesson();
				lesson.setId(rs.getInt(1));
				lesson.setDate(rs.getTime(2));
				lesson.setLength(rs.getInt(3));
				lesson.setStudentName(rs.getString(4));
				lessons.add(lesson);
			}
			stmt.close();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return lessons;
	}
	public List<Student> getMembers(int id)
	{
		List<Student> students = new ArrayList<>();
		try
		{
			String sql = "select s.name from students s where s.group_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Student s = new Student();
				s.setStudentName(rs.getString("name"));
				s.setGroup(this.getGroup(rs.getInt(1)));
				students.add(s);
			}
			pstmt.close();	
		}
		catch(SQLException e)
		{
			e.printStackTrace( );
		}
		return students;
	}
	
	public Student getStudent(int id)
	{
		Student student = new Student();
		try
		{
			String sql = "select * from students where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				student.setId(rs.getInt(1));
				student.setStudentName(rs.getString(2));
				student.setStudentAge(rs.getInt(3));
				student.setParentName(rs.getString(4));
				student.setStudentEmail(rs.getString(5));	
			}
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return student;
	}
	
	public Group getGroup(int idg)
	{
		Group group = new Group();
		try
		{
			String sql = "select * from s_groups where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idg);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				group.setId(rs.getInt(1));
				group.setGroupName(rs.getString(2));
			}
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return group;
	}
	
	public List<Group> getGroups()
	{
		List<Group> groups = new ArrayList<>();
		try
		{
			Statement stmt = connection.createStatement();
			String sql = "select * from s_groups";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Group group = new Group();
				group.setId(rs.getInt(1));
				group.setGroupName(rs.getString(2));
				groups.add(group);
				
			}
			stmt.close();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return groups;		
	}
	public void addGroup(String groupName)
	{
		try
		{
			String sql = "insert into s_groups (name) values (?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, groupName);		
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void addStudent(String name, int age, String parent, String email, int group_id)
	{
		try
		{
			String sql = "insert into students (name, age, parent, email, group_id) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, parent);
			pstmt.setString(4, email);
			pstmt.setInt(5, group_id);	
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void edit(int id, String name, int age, String parent, String email, int group_id)
	{
		try
		{
			String sql = "update students set name = ?, age = ?, parent = ?, email = ?, group_id = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
            pstmt.setString( 1, name );
            pstmt.setInt(2, age);
            pstmt.setString(3, parent);
            pstmt.setString(4, email);
            pstmt.setInt(5, group_id);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void editGroup(int idg, String groupName)
	{
		try
		{
			String sql = "update s_groups set name = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.setInt(2, idg);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void delete(int id)
	{
		 try
	     {
 		     String sql = "delete from students where id = ?";
 		     PreparedStatement pstmt = connection.prepareStatement( sql );
 		     pstmt.setInt( 1, id );
 		     pstmt.executeUpdate();
 		     pstmt.close();
	     }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
	}
	public void deleteGroup(int id)
	{
		try
		{
			String sql = "delete from s_groups where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	

}
