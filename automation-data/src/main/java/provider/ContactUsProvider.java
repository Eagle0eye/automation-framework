package provider;

import DTO.ContactUs;
import org.testng.annotations.DataProvider;

import static variables.ContactUsVariables.*;


public class ContactUsProvider {

    @DataProvider(name = "SendValidInfo")
    public ContactUs[] validInfo(){
        return new ContactUs[] {
                ContactUs.builder()
                        .name(name)
                        .email(email)
                        .subject(subject)
                        .message(message)
                        .path(path)
                .build()};
    }

    @DataProvider(name = "SendWithoutEmail")
    public ContactUs[] SendWithoutEmail(){
        return new ContactUs[] {
                ContactUs.builder()
                        .name(name)
                        .email(null)
                        .subject(subject)
                        .message(message)
                        .path(path)
                .build()};
    }

}
