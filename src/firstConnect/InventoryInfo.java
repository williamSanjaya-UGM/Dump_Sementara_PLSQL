package firstConnect;

import java.io.Serializable;

public class InventoryInfo implements Serializable {
    private int id;
    private String name;
    private String sectorName;

    public InventoryInfo(){}

    public InventoryInfo(int id, String name, String sectorName) {
        this.id = id;
        this.name = name;
        this.sectorName = sectorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String toString() {
        return "InventoryInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }
}
