package rw.rca.ne.pacis.idm_server.serviceImpls;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rw.rca.ne.pacis.idm_server.exceptions.ResourceNotFoundException;
import rw.rca.ne.pacis.idm_server.models.Website;
import rw.rca.ne.pacis.idm_server.repositories.IWebsiteRepository;
import rw.rca.ne.pacis.idm_server.services.ILinkService;
import rw.rca.ne.pacis.idm_server.services.IWebsiteService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.UUID;

@Repository
public class WebsiteServiceImpl implements IWebsiteService {

    private final IWebsiteRepository websiteRepository;

    private final ILinkService linkService;

    @Value("${upload.directory}")
    private String downloadFolder;

    public WebsiteServiceImpl(IWebsiteRepository websiteRepository, ILinkService linkService) {
        this.websiteRepository = websiteRepository;
        this.linkService = linkService;
    }

    @Override
    public List<Website> getAllWebsitesDownloaded() {
        return websiteRepository.findAll();
    }

    @Override
    public Website download(URL url) {
        try{
            Website website = new Website();
            website.setWebsiteName(url.getHost());
            website.setDownloadStartDateTime(LocalDateTime.now());

            String homeFileName = url.getFile().length() < 3 ? "index.html" : url.getFile();

            String folderPath = downloadFolder + "/"+ website.getWebsiteName() + "/";
            String linksPath = folderPath + "links";

            createFolder(linksPath);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath+homeFileName));

            String line;
            while((line = reader.readLine()) != null){
                writer.write(line);
            }
            reader.close();
            writer.close();

            website = websiteRepository.save(website);

            Set<String> links = getLinks(url.toExternalForm());
            for(String link: links){
                    link = filterLink(link, url.toExternalForm());
                    System.out.println(link);
                    URL linkUrl = new URL(url.toExternalForm() + "/"+link);

                    String filename = link;
                    filename = filterFileName(filename);

                    createAFile(linksPath+"/"+filename);

                    linkService.download(linkUrl,linksPath+"/"+filename,website);
                }

            website.setDownloadEndDateTime(LocalDateTime.now());
            website.setTotalElapsedTime(Duration.between(website.getDownloadStartDateTime(), website.getDownloadEndDateTime()).toMillis());
            File folder = new File(folderPath);
            website.setTotalDownloadedKilobytes(getFolderSize(folder) / 1024);

            return websiteRepository.save(website);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Website findById(UUID websiteId) {
        return websiteRepository.findById(websiteId).orElseThrow(()-> new ResourceNotFoundException("Website","id",websiteId));
    }

    public Set<String> getLinks(String url){
        Set<String> links = new HashSet<>();

        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        if(doc != null){
            Elements elements = doc.select("a[href]");
            for(Element element: elements){
                String link = element.attr("href");
                link = link.replace(url,"");
                if(
                        !( link.contains("http") || link.contains("www") || link.startsWith("#") || link.startsWith("./#") || link.startsWith("/#")
                                || link.equals("./") || link.contains("mailto") || link.isEmpty())
                ) {

                    links.add(link);
                }
            }
        }

        return links;

    }

    public String filterLink(String link, String url){
        link = link.contains(url) ? link.replace(url,"") : link;
        link = link.startsWith("/") ? link.substring(1) : link;
        link = link.replace("../","");
        link = link.replace("./","");
        link = link.replace("\n","");

        return link;
    }

    public String filterFileName(String filename){
        filename = filename.contains("?") ? filename.split("\\?")[0] : filename;
        if (filename.endsWith("/") || filename.endsWith("\\")) {
            filename = filename + "index.html";
        }

        return filename;
    }

    public void createFolder(String path){
        File folder = new File(path);

        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    public void createAFile(String path) throws IOException {
            File file = new File(path);
            if(!file.exists()){
                if(file.getParentFile() != null){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
    }

    public long getFolderSize(File folder){
        long size = 0;
        if(folder.isDirectory()){
            File[] files = folder.listFiles();
            for(File file: files){
                if(file.isFile()){
                    size += file.length();
                }
                else if(file.isDirectory()){
                    size += getFolderSize(file);
                }
            }
        }
        return size;
    }
}
