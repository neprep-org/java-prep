package rw.rca.ne.pacis.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Link {
    private UUID id;

    private Website website;

    private String linkName;

    private Long totalElapsedTime;

    private Long totalDownloadedKilobytes;
}
