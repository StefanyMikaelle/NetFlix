import java.util.StringJoiner;

public class Employee  implements Comparable<Employee> {
    private String userID;
    private String subscriptionType;
    private String monthlyRevenue;
    private String joinDate;
    private String lastPaymentDate;
    private String country;
    private String age;
    private String gender;
    private String device;
    private String planDuration;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(String monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPlanDuration() {
        return planDuration;
    }

    public void setPlanDuration(String planDuration) {
        this.planDuration = planDuration;
    }

    public Employee(String userID, String subscriptionType, String monthlyRevenue, String joinDate, String lastPaymentDate, String country, String age, String gender, String device, String planDuration) {
        this.userID = userID;
        this.subscriptionType = subscriptionType;
        this.monthlyRevenue = monthlyRevenue;
        this.joinDate = joinDate;
        this.lastPaymentDate = lastPaymentDate;
        this.country = country;
        this.age = age;
        this.gender = gender;
        this.device = device;
        this.planDuration = planDuration;
    }

    public Employee() {
    }

    @Override
    public int compareTo(Employee o) {
        return this.country.compareTo(o.country);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("userID='" + userID + "'")
                .add("subscriptionType='" + subscriptionType + "'")
                .add("monthlyRevenue='" + monthlyRevenue + "'")
                .add("joinDate='" + joinDate + "'")
                .add("lastPaymentDate='" + lastPaymentDate + "'")
                .add("country='" + country + "'")
                .add("age='" + age + "'")
                .add("gender='" + gender + "'")
                .add("device='" + device + "'")
                .add("planDuration='" + planDuration + "'")
                .toString();
    }
}
