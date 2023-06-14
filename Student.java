package models;

public class Student {
		
		// Data fields
		private int id;
		private String studentName;
		private int studentAge;
		private String parentName;
		private String studentEmail;
		private Group group;
		private String gName;
		private int groupId;
		
		
		// Default Constructor
		public Student()
		{
			
		}

		// Getters and Setters
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public int getStudentAge() {
			return studentAge;
		}

		public void setStudentAge(int studentAge) {
			this.studentAge = studentAge;
		}

		public String getStudentEmail() {
			return studentEmail;
		}

		public void setStudentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
		}

		public String getParentName() {
			return parentName;
		}

		public void setParentName(String parentName) {
			this.parentName = parentName;
		}

		public Group getGroup() {
			return group;
		}

		public void setGroup(Group group) {
			this.group = group;
		}
		public void removeGroup()
		{
			this.group = null;
		}
		
		public String getgName() {
			return this.gName;
		}

		public void setgName(String gName) {
			this.gName = gName;
		}

		public int getGroupId() {
			return groupId;
		}

		public void setGroupId(int groupId) {
			
		}

		
}
