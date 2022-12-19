package io.aharo.inbox.emaillist;

import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;


@Table(value = "messages_by_user_folder")
public class EmailListItem 
{  
    
    @PrimaryKey                                                         // repository system design pattern
    private EmailListItemKey key;

    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> to;
    
    @CassandraType(type = Name.TEXT)
    private String subject;

    @CassandraType(type = Name.BOOLEAN)
    private boolean isUnread;

    @Transient  // not persistent with schema [temp]
    private String agoTimeString;

    public EmailListItem(EmailListItemKey key, List<String> to, String subject, boolean isUnread) {
        this.key = key;
        this.to = to;
        this.subject = subject;
        this.isUnread = isUnread;
    }

    

    public EmailListItem(){}

    public EmailListItemKey getKey() {
        return key;
    }

    public void setKey(EmailListItemKey key) {
        this.key = key;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean isUnread) {
        this.isUnread = isUnread;
    }



    public String getAgoTimeString() {
        return agoTimeString;
    }



    public void setAgoTimeString(String agoTimeString) {
        this.agoTimeString = agoTimeString;
    }

    


    


}