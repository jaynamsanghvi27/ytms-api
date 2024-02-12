package com.yash.ytms.dto;

public class NominationData {
	private String emp_id;
	private String emp_name;
	private String emp_mail_id;
	private String grade;
	private String skill;
	private String current_allocation;
	private String project;
	private String current_location;
	private Long training_id;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_mail_id() {
		return emp_mail_id;
	}
	public void setEmp_mail_id(String emp_mail_id) {
		this.emp_mail_id = emp_mail_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getCurrent_allocation() {
		return current_allocation;
	}
	public void setCurrent_allocation(String current_allocation) {
		this.current_allocation = current_allocation;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getCurrent_location() {
		return current_location;
	}
	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}
	public Long getTraining_id() {
		return training_id;
	}
	public void setTraining_id(Long training_id) {
		this.training_id = training_id;
	}
	@Override
	public String toString() {
		return "NominationData [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_mail_id=" + emp_mail_id
				+ ", grade=" + grade + ", skill=" + skill + ", current_allocation=" + current_allocation + ", project="
				+ project + ", current_location=" + current_location + ", training_id=" + training_id + "]";
	}
	public NominationData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NominationData(String emp_id, String emp_name, String emp_mail_id, String grade, String skill,
			String current_allocation, String project, String current_location, Long training_id) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_mail_id = emp_mail_id;
		this.grade = grade;
		this.skill = skill;
		this.current_allocation = current_allocation;
		this.project = project;
		this.current_location = current_location;
		this.training_id = training_id;
	}
	
	
}
