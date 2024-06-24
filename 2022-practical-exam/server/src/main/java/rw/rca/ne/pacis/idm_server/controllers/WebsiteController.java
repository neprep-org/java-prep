package rw.rca.ne.pacis.idm_server.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.rca.ne.pacis.idm_server.dtos.DownloadWebsiteDTO;
import rw.rca.ne.pacis.idm_server.services.ILinkService;
import rw.rca.ne.pacis.idm_server.services.IWebsiteService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/websites")
public class WebsiteController {

    private final IWebsiteService websiteService;

    private final ILinkService linkService;

    public WebsiteController(IWebsiteService websiteService, ILinkService linkService) {
        this.websiteService = websiteService;
        this.linkService = linkService;
    }

    @GetMapping
    public ResponseEntity<?> allDownloadedWebsites(){
        return ResponseEntity.ok(websiteService.getAllWebsitesDownloaded());
    }

    @GetMapping("/links/{websiteId}")
    public ResponseEntity<?> getLinksOfAWebsite(@PathVariable UUID websiteId) {
        return ResponseEntity.ok(linkService.findByWebsite(websiteId));
    }

    @PostMapping("/download")
    public ResponseEntity<?> download(@Valid @RequestBody DownloadWebsiteDTO website) {
        try{
            URL url = new URL(website.getUrl());
            return ResponseEntity.ok(websiteService.download(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
