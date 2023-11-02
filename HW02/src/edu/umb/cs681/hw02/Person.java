package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static edu.umb.cs681.hw02.AgeCat.*;

public class Person {

    public Person (String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doses = new LinkedList<> ();
    }

    private String firstName;
    private String lastName;
    private LocalDate dob;

    private LinkedList<Dose> doses;

    int getAge () {
        return Period.between (dob, LocalDate.now ()).getYears ();
    }

    AgeCat getAgeCat () {
        int age = getAge ();
        if ( age > 60 )
            return OLD;
        else if ( age >= 25 && age <= 60 )
            return MID;
        else if ( age < 25 )
            return AgeCat.YOUNG;
        return null;
    }

    LinkedList<Dose> getDoses () {
        return doses;
    }

    int getVacCount () {
        return doses.size ();
    }

    static LinkedList<Person> GetRandomInstances () {
        LinkedList<Person> people = new LinkedList<> ();
        Random rand = new Random ();

        String[] firstNames = {"Anu", "Abhi", "Shounik", "Parag", "Nitish", "Simran", "Sonali", "Tushar"};
        String[] lastNames = {"Sharma", "Verma", "Munjal", "Paul", "Vats", "Singh", "Muni", "Sai"};
        String[] vacProduct = {"Pfizer", "Covishield", "Covaxin"};
        String[] vacSites = {"Boston", "Dorchester", "Chelsea", "Cambridge", "Revere"};

        for (int i = 0; i < 1000; i++) {
            String firstName = firstNames[rand.nextInt (firstNames.length)];
            String lastName = lastNames[rand.nextInt (lastNames.length)];
            LocalDate dob = LocalDate.of (rand.nextInt (60) + 1960, rand.nextInt (12) + 1, rand.nextInt (28) + 1);

            Person person = new Person (firstName, lastName, dob);
            int doseCount = rand.nextInt (4);
            for (int j = 0; j < doseCount; j++) {
                String vacProductName = vacProduct[rand.nextInt (vacProduct.length)];
                String lotNumber = "LOT" + (100 + rand.nextInt (900));
                LocalDate doseDate = LocalDate.of (rand.nextInt (3) + 2021, rand.nextInt (12) + 1, rand.nextInt (28) + 1);
                String vacSite = vacSites[rand.nextInt (vacSites.length)];
                Dose dose = new Dose (vacProductName, lotNumber, doseDate, vacSite);
                person.doses.add (dose);
            }

            people.add (person);

        }
        return people;
    }



    public static void main (String[] args) {

        LinkedList<Person> Persons= GetRandomInstances();
        Map<Enum, Long > PersonGroupedByAgeCat =
                Persons.stream().filter(p -> p.getVacCount() >= 3)
                        .collect(Collectors.groupingBy((Person p)-> p.getAgeCat () ,Collectors.counting ()));

        float vacRateOld = (float)PersonGroupedByAgeCat.get (OLD)/Persons.size() * 100;
        float vacRateYoung = (float)PersonGroupedByAgeCat.get (YOUNG)/Persons.size() * 100;
        float vacRateMid = (float)PersonGroupedByAgeCat.get (MID)/Persons.size() * 100;



        Map<Enum,Double> AvgVacCount= Persons.stream ().collect (Collectors.groupingBy (p -> p.getAgeCat (),Collectors.averagingInt (p -> p.getVacCount ())));
        System.out.println (AvgVacCount);

        Map<Boolean, List<Person>> AvgAgePeopleNotVac = Persons.stream ().collect (Collectors.partitioningBy (p -> p.getVacCount ()<1));

        double AvgAgePeopleNotVacc= AvgAgePeopleNotVac.get (true).stream ().collect (Collectors.averagingDouble (p -> p.getAge ()));
        System.out.println (AvgAgePeopleNotVacc);





    }


}



