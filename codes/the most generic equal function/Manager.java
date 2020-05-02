package labs.lesson11.prob6;

public class Manager extends Employee {
    @SuppressWarnings("unused")
    private int bonus;

    public Manager(int id, String n, int s, int bonus, Address addrs) {
        super(id, n, s, addrs);
        this.bonus = bonus;
    }
}
