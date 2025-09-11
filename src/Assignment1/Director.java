package Assignment1;

import Assignment1.interfaces.IComputerBuilder;

public class Director {
    public void buildGamingComputer(IComputerBuilder builder){
        builder.setOperatingSystem("Windows").setGpu("RTX 4090 24GB").setRamGB(32).setStorage(1000).setProcessor("Intel Core-i7").setName("GamingComputer").setHasGuaranteed(false).setId(1);
    }
    public void buildResearchComputer(IComputerBuilder builder){
        builder.setOperatingSystem("Linux").setGpu("RTX 9090 64GB").setRamGB(128).setStorage(10000).setProcessor("AMD Ryzen 9 9950X").setName("ResearchComputer").setHasGuaranteed(true).setId(2);
    }
    public void buildOfficeComputer(IComputerBuilder builder){
        builder.setOperatingSystem("Windows").setGpu("Intel graphics 2GB").setRamGB(8).setStorage(128).setProcessor("Intel Pentium").setName("OfficeComputer").setHasGuaranteed(true).setId(3);
    }
}
