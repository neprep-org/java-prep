package rw.rca.ne.pacis.idm_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rca.ne.pacis.idm_server.models.Link;
import rw.rca.ne.pacis.idm_server.models.Website;

import java.util.List;
import java.util.UUID;

public interface ILinkRepository extends JpaRepository<Link, UUID> {

    List<Link> findByWebsite(Website website);
}
