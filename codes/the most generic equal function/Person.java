package labs.lesson11.prob6;

public class Person {
    protected String name;
    protected Address addrs;

    public Person(String n, Address addrs) {
        name = n;
        this.addrs = addrs;
    }

    public String getName() {
        return name;
    }
    
    public Address getAddress() {
        return addrs;
    }
}
