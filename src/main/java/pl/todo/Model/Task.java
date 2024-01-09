package pl.todo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID externalId;
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;
    private Instant createdOn;
    private Instant modifyOn;
    private int version;

    private Task() {
    }

    private Task(Builder builder) {
        this.externalId = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.startTaskTime = builder.startTaskTime;
        this.endTaskTime = builder.endTaskTime;
        this.createdOn = builder.createdOn;
        this.modifyOn = builder.modifyOn;
        this.version = builder.version;
    }

    public long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getStartTaskTime() {
        return startTaskTime;
    }

    public Instant getEndTaskTime() {
        return endTaskTime;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Instant getModifyOn() {
        return modifyOn;
    }

    public int getVersion() {
        return version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTaskTime(Instant startTaskTime) {
        this.startTaskTime = startTaskTime;
    }

    public void setEndTaskTime(Instant endTaskTime) {
        this.endTaskTime = endTaskTime;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public void setModifyOn(Instant modifyOn) {
        this.modifyOn = modifyOn;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static class Builder {
        private UUID uuid;
        private String name;
        private String description;
        private Instant startTaskTime;
        private Instant endTaskTime;
        private Instant createdOn;
        private Instant modifyOn;
        private int version;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startTaskTime(Instant startTaskTime) {
            this.startTaskTime = startTaskTime;
            return this;
        }

        public Builder endTaskTime(Instant endTaskTime) {
            this.endTaskTime = endTaskTime;
            return this;
        }

        public Builder createdOn(Instant createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder setModifyOn(Instant modifyOn) {
            this.modifyOn = modifyOn;
            return this;
        }

        public Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid=" + externalId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTaskTime=" + startTaskTime +
                ", endTaskTime=" + endTaskTime +
                ", createdOn=" + createdOn +
                '}';
    }
}
