public class Manager extends Employee {
    private Employee secretary;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}
