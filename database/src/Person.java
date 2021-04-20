
public class Person {

    protected String email;
    protected String passw;
    protected String fName;
    protected String lName;
    protected String gender;
    protected String birthday;
 
    public Person() {
    }
 
    public Person(String email) {
        this.email = email;
    }

	public Person(String email, String passw, String fName, String lName, String gender, String birthday) {
		super();
		this.email = email;
		this.passw = passw;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.birthday = birthday;
	}
	public Person(String email, String fName, String lName, String gender, String birthday) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.birthday = birthday;
	}
	public Person(String email, String passw) {
		super();
		this.email = email;
		this.passw = passw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
 
 
    
}
