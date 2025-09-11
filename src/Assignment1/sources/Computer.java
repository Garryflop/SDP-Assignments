package Assignment1.sources;

public class Computer {
    private final int id;
    private final String name;
    private final String operatingSystem;
    private final String gpu;
    private final int ramGB;
    private final int storageGB;
    private final String processor;
    private final boolean hasGuaranteed;

    public Computer(int id, String name, String operatingSystem, String gpu, int ramGB, int storageGB, String processor, boolean hasGuaranteed) {
        this.id = validateId(id);
        this.name = validateName(name);
        this.operatingSystem = validateOperatingSystem(operatingSystem);
        this.gpu = validateGpu(gpu);
        this.ramGB = validateRamGB(ramGB);
        this.storageGB = validateStorageGB(storageGB);
        this.processor = validateProcessor(processor);
        this.hasGuaranteed = hasGuaranteed;
    }

    private int validateId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Computer ID cannot be negative");
        }
        return id;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Computer name cannot be null or empty");
        }
        return name.trim();
    }

    private String validateOperatingSystem(String os) {
        if (os == null || os.trim().isEmpty()) {
            throw new IllegalArgumentException("Operating system cannot be null or empty");
        }
        return os.trim();
    }

    private String validateGpu(String gpu) {
        if (gpu == null || gpu.trim().isEmpty()) {
            throw new IllegalArgumentException("GPU cannot be null or empty");
        }
        return gpu.trim();
    }

    private int validateRamGB(int ramGB) {
        if (ramGB <= 0) {
            throw new IllegalArgumentException("RAM must be positive");
        }
        return ramGB;
    }

    private int validateStorageGB(int storageGB) {
        if (storageGB <= 0) {
            throw new IllegalArgumentException("Storage must be positive");
        }
        return storageGB;
    }

    private String validateProcessor(String processor) {
        if (processor == null || processor.trim().isEmpty()) {
            throw new IllegalArgumentException("Processor cannot be null or empty");
        }
        return processor.trim();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getOperatingSystem() { return operatingSystem; }
    public String getGpu() { return gpu; }
    public int getRamGB() { return ramGB; }
    public int getStorageGB() { return storageGB; }
    public String getProcessor() { return processor; }
    public boolean isHasGuaranteed() { return hasGuaranteed; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Computer computer = (Computer) obj;
        return id == computer.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", operatingSystem='" + getOperatingSystem() + '\'' +
                ", gpu='" + getGpu() + '\'' +
                ", ramGB='" + getRamGB() + '\'' +
                ", storageGB='" + getStorageGB() + '\'' +
                ", processor='" + getProcessor() + '\'' +
                ", hasGuaranteed=" + isHasGuaranteed() +
                '}';
    }
}
