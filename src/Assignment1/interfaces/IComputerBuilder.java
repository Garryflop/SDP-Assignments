package Assignment1.interfaces;

import Assignment1.sources.Computer;

public interface IComputerBuilder {
    IComputerBuilder setId(int id);
    IComputerBuilder setName(String name);
    IComputerBuilder setOperatingSystem(String operatingSystem);
    IComputerBuilder setGpu(String gpu);
    IComputerBuilder setRamGB(int ram);
    IComputerBuilder setStorage(int storage);
    IComputerBuilder setProcessor(String proccesor);
    IComputerBuilder setHasGuaranteed(boolean hasGuaranteed);
    IComputerBuilder reset();

    Computer build();
}
