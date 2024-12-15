public class CreatePet {
    public CreatePet(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    private Integer id;
    private String name;
    private String status;
}
