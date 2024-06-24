package rw.pacis.ne.equipment_distribution_system.payload;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthenticationResponse(String accessToken){
        this.accessToken = accessToken;
    }
}
