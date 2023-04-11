package entities;

public class Customer {
    Integer ID;
    String Name;
    String Address;
    String City;
    String State;
    Integer Zip;

    public Integer getId() { return ID; }
    public void setId(Integer ID)
    { 
        this.ID = ID; 
    }
    public String getName() { return Name; }
    public void setName(String Name)
    {
        this.Name = Name;
    } 
    public String getAddress() { return Address; }
    public void setAddress(String Address)
    {
        this.Address = Address;
    }
    public String getCity() { return City; }
    public void setCity(String City)
    {
        this.City = City;
    }
    public String getState() { return State; }
    public void setState(String State)
    {
        this.State = State;
    }
    public Integer getZip() { return Zip; }
    public void setZip(Integer Zip)
    {
        this.Zip = Zip;
    }

    @Override
    public String toString()
    {
        return "Customer [ID =" + ID + ", Name = " + Name + ", Address = " + Address 
        + ", City = " + City + ", State = " + State + ", Zip = " + Zip + "]";
    }
}
