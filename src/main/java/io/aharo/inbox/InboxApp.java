package io.aharo.inbox;

import io.aharo.inbox.folders.Folder;
import io.aharo.inbox.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@SpringBootApplication
@RestController
public class InboxApp
{
	@Autowired 
	private FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);

		// System.out.println(folderRepository);
		// FolderRepository fr = new FolderRepository("aharo", "idk", "red");
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
		folderRepository.save( new Folder("aharo","Inbox", "blue"));
		folderRepository.save( new Folder("aharo","Sent", "green"));
		folderRepository.save( new Folder("aharo","Important", "yellow "));

	}




	// @RequestMapping("/user")
	// public String user(@AuthenticationPrincipal OAuth2User principal) {
	// 	System.out.println(principal);
	// 	return principal.getAttribute("name");
	// }
	

}
