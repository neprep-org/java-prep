package rw.rca.ne.pacis.idm_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.rca.ne.pacis.idm_server.models.Website;

import java.util.UUID;

public interface IWebsiteRepository extends JpaRepository<Website, UUID> {
}
