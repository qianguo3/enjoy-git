package hello;

/**
 * 安欣 生物资产 中生物类型 6种
 */
public enum RanchBiologyClassEnum {
    CLASS_100(100,"种公羊"),
    CLASS_99(99,"成年母羊"),
    CLASS_98(98,"青年母羊"),
    CLASS_97(97,"育肥羊"),
    CLASS_96(96,"育成羊"),
    CLASS_91(91,"羔羊");


    private Integer key;

    private String name;



    RanchBiologyClassEnum(Integer key, String name){
        this.key = key;
        this.name = name;
    }

    public static String getDescByKey(Integer key) {
        for (RanchBiologyClassEnum value : RanchBiologyClassEnum.values()) {
            if (value.getKey().equals(key)) {
                return value.getName();
            }
        }
        return "";
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
