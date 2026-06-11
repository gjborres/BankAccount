package firstpackage;
import java.util.Scanner;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Animal {

	private String type;
	
	public int age;
	public String name = "MOMMY MONKEY";
	public String dad = "GRANDPA MONKEY";

	public Animal(int age) {
		this.age = age;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public String getType() {
		return type;
	}
	
	public static void main(String[] args) {
		
		Monkey monkey = new Monkey();
		monkey.setDetails();
//ARRAYLIST
		ArrayList list = new ArrayList();
		list.add("hawk");
		list.add(Boolean.TRUE);
		System.out.println(list);
		
		ArrayList<String> safer = new ArrayList<>();
		safer.add("sparrow");
		safer.add(Boolean.TRUE.toString());
		safer.add(1,"robin");
		System.out.println(safer);
		
//ITERATOR		
		ArrayList<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");
		
		Iterator<String> it = names.iterator();
		
		System.out.println("Names in the list:");
		
		while (it.hasNext()) {
			String name = it.next();
			System.out.println(name);
			
			if (name.equals("Bob")) {
				it.remove();
			}
		}
		System.out.println("Updated names in the list: " + names);
		

		Random rand1 = new Random();
		System.out.println("Without Seed: " + rand1.nextInt(100));
		
		Random rand2 = new Random(10);
		System.out.println("With Seed 50: " + rand2.nextInt(100));
		
		Random rand3 = new Random(50);
		System.out.println("With Seed 50 again: " + rand3.nextInt(100));
	}
}


class Monkey extends Animal{
	
	public String name = "Monkey";
	public String dad = "daddy monkey";

	public Monkey(int age) {
		super(age);
	}
	
	public Monkey() {
		this(150);
	}
	
	
	public void setDetails() {
		setType("mammal");
		System.out.println(super.dad + " is a " + getType() + " and it's " + age + " years old.");
	}
	
	
	
}

