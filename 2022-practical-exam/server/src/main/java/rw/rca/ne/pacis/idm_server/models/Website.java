package rw.rca.ne.pacis.idm_server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="websites")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column(name = "website_name")
    private String websiteName;

    @Column(name = "download_start_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downloadStartDateTime;


    @Column(name = "download_end_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downloadEndDateTime;

    @Column(name="total_elapsed_time")
    private Long totalElapsedTime;

    @Column(name="total_downloaded_kilobytes")
    private Long totalDownloadedKilobytes;
}
