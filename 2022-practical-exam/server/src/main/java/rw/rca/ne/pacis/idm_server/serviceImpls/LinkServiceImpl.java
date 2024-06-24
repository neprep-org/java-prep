package rw.rca.ne.pacis.idm_server.serviceImpls;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rw.rca.ne.pacis.idm_server.models.Link;
import rw.rca.ne.pacis.idm_server.models.Website;
import rw.rca.ne.pacis.idm_server.repositories.ILinkRepository;
import rw.rca.ne.pacis.idm_server.services.ILinkService;
import rw.rca.ne.pacis.idm_server.services.IWebsiteService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LinkServiceImpl implements ILinkService {

    private final IWebsiteService websiteService;

    private final ILinkRepository linkRepository;

    public LinkServiceImpl(@Lazy IWebsiteService websiteService, ILinkRepository linkRepository) {
        this.websiteService = websiteService;
        this.linkRepository = linkRepository;
    }

    @Override
    public Link download(URL url, String path, Website website) {
        try{
            Link link = new Link();
            link.setLinkName(url.getFile());
            link.setWebsite(website);

            LocalDateTime start = LocalDateTime.now();

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            String line;
            while((line = reader.readLine()) != null){
                writer.write(line);
            }
            reader.close();
            writer.close();

            LocalDateTime end = LocalDateTime.now();

            link.setTotalElapsedTime(Duration.between(start,end).toMillis());
            link.setTotalDownloadedKilobytes(Files.size(Paths.get(path)) / 1024);

            return linkRepository.save(link);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<Link> findByWebsite(UUID websiteId) {
        Website website = websiteService.findById(websiteId);
        return linkRepository.findByWebsite(website);
    }
}
