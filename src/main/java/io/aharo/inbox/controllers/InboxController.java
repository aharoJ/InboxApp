package io.aharo.inbox.controllers;

import io.aharo.inbox.emaillist.EmailListItem;
import io.aharo.inbox.emaillist.EmailListItemRepository;
import io.aharo.inbox.folders.Folder;
import io.aharo.inbox.folders.FolderRepository;
import io.aharo.inbox.folders.FolderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InboxController 
{
    @Autowired private FolderRepository folderRepository;
    @Autowired private FolderService folderService;
    @Autowired private EmailListItemRepository emailListItemRepository;

    @GetMapping(value = "/")
    //@RequestMapping("/")
    public String homePage(
            @AuthenticationPrincipal OAuth2User principal,
            Model model     // use model for templating/access;

    ) {

        if (principal == null || !StringUtils .hasText(principal.getAttribute("login")) )
        {
            return "index";
        }

        /*
         * Fetch the folders
         */
        String userId = principal.getAttribute("login");
       
        List<Folder> userFolders = folderRepository.findAllById(userId);
        model.addAttribute("userFolders", userFolders);

        List<Folder> defaulFolders = folderService.fetchDefaultFolders(userId);
        model.addAttribute("defaultFolders", defaulFolders);

        /*
         * fetch messages
         */
        String folderlabel = "Inbox";
        List<EmailListItem> emailList = emailListItemRepository
            .findAllByKey_IdAndKey_Label(userId, folderlabel);
        model.addAttribute("emailList", emailList);

        return "inbox-page";
    }
    
}