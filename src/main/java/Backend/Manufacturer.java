package Backend;

public class Manufacturer extends Person {
    protected String Company;
    protected int id;
    protected Picture carPicture;
    protected String sellerFullName, sellerEmail, sellerPhone;

    // Gonna use this one for (Admin)
    public Manufacturer(String Company) {
        super();
        this.Company = Company;

    }

    // Use this Constructor for the store
    public Manufacturer(String company, int id, Picture carPicture){
        this.Company = company;
        this.id = id;
        this.carPicture = carPicture;
    }

    public Manufacturer(String cnic, String phoneNo, String email, String password, Date dateOfBirth, Name name, Address address, String company, int id) {
        super(cnic, phoneNo, email, password, dateOfBirth, name, address);
        this.Company = company;
        this.id = id;
    }

    public Manufacturer(String company, String cnic){
        super(cnic, "", "", "", null, null, null);
        this.Company = company;
    }
    public Manufacturer(String company, String cnic, String ownerFullName, String ownerEmail, String sellerPhone){
        super(cnic, "", "", "", null, null, null);
        this.Company = company;
        this.sellerEmail = ownerEmail;
        this.sellerFullName = ownerFullName;
        this.sellerPhone = sellerPhone;
    }
    public Manufacturer(String company, int id) {
        this.Company = company;
        this.id = id;
    }

    public Manufacturer(Person p1, String company, int id) {
        super(p1);
        Company = company;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return Company;
    }

    public Picture getCarPicture() {
        return carPicture;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public String getOwnerFullName() {
        return sellerFullName;
    }

    @Override
    public String toString() {
        return super.toString() + this.Company;
    }
}
