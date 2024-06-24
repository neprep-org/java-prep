package rw.rca.ne.pacis.idm_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private Website website;

    @Column(name = "link_name")
    private String linkName;

    @Column(name="total_elapsed_time")
    private Long totalElapsedTime;

    @Column(name="total_downloaded_kilobytes")
    private Long totalDownloadedKilobytes;

}
