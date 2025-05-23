package Provider;

import DTO.ContactUs;
import org.testng.annotations.DataProvider;
import variables.ContactUsVariables;


public class ContactUsProvider {

    @DataProvider(name = "validInfo")
    public ContactUs[] validInfo(){
        ContactUs contactUs = new ContactUs();
        contactUs.setName(ContactUsVariables.name);
        contactUs.setEmail(ContactUsVariables.email);
        contactUs.setSubject(ContactUsVariables.subject);
        contactUs.setMessage(ContactUsVariables.message);
        contactUs.setPath(ContactUsVariables.path);
        return new ContactUs[] {contactUs};


    }

}
