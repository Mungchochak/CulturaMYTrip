package org.example.jobby.Model;
//POJO class
public class Data {

        private String name;
        private String email;
        private String industry;
        private String ssmNo;
        private String address;
        private String contact;
        private String additionalInfo;

        // Constructors
        public Data() {}

        public Data(String name, String email, String industry, String ssmNo, String address, String contact, String additionalInfo) {
            this.name = name;
            this.email = email;
            this.industry = industry;
            this.ssmNo = ssmNo;
            this.address = address;
            this.contact = contact;
            this.additionalInfo = additionalInfo;
        }

        // Getters and setters for all fields
        public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getSsmNo() { return ssmNo; }
    public void setSsmNo(String ssmNo) { this.ssmNo = ssmNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getAdditionalInfo() { return additionalInfo; }
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }
}




