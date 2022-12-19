package io.aharo.inbox.emaillist;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailListItemRepository extends CassandraRepository<EmailListItem, EmailListItemKey> 
{
    //List<EmailListItem> findAllById(EmailListItemKey id);   // EmailListItemKey --> key we need to fetch <:?:> EmailListItem --> what we get back    
    List<EmailListItem> findAllByKey_IdAndKey_Label(String id, String label);       // we dont want to map uuid soo we are updating the two paramerts we want
}
 