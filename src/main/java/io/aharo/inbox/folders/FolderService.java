package io.aharo.inbox.folders;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FolderService 
{
    public List<Folder> fetchDefaultFolders(String id)
    {
        return Arrays.asList
        (
            new Folder(id, "Inbox", "white"),
            new Folder(id, "Sent Items", "green"),
            new Folder(id, "Important", "red")  
        );
        
    }
    

    
}
