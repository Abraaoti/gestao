
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_device_metadatas")
public class DeviceMetadata  extends Entidade {
    private Long userId;
    private String deviceDetails;
    private String location;
    private Date lastLoggedIn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(String deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }
    
    

    @Override
    public String toString() {
        return "DeviceMetadata{" + "userId=" + userId + ", deviceDetails=" + deviceDetails + ", location=" + location + ", lastLoggedIn=" + lastLoggedIn + '}';
    }
    
    
}
