package io.aharo.inbox.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InboxController 
{
    @Autowired private FolderRepository folderRepository;
    @Autowired private FolderService folderService;

    @GetMapping(value = "/")
    @RequestMapping("/")
    public String homePage(
            @AuthenticationPrincipal OAuth2User principal,
            Model model     // use model for templating/access;

    ) {

        if (principal == null || !StringUtils.hasText(principal.getAttribute("login")) )
        {
            return "index";
        }


        String loginId = principal.getAttribute("login");
       
        List<Folder> userFolders = folderRepository.findAllById(loginId);
        model.addAttribute("userFolders", userFolders);

        List<Folder> defaulFolders = folderService.fetchDefaultFolders(loginId);
        model.addAttribute("defaultFolders", defaulFolders);


        // I added these code to see if it fixes defaultFolders
        model.addAttribute("defaultFolders", defaulFolders);
        if (userFolders.size() > 0) {
            model.addAttribute("userFolders", userFolders);
        }

        return "inbox-page";
    }
    
}