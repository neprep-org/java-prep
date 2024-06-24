package rw.rca.ne.pacis.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Website {

    private UUID id;

    private String websiteName;

    private String downloadStartDateTime;

    private String downloadEndDateTime;

    private Long totalElapsedTime;

    private Long totalDownloadedKilobytes;
}
