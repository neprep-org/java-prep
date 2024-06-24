package rw.rca.ne.pacis.idm_server.services;

import rw.rca.ne.pacis.idm_server.models.Link;
import rw.rca.ne.pacis.idm_server.models.Website;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface ILinkService {

    Link download(URL url,String path, Website website);

    List<Link> findByWebsite(UUID websiteId);
}
