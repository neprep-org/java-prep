package rw.rca.ne.pacis.idm_server.services;


import rw.rca.ne.pacis.idm_server.models.Website;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface IWebsiteService {

    List<Website> getAllWebsitesDownloaded();

    Website download(URL url);

    Website findById(UUID websiteId);

}
