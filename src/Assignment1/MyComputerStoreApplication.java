package Assignment1;

import Assignment1.sources.Computer;
import Assignment1.sources.ComputerBuilder;


public class MyComputerStoreApplication {
    public static void main(String[] args) {
        Director director = new Director();
        ComputerBuilder computerBuilder = new ComputerBuilder();
        Computer gamingPC = buildAndDisplayComputer(director, computerBuilder, "gaming");
        Computer officePC = buildAndDisplayComputer(director, computerBuilder, "office");
        Computer workstationPC = buildAndDisplayComputer(director, computerBuilder, "workstation");
        computerBuilder.reset();
        Computer customPC = computerBuilder.setGpu("RTX2020").setRamGB(-1).build();
        System.out.println("Computer: " + customPC);
    }


    private static Computer buildAndDisplayComputer(Director director, ComputerBuilder computerBuilder, String type) {
        switch (type.toLowerCase()) {
            case "gaming":
                director.buildGamingComputer(computerBuilder);
                break;
            case "workstation":
                director.buildResearchComputer(computerBuilder);
                break;
            case "office":
                director.buildOfficeComputer(computerBuilder);
                break;
        }
        Computer computer = computerBuilder.build();
        System.out.println("Computer: " + computer);
        System.out.println();
        return computer;
    }
}
