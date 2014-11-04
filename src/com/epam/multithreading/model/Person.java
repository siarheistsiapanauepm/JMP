package  com.epam.multithreading.model;

public class Person {
    private String firstName;
    private String lastName;
    private String govermentalId;

    public Person(String firstName, String lastName, String govermentalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.govermentalId = govermentalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGovermentalId() {
        return govermentalId;
    }

    public void setGovermentalId(String govermentalId) {
        this.govermentalId = govermentalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (govermentalId != null ? !govermentalId.equals(person.govermentalId) : person.govermentalId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return govermentalId != null ? govermentalId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + govermentalId;
    }
}
