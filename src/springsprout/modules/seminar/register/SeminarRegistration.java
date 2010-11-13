package springsprout.modules.seminar.register;

public class SeminarRegistration {
	private int  comerId;
	private String name;
	private String password;
	private String email;
	private String job;
	private String tel;
	private String note;
	private String confirmPassword;
	private String confirmEmail;
	private boolean isMemberRegistration = false;

    public int getComerId() {
        return comerId;
    }

    public void setComerId(int comerId) {
        this.comerId = comerId;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public boolean isMemberRegistration() {
		return isMemberRegistration;
	}
	public boolean getIsMemberRegistration() {
		return this.isMemberRegistration;
	}
	public void setMemberRegistration(boolean isMemberRegistration) {
		this.isMemberRegistration = isMemberRegistration;
	}

}
