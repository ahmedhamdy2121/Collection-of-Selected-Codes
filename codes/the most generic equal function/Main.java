package labs.lesson11.prob6;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    // simple version
    public static boolean contains1(List<String> list, String s) {
        for (String x : list) {
            if (x == null && s == null)
                return true;
            if (s == null || x == null)
                continue;
            if (x.equals(s))
                return true;
        }
        return false;
    }
    
    public static <T> boolean contains(List<T> list, T s) {
        if (list == null || s == null || list.size() == 0)
            return false;
        for (T x : list) {
            if (x == null && s == null)
                return true;
            if (s == null || x == null)
                continue;
            if (containsHelper(x, s))
                return true;
        }
        return false;
    }
    
    private static <T> boolean containsHelper(T x, T s) {
        if (x == null && s == null)
            return true;
        if (s == null || x == null)
            return false;
        
        if (x.getClass().isPrimitive() || x.getClass().equals(String.class))
            return Objects.deepEquals(x, s);
        
        // if it is complex type
        // getting all the instance variables from all super classes
        Field[] fsTemp;
        List<Field> fs = new ArrayList<>();
        Class<?> current = x.getClass();
        while(current.getSuperclass() != null){ 
            fsTemp = current.getDeclaredFields();
            Collections.addAll(fs, fsTemp);
            current = current.getSuperclass();
        }
        
        for (Field f: fs) {
            f.setAccessible(true);
            if (f.getType().isPrimitive() 
                || f.getType().equals(String.class)) {
                try {
                    if (! Objects.deepEquals(f.get(x), f.get(s)))
                        return false;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            } 
            else {
                try {
                    if (! checkComplexFields(f.get(x), f.get(s)))
                        return false;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    
    private static <T> boolean checkComplexFields(T x, T s) {
        if (x == null && s == null)
            return true;
        if (s == null || x == null)
            return false;
        try {
            Field[] fs = x.getClass().getDeclaredFields();
            for (Field fld: fs) {
                fld.setAccessible(true);
                if (fld.getType().isPrimitive()
                    || fld.getType().equals(String.class)) {
                    if (! Objects.deepEquals(fld.get(x), fld.get(s)))
                        return false;
                }
                else {
                    return checkComplexFields(fld.get(x), fld.get(s));
                }
            }
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // your generalization -- should work with lists of
    // Employees, Accounts. You may not override equals
    // in the data-holder classes (like Employee, Account, etc)
    // so you need to find another way to deal with the fact
    // that these classes do not have their own equals methods.

    // public static boolean contains2(List<T> list ... )

    public static void test1() {
        List<String> list = Arrays.asList("Bob", "Joe", "Tom");
        boolean result = Main.contains(list, "Tom");
        System.out.println(result);
    }

    public static void test2() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1003, "Tom", 60000, 
                new Address("str1", "city1", "state1", "zip1")));
        list.add(new Employee(1002, "Harry", 70000, 
                new Address("str2", "city2", "state2", "zip2", 
                        new Complex("c2", 2))));
        list.add(new Employee(1001, "Joe", 50000, 
                new Address("str3", "city3", "state3", "zip3")));
        boolean result = Main.contains(list, 
                new Employee(1002, "Harry", 70000, 
                        new Address("str2", "city2", "state2", "zip2", 
                                new Complex("c2", 2))));
        System.out.println(result);
    }

    public static void test3() {
        List<Manager> list = new ArrayList<>();
        list.add(new Manager(1003, "Tom", 60000, 700, 
                new Address("str1", "city1", "state1", "zip1", 
                        new Complex("c1", 1))));
        list.add(new Manager(1002, "Harry", 70000, 400, 
                new Address("str2", "city2", "state2", "zip2")));
        list.add(new Manager(1001, "Joe", 50000, 500, 
                new Address("str3", "city3", "state3", "zip3")));
        boolean result = Main.contains(list, 
                new Manager(1003, "Tom", 60000, 700, 
                        new Address("str1", "city1", "state1", "zip1", 
                                new Complex("c1", 1))));
        System.out.println(result);
    }

    public static void test4() {
        List<CheckingAccount> list = new ArrayList<>();
        list.add(new CheckingAccount(1001, 25.00));
        list.add(new CheckingAccount(1002, 35.00));
        list.add(new CheckingAccount(1003, 125.00));
        boolean result = Main.contains(list, 
                new CheckingAccount(1003, 125.00));
        System.out.println(result);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

}
