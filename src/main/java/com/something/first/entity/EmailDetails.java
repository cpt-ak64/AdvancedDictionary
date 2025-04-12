package com.something.first.entity;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
// Class
public class EmailDetails {


    public String getRecipient() {
        return recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public String getAttachment() {
        return attachment;
    }

    // Class data members
    private final String recipient = "Kukretia8@gmail.com";
    @Setter
    private String msgBody;
    private final String subject = "Word Searched";
    @Setter
    private String attachment;
}
