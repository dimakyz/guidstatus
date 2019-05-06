package guidstatus.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Entity")
public class Entity {

    @Id
    @ApiModelProperty(value = "guid")
    private String guid;
    @ApiModelProperty(required = true, value = "status")
    private Status status;
    @ApiModelProperty(required = true, value = "time")
    private String datetime;

    public Entity(){}

    public enum Status {
        CREATED,
        RUNNING,
        FINISHED
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "guid='" + guid + '\'' +
                ", status=" + status +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
