package io.aharo.inbox;


import io.aharo.inbox.emaillist.EmailListItem;
import io.aharo.inbox.emaillist.EmailListItemKey;
import io.aharo.inbox.emaillist.EmailListItemRepository;
import io.aharo.inbox.folders.Folder;
import io.aharo.inbox.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.Arrays;

@SpringBootApplication
@RestController
public class InboxApp
{
	@Autowired 
	private FolderRepository folderRepository;
	
	@Autowired
	private EmailListItemRepository emailListItemRepository;
 

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);
	}

	/**
	 * This is neccesary for the Spring Boot App to use the Astra secure bundle
	 * && connect to our database.
	 */
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer (DataStaxAstraProperties astraProperties)
	{
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void init()
	{
		folderRepository.save( new Folder("aharo","Inbox", "blue"));				// this is how we see data  <--> make the paths 
		folderRepository.save( new Folder("aharo","Sent", "green"));
		folderRepository.save( new Folder("aharo","Important", "yellow "));
//issue here
		for (int i=0; i < 10; ++ i)
		{
			EmailListItemKey key = new EmailListItemKey();
			key.setId("aharo");
			key.setLabel("Inbox");
			key.setTimeUuid(Uuids.timeBased());													// use api for time 

			EmailListItem item = new EmailListItem();
			item.setKey(key);
			item.setTo(Arrays.asList("aharo"));
			item.setSubject("Subject" + i);
			item.setUnread(true);

			emailListItemRepository.save(item);
		}
	}

}

  
