package Assignment1.sources;

import Assignment1.interfaces.IComputerBuilder;

public class ComputerBuilder implements IComputerBuilder {

    private int id;
    private String name;
    private String operatingSystem;
    private String gpu;
    private int ramGB;
    private int storageGB;
    private String processor;
    private boolean hasGuaranteed;

    @Override
    public IComputerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public IComputerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IComputerBuilder setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    @Override
    public IComputerBuilder setGpu(String gpu) {
        this.gpu = gpu;
        return this;
    }

    @Override
    public IComputerBuilder setRamGB(int ram) {
        this.ramGB = ram;
        return this;
    }

    @Override
    public IComputerBuilder setStorage(int storageGB) {
        this.storageGB = storageGB;
        return this;
    }

    @Override
    public IComputerBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    @Override
    public IComputerBuilder setHasGuaranteed(boolean hasGuaranteed) {
        this.hasGuaranteed = hasGuaranteed;
        return this;
    }

    public Computer build() {
        return new Computer(id, name, operatingSystem, gpu, ramGB, storageGB, processor, hasGuaranteed);
    }

    @Override
    public IComputerBuilder reset() {
        this.id = 0;
        this.name = "Default Computer";
        this.operatingSystem = "Windows 11";
        this.gpu = "Integrated Graphics";
        this.ramGB = 8;
        this.storageGB = 256;
        this.processor = "Intel Core i5";
        this.hasGuaranteed = true;
        return this;
    }
}
